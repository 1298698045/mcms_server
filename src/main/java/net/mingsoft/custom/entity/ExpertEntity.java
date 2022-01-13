package net.mingsoft.custom.entity;

import net.mingsoft.base.entity.BaseEntity;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;

/**
* 专家管理实体
* @author t-think
* 创建日期：2021-6-27 12:29:09<br/>
* 历史修订：<br/>
*/
@TableName("expert")
public class ExpertEntity extends BaseEntity {

private static final long serialVersionUID = 1624768149979L;

	private String portrait;
	private Integer gender;
	private String jobNumber;
	private String schedule;
	private String stoppingStart;
	private String stoppingEnd;
	private String contentType;
	private Integer contentSort;
	/**
	* 姓名
	*/
	private String name;
	/**
	* 领域
	*/
	private String field;
	/**
	* 科室
	*/
	private String departmentId;
	/**
	* 擅长
	*/
	private String goodAt;
	/**
	* 职称
	*/
	private String technicalTitle;
	/**
	* 职务
	*/
	private String position;
	/**
	* 教学任职
	*/
	private String techingPost;
	/**
	* 简介
	*/
	private String briefIntro;
	/**
	* 介绍
	*/
	private String introduce;

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getStoppingEnd() {
		return stoppingEnd;
	}

	public void setStoppingEnd(String stoppingEnd) {
		this.stoppingEnd = stoppingEnd;
	}

	public String getStoppingStart() {
		return stoppingStart;
	}

	public void setStoppingStart(String stoppingStart) {
		this.stoppingStart = stoppingStart;
	}

	public String getJobNumber() {
		return this.jobNumber;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public String getSchedule() {
		return this.schedule;
	}

	/**
	* 设置姓名
	*/
	public void setName(String name) {
		this.name = name;
	}

	/**
	* 获取姓名
	*/
	public String getName() {
		return this.name;
	}
	/**
	* 设置领域
	*/
	public void setField(String field) {
	this.field = field;
	}

	/**
	* 获取领域
	*/
	public String getField() {
		return this.field;
	}
	/**
	* 设置科室
	*/
	public void setDepartmentId(String departmentId) {
	this.departmentId = departmentId;
	}

	/**
	* 获取科室
	*/
	public String getDepartmentId() {
		return this.departmentId;
	}
	/**
	* 设置擅长
	*/
	public void setGoodAt(String goodAt) {
		this.goodAt = goodAt;
	}

	/**
	* 获取擅长
	*/
	public String getGoodAt() {
	return this.goodAt;
	}
	/**
	* 设置职称
	*/
	public void setTechnicalTitle(String technicalTitle) {
		this.technicalTitle = technicalTitle;
	}

	/**
	* 获取职称
	*/
	public String getTechnicalTitle() {
		return this.technicalTitle;
	}
	/**
	* 设置职务
	*/
	public void setPosition(String position) {
	this.position = position;
	}

	/**
	* 获取职务
	*/
	public String getPosition() {
	return this.position;
	}
	/**
	* 设置教学任职
	*/
	public void setTechingPost(String techingPost) {
	this.techingPost = techingPost;
	}

	/**
	* 获取教学任职
	*/
	public String getTechingPost() {
	return this.techingPost;
	}
	/**
	* 设置简介
	*/
	public void setBriefIntro(String briefIntro) {
	this.briefIntro = briefIntro;
	}

	/**
	* 获取简介
	*/
	public String getBriefIntro() {
	return this.briefIntro;
	}
	/**
	* 设置介绍
	*/
	public void setIntroduce(String introduce) {
	this.introduce = introduce;
	}

	/**
	* 获取介绍
	*/
	public String getIntroduce() {
	return this.introduce;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Integer getContentSort() {
		return contentSort;
	}

	public void setContentSort(Integer contentSort) {
		this.contentSort = contentSort;
	}
}
