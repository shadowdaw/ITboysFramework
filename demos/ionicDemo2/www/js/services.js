var _url="http://admin.53xsd.com/mobi/";


angular.module('starter.services', [])

/**
 * A simple example service that returns some data.
 */
.factory('Friends', function($http, $q) {
  var friends=new Array();
  friends.push(new friend(0,'Scruff McGruff'));
  friends.push(new friend(1,'Show Cai'));
    

    var three = [
    { id: 0, name: 'AAAA' },
    { id: 1, name: 'BBBB' },
    { id: 3, name: 'CCCC' }
  ];

  return {
    all: function() {
     return friends;
    },
    three: function() {
      return three;
    },
    httpget:function(){
      var friendNew;
      var httpReq2 = null;
      httpReq2 = new plugin.HttpRequest();
      httpReq2.get    ("http://pretty.o2o2m.com/cheshi/test", function(status, data) {
        friendNew=data;
      });
        return friendNew;
    },
    get: function(friendId) {
      // Simple index lookup
      return friends[friendId];
    },
    jsonp: function() {
      var deferred = $q.defer();
      $http({
        method: 'JSONP',
        url: 'http://pretty.o2o2m.com/cheshi/testjson?callbackName=JSON_CALLBACK'
      }).success(function(data){
        deferred.resolve(data);
      }).error(function(){
        deferred.reject('There was an error')
      })
      return deferred.promise;
    }
  }
})


.factory('Shops', function($http, $q) {
  var shops1 = [
    { id: 0, name: 'Phillip',distance:'8.5',postion:'西湖区银泰in77-B1',logo:'logo_01.png' },
    { id: 1, name: 'Elle.Homme',distance:'8.5',postion:'西湖区银泰in77-B1',logo:'logo_02.png' },
    { id: 2, name: 'H&M',distance:'8.5',postion:'西湖区银泰in77-B1',logo:'logo_03.png' },
    { id: 3, name: 'Mo&Co',distance:'8.5',postion:'西湖区银泰in77-B1',logo:'logo_04.png' },
    { id: 4, name: 'MaxMara',distance:'8.5',postion:'西湖区银泰in77-B1',logo:'logo_05.png' },
    { id: 5, name: 'ZARA',distance:'8.5',postion:'西湖区银泰in77-B1',logo:'logo_06.png' }
  ];

    var shops2 = [
    { id: 4, name: 'MaxMara',distance:'8.5',postion:'西湖区银泰in77-B1',logo:'logo_05.png' },
    { id: 2, name: 'ZARA',distance:'8.5',postion:'西湖区银泰in77-B1',logo:'logo_06.png' },
    { id: 0, name: 'Phillip',distance:'8.5',postion:'西湖区银泰in77-B1',logo:'logo_01.png' },
    { id: 5, name: 'Elle.Homme',distance:'8.5',postion:'西湖区银泰in77-B1',logo:'logo_02.png' },
    { id: 1, name: 'H&M',distance:'8.5',postion:'西湖区银泰in77-B1',logo:'logo_03.png' },
    { id: 3, name: 'Mo&Co',distance:'8.5',postion:'西湖区银泰in77-B1',logo:'logo_04.png' }
  ];

  var categorys = [
    { id: 0, name: '女装',logo:'lady.png',chosen:'lady_active.png'},
    { id: 1, name: '男装',logo:'man.png',chosen:'man_active.png' },
    { id: 2, name: '裤子',logo:'pants.png',chosen:'pants_active.png' },
    { id: 3, name: '家具',logo:'furniture.png' ,chosen:'furniture_active.png'},
    { id: 4, name: '配饰',logo:'ornament.png',chosen:'ornament_active.png' },
    { id: 5, name: '鞋子',logo:'shoes.png' ,chosen:'shoes_active.png'},
    { id: 6, name: '内衣',logo:'underwear.png',chosen:'underwear_active.png' },
    { id: 7, name: '包包',logo:'bag.png',chosen:'bag_active.png' }
  ];
  
  return {
    categorys: function() {
      return categorys;
    },
    one: function() {
      return shops1;
    },
    two: function() {
      return shops2;
    },
    get: function(friendId) {
      // Simple index lookup
      return friends[friendId];
    }, 
    getcategorys: function(id) {
      var deferred = $q.defer();
      $http({
        method: 'JSONP',
        url: _url+'getCategory?callbackName=JSON_CALLBACK&parentId='+id
      }).success(function(data){
        deferred.resolve(data);
      }).error(function(data,status){
        deferred.reject(status)
      })
      return deferred.promise;
    },
    getShops: function(id) {
      var deferred = $q.defer();
      $http({
        method: 'JSONP',
        url: _url+'getShopsByCategory?callbackName=JSON_CALLBACK&categoryId='+id
      }).success(function(data){
        deferred.resolve(data);
      }).error(function(data,status){
        deferred.reject(status)
      })
      return deferred.promise;
    },
    getShopdetail: function(id) {
      var deferred = $q.defer();
      $http({
        method: 'JSONP',
        url: _url+'getProductByShopId?callbackName=JSON_CALLBACK&shopId='+id
      }).success(function(data){
        deferred.resolve(data);
      }).error(function(data,status){
        deferred.reject(status)
      })
      return deferred.promise;
    }
  }
})



