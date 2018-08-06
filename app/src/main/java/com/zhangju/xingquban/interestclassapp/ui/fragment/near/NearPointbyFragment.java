package com.zhangju.xingquban.interestclassapp.ui.fragment.near;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
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
import com.fastlib.utils.Utils;
import com.youth.banner.Banner;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.NetWork;
import com.zhangju.xingquban.interestclassapp.adapter.NearRecylerAdapter;
import com.zhangju.xingquban.interestclassapp.adapter.near.NaerKemuLeftAdapter;
import com.zhangju.xingquban.interestclassapp.adapter.near.NaerKemuRightAdapter;
import com.zhangju.xingquban.interestclassapp.adapter.near.NaerLeibieAdapter;
import com.zhangju.xingquban.interestclassapp.adapter.near.NaerPaixuAdapter;
import com.zhangju.xingquban.interestclassapp.adapter.near.NaerQuyuLeftAdapter;
import com.zhangju.xingquban.interestclassapp.adapter.near.NaerQuyuRightAdapter;
import com.zhangju.xingquban.interestclassapp.base.BaseFragment;
import com.zhangju.xingquban.interestclassapp.bean.NearDataBean;
import com.zhangju.xingquban.interestclassapp.bean.NearSubjectBean;
import com.zhangju.xingquban.interestclassapp.bean.near.NearDistrictBean;
import com.zhangju.xingquban.interestclassapp.refactor.location.Location;
import com.zhangju.xingquban.interestclassapp.refactor.location.LocationManager;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.HomeRecyclerViewData;
import com.zhangju.xingquban.interestclassapp.util.DpUtil;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;
import com.zhangju.xingquban.interestclassapp.view.BannerHelper;
import com.zhangju.xingquban.refactoring.view.AppBarStateChangeListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by dhy on 2017/6/14.
 */
