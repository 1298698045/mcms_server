<!DOCTYPE html>
<html>
<head>
	<title>广告位</title>
		<#include "../../include/head-file.ftl">
</head>
<body>
	<div id="index" class="ms-index" v-cloak>
		<ms-search ref="search" @search="search" :condition-data="conditionList" :conditions="conditions"></ms-search>
			<el-header class="ms-header" height="50px">
				<el-col :span="12">
					<@shiro.hasPermission name="custom:adposition:save">
					<el-button type="primary" icon="el-icon-plus" size="mini" @click="save()">新增</el-button>
				</@shiro.hasPermission>
					<@shiro.hasPermission name="custom:adposition:del">
					<el-button type="danger" icon="el-icon-delete" size="mini" @click="del(selectionList)"  :disabled="!selectionList.length">删除</el-button>
					</@shiro.hasPermission>
				</el-col>
			</el-header>
		<div class="ms-search">
			<el-row>
				<el-form :model="form"  ref="searchForm"  label-width="120px" size="mini">
							<el-row>
											<el-col :span="8">

        <el-form-item  label="广告位名称" prop="name">
                <el-input
                        v-model="form.name"
                        :disabled="false"
                          :readonly="false"
                          :style="{width:  '100%'}"
                          :clearable="true"
                        placeholder="请输入广告位名称">
                </el-input>
         </el-form-item>
											</el-col>
										<el-col :span="16" style="text-align: right;">
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
                 <el-table-column label="编号"   min-width="80" align="center" prop="id">
                 </el-table-column>
                 <el-table-column label="广告位名称"   align="left" prop="name">
                 </el-table-column>
                <el-table-column label="广告位高度"   align="right" prop="height">
                </el-table-column>
                <el-table-column label="广告位宽度"   align="right" prop="width">
                </el-table-column>
                 <el-table-column label="广告位描述"   align="left" prop="description">
                 </el-table-column>
				<el-table-column label="操作"  width="180" align="center">
					<template slot-scope="scope">
						<@shiro.hasPermission name="custom:adposition:update">
						<el-link type="primary" :underline="false" @click="save(scope.row.id)">编辑</el-link>
						</@shiro.hasPermission>
						<@shiro.hasPermission name="custom:adposition:del">
						<el-link type="primary" :underline="false" @click="del([scope.row])">删除</el-link>
						</@shiro.hasPermission>
						<@shiro.hasPermission name="custom:adposition:copy">
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
		<#include "/cms/adposition/form.ftl">
</body>

</html>
<script>
var indexVue = new Vue({
	el: '#index',
	data:{
		conditionList:[
        {'action':'and', 'field': 'name', 'el': 'eq', 'model': 'name', 'name': '广告位名称', 'type': 'input'},
         {'action':'and', 'field': 'width', 'el': 'eq', 'model': 'width', 'name': '广告位宽度', 'type': 'number'},
         {'action':'and', 'field': 'height', 'el': 'eq', 'model': 'height', 'name': '广告位高度', 'type': 'number'},
         {'action':'and', 'field': 'description', 'el': 'eq', 'model': 'description', 'name': '广告位描述', 'type': 'textarea'},
            {'action':'and', 'field': 'create_date', 'el': 'eq', 'model': 'createDate', 'name': '创建时间', 'type': 'date'},
           {'action':'and', 'field': 'update_date', 'el': 'eq', 'model': 'updateDate', 'name': '修改时间', 'type': 'date'},
 		],
		conditions:[],
		dataList: [], //广告位列表
		selectionList:[],//广告位列表选中
		total: 0, //总记录数量
        pageSize: 10, //页面数量
        currentPage:1, //初始页
        manager: ms.manager,
		loadState:false,
		loading: true,//加载状态
		emptyText:'',//提示文字
		//搜索表单
		form:{
			sqlWhere:null,
			// 广告位名称
			name:null,
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
			ms.http.post(ms.manager+"/cms/adposition/list.do",form.sqlWhere?Object.assign({},{sqlWhere:form.sqlWhere}, page)
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
		//广告位列表选中
		handleSelectionChange:function(val){
			this.selectionList = val;
		},
		//复制
		copy: function (row) {
			var that = this;
			delete row.id
			ms.http.post(ms.manager + "/cms/adposition/save.do", row).then(function (res) {
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
					    	ms.http.post(ms.manager+"/cms/adposition/delete.do", row.length?row:[row],{
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
			form.open(id);
        },

//表格数据转换

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
	},
	created:function(){
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
		height: calc(100vh - 141px);
	}
</style>
