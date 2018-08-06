package com.zhangju.xingquban.interestclassapp.refactor.me.bean;

import com.zhangju.xingquban.interestclassapp.refactor.discover.bean.ResponseActive;

/**
 * Created by sgfb on 2017/10/26.
 * 个人中心接口
 */
public interface MeInterface {

    /**
     * 设置提现密码
     * in:tradePwd
     * out:{id,customerId,tradePwd}
     */
    String POST_SET_TRADE_PASSWORD="/stdcoin/pwd.json";

    /**
     * 查询视频文件夹
     * in:customerId,id(传就查询某个文件夹里面的，不传查询所有文件夹的)
     * out:[{@link ResponseVideoFolderWithVideo}]
     */
    String POST_VIDEO_FOLDER = "/videoFiles/ls.json";

    /**
     * 查询视频课程（最近）
     * in:customerId
     * out:[{@link ResponseCourseVideo}]
     */
    String POST_COURSE_VIDEO_LATEST = "/organVideo/ls.json";

    /**
     * 添加视频课程
     * in:customerId,title,comment,contactWay,videoStr,isCharge,price,address,latitude,longitude,videoTitlePic,videoFilesId(文件夹id)
     * out:
     */
    String POST_ADD_COURSE_VIDEO = "/organVideo/add.json";

    /**
     * 删除视频文件夹
     * in:id
     * out:
     */
    String POST_DEL_VIDEO_FOLDER = "/videoFiles/del.json";

    /**
     * 创建视频文件夹
     * in:customerId,files
     * out:
     */
    String POST_CREATE_VIDEO_FOLDER = "/videoFiles/add.json";

    /**
     * 教授课程
     * in:id
     * out:
     */
    String GET_TEACH_LIST = "/teacher/ls.json";

    /**
     * 删除课程
     * in:id,teacherTimeId
     * out:
     */
    String POST_LESSON_DEL="/lesson/del.json";

    /**
     * 删除视频
     * in:id
     * out:
     */
    String POST_VIDEO_DELETE="/organVideo/del.json";

    /**
     * 修改评论点赞状态
     * in:customerId,customerPicture,teacherCommentId
     * out:
     */
    String POST_COMMENT_CHANGE_THUMB ="/thumbComment/add.json";

    /**
     * 申请成为老师
     * in:IDCard,areasId,catagoriesId,cityId,classRoom,contactTel,customerId,degreeTypeString,name,provinceId,qCertificate,qualifications,
     * school,sex,teacherAge
     * out:
     */
    String POST_APPLY_IN_TEACHER = "/chgDegree/add.json";

    /**
     * 扫描劵码
     * in:qcode
     * out:[{@link ResponseScanCode}]
     */
    String POST_SCAN_CODE = "/orders/scan.json";

    /**
     * 教师或机构信息
     * in:id
     * out:[{@link ResponseTeachInfo}]
     */
    String POST_TEACH_INFO = "/teachertime/ls.json";

    /**
     * 第三方登录
     * in:oauthType(weibo,qq,weixin),openid,access_token
     * out:{@link ResponseThirdPartyLogin}
     */
    String POST_THIRD_LOGIN = "/customer/thirdLog.json";

    /**
     * 第三方绑定手机号
     * in:phone,varCode
     * out:
     */
    String POST_BINDING_PHONE = "/customer/bindingphone.json";

    /**
     * 获取验证码
     * aes 模式cbc 填充pkcs5padding 密钥 bicikeji@zhangju 向量 zhangju@bicikeji
     * des密钥 bicikeji#zhangju 向量 17608419
     * 都是utf8编码
     * verifyCode 手机号aes加密，checkCode 随机6位des加密
     * in:types(reviseAccount,bindingphone,QuickLogin,register,modifyPwd,forgetPwd),phone,verifyCode(用户输入图形码aes加密),checkCode（机器生成图形码des加密）
     * out:
     */
    String GET_GET_VERIFY_CODE = "/customer/sendRegCode.json";

    /**
     * 绑定其他手机号
     * in:phone,varCode
     * out:
     */
    String GET_REBIND_PHONE = "/customer/reviseAccount.json";

    /**
     * 用户注册
     * in:phone,password,confirmPassword,varCode
     * out:
     */
    String POST_REGISTER = "/customer/register.json";

    /**
     * 用户登录
     * in:phone,password 可选：Rtn_Token 1表示需要token，token认证后，需在header中设X-CustomToken的值为token 如果不要token在之后的接口调用中需要用到用户验证的需要传jsessionid
     * out:{@link com.zhangju.xingquban.interestclassapp.refactor.user.UserVerify}
     */
    String POST_LOGIN = "/customer/login.json";

