<template>
<div class="servicemy">
  <div class="row">
    <div class="col-md-11 p-0">
      <b-nav id="nav" class="flex-nav">
        <b-dropdown offset="25" text="Modifica Account" class="m-2 ddown-offset">
          <b-dropdown-item
            onclick="document.getElementById('id01').style.display='block'"
            style="width:auto;"
            @click="closeAll()"
            v-if="accesso === 'A' || accesso === 'B'"
          >Registra nuovo socio</b-dropdown-item>
          <b-dropdown-item
            onclick="document.getElementById('id02').style.display='block'"
            style="width:auto;"
            @click="closeAll()"
          >Modifica password</b-dropdown-item>
          <b-dropdown-item
            onclick="document.getElementById('id03').style.display='block'"
            style="width:auto;"
            @click="closeAll()"
            v-if="accesso === 'A' || accesso === 'B'"
          >Aggiorna dati socio</b-dropdown-item>
          <b-dropdown-item
            onclick="document.getElementById('id04').style.display='block'"
            style="width:auto;"
            @click="closeAll()"
          >Visualizza lista dei soci</b-dropdown-item>
        </b-dropdown>

        <b-dropdown offset="25" text="Gestione bilancio" class="m-2 button ddown-offset">
          <b-dropdown-item
            onclick="document.getElementById('id05').style.display='block'"
            style="width:auto;"
            @click="closeAll()"
          >Visualizza bilancio</b-dropdown-item>
          <b-dropdown-item
            onclick="document.getElementById('id08').style.display='block'"
            style="width:auto;"
            @click="closeAll()"
            v-if="accesso === 'A' || accesso === 'B'"
          >Inserisci voce in bilancio</b-dropdown-item>
          <b-dropdown-item
            onclick="document.getElementById('id07').style.display='block'"
            style="width:auto;"
            @click="closeAll()"
            v-if="accesso === 'A' || accesso === 'B'"
          >Rimuovi voce di bilancio</b-dropdown-item>
        </b-dropdown>

        <b-dropdown
          offset="25"
          text="Area amministratore"
          class="m-2 ddown-offset fake"
          @shown="openAdmin()"
          v-if="(accesso === 'A' || accesso === 'B')"
        ></b-dropdown>

        <b-dropdown
          offset="25"
          text="Visualizza file log"
          class="m-2 ddown-offset fake"
          @click="openLog()"
          v-if="accesso === 'A' || accesso === 'B'"
        ></b-dropdown>
      </b-nav>
    </div>
    <div class="col-md-1 p-0">
      <button
        offset="25"
        text="Logout"
        variant="btn-block"
        class="logout btn-block"
        @click="logout()"
      >Logout</button>
    </div>
  </div>

  <div v-if="adminTrue">
    <form action>
      <div class="container">
        <span @click="closeAdmin()" class="close" title="Close Modal">&times;</span>
        <h1>Area Amministratore</h1>
        <hr>
        
        <div class="row">
          <div class="col-md-6">
            <label for="nome">
              <b>Durata sessione (in secondi)</b>
            </label>
            <br>
            <input
              type="number"
              class="testo anagrafica"
              placeholder="Durata sessione"
              name="durata"
            >
          </div>
          <div class="col-md-6">
            <label for="cognome">
              <b>Aggiungi ruolo</b>
            </label>
            <br>
            <input
              type="text"
              class="testo anagrafica"
              placeholder="Aggiungi ruolo"
              name="cognome"
            >
          </div>
        </div>

        <label for="Ruoli">
          <b>Scegli le funzioni che vedrà attive il nuovo ruolo</b>
        </label>
        <div class="funkyradio row">
          <div class="funkyradio-danger col-md-3">
            <input type="checkbox" value="amministratore" name="checkbox">
            <label for="checkbox1">Visualizzazione</label>
          </div>
          <div class="funkyradio-danger col-md-2">
            <input type="checkbox" value="presidente" name="checkbox">
            <label for="checkbox2">Admin</label>
          </div>
          <div class="funkyradio-danger col-md-2">
            <input type="checkbox" value="tesoriere" name="checkbox">
            <label for="checkbox3">Bilancio</label>
          </div>
          <div class="funkyradio-danger col-md-2">
            <input type="checkbox" value="consigliere" name="checkbox">
            <label for="checkbox4">Log</label>
          </div>
          <div class="funkyradio-danger col-md-2">
            <input type="checkbox" name="checkbox" disabled="true" checked>
            <label for="checkbox5">Base</label>
          </div>
        </div>
        <br>

        <label for="indirizzo">
          <b>Inserisci Logo</b>
        </label>
        <div style="width:100%">
          <input
            type="Upload"
            class="ind"
            placeholder="Inserisci logo"
            name="logo"
          >
      
        </div>

        <hr>
        <label>
          <b>Scrivi un avviso in bacheca</b>
        </label>
        <br>
        <textarea name="message" rows="2" class="form-control md-textarea"></textarea>
      </div>
    </form>
  </div>
  <div class="bg" v-else>

  </div>

  <body>
    <div id="id01" class="modal">
      <RegistraUtente/>
    </div>
    <div id="id02" class="modal">
      <CambiaPassword/>
    </div>
    <div id="id03" class="modal">
      <AggiornaUtente/>
    </div>
    <div id="id04" class="modal">
      <ListaSoci/>
    </div>
    <div id="id05" class="modal">
      <VisualizzaBilancio/>
    </div>
    <div id="id07" class="modal">
      <RimuoviVoce/>
    </div>
    <div id="id08" class="modal">
      <AggiungiVoceBilancio/>
    </div>
  </body>
