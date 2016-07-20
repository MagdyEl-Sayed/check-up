package com.example.gm7.checkup;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.gm7.checkup.R.id.activity_main;

public class MainActivity extends AppCompatActivity {
int counter=0;
    private ImageButton shopping;
    //Fuck u _!_
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_menu   );
        shopping=(ImageButton) findViewById(R.id.btn_shopping);
        Toast.makeText(getApplicationContext(),"hhhhhhhhhhhhhhhhh",Toast.LENGTH_LONG).show();

        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Main_Menu.class));
                Toast.makeText(getApplicationContext(),"shopping clicked",Toast.LENGTH_LONG).show();
            }
        });


    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
counter++;
    }

}
