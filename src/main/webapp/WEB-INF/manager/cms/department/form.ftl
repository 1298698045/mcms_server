<!DOCTYPE html>
<html>
<head>
	 <title>部门管理</title>
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

        <el-form-item  label="部门名称" prop="name">
                <el-input
                        v-model="form.name"
                        :disabled="false"
                          :readonly="false"
                          :style="{width:  '100%'}"
                          :clearable="true"
                        placeholder="请输入部门名称">
                </el-input>
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
                //表单数据
                form: {
                    // 部门名称
                    name:' ',
                },
                rules:{
                // 部门名称
                name: [{"required":true,"message":"部门名称不能为空"},{"min":0,"max":64,"message":"部门名称长度必须为0-64"}],
                },

            }
        },
        watch:{
        },
        computed:{
        },
        methods: {
            save:function() {
                var that = this;
                var url = ms.manager + "/cms/department/save.do"
                if (that.form.id > 0) {
                    url = ms.manager + "/cms/department/update.do";
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
                                location.href = ms.manager + "/cms/department/index.do";
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

            //获取当前部门管理
            get:function(id) {
                var that = this;
                this.loading = true
                ms.http.get(ms.manager + "/cms/department/get.do", {"id":id}).then(function (res) {
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
