package com.zhangju.xingquban.interestclassapp.ui.fragment.near;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fastlib.annotation.Event;
import com.fastlib.app.EventObserver;
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
import com.zhangju.xingquban.interestclassapp.bean.HomeCityIdBaen;
import com.zhangju.xingquban.interestclassapp.bean.NearDataBean;
import com.zhangju.xingquban.interestclassapp.bean.NearSubjectBean;
import com.zhangju.xingquban.interestclassapp.bean.near.NearDistrictBean;
import com.zhangju.xingquban.interestclassapp.refactor.common.PopupListFragment;
import com.zhangju.xingquban.interestclassapp.refactor.location.EventLocationChanged;
import com.zhangju.xingquban.interestclassapp.refactor.location.Location;
import com.zhangju.xingquban.interestclassapp.refactor.location.LocationManager;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.CityActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.HomeRecyclerViewData;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.zyzs.ChangeCityEvent;
import com.zhangju.xingquban.interestclassapp.ui.fragment.near.NearShare.NearShareActivity;
import com.zhangju.xingquban.interestclassapp.util.DpUtil;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;
import com.zhangju.xingquban.interestclassapp.view.BannerHelper;
import com.zhangju.xingquban.refactoring.adapter.NearByListAdapter;
import com.zhangju.xingquban.refactoring.view.AppBarStateChangeListener;
import com.zhangju.xingquban.refactoring.view.XQBLoadMoreView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;


/**
 * Created by dhy on 2017/6/14.
 */
