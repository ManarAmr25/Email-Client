<template>
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <body>
  <div class="bar">
    <ul>
      <li><button @click="Search" class="zr">&#x219d; Search</button>
        <select v-model = "s">
          <option value="0" disabled>By</option>
          <option value="Sender" >Sender</option>
          <option value="Subject">Subject</option>
          <option value="Body" >Body</option>
          <option value="Date">Date</option>
          <option value="Receiver" >Receiver</option>
          <option value="Importance" >Importance</option>
        </select>
        <input v-model="searchInput" name="search" type="text" placeholder="enter search key">
      </li>
      <li><button @click="Sort" class="zr">&#x219d; Sort</button>
        <select v-model = "sort">
          <option value="0" disabled>By</option>
          <option value="Sender" >Sender</option>
          <option value="Subject">Subject</option>
          <option value="Body" >Body</option>
          <option value="Date">Date</option>
          <option value="Receiver" >Receiver</option>
          <option value="Importance" >Importance</option>
        </select>
      </li>
      <li><button @click="filter" class="zr">&#x219d; Filter</button>
        <select v-model = "f">
          <option value="0" disabled>By</option>
          <option value="sender" >Sender</option>
          <option value="subject">Subject</option>
        </select>
        <input v-model="filterInput" name="search" type="text" placeholder="enter name of destination folder">
      </li>
    </ul></div>
  <div id="list">
    <ul>
      <li class="elist" v-for="(mail, index) in List" :key="index"><label><input type="checkbox" :id="mail" :value="index" v-model="checkList">{{index+1}} {{ mail }}</label></li>
    </ul>
    <span><button class="check" @click="selectAll">Select All</button></span>
    <span><button class="check" @click="deselectAll">Deselect All</button></span>
    <span><button class="check" @click="refreshList">refresh</button></span>
  </div>
  <div id="editButtons">
    <button @click="edit" v-if="showEdit" id="e" class="op"><i class="material-icons" >&#xe3c9;</i> <span>edit</span></button>
    <button @click="dlt"  v-if="showDlt" id="d" class="op"><i class="material-icons">&#xe872;</i> <span>delete</span></button>
    <button @click="view" v-if="showView" id="v" class="op"><i class="material-icons">&#xe417;</i> <span>view</span></button>
    <button @click="copy" v-if="showCM" id="c" class="op"><i class="material-icons">&#xe151;</i> <span>copy</span></button>
  </div>
  <div id="container">
    <div id="pages">
      <button @click="decreaseP" class="browse" id="leftB"><i class="material-icons">&#xe5cb;</i></button>
      <p id="num">{{ pageNum }}</p>
      <button @click="increaseP" class="browse" id="rightB"><i class="material-icons">&#xe5cc;</i></button>
    </div>
  </div>
  </body>
</template>

