package net.mingsoft.cms.entity;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.util.Date;
@Data
public class ContentVO {
    @Excel(name = "文章编号", width = 20)
    private String id;

    /**
     * 文章标题
     */
    @Excel(name = "文章标题", width = 60)
    private String contentTitle;
    /**
     * 所属栏目
     */
    @Excel(name = "栏目名称", width = 20)
    private String categoryTitle;

    /**
     * 文章作者
     */
    @Excel(name = "文章作者", width = 20)
    private String contentAuthor;
    /**
     * 发布时间
     */
    @Excel(name = "发布时间", width = 20)
    private Date contentDatetime;
    /**
     * 自定义顺序
     */
    @Excel(name = "排序", width = 20)
    private Integer contentSort;
    /**
     * 点击次数
     */
    @Excel(name = "点击量", width = 20)
    private Integer contentHit;

}
