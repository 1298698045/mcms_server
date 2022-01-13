package net.mingsoft.custom.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.mingsoft.base.biz.impl.BaseBizImpl;
import net.mingsoft.base.dao.IBaseDao;
import net.mingsoft.custom.entity.AdvertisementEntity;
import net.mingsoft.custom.biz.IAdvertisementBiz;
import net.mingsoft.custom.dao.IAdvertisementDao;

/**
 * 广告管理持久化层
 * @author t-think
 * 创建日期：2021-9-17 15:34:40<br/>
 * 历史修订：<br/>
 */
 @Service("customadvertisementBizImpl")
public class AdvertisementBizImpl extends BaseBizImpl<IAdvertisementDao,AdvertisementEntity> implements IAdvertisementBiz {


	@Autowired
	private IAdvertisementDao advertisementDao;


	@Override
	protected IBaseDao getDao() {
		// TODO Auto-generated method stub
		return advertisementDao;
	}
}
