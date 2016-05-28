package com.lee.iweibo.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void Intent2Activity(Class transActivity){
        Intent intent = new Intent(this,transActivity);
        startActivity(intent);
        finish();
    }

    public void showMsg(String text){
        Toast.makeText(BaseActivity.this, text, Toast.LENGTH_SHORT).show();
    }
}
