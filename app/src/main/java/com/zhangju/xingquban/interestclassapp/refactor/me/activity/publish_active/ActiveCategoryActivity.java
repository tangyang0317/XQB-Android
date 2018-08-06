package com.zhangju.xingquban.interestclassapp.refactor.me.activity.publish_active;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.utils.DensityUtils;
import com.fastlib.utils.N;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseActiveCategory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sgfb on 2017/11/2.
 * 选择活动类别
 */
public class ActiveCategoryActivity extends AppCompatActivity{
    public static final String RES_STR_CATEGORY_NAME ="categoryName";
    public static final String RES_STR_CATEGORY_ID ="categoryId";
    private LinearLayout mContentView;
    private TitleBar mTitleBar;
    private ListView mList;
    private ArrayAdapter<String> mAdapter;
    private Map<String,String> mNameToId =new HashMap<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContentView=new LinearLayout(this);

        mContentView.setOrientation(LinearLayout.VERTICAL);
        mContentView.addView(mTitleBar=new TitleBar(this));
        mContentView.addView(mList=new ListView(this));
        mContentView.setBackgroundColor(Color.WHITE);
        setContentView(mContentView);
        init();
    }

    private void init(){
        mList.setAdapter(mAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1));
        mTitleBar.getLeftIcon().setImageResource(R.drawable.home_city_back);
        mTitleBar.getTitle().setText("选择类别");
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTitleBar.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DensityUtils.dp2px(this,48)));
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String categoryName=mAdapter.getItem(position);
                String categoryId= mNameToId.get(categoryName);
                Intent intent=new Intent();
                intent.putExtra(RES_STR_CATEGORY_ID,categoryId);
                intent.putExtra(RES_STR_CATEGORY_NAME,categoryName);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        requestActiveCategory();
    }

    /**
     * 请求接口活动列表
     */
    private void requestActiveCategory(){
        Request request=Request.obtain("get", MeInterface.GET_CATEGORY_LIST);
        request.setListener(new SimpleListener<Response<List<ResponseActiveCategory>>>(){

            @Override
            public void onResponseListener(Request r, Response<List<ResponseActiveCategory>> result){
                if(result.success){
                    if(result.data==null||result.data.isEmpty()){
                        System.out.println("种类列表为空");
                        return;
                    }
                    for(ResponseActiveCategory activeCategory:result.data){
                        mNameToId.put(activeCategory.name,activeCategory.id);
                        mAdapter.add(activeCategory.name);
                    }
                }
                else N.showShort(ActiveCategoryActivity.this,"获取活动列表失败");
            }
        });
        request.start();
    }
}
