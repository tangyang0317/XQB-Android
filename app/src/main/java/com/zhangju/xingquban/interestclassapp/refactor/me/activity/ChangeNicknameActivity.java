package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.LocalData;
import com.fastlib.app.FastActivity;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.utils.KeyBoardUtils;
import com.fastlib.utils.N;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.user.User;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;

/**
 * Created by sgfb on 2017/10/28.
 * 修改昵称或机构名称
 */
@ContentView(R.layout.act_change_nickname)
public class ChangeNicknameActivity extends FastActivity {
    public static final String ARG_INT_CHANGE_TYPE = "changeType"; //学生1 老师2 机构3

    @LocalData(ARG_INT_CHANGE_TYPE)
    int mType;
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.nickname)
    EditText mNickname;

    @Override
    protected void alreadyPrepared() {
        String userName = UserManager.getInstance().getUser().signame;
        mNickname.append(TextUtils.isEmpty(userName) ? "" : userName);
        mNickname.setHint("请输入新的昵称");
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (mType == 3) {
            mTitleBar.getTitle().setText("修改机构名称");
            mNickname.setHint("请输入新的机构名称，如：私塾学院");
        }
    }

    @Bind(R.id.commit)
    private void commit() {
        final String newNickname = mNickname.getText().toString();

        if (TextUtils.isEmpty(newNickname)) {
            N.showSnackbarShort(mNickname, "新昵称不能为空");
            return;
        }
        KeyBoardUtils.closeKeybord(this);
        loading();
        Request request = Request.obtain(MeInterface.POST_CHANGE_USER_DATA);
        request.put("id", UserManager.getInstance().getUser().id);
        switch (mType){
            case 1:request.put("signame",newNickname);break;
            case 2:request.put("username",newNickname);break;
            case 3:request.put("changeName",newNickname);break;
            default:System.out.println("一个不存在的用户类型");break;
        }
        request.setListener(new SimpleListener<Response>() {

            @Override
            public void onResponseListener(Request r, Response result) {
                dismissLoading();
                if (result.success) {
                    N.showShort(ChangeNicknameActivity.this, "修改昵称成功");
                    setResult(RESULT_OK);
                    finish();
                } else N.showShort(ChangeNicknameActivity.this, "修改昵称失败");
            }

            @Override
            public void onErrorListener(Request r, String error) {
                super.onErrorListener(r, error);
                dismissLoading();
            }
        });
        net(request);
    }
}
