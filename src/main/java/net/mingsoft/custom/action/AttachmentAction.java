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
import net.mingsoft.custom.biz.IAttachmentBiz;
import net.mingsoft.custom.entity.AttachmentEntity;
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
 * 附件管理管理控制层
 * @author t-think
 * 创建日期：2021-8-20 16:48:45<br/>
 */
@Api(value = "附件管理接口")
@Controller("customAttachmentAction")
@RequestMapping("/${ms.manager.path}/cms/attachment")
public class AttachmentAction extends net.mingsoft.cms.action.BaseAction{

	/**
	 * 注入附件管理业务层
	 */
	@Autowired
	private IAttachmentBiz attachmentBiz;

	/**
	 * 返回主界面index
	 */
	@GetMapping("/index")
	public String index(HttpServletResponse response,HttpServletRequest request){
		return "/cms/attachment/index";
	}

	/**
	 * 查询附件管理列表
	 * @param attachment 附件管理实体
	 */
	@ApiOperation(value = "查询附件管理列表接口")
	@ApiImplicitParams({
    	@ApiImplicitParam(name = "title", value = "标题", required =false,paramType="query"),
    	@ApiImplicitParam(name = "url", value = "附件", required =false,paramType="query"),
		@ApiImplicitParam(name = "extension", value = "扩展名", required =false,paramType="query"),
		@ApiImplicitParam(name = "size", value = "大小", required =false,paramType="query"),
		@ApiImplicitParam(name = "type", value = "类型", required =false,paramType="query"),
    	@ApiImplicitParam(name = "createBy", value = "创建人", required =false,paramType="query"),
    	@ApiImplicitParam(name = "createDate", value = "创建时间", required =false,paramType="query"),
    	@ApiImplicitParam(name = "updateBy", value = "修改人", required =false,paramType="query"),
    	@ApiImplicitParam(name = "updateDate", value = "修改时间", required =false,paramType="query"),
    	@ApiImplicitParam(name = "del", value = "删除标记", required =false,paramType="query"),
    	@ApiImplicitParam(name = "id", value = "编号", required =false,paramType="query"),
    })
	@RequestMapping(value ="/list",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ResultData list(@ModelAttribute @ApiIgnore AttachmentEntity attachment,HttpServletResponse response, HttpServletRequest request,@ApiIgnore ModelMap model,BindingResult result) {
		BasicUtil.startPage();
		List<AttachmentEntity> attachmentList = attachmentBiz.list(new QueryWrapper<AttachmentEntity>(attachment).orderByDesc("create_date"));
		return ResultData.build().success(new EUListBean(attachmentList,(int)BasicUtil.endPage(attachmentList).getTotal()));
	}

	/**
	 * 返回编辑界面attachment_form
	 */
	@GetMapping("/form")
	public String form(@ModelAttribute AttachmentEntity attachment,HttpServletResponse response,HttpServletRequest request,ModelMap model){
		return "/cms/attachment/form";
	}
	/**
	 * 获取附件管理
	 * @param attachment 附件管理实体
	 */
	@ApiOperation(value = "获取附件管理列表接口")
    @ApiImplicitParam(name = "id", value = "编号", required =true,paramType="query")
	@GetMapping("/get")
	@ResponseBody
	public ResultData get(@ModelAttribute @ApiIgnore AttachmentEntity attachment,HttpServletResponse response, HttpServletRequest request,@ApiIgnore ModelMap model){
		if(attachment.getId()==null) {
			return ResultData.build().error();
		}
		AttachmentEntity _attachment = (AttachmentEntity)attachmentBiz.getById(attachment.getId());
		return ResultData.build().success(_attachment);
	}

	@ApiOperation(value = "保存附件管理列表接口")
	 @ApiImplicitParams({
    	@ApiImplicitParam(name = "title", value = "标题", required =false,paramType="query"),
    	@ApiImplicitParam(name = "url", value = "附件", required =false,paramType="query"),
		@ApiImplicitParam(name = "extension", value = "扩展名", required =false,paramType="query"),
		@ApiImplicitParam(name = "size", value = "大小", required =false,paramType="query"),
		@ApiImplicitParam(name = "type", value = "类型", required =true,paramType="query"),
		@ApiImplicitParam(name = "createBy", value = "创建人", required =false,paramType="query"),
		@ApiImplicitParam(name = "createDate", value = "创建时间", required =false,paramType="query"),
		@ApiImplicitParam(name = "updateBy", value = "修改人", required =false,paramType="query"),
		@ApiImplicitParam(name = "updateDate", value = "修改时间", required =false,paramType="query"),
		@ApiImplicitParam(name = "del", value = "删除标记", required =false,paramType="query"),
		@ApiImplicitParam(name = "id", value = "编号", required =false,paramType="query"),
	})

	/**
	* 保存附件管理
	* @param attachment 附件管理实体
	*/
	@PostMapping("/save")
	@ResponseBody
	@LogAnn(title = "保存附件管理", businessType = BusinessTypeEnum.INSERT)
	@RequiresPermissions("custom:attachment:save")
	public ResultData save(@ModelAttribute @ApiIgnore AttachmentEntity attachment, HttpServletResponse response, HttpServletRequest request) {
		//验证标题的值是否合法
		if(StringUtils.isBlank(attachment.getOriginal())){
			return ResultData.build().error(getResString("err.empty", this.getResString("original")));
		}
		if(!StringUtil.checkLength(attachment.getOriginal()+"", 0, 20)){
			return ResultData.build().error(getResString("err.length", this.getResString("original"), "0", "20"));
		}
		//验证附件的值是否合法
		if(StringUtils.isBlank(attachment.getUrl())){
			//this.getResString("filepath")
			return ResultData.build().error(getResString("err.empty", "文件格式不允许，上次失败！"));
		}
		attachmentBiz.save(attachment);
		return ResultData.build().success(attachment);
	}

	/**
	 * @param attachments 附件管理实体
	 */
	@ApiOperation(value = "批量删除附件管理列表接口")
	@PostMapping("/delete")
	@ResponseBody
	@LogAnn(title = "删除附件管理", businessType = BusinessTypeEnum.DELETE)
	@RequiresPermissions("custom:attachment:del")
	public ResultData delete(@RequestBody List<AttachmentEntity> attachments,HttpServletResponse response, HttpServletRequest request) {
		List<String> ids = (List)attachments.stream().map((p) -> {
		return p.getId();
		}).collect(Collectors.toList());
		return this.attachmentBiz.removeByIds(ids) ? ResultData.build().success() : ResultData.build().error(this.getResString("err.error", new String[]{this.getResString("id")}));

		}

	/**
	*	更新附件管理列表
	* @param attachment 附件管理实体
	*/
	 @ApiOperation(value = "更新附件管理列表接口")
	 @ApiImplicitParams({
	    @ApiImplicitParam(name = "id", value = "编号", required =true,paramType="query"),
    	@ApiImplicitParam(name = "title", value = "标题", required =false,paramType="query"),
    	@ApiImplicitParam(name = "url", value = "附件", required =false,paramType="query"),
		@ApiImplicitParam(name = "extension", value = "扩展名", required =false,paramType="query"),
		@ApiImplicitParam(name = "size", value = "大小", required =false,paramType="query"),
		@ApiImplicitParam(name = "type", value = "类型", required =true,paramType="query"),
		@ApiImplicitParam(name = "createBy", value = "创建人", required =false,paramType="query"),
		@ApiImplicitParam(name = "createDate", value = "创建时间", required =false,paramType="query"),
		@ApiImplicitParam(name = "updateBy", value = "修改人", required =false,paramType="query"),
		@ApiImplicitParam(name = "updateDate", value = "修改时间", required =false,paramType="query"),
		@ApiImplicitParam(name = "del", value = "删除标记", required =false,paramType="query"),
		@ApiImplicitParam(name = "id", value = "编号", required =false,paramType="query"),
	})
	@PostMapping("/update")
	@ResponseBody
	@LogAnn(title = "更新附件管理", businessType = BusinessTypeEnum.UPDATE)
	@RequiresPermissions("custom:attachment:update")
	public ResultData update(@ModelAttribute @ApiIgnore AttachmentEntity attachment, HttpServletResponse response,
			HttpServletRequest request) {
		//验证标题的值是否合法
		if(StringUtils.isBlank(attachment.getTitle())){
			return ResultData.build().error(getResString("err.empty", this.getResString("name")));
		}
		if(!StringUtil.checkLength(attachment.getTitle()+"", 0, 20)){
			return ResultData.build().error(getResString("err.length", this.getResString("name"), "0", "20"));
		}
		//验证附件的值是否合法
		if(StringUtils.isBlank(attachment.getUrl())){
			return ResultData.build().error(getResString("err.empty", "文件格式不允许，上次失败！"));
			//return ResultData.build().error(getResString("err.empty", this.getResString("fileupload.1629447835000.37180")));
		}
		attachmentBiz.updateById(attachment);
		return ResultData.build().success(attachment);
	}

}
