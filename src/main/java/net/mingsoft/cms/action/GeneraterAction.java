package net.mingsoft.cms.action;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import freemarker.template.TemplateException;
import net.mingsoft.base.entity.ResultData;
import net.mingsoft.basic.annotation.LogAnn;
import net.mingsoft.basic.biz.IModelBiz;
import net.mingsoft.basic.constant.e.BusinessTypeEnum;
import net.mingsoft.basic.entity.AppEntity;
import net.mingsoft.basic.util.BasicUtil;
import net.mingsoft.cms.bean.CategoryBean;
import net.mingsoft.cms.bean.ContentBean;
import net.mingsoft.cms.biz.ICategoryBiz;
import net.mingsoft.cms.biz.IContentBiz;
import net.mingsoft.cms.constant.e.CategoryTypeEnum;
import net.mingsoft.cms.entity.CategoryEntity;
import net.mingsoft.cms.util.CmsParserUtil;
import net.mingsoft.mdiy.util.ParserUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: GeneraterAction
 * @Description:TODO 生成器
 */
@Controller("cmsGenerater")
@RequestMapping("/${ms.manager.path}/cms/generate")
@Scope("request")
public class GeneraterAction extends BaseAction {

    /*
     * log4j日志记录
     */
    protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

    /**
     * 文章管理业务层
     */
    @Autowired
    private IContentBiz contentBiz;

    /**
     * 栏目管理业务层
     */
    @Autowired
    private ICategoryBiz categoryBiz;

    /**
     * 模块管理业务层
     */
    @Autowired
    private IModelBiz modelBiz;

    @Value("${ms.manager.path}")
    private String managerPath;

    @Value("${ms.diy.html-dir:html}")
    private String htmlDir;

    @Value("${ms.diy.components-dir:components}")
    private String components;

    /**
     * /**
     * 更新主页
     *
     * @return
     */
    @RequestMapping("/index")
    public String index(HttpServletRequest request, ModelMap model) {
        return "/cms/generate/index";
    }

    /**
     * 生成主页
     *
     * @param request
     * @param response
     */
    @RequestMapping("/generateIndex")
    @RequiresPermissions("cms:generate:index")
    @LogAnn(title = "生成主页", businessType = BusinessTypeEnum.UPDATE)
    @ResponseBody
    public ResultData generateIndex(HttpServletRequest request, HttpServletResponse response) throws IOException, TemplateException {
        // 模版文件名称
        String tmpFileName = request.getParameter("url");
        // 生成后的文件名称
        //String generateFileName = request.getParameter("position");

        // 获取文件所在路径 首先判断用户输入的模版文件是否存在
        if (!FileUtil.exist(ParserUtil.buildTemplatePath())) {
            return ResultData.build().error(getResString("templet.file"));
        }
        generateComponents();
        CmsParserUtil.generate(tmpFileName, tmpFileName, htmlDir);
        return ResultData.build().success();
    }

    private ResultData generateComponents() throws IOException, TemplateException {
        String templatePath = ParserUtil.buildTemplatePath();
        if (!"/".equals(File.pathSeparator)) {
            templatePath = templatePath.replace('/', File.separatorChar);
        }
        // 获取文件所在路径 首先判断用户输入的模版文件是否存在
        if (!FileUtil.isDirectory(templatePath)) {
            return ResultData.build().error(getResString("templet.file"));
        }

        List<String> mainList = FileUtil.listFileNames(templatePath);
        for (String srcFile : mainList) {
            if (srcFile.equals("main.js")) {
                CmsParserUtil.generate(srcFile, srcFile, htmlDir);
            }
        }
        List<String> componentList = FileUtil.listFileNames(templatePath + File.separator + components);
        
        for (String srcFile : componentList) {
            String fileName = components + File.separator + srcFile;
            CmsParserUtil.generate(fileName, fileName, htmlDir);
        }
        return ResultData.build().success();
    }

