package com.tvacstudio.tabs;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
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
