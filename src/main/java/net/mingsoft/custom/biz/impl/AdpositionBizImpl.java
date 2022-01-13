package net.mingsoft.custom.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.mingsoft.base.biz.impl.BaseBizImpl;
import net.mingsoft.base.dao.IBaseDao;
import net.mingsoft.custom.entity.AdpositionEntity;
import net.mingsoft.custom.biz.IAdpositionBiz;
import net.mingsoft.custom.dao.IAdpositionDao;

/**
 * 广告位管理持久化层
 * @author t-think
 * 创建日期：2021-9-17 15:34:41<br/>
 * 历史修订：<br/>
 */
 @Service("customadpositionBizImpl")
public class AdpositionBizImpl extends BaseBizImpl<IAdpositionDao,AdpositionEntity> implements IAdpositionBiz {


	@Autowired
	private IAdpositionDao adpositionDao;


	@Override
	protected IBaseDao getDao() {
		// TODO Auto-generated method stub
		return adpositionDao;
	}
}
