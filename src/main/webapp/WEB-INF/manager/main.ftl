<html>
<head>
  <meta charset="utf-8" />
  <title>后台主界面</title>
  <meta http-equiv="content-type" content="text/html" />
  <META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
  <meta name="viewport"
        content="initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no,width=device-width"/>
  <meta name="format-detection" content="telephone=no"/>
  <meta name="app-mobile-web-app-capable" content="yes"/>
  <meta name="app-mobile-web-app-status-bar-style" content="black-translucent"/>
  <script src="${base}/static/plugins/vue/2.6.9/vue.min.js"></script>
  <!--通用图标-->
  <link rel="stylesheet" href="${base}/static/plugins/iconfont/1.0.0/iconfont.css" />
  <script src="${base}/static/plugins/element-ui/2.15.1/index.min.js"></script>
  <link rel="stylesheet" href="${base}/static/plugins/element-ui/2.15.1/theme-chalk/index.min.css" />
  <!--网络请求框架-->
  <script src="${base}/static/plugins/axios/0.18.0/axios.min.js"></script>
  <script src="${base}/static/plugins/qs/6.6.0/qs.min.js"></script>
  <!--铭飞-->
  <script src="${base}/static/plugins/ms/1.0.0/ms.js"></script>
  <script src="${base}/static/plugins/ms/1.0.0/ms.http.js"></script>
  <script src="${base}/static/plugins/ms/1.0.0/ms.util.js"></script>
  <link rel="stylesheet" href="${base}/static/plugins/minireset/0.0.2/minireset.min.css" />
  <script>
    ms.base = '${base}';
    ms.manager = '${managerPath}';
  </script>
  <style>
    [v-cloak]{
      display: none;
    }
  </style>
</head>
<body class="custom-body">
<div id="app" v-cloak>
  <div  class="class-1" >
    <div  class="class-2" >
      <div  class="class-3" >
        <div  class="class-4" >
        </div>
        <div  class="class-5" >
        </div>
        <div  class="class-6" >
        </div>
        <div  class="panel" >
            <div  v-if="alwaysList.length>0" class="panel-title">
              常用功能
            </div>
            <div class="v-space"></div>
            <div class="panel-content" style="flex-direction: row;flex-wrap: wrap; flex: unset">
              <div class="mitem"
                   @click="window.parent.indexVue.openParentMenuInTitle(item.title)"
                   v-for="item in alwaysList">
                <!--图标开始-->
                <i :class="['iconfont',item.icon]"></i>
                <div class="item-title">
                  {{ item.title }}
                </div>
                <!--文本结束-->
              </div>
            </div>
        </div>
      </div>
    </div>
    </div>
  </div>
