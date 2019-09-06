package com.devplmr.mtkpAssistant;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Objects;

public class SettingsFragment extends Fragment
{
    public SettingsFragment()
    {
    }

    private CompoundButton debugTopicSwitch;
    private CompoundButton pushTopicSwitch;

    public static SettingsFragment newInstance()
    {
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View FragmentView = inflater.inflate(R.layout.fragment_settings, container, false);

        debugTopicSwitch = (CompoundButton) FragmentView.findViewById(R.id.switch1);
        pushTopicSwitch = (CompoundButton) FragmentView.findViewById(R.id.switch2);

        // Take saved config for "debug" switch
        SharedPreferences debug_topic_s_p = Objects.requireNonNull(this.getActivity()).getSharedPreferences("debug_topic", 0);
        boolean b1 = debug_topic_s_p.getBoolean("switch_debug_key", false);
        debugTopicSwitch.setChecked(b1);

        // Take saved config for "push" switch
        SharedPreferences push_topic_s_p = Objects.requireNonNull(this.getActivity()).getSharedPreferences("push_topic", 0);
        boolean b2 = push_topic_s_p.getBoolean("switch_push_key", false);
        pushTopicSwitch.setChecked(b2);

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
                SharedPreferences settings = Objects.requireNonNull(getActivity()).getSharedPreferences("debug_topic", 0);
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
                SharedPreferences settings = Objects.requireNonNull(getActivity()).getSharedPreferences("push_topic", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("switch_push_key", isChecked);
                editor.apply();
            }

        });

        return FragmentView;
    }
}
