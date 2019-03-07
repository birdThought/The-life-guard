package com.lifeshs.service.tool.impl;

import com.lifeshs.common.constants.common.CacheType;
import com.lifeshs.service.tool.ICacheService;
import com.lifeshs.service.tool.ITokenService;
import com.lifeshs.utils.RandCodeUtil;
import com.lifeshs.utils.Replace;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *  版权归
 *  TODO 令牌(token)工具类
 *  @author yuhang.weng
 *  @DateTime 2016年6月21日 下午2:27:54
 */
@Component(value = "tokenService")
public class TokenServiceImpl implements ITokenService {

    @Autowired
    ICacheService cacheService;
    /**
     *  @author yuhang.weng
     *	@DateTime 2016年6月21日 下午2:31:24
     *  @serverComment 生成token并保存
     *
     *	@param key 缓存key
     *  @return
     */
    public String createToken(String key, String salt){
        String token = RandCodeUtil.randNumberCodeByCustom("5", 16);
        cacheService.saveKeyValue(CacheType.USER_TOKEN_CACHE, salt + key, token);
        return token;
    }

    /**
     *  @author yuhang.weng
     *	@DateTime 2016年6月21日 下午2:48:16
     *  @serverComment 检测token是否过期
     *
     *  @param key 缓存key
     *  @param token
     *  @return
     */
    public boolean isTokenOverTime(String key, String salt, String token){
        Object token_cache = cacheService.getCacheValue(CacheType.USER_TOKEN_CACHE, salt + key);
        if(token_cache == null)
            return true;
        boolean isSameToken = StringUtils.equals(token, (String) token_cache);
        if (isSameToken) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 验证第三方的身份和token( Sha1（appsecert+ timestamp）)
     * @param token
     * @param timestamp
     * @param appsecert
     * @return
     */
    @Override
    public boolean validThirdToken(String token, String timestamp, String appsecert) {
        String accesstoken = Replace.getStringDate(appsecert,Long.parseLong(timestamp));
        if (!token.equals(accesstoken)) {
             return false;
        }
        return true;
    }

    /**
     * 检查第三方接口的时效性，目前规则是timestamp不超过三分钟
     * @param token
     * @param timestamp
     * @return
     */
    @Override
    public boolean isThirdTokenOverTime(String token, String timestamp) {
        long current = System.currentTimeMillis() / 1000;
        long client = Long.parseLong(timestamp);
        if(current - client < 180)
            return true;
        return false;
    }
}
