package com.lifeshs.interceptors;

import java.io.IOException;

import javax.servlet.*;


/**
 *  版权归   gang of three
 *  @TODO 类说明
 *  @author duosheng.mo
 *  @DateTime 2015-7-2 下午09:14:06
 *  @version V1.0
 */
public class ParaFilter implements Filter {
    public void destroy() {
        // TODO Auto-generated method stub
    }

    public void doFilter(ServletRequest request, ServletResponse response,
        FilterChain chain) throws IOException, ServletException {
        //		HttpServletRequest  req = (HttpServletRequest)request ;
        //		Map<String , Object> paras = ParserParaUtil.getInstence().getRequestPara(req);
        //		try{
        //			ParaThreadLocal.setPara(paras);
        //			logger.info("===SRB进入para===" + ParaThreadLocal.getPara());			
        chain.doFilter(request, response);

        //			logger.info("===SRB出去para===" + ParaThreadLocal.getPara());
        //		}finally{			
        //			ParaThreadLocal.remove();
        //		}
    }

    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub
    }
}
