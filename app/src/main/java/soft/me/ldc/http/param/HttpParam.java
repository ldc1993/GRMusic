package soft.me.ldc.http.param;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/20.
 */

public class HttpParam {
    public String url;
    public String method = "";//0 get 1 post 2 put 3 delete 4 patch 请求方式
    public Map<String, Object> parems = new HashMap<>();
    public Map<String, String> files = new HashMap<>();
    public String json = "";
    public int submitType = submitType_Multipart;//默认是表单
    //数据提交类型
    public static final int submitType_Multipart = 0x000;//表单提交_参数_文件提交
    public static final int submitType_Json = 0x001;//json数据提交_Json数据
    public static final int submitType_Default = 0x002;//字段拼接_参数提交
}
