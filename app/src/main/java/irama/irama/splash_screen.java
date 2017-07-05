package irama.irama;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by enagi on 5/7/2017.
 */

public class splash_screen extends AppCompatActivity {

    private ProgressBar progressBar;
    private Animation animation;
    private TextView textView;
    private ObjectAnimator animator;
    private Boolean isFinish;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        initComponents();

        textView.setAnimation(animation);
        animator.setDuration(4000);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();

    }

    private void initComponents(){
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);
        animation = AnimationUtils.loadAnimation(splash_screen.this, R.anim.anim_download);
        textView = (TextView)findViewById(R.id.text2a);
        animator = ObjectAnimator.ofInt(progressBar, "progress", 0, 100);
    }

    private void getData(){




    }

}