//附近模块
public class NearbyFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    public static final String TAG = "NearbyFragment";
    public static final String PAGE_INDEX = "1";

    @BindView(R.id.near_cityText)
    TextView near_cityText;
    @BindView(R.id.near_edit_share)
    LinearLayout near_search;

    @BindView(R.id.near_recylerview)
    RecyclerView nearRecylerview;

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
    @BindView(R.id.nearByAppBarLayout)
    AppBarLayout nearByAppBarLayout;


    private int page = 0;

    private NearByListAdapter nearByListAdapter;
    private Context context;

    private LinearLayoutManager linearLayoutManager;
    private NearDistrictBean districtBean;
    private NearSubjectBean nearSubjectBean;
    private NearDataBean mnearDataBean;
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
    private ArrayList<String> liexinglist = new ArrayList<>();
    private NaerLeibieAdapter naerLeibieAdapter;
    private NaerKemuLeftAdapter naerKemuLeftAdapter;
    private NaerKemuRightAdapter naerKemuRightAdapter;
    private NaerQuyuLeftAdapter naerQuyuLeftAdapter;
    private NaerQuyuRightAdapter naerQuyuRightAdapter;
    private NaerPaixuAdapter naerPaixuAdapter;
    private DisplayMetrics dm;

    private ArrayList<String> quyurightList = new ArrayList<>();
    private ArrayList<String> paixuList = new ArrayList<>();
    private Map<String, String> paixumap = new HashMap<>();
    private String degreeId = null;
    private Integer areasid = null;
    private Integer radius = null;
    private Integer kemuRightid = null;
    private View contentViewliebie;
    private View contentViewkemu;
    private View contentViewquyu;
    private View contentViewshaixuan;
    private int screenHeight;
    private JSONArray jsonArray = new JSONArray();
    private Boolean avgScore = null;
    private String allStringQuery = null;
    private boolean isSearch = false;
    private int lastVisibleItem;
    private String requestCity = "上海市";
    private String requestCityCode = "310000";
    ObjectAnimator mIcon1RotationAnim;
    ObjectAnimator mIcon2RotationAnim;
    ObjectAnimator mIcon3RotationAnim;
    ObjectAnimator mIcon4RotationAnim;
    PopupListFragment mCurrPopupFragment;
    int mCurrPopupListIndex = -1;
    private ArrayList<ArrayList<String>> subListId_kemu = new ArrayList<>();
    private BannerHelper mBannerHelper;

    @Override
    public void initView() {
        EventObserver.getInstance().subscribe(getActivity(), this);
        dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = getResources().getDimensionPixelSize(resourceId);
        }

        screenHeight = dm.heightPixels - DpUtil.dip2px(88) - statusBarHeight1;
        contentViewliebie = LayoutInflater.from(getContext()).inflate(R.layout.pop_down_leibie,
                null);
        popWindow1 = new PopupWindow(contentViewliebie, ViewGroup.LayoutParams.MATCH_PARENT,
                screenHeight);

        contentViewkemu = LayoutInflater.from(getContext()).inflate(R.layout.pop_down_kemu,
                null);
        popWindow2 = new PopupWindow(contentViewkemu, ViewGroup.LayoutParams.MATCH_PARENT,
                screenHeight);

        contentViewquyu = LayoutInflater.from(getContext()).inflate(R.layout.pop_down_kemu,
                null);
        popWindow3 = new PopupWindow(contentViewquyu, ViewGroup.LayoutParams.MATCH_PARENT,
                screenHeight - DpUtil.dip2px(50));


        contentViewshaixuan = LayoutInflater.from(getContext()).inflate(R.layout.pop_down_leibie,
                null);
        popWindow4 = new PopupWindow(contentViewshaixuan, ViewGroup.LayoutParams.MATCH_PARENT,
                screenHeight);
        mIcon1RotationAnim = generateRotationAnim(0, leibie, icon);
        mIcon2RotationAnim = generateRotationAnim(1, kemu, icon1);
        mIcon3RotationAnim = generateRotationAnim(2, quyu, icon2);
        mIcon4RotationAnim = generateRotationAnim(3, shaixuan, icon3);
    }

    @Override
    public void initListener() {
    }

    private ObjectAnimator generateRotationAnim(final int index, final TextView tv, final ImageView imageView) {
        ObjectAnimator oa = ObjectAnimator.ofFloat(imageView, "rotation", 0, 180);
        oa.setDuration(150);
        oa.setInterpolator(new LinearInterpolator());
        oa.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                if (mCurrPopupListIndex == index) {
                    tv.setTextColor(getResources().getColor(R.color.EF4E4C));
                    imageView.setImageDrawable(Utils.tintDrawable(getResources().getDrawable(R.drawable.home_filter_down_normal), getResources().getColor(R.color.EF4E4C)));
                } else {
                    tv.setTextColor(Color.parseColor("#666666"));
                    imageView.setImageResource(R.drawable.home_filter_down_normal);
                }
            }
        });
        return oa;
    }

    @Override
    public void initData() {
        liexinglist.add("全部类型");
        liexinglist.add("老师");
        liexinglist.add("机构");
        mBannerHelper = new BannerHelper(getContext());
        mBannerHelper.init(mHomeBanner).loadBannerDate("7");
        /*     getNearData(null,null,null,radius,null);*/
        /*获取科目数据*/
        getNearSubject();
        /*区域数据*/
        getNearDistrict();
        /*弹框数据*/
        initQuyuRightData();
        setTextforBundle();

    }

    @Event
    private void changeCity(ChangeCityEvent changeCityEvent) {
        //  ToastUtil.showToast("FUCK"+LocationManager.getInstance().getLocation().locationCity);
        /*区域数据*/
        getNearDistrict();
        setCityText();
        quyu.setText("区域");
    }

    @Event
    private void eCityChanged(EventLocationChanged event) {
        Location location = LocationManager.getInstance().getLocation();
        near_cityText.setText(location.locationCity);
        areasid = Integer.valueOf(location.cityId);
        page = 0;
        getNearData(degreeId, kemuRightid, areasid, radius, jsonArray.toJSONString(), avgScore);
    }

    private void setTextforBundle() {
        if (degreeId != null) {
            if (degreeId.equals("1")) {
                leibie.setText("老师");
                pos1 = 1;
            } else if (degreeId.equals("2")) {
                leibie.setText("机构");
                pos1 = 2;
            }
        }

        areasid = Integer.valueOf(LocationManager.getInstance().getLocation().cityId);
        near_cityText.setText(LocationManager.getInstance().getLocation().locationCity);
        nearByListAdapter = new NearByListAdapter();
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipeRefreshLayout.setRefreshing(true);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        nearRecylerview.setLayoutManager(linearLayoutManager);
        nearRecylerview.setAdapter(nearByListAdapter);
        swipeRefreshLayout.setOnRefreshListener(this);
        nearByListAdapter.setLoadMoreView(new XQBLoadMoreView());
        nearByListAdapter.setOnLoadMoreListener(this, nearRecylerview);

        nearByListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                NearDataBean.AaDataBean bean = nearByListAdapter.getItem(position);
                HomeRecyclerViewData.launchActivity(getActivity(), bean.getId());
            }
        });


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
        return R.layout.near_fragment;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //用户选择城市数据搜索
        if (requestCode == 1 && resultCode == RESULT_OK) {
            requestCity = data.getStringExtra("city");
            requestCityCode = data.getStringExtra("cityId");
            page = 1;
            setCityText();
            EventObserver.getInstance().sendEvent(getActivity(), new ChangeCityEvent());
        }
    }

    public void setCityText() {
        LocationManager.getInstance().convertCityId();
    }

    Observer<HomeCityIdBaen> observerCityId = new Observer<HomeCityIdBaen>() {

        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            ToastUtil.showToast("已为你切换为上海市");
            getNearData(degreeId, kemuRightid, 310000, radius, jsonArray.toJSONString(), avgScore);
        }

        @Override
        public void onNext(HomeCityIdBaen homeCityIdBaen) {
            if (homeCityIdBaen.isSuccess()) {
                String name = homeCityIdBaen.getAaData().get(0).getName();
                String id = homeCityIdBaen.getAaData().get(0).getId();
                String pid = homeCityIdBaen.getAaData().get(0).getPid();
                Location location = LocationManager.getInstance().getLocation();

                location.cityId = id;
                location.cityPid = pid;
                location.locationCity = name;
                LocationManager.getInstance().updateLocation(location);
                near_cityText.setText(name);
                areasid = Integer.valueOf(id);
                page = 0;
                getNearData(degreeId, kemuRightid, areasid, radius, jsonArray.toJSONString(), avgScore);
            } else {
                ToastUtil.showToast("加载失败");
            }
        }
    };

    @OnClick({R.id.near_edit_share, R.id.near_cityText, R.id.ll_leibie, R.id.ll_kemu, R.id.ll_quyu, R.id.ll_shaixuan})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.near_edit_share:
                startActivity(new Intent(getActivity(), NearShareActivity.class));
                break;
            case R.id.near_cityText:
                Intent intent = new Intent(getActivity(), CityActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.ll_leibie:
                popWindow3.dismiss();
                toggleSingleListPopup(0, pos1, liexinglist, leibie, mIcon1RotationAnim);
                break;
            case R.id.ll_kemu:
                ArrayList<ArrayList<String>> subList = new ArrayList<>();
                ArrayList<String> list = new ArrayList<>();

                if (nearSubjectBean != null && nearSubjectBean.getAaData() != null) {
                    for (NearSubjectBean.AaDataBean bean : nearSubjectBean.getAaData()) {
                        list.add(bean.getName());
                        if (bean.getChilds() != null) {
                            ArrayList<String> childList = new ArrayList<>();
                            ArrayList<String> childListid = new ArrayList<>();
                            for (NearSubjectBean.AaDataBean.ChildsBean childsBean : bean.getChilds()) {
                                childList.add(childsBean.getName());
                                childListid.add(childsBean.getId());
                            }
                            subListId_kemu.add(childListid);
                            subList.add(childList);
                        }
                    }
                }
                togglePoupup(1, pos2, pos3, false, false, list, subList, kemu, mIcon2RotationAnim);
                break;
            case R.id.ll_quyu:
                ArrayList<ArrayList<String>> subList2 = new ArrayList<>();
                ArrayList<String> list2 = new ArrayList<>();
                if (nearSubjectBean != null && nearSubjectBean.getAaData() != null) {
                    list2.add("附近");
                    for (NearDistrictBean.AaDataBean bean : districtBean.getAaData()) {
                        list2.add(bean.getName());
                    }
                }
                subList2.add(quyurightList);
                togglePoupup(2, 0, 0, false, true, list2, subList2, quyu, mIcon3RotationAnim);
                break;
            case R.id.ll_shaixuan:
                toggleSingleListPopup(3, pos4, paixuList, shaixuan, mIcon4RotationAnim);
                break;
        }
    }

    /**
     * 触发单列表折叠视图
     *
     * @param index
     * @param selectPosition
     * @param mainList
     * @param lable
     * @param oa
     */
    private void toggleSingleListPopup(int index, int selectPosition, ArrayList<String> mainList, TextView lable, ObjectAnimator oa) {
        togglePoupup(index, selectPosition, 0, true, false, mainList, null, lable, oa);
    }

    ObjectAnimator mCurrSuspendAnim;

    /**
     * 触发列表折叠视图
     *
     * @param index                  类型索引
     * @param selectedPosition       已选中左列表中的索引
     * @param secondSelectedPosition 已选中右列表中索引 （双列表时有效）
     * @param singleList             是否单列表
     * @param mainList               主列表数据
     * @param secondList             副列表数据 （双列表时有效）
     * @param lable                  标签
     * @param oa                     动画
     */
    private void togglePoupup(int index, int selectedPosition, int secondSelectedPosition, boolean singleList, boolean fujin, ArrayList<String> mainList, ArrayList<ArrayList<String>> secondList, final TextView lable, final ObjectAnimator oa) {
        if (mCurrPopupFragment != null) {
            mCurrPopupFragment.finish();
            mCurrPopupFragment = null;
        }
        if (mCurrPopupListIndex != index) {
            mCurrPopupListIndex = index;
            Bundle bundle = new Bundle();
            mCurrPopupFragment = new PopupListFragment();
            if (mCurrSuspendAnim != null) mCurrSuspendAnim.reverse();
            mCurrSuspendAnim = oa;

            bundle.putBoolean(PopupListFragment.ARG_BOOL_SINGLE_LIST, singleList);
            bundle.putBoolean(PopupListFragment.ARG_BOOL_ISFUJIN, fujin);
            bundle.putStringArrayList(PopupListFragment.ARG_LIST_STR, mainList);
            bundle.putInt(PopupListFragment.ARG_INT_SELECTED_POSITION, selectedPosition);
            bundle.putSerializable(PopupListFragment.ARG_LIST_SECOND_STR, secondList);
            bundle.putInt(PopupListFragment.ARG_INT_SELECTED_SECOND_POSITION, secondSelectedPosition);
            mCurrPopupFragment.setArguments(bundle);
            mCurrPopupFragment.setOnPopListClickListener(new PopupListFragment.OnListItemClickListener() {
                @Override
                public void onItemClicked(boolean firstList, int mainpostion, int position, String text) {
                    page = 0;
                    if (position != -1) {
                        if (mCurrPopupListIndex == 0) {
                            pos1 = position;
                            lable.setText(text);
                            degreeId = position + "";
                            if (position == 0) degreeId = null;
                            swipeRefreshLayout.setRefreshing(true);
                            getNearData(degreeId, kemuRightid, areasid, radius, jsonArray.toJSONString(), avgScore);
                        } else if (mCurrPopupListIndex == 1) {
                            pos2 = mainpostion;
                            pos3 = position;
                            kemuRightid = Integer.parseInt(subListId_kemu.get(mainpostion).get(position));
                            swipeRefreshLayout.setRefreshing(true);
                            getNearData(degreeId, kemuRightid, areasid, radius, jsonArray.toJSONString(), avgScore);
                            kemu.setText(text);
                        } else if (mCurrPopupListIndex == 2) {
                            if (mainpostion == 0) {
                                if (position == 0) {
                                    areasid = Integer.valueOf(LocationManager.getInstance().getLocation().cityId);
                                    radius = null;
                                } else {
                                    radius = position * 1000;
                                }
                                swipeRefreshLayout.setRefreshing(true);
                                getNearData(degreeId, kemuRightid, areasid, radius, jsonArray.toJSONString(), avgScore);
                                quyu.setText(quyurightList.get(position));
                            } else {
                                radius = null;
                                areasid = Integer.valueOf(districtBean.getAaData().get(position - 1).getId());
                                quyu.setText(districtBean.getAaData().get(position - 1).getName());
                                swipeRefreshLayout.setRefreshing(true);
                                getNearData(degreeId, kemuRightid, areasid, radius, jsonArray.toJSONString(), avgScore);
                            }
                        } else if (mCurrPopupListIndex == 3) {
                            pos4 = position;
                            sort(position);
                            shaixuan.setText(paixuList.get(position));
                            swipeRefreshLayout.setRefreshing(true);
                            getNearData(degreeId, kemuRightid, areasid, radius, jsonArray.toJSONString(), avgScore);
                        }
                    }
                    mCurrPopupListIndex = -1;
                    mCurrPopupFragment.finish();
                    mCurrPopupFragment = null;
                    mCurrSuspendAnim.reverse();
                }
            });
            getChildFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, mCurrPopupFragment)
                    .commit();
            mCurrSuspendAnim.start();
        } else {
            mCurrPopupListIndex = -1;
            mCurrSuspendAnim.reverse();
        }
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


    //获取默认数据
    private Observer<NearDataBean> observer = new Observer<NearDataBean>() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
        }

        @Override
        public void onNext(NearDataBean nearDataBean) {
            List<NearDataBean.AaDataBean> dataBeanList = nearDataBean.getAaData();
            if (page == 0) {
                swipeRefreshLayout.setRefreshing(false);
                if (dataBeanList != null && dataBeanList.size() > 0) {
                    if (dataBeanList.size() < 10) {
                        nearByListAdapter.loadMoreComplete();
                        nearByListAdapter.loadMoreEnd();
                        nearByListAdapter.setNewData(dataBeanList);
                    } else {
                        nearByListAdapter.setEnableLoadMore(true);
                        nearByListAdapter.setNewData(dataBeanList);
                    }
                } else {
                    nearByListAdapter.loadMoreEnd();
                }
            } else {
                if (dataBeanList != null && dataBeanList.size() > 0) {
                    nearByListAdapter.loadMoreComplete();
                    nearByListAdapter.addData(dataBeanList);
                } else {
                    nearByListAdapter.loadMoreComplete();
                    nearByListAdapter.loadMoreEnd();
                    nearByListAdapter.addData(dataBeanList);
                }
            }

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
        NetWork.getNear().getNearData(LocationManager.getInstance().getLocation().longitude, LocationManager.getInstance().getLocation().latitude, page, "20", leixingid, kemuid, areasId, radius, sort, avgScore, allStringQuery)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventObserver.getInstance().unsubscribe(getActivity(), true);
        mBannerHelper.stopBanner();
    }

    @Override
    public void onRefresh() {
        page = 0;
        getNearData(degreeId, kemuRightid, areasid, radius, jsonArray.toJSONString(), avgScore);
    }

    @Override
    public void onLoadMoreRequested() {
        page++;
        getNearData(degreeId, kemuRightid, areasid, radius, jsonArray.toJSONString(), avgScore);
    }
}