webpackJsonp([2],{ApRj:function(t,e,s){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var n=s("Ub+T"),o={name:"comments",data:function(){return{flag:!1,isDel:!1,song:this.$store.state.playSong,singer:this.$store.state.playSinger,delCommentId:0,comments:[{userPic:"",user:"",content:"",commentId:0}],recomments:[{userPic:"",user:"",content:"",commentId:0}]}},watch:{"$store.state.playSong":function(){var t=this;Object(n.a)({url:"/comment/hot",params:{id:this.$store.state.playId,type:0}}).then(function(e){for(var s=0;e.data.hotComments[s];s++)console.log("index；"+s),t.$set(t.recomments,s,{userPic:e.data.hotComments[s].user.avatarUrl,user:e.data.hotComments[s].user.nickname,content:e.data.hotComments[s].content,commentId:e.data.hotComments[s].commentId});console.log("热评："+e)}).catch(function(t){console.log(t)}),this.getNewComments()}},created:function(){var t=this;""===this.$store.state.playSong?(alert("请选择歌曲播放"),this.$router.push("/index/discover")):(Object(n.a)({url:"/comment/hot",params:{id:this.$store.state.playId,type:0}}).then(function(e){for(var s=0;e.data.hotComments[s];s++)console.log("index；"+s),t.$set(t.recomments,s,{userPic:e.data.hotComments[s].user.avatarUrl,user:e.data.hotComments[s].user.nickname,content:e.data.hotComments[s].content,commentId:e.data.hotComments[s].commentId});console.log(e)}).catch(function(t){console.log(t)}),this.getNewComments())},destroyed:function(){clearTimeout(this.timer)},methods:{getNewComments:function(){var t=this;Object(n.a)({url:"/comment/music",params:{id:this.$store.state.playId,limit:100}}).then(function(e){for(var s=0;e.data.comments[s];s++)t.$set(t.comments,s,{userPic:e.data.comments[s].user.avatarUrl,user:e.data.comments[s].user.nickname,content:e.data.comments[s].content,commentId:e.data.comments[s].commentId});console.log("更新评论"),console.log(e)}).catch(function(t){console.log(t)})},timer:function(){var t=this;return setTimeout(function(){t.getNewComments()},1e4)},open:function(){var t=this;null===this.$store.state.user.userPic?alert("请先登录"):this.$prompt("音乐始于故事","",{confirmButtonText:"评论",cancelButtonText:"取消",inputType:"textarea",inputPlaceholder:"发表评论",confirmButtonClass:"pinglun"}).then(function(e){var s=e.value;console.log(123),Object(n.a)({url:"/comment",params:{t:1,type:0,id:t.$store.state.playId,content:s}}).then(function(e){console.log("评论成功");var s={userPic:e.data.comment.user.avatarUrl,user:e.data.comment.user.nickname,content:e.data.comment.content,commentId:e.data.comment.commentId};t.comments.unshift(s),console.log(s),console.log(t.comments),console.log(e)}).catch(function(t){console.log(t)}),t.flag=!t.flag,t.$message({type:"success",message:"评论成功"})}).catch(function(){t.$message({type:"info",message:"取消评论"})})},openDel:function(){var t=this;this.$confirm("此操作将永久删除评论, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",confirmButtonClass:"pinglun",type:"warning"}).then(function(){Object(n.a)({url:"/comment",params:{t:0,type:0,id:t.$store.state.playId,commentId:t.delCommentId}}).then(function(e){console.log("删除评论 "),console.log(e),t.getNewComments()}).catch(function(t){console.log(t)}),t.$message({type:"success",message:"删除成功!"}),t.isDel=!t.isDel}).catch(function(){t.$message({type:"info",message:"已取消删除"}),t.isDel=!t.isDel})},deleteComment:function(t){t.user===this.$store.state.user.userName&&(this.isDel=!this.isDel,this.delCommentId=t.commentId)}}},a={render:function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",[t._m(0),t._v(" "),s("div",{staticClass:"comments"},[s("h1",{staticClass:"mus-tit"},[t._v(t._s(t.song)+"-"+t._s(t.singer))]),t._v(" "),t._m(1),t._v(" "),s("div",{staticClass:"retext text"},t._l(t.recomments,function(e,n){return s("div",{key:n,staticClass:"com"},[s("div",{staticClass:"touxiang"},[s("img",{attrs:{src:e.userPic,alt:""}})]),t._v(" "),s("div",{staticClass:"user"},[s("div",{staticClass:"userName"},[t._v(t._s(e.user)+":")]),t._v(" "),s("div",{staticClass:"con"},[t._v(t._s(e.content))])])])}),0),t._v(" "),t._m(2),t._v(" "),s("div",{staticClass:"lasttext text"},t._l(t.comments,function(e,n){return s("div",{key:n,staticClass:"com"},[s("div",{staticClass:"touxiang",on:{click:function(s){return t.deleteComment(e)}}},[s("img",{attrs:{src:e.userPic,alt:""}})]),t._v(" "),s("div",{staticClass:"user"},[s("div",{staticClass:"userName"},[t._v(t._s(e.user)+":")]),t._v(" "),s("div",{staticClass:"con"},[t._v(t._s(e.content))])])])}),0),t._v(" "),s("div",{staticClass:"content-box"},[s("el-button",{directives:[{name:"show",rawName:"v-show",value:t.isDel,expression:"isDel"}],attrs:{type:"text"},on:{click:t.openDel}},[s("div",{staticClass:"del"},[t._v("删除评论")])]),t._v(" "),s("el-button",{attrs:{type:"text"},on:{click:t.open}},[s("i",{staticClass:"el-icon-edit writeicon"}),t._v("写评论")])],1)])])},staticRenderFns:[function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticClass:"miao"},[e("a",{attrs:{href:"#reping"}},[this._v("-网易云热评墙-")]),this._v(" "),e("a",{attrs:{href:"#xinping"}},[this._v("-最新评论墙-")])])},function(){var t=this.$createElement,e=this._self._c||t;return e("a",{attrs:{id:"reping"}},[e("div",{staticClass:"comlei"},[this._v("网易云热评墙")])])},function(){var t=this.$createElement,e=this._self._c||t;return e("a",{attrs:{id:"xinping"}},[e("div",{staticClass:"comlei"},[this._v("最新评论墙")])])}]};var c=s("VU/8")(o,a,!1,function(t){s("qaAp")},null,null);e.default=c.exports},qaAp:function(t,e){}});
//# sourceMappingURL=2.dfbd1b920e3e94fea9c2.js.map