<script>
import axios from 'axios';
export default {
  name: "Email",
  props:{
    folder:{
      type: String,
      required:true,
    },
  },
  data(){
    return({
      s : '0',
      searchInput : '',
      sort : '0',
      f : '0',
      filterInput:'',
      List:['one','two','three','four','five','six','seven','eight','nine','ten'],
      /*list:[],*/
      pageNum:1,
      checkList:[],
    })
  },
  created(){
    console.log(this.folder);
  },
  async mounted(foldername){
    var f;
    if(this.folder === undefined){
      f = foldername;
      this.pageNum = 1;
    }else {
      f = this.folder;
    }
    console.log("mounted " + this.folder);
    console.log("f" + f);
    await axios.get("http://localhost:8085/listMails",{
      params:{
        foldername:f,
        page:this.pageNum,
      }
    }).then(response => {
      this.List = response.data;
      console.log(this.List);
    });
    console.log('after request');
    console.log(this.List);
  },
  async watch(){
      console.log("update " + this.folder);
      await axios.get("http://localhost:8085/listMails", {
        params: {
          foldername: this.folder,
          page: this.pageNum,
        }
      }).then(response => {
        this.List = response.data;
        console.log(this.List);
      });

  },
  methods:{
    selectAll(){
      this.checkList = [];
      for(var i = 0; i < this.List.length; i++){
        this.checkList.push(i);
      }
    },
    deselectAll(){
      this.checkList = [];
    },
    async refreshList(){
      this.s = '0';
      this.sort = '0';
      this.f = '0';
      this.searchInput = '';
      this.filterInput = '';
      await axios.get("http://localhost:8085/listMails",{
        params:{
          foldername:this.folder,
          page:this.pageNum,
        }
      }).then(response => {
        this.List = response.data;
        console.log('on refresh');
        console.log(this.List);
      });
    },
    edit(){//view an email in editable mode, only one email should be selected
      if(this.checkList.length == 0){
        alert('select an email to edit');
      }else if(this.checkList.length > 1){
        alert('only one email must be selected to edit');
      }else {
        axios.get("http://localhost:8085/openMail",{
          params:{
            index:this.checkList[0],
          }
        }).then(response => {
          console.log(response.data);
          var x = {
            'to':response.data['to'],
            'from':response.data['from'],
            'subject':response.data['subject'],
            'body':response.data['body'],
            'priority':response.data['key'],
          }
        this.$emit('edit',x);
        console.log('edit event is emitted');
        });
      }
    },
    dlt(){
      if(this.checkList.length == 0){
        alert('select one or more emails');
      }else {
        var str = "";
        for (var y in this.checkList){
          str += y + ",";
        }
        str = str.substr(0,str.length-1);
        axios.get("http://localhost:8085/dltMails",{
          params:{
            index:str,
          }
        }).then(response => {
          console.log(response.data);
          this.List = response.data;
          this.pageNum = 1;
          this.checkList = [];
        });
      }
    },
    view(){ //view an email in read only mode, only one email should be selected
      if(this.checkList.length == 0){
        alert('select an email to view');
      }else if(this.checkList.length > 1){
        alert('only one email must be selected to view');
      }else {
        //send a request to the backend to set which email to be read
        axios.get("http://localhost:8085/openMail",{
          params:{
            index:this.checkList[0],
          }
        }).then(response => {
          console.log(response.data);
          var x = {
            'to':response.data['to'],
            'from':response.data['from'],
            'subject':response.data['subject'],
            'body':response.data['body'],
            'priority':response.data['key'],
          }
          this.$emit('view',x);
          console.log('view event is emitted');
        })

      }
    },
    copy(){
      var dest = prompt("enter destination folder");
      if(dest != null){
        var str = "";
        for (var y in this.checkList){
          str += y + ",";
        }
        str = str.substr(0,str.length-1);
        axios.get("http://localhost:8085/copyMails",{
          params:{
            index:str,
            des:dest,
          }
        }).then(response => {
          console.log('copy response');
          console.log(response.data);
          if(response.data != ''){
            var x = {
              namef:response.data,
            }
            this.$emit('crt-folder',x);
          }
          this.checkList = [];
        });
      }
    },
    move(){

    },
    async setPageList(page){
      console.log(page);
      //send a get request to backend to get a new list of emails
      await axios.get("http://localhost:8085/listMails",{
        params:{
          foldername:this.folder,
          page:this.pageNum+1,
        }
      }).then(response => {
        if(response.data.length != 0){
          this.List = response.data;
          console.log(this.List);
        }

      });
      //return false if there is no such page or set list and return true
      return true;
    },
    async decreaseP(){
      if(this.pageNum == 1){
        return;
      }
      await axios.get("http://localhost:8085/listMails",{
        params:{
          foldername:this.folder,
          page:this.pageNum-1,
        }
      }).then(response => {
        if(response.data.length != 0){
          this.List = response.data;
          console.log(this.List);
          this.pageNum--;
        }
      });
    },
    async increaseP(){
      //send a get request to backend to get a new list of emails
      await axios.get("http://localhost:8085/listMails",{
        params:{
          foldername:this.folder,
          page:this.pageNum+1,
        }
      }).then(response => {
        if(response.data.length != 0){
          this.List = response.data;
          console.log(this.List);
          this.pageNum++;
        }
      });
    },
    Search(){
      if (this.s == 0){
          alert('select search category');
      }else {
        if(this.s === 'Date'){
          if(!this.searchInput.match("[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|(1|2)[0-9]|3[0-1])")){
            alert('Invalid date format. enter date as yyyy-mm-dd');
            return ;
          }
        }
        axios.get("http://localhost:8085/searchMails",{
          params:{
            type:this.s,
            key:this.searchInput,
          }
        }).then(response => {
          console.log("search response");
          console.log(response.data);
          this.List = response.data;
          this.pageNum = 1;
        })
      }
    },
    Sort(){
      if (this.sort == 0){
        alert('select sort category');
      }else {
        axios.get("http://localhost:8085/sortMails",{
          params:{
            type:this.sort,
          }
        }).then(response => {
          console.log("sort response");
          console.log(response.data);
          this.List = response.data;
          this.pageNum = 1;
        })
      }
    },
    filter(){
      if(this.f == 0){
        alert('enter filter category');
      }else if(this.filterInput == ''){
        alert('enter destination folder');
      }else if(this.checkList.length == 0){
        alert('select an email to filter');
      }else if(this.checkList.length > 1){
        alert('only one email must be selected to filter');
      }else{
        axios.get("http://localhost:8085/filterMails",{
          params:{
            index:this.checkList[0],
            mode:this.f,
            dest:this.filterInput,
          }
        }).then(response => {
          console.log('response');
          console.log(response.data);
          if(response.data != ''){
            var x = {
              namef:response.data,
            }
            this.$emit('crt-folder',x);
          }
        })
      }
    },
  },
  computed:{
    showEdit(){//1>sent ,2> inbox, 3>draft, 4> trash
      if(this.folder == 'draft'){
        return true;
      }
      return false;
    },
    showDlt(){
      // if(this.folder == 'trash'){
      //   return false;
      // }
      return true;
    },
    showView(){
      if(this.folder == 'draft'){
        return false;
      }
      return true;
    },
    showCM(){
      if(this.folder != 'draft'){
        return true;
      }
      return false;
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
  grid-template-rows: 14% 71% 7% 1% 6% 1%;
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

#pages button:active{
  color: red;
  transition-duration: 0s;
}

#pages button i{
  font-size: 30px;
}

#pages p{
  font-size: large;
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

.bar li .zr:focus{
  background-color: #000000;
  border-radius: 50px 20px;
  color: white;
  cursor: pointer;
}

.check{
  cursor: pointer;
  border: 2px solid black;
  outline: none;
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

.elist{
  overflow-x: auto;
}

</style>