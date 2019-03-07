package com.lifeshs.service.tool;

import java.util.List;

/**
 *  邀请码生成工具
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年4月5日 下午4:36:24
 */
public interface IInvitationCodeService {

    /**
     *  生成邀请码
     *  @author yuhang.weng 
     *  @DateTime 2017年10月16日 上午10:29:07
     *
     *  @return
     */
    String createCode();
    
    /**
     *  批量生成邀请码
     *  @author yuhang.weng 
     *  @DateTime 2017年10月16日 上午10:28:47
     *
     *  @param size 数量
     *  @return
     */
    List<String> createCodes(int size);
}
