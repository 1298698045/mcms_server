package net.mingsoft.custom.entity;

import net.mingsoft.base.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;

/**
* 部门管理实体
* @author t-think
* 创建日期：2021-6-27 12:29:09<br/>
* 历史修订：<br/>
*/
@TableName("department")
public class DepartmentEntity extends BaseEntity {

private static final long serialVersionUID = 1624768149895L;

	/**
	* 部门名称
	*/
	private String name;


	/**
	* 设置部门名称
	*/
	public void setName(String name) {
	this.name = name;
	}

	/**
	* 获取部门名称
	*/
	public String getName() {
	return this.name;
	}
}
