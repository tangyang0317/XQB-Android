package com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh.interfacequestion;

/**
 * Created by hqf on 2017/11/16.
 * 问答接口
 */

public interface MyQuestion {
    //我的问答--推荐和最新/我的提问数据/提问草稿
    String POST_QUESTION_RECOMMEND = "/myQuestion/ls.json";
    //我的问答--详情下面列表数据/问答草稿
    String POST_QUESTION_TWO_ANSWER = "/myAnswer/ls.json";
    String POST_QUESTION_DELETE_Question = "/question/del.json";
    String POST_QUESTION_DELETE_ANSWER = "/myAnswer/del.json";


    //我的问答--收藏
    String POST_QUESTION_COLLECT = "/questionCollection/add.json";
    //我的问答--回答提交/保存编辑
    String POST_QUESTION_MYANSWER_COMMIT = "/myAnswer/add.json";
    //我的问答--提问提交/保存编辑
    String POST_QUESTION_ADD_COMMIT = "/myQuestion/add.json";
    //我的问答--我的收藏
    String POST_QUESTION_ADD_COLLECT_ME = "/questionCollection/ls.json";
}
