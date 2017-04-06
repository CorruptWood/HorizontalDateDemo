package com.example.zdm.horizontaldatedemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zdm.horizontaldatedemo.R;
import com.example.zdm.horizontaldatedemo.interf.OnRecyclerViewItemClickListener;
import com.example.zdm.horizontaldatedemo.utils.TimeUtils;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * ......................我佛慈悲....................
 * ......................_oo0oo_.....................
 * .....................o8888888o....................
 * .....................88" . "88....................
 * .....................(| -_- |)....................
 * .....................0\  =  /0....................
 * ...................___/`---'\___..................
 * ..................' \\|     |// '.................
 * ................./ \\|||  :  |||// \..............
 * .............../ _||||| -卍-|||||- \..............
 * ..............|   | \\\  -  /// |   |.............
 * ..............| \_|  ''\---/''  |_/ |.............
 * ..............\  .-\__  '-'  ___/-. /.............
 * ............___'. .'  /--.--\  `. .'___...........
 * .........."" '<  `.___\_<|>_/___.' >' ""..........
 * ........| | :  `- \`.;`\ _ /`;.`/ - ` : | |.......
 * ........\  \ `_.   \_ __\ /__ _/   .-` /  /.......
 * ....=====`-.____`.___ \_____/___.-`___.-'=====....
 * ......................`=---='.....................
 * <p>
 * ..................佛祖开光 ,永无BUG................
 * <p>
 * <p>
 * <p>
 * Created by zdm on 2017/4/6/0006.
 * <p>
 * 描述:
 */

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.ViewHolder> {

    private Context mContext;
    private List<String> dateList;
    private int selectPosition;
    private OnRecyclerViewItemClickListener listener;

    public DateAdapter(Context context) {
        this.mContext = context;
    }

    public void setOnItemListener(OnRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.date_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindViewData(dateList.get(position),position);
    }

    @Override
    public int getItemCount() {
        return dateList==null?0:dateList.size();
    }

    public void setDateList(List<String> dateList) {
        this.dateList=dateList;
        notifyDataSetChanged();
    }

    public void selectPosition(int position) {
        selectPosition=position;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @InjectView(R.id.week)
        TextView week;
        @InjectView(R.id.timer)
        TextView timer;
//        private View itemView;
        public ViewHolder(View itemView) {
            super(itemView);
            AutoUtils.auto(itemView);
            ButterKnife.inject(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(listener!=null){
                listener.OnItemClick(v,getAdapterPosition());
            }
        }

        public void bindViewData(String date,int position) {
            String[] split = date.split("-");
            timer.setText(split[1]+"."+split[2]);
            week.setText(TimeUtils.getWeekDay(date));

            if(selectPosition==position){
                itemView.setBackgroundResource(R.mipmap.course_bg);
            }else {
                itemView.setBackgroundColor(mContext.getResources().getColor(R.color.text_white));
            }
        }
    }
}
