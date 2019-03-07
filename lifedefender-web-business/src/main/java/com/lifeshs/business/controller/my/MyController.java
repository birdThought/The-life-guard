package com.lifeshs.business.controller.my;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * author: wenxian.cai
 * date: 2017/10/12 16:58
 */

@Controller
@RequestMapping("index")
public class MyController {

	@RequestMapping("")
	public ModelAndView page () {
		return new ModelAndView("platform/index");
	}


}
