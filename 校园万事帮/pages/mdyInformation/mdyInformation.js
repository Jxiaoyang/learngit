// pages/mdyInformation/mdyInformation.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    img:'',
    index:0
    
  },
  upload:function(){
    var that = this;
    wx.showActionSheet({
      itemList: ['从相册中选择','拍照'],
      itemColor:'#f7982a',
      success:function(res){

        if(!res.cancel){
          if(res.tapIndex==0){
            that.chooseWxImage('album')

          }else if(res.tapIndex==1){
            that.chooseWxImage('camera')
          }
        }

      }
    })
   
  },
  chooseWxImage:function(type){
    let that = this;
    wx.chooseImage({
      count: 1,
      sizeType: ['original', 'compressed'],
      sourceType: [type],
      success(res) {
        // tempFilePath可以作为img标签的src属性显示图片
        const tempFilePaths = res.tempFilePaths
        console.log(res)
        that.setData({
          img:res.tempFilePaths[0],
          
        })
        console.log(that.data.img)
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
    let that = this;
    wx.request({
      url: 'http://127.0.0.1:8080/helloWorld/student/getInformation',
      data:{},
      method:'post',
      header:{
        'Cookie':'JSESSIONID=' + wx.getStorageSync("sessionid"),
      },
      success:function(res){
        console.log(res.data)
        if(that.data.index==1){    //若更改图片则不做任何
        }else{
          that.setData({
            img: 'http://localhost:8080/helloWorld/student/img?name=' + res.data.stu_img,
            list:res.data
          })
        }
        
      },
      fail:function(){
        console.log('失败')
      }
      

    })
  
  },
  toUpload:function(){
    let that = this;
    wx.uploadFile({
      url: 'http://127.0.0.1:8080/helloWorld/student/upload',
      filePath: that.data.img,
      name: 'file',
      formData:{
        'user':'test'
      },
      header: {
        'Cookie': 'JSESSIONID=' + wx.getStorageSync("sessionid"),
      },
      
      success:function(res){
        console.log('haha')
        console.log(res.data)
      }
    })
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
    this.setData({
      index:1     //用来判断石否 更改图片
    })
  
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