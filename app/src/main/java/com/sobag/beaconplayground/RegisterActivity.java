package com.sobag.beaconplayground;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by bdesh on 7/25/2017.
 */

public class RegisterActivity extends Activity {

    private static final String url = "jdbc:mysql://inoicbeacon.mysql.database.azure.com:3306/inoicbeacon";
    private static final String user = "inoic@inoicbeacon";
    private static final String pass = "test@123";

    private static final String LOG_TAG = "RegisterActivity";

    TextView txtRegisterEmail, txtRegisterPassword, txtRegisterPhoneNumber, txtRegisterUsername;
    Button btnRegister;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtRegisterEmail = (TextView) findViewById(R.id.txtRegisterEmail);
        txtRegisterPassword = (TextView) findViewById(R.id.txtRegisterPassword);
        txtRegisterPhoneNumber = (TextView) findViewById(R.id.txtRegisterPhoneNumber);
        txtRegisterUsername = (TextView) findViewById(R.id.txtRegisterUsername);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String email = txtRegisterEmail.getText().toString();
                String password = txtRegisterPassword.getText().toString();
                String phonenumber = txtRegisterPhoneNumber.getText().toString();
                String username = txtRegisterUsername.getText().toString();

                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedDate = df.format(c.getTime());

                if (android.os.Build.VERSION.SDK_INT > 9) {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                }

                if (email.equals("") || password.equals("") || phonenumber.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Please Fill All The Fields!!!",
                            Toast.LENGTH_LONG).show();
                } else {
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection(url, user, pass);
                        Log.i(LOG_TAG, "Connection Success ");

                        Statement myStmt = con.createStatement();
                        String sql = "INSERT INTO `user` (`userEmail`, `userPassword`, `userName`, `userTelephone`, `userRegisteredTime`) VALUES ('" + email + "', '" + password + "', '" + username + "', '" + phonenumber + "', '"+ formattedDate +"')" ;
                        myStmt.execute(sql);
                        Toast.makeText(RegisterActivity.this, "Registration Successful !!!",
                                Toast.LENGTH_LONG).show();

                        Intent myIntent = new Intent(RegisterActivity.this,
                                MainActivity.class);
                        startActivity(myIntent);


                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(RegisterActivity.this, "Registration Failed !!!",
                                Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }
}
