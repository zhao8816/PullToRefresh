package test.bwie.com.pulltorefresh_demo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import test.bwie.com.pulltorefresh_demo.R;
import test.bwie.com.pulltorefresh_demo.bean.Person_2;

/**
 * Author：ZhaoTieJin
 * date :  2016/12/21
 */

public class PTR_Adapter_2 extends BaseAdapter {
    private Context context;
    private ArrayList<Person_2> listAll = new ArrayList<>();//定义一个基本的集合，存放外部传递过来的数据
    private ViewHolder viewHolder;

    public PTR_Adapter_2(Context context) {
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            //实例化ViewHolder
            viewHolder = new ViewHolder();
            view = View.inflate(context, R.layout.item_2, null);
            //获取条目控件，存放到ViewHolder中
            viewHolder.tv = (TextView) view.findViewById(R.id.tv_item_2);
            viewHolder.cb = (CheckBox) view.findViewById(R.id.cb_item);
            view.setTag(viewHolder);
        } else {
            //如果view不为空，就直接取出ViewHolder中储存的控件
            viewHolder = (ViewHolder) view.getTag();
        }

        //设置控件显示内容
        viewHolder.tv.setText(listAll.get(i).getName());

        //解决CheckBox复用问题，被勾选的，状态设为true，没有勾选的设置为false
        viewHolder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewHolder.cb.isChecked()) {
                    listAll.get(i).setState(true);
                } else {
                    listAll.get(i).setState(false);
                }
            }
        });

        viewHolder.cb.setChecked(listAll.get(i).getState());
        return view;
    }

    //给外部提供一个向本适配器传递数据的方法
    public void setData(ArrayList<Person_2> list) {
        //把外部传递过来的数据，存放到基本集合里
        this.listAll.addAll(list);
        //刷新适配器
        notifyDataSetChanged();
    }

    //下拉刷新
    public void freshData(ArrayList<Person_2> list) {
        //先清空，在添加新数据
        listAll.clear();
        listAll.addAll(list);
        //刷新适配器
        notifyDataSetChanged();
    }

    //上拉加载更多
    public void loadMoreData(ArrayList<Person_2> list) {
        //代码相同，调用setData()方法即可
        setData(list);
    }

    //创建ViewHolder
    class ViewHolder {
        public TextView tv;
        public CheckBox cb;
    }
}
