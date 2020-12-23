<template>
  <body >
  <div id="container">
    <div id="bar">
      <ul>
        <div class = "img">
          <img src="../assets/logo-icon.png" alt="System">
          <div>System.com</div>
        </div>
        <label>{{ Uname }}</label>
        <li><button @click="setComponent(1)">&#x2712; Sent</button></li>
        <li><button @click="setComponent(2)">&#x2712; Inbox</button></li>
        <li><button @click="setComponent(3)">&#x2712; Drafts</button></li>
        <li><button @click="setComponent(4)">&#x2712; Trash</button></li>
        <li><button @click="setViewMode('editable');setComponent(5);clearEcontent();">&#x2712; Compose</button></li>
        <li><button @click="setComponent(6)">&#x2712; Contact</button></li>
        <button @click="logOut" class="logout">&#x276E;&#x276E; Log Out</button>
      </ul>
    </div>
    <div class="c" id="contacts" v-if="show6"  >
      <contact @new-cont="openNewCont(1);clearCcontent();" @editC="editNew" @viewC="viewNew"></contact>
    </div>
    <div class="c" id="compose" v-if="show5">
      <compose :mode="getViewMode" :content="email"></compose>
    </div>
    <div class="c" id="email1" v-if="show1"> <!--sent : view-->
      <Email @view="view" :folder = "getFolder" ></Email>
    </div>
    <div class="c" id="email2" v-if="show2"> <!--inbox : view, delete-->
      <Email @view="view" :folder = "getFolder" ></Email>
    </div>
    <div class="c" id="email3" v-if="show3"> <!--draft : edit, delete-->
      <Email @edit="edit" :folder = "getFolder" ></Email>
    </div>
    <div class="c" id="email4" v-if="show4"> <!--trash : view, restore-->
      <Email @view="view" :folder = "getFolder" ></Email>
    </div>
    <div class="c" id="new_cont" v-if="newCont">
      <new :content="contact" :mode="getViewMode2" @close-window="newCont = false;" ></new>
    </div>
  </div>
  </body>
</template>

<script>
import compose from "@/components/compose";
import Email from "@/components/Email";
import contact from "@/components/contact";
import New from "@/components/new";

export default {
  name: "home",
  components:{
    New,
    compose,
    Email,
    contact,
  },
  props:{
    userName:{
      required:true,
    },
  },
  data(){
    return({
      Uname:'',
      shown: 1,
      folder:1,
      newCont:false,
      viewMode:'editable',
      viewModeC:'editable',
      Econtent: {
        'from':'',
        'to':'',
        'subject':'',
        'body':'',
        'priority':'three'
      },
      Ccontent:{
        'Cname':'',
        'address':'',
      },
    })
  },
  mounted() {
    console.log(this.userName);
    this.Uname = this.userName;
    console.log("current = " + this.Uname);
  },
  methods:{
    setComponent(num, mode=''){
      if(num === 1 || num === 2 || num === 3 || num === 4){
        this.folder=num;
        this.newCont = false;
      }else if(num === 5){
        this.newCont = false;
        if(mode !== ''){
          this.setViewMode(mode);
        }
      }
      this.shown = num;
      console.log(this.shown);
    },
    setViewMode(mode){
      this.viewMode = mode;
      console.log(this.viewMode);
    },
    setViewMode2(mode){
      this.viewModeC = mode;
      console.log('set view mode 2 >>'+this.viewModeC);
    },
    openNewCont(v = 0){
      if(v !== 1) {
        this.setViewMode2('readOnly');
      }else if(v !== 2) {
        this.setViewMode2('editable');
      }
      this.newCont = true;
    },
    logOut(){
      this.$emit('log-out');
      console.log('logOut');
    },
    view(x){
      this.setViewMode('readOnly');
      this.setComponent(5);
      this.Econtent = x;
    },
    edit(x){
      this.setViewMode('editable');
      this.setComponent(5);
      this.Econtent = x;
      console.log(x);
    },
    viewNew(x){
      this.openNewCont(2);
      this.Ccontent = x;
    },
    editNew(x){
      this.openNewCont(1);
      this.Ccontent = x;
    },
    clearEcontent(){
      this.Econtent = {
        'from':'',
        'to':'',
        'subject':'',
        'body':'',
        'priority':'three'
      };
    },
    clearCcontent(){
      this.Ccontent = {
        'Cname':'',
        'address':'',
      };
    }
  },
  computed:{
    show1(){
      if(this.shown == 1){
        return true;
      }
      return false;
    },
    show2(){
      if(this.shown == 2){
        return true;
      }
      return false;
    },
    show3(){
      if(this.shown == 3){
        return true;
      }
      return false;
    },
    show4(){
      if(this.shown == 4){
        return true;
      }
      return false;
    },
    show5(){
      if(this.shown == 5) {
        return true;
      }
      return false;
    },
    show6(){
      if(this.shown == 6) {
        return true;
      }
      return false;
    },
    getFolder(){
      return this.folder;
    },
    showNewCont(){
      return this.newCont;
    },
    getViewMode(){
      return this.viewMode;
    },
    getViewMode2(){
      return this.viewModeC;
    },
    email(){
      /*return {
        'from':'hello world',
        'to':'my friend',
        'subject':'assignment',
        'body':'hello friend',
        'priority':'one'
      }*/
      return this.Econtent;
    },
    contact(){
      /*return{
        'Cname':'user',
        'address':'user1,user2,user3',
      }*/
      return this.Ccontent;
    }
  }
}
</script>

