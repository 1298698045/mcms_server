


package net.mingsoft.cms.biz.impl;

import net.mingsoft.cms.biz.IHistoryLogBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.mingsoft.base.biz.impl.BaseBizImpl;
import net.mingsoft.base.dao.IBaseDao;
import net.mingsoft.cms.dao.ICmsHistoryLogDao;

/**
 * 文章浏览记录管理持久化层
 
 * 创建日期：2019-12-23 9:24:03<br/>
 * 历史修订：<br/>
 */
 @Service("cmshistoryLogBizImpl")
public class HistoryLogBizImpl extends BaseBizImpl implements IHistoryLogBiz {

	
	@Autowired
	private ICmsHistoryLogDao historyLogDao;
	
	
	@Override
	protected IBaseDao getDao() {
		// TODO Auto-generated method stub
		return historyLogDao;
	} 
}
