// pages/myPut/myPut.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    navbar:['待接受','待完成','已完成'],
    index:0,
    currentTab: 0,
  },
  navbarTap:function(e){
    this.setData({
      currentTab:e.currentTarget.dataset.idx
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
    wx.request({
      url: 'http://127.0.0.1:8080/helloWorld/task/getMyPut',
      data: {},
      method: 'post',
      header: {
        'Cookie': 'JSESSIONID=' + wx.getStorageSync("sessionid"),
      },
      success: function (res) {
        console.log(res.data)
      },
      fail: function () {
        console.log('失败')
      }


    })
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