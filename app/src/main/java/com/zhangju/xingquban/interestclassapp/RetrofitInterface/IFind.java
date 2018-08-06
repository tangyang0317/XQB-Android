package com.zhangju.xingquban.interestclassapp.RetrofitInterface;

import com.zhangju.xingquban.interestclassapp.bean.MessageSendBean;
import com.zhangju.xingquban.interestclassapp.bean.find.FindQuestionBean;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekMessage.FindMessageBean;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.FindMeCgTwXqBean;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.FindMeWendaBean;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.FindMeWendaXqBean;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.FindSCBean;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.FindTiWenXqHdBean;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.FindTiWenXqPlBean;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.FindTiWenXqSCBean;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.FindWDBean;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.FindWDCgTiWenBean;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.FindWDCgWendaBean;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zsl on 2017/8/24.
 */

public interface IFind {

    //old提问
    @POST("admnxzcmr/myQuestion/add.json")
    Observable<Object> getMeTiwen(
            @Query("customerId") String customerId,
            @Query("title") String title,
            @Query("pic") String picurl,
            @Query("isRelease") Integer isRelease,
            @Query("label") String label
    );


    // new提问
    @POST("admnxzcmr/myQuestion/add.json")
    Observable<FindQuestionBean> getMeNewTiwen(
            @Query("customerId") String customerId,
            @Query("title") String title,
            @Query("qExplain") String qExplain,
            @Query("pic") String picurl,
            @Query("isRelease") Integer isRelease,
            @Query("label") String label
    );


    // new 草稿详情
    @POST("admnxzcmr/question/ls.json")
    Observable<FindMeWendaXqBean> getDraftinfo(
            @Query("customerId") String customerId,
            @Query("isRelease") int isRelease,
            @Query("id") String id
    );


    //old
    @POST("admnxzcmr/myQuestion/ls.json")
    Observable<FindWDBean> myQuestionAll(
//            @Query("customerId") String customerId,
            @Query("isRelease") Integer isRelease,
            @Query("isNew") Integer isNew

    );

    //查询提问或者草稿详情
    @POST("admnxzcmr/myQuestion/ls.json")
    Observable<FindWDBean> myQuestion(
            @Query("customerId") String customerId,
            @Query("isRelease") Integer isRelease,
            @Query("isNew") boolean isNew,
            @Query("pageIndex") int pageIndex,
            @Query("pageSize") String pageSize
    );

    //我的问答
    @POST("admnxzcmr/myQuestion/ls.json")
    Observable<FindMeWendaXqBean> myQuestionWd(
            @Query("isRelease") Integer isRelease,
            @Query("isNew") boolean isNew,
            @Query("id") String id
    );

    //查询提问或者草稿详情页码
    @POST("admnxzcmr/myQuestion/ls.json")
    Observable<FindWDBean> myQuestionPage(
            @Query("pageIndex") int pageIndex,
            @Query("pageSize") String pageSize
    );

    //添加草稿
    @POST("admnxzcmr/myQuestion/add.json")
    Observable<FindWDBean> myQuestionCg(
            @Query("customerId") String customerId,
            @Query("title") String title,
            @Query("qExplain") String qExplain,
            @Query("isRelease") Integer isRelease,
            @Query("label") String label
    );

    //查询我的提问草稿
    @POST("admnxzcmr/myQuestion/ls.json")
    Observable<FindWDCgWendaBean> myQuestionWdCg(
            @Query("customerId") String customerId,
            @Query("isRelease") Integer isRelease,
            @Query("pageIndex") int pageIndex,
            @Query("pageSize") String pageSize
    );

    //查询我的回答草稿
    @POST("admnxzcmr/myAnswer/ls.json")
    Observable<FindWDCgTiWenBean> myQuestionWdWd(
            @Query("customerId") String customerId,
            @Query("isRelease") Integer isRelease,
            @Query("pageIndex") int pageIndex,
            @Query("pageSize") String pageSize
    );

    //new查询我的回答草稿
    @POST("admnxzcmr/myAnswer/ls.json")
    Observable<FindWDCgTiWenBean> myDraftinfo(
            @Query("customerId") String customerId,
            @Query("isRelease") Integer isRelease,
            @Query("id") String id
    );

    //添加我的收藏
    @POST("admnxzcmr/questionCollection/add.json")
    Observable<FindTiWenXqSCBean> questionCollection(
            @Query("customerId") String customerId,
            @Query("questionId") String questionId
    );

    //查询我的收藏
    @POST("admnxzcmr/questionCollection/ls.json")
    Observable<FindSCBean> questionCollectionls(
            @Query("customerId") String customerId

    );


    //添加问题回答
    @POST("admnxzcmr/myAnswer/add.json")
    Observable<FindTiWenXqHdBean> myAnswer(
            @Query("signName") String signName,
            @Query("customerPic") String customerPic,
            @Query("title") String title,
            @Query("questionId") String questionId,
            @Query("customerId") String customerId,
            @Query("pic") String pic,
            @Query("isRelease") Integer isRelease
    );

    //查询我的回答
    @POST("admnxzcmr/myAnswer/ls.json")
    Observable<FindTiWenXqPlBean> myAnswerls(
            @Query("isRelease") int isRelease,
            @Query("questionId") String questionId,
            @Query("pageSize") int pageSize
    );

    //查询我的草稿
    @POST("admnxzcmr/myQuestion/ls.json")
    Observable<FindMeCgTwXqBean> myQuestionCg(
//            @Query("customerId") String customerId,
            @Query("isRelease") Integer isRelease,
            @Query("id") String questionId
    );

    //查询回答
    @POST("admnxzcmr/myAnswer/ls.json")
    Observable<FindMeWendaBean> myAnswerHd(
            @Query("customerId") String customerId,
            @Query("isRelease") int isRelease,
            @Query("pageIndex") int pageIndex,
            @Query("pageSize") String pageSize
    );


    //信息平台查询
//     infoType;  1 students(招生) 2 recruitment(招聘) 3 rent（租赁） 4 transfer（转租） 5其他
    @POST("admnxzcmr/ipm/index.json")
    Observable<FindMessageBean> myMessage(
            @Query("infoType") Integer infoType,
            @Query("pageIndex") Integer pageIndex,
            @Query("pageSize") String pageSize,
            @Query("stime") String stime,
            @Query("etime") String etime,


            @Query("title") String title,
            @Query("cityName") String cityName,
            @Query("infoContent") String infoContent

    );  //信息平台发布
//     infoType;  1 students(招生) 2 recruitment(招聘) 3 rent（租赁） 4 transfer（转租）
    @POST("admnxzcmr/ipm/add.json")
    Observable<MessageSendBean> addMyMessage(
            @Query("teacherTimeId")String teacherTimeId,
            @Query("infoType") Integer infoType,
            @Query("stime") String stime,
            @Query("etime") String etime,
            @Query("title") String title,
            @Query("cityName") String cityName,
            @Query("infoContent") String infoContent

    );

}
