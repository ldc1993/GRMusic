package soft.me.ldc.view;

import android.content.Context;
import android.widget.Toast;

import soft.me.ldc.utils.StringUtil;

/**
 * Created by liudi on 2018/1/10.
 */

public class GRToastView {
    static Toast toast = null;

    public static void show(Context ctx, String txt, Integer lenght) {
        if (toast == null) {
            if (StringUtil.isNotBlank(txt)) {
                toast = Toast.makeText(ctx, txt, lenght);
            } else {
                toast = Toast.makeText(ctx, "", lenght);
            }
        } else {
            toast.setDuration(lenght);
            if (StringUtil.isNotBlank(txt)) {
                toast.setText(txt);
            } else {
                toast.setText("");
            }
        }
        toast.show();
    }
}
