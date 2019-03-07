package com.lifeshs.tag;

import com.lifeshs.utils.ConvertUtil;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;


/**
 *  版权归
 *  TODO 自定加载js、css的tag
 *  @author duosheng.mo
 *  @DateTime 2016年5月9日 下午3:44:10
 */
public class BaseTag extends TagSupport {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    protected String type = "default"; // 加载类型

    public void setType(String type) {
        this.type = type;
    }

    public int doStartTag() throws JspException {
        return EVAL_PAGE;
    }

    public int doEndTag() throws JspException {
        try {
            JspWriter out = this.pageContext.getOut();
            StringBuffer sb = new StringBuffer();

            String[] types = type.split(",");

            //插入多语言脚本
            String lang = (String) ((HttpServletRequest) this.pageContext
                                    .getRequest()).getSession()
                                    .getAttribute("lang");

            if (StringUtils.isBlank(lang)) {
                lang = "zh";
            }

            //			String langjs = StringUtil.replace("<script type=\"text/javascript\" src=\"plug-in/mutiLang/{0}.js\"></script>", "{0}", lang);
            //			sb.append(langjs);
            if (ConvertUtil.isIn("jquery2.11", types)) {
                sb.append(
                    "<link type=\"image/x-icon\" rel=\"shortcut icon\" href=\"/static/images/favicon.ico\"/>");
                sb.append(
                    "<script type=\"text/javascript\" src=\"/static/jquery/jquery-2.1.1.min.js\"></script>");
            }

            if (ConvertUtil.isIn("jquery", types)) {
                sb.append(
                    "<link type=\"image/x-icon\" rel=\"shortcut icon\" href=\"/static/images/favicon.ico\"/>");
                sb.append(
                    "<script type=\"text/javascript\" src=\"/static/jquery/jquery-1.10.2.min.js\"></script>");
                sb.append(
                    "<script type=\"text/javascript\" src=\"/static/common/js/common.js\"></script>");
            }

            if (ConvertUtil.isIn("jquery.serializejson", types)) {
                sb.append(
                    "<script type=\"text/javascript\" src=\"/static/jquery/jquery.serializejson.js\"></script>");
            }

            if (ConvertUtil.isIn("layer", types)) {
                sb.append(
                    "<script type=\"text/javascript\" src=\"/static/plugins/layer/layer.js\"></script>");
            }

            if (ConvertUtil.isIn("vue", types)) {
                sb.append(
                    "<script type=\"text/javascript\" src=\"/static/plugins/vue/vue.min.js\"></script>");
            }

            if (ConvertUtil.isIn("layui", types)) {
                sb.append(
                        "<script type=\"text/javascript\" src=\"/static/plugins/layui/layui.js\"></script>");
            }

            if (ConvertUtil.isIn("layui2.1.2", types)) {
                sb.append(
                        "<script type=\"text/javascript\" src=\"/static/plugins/layui/v2.1.2/layui.js\"></script>");
            }

            if (ConvertUtil.isIn("baidu.map", types)) {
                sb.append(
                    "<script type=\"text/javascript\" src=\"http://api.map.baidu.com/api?v=2.0&ak=i7QOOG81qeyTB5QvRmwqnipj&callback=initMap\"></script>");
            }

            out.print(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return EVAL_PAGE;
    }
}
