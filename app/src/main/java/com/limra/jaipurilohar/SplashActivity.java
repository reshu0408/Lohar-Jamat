package com.limra.jaipurilohar;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.limra.jaipurilohar.login.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIMEOUT = 3000;
    @BindView(R.id.headTextView1)
    TextView mHeadTextView1;
    @BindView(R.id.headTextView2)
    TextView mHeadTextView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        Typeface face = Typeface.createFromAsset(getAssets(), "arabic.otf");
        mHeadTextView1.setTypeface(face);
        mHeadTextView2.setTypeface(face);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                SplashActivity.this.finish();
                startActivity(intent);
            }
        }, SPLASH_TIMEOUT);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }
}
