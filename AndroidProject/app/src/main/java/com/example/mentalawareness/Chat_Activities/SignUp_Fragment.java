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
 * Use the {@link SignUp_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUp_Fragment extends Fragment {

    public interface SignUp_Responder {
        void CreateUser(String userID, String userName);
        void swapToLogin();
    }


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText userIdET;
    private EditText userNameET;

    private SignUp_Fragment.SignUp_Responder myActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        myActivity = (SignUp_Fragment.SignUp_Responder) context;
    }

    public SignUp_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SignUp_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUp_Fragment newInstance() {
        SignUp_Fragment fragment = new SignUp_Fragment();
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
        final View vf = inflater.inflate(R.layout.fragment_sign_up, container, false);

        Button signUpBTN = vf.findViewById(R.id.signUpSUBTN);
        signUpBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userIdET = vf.findViewById(R.id.userIdET);
                userNameET = vf.findViewById(R.id.userNameET);
                String userID = "";
                String userName = "";

                userID = userIdET.getText().toString();
                userName = userNameET.getText().toString();
                if(userID.equals("") || userName.equals("")) {
                    Toast.makeText(getContext(), "Incorrect User ID or User Name", Toast.LENGTH_SHORT).show();
                    userIdET.setText("");
                    userNameET.setText("");
                    return;
                }
                /*
                try {
                    userID = userIdET.getText().toString();
                    userName = userNameET.getText().toString();
                }catch (Exception e) {
                    Toast.makeText(getContext(), "Incorrect User ID or User Name", Toast.LENGTH_SHORT).show();
                    userIdET.setText("");
                    userNameET.setText("");
                    return;
                }*/
                myActivity.CreateUser(userID, userName);
                myActivity.swapToLogin();

            }
        });

        Button toLogin = vf.findViewById(R.id.toLoginBTN);
        toLogin.setOnClickListener(view -> myActivity.swapToLogin());

        return vf;
    }
}