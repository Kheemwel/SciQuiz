package com.CrimsonKnightBlood.SciQuiz;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SectionPageActivity extends AppCompatActivity {
    MediaPlayer rightMP, leftMP;
    SharedPreferences sp;

    ViewPager vp;
    BottomNavigationView bnv;
    FloatingActionButton Rfab, Lfab;
    TextView txt;
    Button btn;

    static int[] pages = new int[5];
    int color;
    boolean activated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.section_page);

        Rfab = findViewById(R.id.arrowright_floatingbutton);
        Lfab = findViewById(R.id.arrowleft_floatingbutton);
        txt = findViewById(R.id.sectionpageTextView1);
        btn = findViewById(R.id.sectionpageButton1);
        bnv = findViewById(R.id.bottomnavigation_sectionpage);

        Configuration ScreenSize = getResources().getConfiguration();
        if (ScreenSize.screenHeightDp >= 600) {
            pages[0] = R.layout.biologypage_sh600dp;
            pages[1] = R.layout.earthsciencepage_sh600dp;
            pages[2] = R.layout.astronomypage_sh600dp;
            pages[3] = R.layout.chemistrypage_sh600dp;
            pages[4] = R.layout.physicspage_sh600dp;
        } else {
            pages[0] = R.layout.biology_page;
            pages[1] = R.layout.earthscience_page;
            pages[2] = R.layout.astronomy_page;
            pages[3] = R.layout.chemistry_page;
            pages[4] = R.layout.physics_page;
        }

        sp = getSharedPreferences("app_preferences", Context.MODE_PRIVATE);
        activated = sp.getBoolean("activate_sound", true);
        rightMP = MediaPlayer.create(this, R.raw.swoosh_right);
        leftMP = MediaPlayer.create(this, R.raw.swish_left);

        rightMP.setOnCompletionListener(mp -> {
            rightMP.release();
            rightMP = MediaPlayer.create(getApplication(), R.raw.swoosh_right);
        });

        leftMP.setOnCompletionListener(mp -> {
            leftMP.release();
            leftMP = MediaPlayer.create(getApplication(), R.raw.swish_left);
        });

        color = sp.getInt("color_scheme", 0);
        ColorChange(color);

        vp = findViewById(R.id.section_viewpager);
        vp.setAdapter(new PageAdapter(pages));
        vp.setCurrentItem(2);
        vp.beginFakeDrag();


        bnv.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.status_nav) {
                Intent i = new Intent(SectionPageActivity.this, StatusPageActivity.class);
                startActivity(i);
            } else if (itemId == R.id.exit_nav) {
                EXIT();
            } else if (itemId == R.id.options_nav) {
                Intent i = new Intent(SectionPageActivity.this, OptionsPageActivity.class);
                startActivity(i);
            }
            return true;
        });
    }
    public static class PageAdapter extends PagerAdapter {

        private final int[] pages;

        public PageAdapter(int[] pages) {
            this.pages = pages;
        }

        @Override
        public int getCount() {
            return pages.length;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View view = LayoutInflater.from(container.getContext()).inflate(pages[position], container, false);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

    }

    public void ColorChange(int clr) {
        switch (clr) {
            case 0:
                txt.setBackgroundResource(R.color.cyan_500);
                btn.setBackgroundResource(R.drawable.bluebutton);
                Rfab.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.blue_700));
                Lfab.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.blue_700));
                bnv.setItemBackgroundResource(R.color.cyan_700);
                break;
            case 1:
                txt.setBackgroundResource(R.color.teal_500);
                btn.setBackgroundResource(R.drawable.greenbutton);
                Rfab.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.green_700));
                Lfab.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.green_700));
                bnv.setItemBackgroundResource(R.color.teal_700);
                break;
            case 2:
                txt.setBackgroundResource(R.color.deeppurple_500);
                btn.setBackgroundResource(R.drawable.purplebutton);
                Rfab.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.purple_700));
                Lfab.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.purple_700));
                bnv.setItemBackgroundResource(R.color.deeppurple_700);
                break;
            case 3:
                txt.setBackgroundResource(R.color.deeporange_500);
                btn.setBackgroundResource(R.drawable.orangebutton);
                Rfab.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.orange_700));
                Lfab.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.orange_700));
                bnv.setItemBackgroundResource(R.color.deeporange_700);
                break;
        }
    }

    public void StartQuiz(View v) {
        int page = vp.getCurrentItem();
        Intent intent = new Intent(this, QuizSectionActivity.class);
        String cls = "";

        switch (page) {
            case 0:
                cls = "BiologySection";
                break;
            case 1:
                cls = "EarthScienceSection";
                break;
            case 2:
                cls = "AstronomySection";
                break;
            case 3:
                cls = "ChemistrySection";
                break;
            case 4:
                cls = "PhysicsSection";
                break;
        }
        intent.putExtra("targetClass", cls);
        startActivity(intent);
    }

    public void SwishLeft() {
        if (activated) {
            if (leftMP.isPlaying()) {
                leftMP.stop();
                leftMP = MediaPlayer.create(this, R.raw.swish_left);
                leftMP.start();
            } else {
                leftMP.start();
            }
        }
    }

    public void SwooshRight() {
        if (activated) {
            if (rightMP.isPlaying()) {
                rightMP.stop();
                rightMP = MediaPlayer.create(this, R.raw.swoosh_right);
                rightMP.start();
            } else {
                rightMP.start();
            }
        }
    }

    public void ArrowRight(View v) {
        SwooshRight();
        vp.setCurrentItem(vp.getCurrentItem() + 1);
        FloatButton();
    }

    public void ArrowLeft(View v) {
        SwishLeft();
        vp.setCurrentItem(vp.getCurrentItem() - 1);
        FloatButton();
    }

    private void FloatButton() {
        if (vp.getCurrentItem() == 4) {
            Rfab.setVisibility(View.GONE);
        } else if (vp.getCurrentItem() != 4) {
            Rfab.setVisibility(View.VISIBLE);
        }

        if (vp.getCurrentItem() == 0) {
            Lfab.setVisibility(View.GONE);
        } else if (vp.getCurrentItem() != 0) {
            Lfab.setVisibility(View.VISIBLE);
        }
    }

    private void EXIT() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Exit");
        builder.setMessage("Are you sure you want to leave?");

        builder.setPositiveButton("YES", (dialog, which) -> finish());

        builder.setNegativeButton("NO", (dialog, which) -> dialog.dismiss());

        builder.create().show();
    }

    @Override
    public void onBackPressed() {
        EXIT();
    }

    @Override
    protected void onResume() {
        super.onResume();
        activated = sp.getBoolean("activate_sound", true);
        color = sp.getInt("color_scheme", 0);
        ColorChange(color);
    }

}
