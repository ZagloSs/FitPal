package com.example.fitpal20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }

    /**
     * A simple {@link Fragment} subclass.
     * Use the {@link ProfileFragment#newInstance} factory method to
     * create an instance of this fragment.
     */
    public static class ProfileFragment extends Fragment {

        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private static final String ARG_PARAM1 = "param1";
        private static final String ARG_PARAM2 = "param2";

        // TODO: Rename and change types of parameters
        private String mParam1;
        private String mParam2;

        Button logoutbtn;
        ImageView btnEditName, btnEditEmail;

        EditText editName, editEmail;

        TextView name, email, acceptChangeName,acceptChangeEmail;

        public ProfileFragment() {
            // Required empty public constructor
        }

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        public static ProfileFragment newInstance(String param1, String param2) {
            ProfileFragment fragment = new ProfileFragment();
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
            View view = inflater.inflate(R.layout.fragment_profile, container, false);
            logoutbtn = view.findViewById(R.id.btnCloseSes);
            btnEditName = view.findViewById(R.id.imgEditName);
            btnEditEmail = view.findViewById(R.id.imgEditEmail);

            name = view.findViewById(R.id.tv_nameProfile);
            email= view.findViewById(R.id.tv_emailProfile);

            editName = view.findViewById(R.id.editTextName);
            acceptChangeName = view.findViewById(R.id.acceptNameChange);

            editEmail = view.findViewById(R.id.editTextEmail);
            acceptChangeEmail = view.findViewById(R.id.acceptEmailChange);

            editName.setVisibility(View.GONE);
            editEmail.setVisibility(View.GONE);
            acceptChangeName.setVisibility(View.GONE);
            acceptChangeEmail.setVisibility(View.GONE);

            btnEditName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editName.setText(name.getText().toString());
                    editName.setVisibility(View.VISIBLE);
                    acceptChangeName.setVisibility(View.VISIBLE);
                    name.setVisibility(View.GONE);
                    btnEditName.setVisibility(View.GONE);
                    editName.requestFocus();

                }
            });
            acceptChangeName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editName.setVisibility(View.GONE);
                    acceptChangeName.setVisibility(View.GONE);
                    name.setText(editName.getText().toString());
                    name.setVisibility(View.VISIBLE);
                    btnEditName.setVisibility(View.VISIBLE);
                }
            });


            btnEditEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editEmail.setText(email.getText().toString());
                    editEmail.setVisibility(View.VISIBLE);
                    acceptChangeEmail.setVisibility(View.VISIBLE);
                    email.setVisibility(View.GONE);
                    btnEditEmail.setVisibility(View.GONE);
                    editEmail.requestFocus();
                }
            });
            acceptChangeEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editEmail.setVisibility(View.GONE);
                    acceptChangeEmail.setVisibility(View.GONE);
                    email.setText(editEmail.getText().toString());
                    email.setVisibility(View.VISIBLE);
                    btnEditEmail.setVisibility(View.VISIBLE);
                }
            });


            logoutbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), Lobby.class);
                    startActivity(intent);
                }
            });

            return view;
        }



    }
}