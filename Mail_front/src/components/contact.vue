<template>
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <body>
  <div class="bar">
    <ul>
      <li><button @click="Search" class="zr">&#x219d; Search</button>
        <select v-model = "s">
          <option value="0" disabled>By</option>
          <option value="sender" >name</option>
        </select>
        <input v-model="searchInput" name="search" type="text">
      </li>
      <li><button @click="Sort" class="zr">&#x219d; Sort</button>
        <select v-model = "sort">
          <option value="0" disabled>By</option>
          <option value="sender" >name</option>
        </select>
      </li>
<!--      <li><button @click="filter" class="zr">&#x219d; Filter</button>-->
<!--        <select v-model = "f">-->
<!--          <option value="0" disabled>By</option>-->
<!--          <option value="sender" >Sender</option>-->
<!--          <option value="subject">Subject</option>-->
<!--        </select>-->
<!--      </li>-->
    </ul></div>
  <div id="list">
    <ul>
      <li v-for="(cont, index) in List" :key="index"><label><input type="checkbox" :id="cont" :value="cont" v-model="checkList">{{index+1}} {{ cont }}</label></li>
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

  </div>
  <div id="select">
    <span><button class="check" @click="selectAll">Select All</button></span>
    <span><button class="check" @click="deselectAll">Deselect All</button></span>
<!--    <span>{{checkList}}</span>-->
  </div>

  <div id="editButtons">
    <button @click="edit" class="op"><i class="material-icons">&#xe3c9;</i> <span>edit</span></button>
    <button @click="Delete" class="op"><i class="material-icons">&#xe872;</i> <span>delete</span></button>
    <button @click="view" class="op"><i class="material-icons">&#xe89d;</i> <span>view</span></button>
    <button @click="newC" class="op"><i class="material-icons">&#xe7fe;</i> <span>add</span></button>
  </div>
<!--  <div id="container">-->
<!--    <div id="pages">-->
<!--      &lt;!&ndash;<button @click="getFirstP" class="browse" id="firstP"><i class="material-icons">&#xe045;</i></button>&ndash;&gt;-->
<!--      <button @click="decreaseP" class="browse" id="leftB"><i class="material-icons">&#xe5cb;</i></button>-->
<!--      <p id="num">{{ pageNum }}</p>-->
<!--      <button @click="increaseP" class="browse" id="rightB"><i class="material-icons">&#xe5cc;</i></button>-->
<!--      &lt;!&ndash;<button @click="getLastP" class="browse" id="lastP"><i class="material-icons">&#xe044;</i></button>&ndash;&gt;-->
<!--    </div>-->
<!--  </div>-->
  </body>
</template>

<script>
import axios from 'axios'

