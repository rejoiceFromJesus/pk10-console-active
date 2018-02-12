/**
 * 系统项目名称
 * com.pk10.active.console.service
 * UserService.java
 * 
 * 2017年6月9日-下午2:49:27
 *  2017金融街在线公司-版权所有
 *
 */
package com.pk10.active.console.service;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import tk.mybatis.mapper.util.StringUtil;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pk10.active.console.common.util.RejoiceUtil;
import com.pk10.active.console.entity.User;

/**
 *
 * UserService
 * 
 * @author rejoice 948870341@qq.com
 * @date 2017年6月9日 下午2:49:27
 * 
 * @version 1.0.0
 *
 */
@Service
@Transactional
public class UserService extends BaseService<User> {

	
	/* (non-Javadoc)
	 * @see com.pk10.active.console.service.BaseService#save(java.lang.Object)
	 */
	@Override
	public void save(User user) throws Exception {
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		super.save(user);
	}
	
	/* (non-Javadoc)
	 * @see com.pk10.active.console.service.BaseService#updateByIdSelective(java.lang.Object)
	 */  
	@Override    
	public void updateByIdSelective(User t){
		if(!t.getOldPassword().equals(t.getPassword())){ 
			t.setPassword(DigestUtils.md5Hex(t.getPassword()));
		}  
		super.updateByIdSelective(t);  
	} 
	 
}