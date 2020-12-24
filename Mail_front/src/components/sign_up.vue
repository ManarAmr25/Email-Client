<template>
  <div class="super">
    <form>
      <legend class="title">Sign UP</legend>
      <label for="fname" class="l">First Name</label>
      <input v-model = "fname" type="text" id="fname" name="fname"><br>
      <label for="lname" class="l">Last Name</label>
      <input v-model = "lname" type="text" id="lname" name="lname"><br>
      <label for="address" class="l" >Address</label>
      <input v-model = "address" type="text" id="address" name="address" placeholder=" user@system.com"><br>
      <label class="l">password</label>
      <input v-model = "password" type="password" id="password" placeholder="cannot be less than 8 characters"><br>
      <label class="sp">Show password</label>
      <input type="checkbox" @click="showPassword"><br>
      <label for="birthday" class="l">Birthday date</label>
      <input type="date" id="birthday" name="trip-start" value="2000-01-01" min="1960-01-01" max="2010-12-31"><br>
      <label class="l">Gender</label>
      <select v-model = "gender">
        <option value="0" disabled>Select gender</option>
        <option value="1">Male</option>
        <option value="2">Female</option>
      </select><br>

    </form>
    <button @click="reset" id="reset">Reset</button>
  </div>
  <button @click="goToSignIn" id="signIn"> have an account ?</button>
  <button @click="submit" class="zr">Submit</button>
</template>

<script>
import axios from 'axios';
export default {
  name: "sign_up",
  data(){
    return({
      fname:"",
      lname:"",
      address:"",
      password:"",
      date:"2000-01-01",
      gender:"0",
    })
  },
  methods:{
    reset(){
      console.log('start reset');
      document.getElementById("birthday").setAttribute("value","defaultValue");
      this.fname = "";
      this.lname = "";
      this.address = "";
      this.date = "2000-01-01";
      this.gender = "0";
      this.password = "";
      console.log('end reset');
    },
    showPassword(){
      var x = document.getElementById("password");
      if (x.type === 'password'){
        x.type = "text";
      }else{
        x.type = "password";
      }
    },
    async submit(){
      if(this.fname === ""){
        document.getElementById('fname').style.backgroundColor = '#ffe6e6';
        alert('first name missing');
        return;
      }else if(this.lname === ""){
        document.getElementById('lname').style.backgroundColor = '#ffe6e6';
        alert('last name missing');
        return ;
      }else if(this.address === "") {
        document.getElementById('address').style.backgroundColor = '#ffe6e6';
        alert('address  missing');
        return;
      }else if(this.password.length < 8){
        document.getElementById('password').style.backgroundColor = '#ffe6e6';
        alert('too short password');
        return;
      }else if(this.gender === ""){
        document.getElementById('gender').style.backgroundColor = '#ffe6e6';
        alert('gender missing');
        return;
      }else {
        var user = new Map();
        //user['username'] = this.fname + ' ' + this.lname; //why??
        user['password'] = this.password;
        user['email'] = this.address;
        user['gender'] = this.gender; //number or string ??
        user['date'] = this.date; //check date format ??
        user['firstname'] = this.fname;
        user['lastname'] = this.lname;
        console.log(user);
        axios.post("http://localhost:8085/signUp",{
            user
        }).then(response => {
          if(response.data == ''){
            alert('address already exists');
          }else {
            var x = {
              'name':response.data,
            }
            this.$emit('submit',x);
            console.log(response.data);
            console.log("event emitted");
          }
        });
      }
    },
    goToSignIn(){
      this.$emit('sign');
      console.log('goToSignIn');
    }
  },
}
</script>

<style scoped>
body{
  background-image:url('../assets/logo-icon.png');
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-size:50% 100%;
  background-position:right;
}
.super{
  margin-left:110px;
  margin-top:50px;
}
input[type=text],input[type=password],input[type=date]{
  width: 25%;
  padding: 12px 20px;
  margin: 8px 0;
  box-sizing: border-box;
  border: 2px solid #DD162D;
  border-radius: 4px;
}
input[type=text]:focus,input[type=password]:focus{
  background-color: white;
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
.super .l,.sp{
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
.zr{
  display: inline-block;
  padding: 15px 25px;
  font-size: 24px;
  text-align: center;
  text-decoration: none;
  background-color: #DD162D;
  border: none;
  border-radius: 15px;
  box-shadow: 0 9px #999;
  margin-left: 100px;
  margin-top: 20px;
}
.zr:hover{background-color: #121412}

.zr:active {
  background-color: #DD162D;
  box-shadow: 0 5px #666;
  transform: translateY(4px);
}
#reset{
  box-shadow: 3px 4px 0px 0px #DD162D;
  background:linear-gradient(to bottom, #ededed 5%, #CE4A60 100%);
  background-color:#ededed;
  border-radius:15px;
  border:1px solid #d6bcd6;
  display:inline-block;
  font-size:17px;
  padding:7px 25px;
  margin-left:400px ;
  text-decoration:none;
  text-shadow:0px 1px 0px #e1e2ed;
}
#reset:hover{
  background:linear-gradient(to bottom, #ededed 5%, #2D2626 100%);
  background-color:black;
  outline: none;
}
.zr , #reset{
  font-family:Arial;
  color:#fff;
  outline: none;
  cursor:pointer;
}

#signIn{
  background-color: transparent;
  border: none;
  color: #0e0101;
  width: 300px;
  font-size : large;
  margin-left: 100px;
  border: none;
  outline: none;
}

#signIn:hover{
  color: #DD162D;
  text-decoration: underline;
}

</style>