</div>
</body>
</html>
<script>
  var app = new Vue({
    el: '#app',
    watch: {},
    data: {
      base: ms.base,
      msNewsLast: '',
      msNewsPath: '',
      alwaysList: [], //常用功能列表
    },
    methods: {
      jumpArtcleManager: function () {
        window.parent.indexVue.openMenu({
          modelId: 706,
          modelTitle: '文章管理',
          modelIcon: "icon-neirongguanli"
        });
      },
      //栏目管理
      jumpCategorymanager: function () {
        window.parent.indexVue.open({
          "modelId": 708,
          "modelTitle": "栏目管理",
          "modelId": 706,
          "modelUrl": "cms/category/index.do"
        });
      },
      //静态化
    jumpStaticManager: function () {
        window.parent.indexVue.open({"modelId":709,"modelTitle":"静态化","modelId":706,"modelUrl":"cms/generate/index.do"});
      },
      //管理员管理
      jumpAdmininstatorManager: function () {
        window.parent.indexVue.open({
          "modelId": 411,
          "modelTitle": "管理员管理",
          "modelId": 23,
          "modelUrl": "basic/manager/index.do"
        });
      },
      //角色管理
      jumpUserManager: function () {
        window.parent.indexVue.open({
          "modelId": 406,
          "modelTitle": "角色管理",
          "modelId": 23,
          "modelUrl": "basic/role/index.do"
        });
      },
      //菜单管理
      jumpMenuManager: function () {
        window.parent.indexVue.open({
          "modelId": 183,
          "modelTitle": "菜单管理",
          "modelId": 23,
          "modelUrl": "model/index.do"
        });
      },
      getAlwaysList: function () {
        var markList = localStorage.getItem("markList");
        if (markList) {
          this.alwaysList = JSON.parse(markList)
        }
      },
      //模板管理
      jumpTemplateManager: function () {
        window.parent.indexVue.open({
          "modelId": 87,
          "modelTitle": "模板管理",
          "modelId": 84,
          "modelUrl": "template/index.do"
        });
      },
      setCallBackFun: function () {
        window.parent.indexVue.addCallBackFun(this.getAlwaysList);
      },
      //应用管理
      jumpApplicationManager: function () {
        window.parent.indexVue.open({
          "modelId": 86,
          "modelTitle": "应用管理",
          "modelId": 84,
          "modelUrl": "app/-1/edit.do"
        });
      },
      //铭飞开发文档
      jumpMCMSDocument: function () {
        window.open("http://doc.mingsoft.net/plugs-cms/");
      },
      enterQQOneGroup: function () {
        window.open("https://shang.qq.com/wpa/qunwpa?idkey=ebf251dc9758de6b9c78c499956431cba73e28b3f0b72c0fc28242e98b20fca2");
      },
      enterQQTwoGroup: function () {
        window.open("http://shang.qq.com/wpa/qunwpa?idkey=cfb32b0f47d89d7ef1c3a9493984d4ffbdfe14049fdedd90c517a072e90d68b9");
      },
      enterQQThreeGroup: function () {
        window.open("http://shang.qq.com/wpa/qunwpa?idkey=5dd11fdb492c4ded090fa1f78a166583978e33c4a61301b136d31e9e3eb7df72");
      },
      enterQQFourGroup: function () {
        window.open("http://shang.qq.com/wpa/qunwpa?idkey=565f1e4c4fabeee42947f6c6b96ac7ca4853dece16559d3d78e944ca2931b7f5");
      },
      addBusinessQQ: function () {
        window.open("http://wpa.qq.com/msgrd?v=3&uin=3336073455&site=qq&menu=yes");
      },
      //打开铭飞消息页面
      openMCMSNews: function () {
        window.open(this.msNewsPath);
      },
      getNewsLast: function () {
        var that = this;
        axios.create({
          withCredentials: true
        }).get("https://mingsoft.net/cms/content/list.do?categoryId=202").then(function (res) {
          that.msNewsLast = res.data.data.rows[0].contentTitle.toString();
          that.msNewsPath = 'https://mingsoft.net/html/1/203/202/' + res.data.data.rows[0].id + '.html';
        });
        this.setCallBackFun();
      }
    },
    created: function () {
      this.getNewsLast();
      this.getAlwaysList();
    }
  });
