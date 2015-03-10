/**
 * 使用ObjectAnimator生成各种动画效果，使用AnimatorSet使动画联动
 * **/

package cn.com.tony.animations;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    Button alphaButton;
    Button translateButton;
    Button rotateButton;
    Button scaleButton;
    Button setButton;
    CheckBox checkBox;
    ObjectAnimator alphaAnimation,translateAnimation,rotateAnimation,scaleAnimation;
    AnimatorSet setAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alphaButton = (Button) findViewById(R.id.button);
        alphaButton.setOnClickListener(this);
        translateButton = (Button) findViewById(R.id.button2);
        translateButton.setOnClickListener(this);
        rotateButton = (Button) findViewById(R.id.button3);
        rotateButton.setOnClickListener(this);
        scaleButton = (Button) findViewById(R.id.button4);
        scaleButton.setOnClickListener(this);
        setButton = (Button) findViewById(R.id.button5);
        setButton.setOnClickListener(this);
        checkBox = (CheckBox) findViewById(R.id.checkBox1);

        alphaAnimation = ObjectAnimator.ofFloat(alphaButton,View.ALPHA,0); //默认周期300ms
        alphaAnimation.setRepeatCount(1);//重复次数
        alphaAnimation.setRepeatMode(ValueAnimator.REVERSE);//变回去

        translateAnimation = ObjectAnimator.ofFloat(translateButton,View.TRANSLATION_X,800);
        translateAnimation.setRepeatCount(1);
        translateAnimation.setRepeatMode(ValueAnimator.REVERSE);

        rotateAnimation = ObjectAnimator.ofFloat(rotateButton,View.ROTATION,360);
        rotateAnimation.setRepeatCount(2);
        rotateAnimation.setRepeatMode(ValueAnimator.REVERSE);

        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat(View.SCALE_X,3);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat(View.SCALE_Y,3);
        scaleAnimation = ObjectAnimator.ofPropertyValuesHolder(scaleButton,pvhX,pvhY);
        scaleAnimation.setRepeatCount(1);
        scaleAnimation.setRepeatMode(ValueAnimator.REVERSE);

        //AnimatorSet can apply to multiple targets
        setAnimation = new AnimatorSet();
        setAnimation.play(translateAnimation).after(alphaAnimation).before(rotateAnimation);
        setAnimation.play(rotateAnimation).before(scaleAnimation);
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button:
                if(checkBox.isChecked()){
                    Animator animator = AnimatorInflater.loadAnimator(MainActivity.this, R.animator.fade);
                    animator.setTarget(view);
                    animator.start();
                    return;
                }
                alphaAnimation.start();
                break;
            case R.id.button2:
                if(checkBox.isChecked()){
                    Animator animator = AnimatorInflater.loadAnimator(MainActivity.this,R.animator.move);
                    animator.setTarget(view);
                    animator.start();
                    return;
                }
                translateAnimation.start();
                break;
            case R.id.button3:
                if(checkBox.isChecked()){
                    Animator animator = AnimatorInflater.loadAnimator(MainActivity.this,R.animator.spin);
                    animator.setTarget(view);
                    animator.start();
                    return;
                }
                rotateAnimation.start();
                break;
            case R.id.button4:
                if(checkBox.isChecked()){
                    Animator animator = AnimatorInflater.loadAnimator(MainActivity.this,R.animator.scale);
                    animator.setTarget(view);
                    animator.start();
                    return;
                }
                scaleAnimation.start();
                break;
            case R.id.button5:
                if(checkBox.isChecked()){
                    Animator animator = AnimatorInflater.loadAnimator(MainActivity.this,R.animator.combo);
                    animator.setTarget(view);
                    animator.start();
                    return;
                }
                setAnimation.start();
                break;
        }
    }
}
