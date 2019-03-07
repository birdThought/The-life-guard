package com.lifeshs.service1.data;

import java.util.Date;
import java.util.List;

import com.lifeshs.po.data.HobbyPO;
import com.lifeshs.vo.data.hobby.HotHobbyVO;

/**
 *  兴趣爱好服务类
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年8月4日 上午10:05:01
 */
public interface HobbyService {

    /**
     *  找到一个兴趣爱好
     *  @author yuhang.weng 
     *  @DateTime 2017年8月4日 上午10:05:16
     *
     *  @param id
     *  @return
     */
    HobbyPO getHobby(int id);
    
    /**
     *  获取兴趣爱好列表
     *  @author yuhang.weng 
     *  @DateTime 2017年8月4日 上午10:05:34
     *
     *  @return
     */
    List<HobbyPO> listHobby();
    
    /**
     *  获取兴趣爱好列表
     *  @author yuhang.weng 
     *  @DateTime 2017年8月4日 上午10:05:44
     *
     *  @param idList id列表
     *  @return
     */
    List<HobbyPO> listHobby(List<Integer> idList);
    
    /**
     *  获取热门的兴趣爱好列表
     *  @author yuhang.weng 
     *  @DateTime 2017年8月4日 下午4:01:52
     *
     *  @param maxSize
     *  @return
     */
    List<HotHobbyVO> listHotHobby(int maxSize);
    
    /**
     *  获取时间节点之后的变更的兴趣爱好列表
     *  @author yuhang.weng 
     *  @DateTime 2017年8月22日 下午5:48:32
     *
     *  @param datePoint 时间节点
     *  @return
     */
    List<HobbyPO> listModifyHobby(Date datePoint);
}
