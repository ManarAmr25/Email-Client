<template>
  <body>
  <sign_in v-if="show2" @submit="setCompenent(4);" @sign-in="setCompenent(4)" @sign-up="setCompenent(3)"></sign_in>
  <sign_up v-if="show3" @submit="setCompenent(4);" @sign-in="setCompenent(4)"></sign_up>
  <home v-if="show4" @log-out="setCompenent(2);"></home>
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
    })
  },
  methods:{
    myFunction(){
      window.location.href = "/components/sign_up.vue";
    },
    setCompenent(num){
      if(num === 4){
        document.body.style.backgroundImage = "none";
      }
      this.shown = num;
      console.log("component set");
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