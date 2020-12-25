<template>
  <body>
  <div class="super">
    <h1 class="contact">New Contact</h1>
    <label for="name" class="l">Name</label>
    <input v-model="contactName" type="text" id="name" placeholder="enter Name" ><br>
    <label for="email" class="l">E-mail</label>
    <input v-model="address" type="text" id="email" placeholder="enter one or more addresses separated by single comma" >
  </div>
  <button @click="add">Add</button>
  <button @click="close">close</button>
  </body>
</template>

<script>
import axios from 'axios'

export default {
  name: "new",
  data(){
    return({
      contactName:'',
      address:'',
    })
  },
  props:{
    mode:{
      type: String,
      required:true,
    },
    content:{

    },
  },
  mounted() {
    if(this.mode === 'readOnly'){
      this.disableInputField();
    }else if(this.mode === 'editable'){
      this.enableInputField();
    }
    this.contactName = this.content.Cname;
    this.address = this.content.address;
  },
  methods:{
    add(){
      axios.get("http://localhost:8085/addCont",{params:{
        'name':this.contactName,
          'adresses':this.address,
        }}).then(response => {
          if(response.data == 'true'){
            alert('contact added successfully');
          }else {
            alert('contact name already exists');
            this.disableInputField();
          }
      });
    },
    close(){
      this.$emit('close-window');
      console.log('close window');
    },
    disableInputField(){
      document.getElementById('name').disabled = true;
      document.getElementById('email').disabled = true;
    },
    enableInputField(){
      document.getElementById('name').disabled = false;
      document.getElementById('email').disabled = false;
    },
  }
}
</script>

<style scoped>

* {
  margin: 0;
  padding: 0;
  background: whitesmoke;
  font-family: Verdana, Geneva, Tahoma, sans-serif;
}
body {
  border: solid red 3px;
  border-radius: 7px;
}

.super{
  margin-left:110px;
  margin-top:50px;
}
input[type=text],input[type=password],input[type=date]{
  width: 40%;
  padding: 12px 20px;
  margin: 8px 0;
  box-sizing: border-box;
  border: 2px solid #DD162D;
  border-radius: 4px;
}

.super .l{
  background-color: #DD162D;
  display: inline-block;
  padding: 10px 10px;
  margin: 10px;
  font-size: 20px;
  color: #fff;
  width: 130px;
  text-align: center;
}

button {
  width: 90px;
  height: 30px ;
  margin-left: 500px;
  margin-top: 20px;
  margin-bottom: 20px;
  border: solid rgb(223, 18, 18) 0.7px;
  border-radius: 5px;
  background-color: white;
  color: black;
  display: block;
}
button:hover {
  transition-duration: 0.4s;
  color: white;
  background-color: rgb(223, 18, 18);
  cursor: pointer;
}
button:focus {
  outline: none;
}
button:active {
  transition-duration: 0.1s;
  background-color: rgb(138, 11, 11);
  border-color: rgb(138, 11, 11);
}
</style>