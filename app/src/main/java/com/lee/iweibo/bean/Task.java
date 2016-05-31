package com.lee.iweibo.bean;

/**
 * 任务
 */
public class Task {

    //任务ID
    private int taskId;
    //任务参数
    private String taskParams;
    //登录的ID
    public static final int WEIBO_LOGIN = 1;

    public Task(int taskId, String taskParams) {
        this.taskId = taskId;
        this.taskParams = taskParams;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskParams() {
        return taskParams;
    }

    public void setTaskParams(String taskParams) {
        this.taskParams = taskParams;
    }
}
