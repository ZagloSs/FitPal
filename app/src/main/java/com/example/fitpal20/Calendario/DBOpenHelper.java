package com.example.fitpal20.Calendario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.fitpal20.retrofit.APIClient;
import com.example.fitpal20.retrofit.APIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DBOpenHelper extends SQLiteOpenHelper  {

    APIService apiService;
    APIClient apiClient = new APIClient();
    private final static String CREATE_EVENTS_TABLE = "create table "+DBStructure.EVENT_TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
            +DBStructure.EVENT+" TEXT, "+DBStructure.TIME+" TEXT, "+DBStructure.DATE+" TEXT, "+DBStructure.MONTH+" TEXT,"
            +DBStructure.YEAR+" TEXT)";

    private static final  String DROP_EVENTS_TABLE= "DROP TABLE IF EXISTS "+DBStructure.EVENT_TABLE_NAME;

    public DBOpenHelper(@Nullable Context context) {
        super(context, DBStructure.DB_NAME, null, DBStructure.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_EVENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_EVENTS_TABLE);
        onCreate(db);
    }



    public Cursor ReadEvents(String date, SQLiteDatabase database){
        String [] Projections = {DBStructure.EVENT,DBStructure.TIME,DBStructure.DATE,DBStructure.MONTH,DBStructure.YEAR};
        String Selection = DBStructure.DATE +"=?";
        String [] SelectionArgs = {date};

        return database.query(DBStructure.EVENT_TABLE_NAME,Projections,Selection,SelectionArgs,null,null,null);
    }
    public Cursor ReadEventsperMonth(String month, String year, SQLiteDatabase database){
        String [] Projections = {DBStructure.EVENT,DBStructure.TIME,DBStructure.DATE,DBStructure.MONTH,DBStructure.YEAR};
        String Selection = DBStructure.MONTH +"=? and "+DBStructure.YEAR+"=?";
        String [] SelectionArgs = {month,year};

        return database.query(DBStructure.EVENT_TABLE_NAME,Projections,Selection,SelectionArgs,null,null,null);
    }
}
