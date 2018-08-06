package com.example.dell.kuang;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dell.kuang.DCZ_adapter.MyMainAdapter;
import com.example.dell.kuang.DCZ_fragment.AFragment;
import com.example.dell.kuang.DCZ_fragment.BFragment;
import com.example.dell.kuang.DCZ_fragment.CFragment;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {
    private MainActivity INSTANCE;
    public static ViewPager vp;
    private ArrayList<Fragment> list = new ArrayList<>();   //fragment页面的集合
    private MyMainAdapter adapter;
    private ImageView[] ivs;                                //底部button按钮的图片控件集合
    private TextView[] tvs;                                 //底部按钮字体控件集合

    private RelativeLayout r1;
    private RelativeLayout r2;
    private RelativeLayout r3;
    private int selectedItem;                           //当前显示的item
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        INSTANCE=this;
        setViews();
        initFragmentViewPager();        //将fragment适配到viewpage中
        setSelectedItem(0);
        setListener();
    }

    private void initFragmentViewPager() {
        list.add(new AFragment());
        list.add(new BFragment());
        list.add(new CFragment());
        adapter = new MyMainAdapter(getSupportFragmentManager(), list);
        vp.setOffscreenPageLimit(list.size());                        //设置幕后item的缓存数目
        vp.setAdapter(adapter);                             //给ViewPage设置适配器
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                int count = ivs.length;
                for (int i = 0; i < count; i++) {
                    if (position != 3 || i != 0) {
                        ivs[(position + i) % count].setBackgroundResource(getResources().getIdentifier("t" + (position + i) % count + (i == 0 ? "r" : ""), "mipmap", INSTANCE.getPackageName()));
                        tvs[i].setTextColor(getResources().getColor((R.color.colorAccent)));
                    } else {
                        count += 1;
                    }
                }
                tvs[position].setTextColor(getResources().getColor((R.color.colorPrimary)));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setViews() {
        vp = (ViewPager) findViewById(R.id.main_viewpager);//ViewPage控件
        r1=(RelativeLayout)findViewById(R.id.rl0);
        r2=(RelativeLayout)findViewById(R.id.rl1);
        r3=(RelativeLayout)findViewById(R.id.rl2);

        ivs = new ImageView[]{(ImageView) findViewById(R.id.iv0),(ImageView) findViewById(R.id.iv1),(ImageView) findViewById(R.id.iv2)};
        tvs = new TextView[]{(TextView) findViewById(R.id.tv0),(TextView) findViewById(R.id.tv1),(TextView) findViewById(R.id.tv2)};
    }

    private void setListener() {

    }

    public void setSelectedItem(int pos) {
        vp.setCurrentItem(pos, false);
    }

    public void onTabClicked(View view) {
        switch (view.getId()) {
            case R.id.rl0:
                setSelectedItem(0);
                break;
            case R.id.rl1:
                setSelectedItem(1);
                break;
            case R.id.rl2:
                setSelectedItem(2);
                break;
        }
    }
}
