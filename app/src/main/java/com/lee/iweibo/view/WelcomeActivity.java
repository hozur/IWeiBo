package com.lee.iweibo.view;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.lee.iweibo.R;

public class WelcomeActivity extends BaseActivity {
    /**
     * 欢迎界面
     *
     * @param savedInstanceState
     */
    private ImageView ivSlogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);

        //获取slogan图片设置动画
        ivSlogan = (ImageView) findViewById(R.id.iv_slogan);
        AlphaAnimation alphaAnim = new AlphaAnimation(0.0f, 1.0f);
        alphaAnim.setDuration(3000);
        alphaAnim.setRepeatMode(Animation.REVERSE);
        ivSlogan.setAnimation(alphaAnim);

        //设置动画监听,动画结束跳转页面
        alphaAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.i("info", "anim start");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.i("info", "anim end");
                //页面跳转
                Intent2Activity(LoginActivity.class);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.i("info", "anim repeat");
            }
        });
    }
}
