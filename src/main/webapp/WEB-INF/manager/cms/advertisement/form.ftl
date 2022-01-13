<!DOCTYPE html>
<html>
<head>
	 <title>广告</title>
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
                        <el-row
                                :gutter="0"
                                justify="start" align="top">
                                <el-col :span="12">

        <el-form-item  label="广告位" prop="adposId">
                    <el-select v-model="form.adposId"
                               :style="{width: '100%'}"
                               :filterable="false"
                               :disabled="false"
                    :multiple="false" :clearable="true"
                placeholder="请选择广告位">
                        <el-option v-for='item in adposIdOptions' :key="item.id" :value="item.id"
                                   :label="item.name"></el-option>
                    </el-select>
        </el-form-item>
                                </el-col>
                                <el-col :span="12">

        <el-form-item  label="广告名称" prop="name">
                <el-input
                        v-model="form.name"
                        :disabled="false"
                          :readonly="false"
                          :style="{width:  '100%'}"
                          :clearable="true"
                        placeholder="请输入广告名称">
                </el-input>
        </el-form-item>
                                </el-col>
                        </el-row>
                        <el-row
                                :gutter="0"
                                justify="start" align="top">
                                <el-col :span="12">

        <el-form-item  label="广告类型" prop="flag">
                    <el-select  v-model="form.flag"
                               :style="{width: '100%'}"
                               :filterable="false"
                               :disabled="false"
                               :multiple="false" :clearable="true"
                            placeholder="请选择广告类型">
                        <el-option v-for='item in flagOptions' :key="item.dictValue" :value="item.dictValue"
                                   :label="item.dictLabel"></el-option>
                    </el-select>
        </el-form-item>
                                </el-col>
                                <el-col :span="12">

        <el-form-item  label="是否开启" prop="isEnabled">
                    <el-radio-group v-model="form.isEnabled"
                                    :style="{width: ''}"
                                    :disabled="false">
                        <el-radio :style="{display: true ? 'inline-block' : 'block'}" :label="item.value"
                                  v-for='(item, index) in isEnabledOptions' :key="item.value + index">
                            {{item.label}}
                        </el-radio>
                    </el-radio-group>
        </el-form-item>
                                </el-col>
                        </el-row>
                        <el-row
                                :gutter="0"
                                justify="start" align="top">
                                <el-col :span="12">

        <el-form-item  label="广告时间" prop="dateRange">
                <ms-date-picker
                        :start-date.sync="form.dateStart"
                        :end-date.sync="form.dateEnd"