<style scoped>
body{
  background-image: none ;
  overflow: hidden;
  /*max-height: 800px;*/
}
#container{
  display: grid;
  grid-template-columns: 15% auto;
  grid-gap : 10px;
}
#bar{
  /*grid-area: menu;*/
  grid-column-start: 1;
  grid-column-end: 4;
  grid-row-start: 1;
  /*grid-row-end: 3;*/
}
.c{
  /*grid-area: main;*/
  grid-column-start: 2;
  grid-column-end: 4;
  grid-row-start: 1;
  /*grid-row-end: 3;*/
}

#email{
  margin: 10px;
}

body{
  margin : 0px;
  max-height: fit-content;
}

ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  width: 15%;
  height :720px;
  background-color: #f1f1f1;
}
li button,label{
  display: block;
  color: black;
  padding: 10px 20px;
  text-decoration: none;
  font-size :x-large;
  font-family:monospace;
  outline: none;
  border: none;
}
/* Change the link color on hover */
li button:hover,label{
  border-radius: 50px 20px;
  background-color: #DD162D;
  color: white;
}
li button:focus{
  border-radius: 50px 20px;
  background-color: #19181C;
  color: white;
}

img{
  width :120px;
  height:120px;
}
.img{
  text-align: center;
  font-weight: bold;
  font-family:monospace;
  font-size : large;
  text-shadow: 2px 2px 8px #FF0000;
  margin-bottom:20px;
}

.logout{
  box-shadow:inset 0px 1px 0px 0px #efdcfb;
  background:linear-gradient(to bottom, #dfbdfa 5%, #d61111 100%);
  background-color:#dfbdfa;
  border-radius:6px;
  border:1px solid #e63c3c;
  display:inline-block;
  cursor:pointer;
  color:#ffffff;
  font-family:Arial;
  font-size:15px;
  font-weight:bold;
  padding:6px 24px;
  text-decoration:none;
  text-shadow:0px 1px 0px #eb1729;
  margin-top:150px;
  margin-left:45px;
  border: none;
  outline: none;
}
.logout:hover {
  background: linear-gradient(to bottom, #d61111 5%, #dfbdfa 100%);
  background-color: #d61111;
}
.logout:active {
  position:relative;
  top:1px;
}
</style>