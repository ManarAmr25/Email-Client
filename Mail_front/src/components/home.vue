<template>
  <body >
  <div id="container">
    <div id="bar">
      <ul>
        <div class = "img">
          <img src="../assets/logo-icon.png" alt="System">
          <div>System.com</div>
        </div>
        <label>User name</label>
        <li><button @click="setComponent(1)">&#x2712; Sent</button></li>
        <li><button @click="setComponent(2)">&#x2712; Inbox</button></li>
        <li><button @click="setComponent(3)">&#x2712; Drafts</button></li>
        <li><button @click="setComponent(4)">&#x2712; Trash</button></li>
        <li><button @click="setViewMode('editable');setComponent(5)">&#x2712; Compose</button></li>
        <li><button @click="setComponent(6)">&#x2712; Contact</button></li>
      </ul>
    </div>
    <div class="c" id="contacts" v-if="show6"  >
      <contact @new-cont="newCont = true; console.log('event listened')"></contact>
    </div>
    <div class="c" id="compose" v-if="show5">
      <compose :mode="getViewMode"></compose>
    </div>
    <div class="c" id="email1" v-if="show1"> <!--sent : view-->
      <Email @view="setViewMode('readOnly');setComponent(5)" :folder = "getFolder" ></Email>
    </div>
    <div class="c" id="email2" v-if="show2"> <!--inbox : view, delete-->
      <Email @view="setViewMode('readOnly');setComponent(5)" :folder = "getFolder" ></Email>
    </div>
    <div class="c" id="email3" v-if="show3"> <!--draft : edit, delete-->
      <Email @edit="setViewMode('editable');setComponent(5)" :folder = "getFolder" ></Email>
    </div>
    <div class="c" id="email4" v-if="show4"> <!--trash : view, restore-->
      <Email @view="setViewMode('readOnly');setComponent(5)" :folder = "getFolder" ></Email>
    </div>
    <div class="c" id="new_cont" v-if="newCont">
      <new ></new>
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
  data(){
    return({
      shown: 1,
      folder:1,
      newCont:false,
      viewMode:'editable',
    })
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
</style>