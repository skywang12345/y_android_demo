package cn.skw.droidlib.ui;

import cn.skw.droidlib.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.util.Log;

public class PointIndicator extends LinearLayout {
    private final static String TAG ="##skywang-PointIndicator";

    private LinearLayout.LayoutParams mDefaultLayoutParam;

    private int mIndex=0;
    private int mPointNumber;
    private Drawable mDrawableSel;
    private Drawable mDrawableUnsel;

    public PointIndicator(Context context) {
        this(context, null);
    }

    public PointIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 获取自定义的属性(attrs.xml中)对应的TypedArray
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PointIndicator);
        // 获取number属性对应的值
        mPointNumber = a.getInt(R.styleable.PointIndicator_number, 0);
        // 回收TypedArray
        a.recycle();

        // 获取resource 对应的Drawable
        mDrawableSel = context.getResources().getDrawable(R.drawable.pointer_selected);
        mDrawableUnsel = context.getResources().getDrawable(R.drawable.pointer_unselected);

        setGravity(Gravity.CENTER);
        setOrientation(LinearLayout.HORIZONTAL);
        // mDefaultLayoutParam = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
        mDefaultLayoutParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        setupViews(context);
    }

    private void setupViews(Context context) {
        removeAllViews();
        for (int i=0; i<mPointNumber; i++) {
            ImageView imgView = new ImageView(context);
            imgView.setImageDrawable((i==mIndex) ? mDrawableSel : mDrawableUnsel);
            addView(imgView, mDefaultLayoutParam);
        }
    }

    public void setNumber(int number) {
        if (number>0) {
            mPointNumber = number;
            setupViews(getContext());
        }
    }

    public void setCurrentIndex(int index) {
        if (index<0 || index>=getChildCount() || index==mIndex) {
            return ;
        }

        ImageView preView = (ImageView) getChildAt(mIndex);
        ImageView curView = (ImageView) getChildAt(index);
        preView.setImageDrawable(mDrawableUnsel);
        curView.setImageDrawable(mDrawableSel);
        mIndex = index;
    }
}