</div>
</template>

<script>
import { AXIOS } from "./http-common";
import {closeMixin} from "./close-mixin";

import RegistraUtente from "./RegistraUtente";
import CambiaPassword from "./CambiaPassword";
import AggiornaUtente from "./AggiornaUtente";
import ListaSoci from "./ListaSoci";
import VisualizzaBilancio from "./VisualizzaBilancio";
import AggiungiVoceBilancio from "./AggiungiVoceBilancio";
import RimuoviVoce from "./RimuoviVoce";
export default {
  name: "service",
  mixins: [closeMixin],
  data() {
    return {
      response: [],
      errors: [],
      modal: "id01",
      modal: "id02",
      modal: "id03",
      modal: "id04",
      modal: "id05",
      modal: "id07",
      modal: "id08",
      accesso: null,
      adminTrue: false,
      all: ["id01", "id02", "id03", "id04", "id05", "id07", "id08"]
    };
  },
  created() {
    this.accesso = this.$route.query.livello;
  },
  components: {
    RegistraUtente,
    CambiaPassword,
    AggiornaUtente,
    ListaSoci,
    VisualizzaBilancio,
    AggiungiVoceBilancio,
    RimuoviVoce
  },
   methods: {
    logout() {
      this.accesso = null;
      window.location.href = "/#/";
    },
    openAdmin() {
      console.log("open");
      this.adminTrue = true;
    },
    closeAdmin() {
      console.log("close");
      this.adminTrue = false;
    },
    openLog() {
      AXIOS.get(`/hello`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.response = response.data;
          console.log(response.data);
        })
        .catch(e => {
          this.errors.push(e);
        });
    },
    onclick: function(event) {
      if (event.target == modal) {
        modal.style.display = "none";
      }
    }
  }
};
</script>



<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.flex-nav {
  display: flex;
  justify-content: center;
  max-height: 5em !important;
}

.test > .btn-group > .dropdown-menu:active {
  display: block;
  position: static;
}
.ddown-offset {
  height: 60px;
  margin: 0;
  font-weight: 700;
  box-shadow: 0px 1px 5px;
  border-radius: 0.25rem;
}

.fa-fw {
  width: 2em;
}
h1,
h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
* {
  box-sizing: border-box;
}

.bg {
  height: 100vh;
  width: 100% !important;
  background-image: url(../assets/hands.jpg) !important;
  transform: scale(1.17);
  background-position: top;
  background-repeat: no-repeat;
  background-size: cover;
  opacity: .2;
  margin-top: 8vh;
  padding: 0;
}
/* The Modal (background) */
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  width: 100%;
  height: 100%;
  overflow: auto;
  background-color: #474e5d94;
  padding-top: 20px;
}

/* Modal Content/Box */
.modal-content {
  background-color: #fefefe;
  margin: 5% auto 15% auto;
  border: 1px solid #888;
  width: 80%;
}

button {
  margin: 0 !important;
}
button:focus {
  outline: none !important;
}
.logout {
  background: rgb(100, 99, 99) !important;
  color: #fefefe !important;
  height: 100% !important;
  padding: 0 !important;
  font-weight: 700;
}
.logout:hover {
  background: rgba(80, 80, 80, 0.637) !important;
  color: white !important;
}

