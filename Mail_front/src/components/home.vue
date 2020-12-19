<template>
  <body @new-cont="setNew(true)">
  <div id="container" @new-cont="setNew(true)">
    <div id="bar" @new-cont="setNew(true)">
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
        <li><button @click="setComponent(5)">&#x2712; Compose</button></li>
        <li><button @click="setComponent(6)">&#x2712; Contact</button></li>
      </ul>
    </div>
    <div class="c" id="contacts" v-if="show6" @new-cont="setNew(true)">
      <contact></contact>
    </div>
    <div class="c" id="compose" v-if="show5" @new-cont="setNew(true)">
      <compose ></compose>
    </div>
    <div class="c" id="email" v-if="show1" @new-cont="setNew(true)">
      <Email folder="getFolder"></Email>
    </div>
    <div class="c" id="new_cont" v-if="showNewCont" @new-cont="setNew(true)">
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
    })
  },
  methods:{
    setComponent(num){
      if(num === 1 || num === 2 || num === 3 || num === 4){
        this.shown = 1;
        this.folder=num;
      }else{
        this.shown = num;
      }
    },
    setNew(b){
      this.newCont = b;
    }
  },
  computed:{
    show1(){
      if(this.shown == 1){
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