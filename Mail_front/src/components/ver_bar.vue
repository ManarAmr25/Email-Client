<template>
  <body>
  <ul>
    <div class = "img">
      <img src="../assets/logo-icon.png" alt="System">
      <div>System.com</div>
    </div>
    <label>User name</label>
    <li><button @click="setComponent(6)">&#x2712; logout</button></li>
    <li><button @click="setComponent(1)">&#x2712; Sent</button></li>
    <li><button @click="setComponent(2)">&#x2712; Inbox</button></li>
    <li><button @click="setComponent(3)">&#x2712; Drafts</button></li>
    <li><button @click="setComponent(4)">&#x2712; Trash</button></li>
    <li><button @click="setComponent(5)">&#x2712; Compose</button></li>
    <li><button @click="setComponent(6)">&#x2712; Contact</button></li>

    <!--<li><button class="log">Log Out</button></li>
    <button class="logout">&#x276E;&#x276E; Log Out</button>-->
  </ul>
  </body>
</template>

<script>
export default {
  name: "ver_bar",
  data(){
    return({
      shown: 2,
    })
  },
  methods:{ds:{
    myFunction(){
      window.location.href = "/components/sign_up.vue";
    },
    setCompenent(num){
      this.shown = num;
      console.log("component set");
      /*if(num === 5){

      }*/
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
    show2(){
      if(this.shown == 2){
        return true;
      }
      return false;
    },
    show3(){
      if(this.shown == 3) {
        return true;
      }
      return false;
    },
    show4(){
      if(this.shown == 4) {
        return true;
      }
      return false;
    },
  }
}
</script>

<style scoped>
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