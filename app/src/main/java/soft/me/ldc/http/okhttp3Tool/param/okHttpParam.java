package soft.me.ldc.http.okhttp3Tool.param;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/20.
 */

public class okHttpParam {
    public String url;
    public String method = "";//|0 get | 1 post | 2 put | 3 delete | 4 patch| 请求方式
    public Map<String, Object> parems = new HashMap<>();//参数
    public Map<String, String> files = new HashMap<>();//二进制流
    public int requestType = MultipartBodyType;//默认是表单
    //数据提交类型
    public static final int MultipartBodyType = 0x000;//参数加文件
    public static final int MediaType = 0x001;//json提交
    public static final int FormBodyType = 0x002;//参数
}
