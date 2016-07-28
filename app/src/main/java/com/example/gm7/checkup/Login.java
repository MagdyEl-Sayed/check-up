package com.example.gm7.checkup;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.media.Image;
import android.os.Bundle;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.askerlap.emadahmed.checkup.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

/**
 * Created by GM7 on 03/07/2016.
 */

public class Login extends AppCompatActivity {
    EditText txt1, txt2;
    TextView txt;
//    ImageView img;
    Button btn1;
    LoginButton faceelogin;
    TwitterLoginButton twitterlogin;
    LoginDataBaseAdapter loginDataBaseAdapter;
    private DB_shoppingHelper shopHelper;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        facebookSDKInitialize();
        setContentView(R.layout.activity_main);
        assert getSupportActionBar() !=null;
        getSupportActionBar().hide();
        //
        SpannableString content = new SpannableString("Content");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        //assert  getSupportActionBar() !=null;
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        shopHelper=new DB_shoppingHelper(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();
        txt=(TextView)findViewById(R.id.signup);
        txt.setPaintFlags(txt.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,SignUPActivity.class));
            }
        });
        //
        txt1 = (EditText) findViewById(R.id.editText);
        txt2 = (EditText) findViewById(R.id.editText2);

//        img = (ImageView) findViewById(R.id.errormessage);
        btn1 = (Button) findViewById(R.id.btn);
        faceelogin = (LoginButton) findViewById(R.id.fb_login);
        faceelogin.setReadPermissions("email");
        twitterlogin = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        twitterlogin.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                TwitterSession session=result.data;
                String user_Name=session.getUserName();
                Intent i=new Intent(Login.this, MainActivity.class);
                i.putExtra("username",user_Name);
                shopHelper.getWritableDatabase();
                //shopHelper.insertEntry(user_Name);
                startActivity(i);
            }
            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
            }
        });

        getLoginDetails(faceelogin);

        btn1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // get The User name and Password
                String userName = txt1.getText().toString();
                String password = txt2.getText().toString();

                // fetch the Password form database for respective user name
                String storedPassword = loginDataBaseAdapter.getSinlgeEntry(userName);
                String storedPassword1 = loginDataBaseAdapter.getSinlgeEnt(userName);
                Toast.makeText(getBaseContext(),storedPassword,Toast.LENGTH_SHORT).show();
                //
                if (txt1.getText().toString().isEmpty()) {
                    txt1.setError("UserName Should not be blank");
                    return;
                }
                if (txt2.getText().toString().isEmpty()) {
                    txt2.setError("Password Should not be blank");
                    return;
                }

                // check if the Stored password matches with  Password entered by user
                else if (password.equals(storedPassword) || password.equals(storedPassword1)) {
                    // progressDialog = ProgressDialog.show(Login.this,"Please wait", "Sending mail", true, false);
                    // progressDialog = ProgressDialog.show(Login.this, "", "Loading...");
                    final ProgressDialog progressDialog = new ProgressDialog(Login.this);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setMessage("Authenticating...");
                    progressDialog.show();
                    new android.os.Handler().postDelayed(
                            new Runnable() {
                                public void run() {
                                    // On complete call either onLoginSuccess or onLoginFailed
                                    //onLoginSuccess();
                                    // onLoginFailed();
                                    // Toast.makeText(getBaseContext(), "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(Login.this, MainActivity.class);
                                    startActivity(intent);
//                                    img.setVisibility(View.INVISIBLE);
                                    progressDialog.dismiss();
                                }
                            }, 3000);


                } else {
//                    img.setVisibility(View.VISIBLE);
                    Toast.makeText(getBaseContext(), "User Name or Password does not match", Toast.LENGTH_LONG).show();
                }


            }
        });

    }

protected void facebookSDKInitialize(){
    FacebookSdk.sdkInitialize(getApplicationContext());
    callbackManager=CallbackManager.Factory.create();

}
    protected void getLoginDetails(LoginButton loginButton){
        //call back registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                 Intent i=new Intent(Login.this, MainActivity.class);
                startActivity(i);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
        twitterlogin.onActivityResult(requestCode,resultCode,data);
        Log.e("data",data.toString());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onPostResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close The Database
        loginDataBaseAdapter.close();
    }
/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
switch (item.getItemId()){
    case android.R.id.home:
        NavUtils.navigateUpFromSameTask(this);
        return true;
}
        return super.onOptionsItemSelected(item);
    }*/
}
