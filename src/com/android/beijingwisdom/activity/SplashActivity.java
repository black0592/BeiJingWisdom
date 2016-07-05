package com.android.beijingwisdom.activity;

import com.android.beijingwisdom.R;
import com.android.beijingwisdom.utils.SPUtil;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

public class SplashActivity extends Activity {
	
	private RelativeLayout rl_splash_root;
	private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        rl_splash_root = (RelativeLayout) findViewById(R.id.rl_splash_root);
        sp = getSharedPreferences("config", MODE_PRIVATE);
        /**
         * ��ת����,Χ������������ת360
         */
        RotateAnimation rotaani = new RotateAnimation(0, 360, 
        		Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotaani.setDuration(1000);
        rotaani.setFillAfter(true);
        /**
         * ���Ŷ���
         */
        ScaleAnimation scaleani = new ScaleAnimation(0, 1,0, 1, 
        		Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleani.setDuration(1000);
        scaleani.setFillAfter(true);
        
        /**
         * ���䶯��
         */
        AlphaAnimation alphaani = new AlphaAnimation( 0, 1);
        alphaani.setDuration(2000);
        alphaani.setFillAfter(true);
        
        /**
         * ��������
         */
        AnimationSet set = new AnimationSet(true);
        set.addAnimation(rotaani);
        set.addAnimation(scaleani);
        set.addAnimation(alphaani);
        
        rl_splash_root.startAnimation(set);
        
        set.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				//������������ж��û��Ƿ��һ�ν���ʹ��
				boolean isFirstEnter = SPUtil.getBoolean(sp, "isFirstEnter", true);
				Intent intent	;
				if(isFirstEnter){
					//������������ҳ�棬����isFirstEnter��Ϊfalse
					intent = new Intent(SplashActivity.this,GuideActivtiy.class);
					startActivity(intent);
					SPUtil.setBoolean(sp,"isFirstEnter", true);
				}else{
					//������ҳ��
					intent = new Intent(SplashActivity.this,HomeActivity.class);
					startActivity(intent);
				}
			}
		});
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
        
    }
    
}
