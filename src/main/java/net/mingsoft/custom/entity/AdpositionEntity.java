package net.mingsoft.custom.entity;

import net.mingsoft.base.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
/**
* 广告位实体
* @author t-think
* 创建日期：2021-9-17 15:34:41<br/>
* 历史修订：<br/>
*/
@TableName("adposition")
public class AdpositionEntity extends BaseEntity {

private static final long serialVersionUID = 1631864081073L;

	/**
	* 广告位名称
	*/
	private String name;
	/**
	* 广告位宽度
	*/
	private Integer width;
	/**
	* 广告位高度
	*/
	private Integer height;
	/**
	* 广告位描述
	*/
	private String description;


	/**
	* 设置广告位名称
	*/
	public void setName(String name) {
	this.name = name;
	}

	/**
	* 获取广告位名称
	*/
	public String getName() {
	return this.name;
	}
	/**
	* 设置广告位宽度
	*/
	public void setWidth(Integer width) {
	this.width = width;
	}

	/**
	* 获取广告位宽度
	*/
	public Integer getWidth() {
	return this.width;
	}
	/**
	* 设置广告位高度
	*/
	public void setHeight(Integer height) {
	this.height = height;
	}

	/**
	* 获取广告位高度
	*/
	public Integer getHeight() {
	return this.height;
	}
	/**
	* 设置广告位描述
	*/
	public void setDescription(String description) {
	this.description = description;
	}

	/**
	* 获取广告位描述
	*/
	public String getDescription() {
	return this.description;
	}
}
