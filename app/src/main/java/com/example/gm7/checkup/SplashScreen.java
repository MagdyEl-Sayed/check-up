package com.example.gm7.checkup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.askerlap.emadahmed.checkup.R;

/**
 * Created by PEACE on 3/30/2016.
 */
public class SplashScreen extends AppCompatActivity {
private LoginDataBaseAdapter login_helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        assert getSupportActionBar() !=null;
        getSupportActionBar().hide();
        login_helper=new LoginDataBaseAdapter(this);
        login_helper.open();

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(700);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{

                    if(login_helper.getUserName()!=null&& login_helper.getLoginFlag().equals("true")) {
                        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Intent intent = new Intent(SplashScreen.this, Login.class);
                        startActivity(intent);
                    }

                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        //Intent intent = new Intent(SplashScreen.this,MainActivity.class);
        //startActivity(intent);
    }


}