    /**
     * 快速登录
     * in:phone,varCode,Rtn_Token
     * out:{@link com.zhangju.xingquban.interestclassapp.refactor.user.UserVerify}
     */
    String POST_QUICK_LOGIN = "/customer/quickLogin.json";

    /**
     * 获取用户数据
     * in:
     * out:[{@link com.zhangju.xingquban.interestclassapp.refactor.user.User}]
     */
    String POST_USER_DATA = "/customer/ls.json";

    /**
     * 修改用户数据
     * in:id.可选 phone,sex,age,signame,name,picture,summary,points,balances,username
     * out:
     */
    String POST_CHANGE_USER_DATA = "/customer/add.json";

    /**
     * 修改教师信息
     * in:id,teachAge等
     */
    String POST_CHANGE_TEACH_DATA = "/teachertime/add.json";

    /**
     * 重置密码
     * in:oldPwd,newPwd,cfmNewPwd
     * out:
     */
    String POST_CHANGE_PASS = "/customer/resetPwd.json";

    /**
     * 忘记密码修改
     * in:phone,newPwd,cfmNewPwd,varCode
     * out:
     */
    String POST_FORGOT_PASS = "/customer/forgetPwd.json";

    /**
     * 获取消息列表
     * in:
     * out:[{@link ResponseNotification}]
     */
    String GET_NOTIFY_LIST = "/push/ls.json";

    /**
     * 评论列表
     * in:customerId,resourcesId
     * out:[{@link ResponseComment}]
     */
    String POST_COMMENT_LIST = "/teacherComment/ls.json";

    /**
     * 添加评论的评论
     * in:customerId,customerName,customerPicture,picString,summary,teacherCommentId
     * out:{@link com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseComment.SubComment}
     */
    String POST_COMMENT_SEND="/commentcomment/add.json";

    /**
     * 帮助与反馈
     * in:summary,contact
     * out:{summary,addUserTime,contact,customerId,id}
     */
    String POST_FEEDBACK = "/feedback/add.json";

    /**
     * 钱包余额和汇率
     * in:{@link ResponseWallet}
     */
    String POST_WALLET = "/customer/money2.json";

    /**
     * 订单列表
     * in:isBussiness,status 2-可使用，0-代付款
     * 订单详情
     * in:isBussiness,sid
     * out:[{@link ResponseOrder}]
     */
    String POST_ORDER_LIST = "/orders/lsv5.json";

    /**
     * 取消订单
     * in:sid,isBussiness
     * out:
     */
    String POST_ORDER_CANCEL ="/orders/del.json";

    /**
     * 订单下单
     * in:id,payType(balances-余额，ordersCoupon,aipay-支付宝，qqpay-微信)
     * out:{@link ResponseExchange}
     */
    String POST_ORDER_PAY = "/orders/pay.json";

    /**
     * 查看课程码
     * in:id
     * out:{@link ResponseCheckCourse}
     */
    String POST_ORDER_QRCODE = "/orders/qcode.json";

    /**
     * 收藏列表
     * in:customerId,collectionTypes(2-资源，3-机构/老师)
     * out:[{@link ResponseCollection}]
     */
    String POST_COLLECTION_LIST = "/activityCollection/ls.json";

    /**
     * 删除收藏
     * in:id
     * out:
     */
    String POST_COLLECTION_DELETE = "/activityCollection/del.json";

    /**
     * 发布活动列表
     * in:customerId
     * out:[{@link ResponsePublish}]
     */
    String POST_ACTIVE_PUBLISH_LIST = "/activity/ls.json";

    /**
     * 发布新活动
     * in:title,titlePic,customerId,teacherTimeId,viprice,price,allows,sponsor,partIn,partName,partPhone,partSex,partAge,bondNumber,bondPrice
     * remainNumber,buyNumber,priceType,voteChannel,voteChannelType,FinalTime,longitude,latitude,atie,featured,schedules,notes,statement,catagoryId
     * catagoryName,place
     * out:
     */
    String POST_ACTIVE_PUBLISH = "/activity/add.json";

    /**
     * 获取活动类别列表
     * in:
     * out:[{@link ResponseActiveCategory}]
     */
    String GET_CATEGORY_LIST = "/catagories/lsRoot.json";

    /**
     * 机构简介
     * in:customerId
     * out:[{@link ResponseOrgProfile}]
     */
    String POST_ORG_PROFILE = "/organ/ls.json";

    /**
     * 修改机构简介
     * in:id(修改机构简介，如果是添加置空),customerId,intro(使用#拼接文字和图片)
     * out:{@link ResponseOrgProfile}
     */
    String POST_ORG_CHANGE_PROFILE = "/organ/add.json";

