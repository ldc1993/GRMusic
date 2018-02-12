package soft.me.ldc.layout;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import soft.me.ldc.R;
import soft.me.ldc.base.RootFragment;
import soft.me.ldc.view.GRToastView;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocalMusicFragment extends RootFragment {


    @BindView(R.id.mList)
    RecyclerView mList;
    @BindView(R.id.smartrefreshlayout)
    SmartRefreshLayout smartrefreshlayout;





    final static int ERRORCODE = 0x000;
    final static int REFRESHCODE = 0x001;
    final static int NODATACODE = 0x002;
    Handler dkhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ERRORCODE:
                    break;
                case REFRESHCODE:
                    break;
                case NODATACODE:
                    break;
            }
        }
    };

    @Override
    protected void NewCreate(@Nullable Bundle savedInstanceState) throws Exception {

    }

    @Override
    protected View UI(LayoutInflater inflater) throws Exception {
        return inflater.inflate(R.layout.fragment_local_music, null, false);
    }

    @Override
    protected void Init() throws Exception {

    }

    @Override
    protected void Main() throws Exception {

    }

    @Override
    protected void Exception(Exception e) {
        GRToastView.show(ctx, "系统异常", Toast.LENGTH_SHORT);
    }

}