.nav {
  background: #6ca78c;
}
/* The Close Button (x) */
.close {
  position: absolute;
  right: 35px;
  top: 15vh;
}

.funkyradio input[type="radio"]:checked ~ label,
.funkyradio input[type="checkbox"]:checked ~ label {
  color: #777;
}
.funkyradio input[type="radio"]:checked ~ label:before,
.funkyradio input[type="checkbox"]:checked ~ label:before {
  content: "\2714";
  text-indent: 0.9em;
  color: white;
  background-color: #ccc;
}

.funkyradio-danger input[type="radio"]:checked ~ label:before,
.funkyradio-danger input[type="checkbox"]:checked ~ label:before {
  color: #fff;
  background-color: #1800f19b;
}

</style>


<style>
/* NON SCOPED STYLES */

/* Set a style for all buttons */
button {
  background-color: #4caf50;
  color: white;
  padding: 14px 20px;
  border: none;
  cursor: pointer;
  width: 100%;
  opacity: 0.9;
  height: 60px;
}
button:focus {
  outline: none !important;
}
button:hover {
  opacity: 1;
}
/* Extra styles for the cancel button */
.cancelbtn {
  padding: 14px 20px;
  background-color: #f44336;
  border-top-right-radius: 1rem;
}
.signupbtn {
  border-top-left-radius: 1rem;
}
.fake button::after {
  display: none;
}
.dropdown-toggle {
  background: rgb(83, 83, 83) !important;
  border: none !important;
  font-weight: 600 !important;
}
.dropdown-toggle:hover {
  background: rgba(83, 83, 83, 0.781) !important;
}
.fake .dropdown-menu.show {
  display: none !important;
}
/* The Close Button (x) */
.close {
  position: absolute;
  right: 35px;
  top: 3vh;
  font-size: 40px !important;
  font-weight: bold;
  color: black;
}

.close:hover,
.close:focus {
  color: #f44336 !important;
  cursor: pointer;
}

.funkyradio {
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
    Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
}
.funkyradio div {
  clear: both;
  overflow: hidden;
}
.funkyradio label {
  width: 100%;
  border-radius: 3px;
  border: 1px solid #d1d3d4;
  font-weight: normal;
}
.funkyradio input[type="radio"]:empty,
.funkyradio input[type="checkbox"]:empty {
  display: none;
}
.funkyradio input[type="radio"]:empty ~ label,
.funkyradio input[type="checkbox"]:empty ~ label {
  position: relative;
  line-height: 2.5em;
  text-indent: 45px;
  margin-top: 4px;
  cursor: pointer;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}
.funkyradio input[type="radio"]:empty ~ label:before,
.funkyradio input[type="checkbox"]:empty ~ label:before {
  position: absolute;
  display: block;
  top: 0;
  bottom: 0;
  left: 0;
  content: "";
  width: 2.5em;
  background: #d1d3d4;
  border-radius: 3px 0 0 3px;
  color: #777;
}
.funkyradio input[type="radio"]:hover:not(:checked) ~ label,
.funkyradio input[type="checkbox"]:hover:not(:checked) ~ label {
  color: #888;
}
.funkyradio input[type="radio"]:hover:not(:checked) ~ label:before,
.funkyradio input[type="checkbox"]:hover:not(:checked) ~ label:before {
  content: "\2714";
  text-indent: 0.9em;
  color: #c2c2c2;
}

.funkyradio input[type="radio"]:focus ~ label:before,
.funkyradio input[type="checkbox"]:focus ~ label:before {
  box-shadow: 0 0 0 3px #999;
}

.fa-check-circle::before {
  color: green;
  font-size: 7em;
}
.fa-times::before {
  color: red;
  font-size: 7em;
}
.errorMessage {
  color: red;
}
.flex-col {
  display: flex;
  flex-direction: column;
}
.opac {
  opacity: .5;
}
.no-height {
  height: 0px;
}
.middle {
  margin-top: 15vh;
}
.formSuccess-enter {
  
}
.formSuccess-enter-active {
  animation: success 2s ease-in-out;
}
.formSuccess-leave {

}
.formSuccess-leave-active {
  
}
@keyframes success {
  0% {
    opacity: 0;
    transform: scale(.8);
  } 
  50% {
    opacity: .8;
    transform:  scale(1.2);
  }
  100% {
    opacity: 1;
    transform:  scale(1);
  }
}

</style>