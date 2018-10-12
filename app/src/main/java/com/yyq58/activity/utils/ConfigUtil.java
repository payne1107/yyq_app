package com.yyq58.activity.utils;


import com.yyq58.activity.application.MyApplication;


/**
 * Created by Administrator on 2017/9/29 0029.
 * 网络地址存放
 */

public class ConfigUtil {
    public static long TenYears8 = 10L * 365 * 1000 * 60 * 60 * 24L * 80;
    public static long TenYears = 10L * 365 * 1000 * 60 * 60 * 24L * 3;
    public static String sessionId;
    public static final String SAVE_CITY_ID = "city_id";
    public static final String SAVE_CITY_NAME = "city_name";
    /***
     * 分享的链接地址
     */
    public static final String INVTITE_SHARE_URL = "https://wxapi.yyq58.com/share/modules/notice-details.html?noticeId=";

    /****
     * 注册短信验证码接口
     */
    public static final String GET_REGISTER_SMS_URL = MyApplication.REQUEST_URL + "appv1Consumer/smsReg.do";
    public static final int GET_REGISTER_SMS_URL_ACTION = 1;
    /*****
     * 注册接口
     */
    public static final String REGISTER_URL = MyApplication.REQUEST_URL + "appv1Consumer/appRegister.do";
    public static final int REGISTER_URL_ACTION = 2;
    /****
     * 登陆接口
     */
    public static final String LOGIN_URL = MyApplication.REQUEST_URL + "applogin/userLogin.do";
    public static final int LOGIN_URL_ACTION = 3;
    /****
     * 上传头像和图片 avatar/zone
     */
    public static final String UPLOAD_PIC_URL = MyApplication.REQUEST_URL + "appv1zone/upload.do";
    public static final int UPLOAD_PIC_URL_ACTION = 4;
    /***
     * 获取我的才艺 台前和幕后
     */
    public static final String QUERY_MINE_ACQUIREMENT_URL = MyApplication.REQUEST_URL + "appv1consumerlabel/getAllLabel.do";
    public static final int QUERY_MINE_ACQUIREMENT_URL_ACTION = 5;
    /****
     * 忘记密码 ->获取验证码
     */
    public static final String GET_REMEBER_PWD_VERITIFY_CODE_URL = MyApplication.REQUEST_URL + "appv1Consumer/smsPwd.do";
    public static final int GET_REMEBER_PWD_VERITIFY_CODE_URL_ACTION = 6;
    /****
     * 找回密码--->重置密码
     */
    public static final String RESET_PWD_URL = MyApplication.REQUEST_URL + "appv1Consumer/resetPwd.do";
    public static final int RESET_PWD_URL_ACTION = 7;
    /***
     * 获取首页通告列表
     */
    public static final String QUERY_APPV1_NOTICE_LIST_URL = MyApplication.REQUEST_URL + "appv1notice/list.do";
    public static final int QUERY_APPV1_NOTICE_LIST_URL_ACTION = 8;
    /****
     * 发起抢单
     */
    public static final String SAVE_QIANG_DAN_URL = MyApplication.REQUEST_URL + "appv1notice/savenBaoMing.do";
    public static final int SAVE_QIANG_DAN_URL_ACTION = 9;
    /****
     * 通告详情
     */
    public static final String QUERY_NOTICE_DETAILS_URL = MyApplication.REQUEST_URL + "appv1notice/findById.do";
    public static final int QUERY_NOTICE_DETAILS_URL_ACTION = 10;
    /****
     * 推荐公告
     */
    public static final String QUERY_RECOMMEND_NOTICE_LIST_URL = MyApplication.REQUEST_URL + "appv1notice/recommendNotice.do";
    public static final int QUERY_RECOMMEND_NOTICE_LIST_URL_ACTION = 11;
    /****
     * 历史纪录 --》 通告列表 & 求单列表
     */
    public static final String QUERY_NOTICE_LIST_URL = MyApplication.REQUEST_URL + "appv1notice/getNoticeByConsumerId.do";
    public static final int QUERY_NOTICE_LIST_URL_ACTION = 12;
    /****
     * 编辑通告
     */
    public static final String EDIT_NOTICE_URL = MyApplication.REQUEST_URL + "appv1notice/editNotice.do";
    public static final int EDIT_NOTICE_URL_ACTION = 13;
    /****
     * 历史纪录---》删除通告
     */
    public static final String DELETE_NOTICE_URL = MyApplication.REQUEST_URL + "appv1notice/deteteById.do";
    public static final int DELETE_NOTICE_URL_ACTION = 14;
    /****
     * 历史纪录--》设为满员
     */
    public static final String UPDATE_NOTICE_MANYUAN_URL = MyApplication.REQUEST_URL + "appv1notice/updateManYuan.do";
    public static final int UPDATE_NOTICE_MANYUAN_URL_ACTION = 15;
    /***
     * 抢单艺人列表
     */
    public static final String QIANG_DAN_LISTS_BY_NOTICEID_URL = MyApplication.REQUEST_URL + "appv1notice/getBaoMingListsByNoticeId.do";
    public static final int QIANG_DAN_LISTS_BY_NOTICEID_URL_ACTION = 16;
    /***
     * 推荐艺人列表
     */
    public static final String QUERY_TUIJIAN_LISTS_URL = MyApplication.REQUEST_URL + "appConsumer/getRecommendConsumer.do";
    public static final int QUERY_TUIJIAN_LISTS_URL_ACTION = 17;
    /****
     *  求单列表
     */
    public static final String QUERY_QIUDAN_LIST_URL = MyApplication.REQUEST_URL + "appv1qiudan/list.do";
    public static final int QUERY_QIUDAN_LIST_URL_ACTION = 18;
    /***
     * 求单编辑
     */
    public static final String EDIT_QIUDAN_URL = MyApplication.REQUEST_URL + "appv1qiudan/update.do";
    public static final int EDIT_QIUDAN_URL_ACTION = 19;
    /***
     *求单--->删除
     */
    public static final String DELETE_QIUDAN_URL = MyApplication.REQUEST_URL + "appv1qiudan/delete.do";
    public static final int DELETE_QIUDAN_URL_ACTION = 20;
    /***
     * 抢单列表
     */
    public static final String QUERY_QIANGDAN_LISTS_BY_USERID_URL = MyApplication.REQUEST_URL + "appv1notice/getBaoMingListsByConsumerID.do";
    public static final int QUERY_QIANGDAN_LISTS_BY_USERID_URL_ACTION = 21;
    /****
     * 发布通告
     */
    public static final String RELEASE_NOTICE_URL = MyApplication.REQUEST_URL + "appv1notice/publishNotice.do";
    public static final int RELEASE_NOTICE_URL_ACTION = 22;

