<!DOCTYPE html>
<html>
<head>
	 <title>专家管理</title>
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
                        <el-form-item  label="栏目" prop="departmentId">
                            <ms-tree-select :props="{value: 'id', label: 'categoryTitle', children: 'children'}"
                                :options="departmentIdOptions" :style="{width:'100%'}"
                                :value="form.departmentId" ref="treeselect"
                                @get-value="getValue($event)">
                            </ms-tree-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item  label="姓名" prop="name">
                            <el-input
                                v-model="form.name"
                                :disabled="false"
                                :readonly="false"
                                :style="{width:  '100%'}"
                                :clearable="true"
                                placeholder="请输入姓名">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="工号" prop="jobNumber">
                            <el-input
                                v-model="form.jobNumber"
                                :disabled="false"
                                :readonly="false"
                                :style="{width:  '100%'}"
                                :clearable="true"
                                placeholder="请输入工号">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item  label="性别" prop="name">
                            <el-select @change="setGender" v-model="form.gender"
                                placeholder="请选择性别"
                                :disabled="false"
                                :readonly="false"
                                :style="{width:  '100%'}"
                                :clearable="true">
                                <el-option v-for='(item,index) in genderOptions' :key="item.value" :value="item.value"
                                :label="item.title">
                            </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item  label="职务" prop="technicalTitle">
                            <el-input
                                v-model="form.technicalTitle"
                                :disabled="false"
                                :readonly="false"
                                :style="{width:  '100%'}"
                                :clearable="true"
                                placeholder="请输入职务">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item  label="主要研究方向" prop="goodAt">
                            <el-input
                                v-model="form.goodAt"
                                :disabled="false"
                                :readonly="false"
                                :style="{width:  '100%'}"
                                :clearable="true"
                                placeholder="请输入主要研究方向">
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                
                <el-row
                    :gutter="0"
                    justify="start" align="top">
                <el-col :span="12">
                    <el-form-item  label="出诊时间" prop="schedule">
                        <el-input
                            v-model="form.schedule"
                            :disabled="false"
                            :readonly="false"
                            :style="{width:  '100%'}"
                            :clearable="true"
                            placeholder="请输入出诊时间">
                        </el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item  label="停诊起止时间" prop="stopRange">
                        <el-date-picker
                            v-model="stopRange"
                            type="daterange"
                            :disabled="false"
                            :readonly="false"
                            :style="{width:  '100%'}"
                            :clearable="true"
                            value-format="yyyy-MM-dd"
                            range-separator="至"
                            start-placeholder="停诊开始日期"
                            end-placeholder="停诊截止日期">
                        </el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            
            <el-row
                :gutter="0"
                justify="start" align="top">
                <el-col :span="12">
                    <el-form-item  label="科室" prop="position">
                        <el-input
                            v-model="form.position"
                            :disabled="false"
                              :readonly="false"
                              :style="{width:  '100%'}"
                              :clearable="true"
                            placeholder="请输入科室">
                        </el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item  label="学历" prop="techingPost">
                        <el-input
                            v-model="form.techingPost"
                            :disabled="false"
                            :readonly="false"
                            :style="{width:  '100%'}"
                            :clearable="true"
                            placeholder="请输入学历">
                        </el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item  label="领域" prop="field">
                        <el-input
                            v-model="form.field"
                            :disabled="false"
                            :readonly="false"
                            :style="{width:  '100%'}"
                            :clearable="true"
                            placeholder="请输入领域">
                        </el-input>
                    </el-form-item>
                </el-col>
                <el-col span="12">
                    <el-form-item label="文章类型" prop="contentType">
                        <template slot='label'>文章类型
                            <el-popover placement="top-start" title="提示" trigger="hover">
                                用于筛选文章，在自定义字典添加
                                <i class="el-icon-question" slot="reference"></i>
                            </el-popover>
                        </template>
                        <el-select v-model="form.contentType"
                                   :style="{width: '100%'}"
                                   :filterable="false"
                                   :disabled="false"
                                   :multiple="true" :clearable="true"
                                   placeholder="请选择文章类型">
                            <el-option v-for='item in contentTypeOptions' :key="item.dictValue"
                                       :value="item.dictValue"
                                       :label="item.dictLabel"></el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col span="12">
                    <el-form-item label="自定义顺序" prop="contentSort">
                        <el-input-number
                                v-model="form.contentSort"
                                :disabled="false"
                                controls-position="">
                        </el-input-number>
                    </el-form-item>
                </el-col>
            </el-row>

        <el-form-item label="" prop="portrait">
            <template slot='label'>头像
                <el-popover placement="top-start" title="提示" trigger="hover">
                    头像,支持jpg格式
                    <i class="el-icon-question" slot="reference"></i>
                </el-popover>
            </template>
            <el-upload
                :file-list="form.portrait"
                :action="ms.base+'/file/upload.do'"
                :on-remove="uploadHandleRemove"
                :style="{width:''}"
                :limit="1"
                :on-exceed="uploadHandleExceed"
                :disabled="false"
                :data="{uploadPath:'/cms/portrait','isRename':true ,'appId':true}"
                :on-success="uploadSuccess"
                accept="image/*"
                list-type="picture-card">
                <i class="el-icon-plus"></i>
                <div slot="tip" class="el-upload__tip">最多上传1张图片</div>
            </el-upload>
        </el-form-item>
        <el-form-item  label="描述" prop="briefIntro">
            <el-input
                type="textarea" :rows="5"
                v-model="form.briefIntro"
                :disabled="false"
                :readonly="false"
                :style="{width:  '100%'}"
                :clearable="true"
                placeholder="请输入描述">
            </el-input>
        </el-form-item>

        <el-form-item  label="简介" prop="introduce">
            <el-input
                type="textarea" :rows="25"
                :disabled="false"
                :readonly="false"
                v-model="form.introduce"
                :style="{width: '100%'}"
                placeholder="请输入简介">
            </el-input>
        </el-form-item>
            </el-form>
		</el-main>
	</div>
    <#include "/component/ms-tree-select.ftl">
	</body>
	</html>
