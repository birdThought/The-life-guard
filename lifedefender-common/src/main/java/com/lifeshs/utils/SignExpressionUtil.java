package com.lifeshs.utils;

import org.apache.commons.lang3.StringEscapeUtils;

import com.lifeshs.common.constants.common.SignExpressionEnum;
import com.lifeshs.common.model.SignExpressionDTO;

/**
 *  符号表达式工具类
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年6月24日 上午10:49:12
 */
public class SignExpressionUtil {

    public static SignExpressionDTO getResult(String expression) {
        /**
         * = 1
         * > 1
         * < 1
         * 
         * 1 - 2
         * 
         * >= 1
         * <= 1
         * <> 1
         */
        
        
        SignExpressionDTO result = new SignExpressionDTO();
        Double start = 0d, end = 0d;
        SignExpressionEnum se = null;
        
        // 去除空格
        expression = expression.replaceAll(" ", "");
        String unescapeHtmlExpression = StringEscapeUtils.unescapeHtml4(expression);
        boolean matchSign = Toolkits.custom(unescapeHtmlExpression, "^[=<>].+$");
        
        // 判断是否以符号开头
        if (matchSign) {
            String sign = unescapeHtmlExpression.substring(0, 1);
            
            String signExtra = unescapeHtmlExpression.substring(0, 2);
            boolean matchSignExtra = Toolkits.custom(signExtra, "^[=<>]+$");
            if (matchSignExtra) {
                sign = signExtra;
            }
            
            switch(sign) {
            case "=":
                se = SignExpressionEnum.EQUALS;
                start = Double.parseDouble(unescapeHtmlExpression.substring(1));
                break;
            case ">":
                se = SignExpressionEnum.OVER;
                end = Double.parseDouble(unescapeHtmlExpression.substring(1));
                break;
            case "<":
                se = SignExpressionEnum.UNDER;
                start = Double.parseDouble(unescapeHtmlExpression.substring(1));
                break;
            case "<=":
                se = SignExpressionEnum.UNDER_INCLUDE;
                start = Double.parseDouble(unescapeHtmlExpression.substring(2));
                break;
            case ">=":
                se = SignExpressionEnum.OVER_INCLUDE;
                end = Double.parseDouble(unescapeHtmlExpression.substring(2));
                break;
            case "<>":
                se = SignExpressionEnum.NOT_EQUALS;
                start = Double.parseDouble(unescapeHtmlExpression.substring(2));
                break;
            default:
                return null;
            }
        } else {
            se = SignExpressionEnum.BETWEEN;
            String[] ps = unescapeHtmlExpression.split("-");
            start = Double.parseDouble(ps[0]);
            end = Double.parseDouble(ps[1]);
        }
        
        result.setStart(start);
        result.setEnd(end);
        result.setExpression(se);
        return result;
    }
}