    /****
     * Vip刷新
     */
    public static final String REFRESH_VIP_URL = MyApplication.REQUEST_URL + "appVIP/refurbish.do";
    public static final int REFRESH_VIP_URL_ACTION = 23;
    /****
     * 我的粉丝
     */
    public static final String QUERY_FANS_LIST_URL = MyApplication.REQUEST_URL + "appv1Consumer/getFans.do";
    public static final int QUERY_FANS_LIST_URL_ACTION = 24;
    /***
     * 个人详情
     */
    public static final String QUERY_PERSON_INFO_URL = MyApplication.REQUEST_URL + "appv1Consumer/getConsumerInfo.do";
    public static final int QUERY_PERSON_INFO_URL_ACTION = 25;
    /***
     * 绑定银行卡
     */
    public static final String BIND_BANK_CARD_URL = MyApplication.REQUEST_URL + "appConsumer/updateBankInfo.do";
    public static final int BIND_BANK_CARD_URL_ACTION = 26;
    /***
     * 账单明细
     */
    public static final String QUERY_BILL_DETAILS_URL = MyApplication.REQUEST_URL + "appdetail/getDetailList.do";
    public static final int QUERY_BILL_DETAILS_URL_ACTION = 27;
    /**
     * 我关注的人
     */
    public static final String QUERY_MINE_ATTENTION_URL = MyApplication.REQUEST_URL + "appv1Consumer/getAttention.do";
    public static final int QUERY_MINE_ATTENTION_URL_ACTION = 28;
    /***
     * 取消关注艺人
     */
    public static final String CANCEL_ATTENTION_URL = MyApplication.REQUEST_URL + "appv1Consumer/cancelAttention.do";
    public static final int CANCEL_ATTENTION_URL_ACTION = 29;
    /***
     * 我的动态列表 传1查作品 不传查动态
     */
    public static final String QUERY_MINE_DAYNIC_URL = MyApplication.REQUEST_URL + "appv1zone/list.do";
    public static final int QUERY_MINE_DAYNIC_URL_ACTION = 30;
    /***
     * 修改用户信息
     */
    public static final String UPDATE_PERSON_INFO_URL = MyApplication.REQUEST_URL + "appv1Consumer/updateInfo.do";
    public static final int UPDATE_PERSON_INFO_URL_ACTION = 31;
    /***
     * 修改用户标签
     */
    public static final String UPDATE_USER_LABEL_URL = MyApplication.REQUEST_URL + "appv1consumerlabel/consumerLable.do";
    public static final int UPDATE_USER_LABEL_URL_ACTION = 32;
    /***
     * 关注艺人
     */
    public static final String ATTENTION_YR_URL = MyApplication.REQUEST_URL + "appv1Consumer/setAttention.do";
    public static final int ATTENTION_YR_URL_ACTION = 33;
    /***
     * 提现
     */
    public static final String WITHDRAW_BALANCE_URL = MyApplication.REQUEST_URL + "appcashrecord/savecashrecord.do";
    public static final int WITHDRAW_BALANCE_URL_ACTION = 34;
    /****
     * 我的订单-->数量展示
     */
    public static final String QUERY_MINE_ORDER_COUNT_URL = MyApplication.REQUEST_URL + "appv1noticeorder/findOrderCount.do";
    public static final int QUERY_MINE_ORDER_COUNT_URL_ACTION = 35;
    /****
     * 我的订单-->待支付 & 完成
     */
    public static final String QUERY_MINE_ORDER_LIST_FOR_PAY_AND_COMPLETE_URL = MyApplication.REQUEST_URL + "appv1noticeorder/findNotPayOrder.do";
    public static final int QUERY_MINE_ORDER_LIST_FOR_PAY_AND_COMPLETE_URL_ACTION = 36;
    /****
     * 我的订单-->申请退款
     */
    public static final String QUERY_MINE_ORDER_LIST_FOR_REFUND_URL = MyApplication.REQUEST_URL + "appv1refund/list.do";
    public static final int QUERY_MINE_ORDER_LIST_FOR_REFUND_URL_ACTION = 37;
    /***
     * 我的订单-->待处理
     */
    public static final String QUERY_MINE_ORDER_LIST_FOR_PENDING_URL = MyApplication.REQUEST_URL + "appv1noticeorder/findTransaction.do";
    public static final int QUERY_MINE_ORDER_LIST_FOR_PENDING_URL_ACTION = 38;
    /***
     * 我的订单--》抢单 -已定档 & 催付款 & 完成
     */
    public static final String QUERY_MINE_ORDER_QD_PAY_AND_NOT_PAY_URL = MyApplication.REQUEST_URL + "appv1noticeorder/findQiangDanNotPayOrderToPay.do";
    public static final int QUERY_MINE_ORDER_QD_PAY_AND_NOT_PAY_URL_ACTION = 39;
    /***
     * 我的订单的--》抢单 -待定档
     */
    public static final String QUERY_MINE_ORDER_QD_PENDING_URL = MyApplication.REQUEST_URL + "appv1noticeorder/findQiangDanTransaction.do";
    public static final int QUERY_MINE_ORDER_QD_PENDING_URL_ACTION = 40;
    /****
     * 档期发布
     */
    public static final String ADD_CONSUMER_SCHEDULE_URL = MyApplication.REQUEST_URL + "appv1consumerschedule/save.do";
    public static final int ADD_CONSUMER_SCHEDULE_URL_ACTION = 41;
    /***
     * 按月查询档期
     */
    public static final String QUERY_BY_MONTH_SCHEDULE_URL = MyApplication.REQUEST_URL + "appv1consumerschedule/findByMonth.do";
    public static final int QUERY_BY_MONTH_SCHEDULE_URL_ACTION = 42;
    /****
     * 按天查询档期
     */
    public static final String QUERY_BY_DAY_SCHEDULE_URL = MyApplication.REQUEST_URL + "appv1consumerschedule/findByDay.do";
    public static final int QUERY_BY_DAY_SCHEDULE_URL_ACTION = 43;
    /***
     * 档期列表删除
     */
    public static final String DELETE_SCHEDULE_LIST_URL = MyApplication.REQUEST_URL + "appv1consumerschedule/delete.do";
    public static final int DELETE_SCHEDULE_LIST_URL_ACTION = 44;
    /***
     * 档期编辑
     */
    public static final String EDIT_SCHEDULE_LIST_URL = MyApplication.REQUEST_URL + "appv1consumerschedule/edit.do";
    public static final int EDIT_SCHEDULE_LIST_URL_ACTIOn = 45;
    /****
     * 每日登录获取积分
     */
    public static final String QUERY_JIFEN_BY_LOGIN_URL = MyApplication.REQUEST_URL + "appv1Consumer/getJifenByLogin.do";
    public static final int QUERY_JIFEN_BY_LOGIN_URL_ACTION = 46;
    /***
     * 分享得积分
     */
    public static final String QUERY_GET_SHARE_JIFEN_URL = MyApplication.REQUEST_URL + "appv1Consumer/getJifen.do";
    public static final int QUERY_GET_SHARE_JIFEN_URL_ACTION = 47;




}
