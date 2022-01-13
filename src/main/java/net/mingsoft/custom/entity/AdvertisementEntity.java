package net.mingsoft.custom.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import net.mingsoft.base.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
/**
* 广告实体
* @author t-think
* 创建日期：2021-9-17 15:34:40<br/>
* 历史修订：<br/>
*/
@TableName("advertisement")
public class AdvertisementEntity extends BaseEntity {

private static final long serialVersionUID = 1631864080970L;

	/**
	* 广告位
	*/
	private String adposId;
	/**
	* 广告名称
	*/
	private String name;
	/**
	* 广告类型
	*/
	private String flag;
	/**
	* 是否开启
	*/
	private String isEnabled;
	/**
	* 广告开始时间
	*/
	@JSONField(format = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	private Date dateStart;
	/**
	 * 广告结束时间
	 */
	@JSONField(format = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	private Date dateEnd;
	/**
	* 广告链接
	*/
	private String link;
	/**
	* 联系人电话
	*/
	private String contactPhone;
	/**
	* 联系人邮箱
	*/
	private String contactEmail;
	/**
	* 广告联系人
	*/
	private String contactName;
	/**
	* 广告图片
	*/
	private String image;
	/**
	* 广告内容
	*/
	private String content;


	/**
	* 设置广告位
	*/
	public void setAdposId(String adposId) {
	this.adposId = adposId;
	}

	/**
	* 获取广告位
	*/
	public String getAdposId() {
	return this.adposId;
	}
	/**
	* 设置广告名称
	*/
	public void setName(String name) {
	this.name = name;
	}

	/**
	* 获取广告名称
	*/
	public String getName() {
	return this.name;
	}
	/**
	* 设置广告类型
	*/
	public void setFlag(String flag) {
	this.flag = flag;
	}

	/**
	* 获取广告类型
	*/
	public String getFlag() {
	return this.flag;
	}
	/**
	* 设置是否开启
	*/
	public void setIsEnabled(String isEnabled) {
	this.isEnabled = isEnabled;
	}

	/**
	* 获取是否开启
	*/
	public String getIsEnabled() {
	return this.isEnabled;
	}
	/**
	* 设置广告时间
	*/
	public void setDateStart(Date dateStart) {
	this.dateStart = dateStart;
	}

	/**
	* 获取广告时间
	*/
	public Date getDateStart() {
	return this.dateStart;
	}
	/**
	* 设置广告链接
	*/
	public void setLink(String link) {
	this.link = link;
	}

	/**
	* 获取广告链接
	*/
	public String getLink() {
	return this.link;
	}
	/**
	* 设置联系人电话
	*/
	public void setContactPhone(String contactPhone) {
	this.contactPhone = contactPhone;
	}

	/**
	* 获取联系人电话
	*/
	public String getContactPhone() {
	return this.contactPhone;
	}
	/**
	* 设置联系人邮箱
	*/
	public void setContactEmail(String contactEmail) {
	this.contactEmail = contactEmail;
	}

	/**
	* 获取联系人邮箱
	*/
	public String getContactEmail() {
	return this.contactEmail;
	}
	/**
	* 设置广告联系人
	*/
	public void setContactName(String contactName) {
	this.contactName = contactName;
	}

	/**
	* 获取广告联系人
	*/
	public String getContactName() {
	return this.contactName;
	}
	/**
	* 设置广告图片
	*/
	public void setImage(String image) {
	this.image = image;
	}

	/**
	* 获取广告图片
	*/
	public String getImage() {
	return this.image;
	}
	/**
	* 设置广告内容
	*/
	public void setContent(String content) {
	this.content = content;
	}

	/**
	* 获取广告内容
	*/
	public String getContent() {
	return this.content;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
}
