package com.lifeshs.mobile.controller.common;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.model.AjaxJson;
import com.lifeshs.component.uedit.ActionEnter;
import com.lifeshs.entity.report.TReport;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.pojo.client.MemberSharingData;
import com.lifeshs.pojo.client.OrgUserSharingData;
import com.lifeshs.security.sessionmgr.ClientManager;
import com.lifeshs.security.sessionmgr.ISessionManageService;
import com.lifeshs.service.alipay.AlipayService;
import com.lifeshs.service.alipay.util.AlipayNotify;
import com.lifeshs.service.common.impl.transform.CommonTransImpl;
import com.lifeshs.service.data.IDataAreaService;
import com.lifeshs.service.order.IOrderService;
import com.lifeshs.service.tool.ICacheService;
import com.lifeshs.utils.ParserParaUtil;
import com.lifeshs.utils.UUID;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhansi.Xu
 * @DateTime 2016年9月5日
 * @Comment 公共控制器
 */
@RequestMapping("commonControl")
@Controller
public class BaseController {
}
