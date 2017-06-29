package com.example.sarahn.waterreminderapp.activities;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.example.sarahn.waterreminderapp.R;
import com.example.sarahn.waterreminderapp.fragments.SettingsFragment;
import com.example.sarahn.waterreminderapp.fragments.TodayFragment;

public class ActMainScreen extends AppCompatActivity
        implements TabLayout.OnTabSelectedListener,
        TodayFragment.OnFragmentInteractionListener,
        SettingsFragment.OnFragmentInteractionListener

{

    private ShareActionProvider mShareActionProvider;
    TabLayout tabs;
    FragmentTransaction ft;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_main_screen);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Water");


        tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("Today"),true);
        tabs.addTab(tabs.newTab().setText("Settings"));

        tabs.setOnTabSelectedListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {

        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        switch(tabs.getSelectedTabPosition()){
            case 0:
                FragmentManager fm = getSupportFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.frame, new TodayFragment());
                break;

            case 1:
                FragmentManager fm1 = getSupportFragmentManager();
                ft = fm1.beginTransaction();
                ft.replace(R.id.frame, new SettingsFragment());
        }

        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
