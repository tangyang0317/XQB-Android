package com.zhangju.xingquban.interestclassapp.ui.activity.near;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.Event;
import com.fastlib.app.FastActivity;
import com.youth.banner.Banner;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.NetWork;
import com.zhangju.xingquban.interestclassapp.adapter.near.CurriculumAdapter;
import com.zhangju.xingquban.interestclassapp.adapter.near.NaerKemuLeftAdapter;
import com.zhangju.xingquban.interestclassapp.adapter.near.NaerKemuRightAdapter;
import com.zhangju.xingquban.interestclassapp.adapter.near.NaerPaixuAdapter;
import com.zhangju.xingquban.interestclassapp.adapter.near.NaerQuyuLeftAdapter;
import com.zhangju.xingquban.interestclassapp.adapter.near.NaerQuyuRightAdapter;
import com.zhangju.xingquban.interestclassapp.bean.OrderRefresh;
import com.zhangju.xingquban.interestclassapp.bean.near.CurriculumBean;
import com.zhangju.xingquban.interestclassapp.bean.near.NearDistrictBean;
import com.zhangju.xingquban.interestclassapp.refactor.location.LocationManager;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.sjkc.CurriculumXqActivity;
import com.zhangju.xingquban.interestclassapp.util.DpUtil;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;
import com.zhangju.xingquban.interestclassapp.view.BannerHelper;
import com.zhangju.xingquban.refactoring.dblite.CategoryDao;
import com.zhangju.xingquban.refactoring.entity.CategoryBean;
import com.zhangju.xingquban.refactoring.view.AppBarStateChangeListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zgl on 2017/11/24.
 */

