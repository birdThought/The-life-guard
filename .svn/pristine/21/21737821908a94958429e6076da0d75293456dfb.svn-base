package com.lifeshs.service.org.employee.impl;

import java.util.ArrayList;
import java.util.List;

import com.lifeshs.utils.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeshs.dao.org.employee.IEmployeeManageDao;
import com.lifeshs.pojo.org.employee.EmployeeDTO;
import com.lifeshs.pojo.org.employee.EmployeeRegisterDTO;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.pojo.page.QueryPageData;
import com.lifeshs.service.common.impl.CommonServiceImpl;
import com.lifeshs.service.org.employee.IEmployeeManageService;
import com.lifeshs.utils.MD5Utils;

/**
 * 员工管理
 *
 * @author wenxian.cai
 * @create 2017-05-18 19:24
 **/

@Service("employeeManageService")
public class EmployeeManageServiceImpl extends CommonServiceImpl implements IEmployeeManageService {

    @Autowired
    IEmployeeManageDao employeeManageDao;

    @Override
    public PaginationDTO<EmployeeDTO> listEmployee(Integer orgId, String realName, Integer pageIndex, Integer pageSize) {

        PaginationDTO<EmployeeDTO> pagination = new PaginationDTO<>();
        List<EmployeeDTO> services = new ArrayList<>();

        int count = employeeManageDao.getCountOfEmployee(orgId, realName);
        if (PaginationDTO.isDataOverFlow(pageIndex, pageSize, count)) {
            pagination.setData(services);
            return pagination;

        }
        QueryPageData queryPageData = PaginationDTO.getQueryPageData(pageIndex, pageSize, count);
        int startIndex = queryPageData.getStartIndex();
        int totalPage = queryPageData.getTotalPage();
        pageIndex = queryPageData.getCurPage();

        services = employeeManageDao.listEmployee(orgId, realName, startIndex, pageSize);
        for (EmployeeDTO employee : services) {
            if (employee.getEarning() != null) {
                employee.setEarning(NumberUtils.changeF2Y(employee.getEarning().intValue() + ""));
                continue;
            }
            employee.setEarning(0.0);
        }
        pagination.setData(services);
        pagination.setNowPage(pageIndex);
        pagination.setTotalPage(totalPage);
        pagination.setTotalSize(count);

        return pagination;
    }
    
    @Override
    public List<EmployeeDTO> getEmployeeList(Integer orgId, String realName, Integer startIndex, Integer pageSize) {

        List<EmployeeDTO> services = employeeManageDao.listEmployee(orgId, realName, startIndex, pageSize);
        for (EmployeeDTO employee : services) {
            if (employee.getEarning() != null) {
                employee.setEarning(NumberUtils.changeF2Y(employee.getEarning().intValue() + ""));
                continue;
            }
            employee.setEarning(0.0);
        }
        return services;
    }

    @Override
    public boolean updateEmployee(Integer orgUserId, Integer status) {
        employeeManageDao.updateEmployee(orgUserId, status);
        return true;
    }

    @Override
    public boolean addEmployee(EmployeeRegisterDTO employee) {
        String userCode = getOrgUserCode().getOrgUserCode();
        if (!registHxUser(userCode)) {// 注册环信失败
            return false;
        }
        employee.setUserCode(userCode);
        employee.setPassword(MD5Utils.encryptPassword(employee.getPassword()));
        int result = employeeManageDao.addEmployee(employee);
        if (result < 0) {
            return false;
        }
        return true;
    }

    @Override
    public int countEmployee(Integer orgId) {
        int count = employeeManageDao.getCountOfEmployee(orgId, null);
        return count;
    }

    @Override
    public EmployeeDTO getEmployeeByUserCode(String userCode) {
        return employeeManageDao.getEmployeeByUserCode(userCode);
    }
    
    @Override
    public EmployeeDTO getEmployee(int id) {
        return employeeManageDao.findEmployee(id);
    }

    @Override
    public List<EmployeeDTO> listEmployeeByProjectCode(String projectCode) {
        return employeeManageDao.findOrgUserByProjectCode(projectCode);
    }

    @Override
    public List<EmployeeDTO> listEmployee(List<String> userCode) {
        return employeeManageDao.findServeUserByUserCode(userCode);
    }

	@Override
	public EmployeeDTO findEmployeeUserNo(String userNo) {
		// TODO Auto-generated method stub
		return employeeManageDao.findEmployeeUserNo(userNo);
	}
}