    /**
     * 增加课程
     * in:allows,areasName,cityName,courses,customerId,descript,isCantry,lat,lng,methodType,name,picture,price,
     * provinceName,region,resId,summary,teacherTimeId,timelength,vipPrice
     * out:
     */
    String POST_ADD_COURSE = "/lesson/add.json";

    /**
     * 机构管理中的视频或相册
     * in:customerId,isPic(0-图片,2-视频),type(1-机构相册，2-视频课程),分页
     * out:[{@link ResponseVideoAlbum}]
     */
    String POST_MEDIA_LIST = "/organAlbumFiles/ls.json";

    /**
     * 机构管理中添加或修改视频和相册
     * in:title,comment,isPic,picVideo,latitude,longitude,videoTitlePic,intro,customerId,type
     * out:
     */
    String POST_MEDIA_ADD = "/organAlbum/add.json";

    /**
     * 机构管理中的视频或图像删除
     * in:ids (用逗号分隔)
     * out:
     */
    String POST_MEDIA_DELETE = "/organAlbumFiles/delete.json";

    /**
     * 我的资源列表
     * in:customerId,isCharge(0不收费（默认）,1收费)
     * out:[{@link ResponseResource}]
     */
    String POST_RESOURCE_LIST = "/myResources/ls.json";

    /**
     * 资源添加
     * in:title,customerId,types(picture（图片），video（视频），article（文章），audio（音频）),titlePicture,price,isCharge,
     * muitlUploadPicture,videoUrl,address,latitude,longitude,summary
     * out:
     */
    String POST_ADD_RESOURCE = "/myResources/add.json";

    /**
     * 资源删除
     * in:id
     * out:
     */
    String POST_DELETE_RESOURCE = "/myResources/del.json";

    /**
     * 我的直播间
     * in:
     * out:{@link ResponseMyLiveRoom}
     */
    String POST_MY_LIVE_ROOM = "/chatUser/get.json";

    /**
     * 粉丝贡献榜
     * in:
     * out:{@link ResponseFansReward} 全局中attachData有个stdCoin为全部贡献兴趣币数
     */
    String POST_FANS_REWARD = "/chatUser/rank.json";

    /**
     * 我发布的
     * in:
     * out:[{@link ResponsePublishHistory}]
     */
    String POST_LIVE_PUBLISH_HISTORY = "/liveVdo/ls.json";

    /**
     * 删除发布的直播
     * in:pub(false),id
     * out:
     */
    String POST_DELETE_LIVE_PUIBLISH = "/liveVdo/pub.json";

    /**
     * 给自己发布的直播点赞
     * in:subjectId,comtType(liveVdo)
     * out:
     */
    String POST_LINK = "/upvote/add.json";

    /**
     * 场控列表
     * in:types(manager)
     * out:[{@link ResponseLiveroomControl}]
     */
    String POST_LIVE_ROOM_FIELD_CONTROL = "/roomRoles/ls.json";

    /**
     * 我的直播间黑名单
     * in:chatGroup.syskey(1)
     * out:
     */
    String POST_LIVE_ROOM_BLACK_LIST = "/chatGroupList/lsSelf.json";

    /**
     * 搜索其他用户
     * in:allStringQuery,types(manager),isFilterRoomRole
     * out:[{@link ChatUser}]
     */
    String POST_LIVE_ROOM_SEARCH = "/chatUser/search.json";

    /**
     * 更换场控
     * in:targetAccid,command(增加manager,删除common)
     * out:
     */
    String POST_CHANGE_FIELD_CONTROL = "/chatroomRoles/add.json";

    /**
     * 关注列表
     * in:chatUserId
     * out:[{@link ResponseFocusUser}]
     */
    String POST_FOCUS_LIST = "/roomFollows/follow.json";

    /**
     * 粉丝列表
     * in:chatUserId
     * out:[{@link ResponseFans}]
     */
    String POST_FANS_LIST = "/roomFollows/fans.json";

    /**
     * 关注和解除关注
     * in:targetAccid
     * out:
     */
    String POST_FOCUS_AND_DELETE = "/roomFollows/add.json";

    /**
     * 开通会员
     * in:amount,payType(aipay支付宝,uppay银联,qqpay徽信支付,balances余额)
     * out:
     */
    String POST_OPEN_VIP = "/members/pay.json";

    /**
     * 我的票劵列表
     * in:customerId,status 1,2待参与,3已完成,-3已退票
     * out:[{@link ResponseTicket}]
     */
    String POST_MY_TICKET_LIST = "/stuActivityOrders/ls.json";

