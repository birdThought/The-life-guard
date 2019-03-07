package com.lifeshs.service.terminal.app.serve;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.exception.common.BaseException;
import com.lifeshs.common.exception.common.OperationException;

public interface IAppServeService {

    /**
     * 获取服务列表
     * 
     * @author yuhang.weng
     * @DateTime 2016年10月11日 下午2:13:28
     *
     * @param json
     * @return
     */
    JSONObject getServeList(String json);

    /**
     * 获取推荐机构列表
     * 
     * @author yuhang.weng
     * @DateTime 2016年10月11日 下午2:13:37
     *
     * @param json
     * @return
     */
    JSONObject getRecommendOrgList(String json);

    /**
     * 获取服务机构列表
     * 
     * @author yuhang.weng
     * @DateTime 2016年10月11日 下午2:13:56
     *
     * @param json
     * @return
     */
    JSONObject getOrgList(String json);

    /**
     * 获取服务机构
     * 
     * @author yuhang.weng
     * @DateTime 2016年10月11日 下午2:14:08
     *
     * @param json
     * @return
     */
    JSONObject getOrg(String json);

    /**
     * 获取机构服务
     * 
     * @author yuhang.weng
     * @DateTime 2016年10月11日 下午2:14:19
     *
     * @param json
     * @return
     */
    JSONObject getOrgServe(String json);

    /**
     * 获取课堂群组列表
     * 
     * @author yuhang.weng
     * @DateTime 2017年3月7日 下午4:14:16
     *
     * @param json
     * @return
     */
    JSONObject getLessonGroup(String json);

    /**
     * 获取课堂服务详情
     * 
     * @author yuhang.weng
     * @DateTime 2017年3月7日 下午4:14:17
     *
     * @param json
     * @return
     */
    JSONObject getOrgLessonServe(String json);

    /**
     * 获取服务师信息
     * 
     * @author yuhang.weng
     * @DateTime 2016年10月11日 下午2:14:28
     *
     * @param json
     * @return
     */
    JSONObject getServeUser(String json);

    /**
     * 获取分类标签
     * 
     * @author yuhang.weng
     * @DateTime 2016年10月14日 上午10:29:04
     *
     * @param json
     * @return
     */
    JSONObject getClassifyTags(String json);

    /**
     * 获取服务标签
     * 
     * @author yuhang.weng
     * @DateTime 2016年10月14日 上午10:29:15
     *
     * @param json
     * @return
     */
    JSONObject getServiceTags(String json);

    /**
     * 查找服务机构列表
     * 
     * @author yuhang.weng
     * @DateTime 2016年10月17日 下午2:18:21
     *
     * @param json
     * @return
     */
    JSONObject queryOrgList(String json);

    /**
     * 通过环信账号查询服务师信息
     * 
     * @author yuhang.weng
     * @DateTime 2016年10月26日 下午1:44:36
     *
     * @param json
     * @return
     */
    JSONObject getServeUserByHuanxinAcount(String json);

    /**
     * 获取群组所属的服务信息
     * 
     * @author yuhang.weng
     * @DateTime 2016年11月22日 上午9:19:57
     *
     * @param json
     * @return
     */
    JSONObject getGroupServeInfo(String json);

    /**
     * 获取图片
     * 
     * @author yuhang.weng
     * @DateTime 2016年12月13日 下午2:06:45
     *
     * @param json
     * @return
     */
    JSONObject getImages(String json);

    /**
     * 获取课堂设置信息
     * 
     * @author yuhang.weng
     * @DateTime 2017年3月7日 下午7:45:20
     *
     * @param json
     * @return
     */
    JSONObject getLessonDetail(String json);

    /**
     * 提交订单
     * 
     * @author yuhang.weng
     * @DateTime 2016年10月19日 下午7:42:51
     *
     * @param json
     * @return
     */
    JSONObject addOrder(String json);

    /**
     * 完成订单
     * 
     * @author yuhang.weng
     * @DateTime 2016年10月19日 下午7:44:10
     *
     * @param json
     * @return
     */
    JSONObject finishOrder(String json);

    /**
     * 获取用户订单列表
     * 
     * @author yuhang.weng
     * @DateTime 2016年10月19日 下午7:43:33
     *
     * @param json
     * @return
     */
    JSONObject getOrderList(String json);

    /**
     * 获取用户订单
     * 
     * @author yuhang.weng
     * @DateTime 2016年10月19日 下午7:43:35
     *
     * @param json
     * @return
     */
    JSONObject getOrder(String json);

    /**
     * 取消订单
     * 
     * @author yuhang.weng
     * @DateTime 2016年10月22日 下午4:08:45
     *
     * @param json
     * @return
     */
    JSONObject cancelOrder(String json);

    /**
     * 获取已购买服务列表
     * 
     * @author yuhang.weng
     * @DateTime 2016年10月22日 下午4:08:56
     *
     * @param json
     * @return
     */
    JSONObject getUserServeList(String json);

    /**
     * 获取已购买服务详细内容
     * 
     * @author yuhang.weng
     * @DateTime 2016年10月22日 下午4:09:09
     *
     * @param json
     * @return
     */
    JSONObject getUserServeDetail(String json);

    /**
     * 获取订单签名
     * 
     * @author yuhang.weng
     * @DateTime 2016年10月31日 下午2:22:21
     *
     * @param json
     * @return
     */
    JSONObject getOrderInfo(String json) throws BaseException;
    
    /**
     * 
     *  获取订单签名(可扩展)
     *  @author liaoguo
     *  @DateTime 2018年6月19日 下午4:51:58
     *
     *  @param json
     *  @return
     *  @throws BaseException
     */
    JSONObject getOrderInfoNew(String json) throws BaseException;
    
    /**
     * 
     *  获取订单签名(可扩展)
     *  @param json
     *  @return
     *  @throws BaseException
     */
    JSONObject getOrderInfoNew(Map<String, Object> map) throws BaseException;
    
    /**
     * 查看服务是否过期
     * 
     * @author yuhang.weng
     * @DateTime 2016年11月8日 下午2:36:15
     *
     * @param json
     * @return
     */
    JSONObject checkServeValid(String json);

    /**
     * 减少服务次数
     * 
     * @author yuhang.weng
     * @DateTime 2016年11月16日 上午11:32:54
     *
     * @param json
     * @return
     */
    JSONObject reduceServeTime(String json);
    
    /**
     *  退出健康课堂
     *  @author yuhang.weng 
     *	@DateTime 2017年3月9日 下午3:34:22
     *
     *  @param json
     *  @return
     */
    JSONObject outLessonGroup(String json);
    
    /**
     *  提交短信充值订单
     *  @author yuhang.weng 
     *	@DateTime 2017年4月28日 下午4:33:23
     *
     *  @param json
     *  @return
     */
    JSONObject addSMSOrder(String json);
    
    /**
     *  获取剩余短信数量
     *  @author yuhang.weng 
     *  @DateTime 2017年4月28日 上午11:36:21
     *
     *  @param json
     *  @return
     */
    JSONObject getSMSRemain(String json);
    
    /**
     *  输入短信充值邀请码
     *  @author yuhang.weng 
     *  @DateTime 2017年4月28日 上午11:36:18
     *
     *  @param json
     *  @return
     */
    JSONObject entrySMSInvication(String json) throws OperationException;
    
    /**
     *  获取短信充值记录列表
     *  @author yuhang.weng 
     *  @DateTime 2017年4月28日 下午1:39:52
     *
     *  @param json
     *  @return
     */
    JSONObject getSMSRechargeRecordList(String json);
    
    JSONObject getFreeMeasureSiteList(String json);
}
