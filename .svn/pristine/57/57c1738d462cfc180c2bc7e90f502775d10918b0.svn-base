package com.lifeshs.dao1.consult;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.consult.InformationColumnPO;
import com.lifeshs.po.consult.InformationPO;
import com.lifeshs.vo.consult.InformationColumnVO;
import com.lifeshs.vo.consult.InformationVO;

/**
 * 咨询管理DAO
 * @author zizhen.huang
 * @DateTime 2018年1月16日10:57:59
 */
@Repository(value = "consultManagerDao")
public interface IConsultManagerDao {
	/**
	 * 根据id获取资讯栏目信息（包括该栏目下的子栏目）
	 * @author zizhen.huang
	 * @DateTime 2018年1月16日11:24:42
	 * @param parentId 父栏目id
	 * @return
	 */
    List<InformationColumnVO> getColumnListById(@Param("parentId") Integer parentId);
    
    /**
     * 根据id获取所有资讯信息总记录数
     * @author zizhen.huang
     * @DateTime 2018年1月16日19:37:30
     * @param parentId 需要查询的栏目id
     * @return
     */
    int getTotalRecord(@Param("columnIdList") List<Integer> columnIdList);
    
    /**
     * 根据id获取所有资讯信息列表
     * @author zizhen.huang
     * @DateTime 2018年1月16日19:37:40
     * @param columnIdList 需要查询的栏目id
     * @param startRow
     * @param pageSize
     * @return
     */
    List<InformationPO> getInformationListById(@Param("columnIdList") List<Integer> columnIdList, @Param("startRow") int startRow, @Param("pageSize") int pageSize);
    
    /**
     * 根据id获取二级栏目资讯信息总记录数
     * @author zizhen.huang
     * @DateTime 2018年1月18日11:56:53
     * @param id
     * @return
     */
    int getSecondTotalRecord(@Param("id") Integer id);
    
    /**
     * 根据id获取二级栏目资讯信息列表
     * @param id
     * @param startRow
     * @param pageSize
     * @return
     */
    List<InformationPO> getSecondInformationListById(@Param("id") Integer id, @Param("startRow") int startRow, @Param("pageSize") int pageSize);
    
    /**
     * 添加资讯信息
     * @author zizhen.huang
     * @DateTime 2018年1月18日19:14:03
     * @param informationPO
     * @return
     */
    int addInformation(InformationPO informationPO);
    
    /**
     * 根据id删除资讯信息
     * @author zizhen.huang
     * @DateTime 2018年1月18日19:05:32
     * @param id 主键
     * @return
     */
    int delInformationById(@Param("id") Integer id);
    
    /**
     * 修改资讯信息
     * @author zizhen.huang
     * @DateTime 2018年1月18日19:28:47
     * @param informationPO
     * @return
     */
    int updateInformation(InformationPO informationPO);
    
    /**
     * 根据id获取一条资讯信息
     * @author zizhen.huang
     * @DateTime 2018年1月19日14:26:47
     * @param id 主键
     * @return
     */
    InformationVO getInformationById(@Param("id") Integer id);
    
    /**
     * 添加资讯栏目
     * @author zizhen.huang
     * @DateTime 2018年1月19日15:55:04
     * @param informationColumnPO
     * @return
     */
    int addColumn(InformationColumnPO informationColumnPO);
    
    /**
     * 根据id删除资讯栏目
     * @author zizhen.huang
     * @DateTime 2018年1月19日16:03:20
     * @param id 主键
     * @return
     */
    int delColumnById(@Param("id") Integer id);
    
    /**
     * 修改资讯栏目
     * @author zizhen.huang
     * @DateTime 2018年1月19日16:04:23
     * @param informationColumnPO
     * @return
     */
    int updateColumn(InformationColumnPO informationColumnPO);
    
    /**
     * 根据id获取子栏目
     * @author zizhen.huang
     * @DateTime 2018年1月22日10:37:13
     * @param id 主键
     * @return
     */
    List<InformationColumnPO> getChildColumnById(@Param("id") Integer id);
}