    /**
     * 活动模块关注列表
     * in:customerId,collectionTypes(1我关注的活动)
     * out[{@link ResponseActiveFocus}]
     */
    String POST_ACTIVE_MY_FOCUS = "/activityCollection/ls.json";

    /**
     * 活动模块我的参与列表
     * in:customerId
     * out:[{@link ResponseMyTakePartin}]
     */
    String POST_ACTIVE_MY_TAKE_PART_IN = "/activityVote/ls.json";

    /**
     * 活动模块我的发布列表
     * in:customerId
     * out:[{@link ResponseActivePublished}]
     */
    String POST_ACTIVE_PUBLISHED = "/activity/ls.json";

    /**
     * 活动收入
     * in:customerId,activityId(活动id，查询单个活动的，不传，查所有的活动的收入)
     * out:{@link ResponseActiveIncome}
     */
    String POST_ACTIVE_INCOME = "/activityIncome/ls.json";

    /**
     * 活动验票
     * in:qcode
     * out:
     */
    String POST_CHECK_TICKET = "/activityOrders/scan.json";
    /***
     * 课程验证
     * int qcode
     */
    String POST_CHECK_VIP_CODE = "/orders/scan.json";

    /**
     * 身份验证
     * in:checked,customerId,teacherTimeId,uidCard,uidCardImg,username
     * out:
     */
    String POST_QUALITY_REALNAME = "/realnameAuth/add.json";

    /**
     * 资格认证
     * in:checked,certImg,companyName,customerId,degreeId,degreeName,qCertificate,teacherTimeId
     * out:
     */
    String POST_QUALITY_QC = "/qcAuth/add.json";

    /**
     * 平台认证
     * in:customerId,teacherTimeId,effDate,endDate,name,parta,pic,signDate,sno,status
     * out:
     */
    String POST_QUALITY_PLATFORM = "/contract/add.json";

    /**
     * 教授课程
     * in:id,catagoriesId(多字段用‘，’分隔)
     * out:
     */
    String GET_CHANGE_TEACH_COURSES = "/teacher/add.json";

    /**
     * 课程详情
     * in:id
     * out:
     */

    String POST_COURSE_DETAIL = "/lesson/ls.json";

    /**
     * vip商户--信息平台查询
     */
    String POST_PLATFORM_SEARCH = "/ipm/index.json";
    /**
     * 生源平台
     */
    String POST_STUDENT_INTRODUCE = "/stdResource/ls.json";


    /**
     * 充值
     * in:currencyRateId,keyname(aipay支付宝,uppay银联,qqpay徽信支付,balances余额)
     * out:{@link ResponseExchange}
     */
    String POST_EXCHANGE_FUNBEAN="/payv2/topup.json";

    /**
     * 虚拟币人民币等互相兑换
     * in:currencyId,currencyRateId,fromAmount,toCurrencyId
     * out:{@link ResponseWallet}
     */
    String POST_EXCHANGE ="/stdchg/add.json";

    /**
     * 优惠劵列表
     * in:
     * out:[{@link ResponseCoupon}]
     */
    String POST_COUPON_LIST="/uodetail/ls.json";

    /**
     * 交易记录列表
     * in:pageIndex,pageSize,coinId
     * out:[{@link ResponseTransactionHistory}]
     */
    String POST_TRANSACTION_HISTORY_LIST="/stdLedger/lsPhone.json";

    /**
     * 已绑定的支付提现账户
     * in:
     * out:[{@link ResponseBoundAccount}]
     */
    String POST_BOUND_ACCOUNT_LIST ="/coinCard/ls.json";

    /**
     * 解除支付提现账户绑定
     * in:id
     * out:
     */
    String POST_UNBIND_ACCOUNT ="/coinCard/del.json";

    /**
     * 绑定支付提现账户
     * in:account,keyname,username
     * out:
     */
    String POST_BIND_PAY="/coinCard/add.json";

    /**
     * 充值
     * in:amount,paytype(qqpay,aipay)
     * out:{@link ResponseExchange}
     */
    String GET_EXCHANGE="/deposit/add.json";

    /**
     * 提现
     * in:amount,coinCardId,currencyId,tradePwd
     * out:
     */
    String POST_WITHDRAW="/drawCash/add.json";

    /**
     * 支付方式
     * in:modelName(orders)
     * out:[{@link ResponsePayType}]
     */
    String POST_PAY_TYPE="/paytype/ls.json";

    /**
     * in:
     * out:[{@link ResponseActive}]
     */
    String POST_ACTIVE_LIST_HOT="/activity/hots.json";

    /**
     * in:catagoriesId
     * out:[{@link ResponseActive}]
     */
    String POST_ACTIVE_LIST="/activity/ls.json";
}