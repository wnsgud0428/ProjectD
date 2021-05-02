package com.example.projectd.ui.movie_list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.projectd.ui.movie_fragment.MovieFragment1;
import com.example.projectd.ui.movie_fragment.MovieFragment2;
import com.example.projectd.ui.movie_fragment.MovieFragment3;
import com.example.projectd.R;

import java.util.ArrayList;

public class MovieListFragment extends Fragment {
    private MovieListViewModel homeViewModel;
    ViewPager pager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(MovieListViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        //필요없음
        /*final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/


        //pager 코딩 시작
        pager = (ViewPager) root.findViewById(R.id.pager);
        pager.setOffscreenPageLimit(3);
        MoviePagerAdapter moviePagerAdapter = new MoviePagerAdapter(getChildFragmentManager());
        MovieFragment1 fragment1 = new MovieFragment1();
        moviePagerAdapter.addItem(fragment1);
        MovieFragment2 fragment2 = new MovieFragment2();
        moviePagerAdapter.addItem(fragment2);
        MovieFragment3 fragment3 = new MovieFragment3();
        moviePagerAdapter.addItem(fragment3);

        pager.setAdapter(moviePagerAdapter);
        //pager 코딩 끝


        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    class MoviePagerAdapter extends FragmentStatePagerAdapter {
        ArrayList<Fragment> items = new ArrayList<Fragment>();

        public MoviePagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        public void addItem(Fragment item){
            items.add(item);
        }

        //implement method
        @NonNull
        @Override
        public Fragment getItem(int position) {
            return items.get(position);
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return "페이지 "+position;
        }
    }


}