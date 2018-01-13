package soft.me.ldc.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import okhttp3.Response;
import soft.me.ldc.BuildConfig;
import soft.me.ldc.config.AppConfig;
import soft.me.ldc.http.okhttp3Request;
import soft.me.ldc.http.param.HttpParam;
import soft.me.ldc.model.Music;

/**
 * Created by ldc45 on 2018/1/13.
 */

public class MusicService {
    static String result = "";
    static Response response = null;
    static Music music = null;
    static Gson gson = null;

    /**
     * @param type   音乐类型
     * @param size   返回数量
     * @param offset 偏移 分页
     * @return
     */
    static public Music MusicList(String type, int size, int offset) {
        music = null;
        response = null;
        gson = null;
        try {
            HttpParam param = new HttpParam();
            param.url = AppConfig.ServiceUrl;
            param.method = "0";//get
            param.requestType = HttpParam.FormBody;
            param.parems.put("method", "baidu.ting.billboard.billList");
            param.parems.put("type", "" + type);
            param.parems.put("size", "" + size);
            param.parems.put("offset", "" + offset);
            response = okhttp3Request.getInstance().Method(param);
            if (response != null && response.isSuccessful()) {
                if (gson == null)
                    gson = new Gson();
                music = gson.fromJson(response.body().toString(), Music.class);
            } else {
                music = null;
                throw new Exception("失败");
            }

        } catch (Exception e) {
            music = null;
            e.printStackTrace();
        } finally {
            if (response != null)
                response.close();
        }
        return music;

    }

}
