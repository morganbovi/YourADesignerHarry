package com.throwaway.youradesignerharry;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Wizards extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wizards);

        imageView = findViewById(R.id.image);
        Button alpha = findViewById(R.id.alpha);
        Button rotation = findViewById(R.id.rotation);
        Button translation = findViewById(R.id.translation);
        Button scale = findViewById(R.id.scale);
        Button all = findViewById(R.id.all);

        //Alpha!
        AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0f);
        alphaAnimation.setDuration(1000);
        alphaAnimation.setRepeatMode(Animation.REVERSE);
        alphaAnimation.setRepeatCount(1);

        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(alpha, View.ALPHA, 0);
        alphaAnimator.setRepeatCount(1);
        alphaAnimator.setRepeatMode(ValueAnimator.REVERSE);

        //Rotation!
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(1000);

        ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(rotation, View.ROTATION, 360);
        rotateAnimator.setRepeatCount(1);
        rotateAnimator.setRepeatMode(ValueAnimator.REVERSE);

        //Translation!
        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, 250,
                Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, 0);
        translateAnimation.setDuration(1000);
        translateAnimation.setInterpolator(new LinearOutSlowInInterpolator());

        TranslateAnimation translateAnimation1 = new TranslateAnimation(
                Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, 1300);
        translateAnimation1.setDuration(1000);
        translateAnimation1.setStartOffset(1000);
        translateAnimation1.setInterpolator(new AnticipateOvershootInterpolator(1.5f, 2.5f));

        TranslateAnimation translateAnimation2 = new TranslateAnimation(
                Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, -500,
                Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, 0);
        translateAnimation2.setDuration(1000);
        translateAnimation2.setStartOffset(2000);
        translateAnimation2.setInterpolator(new BounceInterpolator());

        TranslateAnimation translateAnimation3 = new TranslateAnimation(
                Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, -1300);
        translateAnimation3.setDuration(1000);
        translateAnimation3.setStartOffset(3000);
        translateAnimation3.setInterpolator(new DecelerateInterpolator(1.5f));

        TranslateAnimation translateAnimation4 = new TranslateAnimation(
                Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, 250,
                Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, 0);
        translateAnimation4.setDuration(1000);
        translateAnimation4.setStartOffset(4000);
        translateAnimation4.setInterpolator(new AnticipateInterpolator(0.5f));

        AnimationSet translationSet = new AnimationSet(false);
        translationSet.addAnimation(translateAnimation);
        translationSet.addAnimation(translateAnimation1);
        translationSet.addAnimation(translateAnimation2);
        translationSet.addAnimation(translateAnimation3);
        translationSet.addAnimation(translateAnimation4);

//        translationSet.setInterpolator(new AnticipateOvershootInterpolator(1.5f, 2.5f));
//        translationSet.setInterpolator(new BounceInterpolator());
//        translationSet.setInterpolator(new DecelerateInterpolator(1.5f));
//        translationSet.setInterpolator(new AnticipateInterpolator(0.5f));
//        translationSet.setInterpolator(new CycleInterpolator(1));
//        translationSet.setInterpolator(new FastOutLinearInInterpolator());
//        translationSet.setInterpolator(new LinearOutSlowInInterpolator());

        ObjectAnimator translationAnimator = ObjectAnimator.ofFloat(translation, View.TRANSLATION_X, 800);
        translationAnimator.setRepeatCount(1);
        translationAnimator.setRepeatMode(ValueAnimator.REVERSE);

        //Scale!
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1, 20,
                1, 20,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5F);
        scaleAnimation.setDuration(1000);

        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat(View.SCALE_X, 2);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 2);
        ObjectAnimator scaleAnimator = ObjectAnimator.ofPropertyValuesHolder(scale, pvhX, pvhY);
        scaleAnimator.setRepeatCount(1);
        scaleAnimator.setRepeatMode(ValueAnimator.REVERSE);

        //SET!!!!!!!
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(scaleAnimation);

        List<Animator> animatorList = new ArrayList();
        animatorList.add(alphaAnimator);
        animatorList.add(translationAnimator);
        animatorList.add(rotateAnimator);
        animatorList.add(scaleAnimator);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(animatorList);

        //DO ALL THE THINGS!
        setupAnimation(alpha, alphaAnimation, alphaAnimator);
        setupAnimation(rotation, rotateAnimation, rotateAnimator);
        setupAnimation(translation, translationSet, translationAnimator);
        setupAnimation(scale, scaleAnimation, scaleAnimator);
        setupAnimation(all, animationSet, animatorSet);

    }

    private void setupAnimation(Button button, final Animation animation, final Animator animator) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.startAnimation(animation);
                animator.start();
            }
        });
    }
}
