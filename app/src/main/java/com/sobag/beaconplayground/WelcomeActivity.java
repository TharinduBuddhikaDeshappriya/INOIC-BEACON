package com.sobag.beaconplayground;


import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.URL;

/**
 * Created by bdesh on 7/25/2017.
 */

public class WelcomeActivity extends Activity {
    Button button;
    Button button1;
    private final static int REQUEST_ENABLE_BT = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from activity_main.xml
        setContentView(R.layout.activity_welcome);

       // isOnline();



       /* ConnectivityManager conMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        if ( conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED
                || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED ) {

            Toast.makeText(WelcomeActivity.this, "Internet connection success !!!",
                    Toast.LENGTH_LONG).show();

        }
        else if ( conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.DISCONNECTED
                || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.DISCONNECTED) {

            Toast.makeText(WelcomeActivity.this, "Internet connection Failed !!!",
                    Toast.LENGTH_LONG).show();
            // notify user you are not online
        }
*/
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

        // Locate the button in activity_main.xml
        button = (Button) findViewById(R.id.ButtonLogin);
        button1 = (Button) findViewById(R.id.ButtonRegister);

        // Capture button clicks
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // Start NewActivity.class
                Intent myIntent = new Intent(WelcomeActivity.this,
                        LoginActivity.class);
                startActivity(myIntent);
            }
        });

        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // Start NewActivity.class
                Intent myIntent1 = new Intent(WelcomeActivity.this,
                        RegisterActivity.class);
                startActivity(myIntent1);
            }
        });
    }
    /*public Boolean isOnline() {
        try {
            Process p1 = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.com");
            int returnVal = p1.waitFor();
            boolean reachable = (returnVal==0);
            return reachable;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
    public static boolean isNetworkAvailable () {
        boolean success = false;
        try {
            URL url = new URL("https://google.com");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(10000);
            connection.connect();
            success = connection.getResponseCode() == 200;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return success;


    }*/
}

