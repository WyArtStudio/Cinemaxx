package com.wahyuhw.cinemaxx.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.wahyuhw.cinemaxx.R;
import com.wahyuhw.cinemaxx.fragment.HomeFavoriteFragment;
import com.wahyuhw.cinemaxx.fragment.HomeGridFragment;
import com.wahyuhw.cinemaxx.fragment.HomeMoviesFragment;
import com.wahyuhw.cinemaxx.fragment.MoviesNowPlayingFragment;
import com.wahyuhw.cinemaxx.fragment.TvShowsTodayFragment;

import static com.wahyuhw.cinemaxx.base.BaseAppCompatActivity.KEY_FRAGMENT;
import static com.wahyuhw.cinemaxx.base.BaseAppCompatActivity.KEY_TITLE;

public class MainActivity extends AppCompatActivity {
    private Fragment pageContent = new HomeMoviesFragment();
    private String title = "Cinemaxx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        final DrawerLayout drawerLayout = findViewById(R.id.main_drawer);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        NavigationView navigationView = findViewById(R.id.main_navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.menu_home:
                        pageContent = new HomeMoviesFragment();
                        title = getResources().getString(R.string.home);
                        break;
                    case R.id.menu_fav:
                        pageContent = new HomeFavoriteFragment();
                        title = getResources().getString(R.string.favorite);
                        break;
                    case R.id.menu_movie_now_playing:
                        pageContent = new MoviesNowPlayingFragment();
                        title = getResources().getString(R.string.movies_now_playing);
                        break;
                    case R.id.menu_tv_airing_today:
                        pageContent = new TvShowsTodayFragment();
                        title = getResources().getString(R.string.tv_shows_airing_today);
                        break;
                    case R.id.nav_grid:
                        pageContent = new HomeGridFragment();
                        title = getResources().getString(R.string.home);
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, pageContent).commit();
                toolbar.setTitle(title);
                drawerLayout.closeDrawers();
                return true;
            }
        });

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, pageContent).commit();
            toolbar.setTitle(title);

        } else {
            pageContent = getSupportFragmentManager().getFragment(savedInstanceState, KEY_FRAGMENT);
            title = savedInstanceState.getString(KEY_TITLE);

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, pageContent).commit();
            toolbar.setTitle(title);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(KEY_TITLE, title);
        getSupportFragmentManager().putFragment(outState, KEY_FRAGMENT, pageContent);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.main_drawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            MainActivity.this.finish();
                            System.exit(0);
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings) {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
