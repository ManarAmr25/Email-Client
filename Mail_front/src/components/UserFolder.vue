<template>
  <body>
    <div class="super">
    <label class="T">Add Folder</label>
    <label for="add" class="l">Name</label>
    <input v-model = "add" type="text" id="add" name="add" placeholder="Enter Folder Name">
    <button @click="addF" >Add</button>
    <br>

    <label class="T">Rename Folder</label>
    <label for="old" class="l">Old Name</label>
    <input v-model = "old" type="text" id="old" name="old" placeholder="Enter Old Name"><br>
    <label for="newn" class="l">New Name</label>
    <input v-model = "newn" type="text" id="newn" name="newn" placeholder="Enter New Name">
    <button @click="rename">Rename</button>
    <br>

    <label class="T">Delete Folder</label>
    <label for="delete" class="l">Name</label>
    <input v-model = "deletef" type="text" id="delete" name="delete" placeholder="Enter Folder Name">
    <button @click="deleteF">Delete</button>
    <br><br><br><br><br>
    </div>
  </body>
</template>

<script>

//import axios from "axios";
export default {
  name: "UserFolder",
  data(){
    return({
      add:"",
      old:"",
      newn:"",
      deletef:"",
    })
  },
  mounted(){
   // axios.get("http://localhost:8085/listCont").then(response => {this.List = response.data; console.log(response.data)});
  },
  methods:{
    addF(){
      if(this.add === ''){
        alert("Enter folder name");
      }else {
        var x = {'namef' : this.add}
        this.$emit('addF', x);
        console.log('Add folder');
      }
    },
    rename(){
      if(this.old === '' || this.newn === ''){
        alert("Enter folder name");
      }else {
        var x = {
          'nameo' : this.old,
          'namen' : this.newn,
        }
        this.$emit('renameF', x);
        console.log('renamef folder');
      }
    },
    deleteF(){
      if(this.deletef === ''){
        alert("Enter folder name");
      }else {
        var x = {
          'named' : this.deletef,
        }
        this.$emit('deleteF', x);
        console.log('deletef folder');
      }
    },
  }
}
</script>

<style scoped>

.super{
  width: 100%;
  height: 100%;
  background-color:whitesmoke;
}

input[type=text]{
  width: 25%;
  padding: 12px 20px;
  margin: 8px 0;
  box-sizing: border-box;
  border: 2px solid #DD162D;
  border-radius: 4px;
}
input[type=text]:focus{
  background-color: white;
}

.l,.T{
  background-color: #DD162D;
  display: inline-block;
  padding: 10px 10px;
  margin: 10px;
  font-size: 20px;
  color: #fff;
  width: 130px;
  text-align: center;
}
.l{
  margin-left:10%;
}
.T{
  display:block;
  background-color: #0F0D0D;
  border-radius: 50px 20px;
  margin-right:20px;
}
Button{
  -moz-box-shadow: 3px 4px 0px 0px #960F27;
  -webkit-box-shadow: 3px 4px 0px 0px #C42326;
  box-shadow: 3px 4px 0px 0px #AA192C;
  background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #ED0B29), color-stop(1, #f24437));
  background:-moz-linear-gradient(top, #ED0B29 5%, #f24437 100%);
  background:-webkit-linear-gradient(top, #ED0B29 5%, #f24437 100%);
  background:-o-linear-gradient(top, #ED0B29 5%, #f24437 100%);
  background:-ms-linear-gradient(top, #ED0B29 5%, #f24437 100%);
  background:linear-gradient(to bottom, #ED0B29 5%, #f24437 100%);
  filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#c62d1f', endColorstr='#f24437',GradientType=0);
  background-color:#c62d1f;
  -webkit-border-radius:18px;
  -moz-border-radius:18px;
  border-radius:18px;
  border:1px solid #E82B1E;
  display:inline-block;
  cursor:pointer;
  color:#ffffff;
  font-family:Arial;
  font-size:17px;
  padding:7px 25px;
  text-decoration:none;
  text-shadow:0px 1px 0px #96150C;
  margin-left:20px;
}
button:hover {
  background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #f24437), color-stop(1, #c62d1f));
  background:-moz-linear-gradient(top, #f24437 5%, #c62d1f 100%);
  background:-webkit-linear-gradient(top, #f24437 5%, #c62d1f 100%);
  background:-o-linear-gradient(top, #f24437 5%, #c62d1f 100%);
  background:-ms-linear-gradient(top, #f24437 5%, #c62d1f 100%);
  background:linear-gradient(to bottom, #f24437 5%, #c62d1f 100%);
  filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#f24437', endColorstr='#c62d1f',GradientType=0);
  background-color:#f24437;
}
button:active {
  position:relative;
  top:1px;
}
</style>