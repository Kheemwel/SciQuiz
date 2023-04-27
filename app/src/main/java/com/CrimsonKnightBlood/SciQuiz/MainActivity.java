package com.CrimsonKnightBlood.SciQuiz;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.Context;
import android.os.Looper;

import java.util.Date;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        SharedPreferences sp = getSharedPreferences("app_preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        Date date = new Date();

        int times = sp.getInt("played_times", 0) + 1;

        editor.putInt("played_times", times);
        editor.putString("last_played", date.toString());
        editor.apply();

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent transition = new Intent(MainActivity.this, TitleActivity.class);
            startActivity(transition);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            finish();
        }, 1500);
    }
}
