/*package com.lifeshs.service.impl.terminal;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.lifeshs.service.device.impl.Band;
import com.lifeshs.service.device.impl.BloodPressure;
import com.lifeshs.service.device.impl.Bodyfatscale;
import com.lifeshs.service.device.impl.Glucometer;
import com.lifeshs.service.device.impl.HeartRate;
import com.lifeshs.service.device.impl.Lunginstrument;
import com.lifeshs.service.device.impl.Oxygen;
import com.lifeshs.service.device.impl.Temperature;
import com.lifeshs.service.terminal.IHealthDataService;

@Component("healthDataService")
public class HealthDataServiceImpl implements IHealthDataService {

	@Resource(name = "bloodPressure")
	private BloodPressure bloodPressure;
	@Resource(name = "lunginstrument")
	private Lunginstrument lunginstrument;
	@Resource(name = "glucometer")
	private Glucometer glucometer;
	@Resource(name = "oxygen")
	private Oxygen oxygen;
	@Resource(name = "heartRate")
	private HeartRate heartRate;
	@Resource(name = "bodyfatscale")
	private Bodyfatscale bodyfatscale;
	@Resource(name = "band")
	private Band band;
	@Resource(name = "temperature")
	private Temperature temperature;

	public List<Map<String, Object>> getBloodPressureByDatediff(String userId, String date, String deviceType) {
		List<Map<String, Object>> list = null;
		try {
			list = bloodPressure.getMeasureDataByDatediff(userId, date, deviceType);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public List<Map<String, Object>> getLunginstrumentByDatediff(String userId, String date, String deviceType) {

		List<Map<String, Object>> list = null;
		try {
			list = lunginstrument.getMeasureDataByDatediff(userId, date, deviceType);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public List<Map<String, Object>> getGlucometerByDatediff(String userId, String date, String deviceType) {

		List<Map<String, Object>> list = null;
		try {
			list = glucometer.getMeasureDataByDatediff(userId, date, deviceType);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public List<Map<String, Object>> getOxygeneByDatediff(String userId, String date, String deviceType) {

		List<Map<String, Object>> list = null;
		try {
			list = oxygen.getMeasureDataByDatediff(userId, date, deviceType);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public List<Map<String, Object>> getHeartRateByDatediff(String userId, String date, String deviceType) {

		List<Map<String, Object>> list = null;
		try {
			list = heartRate.getMeasureDataByDatediff(userId, date, deviceType);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public List<Map<String, Object>> getBodyfatscaleByDatediff(String userId, String date, String deviceType) {

		List<Map<String, Object>> list = null;
		try {
			list = bodyfatscale.getMeasureDataByDatediff(userId, date, deviceType);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public List<Map<String, Object>> getBloodPressureByPage(String userId, int pageSize, int page, String deviceType) {
		return bloodPressure.getMeasureDataByPage(userId, pageSize, page, deviceType);
	}

	@Override
	public List<Map<String, Object>> getLunginstrumentByPage(String userId, int pageSize, int page, String deviceType) {
		List<Map<String, Object>> list = null;
		try {
			list = lunginstrument.getMeasureDataByPage(userId, pageSize, page, deviceType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getGlucometerByPage(String userId, int pageSize, int page, String deviceType) {
		List<Map<String, Object>> list = null;
		try {
			list = glucometer.getMeasureDataByPage(userId, pageSize, page, deviceType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getOxygeneByPage(String userId, int pageSize, int page, String deviceType) {
		return oxygen.getMeasureDataByPage(userId, pageSize, page, deviceType);
	}

	@Override
	public List<Map<String, Object>> getHeartRateByPage(String userId, int pageSize, int page, String deviceType) {
		List<Map<String, Object>> list = null;
		try {
			list = heartRate.getMeasureDataByPage(userId, pageSize, page, deviceType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getBandByPage(String userId, int pageSize, int page, String deviceType) {
		List<Map<String, Object>> list = null;
		try {
			list = band.getMeasureDataByPage(userId, pageSize, page, deviceType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getBodyfatscaleByPage(String userId, int pageSize, int page, String deviceType) {
		return bodyfatscale.getMeasureDataByPage(userId, pageSize, page, deviceType);
	}

	@Override
	public List<Map<String, Object>> getBandStepByDatediff(String userId, String date, String deviceType) {
		List<Map<String, Object>> list = null;
		try {
			list = band.getMeasureDataByDatediff(userId, date, deviceType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getBandSleepByDatediff(String userId, String date, String deviceType) {
		List<Map<String, Object>> list = null;
		try {
			// list= bodyfatscale.getMeasureDataByPage(userId, date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getBandByDatediff(String userId, String date, String deviceType) {

		List<Map<String, Object>> list = null;
		try {
			list = band.getMeasureDataByDatediff(userId, date, deviceType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getTemperatureByDatediff(String userId, String date, String deviceType) {
		List<Map<String, Object>> list = null;
		try {
			list = temperature.getMeasureDataByDatediff(userId, date, deviceType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getTemperatureByPage(String userId, int pageSize, int page, String deviceType) {
		return temperature.getMeasureDataByPage(userId, pageSize, page, deviceType);
	}
}
*/