export default {
  name: "contact",
  data(){
    return({
      showNewCont:true,
      s : '0',
      searchInput : '',
      sort : '0',
      f : '0',
      List:['one','two','three','four','five','six','seven','eight','nine','ten','11','12','13'],
      pageNum:1,
      checkList:[],
    })
  },
  async mounted(){
    await axios.get("http://localhost:8085/listCont").then(response => {
      //console.log('start');
      //console.log(this.List);
      this.List = response.data;
      //console.log('end');
      //console.log(this.List);
      // console.log(response.data);
      // console.log('this is contact response');
    });
  },
  async watch() {
    await axios.get("http://localhost:8085/listCont").then(response => {
      // console.log('start');
      // console.log(this.List);
      this.List = response.data;
      // console.log('end');
      // console.log(this.List);
      // console.log(response.data);
      // console.log('this is contact response');
    });
  },
  methods:{
    async Refresh(){
      await axios.get("http://localhost:8085/listCont").then(response => {
        console.log('start');
        console.log(this.List);
        this.List = response.data;
        console.log('end');
        console.log(this.List);
        console.log(response.data);
        console.log('this is contact response');
      });
    },
    newC(){
      this.$emit('new-cont');
      console.log('new contact');
    },
    edit(){
      if(this.checkList.length == 0){
        alert('select a contact to edit');
      }else if(this.checkList.length > 1){
        alert('only one contact must be selected to edit');
      }else {
        axios.get("http://localhost:8085/showCont",{
          params:{
            user:this.checkList[0],
          }
        }).then(response => {
          var temp;
          temp = response.data;
          console.log(temp);
          var str = "";
          for(var y in temp){
            console.log(temp[y]);
            str += temp[y] + ","
          }
          console.log(str);
          str = str.substr(0,str.length-1);
          var x = {
            'Cname':this.checkList[0],
            'address':str,
          }
          console.log(x);
          this.$emit('editC',x);
          console.log('edit event is emitted');
        });
      }
    },
    view() {
      if(this.checkList.length == 0){
        alert('select a contact to view');
      }else if(this.checkList.length > 1){
        alert('only one contact must be selected to view');
      }else {
        var temp;
        axios.get("http://localhost:8085/showCont",{
          params:{
            user:this.checkList[0],
          }
        }).then(response => {
          temp = response.data;
          console.log(temp);
          var str = "";
          for(var y in temp){
            console.log(temp[y]);
            str += temp[y] + ","
          }
          console.log(str);
          str = str.substr(0,str.length-1);
          var x = {
            'Cname':this.checkList[0],
            'address':str,
          }
          this.$emit('viewC',x);
          console.log('view event is emitted');
        });
      }
    },
    Delete(){
      if(this.checkList.length == 0){
        alert('select one or more contacts');
      }else {
        var x = "";
        for(var y in this.checkList){
          console.log(y);
          x += this.checkList[y] + ",";
        }
        console.log(this.checkList);
        x = x.substr(0,x.length-1);
        console.log('this is delete method');
        console.log(x);
        axios.get("http://localhost:8085/dltCont",{
          params:{
            names : x,
          }
        }).then(response => {
          this.List = response.data;
        });
      }
    },
    selectAll(){
      this.checkList = this.List;
    },
    deselectAll(){
      this.checkList = [];
    },
    setPageList(page){
      console.log(page);
      return true;
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
    Search(){
      axios.get("http://localhost:8085/searchCont",{
        params:{
          name:this.searchInput,
        }
      }).then(response => {
        this.List = response.data;
      });
    },
    Sort(){
      axios.get("http://localhost:8085/sortCont").then(response => {
        this.List = response.data;
      });
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
  grid-template-rows: 14% 60% 9% 7% 3% 1%;
  border: solid red 3px;
  border-radius: 7px;
  min-height: 93%;
  margin-top: 5px;
  overflow: hidden;
}
#list {
  grid-row: 2/3;
  overflow: auto;
}

#select{
  grid-row: 3/4;
}

/*#container {
  grid-row: 6/7;
}*/
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

.bar li .zr:focus{
  background-color: #000000;
  border-radius: 50px 20px;
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

.check {
  -moz-box-shadow: 3px 4px 0px 0px #8a2a21;
  -webkit-box-shadow: 3px 4px 0px 0px #8a2a21;
  box-shadow: 3px 4px 0px 0px #8a2a21;
  background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #c62d1f), color-stop(1, #f24437));
  background: -moz-linear-gradient(top, #c62d1f 5%, #f24437 100%);
  background: -webkit-linear-gradient(top, #c62d1f 5%, #f24437 100%);
  background: -o-linear-gradient(top, #c62d1f 5%, #f24437 100%);
  background: -ms-linear-gradient(top, #c62d1f 5%, #f24437 100%);
  background: linear-gradient(to bottom, #c62d1f 5%, #f24437 100%);
  filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#c62d1f', endColorstr='#f24437', GradientType=0);
  background-color: #c62d1f;
  -webkit-border-radius: 18px;
  -moz-border-radius: 18px;
  border-radius: 18px;
  border: 1px solid #d02718;
  display: inline-block;
  cursor: pointer;
  color: #ffffff;
  font-family: Arial;
  font-size: 17px;
  padding: 7px 25px;
  text-decoration: none;
  text-shadow: 0px 1px 0px #810e05;
  outline: none;
  border: none;
  margin-left: 10px;
}
.check:hover {
  background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #f24437), color-stop(1, #c62d1f));
  background:-moz-linear-gradient(top, #f24437 5%, #c62d1f 100%);
  background:-webkit-linear-gradient(top, #f24437 5%, #c62d1f 100%);
  background:-o-linear-gradient(top, #f24437 5%, #c62d1f 100%);
  background:-ms-linear-gradient(top, #f24437 5%, #c62d1f 100%);
  background:linear-gradient(to bottom, #f24437 5%, #c62d1f 100%);
  filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#f24437', endColorstr='#c62d1f',GradientType=0);
  background-color:#f24437;
}
.check:active {
  position:relative;
  top:1px;
}

::-webkit-scrollbar {
  width: 20px;
}
::-webkit-scrollbar-track {
  box-shadow: inset 0 0 5px grey;
  border-radius: 10px;
}
::-webkit-scrollbar-thumb {
  background: rgb(173, 2, 16);
  border-radius: 10px;
}
::-webkit-scrollbar-thumb:hover {
  background: #750202;
}

</style>