    /**
     * 生成列表的静态页面
     *
     * @param request
     * @param response
     * @param categoryId
     * @throws Exception
     */
    @RequestMapping("/{categoryId}/genernateColumn")
    @LogAnn(title = "生成栏目", businessType = BusinessTypeEnum.UPDATE)
    @RequiresPermissions("cms:generate:column")
    @ResponseBody
    public ResultData genernateColumn(HttpServletRequest request, HttpServletResponse response, @PathVariable String categoryId) throws Exception {
        long beginTime = System.currentTimeMillis();
        LOG.info("begin generate category {}", categoryId);
        // 获取站点id
        AppEntity app = BasicUtil.getApp();
        
        //栏目列表
        List<CategoryEntity> columns = new ArrayList<CategoryEntity>();

        if ("0".equals(categoryId)) {// 0更新所有栏目
            CategoryEntity categoryEntity = new CategoryEntity();
            columns = categoryBiz.query(categoryEntity);
        } else { //选择栏目更新
            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setId(categoryId);
            
            columns = categoryBiz.queryChilds(categoryEntity);
        }

        // 获取栏目列表模版
        for (CategoryEntity column : columns) {

            ContentBean contentBean = new ContentBean();
            contentBean.setCategoryId(column.getId());
            
            // 判断列表类型
            switch (CategoryTypeEnum.get(column.getCategoryType())) {
            //TODO 暂时先用字符串代替
            case LIST: // 列表
                // 判断模板文件是否存在
                if (!FileUtil.exist(ParserUtil.buildTemplatePath(column.getCategoryListUrl()))) {
                    LOG.error("模板不存在：{}", column.getCategoryUrl());
                    continue;
                }
                Map<String, Object> whereMap = new HashMap<>();
                int totalCount = contentBiz.getSearchCount(null, null, whereMap, 1, column.getCategoryId());

                CmsParserUtil.generateList(column, totalCount, htmlDir);
                break;
            case COVER:// 单页
                //文章列表
                List<CategoryBean> articleIdList = contentBiz.queryIdsByCategoryIdForParser(contentBean);
            
                if (articleIdList.size() == 0) {
                    CategoryBean columnArticleIdBean = new CategoryBean();
                    CopyOptions copyOptions = CopyOptions.create();
                    copyOptions.setIgnoreError(true);
                    BeanUtil.copyProperties(column, columnArticleIdBean, copyOptions);
                    articleIdList.add(columnArticleIdBean);
                }
                CmsParserUtil.generateBasic(articleIdList,htmlDir);
                //CmsParserUtil.generateCover(column, 0, htmlDir);
                break;
            case LINK:
                continue;
            }
        }
        LOG.info("generate category {} used {} seconds", categoryId, (System.currentTimeMillis() - beginTime)/1000);
        return ResultData.build().success();
    }

    /**
     * 根据栏目id更新所有的文章
     *
     * @param request
     * @param response
     * @param columnId
     */
    @RequestMapping("/{columnId}/generateArticle")
    @RequiresPermissions("cms:generate:article")
    @LogAnn(title = "生成文章", businessType = BusinessTypeEnum.UPDATE)
    @ResponseBody
    public ResultData generateArticle(HttpServletRequest request, HttpServletResponse response, @PathVariable String columnId) throws Exception {
        String dateTime = request.getParameter("dateTime");
        // 网站风格物理路径
        List<CategoryBean> articleIdList = null;
        List<CategoryEntity> categoryList = new ArrayList<CategoryEntity>();
        ContentBean contentBean = new ContentBean();
        contentBean.setBeginTime(dateTime);

        // 生成所有栏目的文章
        if ("0".equals(columnId)) {
            categoryList = categoryBiz.list(Wrappers.<CategoryEntity>lambdaQuery()
                    .isNull(CategoryEntity::getCategoryParentIds));
        } else {
            CategoryEntity category = (CategoryEntity) categoryBiz.getById(columnId);
            categoryList.add(category);
        }

        for (CategoryEntity category : categoryList) {
            contentBean.setCategoryId(category.getId());
            //将文章列表标签中的中的参数
            articleIdList = contentBiz.queryIdsByCategoryIdForParser(contentBean);
            // 分类是列表
            if (category.getCategoryType().equals(CategoryTypeEnum.LIST.toString())) {
                // 判断模板文件是否存在
                if (!FileUtil.exist(ParserUtil.buildTemplatePath(category.getCategoryListUrl())) || StringUtils.isEmpty(category.getCategoryListUrl())) {
                    LOG.error("模板不存在：{}", category.getCategoryUrl());
                    continue;
                }
            } else if (category.getCategoryType().equals(CategoryTypeEnum.COVER.toString())) {
                CategoryBean columnArticleIdBean = new CategoryBean();
                CopyOptions copyOptions = CopyOptions.create();
                copyOptions.setIgnoreError(true);
                BeanUtil.copyProperties(category, columnArticleIdBean, copyOptions);
                articleIdList.add(columnArticleIdBean);
            }
            // 有符合条件的就更新
            if (articleIdList.size() > 0) {
                CmsParserUtil.generateBasic(articleIdList,htmlDir);
            }
        }
        return ResultData.build().success();
    }


    /**
     * 用户预览主页
     *
     * @param request
     * @return
     */
    @RequestMapping("/{position}/viewIndex")
    public String viewIndex(HttpServletRequest request, @PathVariable String position, HttpServletResponse response) {
        AppEntity app = BasicUtil.getApp();
        // 组织主页预览地址
        String indexPosition = app.getAppHostUrl() + File.separator + htmlDir+ File.separator + app.getAppDir()
                + File.separator + position + ParserUtil.HTML_SUFFIX;
        return "redirect:" + indexPosition;
    }
}