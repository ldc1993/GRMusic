package soft.me.ldc.exceptionhandler.layout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatTextView;
import android.text.ClipboardManager;
import android.widget.Toast;

import java.util.HashMap;

import butterknife.BindView;
import soft.me.ldc.R;
import soft.me.ldc.base.RootMusicActivity;
import soft.me.ldc.exceptionhandler.model.ExceptionBean;
import soft.me.ldc.permission.ActivityList;
import soft.me.ldc.view.GRToolbar;
import soft.me.ldc.view.ToastView;

public class Activity_Error extends RootMusicActivity {
    @BindView(R.id.titlebar)
    GRToolbar titlebar;
    @BindView(R.id.errortxt)
    AppCompatTextView errortxt;
    private Intent getIt = null;
    private ExceptionBean bean = null;


    @Override
    protected void NewCreate(@Nullable Bundle savedInstanceState) {
        getIt = getIntent();
        bean = (ExceptionBean) getIt.getSerializableExtra("errorLog");
    }

    @Override
    protected Integer UI() {
        return R.layout.activity_error;
    }

    @Override
    protected void Main() {
        {
            titlebar.setTitleText("Log(GG)");
            titlebar.setLeftImg(R.mipmap.back_icon);
            titlebar.setRightImg(R.mipmap.setting_icon);
            titlebar.setLeftBtnListener(v -> {
                finish();
                Process.killProcess(Process.myPid());
            });
            titlebar.setRightBtnListener(v -> {
                perDialog();
            });
        }
        showLog(bean);

    }

    // TODO: 2018/3/18 显示日志
    private synchronized void showLog(ExceptionBean exceptionBean) {
        if (exceptionBean == null)
            return;

        if (bean.throwable != null) {
            errortxt.append(String.format("[Cause]::%s\n", bean.throwable.getLocalizedMessage()));
            errortxt.append("\n");
        }
        if (bean.params != null && bean.params.size() > 0) {
            for (HashMap.Entry<String, String> item : bean.params.entrySet()) {
                String str = String.format("[%s]::%s\n", item.getKey(), item.getValue());
                errortxt.append(str);
                errortxt.append("\n");
            }

        }
    }

    // TODO: 2018/3/19
    private void perDialog() {
        String[] items = {"复制", "上传", "取消"};
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setItems(items, (dialog, which) -> {
            switch (which) {
                case 0: {
                    try {
                        String copyStr = errortxt.getText().toString();
                        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        cm.setText(copyStr);
                        ToastView.show(ctx, "复制成功", Toast.LENGTH_SHORT);
                    } catch (Exception e) {
                        ToastView.show(ctx, "复制失败", Toast.LENGTH_SHORT);
                        e.printStackTrace();
                    }
                }
                break;
                case 1:
                    ToastView.show(ctx, "提交服务器", Toast.LENGTH_SHORT);
                    break;
                case 2:
                    dialog.dismiss();
                    break;
            }

        });
        builder.create();
        builder.show();
    }
}
