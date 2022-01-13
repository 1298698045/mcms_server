<!DOCTYPE html>
<html>
<head>
	<title>专家管理</title>
		<#include "../../include/head-file.ftl">
</head>
<body>
	<div id="index" class="ms-index" v-cloak>
		<ms-search ref="search" @search="search" :condition-data="conditionList" :conditions="conditions"></ms-search>
		<el-header class="ms-header" height="50px">
			<el-col :span="12">
				<@shiro.hasPermission name="cms:expert:save">
				<el-button type="primary" icon="el-icon-plus" size="mini" @click="save()">新增</el-button>
			</@shiro.hasPermission>
				<@shiro.hasPermission name="cms:expert:del">
				<el-button type="danger" icon="el-icon-delete" size="mini" @click="del(selectionList)"  :disabled="!selectionList.length">删除</el-button>
				</@shiro.hasPermission>
			</el-col>
		</el-header>
		<div class="ms-search">
			<el-row>
				<el-form :model="form"  ref="searchForm"  label-width="120px" size="mini">
					<el-row>
						<el-col :span="8">
					        <el-form-item  label="工号" prop="jobNumber">
					            <el-input v-model="form.jobNumber"
					                :disabled="false"
					                :style="{width:  '100%'}"
					                :clearable="true"
					                placeholder="请输入内容">
					            </el-input>
					        </el-form-item>
						</el-col>
						<el-col :span="8">
					        <el-form-item  label="姓名" prop="name">
					            <el-input v-model="form.name"
					                :disabled="false"
					                :style="{width:  '100%'}"
					                :clearable="true"
					                placeholder="请输入内容">
					            </el-input>
					        </el-form-item>
						</el-col>
						<el-col :span="8" style="text-align: right;">
							<el-button type="primary" icon="el-icon-search" size="mini" @click="currentPage=1;list()">搜索</el-button>
							<el-button type="primary" icon="iconfont icon-shaixuan1" size="mini" @click="currentPage=1;$refs.search.open()">筛选</el-button>
							<el-button @click="rest"  icon="el-icon-refresh" size="mini">重置</el-button>
						</el-col>
					</el-row>
				</el-form>
			</el-row>
		</div>
		<el-main class="ms-container">
			<el-table height="calc(100vh - 68px)" v-loading="loading" ref="multipleTable" border :data="dataList" tooltip-effect="dark" @selection-change="handleSelectionChange">
				<template slot="empty">
					{{emptyText}}
				</template>
				<el-table-column type="selection" width="40"></el-table-column>
				<el-table-column label="工号"   min-width="80" align="center" prop="jobNumber">
				</el-table-column>
				<el-table-column label="科室"   align="left" prop="departmentId" :formatter="departmentIdFormat">
				</el-table-column>
				<el-table-column label="姓名"   align="left" prop="name">
				</el-table-column>
				<el-table-column label="性别"   align="left" prop="gender" :formatter="genderFormat">
				</el-table-column>
				<el-table-column label="职称"   align="left" prop="technicalTitle">
				</el-table-column>
				<el-table-column label="擅长"   align="left" prop="goodAt">
				</el-table-column>
				<el-table-column label="出诊时间"   align="left" prop="schedule">
				</el-table-column>
				<el-table-column label="停诊开始时间"   align="left" prop="stoppingStart">
				</el-table-column>
				<el-table-column label="停诊截止时间"   align="left" prop="stoppingEnd">
				</el-table-column>
				<el-table-column label="操作"  width="180" align="center">
					<template slot-scope="scope">
						<@shiro.hasPermission name="cms:expert:update">
						<el-link type="primary" :underline="false" @click="save(scope.row.id)">编辑</el-link>
						</@shiro.hasPermission>
						<@shiro.hasPermission name="cms:expert:del">
						<el-link type="primary" :underline="false" @click="del([scope.row])">删除</el-link>
						</@shiro.hasPermission>
						<@shiro.hasPermission name="cms:expert:copy">
						<el-link type="primary" :underline="false" @click="copy(scope.row)">复制</el-link>
						</@shiro.hasPermission>
					</template>
				</el-table-column>
			</el-table>
            <el-pagination
					background
					:page-sizes="[10,20,30,40,50,100]"
					layout="total, sizes, prev, pager, next, jumper"
					:current-page="currentPage"
					:page-size="pageSize"
					:total="total"
					class="ms-pagination"
					@current-change='currentChange'
					@size-change="sizeChange">
            </el-pagination>
         </el-main>
	</div>
</body>

