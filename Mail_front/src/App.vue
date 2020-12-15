<template>
  <main_window v-if="show1"></main_window>
  <sign_in v-if="show2"></sign_in>
  <sign_up v-if="show3"></sign_up>
</template>

<script>
import main_window from './components/main_window.vue';
import sign_in from "@/components/sign_in";
import sign_up from "@/components/sign_up";

export default {
  name: 'App',
  components: {
    main_window,
    sign_in,
    sign_up
  },
  methods:{
    myFunction(){
      window.location.href = "/components/sign_up.vue";
    },
  },
  computed:{

    show1(){
      return false;
    }, show2(){
      return false;
    },
    show3(){
      return true;
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
