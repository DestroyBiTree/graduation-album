(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["pages-home-home"],{"18be":function(t,i,a){var e=a("24fb");i=e(!1),i.push([t.i,'@charset "UTF-8";\n/* 水平间距 */\n/* 水平间距 */.mainClass[data-v-1c78e176]{width:100%;height:100%;position:absolute;left:0;top:0}.swiperclass[data-v-1c78e176]{width:100%;height:100%;position:absolute;left:0;top:0}.swiper-i[data-v-1c78e176]{width:100%;height:100%;position:absolute;left:0;top:0}.swiper-item[data-v-1c78e176]{width:100%;height:100%;position:absolute;left:0;top:0}.bg-set[data-v-1c78e176]{width:100%;height:100%;position:absolute;left:0;top:0}.dmbox[data-v-1c78e176]{position:absolute;width:50px;height:50px;right:10px;top:15px}.barrage_box[data-v-1c78e176]{position:absolute;width:50px;height:50px;top:0;z-index:2}.musicbox[data-v-1c78e176]{position:absolute;width:50px;height:50px;right:10px;top:70px}.bottomiconbox[data-v-1c78e176]{position:absolute;width:40px;height:40px;bottom:10px}.music_box_s[data-v-1c78e176]{position:absolute;width:50px;height:50px;top:0;z-index:2}@-webkit-keyframes box-ani-data-v-1c78e176{from{-webkit-transform:rotate(0);transform:rotate(0)}to{-webkit-transform:rotate(1turn);transform:rotate(1turn)}}@keyframes box-ani-data-v-1c78e176{from{-webkit-transform:rotate(0);transform:rotate(0)}to{-webkit-transform:rotate(1turn);transform:rotate(1turn)}}.mcStop[data-v-1c78e176]{height:50px;width:50px;position:absolute;z-index:2;top:0;-webkit-animation:box-ani-data-v-1c78e176 4s infinite linear;animation:box-ani-data-v-1c78e176 4s infinite linear\n  /*旋转动画*/}.mcStart[data-v-1c78e176]{height:50px;width:50px;position:absolute;z-index:2;top:0}.bottomicon_box_s[data-v-1c78e176]{position:absolute;width:40px;height:40px;top:0;z-index:2}.bottom-dm-pic-login[data-v-1c78e176]{height:%?60?%;border-radius:%?100?%;background-color:rgba(0,0,0,.2);align-items:center;justify-content:center}.btn-bottom-icon[data-v-1c78e176]{position:absolute;width:50px;height:50px;top:0;z-index:2}',""]),t.exports=i},"9a2f":function(t,i,a){"use strict";Object.defineProperty(i,"__esModule",{value:!0}),i.default=void 0;var e=uni.createInnerAudioContext();e.autoplay=!0,e.loop=!0;var o={data:function(){return{background:["color1","color2","color3"],indicatorDots:!0,autoplay:!0,interval:2e3,duration:500,circular:!0,isplay:!0,obeyMuteSwitch:!0,vertical:!0}},onLoad:function(){this.clickPlay()},methods:{changeIndicatorDots:function(t){this.indicatorDots=!this.indicatorDots},changeAutoplay:function(t){this.autoplay=!this.autoplay},intervalChange:function(t){this.interval=t.target.value},durationChange:function(t){this.duration=t.target.value},clickPlay:function(){1==this.isplay?(console.log(this.isplay),e.src="https://bjetxgzv.cdn.bspapp.com/VKCEYUGU-hello-uniapp/2cc220e0-c27a-11ea-9dfb-6da8e309e0d8.mp3",e.play(),this.isplay=!this.isplay,console.log(this.isplay)):0==this.isplay&&(e.pause(),this.isplay=!this.isplay,console.log(this.isplay)),e.onError((function(t){console.log(t.errMsg),console.log(t.errCode)}))},login:function(){uni.navigateTo({url:"/pages/login/login"})}}};i.default=o},bd0b:function(t,i,a){"use strict";a.r(i);var e=a("9a2f"),o=a.n(e);for(var n in e)"default"!==n&&function(t){a.d(i,t,(function(){return e[t]}))}(n);i["default"]=o.a},bfdf:function(t,i,a){var e=a("18be");"string"===typeof e&&(e=[[t.i,e,""]]),e.locals&&(t.exports=e.locals);var o=a("4f06").default;o("4f296b25",e,!0,{sourceMap:!1,shadowMode:!1})},d0ee:function(t,i,a){"use strict";var e=a("bfdf"),o=a.n(e);o.a},e2e0:function(t,i,a){"use strict";var e;a.d(i,"b",(function(){return o})),a.d(i,"c",(function(){return n})),a.d(i,"a",(function(){return e}));var o=function(){var t=this,i=t.$createElement,a=t._self._c||i;return a("v-uni-view",{staticClass:"mainClass"},[a("v-uni-view",{staticClass:"musicbox"},[a("v-uni-image",{class:[t.isplay?"mcStart":"mcStop"],attrs:{src:"https://vkceyugu.cdn.bspapp.com/VKCEYUGU-68ddb9d0-2555-4cfa-8492-a8783c90a7b1/41e923e2-2583-4cfd-8e87-a5ac6f82ae4b.png"},on:{click:function(i){arguments[0]=i=t.$handleEvent(i),t.clickPlay.apply(void 0,arguments)}}})],1),a("v-uni-view",[a("v-uni-swiper",{staticClass:"swiperclass",attrs:{circular:t.circular,vertical:!0,interval:t.interval,duration:t.duration}},[a("v-uni-swiper-item",{staticClass:"swiper-i"},[a("v-uni-view",{staticClass:"swiper-item"},[a("v-uni-image",{staticClass:"bg-set",attrs:{src:"https://vkceyugu.cdn.bspapp.com/VKCEYUGU-68ddb9d0-2555-4cfa-8492-a8783c90a7b1/038b4498-5637-4e2b-bfc8-43a3ebd7c4e4.jpg"}})],1)],1),a("v-uni-swiper-item",{staticClass:"swiper-i"},[a("v-uni-view",{staticClass:"swiper-item"},[a("v-uni-image",{staticClass:"bg-set",attrs:{src:"https://vkceyugu.cdn.bspapp.com/VKCEYUGU-68ddb9d0-2555-4cfa-8492-a8783c90a7b1/f7890e88-3130-4103-ab13-8c49486cdd0e.jpg"}}),a("v-uni-view",{staticClass:"center_text"},[a("v-uni-text",[t._v("毕业季\\n")]),a("v-uni-text",[t._v("花开花落，草枯草荣，岁月流逝\\n")]),a("v-uni-text",[t._v("转瞬间\\n")]),a("v-uni-text",[t._v("你在西大的学生时代即将落幕\\n")]),a("v-uni-text",[t._v("2022年的7月\\n")]),a("v-uni-text",[t._v("你将要挥别你的母校\\n")]),a("v-uni-text",[t._v("踏入下一个成长阶段\\n")])],1)],1)],1)],1)],1),a("v-uni-view",{staticClass:"flex align-center justify-between",staticStyle:{position:"fixed",left:"0",bottom:"0",right:"0",height:"120rpx"}},[a("v-uni-view",{staticClass:"bottomiconbox",staticStyle:{right:"20px"},on:{click:function(i){arguments[0]=i=t.$handleEvent(i),t.login.apply(void 0,arguments)}}},[a("v-uni-image",{staticClass:"bottomicon_box_s",attrs:{src:"https://vkceyugu.cdn.bspapp.com/VKCEYUGU-68ddb9d0-2555-4cfa-8492-a8783c90a7b1/4b71289b-52f6-41de-948a-98785e22f677.png"}})],1)],1)],1)},n=[]},e83a:function(t,i,a){"use strict";a.r(i);var e=a("e2e0"),o=a("bd0b");for(var n in o)"default"!==n&&function(t){a.d(i,t,(function(){return o[t]}))}(n);a("d0ee");var s,c=a("f0c5"),r=Object(c["a"])(o["default"],e["b"],e["c"],!1,null,"1c78e176",null,!1,e["a"],s);i["default"]=r.exports}}]);