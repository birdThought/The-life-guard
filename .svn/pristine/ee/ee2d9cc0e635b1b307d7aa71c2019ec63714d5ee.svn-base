package com.lifeshs.app.api.common;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lifeshs.service.common.transform.ICommonTrans;
import com.lifeshs.service.member.IMemberService;
import com.lifeshs.service.org.user.IOrgUserService;
import com.lifeshs.service.terminal.app.impl.MAppNormalService;
import com.lifeshs.service.tool.ITokenService;

/**
 * @author Yue.Li
 * @date 3/14/17.
 */
@Controller
public class AppBaseController {

    @Autowired
    protected ICommonTrans commonTrans;

    @Autowired
    protected IOrgUserService orgUserService;

    @Autowired
    protected IMemberService memberService;

    @Autowired
    protected ITokenService tokenService;
    
    @Resource(name = "mappNormalService")
    protected MAppNormalService mappNormalService;
}
