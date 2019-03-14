<template>
<body class="bg">
  <div class="container">
    <div class="row">
      <div class="col-md-offset-5 col-md-5 text-center ">
        <form action method="">
          
          <div class="form-login">
            <br>
            <h4>Accesso riservato ai soci</h4>
            <br>

            <input
              name="utente"
              type=" text"
              v-model="service.utente"
              class="form-control input-sm"
              placeholder="username"
              required
            >
            <br>
            
            <br>
            
            <input
              name="password"
              type="password"
              v-model="service.password"
              class="form-control input-sm chat-input"
              placeholder="password"
              required
            >
            <br>
            <br>
            <div class="wrapper">
              <span class="group-btn">
                <button type="login" href class="btn btn-primary btn-md" @click="validator()">
                  Login
                  <i class="fa fa-sign-in"></i>
                </button>
              </span>
            </div>
          </div>
        </form>
      </div>
      <div class="col-md-6 text-center ">
        <div class="desc">
          <br>
          <h4>Descrizione</h4>
          <br>
          <h1 class="test">
            Dudley Wrangel Clarke (Johannesburg, 27 aprile 1899 – Londra, 7 maggio 1974)
            è stato un militare britannico,ijgiuygyguygygygygguygyggyyggygygygygygygygy
          </h1>
        </div>
      </div>
    </div>
  </div>
</body>
</template>


<script>
// import axios from 'axios'
import { AXIOS } from "./http-common";

export default {
  name: "service",

  data() {
    return {
      msg: "HowTo call REST-Services:",
      response: [],
      errors: [],
       service: {
          utente: '',
          password: ''
    },
        showResponse: false,
        retrievedUser: {},
        showRetrievedUser: false
  }
},
methods: {
      // Fetches posts when the component is created.
      validator () {
        var params = new URLSearchParams()
        params.append('utente', this.service.utente)
        params.append('password', this.service.password)
        AXIOS.post(`/restlogin`, params)
          .then(response => {
            // JSON responses are automatically parsed.
            this.response = response.data
            console.log(response.data)
            this.showResponse = true
          })
          .catch(e => {
            this.errors.push(e)
          })
      }
}
}

</script>

<style scoped>
@media (max-width: 640px) { 
body {font-size:1.3rem;} 
h1{font-size: 16px;}
h4{font-size: 18}
} 
@media (max-width: 767px) {
.desc{
margin-top:50px 
}
h1{font-size: 16px;}
h4{font-size: 18px}
.desc{width: 250px;min-height:0px!important;margin-top:50px }
.form-login{width: 250px;}
.container{margin-right:10px;padding: 50px!important }
}

@media (min-width: 641px) { 
body {font-size:1.2rem;} 
h1{font-size: 30px;}
h4{font-size: 18px}

}
 
@media (min-width:960px) { 
body {font-size:1.4rem;} 

} 

@media (min-width:1100px) { 
body {font-size:1.6rem;}
h4{font-size: 28}
.desc{ margin-left:40px}
} 

h1.test{ word-wrap:break-word;}
body {
  height: 100%;
}
.uploading-image {
  display: flex;
}
.resized {
  width: auto;
  height: 150px;
}
.bg {
  /* The image used */
  background-image: url(../assets/sfondo1.jpg);

  /* Full height */
  height: 100%;

  /* Center and scale the image nicely */
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
}

.container {
  padding: 110px;
}
::placeholder {
  /* Chrome, Firefox, Opera, Safari 10.1+ */
  color: #ffffff !important;
  opacity: 1; /* Firefox */
  font-size: 18px !important;
}
.form-login {
  background-color: rgba(0, 0, 0, 0.55);
  padding-top: 10px;
  padding-bottom: 20px;
  padding-left: 20px;
  padding-right: 20px;
  border-radius: 15px;
  border-color: #d2d2d2;
  border-width: 5px;
  color: white;
  box-shadow: 0 1px 0 #cfcfcf;
}
.form-control {
  background: transparent !important;
  color: white !important;
  font-size: 18px !important;
}
h1 {
  color: white !important;
}
h4 {
  border: 0 solid #fff;
  border-bottom-width: 1px;
  padding-bottom: 10px;
  text-align: center;
}

.form-control {
  border-radius: 10px;
}
.text-white {
  color: white !important;
}

.wrapper {
  text-align: center;
}


.a {
  font-weight: bold;
  color: #2c3e50;
}
.desc{
  background-color: rgba(0, 0, 0, 0.55);
  padding-top: 10px;
  padding-bottom: 20px;
  padding-left: 20px;
  padding-right: 20px;
  border-radius: 15px;
  border-color: #d2d2d2;
  border-width: 5px;
  color: white;
  box-shadow: 0 1px 0 #cfcfcf;
  min-height: 450px;
  
}
</style>
