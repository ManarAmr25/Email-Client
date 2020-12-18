<template>
  <body>
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
        <li><button @click="setComponent(5)">&#x2712; Compose</button></li>
        <li><button @click="setComponent(6)">&#x2712; Contact</button></li>
      </ul>
    </div>
    <div id="compose" v-if="show5">
      <compose ></compose>
    </div>
    <div id="email" v-if="show1">
      <Email folder="getFolder"></Email>
    </div>
  </div>
  </body>
</template>

<script>
import compose from "@/components/compose";
import Email from "@/components/Email";
export default {
  name: "home",
  components:{
    compose,
    Email,
  },
  data(){
    return({
      shown: 5,
      folder:1,
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
    getFolder(){
      return this.folder;
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
#compose,#email{
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