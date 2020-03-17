/*
package com.wenwu.pm.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wenwu.pm.R;
import com.wenwu.pm.goson.Msg;
import com.wenwu.pm.utils.OkHttpUtil;

import java.io.IOException;

import okhttp3.FormBody;

import okhttp3.RequestBody;


public class OkHttpTestActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http_test);
        Button sendRequest = findViewById(R.id.send_ok_request);
        responseText = findViewById(R.id.response_ok_text);
        sendRequest.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        sendRequestWithHttpURLConnection();
    }

    private void sendRequestWithHttpURLConnection() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                String address = "http://10.0.2.2:8080/api/user/login";
                String responseData = null;
                try {
                    responseData = OkHttpUtil.post(address,OkHttpUtil.requestBody("root","admin"));
                    Gson gson = new Gson();
                    Msg msg = gson.fromJson(responseData, Msg.class);
                    System.out.println(msg);
                    showResponse(msg.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void parseJsonWithGson(String jsonData) {
        Gson gson = new Gson();
        Msg msg = gson.fromJson(jsonData, Msg.class);
        System.out.println(msg);
    }

    public void showResponse(final String response) {
        runOnUiThread(new Thread(new Runnable() {
            @Override
            public void run() {
                responseText.setText(response);
            }
        }));
    }
}
*/