</html>
<script>
var indexVue = new Vue({
	el: '#index',
	data:{
		conditionList:[
        {'action':'and', 'field': 'job_number', 'el': 'eq', 'model': 'jobNumber', 'name': '工号', 'type': 'input'},
        {'action':'and', 'field': 'name', 'el': 'eq', 'model': 'name', 'name': '姓名', 'type': 'input'},
        {'action':'and', 'field': 'department_id', 'el': 'eq', 'model': 'departmentId', 'name': '科室', 'key': 'dictValue', 'title': 'dictLabel', 'type': 'select', 'multiple': false},
        {'action':'and', 'field': 'good_at', 'el': 'eq', 'model': 'goodAt', 'name': '擅长', 'type': 'input'},
        {'action':'and', 'field': 'technical_title', 'el': 'eq', 'model': 'technicalTitle', 'name': '职称', 'type': 'input'},
        ],
		conditions:[],
		dataList: [], //专家管理列表
		selectionList:[],//专家管理列表选中
		total: 0, //总记录数量
        pageSize: 10, //页面数量
        currentPage:1, //初始页
        manager: ms.manager,
		loadState:false,
		loading: true,//加载状态
		emptyText:'',//提示文字
        departmentIdOptions:[],
		//搜索表单
		form:{
			sqlWhere:null,
		},
	},
	watch:{

	},
	methods:{
	    //查询列表
	    list: function() {
	    	var that = this;
			that.loading = true;
			that.loadState = false;
			var page={
				pageNo: that.currentPage,
				pageSize : that.pageSize
			}
			var form = JSON.parse(JSON.stringify(that.form))
			for (key in form){
				if(!form[key]){
					delete  form[key]
				}
			}
			history.replaceState({form:form,page:page},"");
			ms.http.post(ms.manager+"/cms/expert/list.do",form.sqlWhere?Object.assign({},{sqlWhere:form.sqlWhere}, page)
				:Object.assign({},form, page)).then(
					function(res) {
						if(that.loadState){
							that.loading = false;
						}else {
							that.loadState = true
						}
						if (!res.result||res.data.total <= 0) {
							that.emptyText ="暂无数据"
							that.dataList = [];
							that.total = 0;
						} else {
							that.emptyText = '';
							that.total = res.data.total;
							that.dataList = res.data.rows;
						}
					}).catch(function(err) {
				console.log(err);
			});
			setTimeout(function(){
				if(that.loadState){
					that.loading = false;
				}else {
					that.loadState = true
				}
			}, 500);
			},
		//专家管理列表选中
		handleSelectionChange:function(val){
			this.selectionList = val;
		},
		//复制
		copy: function (row) {
			var that = this;
			delete row.id
			ms.util.removeNull(row)
			ms.http.post(ms.manager + "/cms/expert/save.do", row).then(function (res) {
				if (res.result) {
					that.$notify({
						type: 'success',
						message: "复制成功"
					});
					//复制成功，刷新列表
					that.list();
				} else {
					that.$notify({
						title: "错误",
						message: res.msg,
						type: 'warning'
					});
				}
			});
		},
		//删除
        del: function(row){
        	var that = this;
        	that.$confirm("此操作将永久删除所选内容, 是否继续", "提示", {
					    	confirmButtonText: "确认",
					    	cancelButtonText: "取消",
					    	type: 'warning'
					    }).then(function() {
					    	ms.http.post(ms.manager+"/cms/expert/delete.do", row.length?row:[row],{
            					headers: {
                					'Content-Type': 'application/json'
                				}
            				}).then(
	            				function(res){
		            				if (res.result) {
										that.$notify({
						     				type: 'success',
						        			message:"删除成功"
						    			});
					    				//删除成功，刷新列表
					      				that.list();
					      			}else {
										that.$notify({
											title: "错误",
											message: res.msg,
											type: 'warning'
										});
									}
	            				});
					    })
        		},
		//新增
        save:function(id){
        	console.log('save: id:', id);
			if(id){
				location.href = this.manager+"/cms/expert/form.do?id="+id;
			}else {
				location.href = this.manager+"/cms/expert/form.do";
			}
        },

		//表格数据转换
        departmentIdFormat:function(row, column, cellValue, index){
        	var value="";
            if(cellValue){
	            var data = this.departmentIdOptions.find(function(value){
	            	return value.id == cellValue;
	            })
	            if(data&&data.categoryTitle){
	            	value = data.categoryTitle;
	            }
            }
        	return value;
        },

		//表格数据转换
        genderFormat:function(row, column, cellValue, index){
        	var genderOption = {1: '男', 2: '女'};
            if(cellValue){
            	return genderOption[cellValue];
            }
        	return "";
        },
        //pageSize改变时会触发
        sizeChange:function(pagesize) {
			this.loading = true;
            this.pageSize = pagesize;
            this.list();
        },
        //currentPage改变时会触发
        currentChange:function(currentPage) {
			this.loading = true;
			this.currentPage = currentPage;
            this.list();
        },
		search:function(data){
        	this.form.sqlWhere = JSON.stringify(data);
        	this.list();
		},
		//重置表单
		rest:function(){
			this.currentPage = 1;
			this.form.sqlWhere = null;
			this.$refs.searchForm.resetFields();
			this.list();
		},
        //获取departmentId数据源
        departmentIdOptionsGet:function() {
            var that = this;
            return ms.http.get(ms.base+'/cms/category/list.do', 
                {categoryParentIds:'1400724656644583425', pageSize:99999}).then(function (res) {
                that.departmentIdOptions = res.data.rows;
                console.log('that.departmentIdOptions:', that.departmentIdOptions);
            }).catch(function (err) {
                console.log(err);
            });
        },
	},
	created:function(){
	    this.departmentIdOptionsGet();
		if(history.hasOwnProperty("state")&& history.state){
			this.form = history.state.form;
			this.currentPage = history.state.page.pageNo;
			this.pageSize = history.state.page.pageSize;
		}
		this.list();
	},
})
</script>
<style>
	#index .ms-container {
		height: calc(100vh - 78px);
	}
</style>
