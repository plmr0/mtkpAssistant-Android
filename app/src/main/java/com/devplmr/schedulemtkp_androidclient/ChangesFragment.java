package com.devplmr.schedulemtkp_androidclient;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ChangesFragment extends Fragment
{
    public ChangesFragment()
    {
    }

    public static ChangesFragment newInstance()
    {
        return new ChangesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View FragmentView = inflater.inflate(R.layout.fragment_changes, container, false);
        return FragmentView;
    }
}
