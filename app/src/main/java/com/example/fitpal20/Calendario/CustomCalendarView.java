package com.example.fitpal20.Calendario;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitpal20.HomeFragment;
import com.example.fitpal20.R;
import com.example.fitpal20.models.Usuarios_dias;
import com.example.fitpal20.retrofit.APIClient;
import com.example.fitpal20.retrofit.APIService;
import com.example.fitpal20.retrofit.respuestas.RespuestaUsuario;
import com.example.fitpal20.retrofit.respuestas.ResupuestaDiasAsist;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomCalendarView extends LinearLayout {
    ImageButton NextButton, PreviousButton;
    TextView CurrentDate;
    GridView gridView;
    private static final int MAX_CALENDAR_DAYS = 42;
    Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
    Context context;
    SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM yyyy",Locale.ENGLISH);
    SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM",Locale.ENGLISH);
    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy",Locale.ENGLISH);
    SimpleDateFormat eventDateFormate = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH);

    MyGridAdapter myGridAdapter;
    AlertDialog alertDialog;
    List<Date> dates = new ArrayList<>();
    List<Events> eventsList = new ArrayList<>();


    APIService apiService;
    APIClient apiClient = new APIClient();
    DBOpenHelper dbOpenHelper;

    int contadorDias = 0;

    public CustomCalendarView(Context context) {
        super(context);
    }

    public CustomCalendarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        apiClient = APIClient.getInstance();
        apiClient.ApiClient();
        apiService = apiClient.getApiService();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.calendar_layout, this);
        NextButton = view.findViewById(R.id.nextBtn);
        PreviousButton = view.findViewById(R.id.previousBtn);
        CurrentDate = view.findViewById(R.id.current_Date);
        gridView = view.findViewById(R.id.gridview);
        SetUpCalendar();
        if(apiService != null){
            Call<List<Usuarios_dias>> usersDays = apiService.getDiasByUserId(RespuestaUsuario.getInstance().getUsuario().getId());
            usersDays.enqueue(new Callback<List<Usuarios_dias>>() {
                @Override
                public void onResponse(Call<List<Usuarios_dias>> call, Response<List<Usuarios_dias>> response) {
                    if(response.isSuccessful()){
                        List<Usuarios_dias> usuarios_dias = response.body();
                        for(Usuarios_dias dias: usuarios_dias){
                            Call<Events> diasUser = apiService.getDaysById(dias.getIdDia());
                            diasUser.enqueue(new Callback<Events>() {
                                @Override
                                public void onResponse(Call<Events> call, Response<Events> response) {
                                    eventsList.add(response.body());
                                    contadorDias++;
                                    ResupuestaDiasAsist.getInstance().setDiasAsist(contadorDias);
                                    SetUpCalendar();

                                }

                                @Override
                                public void onFailure(Call<Events> call, Throwable t) {
                                    SetUpCalendar();
                                }
                            });
                        }
                    }
                }
                @Override
                public void onFailure(Call<List<Usuarios_dias>> call, Throwable t) {
                    SetUpCalendar();
                }
            });
        }

        PreviousButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH, -1);
                SetUpCalendar();
            }
        });

        NextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH, 1);
                SetUpCalendar();
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(true);
                final View addView = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_newevent_layout,null);
                EditText EventName = addView.findViewById(R.id.eventname);
                TextView EventTime = addView.findViewById(R.id.eventtime);
                ImageButton SetTime = addView.findViewById(R.id.seteventime);
                Button AddEvent = addView.findViewById(R.id.addevent);
                SetTime.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar = Calendar.getInstance();
                        int hours = calendar.get(Calendar.HOUR_OF_DAY);
                        int minuts = calendar.get(Calendar.MINUTE);
                        TimePickerDialog timePickerDialog = new TimePickerDialog(addView.getContext(), R.style.Theme_FitPal20, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                Calendar c = Calendar.getInstance();
                                c.set(Calendar.HOUR_OF_DAY,hourOfDay);
                                c.set(Calendar.MINUTE,minute);
                                c.setTimeZone(TimeZone.getDefault());
                                SimpleDateFormat hformate = new SimpleDateFormat("K:mm a",Locale.ENGLISH);
                                String event_Time =hformate.format(c.getTime());
                                EventTime.setText(event_Time);
                            }
                        },hours,minuts,false);
                        timePickerDialog.show();
                    }
                });
                final String date =eventDateFormate.format(dates.get(position));
                final String month =monthFormat.format(dates.get(position));
                final String year =yearFormat.format(dates.get(position));


                AddEvent.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SaveEvent(EventName.getText().toString(),EventTime.getText().toString(),date,month,year);
                        SetUpCalendar();
                        alertDialog.dismiss();
                    }
                });

                builder.setView(addView);
                alertDialog = builder.create();
                alertDialog.show();
            }
        });

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String date = eventDateFormate.format(dates.get(position));

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(true);
                View showView = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_events_layout,null);
                RecyclerView recyclerView = showView.findViewById(R.id.EventsRV);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(showView.getContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);
                EventRecyclerAdapter eventRecyclerAdapter = new EventRecyclerAdapter(showView.getContext(),CollectEventByDate(date));
                recyclerView.setAdapter(eventRecyclerAdapter);
                eventRecyclerAdapter.notifyDataSetChanged();

                builder.setView(showView);
                alertDialog = builder.create();
                alertDialog.show();

                return true;
            }
        });
    }

    private ArrayList<Events> CollectEventByDate(String date){
        ArrayList<Events> arrayList = new ArrayList<>();
        dbOpenHelper = new DBOpenHelper(context);
        SQLiteDatabase database = dbOpenHelper.getReadableDatabase();
        Cursor cursor = dbOpenHelper.ReadEvents(date, database);
        while(cursor.moveToNext()){
            @SuppressLint("Range") String event = cursor.getString(cursor.getColumnIndex(DBStructure.EVENT));
            @SuppressLint("Range") String time = cursor.getString(cursor.getColumnIndex(DBStructure.TIME));
            @SuppressLint("Range") String Date = cursor.getString(cursor.getColumnIndex(DBStructure.DATE));
            @SuppressLint("Range") String month = cursor.getString(cursor.getColumnIndex(DBStructure.MONTH));
            @SuppressLint("Range") String Year = cursor.getString(cursor.getColumnIndex(DBStructure.YEAR));

            Events events = new Events(event,time,Date,month,Year);
            arrayList.add(events);
        }
        cursor.close();
        dbOpenHelper.close();

        return arrayList;
    }


    public CustomCalendarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void SaveEvent(String event, String time, String date, String month, String year){
        Events newEvent = new Events(event, time, date, month, year);

        //Instancia de la api
        apiClient = APIClient.getInstance();
        apiClient.ApiClient();
        apiService = apiClient.getApiService();

        if(apiService != null){
            Call<Events> saveEvent = apiService.postDia(newEvent);
            saveEvent.enqueue(new Callback<Events>() {
                @Override
                public void onResponse(Call<Events> call, Response<Events> response) {
                    Log.d("Lo gualdo", "si");
                    Usuarios_dias usuarios_dias = new Usuarios_dias(response.body().getId(), RespuestaUsuario.getInstance().getUsuario().getId());
                    Call<Usuarios_dias> addDayToUser = apiService.postUsuarioDia(usuarios_dias);
                    addDayToUser.enqueue(new Callback<Usuarios_dias>() {
                        @Override
                        public void onResponse(Call<Usuarios_dias> call, Response<Usuarios_dias> response) {
                            Toast.makeText(getContext(), "Dia añadido al usuario", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Usuarios_dias> call, Throwable t) {

                        }
                    });

                }

                @Override
                public void onFailure(Call<Events> call, Throwable t) {
                    Log.d("Lo gualdo", "no");
                }
            });
        }
    }
    private void SetUpCalendar(){
        String currentDate = dateFormat.format(calendar.getTime());
        CurrentDate.setText(currentDate);
        dates.clear();
        Calendar monthCalendar= (Calendar) calendar.clone();
        monthCalendar.set(Calendar.DAY_OF_MONTH,1);
        int FirstDayOfMonth = monthCalendar.get(Calendar.DAY_OF_WEEK)-1;
        monthCalendar.add(Calendar.DAY_OF_MONTH, -FirstDayOfMonth);

        while (dates.size() < MAX_CALENDAR_DAYS){
            dates.add(monthCalendar.getTime());
            monthCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        myGridAdapter = new MyGridAdapter(context,dates,calendar,eventsList);
        gridView.setAdapter(myGridAdapter);
    }

}
