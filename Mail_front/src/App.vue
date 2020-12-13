<template>
  <component v-bind:is="main_window"
</template>

<script>
import main_window from './components/main_window.vue'

export default {
  name: 'App',
  components: {
    main_window
  }
}
</script>>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
