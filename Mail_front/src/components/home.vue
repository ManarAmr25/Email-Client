<template>
  <body>
    <div id="container">
      <div id="bar">
        <ver_bar></ver_bar>
      </div>
      <div id="compose">
        <compose></compose>
      </div>
    </div>
  </body>
</template>

<script>

import ver_bar from "@/components/ver_bar";
import compose from "@/components/compose";

export default {
  name: "home",
  components:{
    ver_bar,
    compose,
  },
  data(){
    return({

    })
  },
  methods:{

  },
  computed:{

  },
}
</script>

<style scoped>
body{
  background: none;
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

#compose{
  /*grid-area: main;*/
  grid-column-start: 2;
  grid-column-end: 4;
  grid-row-start: 1;
  /*grid-row-end: 3;*/
}

</style>