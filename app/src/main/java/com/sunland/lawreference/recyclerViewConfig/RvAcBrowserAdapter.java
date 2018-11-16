package com.sunland.lawreference.recyclerViewConfig;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sunland.lawreference.R;
import com.sunland.lawreference.db.BrowserTrackBean;

import java.text.SimpleDateFormat;
import java.util.List;

public class RvAcBrowserAdapter extends RecyclerView.Adapter<RvAcBrowserAdapter.MyViewHolder> {
    private Context context;
    private List<BrowserTrackBean> dataSet;
    private LayoutInflater inflater;
    private SimpleDateFormat format;
    private OnRvItemClickedListener onRvItemClickedListener;

    public RvAcBrowserAdapter(Context context, List<BrowserTrackBean> dataSet) {
        super();
        this.context = context;
        this.dataSet = dataSet;
        inflater = LayoutInflater.from(context);
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    }

    @NonNull
    @Override
    public RvAcBrowserAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.rv_ac_collect_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    public void setOnRvItemClickedListener(OnRvItemClickedListener listener) {
        this.onRvItemClickedListener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull RvAcBrowserAdapter.MyViewHolder myViewHolder, final int i) {
        myViewHolder.tv_timestamp.setText(format.format(dataSet.get(i).timeStamp));
        myViewHolder.tv_title.setText(dataSet.get(i).fileNameCN);
        myViewHolder.rl_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRvItemClickedListener != null)
                    onRvItemClickedListener.onItemClicked(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_title;
        public TextView tv_timestamp;
        public RelativeLayout rl_container;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.title);
            tv_timestamp = itemView.findViewById(R.id.timestamp);
            rl_container = itemView.findViewById(R.id.rl_container);
        }
    }
}
