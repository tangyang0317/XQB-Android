package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.kcgl;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.NetWork;
import com.zhangju.xingquban.interestclassapp.bean.HomeViewPage;
import com.zhangju.xingquban.interestclassapp.bean.NearMSRight;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

/**
 * Created by zsl on 2017/9/6.
 */

public class MeJiGouKcglSkkmAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private HomeViewPage mHomeViewPage;
    private Context mContext;
    private String id;
    private View view;
    private Subscription subscription;
    private  MeJiGouKcglSkkmItemAdapter meJiGouKcglSkkmItemAdapter;

    public MeJiGouKcglSkkmAdapter(Context context, HomeViewPage homeViewPage) {
        this.mContext = context;
        this.mHomeViewPage = homeViewPage;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.jg_kcgl_skkm_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ViewHolder holder1 = (ViewHolder) holder;
        Glide.with(mContext).load(mHomeViewPage.getAaData().get(position).getCatagoriesPicture()).into(holder1.itemimgae);
        holder1.itemtext.setText(mHomeViewPage.getAaData().get(position).getName());
        id = mHomeViewPage.getAaData().get(position).getId();

        Observer<NearMSRight> observerNearMSRight = new Observer<NearMSRight>() {

            @Override
            public void onCompleted() {
                Log.e(TAG, "======onNext=======: ");
            }

            @Override
            public void onError(Throwable e) {
                ToastUtil.showToast(e.toString());
            }

            @Override
            public void onNext(NearMSRight nearMSRight) {
                Log.i(TAG, "======nearMSRight: " + nearMSRight.getAaData().toString());
                if (nearMSRight.isSuccess()) {
                    holder1.recyclerSkkm.setLayoutManager(new GridLayoutManager(mContext, 4));
                    meJiGouKcglSkkmItemAdapter = new MeJiGouKcglSkkmItemAdapter(mContext, nearMSRight);
                    holder1.recyclerSkkm.setAdapter(meJiGouKcglSkkmItemAdapter);
                } else {

                }

            }
        };

        subscription = NetWork.getNearMSRight().getNearMSRight1(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observerNearMSRight);
    }

    @Override
    public int getItemCount() {
        return mHomeViewPage.getAaData().size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.itemimgae)
        ImageView itemimgae;
        @BindView(R.id.itemtext)
        TextView itemtext;
        @BindView(R.id.recycler_skkm)
        RecyclerView recyclerSkkm;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