.factory('Shopdetail', function() {
  var productcates = [
    { id: 0, name: '女士' },
    { id: 1, name: '外套' },
    { id: 2, name: '毛衣' },
    { id: 3, name: '长袖' },
    { id: 4, name: '内衣' },
    { id: 5, name: '背心' },
    { id: 6, name: '裤子' }
  ];

  return {
    categorys: function() {
      return productcates;
    },
    get: function(friendId) {
      // Simple index lookup
      return productcates [friendId];
    }
  }
})

.factory('Getregions', function() {
  var regionoptions = [
   {id: 8,name: '西湖区' },
   {id: 1,name: '拱墅区' },
   {id: 2,name: '上城区' },
   {id: 3,name: '余杭区' },
   {id: 4,name: '江干区' },
   {id: 5,name: '下城区' },
   {id: 6,name: '滨江区' },
   {id: 7,name: '萧山区' }
  ];

  return {
    categorys: function() {
      return regionoptions;
    },
    get: function(friendId) {
      // Simple index lookup
      return regionoptions [friendId];
    }
  }
})

.factory('Location', function($http, $q) {
  return {
    getLocation: function() {
       var deferred = $q.defer();
      $http({
        method: 'JSONP',
        url: 'http://api.map.baidu.com/location/ip?ak=TDQZ19gKkAGvwKO6HTRFDZpS&callback=JSON_CALLBACK'
      }).success(function(data){
        deferred.resolve(data);
      }).error(function(data,status){
        deferred.reject(status)
      })
      return deferred.promise;
    },
  getAreas: function(cityName) {
       var deferred = $q.defer();
      $http({
        method: 'JSONP',
        url: _url+'getAreaByCityName?callbackName=JSON_CALLBACK&cityName='+cityName
      }).success(function(data){
        deferred.resolve(data);
      }).error(function(data,status){
        deferred.reject(status)
      })
      return deferred.promise;
    }
  }
})

.factory('MemberService', function($http, $q) {
  var member;
  return {    
    getMember: function() {
      return member;
    },
    setMember: function(data) {
      member=data;
    },
    doReg: function(data) {
       var deferred = $q.defer();
      $http({
        method: 'JSONP',
        params:data,
        url: 'http://admin.53xsd.com/user/reg?callbackName=JSON_CALLBACK'
      }).success(function(data){
        deferred.resolve(data);
      }).error(function(data,status){
        deferred.reject(status)
      })
      return deferred.promise;
    },
  doLogin: function(data) {
       var deferred = $q.defer();
      $http({
        method: 'JSONP',
        params:data,
        url: 'http://admin.53xsd.com/user/log?callbackName=JSON_CALLBACK'
      }).success(function(data){
        deferred.resolve(data);
      }).error(function(data,status){
        deferred.reject(status)
      })
      return deferred.promise;
    }
  }
})

.factory('IndexService', function($http, $q) {
  var menus=[
   {id: 1,name: '服装鞋帽',logo:'img/temp/icon/icon-cloth.png' },
   {id: 2,name: '餐饮美食',logo:'img/temp/icon/icon-food.png' },
   {id: 3,name: '酒店宾馆',logo:'img/temp/icon/icon-hotel.png' },
   {id: 4,name: '旅游观光',logo:'img/temp/icon/icon-plane.png' },
   {id: 5,name: '休闲娱乐',logo:'img/temp/icon/icon-drink.png' },
   {id: 6,name: '美容美发',logo:'img/temp/icon/icon-hair.png' },
   {id: 7,name: '家具装饰',logo:'img/temp/icon/icon-bed.png' },
   {id: 8,name: '电子数码',logo:'img/temp/icon/icon-pad.png' },
   {id: 9,name: '工艺礼品',logo:'img/temp/icon/icon-gift.png' },
   {id: 10,name: '日用百货',logo:'img/temp/icon/icon-book.png' },
   {id: 11,name: '汽车摩托',logo:'img/temp/icon/icon-car.png' },
   {id: 12,name: '更多',logo:'img/temp/icon/icon-more.png' }
  ];
  return {    
    get: function() {
      return menus;
    },
    setMember: function(data) {
      member=data;
    }
  }
})
;








function friend(id,name){ //use constructor 
this.id=id; 
this.name=name; 
return this; 
} 



