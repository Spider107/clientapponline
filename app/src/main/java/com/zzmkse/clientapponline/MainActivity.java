package com.itonemm.clientapponline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.gms.ads.MobileAds;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabLayout tabLayout=findViewById(R.id.tablayout);
        ViewPager pager=findViewById(R.id.viewpager);
        MobileAds.initialize(getApplicationContext(),getResources().getString(R.string.app_id));
        ViewPagerAdapter adapter=new ViewPagerAdapter(getSupportFragmentManager());
       adapter.addFragment(new MovieFragment(),"Movies");
       adapter.addFragment(new SeriesFragment(),"Series");

       pager.setAdapter(adapter);
       tabLayout.setupWithViewPager(pager);

    }
}
