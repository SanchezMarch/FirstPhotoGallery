package com.sanchez.firstphotogallery.features.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.sanchez.firstphotogallery.R;
import com.sanchez.firstphotogallery.features.profile.adapters.ViewPagerAdapter;
import com.sanchez.firstphotogallery.features.profile.views.AlbumsFragment;
import com.sanchez.firstphotogallery.features.profile.views.CounterView;
import com.sanchez.firstphotogallery.features.profile.views.PhotosFragment;

/**
 * Created by Олександр on 19.12.2016.
 */

public class ProfileFragment extends Fragment implements TabLayout.OnTabSelectedListener {

    private SimpleDraweeView sdvAvatar;
    private Toolbar toolbar;

    private CounterView photoCounter;
    private CounterView friendsCounter;
    private CounterView followersCounter;

    private TextView tvFullNAme;
    private TextView tvStatus;

    private ViewPager viewPager;
    private TabLayout tabLayout;

    public ProfileFragment(){

    }

    public static ProfileFragment newInstance(){
        return new ProfileFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_profile, container);

        toolbar = (Toolbar) v.findViewById(R.id.toolbar);

        sdvAvatar = (SimpleDraweeView) v.findViewById(R.id.sdvAvatar);

        photoCounter = (CounterView) v.findViewById(R.id.photoCounter);
        friendsCounter = (CounterView) v.findViewById(R.id.friendsCounter);
        followersCounter = (CounterView) v.findViewById(R.id.followersCounter);

        tvFullNAme = (TextView) v.findViewById(R.id.tvFullName);
        tvStatus = (TextView) v.findViewById(R.id.tvStatus);

        viewPager = (ViewPager) v.findViewById(R.id.viewPager);
        setupViewPager(viewPager);


        tabLayout = (TabLayout) v.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(this);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

        toolbar.setTitle("Sanchezzz");

        sdvAvatar.setImageURI("https://pp.vk.me/c421330/v421330591/995c/obaCbDKQsV4.jpg");

        photoCounter.setTitle(R.string.profile_counter_title_photos);
        photoCounter.setCount(1201);

        friendsCounter.setTitle(R.string.profile_counter_title_friends);
        friendsCounter.setCount(1298);

        followersCounter.setTitle(R.string.profile_counter_title_followers);
        followersCounter.setCount(870);

        tvFullNAme.setText("Alekandr Marchuk");
        tvStatus.setText("https://vk.com/eredan17_ua");

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new AlbumsFragment(), "Albums");
        adapter.addFragment(new PhotosFragment(), "Photos");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
