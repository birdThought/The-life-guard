package com.lifeshs.service1.measure;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lifeshs.common.constants.common.HealthPackageType;
import com.lifeshs.common.exception.common.BaseException;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.po.measure.MeasureAnalysisPO;
import com.lifeshs.service1.page.Paging;

/**
 *  健康测量数据批注service
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年10月30日 下午5:35:16
 */
public interface MeasureAnalysisService {

    /**
     *  统计未读批注数量
     *  @author yuhang.weng 
     *  @DateTime 2017年10月30日 下午4:49:09
     *
     *  @param userId 用户id
     *  @return
     */
    int countUnReadAnalysis(int userId);
    
    /**
     *  获取批注列表
     *  @author yuhang.weng 
     *  @DateTime 2017年10月30日 下午4:49:26
     *
     *  @param userId 用户id
     *  @param measureDate 测量日期
     *  @return
     */
    List<MeasureAnalysisPO> listAnalysis(int userId, Date measureDate);
    
    /**
     *  获取批注列表（分页）
     *  @author yuhang.weng 
     *  @DateTime 2017年10月30日 下午5:18:48
     *
     *  @param curPage 当前页码
     *  @param pageSize 页面大小
     *  @param userId 用户id（可以不填写）
     *  @param measureDate 测量日期（可以不填写）
     *  @param healthProduct 健康设备（可以不填写）
     *  @param customerUserId 客服id（可以不填写）
     *  @param read 是否已读（可以不填写）
     *  @param reply 是否已回复（可以不填写）
     *  @return
     */
    Paging<MeasureAnalysisPO> listAnalysis(int curPage, int pageSize, Integer userId, Date measureDate, HealthPackageType healthProduct,
            Integer customerUserId, Boolean read, Boolean reply);
    
    /**
     *  获取批注
     *  @author yuhang.weng 
     *  @DateTime 2017年10月30日 下午4:47:58
     *
     *  @param id 批注id
     *  @return
     */
    MeasureAnalysisPO getAnalysis(int id);
    
    /**
     *  添加批注
     *  @author yuhang.weng 
     *  @DateTime 2017年10月30日 下午4:44:28
     *
     *  @param userId 用户id
     *  @param measureDate 测量时间
     *  @throws BaseException
     */
    void addAnalysis(int userId, Date measureDate) throws BaseException;
    
    /**
     *  添加批注
     *  @author yuhang.weng 
     *  @DateTime 2017年10月30日 下午4:44:16
     *
     *  @param userId 用户id
     *  @param measureDate 测量时间
     *  @param reply 回复内容
     *  @param customerUserId 客服id
     *  @param doctorSign 医生签名
     *  @throws BaseException
     */
    void addAnalysis(int userId, Date measureDate, String reply, int customerUserId, String doctorSign) throws BaseException;
    
    /**
     *  写批注
     *  @author yuhang.weng 
     *  @DateTime 2017年10月30日 下午4:44:07
     *
     *  @param id 批注id
     *  @param reply 回复内容
     *  @param customerUserId 客服id
     *  @param doctorSign 医生签名
     *  @exception BaseException
     */
    void replyAnalysis(int id, String reply, int customerUserId, String doctorSign) throws BaseException;
    
    /**
     *  （用户）修改批注状态为已读
     *  <p>如果客服还没有回复就不能修改为已读
     *  @author yuhang.weng 
     *  @DateTime 2017年10月30日 下午4:48:41
     *
     *  @param id
     *  @throws OperationException
     */
    void readAnalysis(int id) throws OperationException;
    
    /**
     *  （用户）修改批注状态为已读
     *  <p>如果客服还没有回复就不能修改为已读
     *  @author yuhang.weng 
     *  @DateTime 2017年10月30日 下午5:06:22
     *
     *  @param userId 用户id
     *  @param measureDate 测量日期
     */
    void readAnalysis(int userId, Date measureDate);
    
    /**
     *  删除批注
     *  @author yuhang.weng 
     *  @DateTime 2017年10月31日 上午11:58:38
     *
     *  @param id 批注id
     *  @throws OperationException
     */
    void deleteAnalysis(int id) throws OperationException;
    
    /**
     *  删除批注
     *  @author yuhang.weng 
     *  @DateTime 2017年10月31日 上午11:58:47
     *
     *  @param userId 用户id
     *  @param measureDate 测量日期
     */
    void deleteAnalysis(int userId, Date measureDate);

    /**
     *  获取测量日期数据
     * @author Administrator
     * @param userId
     * @param date
     * @return
     */
    Map<String,Object> getSpecifiedDateData(int userId,String date);

}
