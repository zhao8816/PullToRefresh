# PullToRefresh
实现上拉刷新，下来加载更多

效果一： MainActivity -- PTR_Adapter -- Person -- item ：： 主要展示上拉刷新，下拉加载更多；

效果而： SecondActivity -- PTR_Adapter_2 -- Person_2 -- item_2 ::包括了“效果一”，
        还在适配器的getView()方法中添加了ViewHolder,（练习ViewHolder的使用），
        同时还在条目中添加了CheckBox，并解决了它的条目复用问题。
