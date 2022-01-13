package net.mingsoft.custom.entity;

import net.mingsoft.base.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;

/**
* 附件管理实体
* @author t-think
* 创建日期：2021-8-20 16:48:45<br/>
* 历史修订：<br/>
*/
@TableName("attachment")
public class AttachmentEntity extends BaseEntity {

private static final long serialVersionUID = 1629449325761L;

	/**
	* 标题
	*/
	private String title;
	private String original;
	private String extension;
	private String size;
	
	/**
	* 附件
	*/
	private String url;
	private Integer type;


	/**
	* 设置标题
	*/
	public void setTitle(String title) {
		this.title = title;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}

	/**
	* 获取标题
	*/
	public String getTitle() {
		return this.title;
	}
	/**
	* 设置附件
	*/
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	* 获取附件
	*/
	public String getUrl() {
		return this.url;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
