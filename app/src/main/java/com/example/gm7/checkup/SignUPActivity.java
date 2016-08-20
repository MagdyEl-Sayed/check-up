package com.example.gm7.checkup;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.askerlap.emadahmed.checkup.R;

public class SignUPActivity extends AppCompatActivity {
    EditText editTextUserName, editTextPassword, editTextConfirmPassword, editTextEmail;
    Button btnCreateAccount;

    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 0);
        setContentView(R.layout.signup);

        // get Instance  of Database Adapter
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        // Get Refferences of Views
        editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);

        btnCreateAccount = (Button) findViewById(R.id.buttonCreateAccount);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub

                String userName = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();
                String confirmPassword = editTextConfirmPassword.getText().toString();
                String email = editTextEmail.getText().toString();
                // check if any of the fields are vaccant
                //	if(userName.equals("")||password.equals("")||confirmPassword.equals("")||email.equals(""))
                //	{
                //Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
                if (editTextUserName.getText().toString().isEmpty()) {
                    editTextUserName.setError(getResources().getString(R.string.signup_user_error));
                    return;
                }
                if (editTextEmail.getText().toString().isEmpty()) {
                    editTextEmail.setError(getResources().getString(R.string.signup_email_error));
                    return;
                }
                if (!editTextEmail.getText().toString().isEmpty()) {
                    String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
                    java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
                    java.util.regex.Matcher m = p.matcher(email);
                    if (!m.matches()) {
                        editTextEmail.setError(getResources().getString(R.string.signup_vaild_email));
                        return;
                    }

                }
                if (editTextPassword.getText().toString().isEmpty()) {
                    editTextPassword.setError(getResources().getString(R.string.signup_pass_error));
                    return;
                }
                if (password.length() < 8) {
                    editTextPassword.setError(getResources().getString(R.string.signup_pass_length_error));
                    return;
                }
                if (editTextConfirmPassword.getText().toString().isEmpty()) {
                    editTextConfirmPassword.setError(getResources().getString(R.string.signup_pass_error));
                    return;
                }

                // check if both password matches
                if (!password.equals(confirmPassword)) {
                    //Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                    editTextConfirmPassword.setError(getResources().getString(R.string.signup_pass_match));
                    return;
                }
                //
                if (true) {
                    String storedPassword = loginDataBaseAdapter.check(email);
                    //Toast.makeText(getApplicationContext(), storedPassword, Toast.LENGTH_LONG).show();

                    if (storedPassword.equals("NOT EXIST")) {
                        // Save the Data in Database
                        final ProgressDialog progressDialog = new ProgressDialog(SignUPActivity.this);
                        progressDialog.setIndeterminate(true);
                        progressDialog.setMessage(getResources().getString(R.string.auth));
                        progressDialog.show();
                        loginDataBaseAdapter.insertEntry(userName, password, email);
                        new android.os.Handler().postDelayed(
                                new Runnable() {
                                    public void run() {
                                        Toast.makeText(getApplicationContext(),getResources().getString(R.string.account_succ), Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(SignUPActivity.this, Login.class);
                                        startActivity(intent);
                                        progressDialog.dismiss();
                                    }
                                }, 3000);

                    } else {
                        editTextEmail.setError(getResources().getString(R.string.signup_email_exist));
                        return;
                    }
                }

            }
        });

    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        loginDataBaseAdapter.close();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
switch (item.getItemId()){
    case android.R.id.home:
        NavUtils.navigateUpFromSameTask(this);
        return true;
}
        return super.onOptionsItemSelected(item);
    }
}
