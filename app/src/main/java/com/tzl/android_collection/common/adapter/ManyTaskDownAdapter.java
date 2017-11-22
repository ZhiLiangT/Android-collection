package com.tzl.android_collection.common.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tzl.android_collection.R;
import com.tzl.android_collection.common.bean.DownInfo;
import com.tzl.android_collection.main_utils.DBHelper;
import com.tzl.android_collection.main_utils.NetStateUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by tianzl on 2017/11/18.
 */

public class ManyTaskDownAdapter extends RecyclerView.Adapter<ManyTaskDownAdapter.ViewHolder> {

    private List<DownInfo> data;
    private Context context;
    private LayoutInflater inflater;
    private Map<Integer,Boolean> map;
    private DBHelper dbHelper;
    private static final String TAG = "ManyTaskDownAdapter";
    public ManyTaskDownAdapter(Context context,List<DownInfo> data){
        this.context=context;
        this.data=data;
        inflater=LayoutInflater.from(context);
        initMap();
    }

    public void initMap(){

        map=new LinkedHashMap<>();
        for (int i=0;i<data.size();i++){
            map.put(i,false);
        }
    }
    public void changeList(List<DownInfo> list){
        data.removeAll(data);
        data.clear();
        data.addAll(list);
        initMap();
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_common_down,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvName.setText(data.get(position).getResName());
        if (data.get(position).getResType()==0){
            holder.ivIcon.setImageResource(R.mipmap.file_mp3);
        }else {
            holder.ivIcon.setImageResource(R.mipmap.file_mp4);
        }
        int state=data.get(position).getResState();
        if (state==0){
            //未下载
            holder.btPause.setVisibility(View.GONE);
            holder.seekBar.setVisibility(View.GONE);
            holder.tvCurrSpeed.setVisibility(View.GONE);
            holder.tvTotalSize.setVisibility(View.GONE);
            holder.btDownOrDel.setSelected(true);
            holder.btDownOrDel.setTextColor(Color.parseColor("#F00001"));
            holder.btDownOrDel.setText("下载");
        }else if (state==1){
            //下载中
            holder.btPause.setVisibility(View.VISIBLE);
            Log.i("ManyTaskDownAdapter","map:"+map.toString());
            if (map.get(position)){
                listener.onItemPause();
                holder.btPause.setText("继续");
            }else {
                listener.onItemReStart();
                holder.btPause.setText("暂停");
            }
            holder.seekBar.setVisibility(View.VISIBLE);
            holder.tvCurrSpeed.setVisibility(View.VISIBLE);
            holder.tvTotalSize.setVisibility(View.VISIBLE);
            holder.btDownOrDel.setSelected(false);
            holder.btDownOrDel.setTextColor(Color.parseColor("#C0C1C2"));
            holder.btDownOrDel.setText("取消");
            holder.seekBar.setMax(data.get(position).getResSize());
            holder.seekBar.setProgress(data.get(position).getProgress());
            int totalSize=data.get(position).getResSize();
            int currSize=data.get(position).getProgress();
            holder.tvTotalSize.setText(currSize+"M/"+totalSize+"M");
        }else {
            //已下载
            holder.btPause.setVisibility(View.GONE);
            holder.seekBar.setVisibility(View.GONE);
            holder.tvCurrSpeed.setVisibility(View.GONE);
            holder.tvTotalSize.setVisibility(View.GONE);
            holder.btDownOrDel.setSelected(true);
            holder.btDownOrDel.setTextColor(Color.parseColor("#F00001"));
            holder.btDownOrDel.setText("删除");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.btPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map.get(position)){
                    holder.btPause.setText("暂停");
                    map.put(position,false);
                }else {
                    holder.btPause.setText("继续");
                    map.put(position,true);
                }

            }
        });
        holder.btDownOrDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int state=data.get(position).getResState();
                if (state==0){
                    //开始下载
                    if (!NetStateUtils.isNetworkConnected(context)){
                        Toast.makeText(context,"当前无网络连接，无法下载",Toast.LENGTH_SHORT).show();
                    }else {
                        if (!NetStateUtils.isWifi(context)){
                            Toast.makeText(context,"当前网络并非WIFI是否继续下载",Toast.LENGTH_SHORT).show();
                        }else {
                            data.get(position).setResState(1);
                            map.put(position,true);
                            notifyDataSetChanged();
                            listener.onItemDownClick(data.get(position),holder.seekBar,holder.tvTotalSize,holder.tvCurrSpeed,position);
                        }
                    }
                }else if (state==1){
                    //正在下载取消
                    listener.onItemCancel();
                }else {
                    //下载完成删除
                    listener.onItemDelete();
                }
            }
        });
    }
    public void updateResState(final int resId, final int state){
        Observable.create(new ObservableOnSubscribe<Void>() {
            @Override
            public void subscribe(ObservableEmitter<Void> e) throws Exception {
                dbHelper=DBHelper.getInstance(context);
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put("res_state",state);
                db.update("res_down",values,"res_id=?",new String[]{resId+""});

            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }

    public interface OnItemClickListener{
        void onItemDownClick(DownInfo downInfo,SeekBar seekBar,TextView tvTotal,TextView tvCurr,int pos);
        void onItemPause();
        void onItemDelete();
        void onItemCancel();
        void onItemReStart();
    }
    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivIcon;
        TextView tvName;
        TextView tvCurrSpeed;
        TextView tvTotalSize;
        SeekBar seekBar;
        Button btDownOrDel;
        Button btPause;
        public ViewHolder(View itemView) {
            super(itemView);
            ivIcon=itemView.findViewById(R.id.item_res_img);
            tvName=itemView.findViewById(R.id.item_res_name);
            tvCurrSpeed=itemView.findViewById(R.id.item_res_curr_speed);
            seekBar=itemView.findViewById(R.id.item_res_seek);
            btDownOrDel=itemView.findViewById(R.id.item_res_delordown);
            btPause=itemView.findViewById(R.id.item_res_pause);
            tvTotalSize=itemView.findViewById(R.id.item_res_total_speed);
        }
    }
}
