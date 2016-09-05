package db.impl;

import db.mapper.AccountInfoMapper;
import db.model.AccountInfo;
import db.service.DbAccountInfoService;

public class DbAccountInfoServiceImpl implements DbAccountInfoService {

	private AccountInfoMapper accountInfoMapper;

	public AccountInfo listAccountInfo() {

		return accountInfoMapper.selectByPrimaryKey(110L);
	}

	public void setAccountInfoMapper(AccountInfoMapper accountInfoMapper) {
		this.accountInfoMapper = accountInfoMapper;
	}

}
