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
import com.sanchez.firstphotogallery.common.model.user.User;
import com.sanchez.firstphotogallery.common.repo.Repo;
import com.sanchez.firstphotogallery.features.prefs.Preferences;
import com.sanchez.firstphotogallery.features.profile.adapters.ViewPagerAdapter;
import com.sanchez.firstphotogallery.features.profile.repository.IProfileRepo;
import com.sanchez.firstphotogallery.features.profile.repository.RetrofitProfileRepo;
import com.sanchez.firstphotogallery.features.profile.views.AllAlbumsFragment;
import com.sanchez.firstphotogallery.features.profile.views.CounterView;
import com.sanchez.firstphotogallery.features.profile.views.AllPhotosFragment;
import com.sanchez.firstphotogallery.utils.AppUtils;

/**
 * Created by Олександр on 19.12.2016.
 */

public class ProfileFragment extends Fragment {

    private SimpleDraweeView sdvAvatar;
    private Toolbar toolbar;

    private CounterView photoCounter;
    private CounterView friendsCounter;
    private CounterView followersCounter;

    private TextView tvFullNAme;
    private TextView tvStatus;

    private ViewPager viewPager;
    private TabLayout tabLayout;

    private IProfileRepo profileRepo = new RetrofitProfileRepo();

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

        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        toolbar = (Toolbar) v.findViewById(R.id.toolbar);

        sdvAvatar = (SimpleDraweeView) v.findViewById(R.id.sdvAvatar);

        photoCounter = (CounterView) v.findViewById(R.id.photoCounter);
        friendsCounter = (CounterView) v.findViewById(R.id.friendsCounter);
        followersCounter = (CounterView) v.findViewById(R.id.followersCounter);

        tvFullNAme = (TextView) v.findViewById(R.id.tvFullName);
        tvStatus = (TextView) v.findViewById(R.id.tvStatus);

        viewPager = (ViewPager) v.findViewById(R.id.viewPager);
        viewPager.setAdapter(new ViewPagerAdapter(getFragmentManager()));

        tabLayout = (TabLayout) v.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        toolbar.setTitle("Sanchezzz");

        photoCounter.setTitle(R.string.profile_counter_title_photos);
        friendsCounter.setTitle(R.string.profile_counter_title_friends);
        followersCounter.setTitle(R.string.profile_counter_title_followers);

        loadProfile();
    }

    private void loadProfile(){
        profileRepo.getProfile(
                Preferences.with(getActivity()).getUser(),
                new Repo.Result<User>() {
                    @Override
                    public void response(User user) {
                        onProfileLoaded(user);
                    }
                }, new Repo.Result<Throwable>() {
                    @Override
                    public void response(Throwable throwable) {
                        AppUtils.makeToast(getActivity(), "Something gone wrong", false);
                    }
                }
        );
    }

    private void onProfileLoaded(User user){
        if(user != null){
            sdvAvatar.setImageURI(user.getPhoto());
            tvFullNAme.setText(user.getFullName());
            tvStatus.setText(user.getStatus());

            photoCounter.setCount(user.getCounters().getPhotos());
            friendsCounter.setCount(user.getCounters().getFriends());
            followersCounter.setCount(user.getCounters().getFollowers());
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new AllAlbumsFragment(), "Albums");
        adapter.addFragment(new AllPhotosFragment(), "Photos");
        viewPager.setAdapter(adapter);
    }


}
