package com.devplmr.schedulemtkp_androidclient;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;

import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity
{
    private CompoundButton debugTopicSwitch;
    private CompoundButton pushTopicSwitch;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener()
    {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            switch (item.getItemId())
            {
                case R.id.navigation_changes:
                    debugTopicSwitch.setVisibility(View.INVISIBLE);
                    pushTopicSwitch.setVisibility(View.INVISIBLE);
                    return true;
                case R.id.navigation_schedule:
                    debugTopicSwitch.setVisibility(View.INVISIBLE);
                    pushTopicSwitch.setVisibility(View.INVISIBLE);
                    return true;
                case R.id.navigation_debug:
                    debugTopicSwitch.setVisibility(View.VISIBLE);
                    pushTopicSwitch.setVisibility(View.VISIBLE);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        FirebaseApp.initializeApp(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        debugTopicSwitch = (CompoundButton) findViewById(R.id.switch1);
        pushTopicSwitch = (CompoundButton) findViewById(R.id.switch2);

        // Take saved config for "debug" switch
        SharedPreferences debug_topic_s_p = getSharedPreferences("debug_topic", 0);
        boolean b1 = debug_topic_s_p.getBoolean("switch_debug_key", false);
        debugTopicSwitch.setChecked(b1);

        // Take saved config for "push" switch
        SharedPreferences push_topic_s_p = getSharedPreferences("push_topic", 0);
        boolean b2 = push_topic_s_p.getBoolean("switch_push_key", false);
        pushTopicSwitch.setChecked(b2);

        // onCreate Invisibility
        debugTopicSwitch.setVisibility(View.INVISIBLE);
        pushTopicSwitch.setVisibility(View.INVISIBLE);

        // Event checkedChangeListener for debugTopicSwitch
        debugTopicSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked)
            {
                if (isChecked)
                {
                    FirebaseMessaging.getInstance().subscribeToTopic("debug");
                }
                else
                {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("debug");
                }
                SharedPreferences settings = getSharedPreferences("debug_topic", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("switch_debug_key", isChecked);
                editor.apply();
            }

        });

        // Event checkedChangeListener for pushTopicSwitch
        pushTopicSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked)
            {
                if (isChecked)
                {
                    FirebaseMessaging.getInstance().subscribeToTopic("push");
                }
                else
                {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("push");
                }
                SharedPreferences settings = getSharedPreferences("push_topic", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("switch_push_key", isChecked);
                editor.apply();
            }

        });

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
