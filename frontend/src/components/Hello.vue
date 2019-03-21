<template>
<body class="bg">
  <div class="container">
    <div class="row">
      <div class="col-md-5 text-center">
        <form action method>
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
              @keydown.enter.prevent="addCategory"
              class="form-control input-sm chat-input"
              placeholder="password"
              required
            >
            <br>
            <br>
            <div class="wrapper">
              <span class="group-btn">
                <button
                  type="login"
                  href
                  class="btn btn-primary btn-md"
                  @mouseenter="validator()"
                  @click="startchecker()"
                >
                  Login
                  <i class="fa fa-sign-in"></i>
                </button>
              </span>
            </div>
          </div>
        </form>
      </div>
      <div class="col-md-7 text-center">
        <div class="desc">
          <br>
          <h4>Parky Touch Rugby</h4>
          <br>
          <h5 class="test">
            Il "Parky Touch Rugby" ed i suoi pionieri, la squadra dei "Bradirapidi", non smettono di destare
            entusiasmo e interesse di singoli e di istituzioni. [...] Quella del "Parky Touch Rugby" e dei suoi
            "Bradirapidi", ora guidati da un allenatore ex rugbista e arbitro federale, è un'esperienza senza
            precedenti e con prospettive di grande valore, anche nel campo della ricerca clinica. E soprattutto un
            esempio di come lo sport possa essere davvero inclusivo e abbattere tutte le barriere.
          </h5>
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
      checking: null,
      accesso: null,
      response: [],
      errors: [],
      service: {
        utente: "",
        password: ""
      },
      showResponse: false,
      retrievedUser: {},
      showRetrievedUser: false
    };
  },
  beforeRouteLeave(to, from, next) {
    if (this.accesso === "A" || this.accesso === "B" || this.accesso === "C") {
      next();
    } else {
      next("/");
      this.accesso = null;
    }
  },
  methods: {
    // Fetches posts when the component is created.
    startchecker() {
      if (this.checking === true) {
        window.location.href = "/#/callservice?livello=" + this.accesso;
      } else {
        window.location.href = "/#/";
      }
    },
    validator() {
      var params = new URLSearchParams();
      params.append("utente", this.service.utente);
      params.append("password", this.service.password);
      AXIOS.post(`/restlogin`, params)
        .then(response => {
          this.accesso = response.data;
          console.log(this.accesso + "This is answer from our checker");
          this.showResponse = true;
          this.checking = true;

          if (
            this.accesso == "A" ||
            this.accesso == "B" ||
            this.accesso == "C"
          ) {
            console.log("Valid level");
            this.checking = true;
          } else {
            this.checking = false;
            this.accesso = null;
          }
        })
        .catch(e => {
          this.errors.push(e);
        });
    }
  }
};
</script>

<style scoped>
input {
  width: 100%;
  margin-bottom: 10px;
  background: rgba(0, 0, 0, 0.3);
  border: none;
  outline: none;
  padding: 10px;
  font-size: 13px;
  color: #fff;
  text-shadow: 1px 1px 1px rgba(0, 0, 0, 0.3);
  border: 1px solid rgba(0, 0, 0, 0.3);
  border-radius: 4px;
  box-shadow: inset 0 -5px 45px rgba(100, 100, 100, 0.2),
    0 1px 1px rgba(255, 255, 255, 0.2);
  -webkit-transition: box-shadow 0.5s ease;
  -moz-transition: box-shadow 0.5s ease;
  -o-transition: box-shadow 0.5s ease;
  -ms-transition: box-shadow 0.5s ease;
  transition: box-shadow 0.5s ease;
}
@media (max-width: 640px) {
  body {
    font-size: 1.3rem;
  }
  h1 {
    font-size: 16px;
  }
  h5 {
    font-size: 12;
  }
}
@media (max-width: 767px) {
  .desc {
    margin-top: 50px;
  }
  h5 {
    font-size: 8px;
  }
  .desc {
    width: 250px;
    min-height: 0px !important;
    margin-top: 50px;
  }
  .form-login {
    width: 250px;
  }
  .container {
    margin-right: 10px;
    padding: 50px !important;
  }
}

@media (min-width: 641px) {
  body {
    font-size: 1.2rem;
  }

  h5 {
    font-size: 14px;
  }
}

@media (min-width: 960px) {
  body {
    font-size: 1.4rem;
  }
  h5 {
    font-size: 20px;
  }
}

@media (min-width: 1100px) {
  body {
    font-size: 1.6rem;
  }
  h5 {
    font-size: 20px;
  }
  .desc {
    margin-left: 40px;
  }
}

h5.test {
  word-wrap: break-word;
}
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
  background-image: url(../assets/hands.jpg);

  /* Full height */
  transform: scale(1.17);

  /* Center and scale the image nicely */
  background-position: top;
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
h5 {
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
.desc {
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
