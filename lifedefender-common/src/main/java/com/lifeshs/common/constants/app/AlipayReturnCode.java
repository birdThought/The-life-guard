package com.lifeshs.common.constants.app;

import java.util.HashSet;
import java.util.Set;

/**
 * 参数来自支付宝文档,
 *   https://docs.open.alipay.com/common/105806
 *
  */
public class AlipayReturnCode {

    public static final Set<String> RETRY = new HashSet<String>();

    static {
        RETRY.add("isp.unknow-error");//服务暂不可用（业务系统不可用） 解决方案:稍后重试
        RETRY.add("aop.unknow-error");//服务暂不可用（网关自身的未知错误） 解决方案:稍后重试
        RETRY.add("isv.invalid-encrypt");//解密异常 解决方案:重试
        RETRY.add("isv.decryption-error-unknown");//解密出错，未知异常   解决方案:重试
    }

}
