package soft.me.ldc.component;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by ldc45 on 2018/1/20.
 */

public class MusicPlayer extends MediaPlayer {

    static MusicPlayer instance = null;
    Context ctx = null;

    public static MusicPlayer newInstance(Context ctx) {
        synchronized (MusicPlayer.class) {
            if (instance == null)
                instance = new MusicPlayer(ctx);
        }
        return instance;
    }

    private MusicPlayer(Context ctx) {
        this.ctx = ctx;
    }

}
