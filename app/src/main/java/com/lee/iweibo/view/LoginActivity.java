package com.lee.iweibo.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lee.iweibo.R;
import com.lee.iweibo.bean.Task;
import com.lee.iweibo.logic.MainService;

public class LoginActivity extends BaseActivity implements IWeiBoAty{

    private Button btnLogin;
    private TextView tvShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //启动服务
        Intent intentService = new Intent(this, MainService.class);
        startService(intentService);
        //添加当前的activity到UI集合中
        MainService.addActivity(this);

        btnLogin = (Button) findViewById(R.id.btn_login);
        tvShow = (TextView) findViewById(R.id.tv_show);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task task = new Task(Task.WEIBO_LOGIN,null);
                MainService.newTask(task);
            }
        });
    }

    @Override
    public void init() {

    }

    @Override
    public void refresh(Object... params) {
        tvShow.setText(params[0].toString());
    }


}
