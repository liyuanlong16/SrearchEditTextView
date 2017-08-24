package jp.co.jmas.srearchedittextview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import jp.co.jmas.srearchedittextview.ICallBack;
import jp.co.jmas.srearchedittextview.SearchView;
import jp.co.jmas.srearchedittextview.bCallBack;

/**
 * Created by Carson_Ho on 17/8/11.
 * 回调函数就是在一个不确定实现的方法METHOD中用interface或者它的抽象方法留个口子，
 * 留给具体调用者（调用前边那个不确定的方法METHOD）在调用时提供具体实现来补上那个口子。
 * 从而达到更灵活地编码的目的，也大大减少了子类的使用
 *
 * 在A SearchView类中定义了一个方法(此处就是返回键的响应事件)
 * 这个方法中用到了一个接口bCallBack和该接口中的抽象方法void BackAciton(String str)
 * 但是抽象方法没有具体的实现， 需要B SearchDemo类去实现，SearchDemo类BackAciton(String str)的具体实现
 * B SearchDemo类实现该方法后，它本身不会去调用该方法，而是传递给ASearchView类，
 * 供A SearchView类去调用(也就是在返回的点击的响应事件中调用了该方法)这种机制就称为回调。
 */

public class SearchDemo extends AppCompatActivity implements bCallBack {

    // 1. 初始化搜索框变量
    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 2. 绑定视图
        setContentView(R.layout.activity_search);

        // 3. 绑定组件
        searchView = (SearchView) findViewById(R.id.search_view);

        // 4. 设置点击搜索按键后的操作（通过回调接口）
        // 参数 = 搜索框输入的内容
        searchView.setOnClickSearch(new ICallBack() {
            @Override
            public void SearchAciton(String string) {
                System.out.println("我收到了" + string);
            }
        });

        // 5. 设置点击返回按键后的操作（通过回调接口）
        searchView.setOnClickBack(this);
    }

    @Override
    public void BackAciton(String str) {
        Intent intent_back= new Intent ();
        intent_back.putExtra("backMessage",str);
        setResult(RESULT_OK,intent_back);
        finish();
    }
}