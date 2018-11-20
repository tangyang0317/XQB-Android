package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.fastlib.adapter.MultiTypeAdapter;
import com.fastlib.base.CommonViewHolder;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseOrgProfile;
import com.zhangju.xingquban.interestclassapp.util.ScreenUtils;
import com.zhangju.xingquban.refactoring.utils.DimentUtils;

/**
 * Created by sgfb on 2017/11/3.
 * 机构简介适配器
 */
public class OrgProfileDisplayAdapter extends MultiTypeAdapter {
    private TitleGroup mTitleGroup;
    private TextGroup mTextGroup;
    private ImageGroup mImageGroup;

    public OrgProfileDisplayAdapter(Context context) {
        super(context);
        addGroup(mTextGroup = new TextGroup());
        addGroup(mImageGroup = new ImageGroup());
    }

    public void setData(ResponseOrgProfile orgProfile) {
        if (orgProfile == null) return;
        clear();
        String[] intro = TextUtils.isEmpty(orgProfile.intro) ? new String[0] : orgProfile.intro.split("#");
        for (String s : intro) {
            if (Patterns.WEB_URL.matcher(s).matches()) {
                mImageGroup.addData(s);
            } else {
                mTextGroup.addData(s);
            }
        }
    }

    /**
     * 标题群组
     */
    public class TitleGroup extends RecyclerGroup<String> {

        @Override
        protected void binding(int positionOfRecyclerView, int positionOfGroup, String data, CommonViewHolder holder) {
            holder.setText(R.id.title, data);
        }

        @Override
        protected int getLayoutId() {
            return R.layout.item_org_profile_title;
        }
    }

    /**
     * 文本群组
     */
    public class TextGroup extends RecyclerGroup<String> {

        @Override
        protected void binding(int positionOfRecyclerView, int positionOfGroup, String data, CommonViewHolder holder) {
            holder.setText(R.id.text, data);
        }

        @Override
        protected int getLayoutId() {
            return R.layout.item_org_profile_text;
        }
    }

    /**
     * 图像群组
     */
    public class ImageGroup extends RecyclerGroup<String> {

        @Override
        protected void binding(int positionOfRecyclerView, int positionOfGroup, String data, CommonViewHolder holder) {
            final ImageView imageView = holder.getView(R.id.image);
            Glide.with(mContext)
                    .load(data)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            if (imageView == null) {
                                return false;
                            }
                            if (imageView.getScaleType() != ImageView.ScaleType.FIT_XY) {
                                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                            }
                            ViewGroup.LayoutParams params = imageView.getLayoutParams();
                            params.height = resource.getIntrinsicHeight();
                            params.width = ScreenUtils.getScreenWidth(mContext) - DimentUtils.dip2px(mContext, 10);
                            imageView.setLayoutParams(params);
                            return false;
                        }
                    })
                    .into(imageView);
        }

        @Override
        protected int getLayoutId() {
            return R.layout.item_org_profile_image;
        }
    }
}
