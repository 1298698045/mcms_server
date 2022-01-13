package net.mingsoft.custom.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.mingsoft.base.entity.ResultData;
import net.mingsoft.custom.entity.VExpertEntity;
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
import net.mingsoft.custom.biz.IExpertBiz;
import net.mingsoft.custom.entity.ExpertEntity;
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
 * 专家管理管理控制层
 * @author t-think
 * 创建日期：2021-6-27 12:29:09<br/>
 * 历史修订：<br/>
 */
@Api(value = "专家管理接口")
@Controller("customExpertAction")
@RequestMapping("/${ms.manager.path}/cms/expert")
public class ExpertAction extends net.mingsoft.cms.action.BaseAction{


	/**
	 * 注入专家管理业务层
	 */
	@Autowired
	private IExpertBiz expertBiz;

	/**
	 * 返回主界面index
	 */
	@GetMapping("/index")
	public String index(HttpServletResponse response,HttpServletRequest request){
		return "/cms/expert/index";
	}

	/**
	 * 查询专家管理列表
	 * @param expert 专家管理实体
	 */
	@ApiOperation(value = "查询专家管理列表接口")
	@ApiImplicitParams({
    	@ApiImplicitParam(name = "name", value = "姓名", required =false,paramType="query"),
    	@ApiImplicitParam(name = "field", value = "领域", required =false,paramType="query"),
    	@ApiImplicitParam(name = "departmentId", value = "科室", required =false,paramType="query"),
    	@ApiImplicitParam(name = "goodAt", value = "擅长", required =false,paramType="query"),
    	@ApiImplicitParam(name = "technicalTitle", value = "职称", required =false,paramType="query"),
    	@ApiImplicitParam(name = "position", value = "职务", required =false,paramType="query"),
    	@ApiImplicitParam(name = "techingPost", value = "教学任职", required =false,paramType="query"),
    	@ApiImplicitParam(name = "briefIntro", value = "简介", required =false,paramType="query"),
    	@ApiImplicitParam(name = "introduce", value = "介绍", required =false,paramType="query"),
    	@ApiImplicitParam(name = "createBy", value = "创建人", required =false,paramType="query"),
    	@ApiImplicitParam(name = "createDate", value = "创建时间", required =false,paramType="query"),
    	@ApiImplicitParam(name = "updateBy", value = "修改人", required =false,paramType="query"),
    	@ApiImplicitParam(name = "updateDate", value = "修改时间", required =false,paramType="query"),
    	@ApiImplicitParam(name = "del", value = "删除标记", required =false,paramType="query"),
    	@ApiImplicitParam(name = "id", value = "编号", required =false,paramType="query"),
		@ApiImplicitParam(name = "jobNumber", value = "工号", required =false,paramType="query"),
		@ApiImplicitParam(name = "schedule", value = "出诊时间", required =false,paramType="query"),
		@ApiImplicitParam(name = "stoppingStart", value = "停诊开始时间", required =false,paramType="query"),
		@ApiImplicitParam(name = "stoppingEnd", value = "停诊截止时间", required =false,paramType="query"),
		@ApiImplicitParam(name = "contentType", value = "自定义类型", required =false,paramType="query"),
		@ApiImplicitParam(name = "contentSort", value = "自定义排序", required =false,paramType="query"),
    })
	@RequestMapping(value ="/list",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ResultData list(@ModelAttribute @ApiIgnore VExpertEntity expert,HttpServletResponse response, HttpServletRequest request,@ApiIgnore ModelMap model,BindingResult result) {
		BasicUtil.startPage();
		List expertList = expertBiz.listExperts(expert);
		return ResultData.build().success(new EUListBean(expertList,(int)BasicUtil.endPage(expertList).getTotal()));
	}

	/**
	 * 返回编辑界面expert_form
	 */
	@GetMapping("/form")
	public String form(@ModelAttribute ExpertEntity expert,HttpServletResponse response,HttpServletRequest request,ModelMap model){
		return "/cms/expert/form";
	}
	/**
	 * 获取专家管理
	 * @param expert 专家管理实体
	 */
	@ApiOperation(value = "获取专家管理列表接口")
    @ApiImplicitParam(name = "id", value = "编号", required =true,paramType="query")
	@GetMapping("/get")
	@ResponseBody
	public ResultData get(@ModelAttribute @ApiIgnore ExpertEntity expert,HttpServletResponse response, HttpServletRequest request,@ApiIgnore ModelMap model){
		if(expert.getId()==null) {
			return ResultData.build().error();
		}
		ExpertEntity _expert = (ExpertEntity)expertBiz.getById(expert.getId());
		return ResultData.build().success(_expert);
	}

	@ApiOperation(value = "保存专家管理列表接口")
	 @ApiImplicitParams({
    	@ApiImplicitParam(name = "name", value = "姓名", required =true,paramType="query"),
		@ApiImplicitParam(name = "field", value = "领域", required =false,paramType="query"),
    	@ApiImplicitParam(name = "departmentId", value = "科室", required =true,paramType="query"),
		@ApiImplicitParam(name = "goodAt", value = "擅长", required =false,paramType="query"),
		@ApiImplicitParam(name = "technicalTitle", value = "职称", required =false,paramType="query"),
		@ApiImplicitParam(name = "position", value = "职务", required =false,paramType="query"),
		@ApiImplicitParam(name = "techingPost", value = "教学任职", required =false,paramType="query"),
		@ApiImplicitParam(name = "briefIntro", value = "简介", required =false,paramType="query"),
		@ApiImplicitParam(name = "introduce", value = "介绍", required =false,paramType="query"),
		@ApiImplicitParam(name = "createBy", value = "创建人", required =false,paramType="query"),
		@ApiImplicitParam(name = "createDate", value = "创建时间", required =false,paramType="query"),
		@ApiImplicitParam(name = "updateBy", value = "修改人", required =false,paramType="query"),
		@ApiImplicitParam(name = "updateDate", value = "修改时间", required =false,paramType="query"),
		@ApiImplicitParam(name = "del", value = "删除标记", required =false,paramType="query"),
		@ApiImplicitParam(name = "id", value = "编号", required =false,paramType="query"),
		@ApiImplicitParam(name = "jobNumber", value = "工号", required =false,paramType="query"),
		@ApiImplicitParam(name = "schedule", value = "出诊时间", required =false,paramType="query"),
		@ApiImplicitParam(name = "stoppingStart", value = "停诊开始时间", required =false,paramType="query"),
		@ApiImplicitParam(name = "stoppingEnd", value = "停诊截止时间", required =false,paramType="query"),
	})

	/**
	* 保存专家管理
	* @param expert 专家管理实体
	*/
	@PostMapping("/save")
	@ResponseBody
	@LogAnn(title = "保存专家管理", businessType = BusinessTypeEnum.INSERT)
	@RequiresPermissions("cms:expert:save")
	public ResultData save(@ModelAttribute @ApiIgnore VExpertEntity expert, HttpServletResponse response, HttpServletRequest request) {
		//验证工号的值是否合法
		if(StringUtils.isBlank(expert.getJobNumber())){
			return ResultData.build().error(getResString("err.empty", this.getResString("name")));
		}
		if(!StringUtil.checkLength(expert.getJobNumber()+"", 0, 64)){
			return ResultData.build().error(getResString("err.length", this.getResString("name"), "0", "64"));
		}
		//验证姓名的值是否合法
		if(StringUtils.isBlank(expert.getName())){
			return ResultData.build().error(getResString("err.empty", this.getResString("name")));
		}
		if(!StringUtil.checkLength(expert.getName()+"", 0, 64)){
			return ResultData.build().error(getResString("err.length", this.getResString("name"), "0", "64"));
		}
		if(!StringUtil.checkLength(expert.getField()+"", 0, 64)){
			return ResultData.build().error(getResString("err.length", this.getResString("field"), "0", "64"));
		}
		//验证科室的值是否合法
		if(expert.getDepartmentId() == null){
			return ResultData.build().error(getResString("err.empty", this.getResString("department.id")));
		}
		if(!StringUtil.checkLength(expert.getGoodAt()+"", 0, 64)){
			return ResultData.build().error(getResString("err.length", this.getResString("good.at"), "0", "64"));
		}
		if(!StringUtil.checkLength(expert.getTechnicalTitle()+"", 0, 64)){
			return ResultData.build().error(getResString("err.length", this.getResString("technical.title"), "0", "64"));
		}
		if(!StringUtil.checkLength(expert.getPosition()+"", 0, 64)){
			return ResultData.build().error(getResString("err.length", this.getResString("position"), "0", "64"));
		}
		if(!StringUtil.checkLength(expert.getTechingPost()+"", 0, 64)){
			return ResultData.build().error(getResString("err.length", this.getResString("teching.post"), "0", "64"));
		}
		expertBiz.save(expert);
		return ResultData.build().success(expert);
	}

	/**
	 * @param experts 专家管理实体
	 */
	@ApiOperation(value = "批量删除专家管理列表接口")
	@PostMapping("/delete")
	@ResponseBody
	@LogAnn(title = "删除专家管理", businessType = BusinessTypeEnum.DELETE)
	@RequiresPermissions("cms:expert:del")
	public ResultData delete(@RequestBody List<ExpertEntity> experts,HttpServletResponse response, HttpServletRequest request) {
		List<String> ids = (List)experts.stream().map((p) -> {
		return p.getId();
		}).collect(Collectors.toList());
		return this.expertBiz.removeByIds(ids) ? ResultData.build().success() : ResultData.build().error(this.getResString("err.error", new String[]{this.getResString("id")}));

		}

	/**
	*	更新专家管理列表
	* @param expert 专家管理实体
	*/
	 @ApiOperation(value = "更新专家管理列表接口")
	 @ApiImplicitParams({
	    @ApiImplicitParam(name = "id", value = "编号", required =true,paramType="query"),
    	@ApiImplicitParam(name = "name", value = "姓名", required =true,paramType="query"),
		@ApiImplicitParam(name = "field", value = "领域", required =false,paramType="query"),
    	@ApiImplicitParam(name = "departmentId", value = "科室", required =true,paramType="query"),
		@ApiImplicitParam(name = "goodAt", value = "擅长", required =false,paramType="query"),
		@ApiImplicitParam(name = "technicalTitle", value = "职称", required =false,paramType="query"),
		@ApiImplicitParam(name = "position", value = "职务", required =false,paramType="query"),
		@ApiImplicitParam(name = "techingPost", value = "教学任职", required =false,paramType="query"),
		@ApiImplicitParam(name = "briefIntro", value = "简介", required =false,paramType="query"),
		@ApiImplicitParam(name = "introduce", value = "介绍", required =false,paramType="query"),
		@ApiImplicitParam(name = "createBy", value = "创建人", required =false,paramType="query"),
		@ApiImplicitParam(name = "createDate", value = "创建时间", required =false,paramType="query"),
		@ApiImplicitParam(name = "updateBy", value = "修改人", required =false,paramType="query"),
		@ApiImplicitParam(name = "updateDate", value = "修改时间", required =false,paramType="query"),
		@ApiImplicitParam(name = "del", value = "删除标记", required =false,paramType="query"),
		@ApiImplicitParam(name = "id", value = "编号", required =false,paramType="query"),
		@ApiImplicitParam(name = "jobNumber", value = "工号", required =false,paramType="query"),
		@ApiImplicitParam(name = "schedule", value = "出诊时间", required =false,paramType="query"),
		@ApiImplicitParam(name = "stoppingStart", value = "停诊开始时间", required =false,paramType="query"),
		@ApiImplicitParam(name = "stoppingEnd", value = "停诊截止时间", required =false,paramType="query"),
	})
	@PostMapping("/update")
	@ResponseBody
	@LogAnn(title = "更新专家管理", businessType = BusinessTypeEnum.UPDATE)
	@RequiresPermissions("cms:expert:update")
	public ResultData update(@ModelAttribute @ApiIgnore VExpertEntity expert, HttpServletResponse response,
			HttpServletRequest request) {
		//验证工号的值是否合法
		if(StringUtils.isBlank(expert.getJobNumber())){
			return ResultData.build().error(getResString("err.empty", this.getResString("name")));
		}
		if(!StringUtil.checkLength(expert.getJobNumber()+"", 0, 64)){
			return ResultData.build().error(getResString("err.length", this.getResString("name"), "0", "64"));
		}
		
		//验证姓名的值是否合法
		if(StringUtils.isBlank(expert.getName())){
			return ResultData.build().error(getResString("err.empty", this.getResString("name")));
		}
		if(!StringUtil.checkLength(expert.getName()+"", 0, 64)){
			return ResultData.build().error(getResString("err.length", this.getResString("name"), "0", "64"));
		}
		if(!StringUtil.checkLength(expert.getField()+"", 0, 64)){
			return ResultData.build().error(getResString("err.length", this.getResString("field"), "0", "64"));
		}
		//验证科室的值是否合法
		if(expert.getDepartmentId() == null){
			return ResultData.build().error(getResString("err.empty", this.getResString("department.id")));
		}
		if(!StringUtil.checkLength(expert.getGoodAt()+"", 0, 64)){
			return ResultData.build().error(getResString("err.length", this.getResString("good.at"), "0", "64"));
		}
		if(!StringUtil.checkLength(expert.getTechnicalTitle()+"", 0, 64)){
			return ResultData.build().error(getResString("err.length", this.getResString("technical.title"), "0", "64"));
		}
		if(!StringUtil.checkLength(expert.getPosition()+"", 0, 64)){
			return ResultData.build().error(getResString("err.length", this.getResString("position"), "0", "64"));
		}
		if(!StringUtil.checkLength(expert.getTechingPost()+"", 0, 64)){
			return ResultData.build().error(getResString("err.length", this.getResString("teching.post"), "0", "64"));
		}
		expertBiz.updateById(expert);
		return ResultData.build().success(expert);
	}

}
