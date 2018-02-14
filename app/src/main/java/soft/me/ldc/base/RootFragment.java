package soft.me.ldc.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import soft.me.ldc.view.GRToastView;


/**
 * Created by LDC on 2017/7/31.
 */

public abstract class RootFragment extends Fragment {
    protected Context ctx = null;
    protected Activity act = null;
    protected View rootView = null;
    protected FragmentManager fragmentManager = null;
    private Unbinder unbinder = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.ctx = getContext();
            this.act = getActivity();
            NewCreate(savedInstanceState);
            this.fragmentManager = getChildFragmentManager();
        } catch (Exception e) {
            Error(e);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try {
            rootView = UI(inflater);
            unbinder = ButterKnife.bind(this, rootView);
        } catch (Exception e) {
            Error(e);
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            Init();
            Main();
        } catch (Exception e) {
            Error(e);
        }
    }

    // TODO: 2017/9/5 新建
    protected abstract void NewCreate(@Nullable Bundle savedInstanceState) throws Exception;

    // TODO: 2017/9/5 加载视图
    protected abstract View UI(LayoutInflater inflater) throws Exception;

    // TODO: 2017/9/5 设置事物
    protected abstract void Init() throws Exception;

    // TODO: 2018/1/12 入口
    protected abstract void Main() throws Exception;

    // TODO: 2017/9/5 异常捕获
    private void Error(Exception e) {
        GRToastView.show(ctx, "" + e.getLocalizedMessage(), Toast.LENGTH_SHORT);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
