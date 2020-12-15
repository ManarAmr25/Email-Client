<template>
  <div class="signIn">
    <label for="email">Email address</label>
    <input type="email" autofocus="autofocus" placeholder=" user@system.com" id="email"><br>
    <label for="password">Password</label>
    <input type="password" placeholder=" enter password" id="password"><br>
    <div id="showPW">
      <input type="checkbox" @click="showPassword">
      <p>show password</p>
    </div>
    <div id="submitButton">
      <button @click="func()">Sign in</button>
    </div>
    <p id="message">dummy message</p>
  </div>
</template>

<script>
export default {
  name: "sign_in",
  methods:{
    showPassword(){
      var x = document.getElementById("password");
      if (x.type === 'password'){
        x.type = "text";
      }else{
        x.type = "password";
      }
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

label {
  text-align: left;
  font-size: 20px;
}

#showPW {
  display: flex;
  flex-direction: row;
}

input[type="checkbox"] {
  margin-right: 10px;
}

input {
  height: 25px;
  border: solid rgb(223, 18, 18) 2px;
  border-radius: 5px;
  background-color: white;
  font-size: 15px;
}

input:focus {
  background-color: rgb(247, 236, 236);
}

.signIn {
  width: 300px;
  margin: 250px;
  display: flex;
  flex-direction: column;
}

#submitButton {
  display: flex;
  justify-content: center;
}

button {
  width: 60%;
  height: 45px;
  margin-top: 20px;
  margin-bottom: 20px;
  border: solid rgb(223, 18, 18) 2px;
  border-radius: 5px;
  background-color: rgb(223, 18, 18);
  color: white;
  font-size: 20px;
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

</style>