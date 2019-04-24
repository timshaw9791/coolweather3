package com.example.admin.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CountyActivity extends AppCompatActivity {
    private TextView textView=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_county);
        this.textView=findViewById(R.id.textView);

        Intent intent=getIntent();
        int cityid=intent.getIntExtra("cid",0);
        int pid=intent.getIntExtra("pid",0);

        String url="http://guolin.tech/api/china/"+pid+"/"+cityid;
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
               // parseJSONObject(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(responseText);
                    }});
            }
            @Override
            public void onFailure(Call call, IOException e) {

            }
        });
    }
}
