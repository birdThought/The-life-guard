package com.lifeshs.service1.famousDoctor;

import com.lifeshs.po.famousDoctor.FamousDoctorPO;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.famousDoctor.ProfessionKindVO;

/**
 *  名医service
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年10月20日 上午9:30:17
 */
public interface FamousDoctorService {

    /**
     *  获取名医
     *  @author yuhang.weng 
     *  @DateTime 2017年10月24日 上午10:37:22
     *
     *  @param id
     *  @return
     */
    FamousDoctorPO getFamousDoctor(int id);
    
    /**
     *  获取名医列表
     *  <p> 如果likeName跟professionKindLikeName相同，会对两个条件做OR处理，只要满足其中一个即可
     *  @author yuhang.weng 
     *  @DateTime 2017年10月20日 上午10:47:02
     *
     *  @param likeName 名字（模糊查询）
     *  @param professionKindLikeName 职业/疾病名称（模糊查询）
     *  @param curPage 当前页码
     *  @param pageSize 页面大小
     *  @return
     */
    Paging<FamousDoctorPO> listFamousDoctor(String likeName, String professionKindLikeName, int curPage, int pageSize);
    
    /**
     *  获取名医列表（按照疾病分类）
     *  @author yuhang.weng 
     *  @DateTime 2017年10月24日 上午9:33:12
     *
     *  @param curPage
     *  @param pageSize
     *  @return
     */
    Paging<ProfessionKindVO> listProfessionKind(int curPage, int pageSize);
}
