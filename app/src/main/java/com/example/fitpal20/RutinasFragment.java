package com.example.fitpal20;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fitpal20.models.RutineModel;
import com.example.fitpal20.rvadapters.RutineRecylerViewAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RutinasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RutinasFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    ArrayList<RutineModel> RutineModels = new ArrayList<>();
    RutineRecylerViewAdapter adapter;
    public RutinasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeRutinas.
     */
    // TODO: Rename and change types and number of parameters
    public static RutinasFragment newInstance(String param1, String param2) {
        RutinasFragment fragment = new RutinasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rutinas, container, false);
        RecyclerView rv =view.findViewById(R.id.recylcerViewRutine);
        setRutines();


        adapter = new RutineRecylerViewAdapter(view.getContext(), RutineModels);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(view.getContext()));



        return view;
    }

    private void setRutines(){
        String[] rNames = {"Carga Alta", "PushPullLegs", "Sam's"};
        String[] rDays = {"L,M,X", "J,V,S", "L,J,D"};

        for(int i = 0;  i < rNames.length; i++){
            RutineModels.add(new RutineModel(rNames[i], rDays[i]));
        }
    }
}