</script>
<style>
  .panel {
    margin-top:10px;
    color: #333333;
    padding-right:20px;
    padding-top:20px;
    max-width:100%;
    padding-left:20px;
    outline-offset: -1px;
    background-color: rgba(255, 255, 255, 1);
    flex-direction: column;
    display: flex;
    animation-duration: 1s;
    background-repeat: no-repeat;
  }
  .panel .panel-content .mitem:hover i ,.panel .panel-content .mitem:hover .item-title{
    color:#409EFF
  }
  .panel-content .mitem i {
    font-size: 40px;
  }
  .panel-content .mitem {
    color: #333333;
    cursor: pointer;
    outline-offset: -1px;
    max-width:100%;
    flex-direction: column;
    display: flex;
    animation-duration: 1s;
    width: 25%;
    background-repeat: no-repeat;
    align-items: center;
    margin-bottom:20px;
  }
  .panel .panel-title {
    color: #333333;
    word-wrap: break-word;
    font-weight: bold;
    display: inline-block;
    animation-duration: 1s;
    font-size: 16px;
  }
  .panel .panel-content {
    color: #333333;
    padding-right: 10px;
    padding-bottom: 10px;
    outline-offset: -1px;
    flex: 1;
    padding-top: 10px;
    max-width: 100%;
    flex-direction: column;
    display: flex;
    animation-duration: 1s;
    width: 100%;
    padding-left: 10px;
    background-repeat: no-repeat;
  }
  .panel .panel-content .mitem .item-title{
    color:#333333;
    word-wrap:break-word;
    display:inline-block;
    animation-duration:1s;
    font-size:14px;
    line-height:1.4;
  }
  .custom-body {
  }
  .class-1
  {
    color:#333333;
    padding-right:10px;
    min-height:720px;
    outline:none;
    padding-bottom:10px;
    overflow:visible;
    outline-offset:-1px;
    flex:6;
    padding-top:10px;
    height:100%;
    background-color:rgba(238, 238, 238, 1);
    flex-direction:row;
    display:flex;
    animation-duration:1s;
    width:100%;
    padding-left:10px;
    background-repeat:no-repeat;
  }
  .class-2
  {
    color:#333333;
    outline:none;
    outline-offset:-1px;
    flex:7;
    height:100%;
    max-width:100%;
    flex-direction:column;
    display:flex;
    animation-duration:1s;
    background-repeat:no-repeat;
  }
  .class-3
  {
    color:#333333;
    outline:none;
    outline-offset:-1px;
    flex:4;
    max-width:100%;
    background-color:rgba(255, 255, 255, 1);
    flex-direction:column;
    display:flex;
    animation-duration:1s;
    background-repeat:no-repeat;
  }
  .class-4
  {
    color:#333333;
    outline:none;
    outline-offset:-1px;
    max-width:100%;
    flex-direction:column;
    display:flex;
    margin-left:20px;
    animation-duration:1s;
    background-repeat:no-repeat;
  }
  .class-5
  {
    color:#333333;
    margin-right:20px;
    outline:none;
    outline-offset:-1px;
    max-width:100%;
    flex-direction:column;
    display:flex;
    animation-duration:1s;
    background-repeat:no-repeat;
  }
  .class-6
  {
    color:#333333;
    margin-right:20px;
    outline:none;
    outline-offset:-1px;
    max-width:100%;
    flex-direction:column;
    display:flex;
    margin-left:20px;
    animation-duration:1s;
    background-repeat:no-repeat;
  }
  .class-7
  {
    color:#333333;
    padding-right:20px;
    outline:none;
    outline-offset:-1px;
    padding-top:20px;
    max-width:100%;
    background-color:rgba(255, 255, 255, 1);
    flex-direction:column;
    display:flex;
    animation-duration:1s;
    padding-left:20px;
    background-repeat:no-repeat;
    margin-top:10px;
  }
  .class-8
  {
    color:#333333;
    outline:none;
    outline-offset:-1px;
    max-width:100%;
    align-items:center;
    flex-direction:row;
    display:flex;
    justify-content:flex-start;
    animation-duration:1s;
    width:100%;
    margin-bottom:20px;
    background-repeat:no-repeat;
  }
  .class-9
  {
    color:#333333;
    word-wrap:break-word;
    font-weight:bold;
    display:inline-block;
    animation-duration:1s;
    font-size:16px;
    line-height:1.4;
  }
  .class-10
  {
    color:#333333;
    outline:none;
    outline-offset:-1px;
    flex-wrap:wrap;
    max-width:100%;
    flex-direction:row;
    display:flex;
    animation-duration:1s;
    width:100%;
    background-repeat:no-repeat;
  }
  .class-11
  {
    cursor:pointer;
    color:#333333;
    outline:none;
    outline-offset:-1px;
    max-width:100%;
    align-items:center;
    text-align:left;
    flex-direction:column;
    display:flex;
    justify-content:center;
    animation-duration:1s;
    width:25%;
    margin-bottom:20px;
    background-repeat:no-repeat;
  }
  .class-12
  {
    color:#333333;
    outline:none;
    outline-offset:-1px;
    height:40px;
    max-width:100%;
    background-color:rgba(17, 205, 110, 1);
    align-items:center;
    flex-direction:row;
    display:flex;
    justify-content:center;
    animation-duration:1s;
    border-radius:4px;
    width:40px;
    background-repeat:no-repeat;
  }
  .class-13
  {
    height:30px;
    animation-duration:1s;
    width:30px;
  }
  .class-14
  {
    color:#333333;
    word-wrap:break-word;
    display:inline-block;
    animation-duration:1s;
    font-size:14px;
    line-height:1.4;
    margin-top:10px;
  }
  .class-15
  {
    cursor:pointer;
    color:#333333;
    outline:none;
    outline-offset:-1px;
    max-width:100%;
    align-items:center;
    text-align:left;
    flex-direction:column;
    display:flex;
    justify-content:center;
    animation-duration:1s;
    width:25%;
    margin-bottom:20px;
    background-repeat:no-repeat;
  }
  .class-16
  {
    color:#333333;
    outline:none;
    outline-offset:-1px;
    height:40px;
    max-width:100%;
    background-color:rgba(0, 153, 255, 1);
    align-items:center;
    flex-direction:row;
    display:flex;
    justify-content:center;
    animation-duration:1s;
    border-radius:4px;
    width:40px;
    background-repeat:no-repeat;
  }
  .class-17
  {
    height:24px;
    animation-duration:1s;
    width:24px;
  }
  .class-18
  {
    color:#333333;
    word-wrap:break-word;
    display:inline-block;
    animation-duration:1s;
    font-size:14px;
    line-height:1.4;
    margin-top:10px;
  }
  .class-19
  {
    cursor:pointer;
    color:#333333;
    outline:none;
    outline-offset:-1px;
    max-width:100%;
    align-items:center;
    text-align:left;
    flex-direction:column;
    display:flex;
    justify-content:center;
    animation-duration:1s;
    width:25%;
    margin-bottom:20px;
    background-repeat:no-repeat;
  }
  .class-20
  {
    color:#333333;
    outline:none;
    outline-offset:-1px;
    height:40px;
    max-width:100%;
    background-color:rgba(255, 68, 68, 1);
    align-items:center;
    flex-direction:row;
    display:flex;
    justify-content:center;
    animation-duration:1s;
    border-radius:4px;
    width:40px;
    background-repeat:no-repeat;
  }
  .class-21
  {
    height:26px;
    animation-duration:1s;
    width:30px;
  }
  .class-22
  {
    color:#333333;
    word-wrap:break-word;
    display:inline-block;
    animation-duration:1s;
    font-size:14px;
    line-height:1.4;
    margin-top:10px;
  }
  .class-23
  {
    cursor:pointer;
    color:#333333;
    outline:none;
    outline-offset:-1px;
    max-width:100%;
    align-items:center;
    text-align:left;
    flex-direction:column;
    display:flex;
    justify-content:center;
    animation-duration:1s;
    width:25%;
    margin-bottom:20px;
    background-repeat:no-repeat;
  }
  .class-24
  {
    color:#333333;
    outline:none;
    outline-offset:-1px;
    background-size:contain;
    background-position:center;
    height:40px;
    max-width:100%;
    background-color:rgba(0, 153, 255, 1);
    align-items:center;
    flex-direction:row;
    display:flex;
    justify-content:center;
    animation-duration:1s;
    border-radius:4px;
    width:40px;
    background-repeat:no-repeat;
  }
  .class-25
  {
    height:28px;
    animation-duration:1s;
    width:28px;
  }
  .class-26
  {
    color:#333333;
    word-wrap:break-word;
    display:inline-block;
    animation-duration:1s;
    font-size:14px;
    line-height:1.4;
    margin-top:10px;
  }
  .class-27
  {
    cursor:pointer;
    color:#333333;
    outline:none;
    outline-offset:-1px;
    max-width:100%;
    align-items:center;
    text-align:left;
    flex-direction:column;
    display:flex;
    justify-content:center;
    animation-duration:1s;
    width:25%;
    margin-bottom:20px;
    background-repeat:no-repeat;
  }
  .class-28
  {
    color:#333333;
    outline:none;
    outline-offset:-1px;
    height:40px;
    max-width:100%;
    background-color:rgba(255, 68, 68, 1);
    align-items:center;
    flex-direction:row;
    display:flex;
    justify-content:center;
    animation-duration:1s;
    border-radius:4px;
    width:40px;
    background-repeat:no-repeat;
  }
  .class-29
  {
    height:32px;
    animation-duration:1s;
    width:32px;
  }
  .class-30
  {
    color:#333333;
    word-wrap:break-word;
    display:inline-block;
    animation-duration:1s;
    font-size:14px;
    line-height:1.4;
    margin-top:10px;
  }
  .class-31
  {
    cursor:pointer;
    color:#333333;
    outline:none;
    outline-offset:-1px;
    max-width:100%;
    align-items:center;
    text-align:left;
    flex-direction:column;
    display:flex;
    justify-content:center;
    animation-duration:1s;
    width:25%;
    margin-bottom:20px;
    background-repeat:no-repeat;
  }
  .class-32
  {
    color:#333333;
    outline:none;
    outline-offset:-1px;
    height:40px;
    max-width:100%;
    background-color:rgba(17, 205, 110, 1);
    align-items:center;
    flex-direction:row;
    display:flex;
    justify-content:center;
    animation-duration:1s;
    border-radius:4px;
    width:40px;
    background-repeat:no-repeat;
  }
  .class-33
  {
    height:32px;
    animation-duration:1s;
    width:32px;
  }
  .class-34
  {
    color:#333333;
    word-wrap:break-word;
    display:inline-block;
    animation-duration:1s;
    font-size:14px;
    line-height:1.4;
    margin-top:10px;
  }
  .class-35
  {
    cursor:pointer;
    color:#333333;
    outline:none;
    outline-offset:-1px;
    max-width:100%;
    align-items:center;
    text-align:left;
    flex-direction:column;
    display:flex;
    justify-content:center;
    animation-duration:1s;
    width:25%;
    margin-bottom:20px;
    background-repeat:no-repeat;
  }
  .class-36
  {
    color:#333333;
    outline:none;
    outline-offset:-1px;
    height:40px;
    max-width:100%;
    background-color:rgba(0, 153, 255, 1);
    align-items:center;
    flex-direction:row;
    display:flex;
    justify-content:center;
    animation-duration:1s;
    border-radius:4px;
    width:40px;
    background-repeat:no-repeat;
  }
  .class-37
  {
    height:28px;
    animation-duration:1s;
    width:28px;
  }
  .class-38
  {
    color:#333333;
    word-wrap:break-word;
    display:inline-block;
    animation-duration:1s;
    font-size:14px;
    line-height:1.4;
    margin-top:10px;
  }
  .class-39
  {
    cursor:pointer;
    color:#333333;
    outline:none;
    outline-offset:-1px;
    max-width:100%;
    align-items:center;
    text-align:left;
    flex-direction:column;
    display:flex;
    justify-content:center;
    animation-duration:1s;
    width:25%;
    margin-bottom:20px;
    background-repeat:no-repeat;
  }
  .class-40
  {
    color:#333333;
    outline:none;
    outline-offset:-1px;
    height:40px;
    max-width:100%;
    background-color:rgba(17, 205, 110, 1);
    align-items:center;
    flex-direction:row;
    display:flex;
    justify-content:center;
    animation-duration:1s;
    border-radius:4px;
    width:40px;
    background-repeat:no-repeat;
  }
  .class-41
  {
    height:28px;
    animation-duration:1s;
    width:28px;
  }
  .class-42
  {
    color:#333333;
    word-wrap:break-word;
    display:inline-block;
    animation-duration:1s;
    font-size:14px;
    line-height:1.4;
    margin-top:10px;
  }
  
  
  .class-59
  {
    color:#333333;
    background-image:url(${base}/static/images/1577257489392.png);
    outline:none;
    outline-offset:-1px;
    background-size:contain;
    background-position:center;
    height:20px;
    max-width:100%;
    flex-direction:row;
    display:flex;
    animation-duration:1s;
    width:20px;
    background-repeat:no-repeat;
  }
  .class-62
  {
    color:#333333;
    background-image:url(${base}/static/images/1577151868190.png);
    outline:none;
    outline-offset:-1px;
    height:40px;
    max-width:100%;
    flex-direction:row;
    display:flex;
    animation-duration:1s;
    width:40px;
    background-repeat:no-repeat;
  }
  .class-96
  {
    color:#333333;
    background-image:url(${base}/static/images/1578031206821.png);
    outline:none;
    outline-offset:-1px;
    background-size:contain;
    background-position:center;
    height:30px;
    max-width:100%;
    align-items:center;
    flex-direction:row;
    display:flex;
    justify-content:center;
    animation-duration:1s;
    border-radius:4px;
    width:30px;
    background-repeat:no-repeat;
  }
  .class-99
  {
    color:#333333;
    background-image:url(${base}/static/images/1578031321635.png);
    outline:none;
    outline-offset:-1px;
    background-size:contain;
    background-position:center;
    height:30px;
    max-width:100%;
    align-items:center;
    flex-direction:row;
    display:flex;
    justify-content:center;
    animation-duration:1s;
    border-radius:4px;
    width:30px;
    background-repeat:no-repeat;
  }
  .class-102
  {
    color:#333333;
    background-image:url(${base}/static/images/1578031484700.png);
    outline:none;
    outline-offset:-1px;
    background-size:contain;
    background-position:center;
    height:30px;
    max-width:100%;
    align-items:center;
    flex-direction:row;
    display:flex;
    justify-content:center;
    animation-duration:1s;
    border-radius:4px;
    width:30px;
    background-repeat:no-repeat;
  }
  .class-105
  {
    color:#333333;
    background-image:url(${base}/static/images/1578031264207.png);
    outline:none;
    outline-offset:-1px;
    background-size:contain;
    background-position:center;
    height:30px;
    max-width:100%;
    align-items:center;
    flex-direction:row;
    display:flex;
    justify-content:center;
    animation-duration:1s;
    border-radius:4px;
    width:30px;
    background-repeat:no-repeat;
  }
  .class-108
  {
    color:#333333;
    background-image:url(${base}/static/images/1578031682848.png);
    outline:none;
    outline-offset:-1px;
    background-size:contain;
    background-position:center;
    height:30px;
    max-width:100%;
    align-items:center;
    flex-direction:row;
    display:flex;
    justify-content:center;
    animation-duration:1s;
    border-radius:4px;
    width:30px;
    background-repeat:no-repeat;
  }
  .class-111
  {
    color:#333333;
    background-image:url(${base}/static/images/1578031639173.png);
    outline:none;
    outline-offset:-1px;
    background-size:contain;
    background-position:center;
    height:30px;
    max-width:100%;
    align-items:center;
    flex-direction:row;
    display:flex;
    justify-content:center;
    animation-duration:1s;
    border-radius:4px;
    width:30px;
    background-repeat:no-repeat;
  }
  .class-114
  {
    color:#333333;
    background-image:url(${base}/static/images/1578031215338.png);
    outline:none;
    outline-offset:-1px;
    background-size:contain;
    background-position:center;
    height:30px;
    max-width:100%;
    align-items:center;
    flex-direction:row;
    display:flex;
    justify-content:center;
    animation-duration:1s;
    border-radius:4px;
    width:30px;
    background-repeat:no-repeat;
  }
  .class-117
  {
    color:#333333;
    background-image:url(${base}/static/images/1578031228196.png);
    outline:none;
    outline-offset:-1px;
    background-size:contain;
    background-position:center;
    height:30px;
    max-width:100%;
    align-items:center;
    flex-direction:row;
    display:flex;
    justify-content:center;
    animation-duration:1s;
    border-radius:4px;
    width:30px;
    background-repeat:no-repeat;
  }
  .class-120
  {
    color:#333333;
    background-image:url(${base}/static/images/1578031234719.png);
    outline:none;
    outline-offset:-1px;
    background-size:contain;
    background-position:center;
    height:30px;
    max-width:100%;
    align-items:center;
    flex-direction:row;
    display:flex;
    justify-content:center;
    animation-duration:1s;
    border-radius:4px;
    width:30px;
    background-repeat:no-repeat;
  }
  @media (max-width: 768px){
    .class-1
    {
      padding-right:12px;
      box-sizing:border-box;
      margin-right:auto;
      outline:1px dashed hsla(0, 0%, 66.7%, .7);
      padding-bottom:12px;
      flex-wrap:wrap;
      padding-top:12px;
      height:200px;
      max-width:100%;
      flex-direction:column;
      margin-left:auto;
      padding-left:12px;
    }
    .class-2
    {
      padding-right:12px;
      box-sizing:border-box;
      margin-right:auto;
      outline:1px dashed hsla(0, 0%, 66.7%, .7);
      padding-bottom:12px;
      flex-wrap:wrap;
      padding-top:12px;
      height:200px;
      margin-left:auto;
      padding-left:12px;
    }
    .class-3
    {
      padding-right:8px;
      box-sizing:border-box;
      outline:1px dashed hsla(0, 0%, 66.7%, .7);
      padding-bottom:8px;
      padding-top:8px;
      height:80px;
      width:80px;
      padding-left:8px;
    }    
  }
  #app{
    overflow-x: hidden;
  }
</style>
