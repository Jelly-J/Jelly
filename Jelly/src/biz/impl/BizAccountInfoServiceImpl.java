package biz.impl;

import biz.service.BizAccountInfoService;
import db.model.AccountInfo;
import db.service.DbAccountInfoService;

public class BizAccountInfoServiceImpl implements BizAccountInfoService {

	private DbAccountInfoService dbAccountInfoService;
	
	public AccountInfo listAccountInfo() {
		
//		String name = listAccountInfoName();
		
		return dbAccountInfoService.listAccountInfo();
	}

	public void setDbAccountInfoService(DbAccountInfoService dbAccountInfoService) {
		this.dbAccountInfoService = dbAccountInfoService;
	}

	public String listAccountInfoName(){
		return null;
	}

}
