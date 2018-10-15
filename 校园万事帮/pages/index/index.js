//index.js
//获取应用实例
const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {

  },
  checkId:function(e){
    var that = this;
    let name = e.detail.value; //获取值
    if (name.length <= 10) {
      wx.showToast({
        title: '账号错误',
        icon: 'fail',
       // image: '../../image/fail.png'
        
      })
      setTimeout(function () {
        that.setData({
          expressname: '',
        })
      }, 1000)
    }
  },
  checkPwd:function(){

  },
  
  formSubmit: function (e){
    console.log(e.detail.value)
    let value = JSON.stringify(e.detail.value);
    console.log(value);
    wx.request({
      url: 'http://localhost:8080/helloWorld/login/toLogin',
      header:{
        
        'contentType':'application/json'
      },
      dataType: "json", 
      method:'post',
      data: value,
      success:function(res){
        console.log(res.data.flag)
        if(res.data.flag=='1'){   //登录成功获取SessionID
          wx.setStorageSync("sessionid", res.data.SessionID);
          console.log(res.data.SessionID);
          wx.showToast({
            title: '登录成功',
            icon:'success',
            duration:1500
          })
          wx.switchTab({
            url: '../homePage/homePage',
          })
          
          
        }else{
          wx.showToast({
            title: '账号或密码错误',
            icon: 'none',
            duration: 1500
          })
          
        }
      }
    })
   

  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})