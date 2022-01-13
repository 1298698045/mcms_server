package net.mingsoft.custom.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import net.mingsoft.base.entity.BaseEntity;

/**
* 专家管理实体
* @author t-think
* 创建日期：2021-6-27 12:29:09<br/>
* 历史修订：<br/>
*/
@TableName("expert")
public class VExpertEntity extends ExpertEntity {

private static final long serialVersionUID = 1624768149979L;

	private String departmentName;
	private String departmentPath;

	public VExpertEntity() {
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentPath() {
		return departmentPath;
	}

	public void setDepartmentPath(String departmentPath) {
		this.departmentPath = departmentPath;
	}
}
