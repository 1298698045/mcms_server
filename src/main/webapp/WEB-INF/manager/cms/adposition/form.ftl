<el-dialog id="form" v-loading="loading" :close-on-click-modal="false" v-cloak title="广告位" :visible.sync="dialogVisible" width="50%">
            <el-form ref="form" :model="form" :rules="rules" label-width="120px" label-position="right" size="small">

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

        <el-form-item  label="广告位宽度" prop="width">
                <el-input
                        v-model.number="form.width"
                        :disabled="false"
                          :readonly="false"
                          :style="{width:  '100%'}"
                          :clearable="true"
                        placeholder="请输入广告位宽度">
                </el-input>
        </el-form-item>

        <el-form-item  label="广告位高度" prop="height">
                <el-input
                        v-model.number="form.height"
                        :disabled="false"
                          :readonly="false"
                          :style="{width:  '100%'}"
                          :clearable="true"
                        placeholder="请输入广告位高度">
                </el-input>
        </el-form-item>

        <el-form-item  label="广告位描述" prop="description">
                <el-input
                        type="textarea" :rows="5"
                        :disabled="false"
                        :readonly="false"
                        v-model="form.description"
                        :style="{width: '100%'}"
                        placeholder="请输入广告位描述">
                </el-input>
        </el-form-item>
            </el-form>
    <div slot="footer">
        <el-button size="mini" @click="dialogVisible = false">取消</el-button>
        <el-button size="mini" type="primary" @click="save()" :loading="saveDisabled">保存</el-button>
    </div>
</el-dialog>
<script>
        var form = new Vue({
        el: '#form',
        data:function() {
            return {
                loading:false,
                saveDisabled: false,
                dialogVisible:false,
                //表单数据
                form: {
                    // 广告位名称
                    name:'',
                    // 广告位宽度
                    width:'',
                    // 广告位高度
                    height:'',
                    // 广告位描述
                    description:'',
                },
                rules:{
                // 广告位名称
                name: [{"required":true,"message":"广告位名称不能为空"},{"min":0,"max":11,"message":"广告位名称长度必须为0-11"}],
                // 广告位宽度
                width: [{"type":"number","message":"广告位宽度格式不正确"},{"required":true,"message":"广告位宽度不能为空"}],
                // 广告位高度
                height: [{"type":"number","message":"广告位高度格式不正确"},{"required":true,"message":"广告位高度不能为空"}],
                },

            }
        },
        watch:{
            dialogVisible:function (v) {
                if(!v){
                    this.$refs.form.resetFields();
                }
            }
        },
        computed:{
        },
        methods: {
            open:function(id){
                if (id) {
                    this.get(id);
                }
                this.$nextTick(function () {
                    this.dialogVisible = true;
                })
            },
            save:function() {
                var that = this;
                var url = ms.manager + "/cms/adposition/save.do"
                if (that.form.id > 0) {
                    url = ms.manager + "/cms/adposition/update.do";
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
                                that.dialogVisible = false;
                                indexVue.list();
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

            //获取当前广告位
            get:function(id) {
                var that = this;
                this.loading = true
                ms.http.get(ms.manager + "/cms/adposition/get.do", {"id":id}).then(function (res) {
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
        }
    });
</script>
