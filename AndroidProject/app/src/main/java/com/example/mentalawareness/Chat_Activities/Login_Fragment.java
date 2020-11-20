package com.example.mentalawareness.Chat_Activities;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mentalawareness.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Login_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Login_Fragment extends Fragment {

    public interface Login_Responder {
        void loginUser(String UID);
       void swapToSignUp();
    }


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private EditText loginIDET;

    private Login_Fragment.Login_Responder myActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        myActivity = (Login_Responder) context;
    }

    public Login_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Login_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Login_Fragment newInstance() {
        Login_Fragment fragment = new Login_Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View vf = inflater.inflate(R.layout.fragment_login, container, false);

        Button LoginFrgBTN = vf.findViewById(R.id.LoginFrgBTN);
        LoginFrgBTN.setOnClickListener(view -> {
            loginIDET = vf.findViewById(R.id.loginIDET);
            String UID = "";
            try {
                UID = loginIDET.getText().toString();
            }catch (Exception e) {
                Toast.makeText(getContext(), "IncorrectI D", Toast.LENGTH_SHORT).show();
                loginIDET.setText("");
                return;
            }
            myActivity.loginUser(UID.trim());
            loginIDET.setText("");
        });

        Button toSignUp = vf.findViewById(R.id.toSignUpBTN);
        toSignUp.setOnClickListener(view -> myActivity.swapToSignUp());

        return vf;
    }
}