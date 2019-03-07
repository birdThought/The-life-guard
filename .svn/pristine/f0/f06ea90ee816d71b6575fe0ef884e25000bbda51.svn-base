package com.lifeshs.app.api.wearterminal;

import com.lifeshs.service.device.IDeviceService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Author Yue.Li
 * @Date 2017/4/17.
 */
public class TestDeviceService {
    private ApplicationContext ac;

    private IDeviceService deviceService;

    /**
     *  C3手环测试
     */
//    @Test
    public void testPushData(){
       String imei ="867556058100282";
       int userId = 1209;
        Map<String, Object> params = new HashMap<String, Object>() ;
        params.put("imei",imei);
        params.put("userId",userId);
        List<Map<String, Object>> res = deviceService.findCommond(imei,userId);
//        List<Map<String, Object>> res = deviceService.findCommond(params);
        Iterator<Map<String, Object>> resIterator=  res.iterator();
        if(resIterator.hasNext()){
            Map<String, Object> commond = resIterator.next();
            Assert.assertEquals("contact",commond.get("name"));
        }
    }
//    @Before
    public void setUp() throws Exception {
        ac = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
        deviceService = (IDeviceService) ac.getBean("deviceServiceImpl");
    }

//    @After
    public void tearDown() throws Exception {
        ac = null;
    }
}
