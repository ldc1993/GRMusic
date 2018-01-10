package soft.me.ldc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Toast;

import soft.me.ldc.base.RootActivity;
import soft.me.ldc.view.ToastView;

public class LauncherUI extends RootActivity {

    AppCompatButton btn = null;

    @Override
    protected void NewUI(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected Integer UI() {
        return R.layout.launcherui;
    }

    @Override
    protected void ViewId() {
        if (btn == null)
            btn = findViewById(R.id.btn);
    }

    @Override
    protected void Main() {
        btn.setOnClickListener(new OnClickListener());
    }

    @Override
    protected void Error(Exception e) {

        ToastView.show(ctx, "系统异常", Toast.LENGTH_SHORT);
    }


    class OnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn:
                    ToastView.show(ctx, "测试数据", Toast.LENGTH_SHORT);
                    break;
            }
        }
    }


}
