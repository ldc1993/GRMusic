package soft.me.ldc.view;

import android.content.Context;
import android.widget.Toast;

import soft.me.ldc.utils.stringUtil;

/**
 * Created by liudi on 2018/1/10.
 */

public class toastView {
    static Toast toast = null;


    public static void show(Context ctx, String txt, Integer integer) {
        if (toast == null) {
            if (stringUtil.isNotBlank(txt)) {
                toast = Toast.makeText(ctx, txt, integer);
            } else {
                toast = Toast.makeText(ctx, "", integer);
            }
        } else {
            toast.setDuration(integer);
            if (stringUtil.isNotBlank(txt)) {
                toast.setText(txt);
            } else {
                toast.setText("");
            }
        }
        toast.show();
    }
}
