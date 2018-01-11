package soft.me.ldc.layout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import soft.me.ldc.R;
import soft.me.ldc.adapter.StyleThemeAdapter;
import soft.me.ldc.base.RootActivity;
import soft.me.ldc.view.GRToastView;
import soft.me.ldc.view.GRToolbar;

public class StyleThemeActivity extends RootActivity {


    @BindView(R.id.mToolbar)
    GRToolbar mToolbar;
    @BindView(R.id.mList)
    RecyclerView mList;

    private LinearLayoutManager llm = null;
    private StyleThemeAdapter styleThemeAdapter = null;
    private List<String> colors = null;
    private List<String> colortexts = null;
    private List<Boolean> colorstates = null;

    @Override
    protected void NewCreate(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected Integer UI() {
        return R.layout.activity_style_theme;
    }


    @Override
    protected void Main() {
        {
            mToolbar.setLeftText("返回");
            mToolbar.setTitleText("修改主题");
            mToolbar.setColorRes(R.color.colorPrimary);
            mToolbar.setLeftBtnListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            setSupportActionBar(mToolbar);
        }
        //初始化数据
        {
            if (colors == null)
                colors = new ArrayList<>();
            if (colortexts == null)
                colortexts = new ArrayList<>();
            if (colorstates == null)
                colorstates = new ArrayList<>();

            colors.add("#ffb6c1");
            colors.add("#ffa500");
            colors.add("#ffa07a");
            colors.add("#ff8c00");
            //
            colortexts.add("亮粉红色");
            colortexts.add("橙色");
            colortexts.add("亮肉色");
            colortexts.add("暗桔黄色");
            //
            colorstates.add(false);
            colorstates.add(false);
            colorstates.add(false);
            colorstates.add(false);

        }
        if (styleThemeAdapter == null)
            styleThemeAdapter = new StyleThemeAdapter();
        if (llm == null)
            llm = new LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false);
        styleThemeAdapter.setItemListener(new StyleThemeListener());
        styleThemeAdapter.setCheckedlistener(new StyleThemeListener());
        styleThemeAdapter.pushData(colors, colortexts);

        mList.setLayoutFrozen(true);
        mList.setHasFixedSize(true);
        mList.setLayoutManager(llm);
        mList.setAdapter(styleThemeAdapter);

    }


    @Override
    protected void Error(Exception e) {
        GRToastView.show(ctx, "系统异常", Toast.LENGTH_SHORT);
    }


    // TODO: 2018/1/11 选择颜色
    class StyleThemeListener implements StyleThemeAdapter.ItemListener, StyleThemeAdapter.ItemCheckListener {

        @Override
        public void onItemClick(View view, int position) {
            GRToastView.show(ctx, "item" + position, Toast.LENGTH_SHORT);
        }


        @Override
        public void onItemCheckedClick(int position, boolean checked) {
            if (styleThemeAdapter != null) {
                if (checked == true) {
                    styleThemeAdapter.setIsCheckedIndex(position);
                }
                GRToastView.show(ctx, "哈喽" + position + checked, Toast.LENGTH_SHORT);
            }
        }
    }
}
