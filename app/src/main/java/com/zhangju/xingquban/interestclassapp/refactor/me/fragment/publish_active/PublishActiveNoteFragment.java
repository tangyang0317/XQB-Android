package com.zhangju.xingquban.interestclassapp.refactor.me.fragment.publish_active;

import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.Event;
import com.fastlib.app.EventObserver;
import com.fastlib.app.FastDialog;
import com.fastlib.app.FastFragment;
import com.fastlib.utils.N;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.EventPublishDataEdited;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.EventPublishProgressNext;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.PublishActiveInfo;

/**
 * Created by sgfb on 2017/11/1.
 * 发布活动活动须知页
 */
@ContentView(R.layout.frag_publish_active_note)
public class PublishActiveNoteFragment extends FastFragment{
    @Bind(R.id.emptyView)
    View mEmptyView;
    @Bind(R.id.contentView)
    View mContentView;
    @Bind(R.id.note)
    EditText mNote;

    @Override
    protected void alreadyPrepared() {

    }

    @Bind(R.id.add)
    private void addNote(){
        mContentView.setVisibility(View.VISIBLE);
        mEmptyView.setVisibility(View.GONE);
    }

    @Bind(R.id.delete)
    private void deleteNote(){
        FastDialog.showMessageDialog("确定删除该模块？",true).show(getChildFragmentManager(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mNote.setText(null);
                mEmptyView.setVisibility(View.VISIBLE);
                mContentView.setVisibility(View.GONE);
            }
        });
    }

    @Event
    private void ePublishNextStep(EventPublishProgressNext event){
        if(event.getmCla()==getClass()){
            if(TextUtils.isEmpty(mNote.getText().toString())){
                N.showShort(getContext(),"活动须知是必填的");
                return;
            }
            PublishActiveInfo info=new PublishActiveInfo();
            info.notes=new PublishActiveInfo.ActiveNotes();
            info.notes.notes=mNote.getText().toString();
            EventObserver.getInstance().sendEvent(getContext(),new EventPublishDataEdited(info));
        }
    }
}
