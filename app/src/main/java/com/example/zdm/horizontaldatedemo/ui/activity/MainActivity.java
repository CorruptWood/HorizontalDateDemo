package com.example.zdm.horizontaldatedemo.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zdm.horizontaldatedemo.R;
import com.example.zdm.horizontaldatedemo.adapter.DateAdapter;
import com.example.zdm.horizontaldatedemo.interf.OnRecyclerViewItemClickListener;
import com.example.zdm.horizontaldatedemo.utils.TimeUtils;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AutoLayoutActivity implements GestureDetector.OnGestureListener {

    @InjectView(R.id.left)
    ImageView left;
    @InjectView(R.id.timer)
    TextView timer;
    @InjectView(R.id.right)
    ImageView right;
    @InjectView(R.id.recyler_view)
    RecyclerView mRecyler;

    private int tmpe_year;//临时的年
    private int tmpe_month;//临时的月

    GestureDetector gestureDetector = new GestureDetector(this);
    private LinearLayoutManager manager;
    private DateAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        init();
    }

    private void init() {
        initData();
        initRecycler();
        initTime();
    }

    private void initTime() {
        String sysDate = TimeUtils.getToDay();
        for (int x = 0; x < dateList.size(); x++) {
            if (sysDate.equals(dateList.get(x))) {
                manager.scrollToPosition(x - 3);
                mAdapter.selectPosition(x);
                break;
            }
        }
        String sys_year = sysDate.split("-")[0];
        String sys_month = sysDate.split("-")[1];

        //只变化临时的时间变量
        tmpe_year = Integer.parseInt(sys_year);
        tmpe_month = Integer.parseInt(sys_month);

        //设置时间
        setTimer(tmpe_year, tmpe_month);
    }

    private void setTimer(int year, int month) {
        timer.setText(year + "年" + (month < 10 ? "0" + month : month) + "月");
    }

    List<String> dateList = new ArrayList<>();

    private void initData() {
        String toDay = TimeUtils.getToDay();
        dateList.add(toDay);
        getDateAfter(toDay);
        getDateBefore(toDay);
    }

    //获取十天以后的日期
    private void getDateAfter(String day) {
        for (int x = 1; x < 10; x++) {
            dateList.add(TimeUtils.getDateAfter(day, x));
        }
    }

    //获取十天以前的日期
    private void getDateBefore(String day) {
        for (int x = 1; x < 10; x++) {
            dateList.add(0, TimeUtils.getDateBefore(day, x));
        }
    }

    private void initRecycler() {
        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyler.setLayoutManager(manager);

        mAdapter = new DateAdapter(this);

        //创建view池
        RecyclerView.RecycledViewPool pool = new RecyclerView.RecycledViewPool();
        pool.setMaxRecycledViews(0, 20);
        mRecyler.setRecycledViewPool(pool);

        mRecyler.setAdapter(mAdapter);

        mAdapter.setDateList(dateList);

        //将滑动首饰交给gestureDetector更加准确的添加数据
        mRecyler.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });

        //移除更新动画
        ((SimpleItemAnimator) mRecyler.getItemAnimator()).setSupportsChangeAnimations(false);
        mAdapter.setOnItemListener(new OnRecyclerViewItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                mAdapter.selectPosition(position);
                String date = dateList.get(position);
                setTimer(Integer.parseInt(date.split("-")[0]), Integer.parseInt(date.split("-")[1]));
            }
        });

    }


    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        int firstVisibleItemPosition = manager.findFirstCompletelyVisibleItemPosition();
        int lastVisibleItemPosition = manager.findLastCompletelyVisibleItemPosition();
        if (firstVisibleItemPosition == 0) {
            getDateBefore(dateList.get(firstVisibleItemPosition));
            mAdapter.setDateList(dateList);
            //滑动到指定位置
            manager.scrollToPosition(9);
        } else if (lastVisibleItemPosition == dateList.size() - 1) {
            getDateAfter(dateList.get(lastVisibleItemPosition));
            mAdapter.setDateList(dateList);
        }
        return false;
    }

    @OnClick({R.id.left, R.id.right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left:
                if (tmpe_month == 1) {
                    tmpe_year--;
                    tmpe_month = 12;
                } else
                    tmpe_month--;
                break;
            case R.id.right:
                if (tmpe_month == 12) {
                    tmpe_year++;
                    tmpe_month = 1;
                } else {
                    tmpe_month++;
                }
                break;
        }
        setTimer(tmpe_year, tmpe_month);
        dateList.clear();
        String date = tmpe_year + "-" + tmpe_month + "-" + "01";
        dateList.add(date);
        getDateAfter(date);
        getDateBefore(date);
        mAdapter.setDateList(dateList);

        for (int x = 0; x < dateList.size(); x++) {
            if (date.equals(dateList.get(x))) {
                manager.scrollToPosition(x);
                mAdapter.selectPosition(x);
                break;
            }
        }
    }
}
