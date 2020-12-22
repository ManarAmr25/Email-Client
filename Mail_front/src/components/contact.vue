<template>
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <body>
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
      <li v-for="(mail, index) in List" :key="index"><label><input type="checkbox" :id="mail" :value="mail" v-model="checkList">{{index+1}} {{ mail }}</label></li>
      <!--<li><span><input id="one" value="one" type="checkbox" v-model="checkList" ></span> <label for="one">1 list element</label></li>
      <li><span><input id="two" value="two" type="checkbox" v-model="checkList" ></span> <label for="two">2 list element</label></li>
      <li><span><input id="three" value="three" type="checkbox" v-model="checkList" ></span> <label for="three">3 list element</label></li>
      <li><span><input id="four" value="four" type="checkbox" v-model="checkList" ></span> <label for="four">4 list element</label></li>
      <li><span><input id="five" value="five" type="checkbox" v-model="checkList" ></span> <label for="five">5 list element</label></li>
      <li><span><input id="six" value="six" type="checkbox" v-model="checkList" ></span> <label for="six">6 list element</label></li>
      <li><span><input id="seven" value="seven" type="checkbox" v-model="checkList" ></span> <label for="seven">7 list element</label></li>
      <li><span><input id="eight" value="eight" type="checkbox" v-model="checkList" ></span> <label for="eight">8 list element</label></li>
      <li><span><input id="nine" value="nine" type="checkbox" v-model="checkList" ></span> <label for="nine">9 list element</label></li>
      <li><span><input id="ten" value="ten" type="checkbox" v-model="checkList" ></span><label for="ten">10 list element</label></li>-->
    </ul>
    <span><button class="check" @click="selectAll">all</button></span>
    <span><button class="check" @click="deselectAll">remove</button></span>
    <span>Checked: {{ checkList }}</span>
  </div>
  <div id="editButtons">
    <button class="op"><i class="material-icons">&#xe3c9;</i> <span>edit</span></button>
    <button class="op"><i class="material-icons">&#xe872;</i> <span>delete</span></button>
    <button class="op"><i class="material-icons">&#xe89d;</i> <span>view</span></button>
    <button @click="newC" class="op"><i class="material-icons">&#xe7fe;</i> <span>add</span></button>
  </div>
  <div id="container">
    <div id="pages">
      <button @click="getFirstP" class="browse" id="firstP"><i class="material-icons">&#xe045;</i></button>
      <button @click="decreaseP" class="browse" id="leftB"><i class="material-icons">&#xe5cb;</i></button>
      <p id="num">{{ pageNum }}</p>
      <button @click="increaseP" class="browse" id="rightB"><i class="material-icons">&#xe5cc;</i></button>
      <button @click="getLastP" class="browse" id="lastP"><i class="material-icons">&#xe044;</i></button>
    </div>
  </div>
  </body>
</template>

<script>
export default {
  name: "contact",
  data(){
    return({
      showNewCont:true,
      s : '0',
      sort : '0',
      f : '0',
      List:['one','two','three','four','five','six','seven','eight','nine','ten'],
      pageNum:1,
      checkList:[],
    })
  },
  methods:{
    newC(){
     this.$emit('new-cont');
     console.log('new contact');
    },
    edit(){

    },
    view() {
    },
    delete(){

    },
    selectAll(){
      this.checkList = this.List;
    },
    deselectAll(){
      this.checkList = [];
    },
    setPageList(page){
      console.log(page);
      //send a get request to backend to get a new list of emails
      /*axios.get("http://localhost:8085/",{
        params:

      }).then(response => {return response.data;});*/
      //return false if there is no such page or set list and return true
      return true;
    },
    getFirstP(){

    },
    decreaseP(){
      if(this.setPageList()) {
        this.pageNum--;
      }
    },
    increaseP(){
      if(this.setPageList()) {
        this.pageNum++;
      }
    },
    getLastP(){

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
  grid-template-rows: 14% 15% 54% 7% 3% 6% 1%;
  border: solid red 3px;
  border-radius: 7px;
  min-height: 97%;
  margin-top: 5px;
}

#list {
  grid-row: 2/4;
}

#container {
  grid-row: 6/7;
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
  background: whitesmoke;
  display: flex;
  flex-direction: row ;
  justify-content: center;
}

.op {
  text-align: center;
  border: solid 1px red;
  border-radius: 2px;
  box-sizing: border-box;
  padding: 5px;
  margin: 0 5px;
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
  border-bottom: solid 1px red;
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
  border: none;
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

input[type=text]{
  width: 200px;
  padding: 12px 20px;
  margin: 8px 8px;
  box-sizing: border-box;
  border: 2px solid #DD162D;
  border-radius: 4px;
}
input[type=text]:focus{
  background-color: white;
}

</style>