package measure;

import com.lifeshs.dao1.measure.BloodLipidDao;
import com.lifeshs.dao1.measure.HeartRateDao;
import com.lifeshs.po.HeartRatePO;
import junit.framework.TestCase;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testng.annotations.Test;

import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class HeartRateDaoTest extends TestCase {

    @Autowired
    HeartRateDao heartRateDao;
    
    @Autowired
    BloodLipidDao bloodLipidDao;
    
    @Test
    public void testGetLastDateByUserId() {
//        Date date = bloodLipidDao.getLastDateByUserId(1330);
//        System.out.println(new Date().compareTo(date));
//        List list = new ArrayList();
//        list.add(new Date());
//        list.add(date);
//        list.add(date);
//
//        list.sort(null);
//
//        System.out.println(list);

    }
    
    @Test
    public void testSelectMeasureDatesByUserId() {
        //结果集
        Map<String, Integer> result = new HashMap<>();
//        List<HeartRatePO> list = heartRateDao.selectMeasureDatesByUserId(1330, "2017-05-01");
//        for (HeartRatePO po : list) {
//            //具有数据的日期
//            String date = DateFormatUtils.format(po.getMeasureDate(), "yyyy-MM-dd");
//
//            //之前的结果
//            Integer preStatus = result.get(date);
//
//            int status = po.getStatus().intValue();
//
//            if (preStatus != null && preStatus > 0) {
//                continue;
//            } else if ( preStatus == null||preStatus==0) {
//                result.put(date, status);
//            }
//
//        }
//
//        System.out.println(result);
    }


}