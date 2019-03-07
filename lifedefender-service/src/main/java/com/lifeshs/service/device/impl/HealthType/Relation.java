package com.lifeshs.service.device.impl.HealthType;

import com.lifeshs.common.constants.common.HealthPackageType;
import com.lifeshs.common.constants.common.HealthType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  健康包设备关系
 *  @author yuhang.weng  
 *  @DateTime 2017年2月14日 下午4:42:32
 */
public class Relation {

    private static Map<String, List<HealthType>> relation;
    
    static {
        relation = new HashMap<>();
        
        List<HealthType> Band = new ArrayList<>();
        Band.add(HealthType.heartRate);
        relation.put(HealthPackageType.Band.name(), Band);
        
        List<HealthType> BloodPressure = new ArrayList<>();
        BloodPressure.add(HealthType.systolic);
        BloodPressure.add(HealthType.diastolic);
        relation.put(HealthPackageType.BloodPressure.name(), BloodPressure);
        
        List<HealthType> Oxygen = new ArrayList<>();
        Oxygen.add(HealthType.saturation);
        Oxygen.add(HealthType.heartRate);
        relation.put(HealthPackageType.Oxygen.name(), Oxygen);
        
        List<HealthType> BodyFatScale = new ArrayList<>();
        BodyFatScale.add(HealthType.weight);
        BodyFatScale.add(HealthType.axungeRatio);
        BodyFatScale.add(HealthType.WHR);
        BodyFatScale.add(HealthType.BMI);
        BodyFatScale.add(HealthType.fatFreeWeight);
        BodyFatScale.add(HealthType.muscle);
        BodyFatScale.add(HealthType.moisture);
        BodyFatScale.add(HealthType.boneWeight);
        BodyFatScale.add(HealthType.bodyage);
        BodyFatScale.add(HealthType.baseMetabolism);
        BodyFatScale.add(HealthType.proteide);
        BodyFatScale.add(HealthType.visceralFat);
        relation.put(HealthPackageType.BodyFatScale.name(), BodyFatScale);
        
        List<HealthType> Glucometer = new ArrayList<>();
        Glucometer.add(HealthType.bloodSugar);
        relation.put(HealthPackageType.Glucometer.name(), Glucometer);
        
        List<HealthType> Lunginstrument = new ArrayList<>();
        Lunginstrument.add(HealthType.vitalCapacity);
        relation.put(HealthPackageType.Lunginstrument.name(), Lunginstrument);
        
        List<HealthType> Temperature = new ArrayList<>();
        Temperature.add(HealthType.temperature);
        relation.put(HealthPackageType.Temperature.name(), Temperature);
    }
    
    public static List<HealthType> getDeviceHealthType(HealthPackageType deviceType) {
        List<HealthType> healthTypes = relation.get(deviceType.name());
        return healthTypes;
    }
}
