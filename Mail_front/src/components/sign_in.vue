<template>
  <body>
  <div class="signIn">
    <form>
      <legend class="title">Sign In</legend>
      <label class="l" for="email">Email address</label>
      <input v-model = "email" type="email" autofocus="autofocus" placeholder=" user@system.com" id="email"><br>
      <label class="l" for="password">Password</label>
      <input v-model = "password" type="password" placeholder=" enter password" id="password"><br>
      <label class="sp">Show password</label>
      <input type="checkbox" @click="showPassword"><br>
    </form>
    <div class="buttons">
      <button @click="goToSignUp" id="signUp">don't have an account yet?</button>
      <button @click="signIn">Sign in</button>
    </div>
  </div>
  </body>
</template>

<script>
import axios from "axios";
export default {
  name: "sign_in",
  data(){
    return({
      email:"",
      password:"",
    })
  },
  methods:{
    showPassword(){
      var x = document.getElementById("password");
      if (x.type === 'password'){
        x.type = "text";
      }else{
        x.type = "password";
      }
    },
    signIn(){
      if(this.email === ""){
        document.getElementById('email').style.backgroundColor = '#ffe6e6';
        alert('email missing');
        return;
      }else if(this.password === ""){
        document.getElementById('password').style.backgroundColor = '#ffe6e6';
        alert('password missing');
        return;
      }else {
        axios.get("http://localhost:8085/signIn", {
          params: {
            email:this.email,
            password:this.password,
          }
        }).then(response => {
          if(response.data == ''){
            alert('incorrect address or password');
          }else {
            console.log(response.data);
            var x = {
              'name':response.data,
            }
            this.$emit('sign-in',x);
            console.log("event emitted");
          }
        });
        /*var x = {'user':"sign in name"};
        this.$emit('sign-in',x);
        console.log("event emitted");*/
      }
    },
    goToSignUp(){
      this.$emit('sign-up');
      console.log('goToSignUp');
    }
  },
}
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  font-size: 17px;
}
body {
  background-image: url("../assets/logo-icon.png");
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: 50% 100%;
  background-position: right;
}
.signIn{
  margin: 250px;
  display: flex;
  flex-direction: column;
}
.signIn .l,.sp{
  background-color: #DD162D;
  display: inline-block;
  padding: 10px 10px;
  margin: 10px;
  font-size: 20px;
  color: #fff;
  width: 130px;
  text-align: center;
}
.sp{
  border-radius: 50px 20px;
  margin-right:20px;
}
input[type="checkbox"] {
  margin-right: 10px;
}
input {
  height: 25px;
  background-color: white;
  font-size: 15px;
  padding: 12px 20px;
  margin: 8px 0;
  box-sizing: border-box;
  border: 2px solid #DD162D;
  border-radius: 4px;
}
.buttons {
  display: flex;
  flex-direction: row;
  justify-content: left;
}
button{
  width: 90px;
  height: 45px;
  /*margin-left: 300px;*/
  margin-top: 20px;
  border: solid rgb(223, 18, 18) 2px;
  border-radius: 5px;
  background-color: rgb(223, 18, 18);
  color: white;
  font-size: 20px;
  outline: none;
  cursor:pointer;
}
button:hover {
  transition-duration: 0.4s;
  color: black;
  background-color: white;
}
#message {
  text-align: center;
  font-weight: 900;
}
.title{
  background-color: #BF2D3C;
  display: inline-block;
  padding: 10px 10px;
  margin-left:15%;
  margin-text-outline:-50px;
  font-size: 20px;
  color: #fff;
  width: 130px;
  text-align: center;
}
#signUp{
  background-color: transparent;
  border: none;
  color: #0e0101;
  width: 300px;
}

#signUp:hover{
  color: #DD162D;
  text-decoration: underline;
}

</style>