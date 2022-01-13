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
import net.mingsoft.custom.biz.IDepartmentBiz;
import net.mingsoft.custom.entity.DepartmentEntity;
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
 * 部门管理管理控制层
 * @author t-think
 * 创建日期：2021-6-27 12:29:09<br/>
 * 历史修订：<br/>
 */
@Api(value = "部门管理接口")
@Controller("customDepartmentAction")
@RequestMapping("/${ms.manager.path}/cms/department")
public class DepartmentAction extends net.mingsoft.cms.action.BaseAction{


	/**
	 * 注入部门管理业务层
	 */
	@Autowired
	private IDepartmentBiz departmentBiz;

	/**
	 * 返回主界面index
	 */
	@GetMapping("/index")
	public String index(HttpServletResponse response,HttpServletRequest request){
		return "/cms/department/index";
	}

	/**
	 * 查询部门管理列表
	 * @param department 部门管理实体
	 */
	@ApiOperation(value = "查询部门管理列表接口")
	@ApiImplicitParams({
    	@ApiImplicitParam(name = "name", value = "部门名称", required =false,paramType="query"),
    	@ApiImplicitParam(name = "createBy", value = "创建人", required =false,paramType="query"),
    	@ApiImplicitParam(name = "createDate", value = "创建时间", required =false,paramType="query"),
    	@ApiImplicitParam(name = "updateBy", value = "修改人", required =false,paramType="query"),
    	@ApiImplicitParam(name = "updateDate", value = "修改时间", required =false,paramType="query"),
    	@ApiImplicitParam(name = "del", value = "删除标记", required =false,paramType="query"),
    	@ApiImplicitParam(name = "id", value = "编号", required =false,paramType="query"),
    })
	@RequestMapping(value ="/list",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ResultData list(@ModelAttribute @ApiIgnore DepartmentEntity department,HttpServletResponse response, HttpServletRequest request,@ApiIgnore ModelMap model,BindingResult result) {
		BasicUtil.startPage();
		List departmentList = departmentBiz.list(new QueryWrapper(department));
		return ResultData.build().success(new EUListBean(departmentList,(int)BasicUtil.endPage(departmentList).getTotal()));
	}

	/**
	 * 返回编辑界面department_form
	 */
	@GetMapping("/form")
	public String form(@ModelAttribute DepartmentEntity department,HttpServletResponse response,HttpServletRequest request,ModelMap model){
		return "/cms/department/form";
	}
	/**
	 * 获取部门管理
	 * @param department 部门管理实体
	 */
	@ApiOperation(value = "获取部门管理列表接口")
    @ApiImplicitParam(name = "id", value = "编号", required =true,paramType="query")
	@GetMapping("/get")
	@ResponseBody
	public ResultData get(@ModelAttribute @ApiIgnore DepartmentEntity department,HttpServletResponse response, HttpServletRequest request,@ApiIgnore ModelMap model){
		if(department.getId()==null) {
			return ResultData.build().error();
		}
		DepartmentEntity _department = (DepartmentEntity)departmentBiz.getById(department.getId());
		return ResultData.build().success(_department);
	}

	@ApiOperation(value = "保存部门管理列表接口")
	 @ApiImplicitParams({
    	@ApiImplicitParam(name = "name", value = "部门名称", required =true,paramType="query"),
		@ApiImplicitParam(name = "createBy", value = "创建人", required =false,paramType="query"),
		@ApiImplicitParam(name = "createDate", value = "创建时间", required =false,paramType="query"),
		@ApiImplicitParam(name = "updateBy", value = "修改人", required =false,paramType="query"),
		@ApiImplicitParam(name = "updateDate", value = "修改时间", required =false,paramType="query"),
		@ApiImplicitParam(name = "del", value = "删除标记", required =false,paramType="query"),
		@ApiImplicitParam(name = "id", value = "编号", required =false,paramType="query"),
	})

	/**
	* 保存部门管理
	* @param department 部门管理实体
	*/
	@PostMapping("/save")
	@ResponseBody
	@LogAnn(title = "保存部门管理", businessType = BusinessTypeEnum.INSERT)
	@RequiresPermissions("cms:department:save")
	public ResultData save(@ModelAttribute @ApiIgnore DepartmentEntity department, HttpServletResponse response, HttpServletRequest request) {
		//验证部门名称的值是否合法
		if(StringUtils.isBlank(department.getName())){
			return ResultData.build().error(getResString("err.empty", this.getResString("name")));
		}
		if(!StringUtil.checkLength(department.getName()+"", 0, 64)){
			return ResultData.build().error(getResString("err.length", this.getResString("name"), "0", "64"));
		}
		departmentBiz.save(department);
		return ResultData.build().success(department);
	}

	/**
	 * @param departments 部门管理实体
	 */
	@ApiOperation(value = "批量删除部门管理列表接口")
	@PostMapping("/delete")
	@ResponseBody
	@LogAnn(title = "删除部门管理", businessType = BusinessTypeEnum.DELETE)
	@RequiresPermissions("cms:department:del")
	public ResultData delete(@RequestBody List<DepartmentEntity> departments,HttpServletResponse response, HttpServletRequest request) {
		List<String> ids = (List)departments.stream().map((p) -> {
		return p.getId();
		}).collect(Collectors.toList());
		return this.departmentBiz.removeByIds(ids) ? ResultData.build().success() : ResultData.build().error(this.getResString("err.error", new String[]{this.getResString("id")}));

		}

	/**
	*	更新部门管理列表
	* @param department 部门管理实体
	*/
	 @ApiOperation(value = "更新部门管理列表接口")
	 @ApiImplicitParams({
	    @ApiImplicitParam(name = "id", value = "编号", required =true,paramType="query"),
    	@ApiImplicitParam(name = "name", value = "部门名称", required =true,paramType="query"),
		@ApiImplicitParam(name = "createBy", value = "创建人", required =false,paramType="query"),
		@ApiImplicitParam(name = "createDate", value = "创建时间", required =false,paramType="query"),
		@ApiImplicitParam(name = "updateBy", value = "修改人", required =false,paramType="query"),
		@ApiImplicitParam(name = "updateDate", value = "修改时间", required =false,paramType="query"),
		@ApiImplicitParam(name = "del", value = "删除标记", required =false,paramType="query"),
		@ApiImplicitParam(name = "id", value = "编号", required =false,paramType="query"),
	})
	@PostMapping("/update")
	@ResponseBody
	@LogAnn(title = "更新部门管理", businessType = BusinessTypeEnum.UPDATE)
	@RequiresPermissions("cms:department:update")
	public ResultData update(@ModelAttribute @ApiIgnore DepartmentEntity department, HttpServletResponse response,
			HttpServletRequest request) {
		//验证部门名称的值是否合法
		if(StringUtils.isBlank(department.getName())){
			return ResultData.build().error(getResString("err.empty", this.getResString("name")));
		}
		if(!StringUtil.checkLength(department.getName()+"", 0, 64)){
			return ResultData.build().error(getResString("err.length", this.getResString("name"), "0", "64"));
		}
		departmentBiz.updateById(department);
		return ResultData.build().success(department);
	}

}
