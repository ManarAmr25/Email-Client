<temelate>
<body>
<div class="buttons">
  <button>Sign up</button>
  <button>Sign in</button>
</div>
</body>
</temelate>

<script>
export default {
  name: "main_window"
}
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
}
body {
  background-image: url("../assets/logo-icon.png");
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: 50% 100%;
  background-position: right;
}
button {
  width: 100%;
  height: 50px;
  margin-top: 20px;
  margin-bottom: 20px;
  border: solid rgb(223, 18, 18) 2px;
  border-radius: 5px;
  background-color: white;
  font-size: 20px;
}
button:hover {
  transition-duration: 0.4s;
  color: white;
  background-color: rgb(223, 18, 18);
}
.buttons {
  width: 150px;
  margin: 250px;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}
</style>