package soft.me.ldc.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ldc45 on 2018/1/14.
 */

public class ToFormat {

    public static String Stream2String(InputStream is) {
        byte[] result;
        int len = 0;
        byte[] buffer = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            buffer = new byte[1024];

            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            is.close();
            baos.flush();
            baos.close();
            result = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            String errorStr = "获取数据失败。";
            return errorStr;
        }
        return new String(result);
    }

    /**
     * 定义一个方法用来格式化获取到的时间
     */
    public static String formatTime(int time) {
        if (time / 1000 % 60 < 10) {
            return time / 1000 / 60 + ":0" + time / 1000 % 60;

        } else {
            return time / 1000 / 60 + ":" + time / 1000 % 60;
        }

    }

}

