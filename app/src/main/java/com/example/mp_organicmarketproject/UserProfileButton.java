package com.example.mp_organicmarketproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

public class UserProfileButton extends Fragment {




    private FirebaseAuth myAuth;
    Button settingsButton;
    Button buttonProfile;
    Button faqButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myAuth = FirebaseAuth.getInstance();
    }



    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.userprofile_fragment, viewGroup, false);
        //TextView output= (TextView)view.findViewById(R.id.main_TextViewUser);
        //output.setText("User Profile Fragment");

        Button button = (Button)view.findViewById(R.id.main_buttonLogout);
        settingsButton = view.findViewById(R.id.userSettingsButton);
        buttonProfile = (Button)view.findViewById(R.id.myProfile);
        faqButton = (Button)view.findViewById(R.id.faqButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAuth.signOut();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();

            }
        });




        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSetting = new Intent(getActivity(), UserProfileActivity.class);
                startActivity(goToSetting);

            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserSettingsActivity.class);
                startActivity(intent);

            }
        });

        faqButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToFaq = new Intent (getActivity(), Faq_Activity.class);
                startActivity(goToFaq);
            }
        });


        return view;


    }

    public UserProfileButton(){

    }



/*

     @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.userprofile_fragment, container, false);
    }
*/
}
