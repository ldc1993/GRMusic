package soft.me.ldc.layout;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import soft.me.ldc.R;
import soft.me.ldc.base.RootFragment;
import soft.me.ldc.view.GRToastView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Main2Fragment extends RootFragment {


    @Override
    protected void NewCreate(@Nullable Bundle savedInstanceState) throws Exception {

    }

    @Override
    protected View UI(LayoutInflater inflater) throws Exception {
        return inflater.inflate(R.layout.fragment_main2, null);
    }

    @Override
    protected void Init() throws Exception {

    }

    @Override
    protected void Main() throws Exception {

    }

    @Override
    protected void Exception(Exception e) {
        GRToastView.show(ctx, "系统异常", Toast.LENGTH_SHORT);
    }
}
