package com.lifeshs.service.terminal.app.record;

import com.alibaba.fastjson.JSONObject;

/**
 * 健康档案
 * 
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年3月18日 下午5:37:11
 */
public interface IAppRecordService {

    /**
     * 获取病历列表
     * 
     * @author wenxian.cai
     * @DateTime 2016年8月9日上午11:34:40
     * @serverComment
     * @param json
     */
    JSONObject getMedicalRecordsList(String json) throws Exception;

    /**
     * 获取病历详细
     * 
     * @author wenxian.cai
     * @DateTime 2016年8月9日上午11:37:54
     * @serverComment
     * @param
     */
    JSONObject getMedicalRecords(String json) throws Exception;

    /**
     * 添加个人病历
     * 
     * @author wenxian.cai
     * @DateTime 2016年8月9日上午11:37:54
     * @serverComment
     * @param
     */
    JSONObject addMedicalRecords(String json) throws Exception;

    /**
     * 修改个人病历
     * 
     * @author wenxian.cai
     * @DateTime 2016年8月9日上午11:37:54
     * @serverComment
     * @param
     */
    JSONObject modifyMedicalRecords(String json) throws Exception;

    /**
     * 删除个人病历
     * 
     * @author wenxian.cai
     * @DateTime 2016年8月9日上午11:37:54
     * @serverComment
     * @param
     */
    JSONObject delMedicalRecords(String json) throws Exception;

    /**
     * 添加个人病程
     * 
     * @author wenxian.cai
     * @DateTime 2016年8月9日上午11:37:54
     * @serverComment
     * @param
     */
    JSONObject addMedicalCourse(String json) throws Exception;

    /**
     * 修改个人病程
     * 
     * @author wenxian.cai
     * @DateTime 2016年8月9日上午11:37:54
     * @serverComment
     * @param
     */
    JSONObject modifyMedicalCourse(String json) throws Exception;

    /**
     * 删除个人病程
     * 
     * @author wenxian.cai
     * @DateTime 2016年8月9日上午11:37:54
     * @serverComment
     * @param
     */
    JSONObject delMedicalCourse(String json) throws Exception;

    /**
     * 获取所有科室
     * 
     * @author yuhang.weng
     * @DateTime 2016年12月12日 上午10:25:15
     *
     * @param json
     * @return
     */
    JSONObject getMedicalDepartments(String json);

    /**
     * 获取体检报告列表
     * 
     * @author wenxian.cai
     * @DateTime 2016年8月9日上午11:37:54
     * @serverComment
     * @param
     */
    JSONObject getPhysicalsList(String json) throws Exception;

    /**
     * 获取体检报告详细
     * 
     * @author wenxian.cai
     * @DateTime 2016年8月9日上午11:37:54
     * @serverComment
     * @param
     */
    JSONObject getPhysicals(String json) throws Exception;

    /**
     * 添加体检报告
     * 
     * @author wenxian.cai
     * @DateTime 2016年8月9日上午11:37:54
     * @serverComment
     * @param
     */
    JSONObject addPhysicals(String json) throws Exception;

    /**
     * 修改体检报告
     * 
     * @author wenxian.cai
     * @DateTime 2016年8月9日上午11:37:54
     * @serverComment
     * @param
     */
    JSONObject modifyPhysicals(String json) throws Exception;

    /**
     * 删除体检报告
     * 
     * @author wenxian.cai
     * @DateTime 2016年8月9日上午11:37:54
     * @serverComment
     * @param
     */
    JSONObject delPhysicals(String json) throws Exception;

    /**
     * 获取健康日记能量列表
     * 
     * @author yuhang.weng
     * @DateTime 2016年10月22日 上午9:12:01
     *
     * @param json
     * @return
     */
    JSONObject getDietKcal(String json);

    /**
     * 获取健康日记能量列表
     * 
     * @author yuhang.weng
     * @DateTime 2017年3月18日 下午5:40:27
     *
     * @param json
     * @return
     */
    JSONObject getDiaryEnergy(String json);

    /**
     * 获取健康日记(分页)
     * 
     * @author yuhang.weng
     * @DateTime 2016年10月28日 上午11:42:09
     *
     * @param json
     * @return
     */
    JSONObject getDiets(String json);

    /**
     * 获取健康日记详细
     * 
     * @author wenxian.cai
     * @DateTime 2016年8月9日上午11:37:54
     * @serverComment
     * @param
     */
    JSONObject getDiet(String json) throws Exception;

    /**
     * 获取健康日记
     * 
     * @author yuhang.weng
     * @DateTime 2017年2月14日 上午11:34:18
     *
     * @param json
     * @return
     */
    JSONObject getHealthDiary(String json);

    /**
     * 添加健康日记
     * 
     * @author wenxian.cai
     * @DateTime 2016年8月9日上午11:37:54
     * @serverComment
     * @param
     */
    JSONObject addDiet(String json) throws Exception;

    /**
     * 添加运动
     * 
     * @author yuhang.weng
     * @DateTime 2017年2月14日 上午11:34:36
     *
     * @param json
     * @return
     */
    JSONObject addSport(String json);

    /**
     * 获取所有运动
     * 
     * @author yuhang.weng
     * @DateTime 2017年2月14日 上午11:34:26
     *
     * @param json
     * @return
     */
    JSONObject getAllSport(String json);

    /**
     * 获取全部食物类型
     * 
     * @author wenxian.cai
     * @DateTime 2016年8月20日上午10:24:41
     * @serverComment
     * @param
     */
    JSONObject getFoodType(String json) throws Exception;

    /**
     * 获取食物详细
     * 
     * @author wenxian.cai
     * @DateTime 2016年8月20日上午11:01:33
     * @serverComment
     * @param
     */
    JSONObject getFood(String json) throws Exception;

    /**
     * 获取食物类型，以及食物详细内容
     * 
     * @author yuhang.weng
     * @DateTime 2016年10月25日 下午5:57:44
     *
     * @param json
     * @return
     */
    JSONObject getFoodTypeWithFoods(String json);

    /**
     * 修改健康日记(暂不实现)
     * 
     * @author wenxian.cai
     * @DateTime 2016年8月9日上午11:37:54
     * @serverComment
     * @param
     */
    JSONObject modifyDiet(String json) throws Exception;

    /**
     * 删除健康日记
     * 
     * @author wenxian.cai
     * @DateTime 2016年8月9日上午11:37:54
     * @serverComment
     * @param
     */
    JSONObject delDiet(String json) throws Exception;
}
