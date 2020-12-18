<template>
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <body>
  <div id="toContainer">
    <div class="comp" id="to">
      <label for="toInput">To</label>
      <input type="text" id="toInput" placeholder="enter one or more recievers separated by a single space">
    </div>
  </div>
  <div id="subjectContainer">
    <div class="comp" id="subject">
      <label for="subInput">Subject</label>
      <input type="text" id="subInput" placeholder="enter subjest">
    </div>
  </div>
  <div id="mailContainer">
    <div class="comp" id="mail">
      <label for="mailInput">Email</label>
      <textarea name="mail content" id="mailInput" cols="30" rows="10" placeholder="Go ahead.."></textarea>
    </div>
  </div>
  <div id="attachsContainer">
    <div class="comp" id="attachs">
      <label for="fileInput">Attachments</label>
      <input type="file" id="fileInput" multiple onchange="handleFiles(this.files)">
    </div>
  </div>
  <div id="submitContainer">
    <div class="comp" id="submit">
      <button id="b"><i id="a" class="material-icons">&#xe163;</i>Send</button>
    </div>
  </div>
  </body>
</template>

<script>
export default {
  name: "compose",
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
* {
  margin: 0;
  padding: 0;
  background: whitesmoke;
  font-family: Verdana, Geneva, Tahoma, sans-serif;
}

html {
  height: 100%;
}

body {
  border: solid red 3px;
  border-radius: 7px;
  min-height: 97%;
  margin-top: 5px;
  display: grid;
  grid-template-rows: 5% 5% 73% 7% 10%;
}

i{
  background: whitesmoke;
}

#toContainer {
  grid-row: 1/2;
}

#subjectContainer {
  grid-row: 2/3;
}

#mailContainer {
  grid-row: 3/4;
}

#attachsContainer {
  grid-row: 4/5;
}

#submitContainer {
  grid-row: 5/6;
}

label {
  box-sizing: border-box;
  width: 20%;
  text-align: center;
  padding: 6px;
  font-weight: 500;
}

label[for="toInput"],
label[for="subInput"],
#toInput,
#subInput {
  border-bottom: solid 1px black;
}

label:hover {
  transition-duration: 0.2s;
  color: rgb(201, 12, 12);
}

input {
  box-sizing: border-box;
  border: none;
  width: 93%;
  padding: 5px;
  height: 100%;
}

input:focus,
textarea:focus {
  outline: none;
  background: white;
}

.comp {
  display: flex;
  flex-direction: row;
  height: 100%;
  margin: auto;
  text-align: center;
}

textarea {
  box-sizing: border-box;
  padding: 5px;
  width: 93%;
  margin: 8px 0px;
  border: none;
  height: 150%;
}

#mail {
  height: 300px;
}

button {
  width: 70px;
  margin: auto 30px;
  border: solid rgb(223, 18, 18) 0.7px;
  border-radius: 2px;
  background-color: white;
  color: black;
  height: 50%;
}

button:hover {
  transition-duration: 0.3s;
  border-color: whitesmoke;
  border-bottom-color: red;
  color: red;
  cursor: pointer;
  outline: none;
}

button:active {
  transition-duration: 0.1s;
  border-color: whitesmoke;
  border-bottom-color: rgb(75, 0, 0);
  color: rgb(75, 0, 0);
  cursor: pointer;
  outline: none;
}

button:focus {
  outline: none;
}

#submit {
  display: flex;
  flex-direction: row-reverse;
}
</style>