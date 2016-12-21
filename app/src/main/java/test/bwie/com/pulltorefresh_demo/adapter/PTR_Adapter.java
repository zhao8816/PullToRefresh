package test.bwie.com.pulltorefresh_demo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import test.bwie.com.pulltorefresh_demo.R;
import test.bwie.com.pulltorefresh_demo.activity.MainActivity;
import test.bwie.com.pulltorefresh_demo.bean.Person;

/**
 * Author：ZhaoTieJin
 * date :  2016/12/21
 */

public class PTR_Adapter extends BaseAdapter {
    private Context context;
    private ArrayList<Person> listAll = new ArrayList<>();//定义一个基本的集合，存放外部传递过来的数据

    public PTR_Adapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return listAll.size();
    }

    @Override
    public Object getItem(int i) {
        return listAll.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = View.inflate(context, R.layout.item, null);
        }
        TextView tv_item = (TextView) view.findViewById(R.id.tv_item);
        tv_item.setText(listAll.get(i).getName());
        return view;
    }

    //给外部提供一个向本适配器传递数据的方法
    public void setData(ArrayList<Person> list) {
        //把外部传递过来的数据，存放到基本集合里
        this.listAll.addAll(list);
        //刷新适配器
        notifyDataSetChanged();
    }

    //下拉刷新
    public void freshData(ArrayList<Person> list) {
        //先清空，在添加新数据
        listAll.clear();
        listAll.addAll(list);
        //刷新适配器
        notifyDataSetChanged();
    }

    //上拉加载更多
    public void loadMoreData(ArrayList<Person> list) {
        //代码相同，调用setData()方法即可
        setData(list);
    }
}
