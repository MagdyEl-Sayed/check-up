package com.example.gm7.checkup;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUPActivity extends Activity {
    EditText editTextUserName, editTextPassword, editTextConfirmPassword, editTextEmail;
    Button btnCreateAccount, btnrecoverypass;

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
        btnrecoverypass = (Button) findViewById(R.id.buttonrecoverypass);
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
                    editTextUserName.setError("UserName Should not be blank");
                    return;
                }
                if (editTextEmail.getText().toString().isEmpty()) {
                    editTextEmail.setError("Email Should not be blank");
                    return;
                }
                if (!editTextEmail.getText().toString().isEmpty()) {
                    String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
                    java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
                    java.util.regex.Matcher m = p.matcher(email);
                    if (!m.matches()) {
                        editTextEmail.setError("Please Check and Enter a valid Email Address");
                        return;
                    }

                }
                if (editTextPassword.getText().toString().isEmpty()) {
                    editTextPassword.setError("Password Should not be blank");
                    return;
                }
                if (password.length() < 8) {
                    editTextPassword.setError("Enter at least 8 characters.");
                    return;
                }
                if (editTextConfirmPassword.getText().toString().isEmpty()) {
                    editTextConfirmPassword.setError("Password Should not be blank");
                    return;
                }

                // check if both password matches
                if (!password.equals(confirmPassword)) {
                    //Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                    editTextConfirmPassword.setError("Password does not match");
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
                        progressDialog.setMessage("Authenticating...");
                        progressDialog.show();
                        loginDataBaseAdapter.insertEntry(userName, password, email);
                        new android.os.Handler().postDelayed(
                                new Runnable() {
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(SignUPActivity.this, Login.class);
                                        startActivity(intent);
                                        progressDialog.dismiss();
                                    }
                                }, 3000);

                    } else {
                        editTextEmail.setError("This Email EXIST");
                        Toast.makeText(getApplicationContext(), "This Email Already Exist", Toast.LENGTH_LONG).show();
                        return;
                    }
                }

            }
        });
        btnrecoverypass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmail.getText().toString();
                String storedPassword = loginDataBaseAdapter.recoverypass(email);
                sendEmail(email, storedPassword);


            }
        });
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        loginDataBaseAdapter.close();
    }

    protected void sendEmail(String mail, String message) {
        Log.i("Send email", "");
        //String[] TO = {""};
        //String[] message = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, mail);
        //emailIntent.putExtra(Intent.EXTRA_CC, CC);
        //emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(SignUPActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
