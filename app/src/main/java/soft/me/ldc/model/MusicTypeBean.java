package soft.me.ldc.model;

import java.io.Serializable;

/**
 * Created by ldc45 on 2018/1/13.
 */

public class MusicTypeBean implements Serializable {
    static final long serialVersionUID = 1l;

    public MusicTypeBean(String typeCode, String typeName, Integer typeIcon) {
        this.typeCode = typeCode;
        this.typeName = typeName;
        this.typeIcon = typeIcon;
    }

    public String typeCode = "";
    public String typeName = "";
    public Integer typeIcon;
}
