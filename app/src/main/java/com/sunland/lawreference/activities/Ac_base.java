package com.sunland.lawreference.activities;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sunland.lawreference.MyApplication;
import com.sunland.lawreference.R;
import com.sunland.lawreference.utils.DialogUtils;
import com.sunland.lawreference.utils.WindowInfoUtils;

import butterknife.ButterKnife;
import cn.com.cybertech.pdk.OperationLog;

public class Ac_base extends AppCompatActivity {
    public Toolbar toolbar;
    private FrameLayout container;
    private ImageView nav_back;
    private TextView tb_title;
    public LinearLayout root;
    public MyApplication mApplication;
    public DialogUtils dialogUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_base);

        mApplication = (MyApplication) getApplication();
        toolbar = findViewById(R.id.toolbar);
        container = findViewById(R.id.container);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP&&setImmersive()) {
            Window window = getWindow();
            window.setStatusBarColor(Color.TRANSPARENT);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            TypedArray actionbarSizeTypedArray = obtainStyledAttributes(new int[]{android.R.attr.actionBarSize});
            float actionBarHeight = actionbarSizeTypedArray.getDimension(0, 0);
            actionbarSizeTypedArray.recycle();
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    WindowInfoUtils.getStatusBarHeight(this) + (int) actionBarHeight);
            toolbar.setLayoutParams(lp);
        }
        nav_back = findViewById(R.id.nav_back);
        root = findViewById(R.id.root);
        nav_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tb_title = findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        dialogUtils = DialogUtils.getInstance();
    }

    public void setContentLayout(int layout) {
        getLayoutInflater().inflate(layout, container, true);
        ButterKnife.bind(this);
    }

    public boolean setImmersive() {
        return true;
    }

    public void setToolbarTitle(String title) {
        tb_title.setText(title);
    }

    public void showNavIcon(boolean isShow) {
        if (isShow)
            nav_back.setVisibility(View.VISIBLE);
        else
            nav_back.setVisibility(View.GONE);
    }

    //pstore日志记录
    public void saveLog(int operateType, int operationResult, String operateCondition) {
        if (mApplication.isAppCyber()) {
            OperationLog.saveLog(this
                    , getTitle().toString()
                    , "com.sunland.lawreference"
                    , "lawreference"
                    , operateType
                    , OperationLog.OperationResult.CODE_SUCCESS
                    , 1
                    , operateCondition);
        } else {
            return;
        }
    }

    //
    public String appendString(String... strings) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            sb.append(strings[i]);
            if (i != strings.length - 1) {
                sb.append("@");
            }
        }
        return sb.toString();
    }

    public void hop2Activity(Class<? extends Ac_base> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    public void hop2Activity(Class<? extends Ac_base> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra("bundle", bundle);
        startActivity(intent);
    }


}
