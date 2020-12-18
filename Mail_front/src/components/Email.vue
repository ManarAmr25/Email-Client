<template>
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <div class="bar">
    <ul>
    <li><button class="zr">&#x219d; Search</button>
      <select v-model = "s">
        <option value="0" selected>Sender</option>
        <option value="1">Subject</option>
      </select>
      <input name="search" type="text">
    </li>
    <li><button class="zr">&#x219d; Sort</button>
      <select v-model = "sort">
        <option value="0" selected>Sender</option>
        <option value="1">Subject</option>
      </select>
    </li>
      <li><button class="zr">&#x219d; Filter</button>
        <select v-model = "f">
          <option value="0" selected>Sender</option>
          <option value="1">Subject</option>
        </select>
      </li>
    </ul></div>
  <div id="list">
    <ul>
      <li><span><input type="checkbox"></span> list element</li>
      <li><span><input type="checkbox"></span> list element</li>
      <li><span><input type="checkbox"></span> list element</li>
      <li><span><input type="checkbox"></span> list element</li>
      <li><span><input type="checkbox"></span> list element</li>
      <li><span><input type="checkbox"></span> list element</li>
      <li><span><input type="checkbox"></span> list element</li>
      <li><span><input type="checkbox"></span> list element</li>
      <li><span><input type="checkbox"></span> list element</li>
      <li><span><input type="checkbox"></span> list element</li>
      <li><span><input type="checkbox"></span> list element</li>
      <li><span><input type="checkbox"></span> list element</li>
      <li><span><input type="checkbox"></span> list element</li>
    </ul>
  </div>
  <div id="editButtons">
    <button class="op"><i class="material-icons" >&#xe3c9;</i> <span>edit</span></button>
    <button class="op"><i class="material-icons">&#xe872;</i> <span>delete</span></button>
    <button class="op"><i class="material-icons">&#xe151;</i> <span>view</span></button>
    <button class="op"><i class="material-icons">&#xe163;</i> <span>send</span></button>
  </div>
  <div id="container">
    <div id="pages">
      <button class="browse" id="firstP"><i class="material-icons">&#xe045;</i></button>
      <button class="browse" id="leftB"><i class="material-icons">&#xe5cb;</i></button>
      <p id="num">page</p>
      <button class="browse" id="rightB"><i class="material-icons">&#xe5cc;</i></button>
      <button class="browse" id="lastP"><i class="material-icons">&#xe044;</i></button>
    </div>
  </div>
</template>

<script>
export default {
  name: "Email",
  props:{
    folder:{
      required:true,
    },
  }
}
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  font-family: Verdana, Geneva, Tahoma, sans-serif;
  background-color: whitesmoke;
}

html {
  height: 100%;
}

body {
  display: grid;
  grid-template-rows: 20% 63% 7% 3% 6% 1%;
  border: solid red 3px;
  border-radius: 7px;
  min-height: 97%;
  margin-top: 5px;
}

#list {
  grid-row: 2/3;
}

#container {
  grid-row: 5/6;
}

li {
  margin: 10px;
  border-bottom: solid 0.5px black;
  list-style-type: none;
  height: 30px;
}

input[type="checkbox"] {
  margin-right: 5px;
}

#pages {
  box-sizing: border-box;
  padding: 10px;
  margin: auto;
  display: flex;
  flex-direction: row;
  /* margin-top: 10px; */
  justify-content: center;
}

#num {
  margin: 0 8px;
}

.browse {
  border: none;
  margin: 0 8px;
  cursor: pointer;
}

.browse:focus {
  outline: none;
}

.browse:hover,
.browse:active,
.browse:focus {
  background-color: rgb(248, 4, 4);
  outline: none;
  transition-duration: 0.4s;
}

#editButtons {
  margin: auto;
}

.op {
  text-align: center;
  border: solid 1px red;
  border-radius: 2px;
  box-sizing: border-box;
  padding: 5px;
}

.op:hover {
  transition-duration: 0.3s;
  border-color: whitesmoke;
  border-bottom-color: red;
  color: red;
  cursor: pointer;
  outline: none;
}

.op:active {
  transition-duration: 0.1s;
  border-color: whitesmoke;
  border-bottom-color: rgb(75, 0, 0);
  color: rgb(75, 0, 0);
  cursor: pointer;
  outline: none;
}

span {
  box-sizing: border-box;
  padding: 3px;
}

.bar{
  grid-row: 1/2;
}

.bar ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
 /* margin-left:20%;*/
  overflow: hidden;
  background-color: #f1f1f1;
}

.bar li {
  float: left;
  height: 100%;
}

.bar li .zr{
  outline: none;
  border: none;
  display: inline;
  text-align: center;
  padding: 14px 20px;
  text-decoration: none;
  color: black;
  font-size :x-large;
  font-family:monospace;
}

.bar li .zr:hover {
  border-radius: 50px 20px;
  background-color: #DD162D;
  color: white;
  cursor: pointer;
}

select{
  margin-bottom: 1em;
  padding: .25em;
  border: 0;
  border-bottom: 2px solid currentcolor;
  font-weight: bold;
  letter-spacing: .15em;
  border-radius: 0;
  outline: 0;
  border-bottom-color: red;
}

</style>