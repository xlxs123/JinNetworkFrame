package com.jin.jinnetworkframe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.jin.Bean;
import com.jin.http.IDataListener;
import com.jin.http.JinNetFramework;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        String url = "https://www.xlzrs.top";
        Bean bean = new Bean();

        JinNetFramework.sendJsonRequest(url, bean, Bean.class, new IDataListener() {
            @Override
            public void onSuccess(Object o) {
                Toast.makeText(MainActivity.this, "666", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure() {

            }
        });
    }
}