//附近模块
public class NearPointbyFragment extends BaseFragment {
    public static final String TAG = "NearbyFragment";
    public static final String PAGE_INDEX = "1";
    private int showAtLocationY = 0;

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.mytitle)
    SearchView mytitle;
    @BindView(R.id.nearSearchAppBarLayout)
    AppBarLayout nearSearchAppBarLayout;
    @BindView(R.id.near_recylerview)
    RecyclerView nearRecylerview;
    Unbinder unbinder;
    @BindView(R.id.swiperefreshlayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.leibie)
    TextView leibie;
    @BindView(R.id.icon)
    ImageView icon;
    @BindView(R.id.ll_leibie)
    LinearLayout llLeibie;
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
    @BindView(R.id.home_banner)
    Banner mHomeBanner;

    private int page = 0;

    private boolean load = true;

    private NearRecylerAdapter nearRecylerAdapter;
    private Context context;
    private List<NearDataBean.AaDataBean> list = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private NearDistrictBean districtBean;
    private NearSubjectBean nearSubjectBean;
    //弹框设置
    private boolean click1 = false;
    private boolean click2 = false;
    private boolean click3 = false;
    private boolean click4 = false;
    /*记录上次选择位置*/
    private int pos1 = 0;
    private int pos2 = 0;
    private int pos3 = 0;
    private int pos4 = 0;
    private int statusBarHeight1 = 0;
    private PopupWindow popWindow1;
    private PopupWindow popWindow2;
    private PopupWindow popWindow3;
    private PopupWindow popWindow4;
    private ListView mainlist;
    private ListView morelist;
    private GridView gridView;
    private LinearLayout dismiss;
    private List<String> liexinglist = new ArrayList<>();
    private NaerLeibieAdapter naerLeibieAdapter;
    private NaerKemuLeftAdapter naerKemuLeftAdapter;
    private NaerKemuRightAdapter naerKemuRightAdapter;
    private NaerQuyuLeftAdapter naerQuyuLeftAdapter;
    private NaerQuyuRightAdapter naerQuyuRightAdapter;
    private NaerPaixuAdapter naerPaixuAdapter;
    private DisplayMetrics dm;
    private List<String> quyurightList = new ArrayList<>();
    private List<String> paixuList = new ArrayList<>();
    private Map<String, String> paixumap = new HashMap<>();
    private String degreeId = null;
    private Integer areasid = null;
    private Integer radius = null;
    private Integer kemuRightid = null;
    private String allStringQuery = null;
    private View contentViewliebie;
    private View contentViewkemu;
    private View contentViewquyu;
    private View contentViewshaixuan;
    private int screenHeight;
    private JSONArray jsonArray = new JSONArray();
    private NearSubjectBean.AaDataBean dataBean;
    private Boolean avgScore = null;
    private NearDataBean mnearDataBean;
    private int lastVisibleItem;
    private BannerHelper mBannerHelper;

    @Override
    public void initView() {
        dm = new DisplayMetrics();

        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = getResources().getDimensionPixelSize(resourceId);
        }

        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));

        screenHeight = dm.heightPixels - DpUtil.dip2px(88) - statusBarHeight1;

        contentViewliebie = LayoutInflater.from(getContext()).inflate(R.layout.pop_down_leibie,
                null);
        popWindow1 = new PopupWindow(contentViewliebie, ViewGroup.LayoutParams.MATCH_PARENT,
                screenHeight);

        contentViewkemu = LayoutInflater.from(getContext()).inflate(R.layout.pop_down_leibie,
                null);
        popWindow2 = new PopupWindow(contentViewkemu, ViewGroup.LayoutParams.MATCH_PARENT,
                screenHeight);

        contentViewquyu = LayoutInflater.from(getContext()).inflate(R.layout.pop_down_kemu,
                null);
        popWindow3 = new PopupWindow(contentViewquyu, ViewGroup.LayoutParams.MATCH_PARENT,
                screenHeight);


        contentViewshaixuan = LayoutInflater.from(getContext()).inflate(R.layout.pop_down_leibie,
                null);
        popWindow4 = new PopupWindow(contentViewshaixuan, ViewGroup.LayoutParams.MATCH_PARENT,
                screenHeight);

        mytitle.setIconifiedByDefault(false);
        mytitle.setQueryHint("搜索老师/机构");
        mytitle.setFocusable(false);
        if (mytitle == null) {
            return;
        } else {
            SearchView.SearchAutoComplete textView = (SearchView.SearchAutoComplete) mytitle.findViewById(R.id.search_src_text);
            textView.setTextSize(13);
        }

    }

    private void initBanner() {
        mBannerHelper = new BannerHelper(getActivity());
        String name = dataBean.getName();

        mBannerHelper.init(mHomeBanner).setSubject(dataBean.getId() + "").loadBannerDate("4");
    }

    @Override
    public void initData() {

        liexinglist.add("全部类型");
        liexinglist.add("老师");
        liexinglist.add("机构");
        /*     getNearData(null,null,null,radius,null);*/
        /*获取科目数据*/
        getNearSubject();
        /*区域数据*/
        getNearDistrict();
        /*弹框数据*/
        initQuyuRightData();
        //获取项目数据
        setNearPulltorefrsh();


    }

    private void setTextforBundle() {
        if (dataBean != null) {
            kemu.setText(dataBean.getName());
        }
        areasid = Integer.valueOf(LocationManager.getInstance().getLocation().cityId);
        nearRecylerAdapter = new NearRecylerAdapter(getActivity());
        swipeRefreshLayout.setRefreshing(true);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        nearRecylerview.setLayoutManager(linearLayoutManager);
        nearRecylerview.setAdapter(nearRecylerAdapter);
        getNearData(degreeId, kemuRightid, areasid, radius, jsonArray.toJSONString(), avgScore);
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


    @Override
    public int getMyLayout() {
        return R.layout.near_search_fragment;
    }


    @Override
    public void initListener() {

        nearSearchAppBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
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

        mytitle.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                allStringQuery = query;
                page = 0;
                load = true;
                mytitle.clearFocus();
                getNearData(degreeId, kemuRightid, areasid, radius, jsonArray.toJSONString(), avgScore);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        nearRecylerview.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (nearRecylerAdapter != null) {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == nearRecylerAdapter.getItemCount()) {
                        page++;
                        getNearData(degreeId, kemuRightid, areasid, radius, jsonArray.toJSONString(), avgScore);
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

    @OnClick({R.id.ll_leibie, R.id.ll_kemu, R.id.ll_quyu, R.id.ll_shaixuan, R.id.back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_leibie:
                if (!click1) {
                    downPopwindow1();
                    popWindow2.dismiss();
                    popWindow3.dismiss();
                    popWindow4.dismiss();
                    click1 = true;
                } else {
                    popWindow1.dismiss();
                }

                break;
            case R.id.ll_kemu:
                if (!click2) {
                    downPopwindow2();
                    popWindow1.dismiss();
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
                    popWindow1.dismiss();
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
                    popWindow1.dismiss();
                    popWindow2.dismiss();
                    popWindow3.dismiss();
                    click4 = true;
                } else {
                    popWindow4.dismiss();
                }
                break;
            case R.id.back:
                getActivity().finish();
                break;
        }
    }

    private void downPopwindow1() {
        popWindow1.setClippingEnabled(true);
        popWindow1.setFocusable(false);// 是否具有获取焦点的能力
        // 点击PopupWindow以外的区域，PopupWindow是否会消失。
        popWindow1.setBackgroundDrawable(new BitmapDrawable());
        popWindow1.setOutsideTouchable(false);
        popWindow1.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                leibie.setTextColor(getContext().getResources().getColor(R.color.mainTextColor));
                icon.setImageDrawable(Utils.tintDrawable(getResources().getDrawable(R.drawable.home_filter_down_normal),
                        getResources().getColor(R.color.mainTextColor)));
                click1 = false;
            }
        });
        gridView = (GridView) contentViewliebie.findViewById(R.id.gridview_liebie);
        dismiss = (LinearLayout) contentViewliebie.findViewById(R.id.dismiss);
        naerLeibieAdapter = new NaerLeibieAdapter(getContext(), liexinglist);
        gridView.setAdapter(naerLeibieAdapter);
        naerLeibieAdapter.selectnum(pos1);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                naerLeibieAdapter.selectnum(position);
                load = true;
                page = 0;
                degreeId = position + "";
                pos1 = position;
                if (position == 0) {
                    degreeId = null;
                    /*     getNearData(null,kemuRightid,areasid,radius,jsonArray.toString());*/
                } else {
                    /*    getNearData(degreeId,kemuRightid,areasid,radius,jsonArray.toString());*/
                }
                String s = liexinglist.get(position);
                swipeRefreshLayout.setRefreshing(true);
                getNearData(degreeId, kemuRightid, areasid, radius, jsonArray.toJSONString(), avgScore);
                leibie.setText(s);
                popWindow1.dismiss();
            }
        });
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindow1.dismiss();
            }
        });
        popWindow1.showAtLocation(llLeibie, Gravity.NO_GRAVITY, 0, showAtLocationY);
        leibie.setTextColor(getContext().getResources().getColor(R.color.color_main));
        icon.setImageDrawable(Utils.tintDrawable(getResources().getDrawable(R.drawable.home_filter_up_red_selected),
                getResources().getColor(R.color.EF4E4C)));
        /*  icon.setBackground(getContext().getResources().getDrawable(R.drawable.shaixuan));*/
    }

    private void downPopwindow2() {
        popWindow2.setFocusable(false);// 是否具有获取焦点的能力
        popWindow2.setBackgroundDrawable(new BitmapDrawable());
        popWindow2.setOutsideTouchable(false);
        gridView = (GridView) contentViewkemu.findViewById(R.id.gridview_liebie);
        dismiss = (LinearLayout) contentViewkemu.findViewById(R.id.dismiss);
        popWindow2.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                kemu.setTextColor(getContext().getResources().getColor(R.color.mainTextColor));
                icon1.setImageDrawable(Utils.tintDrawable(getResources().getDrawable(R.drawable.home_filter_down_normal),
                        getResources().getColor(R.color.mainTextColor)));
                click2 = false;
            }
        });
        naerKemuRightAdapter = new NaerKemuRightAdapter(getActivity(), dataBean);
        gridView.setAdapter(naerKemuRightAdapter);
        naerKemuRightAdapter.selectnum(pos2);
        /*  mainlist.setSelection(0);*/
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                kemuRightid = Integer.parseInt(dataBean.getChilds().get(position).getId());
                page = 0;
                load = true;
                pos2 = position;
                naerKemuRightAdapter.selectnum(position);
                String name = naerKemuRightAdapter.getData().getChilds().get(position).getName();
                swipeRefreshLayout.setRefreshing(true);
                getNearData(degreeId, kemuRightid, areasid, radius, jsonArray.toJSONString(), avgScore);
                kemu.setText(name);
                popWindow2.dismiss();
            }
        });
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindow2.dismiss();
            }
        });
        popWindow2.showAtLocation(llLeibie, Gravity.NO_GRAVITY, 0, showAtLocationY);
        kemu.setTextColor(getContext().getResources().getColor(R.color.color_main));
        icon1.setImageDrawable(Utils.tintDrawable(getResources().getDrawable(R.drawable.home_filter_up_red_selected),
                getResources().getColor(R.color.EF4E4C)));
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
                quyu.setTextColor(getContext().getResources().getColor(R.color.mainTextColor));
                icon2.setImageDrawable(Utils.tintDrawable(getResources().getDrawable(R.drawable.home_filter_down_normal),
                        getResources().getColor(R.color.mainTextColor)));
                click3 = false;
            }
        });

        naerQuyuLeftAdapter = new NaerQuyuLeftAdapter(getContext(), districtBean);
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
                    swipeRefreshLayout.setRefreshing(true);
                    getNearData(degreeId, kemuRightid, areasid, radius, jsonArray.toJSONString(), avgScore);
                    quyu.setText(districtBean.getAaData().get(position - 1).getName());
                    popWindow3.dismiss();
                }
            }
        });
        morelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    areasid = Integer.valueOf(LocationManager.getInstance().getLocation().cityId);
                    radius = null;
                } else {
                    radius = position * 1000;
                }
                page = 0;
                load = true;
                swipeRefreshLayout.setRefreshing(true);
                getNearData(degreeId, kemuRightid, areasid, radius, jsonArray.toJSONString(), avgScore);
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
        popWindow3.showAtLocation(llLeibie, Gravity.NO_GRAVITY, 0, showAtLocationY);
        quyu.setTextColor(getContext().getResources().getColor(R.color.color_main));
        icon2.setImageDrawable(Utils.tintDrawable(getResources().getDrawable(R.drawable.home_filter_up_red_selected),
                getResources().getColor(R.color.EF4E4C)));
    }

    private void downPopwindow4() {
        popWindow4.setClippingEnabled(true);
        popWindow4.setFocusable(false);// 是否具有获取焦点的能力
        // 点击PopupWindow以外的区域，PopupWindow是否会消失。
        popWindow4.setBackgroundDrawable(new BitmapDrawable());
        popWindow4.setOutsideTouchable(false);

        gridView = (GridView) contentViewshaixuan.findViewById(R.id.gridview_liebie);
        dismiss = (LinearLayout) contentViewshaixuan.findViewById(R.id.dismiss);
        naerPaixuAdapter = new NaerPaixuAdapter(getActivity(), paixuList);

        popWindow4.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                shaixuan.setTextColor(getContext().getResources().getColor(R.color.mainTextColor));
                icon3.setImageDrawable(Utils.tintDrawable(getResources().getDrawable(R.drawable.home_filter_down_normal),
                        getResources().getColor(R.color.mainTextColor)));
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
                getNearData(degreeId, kemuRightid, areasid, radius, jsonArray.toJSONString(), avgScore);
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
        popWindow4.showAtLocation(llLeibie, Gravity.NO_GRAVITY, 0, showAtLocationY);
        shaixuan.setTextColor(getContext().getResources().getColor(R.color.color_main));
        icon3.setImageDrawable(Utils.tintDrawable(getResources().getDrawable(R.drawable.home_filter_up_red_selected),
                getResources().getColor(R.color.EF4E4C)));
    }

    private void sort(int sortpos) {
        JSONObject jo1 = new JSONObject(true);
        switch (sortpos) {
            case 0:
                jsonArray.clear();
                jo1.fluentPut("field", "auto");
                jo1.fluentPut("isAsc", Boolean.toString(false));
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
                jo1.fluentPut("field", "minVipPrice");
                jo1.fluentPut("isAsc", Boolean.toString(true));
                jsonArray.add(jo1);
                break;
            case 3:
                jsonArray.clear();
                jo1.fluentPut("field", "minVipPrice");
                jo1.fluentPut("isAsc", Boolean.toString(false));
                jsonArray.add(jo1);
                break;
            case 4:
                jsonArray.clear();
                jo1.fluentPut("field", "commentCount");
                jo1.fluentPut("isAsc", Boolean.toString(false));
                jsonArray.add(jo1);
                break;
            case 5:
                jsonArray.clear();
                jo1.fluentPut("field", "score");
                jo1.fluentPut("isAsc", Boolean.toString(false));
                jsonArray.add(jo1);
                break;
        }
    }

    private void initMoreAdapterQuyu() {
        naerQuyuRightAdapter = new NaerQuyuRightAdapter(getContext(), quyurightList);
        morelist.setAdapter(naerQuyuRightAdapter);
        naerQuyuRightAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        unbinder = ButterKnife.bind(this, rootView);


        Bundle bundle = this.getArguments();
        if (bundle != null) {
            dataBean = (NearSubjectBean.AaDataBean) bundle.getSerializable("data");
        }
        kemuRightid = Integer.valueOf(dataBean.getId());
        setTextforBundle();
        initBanner();
        return rootView;
    }

    //获取默认数据
    private Observer<NearDataBean> observer = new Observer<NearDataBean>() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onNext(NearDataBean nearDataBean) {
            if (load) {
                nearRecylerview.setAdapter(nearRecylerAdapter);
                mnearDataBean = nearDataBean;
                nearRecylerAdapter.addData(mnearDataBean);
                load = false;
                swipeRefreshLayout.setRefreshing(false);
            } else {
                mnearDataBean.getAaData().addAll(nearDataBean.getAaData());
                nearRecylerAdapter.notifyDataSetChanged();
            }

            /*        nearPulltorefrsh.onRefreshComplete();*/
            nearRecylerAdapter.setOnItemClickListener(new NearRecylerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(getActivity(), HomeRecyclerViewData.class);
                    intent.putExtra("tag", position);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("homeXq", nearRecylerAdapter.getdata().getAaData().get(position));
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }
    };

    //获取所有科目的信息
    private Observer<NearSubjectBean> observerSubject = new Observer<NearSubjectBean>() {

        @Override
        public void onCompleted() {
            Log.e(TAG, "======onNext=======: ");
        }

        @Override
        public void onError(Throwable e) {
            ToastUtil.makeText(getContext(), e.getMessage(), ToastUtil.LENGTH_LONG).show();
        }

        @Override
        public void onNext(NearSubjectBean mnearSubjectBean) {
            nearSubjectBean = mnearSubjectBean;
        }
    };

    //区域的地区名数据
    private Observer<NearDistrictBean> observerNearDistrict = new Observer<NearDistrictBean>() {

        @Override
        public void onCompleted() {
            Log.e(TAG, "======onCompleted=======: ");
        }

        @Override
        public void onError(Throwable e) {
            ToastUtil.makeText(getContext(), e.getMessage(), ToastUtil.LENGTH_LONG).show();
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


    //全部科目
    public void getNearSubject() {
        NetWork.getNearSubject().getKemuAllData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observerSubject);
    }


    private void getNearData(String leixingid, Integer kemuid, Integer areasId, Integer radius, String sort, Boolean avgScore) {

        if (sort.length() <= 2) {
            sort = null;
        }
        Location location = LocationManager.getInstance().getLocation();
        NetWork.getNear().getNearData(location.longitude, location.latitude, page, "20", leixingid, kemuid, areasId, radius,
                sort, avgScore, allStringQuery)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }


    //数据刷新
    public void setNearPulltorefrsh() {

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                load = true;
                page = 0;
                getNearData(degreeId, kemuRightid, areasid, radius, jsonArray.toJSONString(), avgScore);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }
}
