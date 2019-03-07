package com.lifeshs.service1.business.impl;

import java.util.List;

import com.lifeshs.dao.org.user.OrgUserDao;
import com.lifeshs.po.business.BusinessPo;
import com.lifeshs.po.org.user.OrgUserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.wall.violation.ErrorCode;
import com.lifeshs.common.constants.common.CacheType;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.BaseException;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.exception.common.ParamException;
import com.lifeshs.dao1.business.IUserDao;
import com.lifeshs.po.business.BusinessUserPO;
import com.lifeshs.service.tool.ICacheService;
import com.lifeshs.service1.business.UserService;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.utils.JSONHelper;
import com.lifeshs.utils.MD5Utils;
import com.lifeshs.vo.business.BusinessSharingDataVO;

@Service(value = "businessUserService")
public class UserServiceImpl implements UserService {

	@Autowired
	IUserDao businessUserDao;

	@Autowired
	ICacheService cacheService;

	@Autowired
	OrgUserDao orgUserDao;

	@Override
	public BusinessUserPO getUser(int userId) {
		return businessUserDao.getUser(userId);
	}

	@Override
	public BusinessUserPO getUserByUserName(String userName) {

		return businessUserDao.getUserByBusiness(userName);
	}

	@Override
	public void saveUserSharingData(BusinessSharingDataVO po) throws OperationException {
		try {
			cacheService.saveKeyValue(CacheType.USER_SHARE_DATA, po.getId() + "", po);
		} catch (Exception e) {
			throw new OperationException("缓存-新增用户出错", ErrorCode.OTHER);
		}
	}

	@Override
	public BusinessSharingDataVO getUserSharingData(int userId) {
		Object o = cacheService.getCacheValue(CacheType.USER_SHARE_DATA, userId + "");
		if (o == null) {
			return null;
		}
		return JSONHelper.toBean(o, BusinessSharingDataVO.class);
	}

	@Override
	public void updateUserSharingData(int userId) throws OperationException {
		BusinessUserPO po = getUser(userId);
		BusinessSharingDataVO vo = new BusinessSharingDataVO();
		vo.setStatus(po.getStatus());
		vo.setCreateDate(po.getCreateDate());
		vo.setId(po.getId());
		vo.setModifyDate(po.getModifyDate());
		vo.setName(po.getName());
		//vo.setPhoto(po.getPhoto());
		//vo.setUserCode(po.getUserCode());
		vo.setUserName(po.getUserName());
		try {
			cacheService.saveKeyValue(CacheType.USER_SHARE_DATA, po.getId() + "", vo);
		} catch (Exception e) {
			throw new OperationException("缓存-更新用户出错", ErrorCode.OTHER);
		}
	}

	@Override
	public void updateUser(BusinessUserPO user) throws BaseException {
		Integer userId = user.getId();
		if (userId == null) {
			throw new ParamException("用户id不能为空", ErrorCodeEnum.MISSING);
		}
		int result = businessUserDao.updateUser(user);
		if (result == 0) {
			throw new OperationException("更新用户信息失败", ErrorCodeEnum.FAILED);
		}
	}

	@Override
	public Paging<BusinessPo> listUser(int pageIndex, int pageSize) {
		Paging<BusinessPo> paging = new Paging<>(pageIndex, pageSize);
		paging.setQueryProc(new IPagingQueryProc<BusinessPo>() {
			@Override
			public int queryTotal() {
				return businessUserDao.countUser();
			}

			@Override
			public List<BusinessPo> queryData(int startRow, int pageSize) {
				return businessUserDao.findUserList(null, startRow, pageSize);
			}
		});
		return paging;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = OperationException.class)
	public void updatePassword(int userId, String oldPassword, String newPassword) throws OperationException {
		BusinessUserPO user = businessUserDao.getUser(userId);
		if (user == null) {
			throw new OperationException("用户不存在", ErrorCode.UPDATE_NOT_ALLOW);
		}
		if (!user.getPassword().equals(MD5Utils.encryptPassword(oldPassword))) {
			throw new OperationException("旧密码不正确", ErrorCode.UPDATE_NOT_ALLOW);
		}
		try {
			businessUserDao.updatePassword(userId, MD5Utils.encryptPassword(newPassword));
		} catch (Exception e) {
			throw new OperationException("修改密码出错", ErrorCode.UPDATE_NOT_ALLOW);
		}

	}

	/**
	 *     废弃
	 * @param businessUserPO
	 * @return
	 * @throws OperationException
	 */
	@Override
	public int addBusiness(BusinessUserPO businessUserPO) throws OperationException {
		String userName = businessUserPO.getName();
        // 通过名字判断是否有记录
		// 如果有记录就不做修改
		try {
			//BusinessUserPO userByUserName = businessUserDao.getUserByUserName(userName);
			/*if (userByUserName == null){
				int result = businessUserDao.saveBusiness(businessUserPO);
				return result;
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int saveBusiness(BusinessPo bp, String userName, String password) {
        String pwd = MD5Utils.encryptPassword(password);
        BusinessUserPO bup = new BusinessUserPO();
        bup.setUserName(userName);
        bup.setPassword(pwd);
        bup.setName(bp.getName());
        bup.setEmail(bp.getEmail());
        bup.setCreateDate(bp.getCreateDate());
        bup.setType(0); // TODO 客服添加的为管理员渠道商用户
        bup.setStatus(bp.getStatus());
        bup.setPhone(bp.getPhone());
        try {
            BusinessPo bbp = businessUserDao.getUserByUserName(bp.getName());
            if (bbp == null){
            int i = businessUserDao.saveBusiness(bp);
                bup.setSuperior(bp.getId());
                if (i != 0){
                int i1 = businessUserDao.saveBusinessUser(bup);
                if (i1 != 0){
                    return i1;
                     }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
	}

    @Override
    public int addbusinessUser(Integer id, BusinessUserPO bup) {
        String password = bup.getPassword();
        String pwd = MD5Utils.encryptPassword(password);
        bup.setSuperior(id);
	    bup.setType(1);
	    bup.setPassword(pwd);
        int i = businessUserDao.saveBusinessUser(bup);
        if (i != 0){
            return i;
        }
        return 0;
    }

    @Override
	public void updateBusiness(String userName,String name,String address,String phone,String email,Integer id) throws OperationException {
		try {
			businessUserDao.updateBusiness(userName,name,address,phone,email,id);
		}catch (Exception e) {
			throw new OperationException("更改渠道商失败", ErrorCodeEnum.REPEAT);
		}
		
	}

    @Override
    public List<BusinessPo> listUser(String name) {
        return businessUserDao.findUserList(name, null, null);
    }

    @Override
    public int findById(Integer orgId) {
        try {
            OrgUserPO user = orgUserDao.findUserByVerifyId(orgId);
            if (user == null){
               return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }
}
