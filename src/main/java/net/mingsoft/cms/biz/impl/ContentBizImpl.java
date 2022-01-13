


package net.mingsoft.cms.biz.impl;

import net.mingsoft.base.biz.impl.BaseBizImpl;
import net.mingsoft.base.dao.IBaseDao;
import net.mingsoft.cms.bean.CategoryBean;
import net.mingsoft.cms.bean.ContentBean;
import net.mingsoft.cms.biz.IContentBiz;
import net.mingsoft.cms.dao.ICategoryDao;
import net.mingsoft.cms.dao.IContentDao;
import net.mingsoft.cms.entity.ContentEntity;
import net.mingsoft.mdiy.entity.ModelEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 文章管理持久化层
 
 * 创建日期：2019-11-28 15:12:32<br/>
 * 历史修订：<br/>
 */
 @Service("cmscontentBizImpl")
public class ContentBizImpl  extends BaseBizImpl<IContentDao, ContentEntity> implements IContentBiz {

	/*
	 * log4j日志记录
	 */
	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IContentDao contentDao;
	/**
	 * 栏目管理业务层
	 */
	@Autowired
	private ICategoryDao categoryDao;

	@Value("${ms.diy.html-dir:html}")
	private String htmlDir;


	@Override
	protected IBaseDao getDao() {
		// TODO Auto-generated method stub
		return contentDao;
	}

	@Override
	public List<CategoryBean> queryIdsByCategoryIdForParser(ContentBean contentBean) {
		return this.contentDao.queryIdsByCategoryIdForParser(contentBean);
	}

	@Override
	public int getSearchCount(ModelEntity contentModel, List diyList, Map whereMap, int appId, String categoryIds) {
		if (contentModel!=null) {
			return contentDao.getSearchCount(contentModel.getModelTableName(),diyList,whereMap, appId,categoryIds);
		}
		return contentDao.getSearchCount(null,null,whereMap, appId,categoryIds);
	}


}
