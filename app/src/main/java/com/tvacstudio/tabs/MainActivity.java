package com.tvacstudio.tabs;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.Manifest.permission;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public Toolbar toolbar;
    public ViewPager viewPager;
    public TabLayout tabLayout;
    public FragmentPost postsFragment;
    public FragmentComment commentsFragment;
    public UpdatesFragment updatedFragment;
    public DelPostFragment delPostFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);

        postsFragment = new FragmentPost();
        commentsFragment= new FragmentComment();
        updatedFragment = new UpdatesFragment();
        delPostFragment = new DelPostFragment();

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
        viewPagerAdapter.addFragment(postsFragment, "Posts");
        viewPagerAdapter.addFragment(commentsFragment, "Comments");
        viewPagerAdapter.addFragment(updatedFragment, "Update Post");
        viewPagerAdapter.addFragment(delPostFragment, "Delete Post");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_local_post_office_24);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_baseline_comment_24);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_baseline_update_24);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_baseline_delete_forever_24);

        initGoogleMap();


    }

    private void initGoogleMap() {
if (isServiceOk()){
    if (checkLocationPermission()){
        Toast.makeText(this, "Map ready", Toast.LENGTH_SHORT).show();
    }else {
        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
    }
}

    }
    private boolean isServiceOk(){
        GoogleApiAvailability googleApi=GoogleApiAvailability.getInstance();
        int result= googleApi.isGooglePlayServicesAvailable(this);
        if (result == ConnectionResult.SUCCESS){
            return true;
        }else if (googleApi.isUserResolvableError(result)){
            int PLAY_SERVICES_ERROR_CODE = 9002;
            Dialog dialog = googleApi.getErrorDialog(this,result, PLAY_SERVICES_ERROR_CODE);
            dialog.show();
        }else {
            Toast.makeText(this, "Play Services are required for this app", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private boolean checkLocationPermission() {
        return ContextCompat.checkSelfPermission(this, permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED;
    }

    private static class ViewPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments = new ArrayList<>();
        private List<String> fragmentTitle = new ArrayList<>();

        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        public void addFragment(Fragment fragment, String title) {
            fragments.add(fragment);
            fragmentTitle.add(title);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitle.get(position);
        }
    }
}
