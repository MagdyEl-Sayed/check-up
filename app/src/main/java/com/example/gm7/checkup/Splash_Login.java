package com.example.gm7.checkup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.askerlap.emadahmed.checkup.R;


public class Splash_Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__login);
        assert getSupportActionBar() !=null;
        getSupportActionBar().hide();
        Button login=(Button)findViewById(R.id.btn_log_in);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( Splash_Login.this,Login.class));
            }
        });
        Button signup=(Button)findViewById(R.id.btn_sign_up);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( Splash_Login.this,SignUPActivity.class));

            }
        });


    }
}
