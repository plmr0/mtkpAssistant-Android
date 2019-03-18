package com.devplmr.schedulemtkp_androidclient;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ScheduleFragment extends Fragment
{
    public ScheduleFragment()
    {
    }

    public static ScheduleFragment newInstance()
    {
        return new ScheduleFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View FragmentView = inflater.inflate(R.layout.fragment_schedule, container, false);
        return FragmentView;
    }
}
