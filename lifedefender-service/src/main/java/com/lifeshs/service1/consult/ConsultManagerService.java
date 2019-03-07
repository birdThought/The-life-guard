package com.lifeshs.service1.consult;

import java.util.List;

import com.lifeshs.po.consult.InformationColumnPO;
import com.lifeshs.po.consult.InformationPO;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.consult.InformationColumnVO;
import com.lifeshs.vo.consult.InformationVO;

/**
 * 资讯管理Service
 * @author zizhen.huang
 * @DateTime 2018年1月16日14:06:02
 */
public interface ConsultManagerService {
	/**
	 * 根据id获取资讯栏目列表
	 * @author zizhen.huang
	 * @DateTime 2018年1月16日14:09:46
	 * @param parentId 父栏目id
	 * @return
	 */
    List<InformationColumnVO> getColumnListById(Integer parentId);
    
    /**
     * 根据id获取所有资讯信息列表
     * @author zizhen.huang
     * @DateTime 2018年1月16日20:10:49
     * @param parentId 父栏目id
     * @param curPage
     * @param pageSize
     * @return
     */
    Paging<InformationPO> getInformationListById(Integer parentId, int curPage, int pageSize);
    
    /**
     * 根据id获取二级栏目资讯信息列表
     * @author zizhen.huang
     * @DateTime 2018年1月18日12:04:07
     * @param id
     * @param curPage
     * @param pageSize
     * @return
     */
    Paging<InformationPO> getSecondInformationListById(Integer id, int curPage, int pageSize);
    
    /**
     * 添加资讯信息
     * @author zizhen.huang
     * @DateTime 2018年1月18日19:40:20
     * @param informationPO
     * @return
     */
    boolean addInformation(InformationPO informationPO);
    
    /**
     * 根据id删除资讯信息
     * @author zizhen.huang
     * @DateTime 2018年1月18日19:41:17
     * @param id 主键
     * @return
     */
    boolean delInformationById(Integer id);
    
    /**
     * 修改资讯信息
     * @author zizhen.huang
     * @DateTime 2018年1月18日19:41:49
     * @param informationPO
     * @return
     */
    boolean updateInformation(InformationPO informationPO);
    
    /**
     * 根据id获取一条资讯信息
     * @author zizhen.huang
     * @DateTime 2018年1月19日14:34:17
     * @param id 主键
     * @return
     */
    InformationVO getInformationById(Integer id);
    
    /**
     * 添加资讯栏目
     * @author zizhen.huang
     * @DateTime 2018年1月19日16:13:09
     * @param informationColumnPO
     * @return
     */
    boolean addColumn(InformationColumnPO informationColumnPO);
    
    /**
     * 根据id删除资讯栏目
     * @author zizhen.huang
     * @DateTime 2018年1月19日16:13:52
     * @param id 主键
     * @return
     */
    boolean delColumnById(Integer id);
    
    /**
     * 修改资讯栏目
     * @author zizhen.huang
     * @DateTime 2018年1月19日16:14:31
     * @param informationColumnPO
     * @return
     */
    boolean updateColumn(InformationColumnPO informationColumnPO);
    
    /**
     * 根据id获取子栏目
     * @author zizhen.huang
     * @DateTime 2018年1月22日10:38:56
     * @param id 主键
     * @return
     */
    List<InformationColumnPO> getChildColumnById(Integer id);
}
