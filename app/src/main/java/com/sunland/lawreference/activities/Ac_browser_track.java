package com.sunland.lawreference.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.sunland.lawreference.Frg_dialog;
import com.sunland.lawreference.R;
import com.sunland.lawreference.db.BrowserTrackBean;
import com.sunland.lawreference.db.MdbOpenHelper;
import com.sunland.lawreference.db.MyDatabase;
import com.sunland.lawreference.recyclerViewConfig.OnRvItemClickedListener;
import com.sunland.lawreference.recyclerViewConfig.RvAcBrowserAdapter;
import com.sunland.lawreference.recyclerViewConfig.Rv_Item_decoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class Ac_browser_track extends Ac_base {

    private List<BrowserTrackBean> browserData;
    @BindView(R.id.browser_data)
    public RecyclerView rv_browser_data;

    private RvAcBrowserAdapter adapter;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            browserData.clear();
            browserData.addAll((List<BrowserTrackBean>) msg.obj);
            adapter.notifyDataSetChanged();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.ac_browser_track);
        setToolbarTitle("浏览记录");
        showNavIcon(true);
        initBrowserTrack();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getBrowserData();
    }

    private void getBrowserData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                MdbOpenHelper.createDb(Ac_browser_track.this);
                MyDatabase mdb = MdbOpenHelper.getDb();
                Message msg = handler.obtainMessage();
                msg.obj = mdb.getTrackDao().getList();
                handler.sendMessage(msg);

            }
        }).start();
    }

    private void initBrowserTrack() {
        browserData = new ArrayList<>();
        adapter = new RvAcBrowserAdapter(this, browserData);
        adapter.setOnRvItemClickedListener(new OnRvItemClickedListener() {
            @Override
            public void onItemClicked(int i) {
                Bundle data = new Bundle();
                data.putString("FILENAME", browserData.get(i).fileNameEN);
                data.putString("title", browserData.get(i).fileNameCN);
                hop2Activity(Ac_info_detail.class, data);
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rv_browser_data.setAdapter(adapter);
        rv_browser_data.setLayoutManager(manager);
        rv_browser_data.addItemDecoration(new Rv_Item_decoration(this));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ac_browser, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.delete:
                if (browserData == null || browserData.isEmpty()) {
                    Toast.makeText(this, "当前无浏览记录", Toast.LENGTH_SHORT).show();
                } else {
                    Frg_dialog dialog = new Frg_dialog();
                    dialog.setOnAlertDialogClickedListener(new Frg_dialog.OnAlertDialogClickedListener() {
                        @Override
                        public void onPositiveButtonClicked(DialogInterface dialog, int which) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    MdbOpenHelper.createDb(Ac_browser_track.this);
                                    MyDatabase mdb = MdbOpenHelper.getDb();
                                    mdb.getTrackDao().deleteAllRecords();
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            browserData.clear();
                                            adapter.notifyDataSetChanged();
                                        }
                                    });
                                }
                            }).start();
                        }

                        @Override
                        public void onNegativeButtonClicked(DialogInterface dialog, int which) {

                        }
                    });
                    dialog.show(getSupportFragmentManager(), "delete_dialog");
                }

                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
