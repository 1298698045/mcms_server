package net.mingsoft.cms.action.web;

import com.alibaba.fastjson.JSON;
import com.mingsoft.ueditor.MsUeditorActionEnter;
import net.mingsoft.basic.util.BasicUtil;
import net.mingsoft.custom.biz.IAttachmentBiz;
import net.mingsoft.custom.entity.AttachmentEntity;

import org.apache.poi.hpbf.model.qcbits.QCTextBit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;
import java.util.Map;
import com.baidu.ueditor.define.ActionMap;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 百度编辑器上传
 */
@Controller("ueAction")
@RequestMapping("/static/plugins/ueditor/{version}/jsp")
public class EditorAction {
    /**
     * 上传路径
     */
    @Value("${ms.upload.path}")
    private String uploadFolderPath;

    @Autowired
    private IAttachmentBiz attachmentBiz;

    /**
     * 上传路径映射
     */
    @Value("${ms.upload.mapping}")
    private String uploadMapping;
    @ResponseBody
    @RequestMapping("editor")
    public String editor(HttpServletRequest request, HttpServletResponse response, String jsonConfig){
        String rootPath = BasicUtil.getRealPath("");
        //如果是绝对路径就直接使用配置的绝对路径
        File saveFolder=new File(uploadFolderPath);
        if (saveFolder.isAbsolute()) {
            rootPath = saveFolder.getPath();
            //因为绝对路径已经映射了路径所以隐藏
            jsonConfig = jsonConfig.replace("{ms.upload}", "");
        } else {
            //如果是相对路径替换成配置的路径
            jsonConfig = jsonConfig.replace("{ms.upload}","/"+ uploadFolderPath);
        }
        //执行exec()方法才保存文件
        String action = request.getParameter("action");
        MsUeditorActionEnter actionEnter = new MsUeditorActionEnter(request, rootPath, jsonConfig, BasicUtil.getRealPath(""));
        String json = actionEnter.exec();

        Map<String, Object> data = (Map<String, Object>) JSON.parse(json);
        if (saveFolder.isAbsolute()) {
            //如果是配置的绝对路径需要在前缀加上映射路径
            data.put("url", uploadMapping.replace("/**", "") + data.get("url"));
        }
        switch(action) {
        case "config":
            return JSON.toJSONString(data);
        case "uploadfile":
            AttachmentEntity attachment = new AttachmentEntity();
            attachment.setOriginal((String)data.get("original"));
            attachment.setTitle((String)data.get("title"));
            attachment.setUrl((String)data.get("url"));
            attachment.setExtension((String)data.get("type"));
            attachment.setSize((String)data.get("size"));
            attachmentBiz.save(attachment);    
            attachment.setType(1);
            break;
        case "listfile":
            int start = actionEnter.getStartIndex();
            int size = Integer.parseInt(request.getParameter("size"));
            QueryWrapper<AttachmentEntity> qw = new QueryWrapper<>();
            qw.select("url", "original").last("limit " + start + "," + size);
            List<Map<String, Object>> result = attachmentBiz.listMaps(qw);
            int total = attachmentBiz.count();
            data.put("total", total);
            data.put("start", start);
            data.put("list", result);
            break;
        }

        return JSON.toJSONString(data);
    }
}
