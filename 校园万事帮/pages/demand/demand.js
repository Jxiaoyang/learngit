// pages/demand/demand.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    array:["佣金服务类","游戏组团类","学习组团类"],
    index:0,
    sum:0,
    
  },
  bindPickerChange:function(e){
    this.setData({

      index: e.detail.value

    })
    
  },
  formSubmit:function(e){
    this.setData({
      sum: parseInt(e.detail.value.taskMoney)*parseInt(e.detail.value.taskPeople)
    })
    console.log(e.detail.value)
    wx.request({
      url: 'http://127.0.0.1:8080/helloWorld/task/addTask',
      header: {
        'Cookie': 'JSESSIONID=' + wx.getStorageSync("sessionid"),
        'contentType': 'application/json'
      },
      dataType: "json",
      method: 'post',
      data:JSON.stringify(e.detail.value),
      success:function(res){
        console.log(res.data)

      },
      fail:function(){
        console.log("default")
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