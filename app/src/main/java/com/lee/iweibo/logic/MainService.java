package com.lee.iweibo.logic;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.lee.iweibo.bean.Task;
import com.lee.iweibo.view.IWeiBoAty;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 服务层，主要负责数据的处理以及返回
 */
public class MainService extends Service implements Runnable{

    //创建任务队列 多态
    private static Queue<Task> tasks = new LinkedList<>();
    //线程是否运行
    private boolean isRunning;
    //UI集合
    private static List<Activity> activities = new ArrayList<>();


    //添加任务到队列，供外部调用
    public static void newTask(Task task){
        tasks.add(task);
    }


    //添加Activity到集合
    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        isRunning = true;
        Thread thread = new Thread(this);
        thread.start();
    }

    //异步消息处理
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case Task.WEIBO_LOGIN://登录

                    //更新界面
                    IWeiBoAty activity = (IWeiBoAty) getActivityByName("LoginActivity");
                    activity.refresh(msg.obj);

                    break;
            }
        }
    };

    @Override
    public void run() {
        Task task = null;
        while(isRunning){
            if(!tasks.isEmpty()){
                //执行后移除
               task = tasks.poll();
                if(task != null){
                    doTask(task);
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void doTask(Task task) {
        //获取消息
        Message msg = mHandler.obtainMessage();
        //指定what
        msg.what = task.getTaskId();

        switch (task.getTaskId()){
            case Task.WEIBO_LOGIN:
                Log.i("info","doTask login");
                msg.obj = "登录成功";
                break;
        }

        //发送消息
        mHandler.sendMessage(msg);
    }

    //通过name获取Activity
    public Activity getActivityByName(String name){
        if(!activities.isEmpty()){
            for(Activity activity:activities){
                if(activity != null){
                    if(activity.getClass().getName().indexOf(name) > 0){
                        return activity;
                    }
                }
            }
        }

        return null;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
