package lt.birziska.monika.lesson.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import lt.birziska.monika.lesson.Modules.DrawerMenuItem;
import lt.birziska.monika.lesson.fragments.CalculatorFragment;
import lt.birziska.monika.lesson.fragments.ProgressFragment;

import lt.birziska.monika.lesson.R;
import lt.birziska.monika.lesson.fragments.TogglerFragment;

public class MainActivity extends AppCompatActivity{

    private DrawerLayout drawer;
    private Toolbar toolbar;
    private NavigationView navigationView;

    // index to identify current nav menu item
    public static int navItemIndex = 0;

    // toolbar titles respected to selected nav menu item
    private DrawerMenuItem[] menuItems;

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mHandler = new Handler();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        menuItems = new DrawerMenuItem[]{
                new DrawerMenuItem("Toggler", "toggler"),
                new DrawerMenuItem("Calculator", "calculator"),
                new DrawerMenuItem("Progress", "progress")
        };

        setUpNavigationView();
        addMenuItemInNavMenuDrawer();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            loadFragments();
        }
    }

    /***
     * Returns respected fragment that user
     * selected from navigation menu
     */
    private void loadFragments() {
        // selecting appropriate nav menu item
        selectNavMenu();

        // set toolbar title
        setToolbarTitle();

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(menuItems[navItemIndex].getTag()) != null) {
            drawer.closeDrawers();
        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getFragments();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, menuItems[navItemIndex].getTag());
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        //Closing drawer on item click
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    private Fragment getFragments() {
        switch (navItemIndex) {
            case 0:
                TogglerFragment togglerFragment = new TogglerFragment();
                return togglerFragment;
            case 1:
                CalculatorFragment calculatorFragment = new CalculatorFragment();
                return calculatorFragment;
            case 2:
                ProgressFragment progressFragment = new ProgressFragment();
                return progressFragment;
            default:
                return new CalculatorFragment();
        }
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(menuItems[navItemIndex].getTitle());
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setUpNavigationView() {
        navigationView.setNavigationItemSelectedListener(new DrawerItemClickListener());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

        if (navItemIndex != 0) {
            navItemIndex = 0;
            loadFragments();
            return;
        }

        super.onBackPressed();
    }

    private void addMenuItemInNavMenuDrawer() {
        Menu menu = navigationView.getMenu();
        for(int i=0; i<menuItems.length; i++){
            menu.add(0, i, Menu.NONE, menuItems[i].getTitle());
        }
        menu.setGroupCheckable(0, true, true);

        navigationView.invalidate();
    }

    private class DrawerItemClickListener
            implements NavigationView.OnNavigationItemSelectedListener {

        @SuppressWarnings("StatementWithEmptyBody")
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            // Handle navigation view item clicks here.
            int id = item.getItemId();

            navItemIndex = id;
            if (item.isChecked()) {
                item.setChecked(false);
            } else {
                item.setChecked(true);
            }

            loadFragments();
            return true;
        }
    }
}