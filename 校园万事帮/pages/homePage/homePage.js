var time = require('../../utils/util.js');
Page({
  /**
   * 页面的初始数据
   */
  data: {
    index: 0,
    navbar: ['佣金服务类', '学习组团类','游戏组团类'],
    currentTab:0,
    newsList: [{'dem_expressname':'sdfsdf'},10],
    DataList1:[],
    DataList2:[],
    DataList3:[],
    
  },
  
  //日期格式化 根据后台传的数据进行格式化
  timeFormate:function(){
    var DataList1 = this.data.DataList1;
    var DataList2 = this.data.DataList2;
    var DataList3 = this.data.DataList3;
    for(var i = 0 ; i < DataList1.length;i++){  
      DataList1[i].task_taskTime = time.formatTimeTwo(DataList1[i].task_taskTime,'M月D日 h:m:s');
      
    }
    for(var i = 0 ; i < DataList2.length;i++){
      DataList2[i].task_taskTime = time.formatTimeTwo(DataList2[i].task_taskTime, 'M月D日 h:m:s');
    }
    for(var i = 0 ; i < DataList3.length;i++){
      DataList3[i].task_taskTime = time.formatTimeTwo(DataList3[i].task_taskTime, 'M月D日 h:m:s');
    }
    this.setData({
      DataList1:DataList1,
      DataList2: DataList2,
      DataList3: DataList3,
    })

  },
  navbarTap: function (e) {
    var that = this

    this.setData({
      currentTab: e.currentTarget.dataset.idx
    })

  },
  send:function(){
   wx.request({
     url: '',
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
    var that = this
    wx.request({
      url: 'http://localhost:8080/helloWorld/task/getTask',
      header: {
        'Cookie': 'JSESSIONID=' + wx.getStorageSync("sessionid"),
      },
     
      data:{},
      success:function(res){
        
        console.log(res.data)
        that.setData({
          DataList1: res.data[0],
          DataList2:res.data[1],
          DataList3:res.data[2]
        })
        
          that.timeFormate();

        
        
        
        
       
        
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