<script>
        var form = new Vue({
        el: '#form',
        data:function() {
            return {
                genderOptions: [{
                    value: 1,
                    title: '男'
                }, {
                    value: 2,
                    title: '女'
                }],
                loading:false,
                saveDisabled: false,
                // 停诊时间段
                stopRange:[],
                //表单数据
                form: {
                    jobNumber: null,
                    // 姓名
                    name:null,
                    // 性别
                    gender: null,
                    // 领域
                    field:null,
                    // 科室
                    departmentId:null,
                    // 出诊时间
                    schedule:null,
                    // 停诊时间段
                    stoppingStart:null,
                    stoppingEnd:null,
                    // 擅长
                    goodAt:null,
                    // 职称
                    technicalTitle:null,
                    // 职务
                    position:null,
                    // 教学任职
                    techingPost:null,
                    // 描述
                    briefIntro:null,
                    // 简介
                    introduce:null,
                    portrait: [],
                    contentType: [],
                    contentSort: null,
                },
                contentTypeOptions: [],
                departmentIdOptions: [{
                    id: '1400724656644583425',
                    categoryTitle: '科室',
                    children: []
                }],
                rules:{
                    jobNumber: [{"min":0,"max":64,"message":"姓名长度必须为0-64"}],
                    // 姓名
                    name: [{"required":true,"message":"姓名不能为空"},{"min":0,"max":64,"message":"姓名长度必须为0-64"}],
                    schedule: [{"required":true,"message":"出诊时间不能为空"},{"min":0,"max":255,"message":"出诊时间长度必须为0-255"}],
                    // 领域
                    field: [{"min":0,"max":64,"message":"领域长度必须为0-64"}],
                    // 科室
                    departmentId: [{"required":true,"message":"请选择栏目"}],
                    // 擅长
                    // goodAt: [{"required":true,"message":"主要研究方向不能为空"},{"min":0,"max":200,"message":"主要研究方向长度必须为0-200"}],
                    // 职称
                    technicalTitle: [{"required":true,"message":"职务不能为空"},{"min":0,"max":64,"message":"职务长度必须为0-64"}],
                    // 职务
                    position: [{"min":0,"max":64,"message":"科室长度必须为0-64"}],
                    // 教学任职
                    techingPost: [{"min":0,"max":64,"message":"学历长度必须为0-64"}],
                    // 简介
                    introduce: [{"required":true,"message":"简介不能为空"}],
                    portrait: [{"required":true,"message":"头像不能为空"}],
                    jobNumber: [{"required":true,"message":"工号不能为空"}]
                },

            }
        },
        watch:{
            stopRange: function(newValue) {
                if (newValue.length == 2) {
                    this.form.stoppingStart = newValue[0];
                    this.form.stoppingEnd = newValue[1];
                }
            }
        },
        computed:{
        },
        methods: {
            getValue: function (data) {
                this.$refs.treeselect.valueId = data.node['id'];
                this.$refs.treeselect.valueTitle = data.node['categoryTitle'];
                console.log("getValue", this.form.departmentId);
                this.form.departmentId = data.node.id;
                data.dom.blur();
            },
            setGender: function (data) {
                this.form.gender = data;
            },
            save:function() {
                console.log('saving');
                var that = this;
                var url;
                if (that.form.id > 0) {
                    url = ms.manager + "/cms/expert/update.do";
                } else {
                    url = ms.manager + "/cms/expert/save.do";
                }
                this.$refs.form.validate(function(valid) {
                    if (!valid) {
                        console.error('not valid')
                        return false;
                    }
                    that.saveDisabled = true;
                    ms.util.removeNull(that.form);
                    var data = JSON.parse(JSON.stringify(that.form));
                    if (data.portrait) {
                        data.portrait = JSON.stringify(data.portrait);
                    }
                    if (data.contentType) {
                        data.contentType = data.contentType.join(',');
                    }
                    console.log('data:', data);
                    ms.http.post(url, data).then(function (res) {
                        if (res.result) {
                            that.$notify({
                                title: "成功",
                                message: "保存成功",
                                type: 'success'
                            });
                            location.href = ms.manager + "/cms/expert/index.do";
                        } else {
                            that.$notify({
                                title: "错误",
                                message: res.msg,
                                type: 'warning'
                            });
                        }
                        that.saveDisabled = false;
                    });
                })
            },
            //获取contentType数据源
            contentTypeOptionsGet: function () {
                var that = this;
                return ms.http.get(ms.base + '/mdiy/dict/list.do', {
                    dictType: '文章属性',
                    pageSize: 99999
                }).then(function (data) {
                    if(data.result){
                        data = data.data;
                        that.contentTypeOptions = data.rows;
                    }
                    return Promise.resolve();
                }).catch(function (err) {
                    console.log(err);
                    return Promise.reject(err);
                });
            },
            //获取当前专家管理
            get: function(id) {
                var that = this;
                this.loading = true
                return ms.http.get(ms.manager + "/cms/expert/get.do", {"id":id}).then(function (res) {
                    that.loading = false
                    if(res.result&&res.data){
                        if (res.data.contentType && res.data.contentType != '') {
                            res.data.contentType = res.data.contentType.split(',');
                        } else {
                            res.data.contentType = [];
                        }
                        //var found = that.find(that.departmentIdOptions.children, res.data.departmentId);
                        //console.log('departmentId:', res.data.departmentId, typeof res.data.departmentId, 'found:', found, 'departmentIdOptions:', that.departmentIdOptions);
                        if (res.data.stoppingStart && res.data.stoppingEnd) {
                            that.stopRange = [res.data.stoppingStart, res.data.stoppingEnd];
                        }
                        if (res.data.portrait) {
                            res.data.portrait = JSON.parse(res.data.portrait);
                            res.data.portrait.forEach(function (value) {
                                value.url = ms.base + value.path;
                            });
                        }
                        that.form = res.data;
                    }
                }).catch(function (err) {
                    console.log(err);
                });
            },

            find: function(arr, value) {
                for(var i in arr) {
                    if (arr[i].id == value) {
                        return arr[i]
                    }
                    console.log('not matched id:', arr[i].id, arr[i].categoryTitle)
                    if (arr[i].hasOwnProperty('children')) {
                        return this.find(arr[i].children, value)
                    }
                }
                return null
            },

            //获取departmentId数据源
            departmentIdOptionsGet:function() {
                var that = this;
                return ms.http.get(ms.base+'/cms/category/list.do', 
                    {categoryParentIds: that.departmentIdOptions[0].id, pageSize:99999}).then(function (res) {
                    that.departmentIdOptions[0].children = ms.util.treeData(res.data.rows, 'id', 'categoryId', 'children', that.departmentIdOptions[0].id);
                    console.log('that.departmentIdOptions:', that.departmentIdOptions);
                    return Promise.resolve();
                }).catch(function (err) {
                    console.log(err);
                    return Promise.reject(err);
                });
            },
            //文件上传完成回调
            uploadSuccess: function (response, file, fileList) {
                if(response.result){
                    if (!this.form.portrait) {
                        this.form.portrait = [];
                    }
                    console.log(file, response, fileList);
                    this.form.portrait.push({
                        url: response.data.url,
                        name: file.name,
                        path: response.data.url,
                        uid: file.uid
                    });
                }else {
                    this.$notify({
                        title: '失败',
                        message: response.msg,
                        type: 'warning'
                    });
                }

            },
            uploadHandleRemove: function (file, files) {
                var index = -1;
                index = this.form.portrait.findIndex(function (text) {
                    return text == file;
                });

                if (index != -1) {
                    this.form.portrait.splice(index, 1);
                }
            },
            //上传超过限制
            uploadHandleExceed: function (files, fileList) {
                this.$notify({
                    title: '失败',
                    message: '当前最多上传1个文件',
                    type: 'warning'
                });
            }

        },
        
        created: function() {
            var that = this;
            this.contentTypeOptionsGet()
                .then(function(res) { return that.departmentIdOptionsGet() })
                .then(function(res) {
                    console.log('departmentIdOptionsGet() res:', res);
                    that.form.id = ms.util.getParameter("id");
                    console.log('ms.utils.getParameter(id): ', ms.util.getParameter("id"));
                    if (that.form.id) {
                        that.get(that.form.id);
                    }
                });
        }
    });
</script>
