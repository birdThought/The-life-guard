package com.lifeshs.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 *  版权归   
 *  @TODO 类说明     解析参数 单例
 *  @author duosheng.mo  
 *  @DateTime 2015-7-5 下午09:43:03
 *  @version V1.0
 */
public class ParserParaUtil {
	
	public static ParserParaUtil  parserParaUtil = null ;
	private Map<String , Object> formData = null ;
	private ParserParaUtil(){
		
	}
	
	public static ParserParaUtil getInstence(){
		if(parserParaUtil == null){
			parserParaUtil = new ParserParaUtil();
		}
		
		return parserParaUtil;
		
	}
	
	/**
	 *  @author duosheng.mo 
	 *	@DateTime 2016年5月4日 下午2:25:24
	 *  @serverCode 服务代码
	 *  @serverComment 获取url参数
	 *
	 *  @param request
	 *  @return
	 */
	public static Map<String , Object> getParams(ServletRequest request){
		HttpServletRequest  req = (HttpServletRequest)request ;
		Map<String , Object> params = getInstence().getRequestPara(req);
		return params;
	}
	
	@SuppressWarnings({ "unused", "rawtypes" })
	public Map<String ,Object> getRequestPara(HttpServletRequest request){
		
		String requestUrl = request.getRequestURI();
		StringBuffer fullUrlSb = new StringBuffer();
		fullUrlSb.append(request.getRequestURL());
		if(request.getQueryString()!=null){
			fullUrlSb.append("?").append(request.getQueryString());
		}
		String requestFullUrl = fullUrlSb.toString();
		
		/**
		 * 获取用户请求的数据并放入DATA对象
		 */
		formData = new HashMap<String,Object>();

		Enumeration paraNames = request.getParameterNames();
		while (paraNames.hasMoreElements()) {
			String paraName = (String) paraNames.nextElement();
			
			Object paraVal;
			String strs[] = request.getParameterValues(paraName);
			if (strs != null && strs.length > 0)
				paraVal = strs;
			else
				paraVal = request.getParameter(paraName);

			String paraStrval = "";
			
			if (PMSUtil.isStringArr(paraVal)) {
				String[] arr = (String[]) paraVal;
				for (String str : arr) {
					str = PMSUtil.addSlashes(str);
					paraStrval += PMSUtil.isEmpty(paraStrval) ? str : "," + str;
				}
			} else {
				paraStrval = request.getParameter(paraName);
			}

			formData.put(paraName, paraStrval.trim());
		}
		
		if(formData.containsKey("pagePara")){
			String pagePara = (String)formData.get("pagePara");
			formData.remove("pagePara");
			JSONArray  jsonArray  = JSONArray.fromObject(pagePara);
			String sEcho = "";// 记录操作的次数  每次加1
		    String iDisplayStart = "";// 起始
		    String iDisplayLength = "";// size
		    String sSearch = "";// 搜索的关键字
			for(int i = 0; i < jsonArray.size(); i++){
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
				if (jsonObject.get("name").equals("sEcho")) {
					sEcho = String.valueOf(jsonObject.get("value"));
				}
				if (jsonObject.get("name").equals("iDisplayStart")) {
					iDisplayStart = String.valueOf(jsonObject.get("value"));
				}
				if (jsonObject.get("name").equals("iDisplayLength")) {
					iDisplayLength = String.valueOf(jsonObject.get("value"));
				}
				if (jsonObject.get("name").equals("sSearch")) {
					sSearch = (String)jsonObject.get("value");
				}
			}
			 //为操作次数加1
		    int  initEcho = Integer.parseInt(sEcho)+1;
			formData.put("BEGIN_NUM", Integer.parseInt(iDisplayStart));
			formData.put("END_NUM", Integer.parseInt(iDisplayLength));
			formData.put("sEcho", initEcho);
			formData.put("sSearch", sSearch);
			formData.put("limit", true);
		}else if(formData.containsKey("nd") && formData.containsKey("rows")){
			int page = Integer.parseInt((String)formData.get("page"));
			int rows = Integer.parseInt((String)formData.get("rows"));
			formData.put("BEGIN_NUM", (page*rows)-rows);
			formData.put("END_NUM", (page*rows));
			formData.put("limit", true);
		}
		return formData ;
	}
	
	
	
	

}
