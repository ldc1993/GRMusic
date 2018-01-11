package soft.me.ldc.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import soft.me.ldc.R;
import soft.me.ldc.utils.StringUtil;

/**
 * Created by liudi on 2018/1/11.
 */

public class GRSearchToolbar extends Toolbar {
    private View mainView = null;
    private LinearLayoutCompat leftBtn, rightBtn, titleBtn;
    private AppCompatTextView leftTv, rightTv;
    private AppCompatEditText titleEt;
    private AppCompatImageView leftIv, rightIv;
    private LinearLayoutCompat.LayoutParams params = null;

    public GRSearchToolbar(Context context) {
        super(context, null);
    }

    public GRSearchToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        UI(context, attrs);
    }

    public GRSearchToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs);
    }


    private void UI(Context context, @Nullable AttributeSet attrs) {
        mainView = LayoutInflater.from(context).inflate(R.layout.gr_search_toolbar, null, true);
        leftBtn = mainView.findViewById(R.id.leftBtn);
        titleBtn = mainView.findViewById(R.id.titleBtn);
        rightBtn = mainView.findViewById(R.id.rightBtn);

        leftTv = mainView.findViewById(R.id.leftTv);
        titleEt = mainView.findViewById(R.id.titleEt);
        rightTv = mainView.findViewById(R.id.rightTv);

        leftIv = mainView.findViewById(R.id.leftIv);
        rightIv = mainView.findViewById(R.id.rightIv);

        //初始化
        if (leftTv != null)
            leftTv.setVisibility(GONE);
        if (titleEt != null)
            titleEt.setVisibility(GONE);
        if (rightTv != null)
            rightTv.setVisibility(GONE);
        if (leftIv != null)
            leftIv.setVisibility(GONE);
        if (rightIv != null)
            rightIv.setVisibility(GONE);
        if (params == null)
            params = new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setContentInsetsRelative(10, 10);
        this.setTitleMargin(0, 0, 0, 0);
        this.addView(mainView, params);

    }

    public void setColor(int color) {
        if (this != null)
            this.setBackgroundColor(color);
    }

    public void setColorRes(int res) {
        if (this != null)
            this.setBackgroundResource(res);
    }

    public void setLeftBtnListener(View.OnClickListener listener) {
        if (this.leftBtn != null)
            this.leftBtn.setOnClickListener(listener);
    }

    public void setRightBtnListener(View.OnClickListener listener) {
        if (this.rightBtn != null)
            this.rightBtn.setOnClickListener(listener);
    }

    public void setTitleBtnListener(View.OnClickListener listener) {
        if (this.titleBtn != null)
            this.titleBtn.setOnClickListener(listener);
    }

    public void setLeftText(String txt) {
        if (this.leftTv != null) {
            this.leftTv.setVisibility(VISIBLE);
            if (this.leftIv != null)
                this.leftIv.setVisibility(GONE);
            if (StringUtil.isNotBlank(txt)) {
                this.leftTv.setText(txt);
            } else {
                this.leftTv.setText("");
            }
        }
    }

    public void setSearchText(String txt) {
        if (this.titleEt != null) {
            this.titleEt.setVisibility(VISIBLE);
            if (StringUtil.isNotBlank(txt)) {
                this.titleEt.setText(txt);
            } else {
                this.titleEt.setText("");
            }
        }
    }

    public void setSearchHint(String txt) {
        if (this.titleEt != null) {
            this.titleEt.setVisibility(VISIBLE);
            if (StringUtil.isNotBlank(txt)) {
                this.titleEt.setHint(txt);
            } else {
                this.titleEt.setHint("");
            }
        }
    }

    public AppCompatEditText getSearchEt(String txt) {
        if (this.titleEt != null) {
            return this.titleEt;
        } else {
            return null;
        }
    }

    public void setRightText(String txt) {
        if (this.rightTv != null) {
            this.rightTv.setVisibility(VISIBLE);
            if (this.rightIv != null)
                this.rightIv.setVisibility(GONE);
            if (StringUtil.isNotBlank(txt)) {
                this.rightTv.setText(txt);
            } else {
                this.rightTv.setText("");
            }
        }
    }

    public void setLeftImg(int res) {
        if (this.leftIv != null) {
            this.leftIv.setVisibility(VISIBLE);
            if (this.leftTv != null)
                this.leftTv.setVisibility(GONE);
            this.leftIv.setImageResource(res);
        }
    }

    public void setLeftImg(Icon res) {
        if (this.leftIv != null) {
            this.leftIv.setVisibility(VISIBLE);
            if (this.leftTv != null)
                this.leftTv.setVisibility(GONE);
            this.leftIv.setImageIcon(res);
        }
    }

    public void setLeftImg(Uri res) {
        if (this.leftIv != null) {
            this.leftIv.setVisibility(VISIBLE);
            if (this.leftTv != null)
                this.leftTv.setVisibility(GONE);
            this.leftIv.setImageURI(res);
        }
    }

    public void setLeftImg(Bitmap res) {
        if (this.leftIv != null) {
            this.leftIv.setVisibility(VISIBLE);
            if (this.leftTv != null)
                this.leftTv.setVisibility(GONE);
            this.leftIv.setImageBitmap(res);
        }
    }

    public void setLeftImg(Drawable res) {
        if (this.leftIv != null) {
            this.leftIv.setVisibility(VISIBLE);
            if (this.leftTv != null)
                this.leftTv.setVisibility(GONE);
            this.leftIv.setImageDrawable(res);
        }
    }

    public void setRightImg(int res) {
        if (this.rightIv != null) {
            this.rightIv.setVisibility(VISIBLE);
            if (this.rightTv != null)
                this.rightTv.setVisibility(GONE);
            this.rightIv.setImageResource(res);
        }
    }

    public void setRightImg(Icon res) {
        if (this.rightIv != null) {
            this.rightIv.setVisibility(VISIBLE);
            if (this.rightTv != null)
                this.rightTv.setVisibility(GONE);
            this.rightIv.setImageIcon(res);
        }
    }

    public void setRightImg(Uri res) {
        if (this.rightIv != null) {
            this.rightIv.setVisibility(VISIBLE);
            if (this.rightTv != null)
                this.rightTv.setVisibility(GONE);
            this.rightIv.setImageURI(res);
        }
    }

    public void setRightImg(Bitmap res) {
        if (this.rightIv != null) {
            this.rightIv.setVisibility(VISIBLE);
            if (this.rightTv != null)
                this.rightTv.setVisibility(GONE);
            this.rightIv.setImageBitmap(res);
        }
    }

    public void setRightImg(Drawable res) {
        if (this.rightIv != null) {
            this.rightIv.setVisibility(VISIBLE);
            if (this.rightTv != null)
                this.rightTv.setVisibility(GONE);
            this.rightIv.setImageDrawable(res);
        }
    }
}
