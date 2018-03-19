package soft.me.ldc.exceptionhandler.model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by LDC on 2018/3/18.
 */

public class ExceptionBean implements Serializable {
    private final static long serialVersionUID = 1l;
    public Throwable throwable = null;
    public HashMap<String, String> params = new HashMap<>();
}
