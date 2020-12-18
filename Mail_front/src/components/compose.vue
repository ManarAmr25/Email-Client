<template>
  <body>
  <div>
    <div class="comp" id="to">
      <label for="toInput">To</label>
      <input type="text" id="toInput" placeholder="enter one or more recievers separated by a single space">
    </div>
    <hr>
    <div class="comp" id="subject">
      <label for="subInput">Subject</label>
      <input type="text" id="subInput" placeholder="enter subjest">
    </div>
    <hr>
    <div class="comp" id="mail">
      <label for="mailInput">Email</label>
      <textarea name="mail content" id="mailInput" cols="30" rows="10" placeholder="Go ahead.."></textarea>
    </div>
    <hr>
    <div class="comp" id="attachs">
      <label for="fileInput">Attachments</label>
      <input type="file" id="fileInput" multiple onchange="handleFiles(this.files)">
    </div>
    <hr>
    <div class="comp" id="submit">
      <button>Send</button>
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

body {
  border: solid red 3px;
  border-radius: 7px;
}

label {
  width: 7%;
  text-align: center;
  padding: 6px;
  font-weight: 500;
}

input {
  border: none;
  width: 93%;
  padding: 5px;
}

input:focus,
textarea:focus {
  outline: none;
  background: white;
}

.comp {
  display: flex;
  flex-direction: row;
  height: 30px;
  text-align: center;
}

hr {
  height: 0.5px;
  border-width: 0;
  color: grey;
  background-color: grey;
}

textarea {
  padding: 4px;
  width: 93%;
  border: none;
}

#mail {
  height: 300px;
}

button {
  width: 70px;
  margin: 3px 10px;
  border: solid rgb(223, 18, 18) 0.7px;
  border-radius: 5px;
  background-color: white;
  color: black;
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

#submit {
  display: flex;
  flex-direction: row-reverse;
}
</style>