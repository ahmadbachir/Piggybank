package com.ab.piggybank.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.ab.piggybank.DatabaseHelper;
import com.ab.piggybank.R;
import com.ab.piggybank.activity.setup1.setupActivity;

import agency.tango.materialintroscreen.MaterialIntroActivity;
import agency.tango.materialintroscreen.SlideFragmentBuilder;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SetupSlideActivity extends MaterialIntroActivity {
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        addSlide(new SlideFragmentBuilder()
                .backgroundColor(android.R.color.background_light)
                .buttonsColor(R.color.colorAccent)
                .image(R.drawable.accountant)
                .title(getString(R.string.i_am_piggybank))
                .description(getString(R.string.your_accountant))
                .build());
        addSlide(new SlideFragmentBuilder()
                .backgroundColor(android.R.color.background_light)
                .buttonsColor(R.color.colorAccent)
                .image(R.drawable.wallet)
                .title(getString(R.string.take_control_wallet))
                .description(getString(R.string.keep_track))
                .build());
        addSlide(new SlideFragmentBuilder()
                .backgroundColor(android.R.color.background_light)
                .buttonsColor(R.color.colorAccent)
                .image(R.drawable.goal)
                .title(getString(R.string.with_my_help))
                .description(getString(R.string.close_to_financial_goals))
                .build());
        addSlide(new SlideFragmentBuilder()
                .backgroundColor(android.R.color.background_light)
                .buttonsColor(R.color.colorAccent)
                .image(R.drawable.trophy)
                .title(getString(R.string.why_wait))
                .description(getString(R.string.get_started))
                .build());
    }

    @Override
    public void onFinish() {
        super.onFinish();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        if (databaseHelper.getMethodTable().getCount() == 0) {
            Intent i = new Intent(this, setupActivity.class);
            startActivity(i);
            finish();
        }
        else if(preferences.getInt("country",-1) == -1) {
            Intent i = new Intent(this, ChooseCountryActivity.class);
            startActivity(i);
            finish();
        }
        else {
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
            finish();
        }
        preferences.edit().putBoolean("finishedsetupslide", true).apply();

    }
}
