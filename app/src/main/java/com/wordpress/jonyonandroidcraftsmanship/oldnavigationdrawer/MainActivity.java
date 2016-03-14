package com.wordpress.jonyonandroidcraftsmanship.oldnavigationdrawer;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {

    private DrawerLayout drawerLayout = null;
    private ListView drawerList = null;
    private ListView drawerList2 = null;
    private String[] days = null;
    private String[] months = null;
    private MyOnItemClickListener myOnItemClickListener = null;
    private MyActionBarDrawerToggle myActionBarDrawerToggle = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        myActionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (myActionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        myActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void initialize() {
        days = getResources().getStringArray(R.array.days);
        months = getResources().getStringArray(R.array.months);
        myOnItemClickListener = new MyOnItemClickListener();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerList = (ListView) findViewById(R.id.drawerList);
        drawerList2 = (ListView) findViewById(R.id.drawerList2);
        drawerList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, days));
        drawerList2.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, months));
        drawerList.setOnItemClickListener(myOnItemClickListener);
        drawerList2.setOnItemClickListener(myOnItemClickListener);
        myActionBarDrawerToggle=new MyActionBarDrawerToggle(this,drawerLayout,R.drawable.ic_drawer,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.setDrawerListener(myActionBarDrawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private class MyOnItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ListView listView = (ListView) parent;
            if (listView.equals(drawerList)) {
                Logger.toast(MainActivity.this, "Days: " + position);
                selectItem(position);
            } else if (listView.equals(drawerList2)) {
                Logger.toast(MainActivity.this, "Months: " + position);
            }
        }
    }

    private void selectItem(int position) {
        drawerList.setItemChecked(position, true);
        getSupportActionBar().setTitle(days[position]);
    }

    private class MyActionBarDrawerToggle extends ActionBarDrawerToggle {

        public MyActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout, int drawerImageRes, int openDrawerContentDescRes, int closeDrawerContentDescRes) {
            super(activity, drawerLayout, drawerImageRes, openDrawerContentDescRes, closeDrawerContentDescRes);
        }

        @Override
        public void onDrawerOpened(View drawerView) {
            super.onDrawerOpened(drawerView);
            Logger.toast(MainActivity.this,"Drawer opened");
        }

        @Override
        public void onDrawerClosed(View drawerView) {
            super.onDrawerClosed(drawerView);
            Logger.toast(MainActivity.this, "Drawer closed");
        }
    }
}
