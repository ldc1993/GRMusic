package soft.me.ldc.utils;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;

import soft.me.ldc.view.ToastView;


/**
 * @author DK
 */
public class CommonFileLogUtil {
    private Context context = null;
    private String StateStr = "";
    public volatile String filePath = "";
    private final String defaultDir = "ErrorLog";

    public enum Self {
        INSTANCE;
        private CommonFileLogUtil instance = null;

        Self() {
            if (instance == null)
                instance = new CommonFileLogUtil();
        }

        public CommonFileLogUtil getInstance() {
            return instance;
        }

    }


    // TODO: 2018/3/17 私有构造函数
    private CommonFileLogUtil() {
    }

    public void Service(Context context) {
        this.context = context;


    }

    /**
     * 数据 文件名称 默认文件夹 DK
     *
     * @param data
     * @param filename
     */
    public void WriteToSD(String data, String filename) {
        StateStr = Environment.getExternalStorageState();
        if (!StateStr.equals(Environment.MEDIA_MOUNTED)) {
            ToastView.show(context, "没有内存卡", Toast.LENGTH_SHORT);
            return;
        }
        try {
            filePath = Environment.getExternalStorageDirectory().toString() + File.separator + defaultDir
                    + File.separator + filename + ".log";
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdir();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] buff = data.getBytes();
            fileOutputStream.write(buff);
            fileOutputStream.flush();
            fileOutputStream.close();

        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }

    /**
     * 数据 文件夹名称 文件名称
     *
     * @param data
     * @param Dir
     * @param filename
     */
    public void WriteToSD(String data, String Dir, String filename) {
        StateStr = Environment.getExternalStorageState();
        if (!StateStr.equals(Environment.MEDIA_MOUNTED)) {
            ToastView.show(context, "没有内存卡", Toast.LENGTH_SHORT);
            return;
        }
        try {
            filePath = Environment.getExternalStorageDirectory().toString() + File.separator + Dir + File.separator
                    + filename + ".log";
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdir();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] buff = data.getBytes();
            fileOutputStream.write(buff);
            fileOutputStream.flush();
            fileOutputStream.close();

        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }

    /**
     * 数据 文件夹名称 文件名称
     *
     * @param data
     * @param Dir
     * @param filename
     */
    public void WriteToSD(HashMap<String, String> data, String Dir, String filename) {
        StateStr = Environment.getExternalStorageState();
        if (!StateStr.equals(Environment.MEDIA_MOUNTED)) {
            ToastView.show(context, "没有内存卡", Toast.LENGTH_SHORT);
            return;
        }
        try {
            StringBuffer sbstr = new StringBuffer();
            //路径
            filePath = Environment.getExternalStorageDirectory().toString() + File.separator + Dir + File.separator
                    + filename + ".log";
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdir();
            }
            //便利数据
            if (data != null && data.size() > 0) {
                for (HashMap.Entry<String, String> item : data.entrySet()) {
                    sbstr.append(String.format("%s:%s", item.getKey() + "", item.getValue() + ""));
                }
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] buff = sbstr.toString().getBytes();
            fileOutputStream.write(buff);
            fileOutputStream.flush();
            fileOutputStream.close();

        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }

}
