package com.devplmr.schedulemtkp_androidclient;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity
{

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener()
    {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            switch (item.getItemId())
            {
                case R.id.navigation_changes:
                    loadFragment(ChangesFragment.newInstance());
                    return true;
                case R.id.navigation_schedule:
                    loadFragment(ScheduleFragment.newInstance());
                    return true;
                case R.id.navigation_notifications:
                    loadFragment(NotificationsFragment.newInstance());
                    return true;
            }
            return false;
        }

    };

    private void loadFragment(Fragment fragment)
    {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.text_edit, fragment);
        ft.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        FirebaseApp.initializeApp(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

//        loadFragment(ChangesFragment.newInstance()); // todo problem onResume()

    }

}
