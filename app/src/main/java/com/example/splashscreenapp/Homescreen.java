package com.example.splashscreenapp;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class Homescreen extends AppCompatActivity {

    public static final String myPrefs = "MyPrefs";
    private TextView tv_text;
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ArrayList<MyListData> myListData;

    // Make sure to be using androidx.appcompat.app.ActionBarDrawerToggle version.
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Find our drawer view
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);
        nvDrawer.setItemIconTintList(null);

        //makes the hamburger icon appear instead of the default arrow
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        //tv_text = (TextView) findViewById(R.id.tv_login_intro);

        SharedPreferences sharedPreferences = getSharedPreferences(myPrefs, MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        //tv_text.setText(getString(R.string.welcome_text) + " " + username);

        myListData = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            MyListData temp = new MyListData("Name " + i, android.R.drawable.ic_dialog_info, R.drawable.edit_logo, android.R.drawable.ic_delete);
            myListData.add(temp);
        }
        updateRecyclerView();
    }

    public void updateRecyclerView(){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        MyListAdapter adapter = new MyListAdapter(myListData, this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        switch(menuItem.getItemId()) {
            case R.id.nav_logout:
                Intent i = new Intent(Homescreen.this, MainActivity.class);
                SharedPreferences sharedPref = getSharedPreferences(myPrefs, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("isLogged", false);
                editor.remove("username");
                editor.remove("password");
                editor.commit();
                startActivity(i);
                break;
        }
        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
