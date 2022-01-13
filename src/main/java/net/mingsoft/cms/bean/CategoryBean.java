package net.mingsoft.cms.bean;

import net.mingsoft.cms.entity.CategoryEntity;

/**
* 文章实体
* 创建日期：2019-11-28 15:12:32<br/>
* 历史修订：<br/>
*/
public class CategoryBean extends CategoryEntity {

	/**
	 * 文章编号
	 */
	private String articleId;




	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
}
