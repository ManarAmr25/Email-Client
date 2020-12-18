<template>
  <body>
  <main_window v-if="show1" @submit="setCompenent(4)" @sign-in="setCompenent(4)"></main_window>
  <sign_in v-if="show2" @submit="setCompenent(4)" @sign-in="setCompenent(4)"></sign_in>
  <sign_up v-if="show3" @submit="setCompenent(4)" @sign-in="setCompenent(4)"></sign_up>
  <home v-if="show4" @submit="setCompenent(4)" @sign-in="setCompenent(4)"></home>
  </body>
</template>

<script>
import main_window from './components/main_window.vue';
import sign_in from "@/components/sign_in";
import sign_up from "@/components/sign_up";
import home from "@/components/home";
export default {
  name: 'App',
  components: {
    main_window,
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