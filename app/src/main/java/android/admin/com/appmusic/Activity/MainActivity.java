package android.admin.com.appmusic.Activity;

import android.admin.com.appmusic.Adapter.MainViewPagerAdapter;
import android.admin.com.appmusic.Fragment.Fragment_BXH;
import android.admin.com.appmusic.Fragment.Fragment_ThongKe;
import android.admin.com.appmusic.Fragment.Fragment_Tim_Kiem;
import android.admin.com.appmusic.Fragment.Fragment_Trang_Chu;
import android.admin.com.appmusic.R;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        init();
    }

    private void init() {
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new Fragment_Trang_Chu(),"");
        mainViewPagerAdapter.addFragment(new Fragment_Tim_Kiem(),"");
        mainViewPagerAdapter.addFragment(new Fragment_BXH(),"");
        mainViewPagerAdapter.addFragment(new Fragment_ThongKe(),"");
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.iconsearch);
        tabLayout.getTabAt(2).setIcon(R.drawable.iconchart);
        tabLayout.getTabAt(3).setIcon(R.drawable.iconstatistics);
    }

    private void AnhXa() {
        tabLayout = findViewById(R.id.myTabLayout);
        viewPager = findViewById(R.id.myViewPager);
    }
}
