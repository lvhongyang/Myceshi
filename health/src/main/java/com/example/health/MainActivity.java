package com.example.health;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements XListView.IXListViewListener {

    private XListView myxlist;
    private List<String> list = new ArrayList<>();
    private Myadapter adapter = new Myadapter();

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //刷新Xlistview
            adapter.notifyDataSetChanged();
            //隐藏头部尾部
            closeXlist();
        }


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myxlist = (XListView) findViewById(R.id.myxlist);


        for (int i = 0; i < 30; i++) {
            list.add("我是真的不会敲第 " + i + " 行代码！");
        }


        myxlist.setPullRefreshEnable(true);

        myxlist.setPullLoadEnable(true);
        //设置上拉下拉接口


        myxlist.setAdapter(adapter);

        myxlist.setXListViewListener(this);


    }

    @Override
    public void onRefresh() {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                list.add(0, "给你一个超大的么么哒。");
                handler.sendEmptyMessage(0);
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    list.add("给你" + i + 1 + "个劳斯莱斯。");
                }
                handler.sendEmptyMessage(0);
            }
        }, 2000);
    }

    private void closeXlist() {
        //停止加载更多
        myxlist.stopLoadMore();
        //停止刷新
        myxlist.stopRefresh();
        //更新时间
        myxlist.setRefreshTime(time());

    }


    public String time() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);
        return time;
    }


    class Myadapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(MainActivity.this, R.layout.item, null);
            TextView mytx = (TextView) convertView.findViewById(R.id.mytx);
            ImageView myimg = (ImageView) convertView.findViewById(R.id.myimage);
            mytx.setText(list.get(position));

            return convertView;
        }
    }
}
