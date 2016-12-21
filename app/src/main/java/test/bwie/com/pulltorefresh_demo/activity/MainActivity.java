package test.bwie.com.pulltorefresh_demo.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

import test.bwie.com.pulltorefresh_demo.R;
import test.bwie.com.pulltorefresh_demo.adapter.PTR_Adapter;
import test.bwie.com.pulltorefresh_demo.bean.Person;

public class MainActivity extends AppCompatActivity implements PullToRefreshBase.OnRefreshListener2, View.OnClickListener {

    private PullToRefreshListView ptr_refresh;
    private PTR_Adapter ptr_adapter;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化界面
        initView();

        //给设置适配器
        ptr_adapter = new PTR_Adapter(MainActivity.this);
        ptr_refresh.setAdapter(ptr_adapter);

        //初始化数据
        initData();

        //设置拖拽监听事件
        ptr_refresh.setOnRefreshListener(this);

        //button按钮设置点击事件
        button.setOnClickListener(this);
    }

    //初始化数据
    private void initData() {
        //模拟数据
        ArrayList<Person> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new Person("麦迪" + i));
        }
        ptr_adapter.setData(list);
    }

    //初始化界面
    private void initView() {
        //连接到控件
        ptr_refresh = (PullToRefreshListView) findViewById(R.id.ptr_refresh);
        button = (Button) findViewById(R.id.button);
        //设置PullToRefreshListView的模式
        ptr_refresh.setMode(PullToRefreshBase.Mode.BOTH);
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
            ptr_refresh.onRefreshComplete();
        }
    };

    //模拟数据，下拉刷新
    public void pullRefresh() {
        //模拟数据
        ArrayList<Person> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new Person("卡特" + i));
        }
        //调用适配器里刷新数据的方法，把新数据传递过去
        ptr_adapter.freshData(list);
    }

    //模拟数据，上拉加载更多
    public void pullLoadMore() {
        //模拟数据
        ArrayList<Person> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new Person("科比" + i));
        }
        //调用适配器里加载更多数据的方法，把新数据传递过去
        ptr_adapter.loadMoreData(list);
    }

    //点击按钮跳转至下一页
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }
}
