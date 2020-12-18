../assets/logo-icon.png<template>
  <body>
    <ul>
      <div class = "img">
        <img src="../assets/logo-icon.png" alt="System">
        <div>System.com</div>
      </div>
      <label>User name</label>
      <li><a href="">&#x2712; Sent</a></li>
      <li><a href="">&#x2712; Inbox</a></li>
      <li><a href="">&#x2712; Drafts</a></li>
      <li><a href="">&#x2712; Trash</a></li>
      <li><a href="">&#x2712; Compose</a></li>
      <li><a href="">&#x2712; Contact</a></li>
    </ul>
  </body>
</template>

<script>
export default {
  name: "ver_bar"
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
  height :1000px;
  background-color: #f1f1f1;
}

li a ,label{
  display: block;
  color: black;
  padding: 10px 20px;
  text-decoration: none;
  font-size :x-large;
  font-family:monospace;
}

/* Change the link color on hover */
li a:hover,label{
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