placeholder="请选择广告时间"start-placeholder=""end-placeholder=""                        :readonly="false"
                        :disabled="false"
                        :editable="true"
                        :clearable="true"
                        format="yyyy-MM-dd"
                        value-format="yyyy-MM-dd"
                        :style="{width:'100%'}"
                        type="daterange">
                </ms-date-picker>
        </el-form-item>
                                </el-col>
                                <el-col :span="12">

        <el-form-item  label="广告链接" prop="link">
                <el-input
                        v-model="form.link"
                        :disabled="false"
                          :readonly="false"
                          :style="{width:  '100%'}"
                          :clearable="true"
                        placeholder="请输入广告链接">
                </el-input>
        </el-form-item>
                                </el-col>
                        </el-row>
                        <el-row
                                :gutter="0"
                                justify="start" align="top">
                                <el-col :span="12">

        <el-form-item  label="联系人电话" prop="contactPhone">
                <el-input
                        v-model="form.contactPhone"
                        :disabled="false"
                          :readonly="false"
                          :style="{width:  '100%'}"
                          :clearable="true"
                        placeholder="请输入联系人电话">
                </el-input>
        </el-form-item>
                                </el-col>
                                <el-col :span="12">

        <el-form-item  label="联系人邮箱" prop="contactEmail">
                <el-input
                        v-model="form.contactEmail"
                        :disabled="false"
                          :readonly="false"
                          :style="{width:  '100%'}"
                          :clearable="true"
                        placeholder="请输入联系人邮箱">
                </el-input>
        </el-form-item>
                                </el-col>
                        </el-row>
                        <el-row
                                :gutter="0"
                                justify="start" align="top">
                                <el-col :span="12">

        <el-form-item  label="广告联系人" prop="contactName">
                <el-input
                        v-model="form.contactName"
                        :disabled="false"
                          :readonly="false"
                          :style="{width:  '100%'}"
                          :clearable="true"
                        placeholder="请输入广告联系人">
                </el-input>
        </el-form-item>
                                </el-col>
                                <el-col :span="12">
                                </el-col>
                        </el-row>

        <el-form-item  label="广告图片" prop="image">
            <ms-upload
                    v-model="form.image"
                    :action="ms.base+'/file/upload.do'"
                    :limit="8"
                    :disabled="false"
                    :data="{uploadPath:'/cms/advertisement','isRename':true,'appId':true}"
                    accept="image/*"
                    list-type="picture-card">
                <i class="el-icon-plus"></i>
                <div slot="tip" class="el-upload__tip">最多上传8张图片</div>
            </ms-upload>
        </el-form-item>

        <el-form-item  label="广告内容" prop="content">
            <vue-ueditor-wrap style="line-height: 0px"
                      v-model="form.content" :config="editorConfig"></vue-ueditor-wrap>
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
                editorConfig:editorConfig,
                dateRange: '',
                //表单数据
                form: {
                    dateEnd: '',
                    // 广告位
                    adposId:'',
                    // 广告名称
                    name:'',
                    // 广告类型
                    flag:'',
                    // 是否开启
                    isEnabled:'0',
                    // 广告时间
                    dateStart: '',
                    // 结束时间
                    dateEnd:'',
                    // 广告链接
                    link:'',
                    // 联系人电话
                    contactPhone:'',
                    // 联系人邮箱
                    contactEmail:'',
                    // 广告联系人
                    contactName:'',
                    // 广告图片
                    image: [],
                    // 广告内容
                    content:'',

                },
                adposIdOptions:[],
                flagOptions:[],
                isEnabledOptions:[{"value":"1","label":"是"},{"value":"0","label":"否"}],
                rules:{
                // 广告位
                adposId: [{"required":true,"message":"请选择广告位"}],
                // 广告名称
                name: [{"required":true,"message":"广告名称不能为空"},{"min":0,"max":20,"message":"广告名称长度必须为0-20"}],
                // 广告类型
                flag: [{"required":true,"message":"请选择广告类型"}],
                // 是否开启
                isEnabled: [{"required":true,"message":"是否开启不能为空"}],
                // 广告链接
                link: [{"min":0,"max":20,"message":"广告链接长度必须为0-20"}],
                // 联系人电话
                contactPhone: [{"pattern":/^[1][0-9]{10}$/,"message":"联系人电话格式不匹配"},{"min":0,"max":20,"message":"联系人电话长度必须为0-20"}],
                // 联系人邮箱
                contactEmail: [{"min":0,"max":20,"message":"联系人邮箱长度必须为0-20"}],
                // 广告联系人
                contactName: [{"min":0,"max":20,"message":"广告联系人长度必须为0-20"}],
                },

            }
        },
        watch:{
            "form.dateRange":function(nev,old){
                this.form.dateStart = nev[0];
                this.form.dateEnd =nev[1]
            },
        },
        computed:{
        },
        methods: {
            save:function() {
                var that = this;
                var url = ms.manager + "/cms/advertisement/save.do"
                if (that.form.id > 0) {
                    url = ms.manager + "/cms/advertisement/update.do";
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
                                location.href = ms.manager + "/cms/advertisement/index.do";
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

            //获取当前广告
            get:function(id) {
                var that = this;
                this.loading = true
                ms.http.get(ms.manager + "/cms/advertisement/get.do", {"id":id}).then(function (res) {
                    that.loading = false
                    if(res.result&&res.data){
                        that.form = res.data;
                    }
                }).catch(function (err) {
                    console.log(err);
                });
            },
            //获取adposId数据源
            adposIdOptionsGet:function() {
                var that = this;
                ms.http.get(ms.manager+'/cms/adposition/list.do', {}).then(function (res) {
                    that.adposIdOptions = res.data.rows;
                    console.log('adposIdOptions:', that.adposIdOptions);
                }).catch(function (err) {
                    console.log(err);
                });
            },
            
            //获取contentType数据源
            flagOptionsGet: function () {
                var that = this;
                ms.http.get(ms.base + '/mdiy/dict/list.do', {
                    dictType: '广告属性',
                    pageSize: 99999
                }).then(function (data) {
                    if(data.result){
                        data = data.data;
                        that.flagOptions = data.rows;
                        console.log('flagOptions:', that.flagOptions);
                    }
                }).catch(function (err) {
                    console.log(err);
                });
            },
        },
        created:function() {
            var that = this;
            this.adposIdOptionsGet();
            this.flagOptionsGet();
            this.form.id = ms.util.getParameter("id");
            if (this.form.id) {
                this.get(this.form.id);
            }
        }
    });
</script>
