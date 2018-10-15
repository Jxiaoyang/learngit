//app.js
App({

  //登录 函数
  // tologin: function () {
  //   wx.login({
  //     success: function (res) {
  //       if (res.code) {
  //         //发起网络请求
  //         console.log(res.code)
  //         wx.request({
  //           url: 'http://localhost:8080/HelpAnything/login/openid', // 访问 servlet 获取 session_key 和openid
  //           method: 'get',
  //           data: {
  //             code: res.code
  //           },
  //           success(res) {
  //             console.log(res.data);
  //             wx.setStorageSync("sessionid", res.data);  //本地存储登录状态
  //              //登录成功则跳转页面
  //             wx.navigateTo({
  //               url: '../homePage/homePage'
  //             })
             
  //           },
  //           fail(res) {
  //             console.log("获取openid失败")
  //           }
  //         })
  //       } else {
  //         console.log('登录失败！' + res.errMsg)
  //       }
  //     }
  //   });
  // },






  /**
   * 当小程序初始化完成时，会触发 onLaunch（全局只触发一次）
   */
  onLaunch: function () {
   
  },

  /**
   * 当小程序启动，或从后台进入前台显示，会触发 onShow
   */
  onShow: function (options) {

  },

  /**
   * 当小程序从前台进入后台，会触发 onHide
   */
  onHide: function () {

  },

  /**
   * 当小程序发生脚本错误，或者 api 调用失败时，会触发 onError 并带上错误信息
   */
  onError: function (msg) {

  }
})
