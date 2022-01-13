package net.mingsoft.custom.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.mingsoft.base.entity.ResultData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.*;
import net.mingsoft.custom.biz.IAdvertisementBiz;
import net.mingsoft.custom.entity.AdvertisementEntity;
import net.mingsoft.basic.util.BasicUtil;
import net.mingsoft.basic.util.StringUtil;
import net.mingsoft.basic.bean.EUListBean;
import net.mingsoft.basic.annotation.LogAnn;
import net.mingsoft.basic.constant.e.BusinessTypeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;
/**
 * 广告管理控制层
 * @author t-think
 * 创建日期：2021-9-17 15:34:40<br/>
 * 历史修订：<br/>
 */
@Api(value = "广告接口")
@Controller("customAdvertisementAction")
@RequestMapping("/${ms.manager.path}/cms/advertisement")
public class AdvertisementAction extends net.mingsoft.cms.action.BaseAction{


	/**
	 * 注入广告业务层
	 */
	@Autowired
	private IAdvertisementBiz advertisementBiz;

	/**
	 * 返回主界面index
	 */
	@GetMapping("/index")
	public String index(HttpServletResponse response,HttpServletRequest request){
		return "/cms/advertisement/index";
	}

	/**
	 * 查询广告列表
	 * @param advertisement 广告实体
	 */
	@ApiOperation(value = "查询广告列表接口")
	@ApiImplicitParams({
    	@ApiImplicitParam(name = "adposId", value = "广告位", required =false,paramType="query"),
    	@ApiImplicitParam(name = "name", value = "广告名称", required =false,paramType="query"),
    	@ApiImplicitParam(name = "flag", value = "广告类型", required =false,paramType="query"),
    	@ApiImplicitParam(name = "isEnabled", value = "是否开启", required =false,paramType="query"),
    	@ApiImplicitParam(name = "dateRange", value = "广告时间", required =false,paramType="query"),
    	@ApiImplicitParam(name = "link", value = "广告链接", required =false,paramType="query"),
    	@ApiImplicitParam(name = "contactPhone", value = "联系人电话", required =false,paramType="query"),
    	@ApiImplicitParam(name = "contactEmail", value = "联系人邮箱", required =false,paramType="query"),
    	@ApiImplicitParam(name = "contactName", value = "广告联系人", required =false,paramType="query"),
    	@ApiImplicitParam(name = "image", value = "广告图片", required =false,paramType="query"),
    	@ApiImplicitParam(name = "content", value = "广告内容", required =false,paramType="query"),
    	@ApiImplicitParam(name = "createBy", value = "创建人", required =false,paramType="query"),
    	@ApiImplicitParam(name = "createDate", value = "创建时间", required =false,paramType="query"),
    	@ApiImplicitParam(name = "updateBy", value = "修改人", required =false,paramType="query"),
    	@ApiImplicitParam(name = "updateDate", value = "修改时间", required =false,paramType="query"),
    	@ApiImplicitParam(name = "del", value = "删除标记", required =false,paramType="query"),
    	@ApiImplicitParam(name = "id", value = "编号", required =false,paramType="query"),
    })
	@RequestMapping(value ="/list",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ResultData list(@ModelAttribute @ApiIgnore AdvertisementEntity advertisement,HttpServletResponse response, HttpServletRequest request,@ApiIgnore ModelMap model,BindingResult result) {
		BasicUtil.startPage();
		List advertisementList = advertisementBiz.list(new QueryWrapper(advertisement));
		return ResultData.build().success(new EUListBean(advertisementList,(int)BasicUtil.endPage(advertisementList).getTotal()));
	}

	/**
	 * 返回编辑界面advertisement_form
	 */
	@GetMapping("/form")
	public String form(@ModelAttribute AdvertisementEntity advertisement,HttpServletResponse response,HttpServletRequest request,ModelMap model){
		return "/cms/advertisement/form";
	}
	/**
	 * 获取广告
	 * @param advertisement 广告实体
	 */
	@ApiOperation(value = "获取广告列表接口")
    @ApiImplicitParam(name = "id", value = "编号", required =true,paramType="query")
	@GetMapping("/get")
	@ResponseBody
	public ResultData get(@ModelAttribute @ApiIgnore AdvertisementEntity advertisement,HttpServletResponse response, HttpServletRequest request,@ApiIgnore ModelMap model){
		if(advertisement.getId()==null) {
			return ResultData.build().error();
		}
		AdvertisementEntity _advertisement = (AdvertisementEntity)advertisementBiz.getById(advertisement.getId());
		return ResultData.build().success(_advertisement);
	}

	@ApiOperation(value = "保存广告列表接口")
	 @ApiImplicitParams({
    	@ApiImplicitParam(name = "adposId", value = "广告位", required =true,paramType="query"),
    	@ApiImplicitParam(name = "name", value = "广告名称", required =true,paramType="query"),
    	@ApiImplicitParam(name = "flag", value = "广告类型", required =true,paramType="query"),
    	@ApiImplicitParam(name = "isEnabled", value = "是否开启", required =true,paramType="query"),
		@ApiImplicitParam(name = "dateRange", value = "广告时间", required =false,paramType="query"),
		@ApiImplicitParam(name = "link", value = "广告链接", required =false,paramType="query"),
		@ApiImplicitParam(name = "contactPhone", value = "联系人电话", required =false,paramType="query"),
		@ApiImplicitParam(name = "contactEmail", value = "联系人邮箱", required =false,paramType="query"),
		@ApiImplicitParam(name = "contactName", value = "广告联系人", required =false,paramType="query"),
		@ApiImplicitParam(name = "image", value = "广告图片", required =false,paramType="query"),
		@ApiImplicitParam(name = "content", value = "广告内容", required =false,paramType="query"),
		@ApiImplicitParam(name = "createBy", value = "创建人", required =false,paramType="query"),
		@ApiImplicitParam(name = "createDate", value = "创建时间", required =false,paramType="query"),
		@ApiImplicitParam(name = "updateBy", value = "修改人", required =false,paramType="query"),
		@ApiImplicitParam(name = "updateDate", value = "修改时间", required =false,paramType="query"),
		@ApiImplicitParam(name = "del", value = "删除标记", required =false,paramType="query"),
		@ApiImplicitParam(name = "id", value = "编号", required =false,paramType="query"),
	})

	/**
	* 保存广告
	* @param advertisement 广告实体
	*/
	@PostMapping("/save")
	@ResponseBody
	@LogAnn(title = "保存广告", businessType = BusinessTypeEnum.INSERT)
	@RequiresPermissions("custom:advertisement:save")
	public ResultData save(@ModelAttribute @ApiIgnore AdvertisementEntity advertisement, HttpServletResponse response, HttpServletRequest request) {
		//验证广告位的值是否合法
		if(StringUtils.isBlank(advertisement.getAdposId())){
			return ResultData.build().error(getResString("err.empty", this.getResString("adpos.id")));
		}
		//验证广告名称的值是否合法
		if(StringUtils.isBlank(advertisement.getName())){
			return ResultData.build().error(getResString("err.empty", this.getResString("name")));
		}
		if(!StringUtil.checkLength(advertisement.getName()+"", 0, 20)){
			return ResultData.build().error(getResString("err.length", this.getResString("name"), "0", "20"));
		}
		//验证广告类型的值是否合法
		if(StringUtils.isBlank(advertisement.getFlag())){
			return ResultData.build().error(getResString("err.empty", this.getResString("flag")));
		}
		//验证是否开启的值是否合法
		if(StringUtils.isBlank(advertisement.getIsEnabled())){
			return ResultData.build().error(getResString("err.empty", this.getResString("is.enabled")));
		}
		if(!StringUtil.checkLength(advertisement.getLink()+"", 0, 20)){
			return ResultData.build().error(getResString("err.length", this.getResString("link"), "0", "20"));
		}
		if(!StringUtil.checkLength(advertisement.getContactPhone()+"", 0, 20)){
			return ResultData.build().error(getResString("err.length", this.getResString("contact.phone"), "0", "20"));
		}
		if(!StringUtil.checkLength(advertisement.getContactEmail()+"", 0, 20)){
			return ResultData.build().error(getResString("err.length", this.getResString("contact.email"), "0", "20"));
		}
		if(!StringUtil.checkLength(advertisement.getContactName()+"", 0, 20)){
			return ResultData.build().error(getResString("err.length", this.getResString("contact.name"), "0", "20"));
		}
		advertisementBiz.save(advertisement);
		return ResultData.build().success(advertisement);
	}

	/**
	 * @param advertisements 广告实体
	 */
	@ApiOperation(value = "批量删除广告列表接口")
	@PostMapping("/delete")
	@ResponseBody
	@LogAnn(title = "删除广告", businessType = BusinessTypeEnum.DELETE)
	@RequiresPermissions("custom:advertisement:del")
	public ResultData delete(@RequestBody List<AdvertisementEntity> advertisements,HttpServletResponse response, HttpServletRequest request) {
		List<String> ids = (List)advertisements.stream().map((p) -> {
		return p.getId();
		}).collect(Collectors.toList());
		return this.advertisementBiz.removeByIds(ids) ? ResultData.build().success() : ResultData.build().error(this.getResString("err.error", new String[]{this.getResString("id")}));

	}

	/**
	*	更新广告列表
	* @param advertisement 广告实体
	*/
	 @ApiOperation(value = "更新广告列表接口")
	 @ApiImplicitParams({
	    @ApiImplicitParam(name = "id", value = "编号", required =true,paramType="query"),
    	@ApiImplicitParam(name = "adposId", value = "广告位", required =true,paramType="query"),
    	@ApiImplicitParam(name = "name", value = "广告名称", required =true,paramType="query"),
    	@ApiImplicitParam(name = "flag", value = "广告类型", required =true,paramType="query"),
    	@ApiImplicitParam(name = "isEnabled", value = "是否开启", required =true,paramType="query"),
		@ApiImplicitParam(name = "dateRange", value = "广告时间", required =false,paramType="query"),
		@ApiImplicitParam(name = "link", value = "广告链接", required =false,paramType="query"),
		@ApiImplicitParam(name = "contactPhone", value = "联系人电话", required =false,paramType="query"),
		@ApiImplicitParam(name = "contactEmail", value = "联系人邮箱", required =false,paramType="query"),
		@ApiImplicitParam(name = "contactName", value = "广告联系人", required =false,paramType="query"),
		@ApiImplicitParam(name = "image", value = "广告图片", required =false,paramType="query"),
		@ApiImplicitParam(name = "content", value = "广告内容", required =false,paramType="query"),
		@ApiImplicitParam(name = "createBy", value = "创建人", required =false,paramType="query"),
		@ApiImplicitParam(name = "createDate", value = "创建时间", required =false,paramType="query"),
		@ApiImplicitParam(name = "updateBy", value = "修改人", required =false,paramType="query"),
		@ApiImplicitParam(name = "updateDate", value = "修改时间", required =false,paramType="query"),
		@ApiImplicitParam(name = "del", value = "删除标记", required =false,paramType="query"),
		@ApiImplicitParam(name = "id", value = "编号", required =false,paramType="query"),
	})
	@PostMapping("/update")
	@ResponseBody
	@LogAnn(title = "更新广告", businessType = BusinessTypeEnum.UPDATE)
	@RequiresPermissions("custom:advertisement:update")
	public ResultData update(@ModelAttribute @ApiIgnore AdvertisementEntity advertisement, HttpServletResponse response,
			HttpServletRequest request) {
		//验证广告位的值是否合法
		if(StringUtils.isBlank(advertisement.getAdposId())){
			return ResultData.build().error(getResString("err.empty", this.getResString("adpos.id")));
		}
		//验证广告名称的值是否合法
		if(StringUtils.isBlank(advertisement.getName())){
			return ResultData.build().error(getResString("err.empty", this.getResString("name")));
		}
		if(!StringUtil.checkLength(advertisement.getName()+"", 0, 20)){
			return ResultData.build().error(getResString("err.length", this.getResString("name"), "0", "20"));
		}
		//验证广告类型的值是否合法
		if(StringUtils.isBlank(advertisement.getFlag())){
			return ResultData.build().error(getResString("err.empty", this.getResString("flag")));
		}
		//验证是否开启的值是否合法
		if(StringUtils.isBlank(advertisement.getIsEnabled())){
			return ResultData.build().error(getResString("err.empty", this.getResString("is.enabled")));
		}
		if(!StringUtil.checkLength(advertisement.getLink()+"", 0, 20)){
			return ResultData.build().error(getResString("err.length", this.getResString("link"), "0", "20"));
		}
		if(!StringUtil.checkLength(advertisement.getContactPhone()+"", 0, 20)){
			return ResultData.build().error(getResString("err.length", this.getResString("contact.phone"), "0", "20"));
		}
		if(!StringUtil.checkLength(advertisement.getContactEmail()+"", 0, 20)){
			return ResultData.build().error(getResString("err.length", this.getResString("contact.email"), "0", "20"));
		}
		if(!StringUtil.checkLength(advertisement.getContactName()+"", 0, 20)){
			return ResultData.build().error(getResString("err.length", this.getResString("contact.name"), "0", "20"));
		}
		advertisementBiz.updateById(advertisement);
		return ResultData.build().success(advertisement);
	}

}
