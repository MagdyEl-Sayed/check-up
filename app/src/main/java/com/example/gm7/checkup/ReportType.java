package com.example.gm7.checkup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.askerlap.emadahmed.checkup.R;

public class ReportType extends AppCompatActivity {
private Button btnDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_type);
        btnDate=(Button)findViewById(R.id.btn_Repo_date);
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ReportType.this,BillDetails.class));
            }
        });
    }
}
