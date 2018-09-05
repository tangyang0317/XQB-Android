package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fastlib.adapter.MultiTypeAdapter;
import com.fastlib.base.CommonViewHolder;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseOrgProfile;

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
            if (Patterns.WEB_URL.matcher(s).matches()) mImageGroup.addData(s);
            else mTextGroup.addData(s);
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
            Glide.with(mContext).load(data).into((ImageView) holder.getView(R.id.image));
        }

        @Override
        protected int getLayoutId() {
            return R.layout.item_org_profile_image;
        }
    }
}
