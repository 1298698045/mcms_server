<!DOCTYPE html>
<html>
<head>
	 <title>附件管理</title>
     <#include "../../include/head-file.ftl">
</head>
<body>
	<div id="form" v-loading="loading" v-cloak>
		<el-header class="ms-header ms-tr" height="50px">
			<el-button type="primary" icon="iconfont icon-baocun" size="mini" @click="save()" :loading="saveDisabled">保存</el-button>
			<el-button size="mini" icon="iconfont icon-fanhui" plain onclick="javascript:history.go(-1)">返回</el-button>
		</el-header>
		<el-main class="ms-container">
            <el-form ref="form" :model="form" :rules="rules" label-width="120px" label-position="right" size="small">

        <el-form-item  label="标题" prop="name">
                <el-input
                        v-model="form.original"
                        :disabled="!form.original"
                          :readonly="false"
                          :style="{width:  '100%'}"
                          :clearable="true"
                        placeholder="请输入标题">
                </el-input>
        </el-form-item>

        <el-form-item  label="附件" prop="filepath">
            <ms-upload
                    v-model="fileList"
                    :action="ms.base+'/file/upload.do'"
                    :limit="1"
                    accept=".jpg,.png,.jpeg,.gif,.mp4,.avi,.zip,.rar,.xlsx,.xls,.doc,.docx,.pdf,.ppt,.pptx,.txt"
                    :disabled="false"
                    @input="onInput"
                    :data="{uploadpath:'/cms/attachment','isRename':true,'appId':true}">
                <el-button size="small" type="primary">点击上传</el-button>
                <div slot="tip" class="el-upload__tip">最多上传1个文件</div>
            </ms-upload>
        </el-form-item>
            </el-form>
		</el-main>
	</div>
	</body>
	</html>
<script>
        var form = new Vue({
        el: '#form',
        data:function() {
            return {
                loading:false,
                saveDisabled: false,
                fileList: [],
                //表单数据
                form: {
                    // 标题
                    original:'',
                    size: 0,
                    // 附件
                    url: '',
                },
                rules:{
                    // 标题
                    original: [{"required":true,"message":"标题不能为空"},{"min":0,"max":20,"message":"标题长度必须为0-20"}],
                    // 附件
                    url: [{"required":true,"message":"附件不能为空"}],
                },

            }
        },
        watch:{
        },
        computed:{
        },
        methods: {
            onInput:function(fileList) {
                if (fileList.length == 0) {
                    return
                }
                file = fileList[0]
                this.form.original = file.name
                this.form.size = file.size
                this.form.url = file.url
            },

            save:function() {
                var that = this;
                var url = ms.manager + "/cms/attachment/save.do"
                if (that.form.id > 0) {
                    url = ms.manager + "/cms/attachment/update.do";
                }
                this.$refs.form.validate(function(valid) {
                    if (valid) {
                        that.saveDisabled = true;
                        var data = JSON.parse(JSON.stringify(that.form));
                        ms.http.post(url, data).then(function (res) {
                            if (res.result) {
                                that.$notify({
                                    title: "成功",
                                    message: "保存成功",
                                    type: 'success'
                                });
                                location.href = ms.manager + "/cms/attachment/index.do";
                            } else {
                                that.$notify({
                                    title: "错误",
                                    message: res.msg,
                                    type: 'warning'
                                });
                            }
                            that.saveDisabled = false;
                        });
                    } else {
                        return false;
                    }
                })
            },

            //获取当前附件管理
            get:function(id) {
                var that = this;
                this.loading = true
                ms.http.get(ms.manager + "/cms/attachment/get.do", {"id":id}).then(function (res) {
                    that.loading = false
                    if(res.result&&res.data){
                        that.form = res.data;
                    }
                }).catch(function (err) {
                    console.log(err);
                });
            },
        },
        created:function() {
            var that = this;
            this.form.id = ms.util.getParameter("id");
            if (this.form.id) {
                this.get(this.form.id);
            }
        }
    });
</script>
