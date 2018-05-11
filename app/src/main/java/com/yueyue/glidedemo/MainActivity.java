package com.yueyue.glidedemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.yueyue.glidedemo.base.BaseActivity;
import com.yueyue.glidedemo.base.BaseFragment;
import com.yueyue.glidedemo.module.chapter_1.Chapter1_Fragment;
import com.yueyue.glidedemo.module.chapter_2.Chapter2_Fragment;
import com.yueyue.glidedemo.module.chapter_3.Chapter3_Fragment;
import com.yueyue.glidedemo.module.chapter_4.Chapter4_Fragment;
import com.yueyue.glidedemo.module.chapter_5.Chapter5_Fragment;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(android.R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private ArrayList<BaseFragment> fragmentList = new ArrayList<BaseFragment>(6) {{
        add(Chapter1_Fragment.launch());
        add(Chapter2_Fragment.launch());
        add(Chapter3_Fragment.launch());
        add(Chapter4_Fragment.launch());
        add(Chapter5_Fragment.launch());
    }};


    @Override
    protected int initLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        initAdapter();
        initToolBar();
    }


    private void initToolBar() {
        setToolbarTitle(getString(R.string.app_name));
    }

    private void initAdapter() {
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return getString(R.string.title_chapter1);
                    case 1:
                        return getString(R.string.title_chapter2);
                    case 2:
                        return getString(R.string.title_chapter3);
                    case 3:
                        return getString(R.string.title_chapter4);
                    case 4:
                        return getString(R.string.title_chapter5);
                    case 5:
                        return getString(R.string.title_chapter6);
                    case 6:
                        return getString(R.string.title_chapter7);
                    case 7:
                        return getString(R.string.title_chapter8);
                    case 8:
                        return getString(R.string.title_chapter9);
                    default:
                        return getString(R.string.title_chapter1);
                }
            }
        });

        //产生联动,否则tabLayout不显示内容
        tabLayout.setupWithViewPager(viewPager);
    }


}