@ContentView(R.layout.act_curriculum)
public class CurriculumActivity extends FastActivity implements View.OnClickListener {
    public static final String TAG = "CurriculumActivity";
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.kemu)
    TextView kemu;
    @BindView(R.id.icon1)
    ImageView icon1;
    @BindView(R.id.ll_kemu)
    LinearLayout llKemu;
    @BindView(R.id.quyu)
    TextView quyu;
    @BindView(R.id.icon2)
    ImageView icon2;
    @BindView(R.id.ll_quyu)
    LinearLayout llQuyu;
    @BindView(R.id.shaixuan)
    TextView shaixuan;
    @BindView(R.id.icon3)
    ImageView icon3;
    @BindView(R.id.ll_shaixuan)
    LinearLayout llShaixuan;
    @BindView(R.id.curriculmAppBarLayout)
    AppBarLayout curriculmAppBarLayout;
    @BindView(R.id.near_recylerview)
    RecyclerView nearRecylerview;
    @BindView(R.id.swiperefreshlayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.home_banner)
    Banner mHomeBanner;
    private NearDistrictBean districtBean;
    private List<String> quyurightList = new ArrayList<>();
    private List<String> paixuList = new ArrayList<>();

    private boolean click2 = false;
    private boolean click3 = false;
    private boolean click4 = false;
    private View contentViewkemu;
    private View contentViewquyu;
    private View contentViewshaixuan;
    private PopupWindow popWindow2;
    private PopupWindow popWindow3;
    private PopupWindow popWindow4;
    private ListView mainlist;
    private ListView morelist;
    private GridView gridView;
    private LinearLayout dismiss;
    private LinearLayoutManager linearLayoutManager;
    private DisplayMetrics dm;
    private int screenHeight;

    private NaerKemuLeftAdapter naerKemuLeftAdapter;
    private NaerKemuRightAdapter naerKemuRightAdapter;
    private NaerQuyuLeftAdapter naerQuyuLeftAdapter;
    private NaerQuyuRightAdapter naerQuyuRightAdapter;
    private NaerPaixuAdapter naerPaixuAdapter;

    private Integer areasid = null;
    private Integer radius = null;
    private Integer kemuRightid = null;
    private int page = 0;
    private boolean load = true;
    private int statusBarHeight1 = 0;
    private JSONArray jsonArray = new JSONArray();
    private int pos4 = 0;
    private CurriculumBean mnearDataBean;
    private CurriculumAdapter curriculumAdapter;
    private int lastVisibleItem;
    private BannerHelper mBannerHelper;
    private int showAtLocationY = 0;
    private CategoryDao categoryDao;

    @Override
    protected void alreadyPrepared() {
        readyData();
        getCurriculumPulltorefrsh();
        initPopData();
        initListener();
        initBanner();
        //获取列表数据

    }

    private void initBanner() {
        mBannerHelper = new BannerHelper(mContext);
        mBannerHelper.init(mHomeBanner).loadBannerDate("13");
    }

    @Event
    private void refreshData(OrderRefresh refresh) {
        page = 0;
        load = true;
        swipeRefreshLayout.setRefreshing(true);
        getCurriculumData(kemuRightid, areasid, radius, jsonArray.toJSONString());

    }

    private void readyData() {
        areasid = Integer.valueOf(LocationManager.getInstance().getLocation().cityId);
        categoryDao = new CategoryDao(this);
        curriculumAdapter = new CurriculumAdapter(CurriculumActivity.this);
        swipeRefreshLayout.setRefreshing(true);
        linearLayoutManager = new LinearLayoutManager(CurriculumActivity.this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        nearRecylerview.setLayoutManager(linearLayoutManager);
        nearRecylerview.setAdapter(curriculumAdapter);
        getCurriculumData(kemuRightid, areasid, radius, jsonArray.toJSONString());
    }

    private void initPopData() {
        /*区域数据*/
        getNearDistrict();
        /*弹框数据*/
        initQuyuRightData();
        initPopView();
    }

    private void initPopView() {
        dm = new DisplayMetrics();
        CurriculumActivity.this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = getResources().getDimensionPixelSize(resourceId);
        }

        screenHeight = dm.heightPixels - DpUtil.dip2px(88) - statusBarHeight1;

        contentViewkemu = LayoutInflater.from(CurriculumActivity.this).inflate(R.layout.pop_down_kemu,
                null);
        popWindow2 = new PopupWindow(contentViewkemu, ViewGroup.LayoutParams.MATCH_PARENT,
                screenHeight);

        contentViewquyu = LayoutInflater.from(CurriculumActivity.this).inflate(R.layout.pop_down_kemu,
                null);
        popWindow3 = new PopupWindow(contentViewquyu, ViewGroup.LayoutParams.MATCH_PARENT,
                screenHeight);


        contentViewshaixuan = LayoutInflater.from(CurriculumActivity.this).inflate(R.layout.pop_down_leibie,
                null);
        popWindow4 = new PopupWindow(contentViewshaixuan, ViewGroup.LayoutParams.MATCH_PARENT,
                screenHeight);
    }

    //区域的地区名数据
    private Observer<NearDistrictBean> observerNearDistrict = new Observer<NearDistrictBean>() {

        @Override
        public void onCompleted() {
            Log.e(TAG, "======onCompleted=======: ");
        }

        @Override
        public void onError(Throwable e) {
            ToastUtil.makeText(CurriculumActivity.this, e.getMessage(), ToastUtil.LENGTH_LONG).show();
        }

        @Override
        public void onNext(NearDistrictBean nearDistrictBean) {
            districtBean = nearDistrictBean;
        }
    };

    //区域数据
    public void getNearDistrict() {
        NetWork.getNearDistrict().getNearDistrict(LocationManager.getInstance().getLocation().cityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observerNearDistrict);
    }

    private void initQuyuRightData() {
        quyurightList.add("全城");
        quyurightList.add("1km");
        quyurightList.add("3km");
        quyurightList.add("5km");
        quyurightList.add("7km");


        paixuList.add("智能排序");
        paixuList.add("据我最近");
        paixuList.add("价格最低");
        paixuList.add("价格最高");
        paixuList.add("人气最高");
        paixuList.add("评分最高");

    }

    //获取默认数据
    private Observer<CurriculumBean> observer = new Observer<CurriculumBean>() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
        }

        @Override
        public void onNext(CurriculumBean curriculumBean) {
            if (load) {
                nearRecylerview.setAdapter(curriculumAdapter);
                mnearDataBean = curriculumBean;
                curriculumAdapter.addData(mnearDataBean);
                load = false;
                swipeRefreshLayout.setRefreshing(false);
            } else {
                mnearDataBean.getAaData().addAll(curriculumBean.getAaData());
                curriculumAdapter.notifyDataSetChanged();
            }

            curriculumAdapter.setOnItemClickListener(new CurriculumAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    String lessonId = curriculumAdapter.getData().getAaData().get(position).getId();
                    CurriculumXqActivity.lanuchActivity(CurriculumActivity.this, lessonId);
                }
            });
        }
    };

    private void getCurriculumData(Integer kemuid, Integer areasId, Integer radius, String sort) {
        if (sort.length() <= 2) {
            sort = null;
        }
        NetWork.getNear().getCurriculumData(null, LocationManager.getInstance().getLocation().longitude, LocationManager.getInstance().getLocation().latitude,
                page, "20", kemuid, areasId, radius, sort)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    private void getCurriculumPulltorefrsh() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                load = true;
                page = 0;
                getCurriculumData(kemuRightid, areasid, radius, jsonArray.toJSONString());
            }
        });

    }

    private void initListener() {
        curriculmAppBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.EXPANDED) {
                    //展开状态
                    showAtLocationY = DpUtil.dip2px(199) + statusBarHeight1;
                } else if (state == State.COLLAPSED) {
                    //折叠状态
                    showAtLocationY = DpUtil.dip2px(89) + statusBarHeight1;
                } else {
                    //中间状态
                }
            }
        });
        nearRecylerview.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (curriculumAdapter != null) {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == curriculumAdapter.getItemCount()) {
                        page++;
                        getCurriculumData(kemuRightid, areasid, radius, jsonArray.toJSONString());
                    }
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            }
        });
    }

    @OnClick({R.id.ll_kemu, R.id.ll_quyu, R.id.ll_shaixuan, R.id.back})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_kemu:
                if (!click2) {
                    downPopwindow2();
                    popWindow3.dismiss();
                    popWindow4.dismiss();
                    click2 = true;
                } else {
                    popWindow2.dismiss();
                }
                break;
            case R.id.ll_quyu:
                if (!click3) {
                    downPopwindow3();

                    popWindow2.dismiss();
                    popWindow4.dismiss();
                    click3 = true;
                } else {
                    popWindow3.dismiss();
                }
                break;
            case R.id.ll_shaixuan:
                if (!click4) {
                    downPopwindow4();

                    popWindow2.dismiss();
                    popWindow3.dismiss();
                    click4 = true;
                } else {
                    popWindow4.dismiss();
                }
                break;
            case R.id.back:
                CurriculumActivity.this.finish();
                break;
        }
    }

    private void downPopwindow2() {
        popWindow2.setFocusable(false);// 是否具有获取焦点的能力
        popWindow2.setBackgroundDrawable(new BitmapDrawable());
        popWindow2.setOutsideTouchable(false);
        mainlist = (ListView) contentViewkemu.findViewById(R.id.classify_mainlist);
        morelist = (ListView) contentViewkemu.findViewById(R.id.classify_morelist);
        dismiss = (LinearLayout) contentViewkemu.findViewById(R.id.djqx);
        popWindow2.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                kemu.setTextColor(CurriculumActivity.this.getResources().getColor(R.color.mainTextColor));
                click2 = false;
            }
        });

        List<CategoryBean> categoryBeanList = categoryDao.queryLevelOneAll();
        naerKemuLeftAdapter = new NaerKemuLeftAdapter(CurriculumActivity.this, categoryBeanList);
        mainlist.setAdapter(naerKemuLeftAdapter);
        naerKemuLeftAdapter.selectnum(0);
        mainlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CategoryBean categoryBean = (CategoryBean) parent.getItemAtPosition(position);
                initMoreAdapter(categoryBean.getId());
                naerKemuLeftAdapter.selectnum(position);
                naerKemuLeftAdapter.notifyDataSetChanged();
            }
        });
        mainlist.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        /*  mainlist.setSelection(0);*/
        initMoreAdapter(0);
        morelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CategoryBean categoryBean = (CategoryBean) parent.getItemAtPosition(position);
                kemuRightid = categoryBean.getId();
                page = 0;
                load = true;
                naerKemuRightAdapter.selectnum(position);
                swipeRefreshLayout.setRefreshing(true);
                getCurriculumData(kemuRightid, areasid, radius, jsonArray.toJSONString());
                kemu.setText(categoryBean.getName());
                popWindow2.dismiss();
            }
        });
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindow2.dismiss();
            }
        });
        popWindow2.showAtLocation(llKemu, Gravity.NO_GRAVITY, 0, showAtLocationY);
        kemu.setTextColor(CurriculumActivity.this.getResources().getColor(R.color.color_main));
        /*  icon.setBackground(getContext().getResources().getDrawable(R.drawable.shaixuan));*/
    }

    private void initMoreAdapter(int levelOneId) {
        List<CategoryBean> levelTwoList = categoryDao.queryLevelTowAll(levelOneId);
        if (levelTwoList != null && levelTwoList.size() > 0) {
            naerKemuRightAdapter = new NaerKemuRightAdapter(CurriculumActivity.this, levelTwoList);
            morelist.setAdapter(naerKemuRightAdapter);
            naerKemuRightAdapter.notifyDataSetChanged();
        }
    }

    private void downPopwindow3() {
        popWindow3.setFocusable(false);// 是否具有获取焦点的能力
        // 点击PopupWindow以外的区域，PopupWindow是否会消失。
        popWindow3.setBackgroundDrawable(new BitmapDrawable());
        popWindow3.setOutsideTouchable(false);
        mainlist = (ListView) contentViewquyu.findViewById(R.id.classify_mainlist);
        morelist = (ListView) contentViewquyu.findViewById(R.id.classify_morelist);
        dismiss = (LinearLayout) contentViewquyu.findViewById(R.id.djqx);
        popWindow3.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                quyu.setTextColor(CurriculumActivity.this.getResources().getColor(R.color.mainTextColor));
                click3 = false;
            }
        });

        naerQuyuLeftAdapter = new NaerQuyuLeftAdapter(CurriculumActivity.this, districtBean);
        mainlist.setAdapter(naerQuyuLeftAdapter);
        naerQuyuLeftAdapter.selectnum(0);
        mainlist.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        initMoreAdapterQuyu();
        mainlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    initMoreAdapterQuyu();
                } else {
                    areasid = Integer.valueOf(districtBean.getAaData().get(position - 1).getId());
                    page = 0;
                    load = true;
                    quyu.setText(districtBean.getAaData().get(position - 1).getName());
                    swipeRefreshLayout.setRefreshing(true);
                    getCurriculumData(kemuRightid, areasid, radius, jsonArray.toJSONString());
                    popWindow3.dismiss();
                }
            }
        });
        morelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    areasid = null;
                    radius = null;
                } else {
                    radius = position * 1000;
                }
                page = 0;
                load = true;
                swipeRefreshLayout.setRefreshing(true);
                getCurriculumData(kemuRightid, areasid, radius, jsonArray.toJSONString());
                quyu.setText(quyurightList.get(position));
                popWindow3.dismiss();
            }
        });
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindow3.dismiss();
            }
        });
        popWindow3.showAtLocation(llKemu, Gravity.NO_GRAVITY, 0, showAtLocationY);
        quyu.setTextColor(CurriculumActivity.this.getResources().getColor(R.color.color_main));
    }

    private void initMoreAdapterQuyu() {
        naerQuyuRightAdapter = new NaerQuyuRightAdapter(CurriculumActivity.this, quyurightList);
        morelist.setAdapter(naerQuyuRightAdapter);
        naerQuyuRightAdapter.notifyDataSetChanged();
    }

    private void downPopwindow4() {
        popWindow4.setClippingEnabled(true);
        popWindow4.setFocusable(false);// 是否具有获取焦点的能力
        // 点击PopupWindow以外的区域，PopupWindow是否会消失。
        popWindow4.setBackgroundDrawable(new BitmapDrawable());
        popWindow4.setOutsideTouchable(false);

        gridView = (GridView) contentViewshaixuan.findViewById(R.id.gridview_liebie);
        dismiss = (LinearLayout) contentViewshaixuan.findViewById(R.id.dismiss);
        naerPaixuAdapter = new NaerPaixuAdapter(CurriculumActivity.this, paixuList);

        popWindow4.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                shaixuan.setTextColor(CurriculumActivity.this.getResources().getColor(R.color.mainTextColor));
                click4 = false;
            }
        });
        gridView.setAdapter(naerPaixuAdapter);
        naerPaixuAdapter.selectnum(pos4);
        /*   naerPaixuAdapter.selectnum(pos4);*/

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                naerPaixuAdapter.selectnum(position);
                load = true;
                page = 0;
                pos4 = position;
                sort(position);
                swipeRefreshLayout.setRefreshing(true);
                getCurriculumData(kemuRightid, areasid, radius, jsonArray.toJSONString());
                String s = paixuList.get(position);
                shaixuan.setText(s);
                popWindow4.dismiss();
            }
        });
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindow4.dismiss();
            }
        });
        popWindow4.showAtLocation(llKemu, Gravity.NO_GRAVITY, 0, showAtLocationY);
        shaixuan.setTextColor(CurriculumActivity.this.getResources().getColor(R.color.color_main));
        //工作经历  2017年6月毕业 进入浙江同花顺网络科技有限公司手机开发部
    }

    private void sort(int sortpos) {
        JSONObject jo1 = new JSONObject(true);
        switch (sortpos) {
            case 0:
                jsonArray.clear();
                jo1.fluentPut("field", "auto");
                jo1.fluentPut("isAsc", Boolean.toString(true));
                jsonArray.add(jo1);
                break;
            case 1:
                jsonArray.clear();
                jo1.fluentPut("field", "gis");
                jo1.fluentPut("isAsc", Boolean.toString(true));
                jsonArray.add(jo1);
                break;
            case 2:
                jsonArray.clear();
                jo1.fluentPut("field", "vipPrice");
                jo1.fluentPut("isAsc", Boolean.toString(true));
                jsonArray.add(jo1);
                break;
            case 3:
                jsonArray.clear();
                jo1.fluentPut("field", "vipPrice");
                jo1.fluentPut("isAsc", Boolean.toString(false));
                jsonArray.add(jo1);
                break;
            case 4:
                jsonArray.clear();
                jo1.fluentPut("field", "collectionCount");
                jo1.fluentPut("isAsc", Boolean.toString(true));
                jsonArray.add(jo1);
                break;
            case 5:
                jsonArray.clear();
                jo1.fluentPut("field", "score");
                jo1.fluentPut("isAsc", Boolean.toString(true));
                jsonArray.add(jo1);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBannerHelper.stopBanner();
    }
}
