package soft.me.ldc.view;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import soft.me.ldc.R;


/**
 * Created by LDC on 2017/12/14.
 */

// TODO: 2017/12/14 等待对话框
public class GRLoadDialog {
    private Context ctx = null;
    private AppCompatTextView msg = null;
    private View mainView = null;
    private LinearLayout parentLineLayout = null;
    private Dialog dialog = null;

    public enum Style {
        Blue, White
    }

    private static GRLoadDialog instance = null;

    public static GRLoadDialog Instance(Context ctx, Style style) {
        synchronized (GRLoadDialog.class) {
            instance = new GRLoadDialog(ctx, style);
        }
        return instance;
    }

    // TODO: 2017/12/14 构造函数
    public GRLoadDialog(Context ctx, Style style) {
        this.ctx = ctx;
        switch (style) {
            case Blue:
                mainView = LayoutInflater.from(ctx).inflate(R.layout.gr_load_black_dialog, null, false);
                break;
            case White:
                mainView = LayoutInflater.from(ctx).inflate(R.layout.ldc_load_white_dialog, null, false);
                break;
        }
        dialog = new Dialog(ctx, R.style.ldc_dialog_style);
        dialog.setContentView(mainView);
        msg = mainView.findViewById(R.id.msg);
        parentLineLayout = mainView.findViewById(R.id.parent_linelayout);
        //初始化
        msg.setVisibility(View.GONE);
        // TODO: 2017/12/14 设置大小
        parentLineLayout.setLayoutParams(new FrameLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
    }

    public GRLoadDialog show(String txt, boolean c) {

        {
            //设置内容
            if (msg == null)
                return null;
            if (txt == null || txt.length() == 0)
                msg.setText(null);
            else {
                msg.setText(txt);
                msg.setVisibility(View.VISIBLE);
            }
        }
        {

            if (dialog != null)
                dialog.setCancelable(c);
        }
        if (dialog != null)
            dialog.show();
        return this;
    }


    public boolean isShow() {
        if (dialog != null) {
            return dialog.isShowing();
        } else {
            return false;
        }
    }

    public void dismiss() {
        if (dialog != null)
            dialog.dismiss();
    }
}
