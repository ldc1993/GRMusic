package soft.me.ldc.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by liudi on 2018/1/17.
 */

public class GRViewPager extends ViewPager {


    //默认可以滑动
    private volatile boolean iScroll = true;

    //设置属性
    public void setScrollEnable(boolean iScroll) {
        this.iScroll = iScroll;
    }

    public GRViewPager(Context context) {
        super(context);
    }

    public GRViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        return iScroll && super.onTouchEvent(arg0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        return iScroll && super.onInterceptTouchEvent(arg0);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item, true);
    }
}
