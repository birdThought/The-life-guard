package com.lifeshs.dao1.famousDoctor;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.famousDoctor.FamousDoctorPO;
import com.lifeshs.vo.famousDoctor.ProfessionKindVO;

/**
 *  名医
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年10月19日 下午3:25:46
 */
@Repository(value = "famousDoctorDao")
public interface FamousDoctorDao {

    /**
     *  获取名医
     *  @author yuhang.weng 
     *  @DateTime 2017年10月24日 上午11:34:05
     *
     *  @param id
     *  @return
     */
    FamousDoctorPO getFamousDoctor(@Param("id") int id);
    
    /**
     *  获取名医列表
     *  @author yuhang.weng 
     *  @DateTime 2017年10月19日 下午3:45:52
     *
     *  @param startRow 开始下标
     *  @param pageSize 获取数量
     *  @param likeName 名字
     *  @param professionKindLikeName 所属职业
     *  @return
     */
    List<FamousDoctorPO> findFamousDoctorListWithCondition(@Param("startRow") int startRow, @Param("pageSize") int pageSize,
            @Param("likeName") String likeName, @Param("professionKindLikeName") String professionKindLikeName);
    
    /**
     *  统计名医数量
     *  @author yuhang.weng 
     *  @DateTime 2017年10月19日 下午3:45:41
     *
     *  @param likeName 名字
     *  @param professionKindLikeName 所属职业
     *  @return
     */
    int countFamousDoctorWithCondition(@Param("likeName") String likeName, @Param("professionKindLikeName") String professionKindLikeName);
    
    /**
     *  获取分类列表
     *  @author yuhang.weng 
     *  @DateTime 2017年10月24日 上午11:32:05
     *
     *  @param startRow 开始下标
     *  @param pageSize 页面大小
     *  @return
     */
    List<String> findProKindNameList(@Param("startRow") int startRow, @Param("pageSize") int pageSize);
    
    /**
     *  获取名医列表
     *  @author yuhang.weng 
     *  @DateTime 2017年10月24日 上午11:32:24
     *
     *  @param maxAvgGroupRow 分类后每组数据最大值
     *  @param kindNameList 分类名称列表
     *  @return
     */
    List<ProfessionKindVO> findFamousDoctorKind(@Param("maxAvgGroupRow") int maxAvgGroupRow, @Param("kindNameList") List<String> kindNameList);
    
    /**
     *  统计分类数量
     *  @author yuhang.weng 
     *  @DateTime 2017年10月24日 上午11:33:07
     *
     *  @return
     */
    int countProKindName();
}
