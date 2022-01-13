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
import net.mingsoft.custom.biz.IAdpositionBiz;
import net.mingsoft.custom.entity.AdpositionEntity;
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
 * 广告位管理控制层
 * @author t-think
 * 创建日期：2021-9-17 15:34:41<br/>
 * 历史修订：<br/>
 */
@Api(value = "广告位接口")
@Controller("customAdpositionAction")
@RequestMapping("/${ms.manager.path}/cms/adposition")
public class AdpositionAction extends net.mingsoft.cms.action.BaseAction{


	/**
	 * 注入广告位业务层
	 */
	@Autowired
	private IAdpositionBiz adpositionBiz;

	/**
	 * 返回主界面index
	 */
	@GetMapping("/index")
	public String index(HttpServletResponse response,HttpServletRequest request){
		return "/cms/adposition/index";
	}

	/**
	 * 查询广告位列表
	 * @param adposition 广告位实体
	 */
	@ApiOperation(value = "查询广告位列表接口")
	@ApiImplicitParams({
    	@ApiImplicitParam(name = "name", value = "广告位名称", required =false,paramType="query"),
    	@ApiImplicitParam(name = "width", value = "广告位宽度", required =false,paramType="query"),
    	@ApiImplicitParam(name = "height", value = "广告位高度", required =false,paramType="query"),
    	@ApiImplicitParam(name = "description", value = "广告位描述", required =false,paramType="query"),
    	@ApiImplicitParam(name = "createBy", value = "创建人", required =false,paramType="query"),
    	@ApiImplicitParam(name = "createDate", value = "创建时间", required =false,paramType="query"),
    	@ApiImplicitParam(name = "updateBy", value = "修改人", required =false,paramType="query"),
    	@ApiImplicitParam(name = "updateDate", value = "修改时间", required =false,paramType="query"),
    	@ApiImplicitParam(name = "del", value = "删除标记", required =false,paramType="query"),
    	@ApiImplicitParam(name = "id", value = "编号", required =false,paramType="query"),
    })
	@RequestMapping(value ="/list",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ResultData list(@ModelAttribute @ApiIgnore AdpositionEntity adposition,HttpServletResponse response, HttpServletRequest request,@ApiIgnore ModelMap model,BindingResult result) {
		BasicUtil.startPage();
		List adpositionList = adpositionBiz.list(new QueryWrapper(adposition));
		return ResultData.build().success(new EUListBean(adpositionList,(int)BasicUtil.endPage(adpositionList).getTotal()));
	}

	/**
	 * 返回编辑界面adposition_form
	 */
	@GetMapping("/form")
	public String form(@ModelAttribute AdpositionEntity adposition,HttpServletResponse response,HttpServletRequest request,ModelMap model){
		return "/cms/adposition/form";
	}
	/**
	 * 获取广告位
	 * @param adposition 广告位实体
	 */
	@ApiOperation(value = "获取广告位列表接口")
    @ApiImplicitParam(name = "id", value = "编号", required =true,paramType="query")
	@GetMapping("/get")
	@ResponseBody
	public ResultData get(@ModelAttribute @ApiIgnore AdpositionEntity adposition,HttpServletResponse response, HttpServletRequest request,@ApiIgnore ModelMap model){
		if(adposition.getId()==null) {
			return ResultData.build().error();
		}
		AdpositionEntity _adposition = (AdpositionEntity)adpositionBiz.getById(adposition.getId());
		return ResultData.build().success(_adposition);
	}

	@ApiOperation(value = "保存广告位列表接口")
	 @ApiImplicitParams({
    	@ApiImplicitParam(name = "name", value = "广告位名称", required =true,paramType="query"),
    	@ApiImplicitParam(name = "width", value = "广告位宽度", required =true,paramType="query"),
    	@ApiImplicitParam(name = "height", value = "广告位高度", required =true,paramType="query"),
		@ApiImplicitParam(name = "description", value = "广告位描述", required =false,paramType="query"),
		@ApiImplicitParam(name = "createBy", value = "创建人", required =false,paramType="query"),
		@ApiImplicitParam(name = "createDate", value = "创建时间", required =false,paramType="query"),
		@ApiImplicitParam(name = "updateBy", value = "修改人", required =false,paramType="query"),
		@ApiImplicitParam(name = "updateDate", value = "修改时间", required =false,paramType="query"),
		@ApiImplicitParam(name = "del", value = "删除标记", required =false,paramType="query"),
		@ApiImplicitParam(name = "id", value = "编号", required =false,paramType="query"),
	})

	/**
	* 保存广告位
	* @param adposition 广告位实体
	*/
	@PostMapping("/save")
	@ResponseBody
	@LogAnn(title = "保存广告位", businessType = BusinessTypeEnum.INSERT)
	@RequiresPermissions("custom:adposition:save")
	public ResultData save(@ModelAttribute @ApiIgnore AdpositionEntity adposition, HttpServletResponse response, HttpServletRequest request) {
		//验证广告位名称的值是否合法
		if(StringUtils.isBlank(adposition.getName())){
			return ResultData.build().error(getResString("err.empty", this.getResString("name")));
		}
		if(!StringUtil.checkLength(adposition.getName()+"", 0, 11)){
			return ResultData.build().error(getResString("err.length", this.getResString("name"), "0", "11"));
		}
		//验证广告位宽度的值是否合法
		if(adposition.getWidth() == null){
			return ResultData.build().error(getResString("err.empty", this.getResString("width")));
		}
		if(!StringUtil.checkLength(adposition.getWidth()+"", 0, 11)){
			return ResultData.build().error(getResString("err.length", this.getResString("width"), "0", "11"));
		}
		//验证广告位高度的值是否合法
		if(adposition.getHeight() == null){
			return ResultData.build().error(getResString("err.empty", this.getResString("height")));
		}
		if(!StringUtil.checkLength(adposition.getHeight()+"", 0, 11)){
			return ResultData.build().error(getResString("err.length", this.getResString("height"), "0", "11"));
		}
		adpositionBiz.save(adposition);
		return ResultData.build().success(adposition);
	}

	/**
	 * @param adpositions 广告位实体
	 */
	@ApiOperation(value = "批量删除广告位列表接口")
	@PostMapping("/delete")
	@ResponseBody
	@LogAnn(title = "删除广告位", businessType = BusinessTypeEnum.DELETE)
	@RequiresPermissions("custom:adposition:del")
	public ResultData delete(@RequestBody List<AdpositionEntity> adpositions,HttpServletResponse response, HttpServletRequest request) {
		List<String> ids = (List)adpositions.stream().map((p) -> {
			return p.getId();
		}).collect(Collectors.toList());
		return this.adpositionBiz.removeByIds(ids) ? ResultData.build().success() : ResultData.build().error(this.getResString("err.error", new String[]{this.getResString("id")}));
	}

	/**
	*	更新广告位列表
	* @param adposition 广告位实体
	*/
	 @ApiOperation(value = "更新广告位列表接口")
	 @ApiImplicitParams({
	    @ApiImplicitParam(name = "id", value = "编号", required =true,paramType="query"),
    	@ApiImplicitParam(name = "name", value = "广告位名称", required =true,paramType="query"),
    	@ApiImplicitParam(name = "width", value = "广告位宽度", required =true,paramType="query"),
    	@ApiImplicitParam(name = "height", value = "广告位高度", required =true,paramType="query"),
		@ApiImplicitParam(name = "description", value = "广告位描述", required =false,paramType="query"),
		@ApiImplicitParam(name = "createBy", value = "创建人", required =false,paramType="query"),
		@ApiImplicitParam(name = "createDate", value = "创建时间", required =false,paramType="query"),
		@ApiImplicitParam(name = "updateBy", value = "修改人", required =false,paramType="query"),
		@ApiImplicitParam(name = "updateDate", value = "修改时间", required =false,paramType="query"),
		@ApiImplicitParam(name = "del", value = "删除标记", required =false,paramType="query"),
		@ApiImplicitParam(name = "id", value = "编号", required =false,paramType="query"),
	})
	@PostMapping("/update")
	@ResponseBody
	@LogAnn(title = "更新广告位", businessType = BusinessTypeEnum.UPDATE)
	@RequiresPermissions("custom:adposition:update")
	public ResultData update(@ModelAttribute @ApiIgnore AdpositionEntity adposition, HttpServletResponse response,
			HttpServletRequest request) {
		//验证广告位名称的值是否合法
		if(StringUtils.isBlank(adposition.getName())){
			return ResultData.build().error(getResString("err.empty", this.getResString("name")));
		}
		if(!StringUtil.checkLength(adposition.getName()+"", 0, 11)){
			return ResultData.build().error(getResString("err.length", this.getResString("name"), "0", "11"));
		}
		//验证广告位宽度的值是否合法
		if(adposition.getWidth() == null){
			return ResultData.build().error(getResString("err.empty", this.getResString("width")));
		}
		if(!StringUtil.checkLength(adposition.getWidth()+"", 0, 11)){
			return ResultData.build().error(getResString("err.length", this.getResString("width"), "0", "11"));
		}
		//验证广告位高度的值是否合法
		if(adposition.getHeight() == null){
			return ResultData.build().error(getResString("err.empty", this.getResString("height")));
		}
		if(!StringUtil.checkLength(adposition.getHeight()+"", 0, 11)){
			return ResultData.build().error(getResString("err.length", this.getResString("height"), "0", "11"));
		}
		adpositionBiz.updateById(adposition);
		return ResultData.build().success(adposition);
	}

}
