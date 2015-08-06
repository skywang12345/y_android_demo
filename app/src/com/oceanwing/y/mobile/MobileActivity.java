package com.oceanwing.y.mobile;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.TextView;
import android.util.Log;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import java.util.ArrayList;
import java.util.List;

public class MobileActivity extends SlidingFragmentActivity
    implements View.OnClickListener {
    private static final String TAG = "##skywang-Main";

    private TextView mTvSearch;
    private Fragment mFrag;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate");

        // 如果保存的状态不为空则得到之前保存的Fragment，否则实例化MobileFragment  
        if (savedInstanceState != null) {  
            mFrag = getSupportFragmentManager().getFragment(
                    savedInstanceState, "mobile_frag");  
        }  
        if (mFrag == null) {  
            mFrag = new MobileFragment();
        }

        // 设置主界面
        setContentView(R.layout.mobile_activity);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, mFrag).commit();

        // 设置滑动菜单的视图 
        setBehindContentView(R.layout.mobile_menu);
        getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame, new MobileMenuFragment()).commit();

        initSlidingMenu(savedInstanceState);

        // 设置控件
        findViewById(R.id.header_left_img).setOnClickListener(this);
        findViewById(R.id.btn_lucky).setOnClickListener(this);

        // 搜索栏
        findViewById(R.id.search_container).setOnClickListener(this);
        mTvSearch = (TextView) findViewById(R.id.search_edit);
    }

    private void initSlidingMenu(Bundle savedInstanceState) {
        // 设置标题
        // setTitle("MMMM-HHHHH");

        // 实例化滑动菜单对象
        SlidingMenu sm = getSlidingMenu();
        // 设置滑动阴影的宽度
        sm.setShadowWidthRes(R.dimen.shadow_width);
        // 设置滑动阴影的图像资源 
        sm.setShadowDrawable(R.drawable.shadow);
        // 设置滑动背后的试图的宽度
        // sm.setBehindOffset(ContextUtil.getScreenWidth(this)/3);
        sm.setBehindOffset(100);
        // 设置渐入渐出效果的值 
        sm.setFadeDegree(0.35f);
        // 设置触摸屏幕的模式
        // sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, "mobile_frag", mFrag);
    }

    void switchContent(Fragment fragment) {
        Log.d(TAG, "switchContent");
        mFrag = fragment;
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
        getSlidingMenu().showContent();
    }

    /**
     * 隐藏SlidingMenu
     */
	void hideSlideMenu() {
        SlidingMenu sm = getSlidingMenu();
        if (sm!=null && sm.isMenuShowing()) {
            toggle();
        }
    }

    public void setSearchText(String text) {
        mTvSearch.setText(text);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.header_left_img:
                toggle();
                break;
            case R.id.btn_lucky:
                break;
            case R.id.search_container:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Log.d(TAG, "onActivityResult: requestCode="+requestCode+", resultCode="+resultCode+", intent="+intent);
    }
}
