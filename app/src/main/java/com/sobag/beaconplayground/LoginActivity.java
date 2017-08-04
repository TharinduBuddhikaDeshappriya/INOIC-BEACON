package com.sobag.beaconplayground;

/**
 * Created by bdesh on 7/25/2017.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends Activity {

    private static final String url = "jdbc:mysql://inoicbeacon.mysql.database.azure.com:3306/inoicbeacon";
    private static final String user = "inoic@inoicbeacon";
    private static final String pass = "test@123";
    private static final String LOG_TAG = "LoginActivity";
    private TextView txtEmail, txtPassword;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from new_activity.xml
        setContentView(R.layout.acivity_login);

        txtEmail = (TextView) findViewById(R.id.txtLoginEmail);
        txtPassword = (TextView) findViewById(R.id.txtLoginPassword);
        Button ButtonLogin = (Button) findViewById(R.id.btnLogin);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        ButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                ProgressDialog dialog = new ProgressDialog(LoginActivity.this); // this = YourActivity
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.setMessage("Logging in... Please wait...");
                dialog.setIndeterminate(true);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();

                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();

                if (email.equals("") || password.equals("") ) {
                    Toast.makeText(LoginActivity.this, "Please Fill All The Fields!!!",
                            Toast.LENGTH_LONG).show();
                } else {
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection(url, user, pass);
                        Log.i(LOG_TAG, "Connection Success ");
                        Statement myStmt = con.createStatement();
                        String sql = "SELECT `userEmail`, `userPassword` FROM `user` WHERE `userEmail` = '" + email + "' AND `userPassword` = '" + password + "' " ;
                        ResultSet RS = myStmt.executeQuery(sql);
                        if(RS.next()){
                            Toast.makeText(LoginActivity.this, "Login Successful !!!",
                                    Toast.LENGTH_LONG).show();

                            Intent myIntent = new Intent(LoginActivity.this,
                                    MainActivity.class);
                            startActivity(myIntent);
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Login Failed !!!",
                                    Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }

            }
        });
    }
}
