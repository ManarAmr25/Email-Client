<template>
  <body>
  <sign_in v-if="show2" @submit="submit" @sign-in="signIn" @sign-up="signUp()"></sign_in>
  <sign_up v-if="show3" @submit="submit" @sign-in="signIn" @sign="setCompenent(2)"></sign_up>
  <home :userName="getUname" v-if="show4" @log-out="logOut"></home>
  </body>
</template>

<script>
import sign_in from "@/components/sign_in";
import sign_up from "@/components/sign_up";
import home from "@/components/home";
export default {
  name: 'App',
  components: {
    sign_in,
    sign_up,
    home,
  },
  data(){
    return({
      shown: 2,
      Uname:'',
      myString:'myName',
    })
  },
  methods:{
    setCompenent(num){
      if(num === 4){
        document.body.style.backgroundImage = "none";
      }
      this.shown = num;
      console.log("component set");
    },
    signIn(x){
      this.setCompenent(4);
      this.Uname = x.name;
      console.log("loaded name = "+x.name);
    },
    signUp(x){
      this.setCompenent(3);
      this.Uname = x;
    },
    logOut(){
      this.setCompenent(2);
    },
    submit(x){
      this.Uname = x.name;
      this.setCompenent(4);
      console.log(x);
      console.log("loaded name = "+x.name);
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
    getUname(){
      return this.Uname;
    }
  }
}
</script>

<style>
* {
  margin: 0;
  padding: 0;
}
body {
  background-image: url("./assets/logo-icon.png");
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: 50% 100%;
  background-position: right;
}
</style>