package test.bwie.com.pulltorefresh_demo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

import test.bwie.com.pulltorefresh_demo.R;
import test.bwie.com.pulltorefresh_demo.adapter.PTR_Adapter_2;
import test.bwie.com.pulltorefresh_demo.bean.Person_2;

/**
 * Author：ZhaoTieJin
 * date :  2016/12/21
 */

public class SecondActivity extends Activity implements PullToRefreshBase.OnRefreshListener2 {

    private PullToRefreshListView ptr_refresh_2;
    private PTR_Adapter_2 ptr_adapter_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //初始化界面
        initView();

        //给设置适配器
        ptr_adapter_2 = new PTR_Adapter_2(SecondActivity.this);
        ptr_refresh_2.setAdapter(ptr_adapter_2);

        //初始化数据
        initData();

        //设置拖拽监听事件
        ptr_refresh_2.setOnRefreshListener(this);

    }

    //初始化数据
    private void initData() {
        //模拟数据
        ArrayList<Person_2> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new Person_2("麦迪" + i,false));
        }
        ptr_adapter_2.setData(list);
    }

    //初始化界面
    private void initView() {
        //连接到控件
        ptr_refresh_2 = (PullToRefreshListView) findViewById(R.id.ptr_refresh_2);
        //设置PullToRefreshListView的模式
        ptr_refresh_2.setMode(PullToRefreshBase.Mode.BOTH);
    }

    //拖拽监听事件的方法--下拉刷新
    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {

        //模拟延时刷新
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(2000);
                    handler.sendEmptyMessage(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                }
            }
        }.start();
    }

    //拖拽监听事件的方法--上拉加载更多
    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {

        //模拟延时刷新
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(2000);
                    handler.sendEmptyMessage(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                }
            }
        }.start();
    }

    //创建Handler更新UI
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    //刷新数据
                    pullRefresh();
                    break;
                case 2:
                    //上拉加载更多
                    pullLoadMore();
                    break;
                default:
                    break;
            }
            //关闭刷新
            ptr_refresh_2.onRefreshComplete();
        }
    };

    //模拟数据，下拉刷新
    public void pullRefresh() {
        //模拟数据
        ArrayList<Person_2> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new Person_2("卡特" + i,false));
        }
        //调用适配器里刷新数据的方法，把新数据传递过去
        ptr_adapter_2.freshData(list);
    }

    //模拟数据，上拉加载更多
    public void pullLoadMore() {
        //模拟数据
        ArrayList<Person_2> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new Person_2("科比" + i,false));
        }
        //调用适配器里加载更多数据的方法，把新数据传递过去
        ptr_adapter_2.loadMoreData(list);
    }
}
