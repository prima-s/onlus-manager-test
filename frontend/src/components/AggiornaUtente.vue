<template>
<div class="modal-content">
  <form action :class="{ '' : (!success && !error), 'opac' : (success || error) }">
    <link
      rel="stylesheet"
      href="https://use.fontawesome.com/releases/v5.2.0/css/all.css"
      integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ"
      crossorigin="anonymous"
    >
    <span @click="closeAll()" class="close" title="Close Modal">&times;</span>
    <div class="container">
      <h1>Aggiorna dati socio</h1>
      <hr>
      <div class="container h-100">
        <div class="d-flex justify-content-center h-100">
          <div class="searchbar">
            <input
              class="search_input"
              type="text"
              name
              placeholder="Ricerca socio per cognome..."
              v-model="cognome"
              @input="searchByCognome"
            >
            <a class="search_icon">
              <i class="fas fa-search"></i>
            </a>
          </div>
        </div>
      </div>

      <div class="row">
        <div class="col-md-6">
          <label for="nome">
            <b>Nome*</b>
          </label>
          <br>
          <input
            type="text"
            v-model="user.nome"
            class="testo anagrafica"
            placeholder="Nome utente"
            name="nome"
            required
          >
        </div>
        <div class="col-md-6">
          <label for="cognome">
            <b>Cognome*</b>
          </label>
          <br>
          <input
            type="text"
            v-model="user.cognome"
            class="testo anagrafica"
            placeholder="Cognome utente"
            name="cognome"
            required
          >
        </div>
      </div>

      <div class="row">
        <div class="col-md-6">
          <label for="CF">
            <b>Codice Fiscale*</b>
          </label>
          <br>
          <input
            type="text"
            v-model="user.cf"
            maxlength="16"
            placeholder="xxxxxxxxxxxxxxxxxx"
            name="CF"
            @input="reCheckCodFisc"
            @blur="checkCodFisc"
            required
          >
          <br>
          <p :class="{'notCorrect' : codFiscOk == false}">{{codFiscMessage}}</p>
        </div>
      </div>

      <label for="Ruoli">
        <b>Ruoli</b>
      </label>
      <div class="funkyradio row">
        <div class="funkyradio-danger col-md-3">
          <input type="checkbox" value="amministratore" v-model="user.ruolo" name="checkbox">
          <label for="checkbox1">Amministratore</label>
        </div>
        <div class="funkyradio-danger col-md-2">
          <input type="checkbox" value="presidente" v-model="user.ruolo" name="checkbox">
          <label for="checkbox2">Presidente</label>
        </div>
        <div class="funkyradio-danger col-md-2">
          <input type="checkbox" value="tesoriere" v-model="user.ruolo" name="checkbox">
          <label for="checkbox3">Tesoriere</label>
        </div>
        <div class="funkyradio-danger col-md-2">
          <input type="checkbox" value="consigliere" v-model="user.ruolo" name="checkbox">
          <label for="checkbox4">Consigliere</label>
        </div>
        <div class="funkyradio-danger col-md-2">
          <input type="checkbox" name="checkbox" disabled="true" checked>
          <label for="checkbox5">Socio</label>
        </div>
      </div>
      <br>
      <label for="indirizzo">
        <b>Indirizzo*</b>
      </label>
      <div class="row">
        <div class="col-md-4">
          <input
            type="text"
            v-model="user.indirizzo"
            placeholder="Via/Piazza, n° civico"
            name="indirizzo"
            required
          >
        </div>
        <div class="col-md-3">
          <input type="text" v-model="user.citta" placeholder=" Città" name="citta" required>
        </div>
        <div class="col-md-2">
          <input type="text" v-model="user.cap" placeholder=" CAP" name="cap" required>
        </div>
        <div class="col-md-3">
          <input type="text" v-model="user.nazione" placeholder="Nazione" name="nazione" required>
        </div>
      </div>
      <hr>
      <label for="email">
        <b>Email*</b>
      </label>
      <div class="row">
        <div class="col-md-6">
        <input
          type="email"
          v-model="user.email"
          class="anagrafica"
          placeholder="primaEmail@ccc.com"
          name="email"
          required
        >
        </div>
         <div class="col-md-6">
        <input
          type="email"
          v-model="user.email2"
          class="anagrafica"
          placeholder="secondaEmail@ccc.com"
          name="email2"
        >
        </div>
      </div>
      <br>
      <label for="numeri">
        <b>Numeri di Telefono</b>
      </label>
       <div class="row">
         <div class="col-md-6">
        <input
          type="text"
          v-model="user.fisso"
          class="anagrafica"
          placeholder="Numero Fisso"
          name="fisso"
        >
         </div>
          <div class="col-md-6">
        <input
          type="text"
          v-model="user.cellulare"
          class="anagrafica"
          placeholder="Numero cellulare*"
          name="cellulare"
          required
        >
          </div>
      </div>
      <hr>
      <label>
        <b>Note</b>
      </label>
      <br>
      <textarea
        type="text"
        v-model="user.note"
        name="message"
        rows="2"
        class="form-control"
      ></textarea>
    </div>
    <br>
    <br>
    <div class="row">
      <div class="col-md-6">
        <button @click="closeAll()" class="cancelbtn btn-block">Annulla</button>
      </div>
      <div class="col-md-6">
        <button @click.prevent="createUser()" class="signupbtn btn-block">Registra</button>
      </div>
    </div>
  </form>

  <transition name="formSuccess">
  <div v-if="success" class="row">
      <div class="col-md-12 text-center no-height">
      <i style="transform: translateY(-50vh);" class="fas fa-check-circle"></i>
      </div>
    </div>
    <div v-if="error" class="row">
      <div class="col-md-12 text-center no-height">
      <i style="transform: translateY(-50vh);" class="fas fa-times"></i>
      </div>
    </div>
</transition>

</div>
</template>
<script>
import { AXIOS } from "./http-common";
import { closeMixin } from "./close-mixin";
import { checkCodFisc } from "./checkCodFiscMixin";

export default {
  mixins: [closeMixin, checkCodFisc],
  data() {
    return {
      response: [],
      errors: [],
      cognome: "",
      user: {
        nome: "",
        cognome: "",
        cf: "",
        indirizzo: "",
        nazione: "",
        cap: "",
        citta: "",
        email: "",
        email2: "",
        fisso: "",
        cellulare: "",
        note: "",
        ruolo: []
      },
      showResponse: false,
      retrievedUser: {},
      showRetrievedUser: false,
      success: false,
      error: false
    };
  },
  methods: {
     resetData() {
      this.user.nome = "";
      this.user.cognome = "";
      this.user.cf = "";
      this.user.indirizzo = "";
      this.user.nazione = "";
      this.user.cap = "";
      this.user.citta = "";
      this.user.email = "";
      this.user.email2 = "";
      this.user.fisso = "";
      this.user.cellulare = "";
      this.user.note = "";
      this.user.Ruolo = [];
    },
    searchByCognome() {
      var params = new URLSearchParams();
      params.append('cognome', this.cognome);
      AXIOS.post(`/cercaByCognome`, params)
        .then(response => {
          this.response = response.data;
          this.user.nome = response.data.nome;
          this.user.cognome = response.data.cognome;
          this.user.cf = response.data.codFisc;
          this.user.indirizzo = response.data.indirizzo;
          this.user.nazione = response.data.nazione;
          this.user.cap = response.data.cap;
          this.user.citta = response.data.citta;
          this.user.email = response.data.email;
          this.user.email = response.data.mail1;
          this.user.email2 = response.data.mail2;
          this.user.fisso = response.data.numFisso;
          this.user.cellulare = response.data.numCellulare;
          this.user.note = response.data.campoNote;
          let ruoloArray = response.data.ruolo.split(" ");
          this.user.ruolo = ruoloArray;
          console.log(response.data);
        })
        .catch(e => {
          this.errors.push(e);
        });
    },
        createUser() {
      var params = new URLSearchParams();
      params.append("nome", this.user.nome);
      params.append("cognome", this.user.cognome);
      params.append("cf", this.user.cf);
      params.append("indirizzo", this.user.indirizzo);
      params.append("nazione", this.user.nazione);
      params.append("cap", this.user.cap);
      params.append("citta", this.user.citta);
      params.append("email", this.user.email);
      params.append("email2", this.user.email2);
      params.append("fisso", this.user.fisso);
      params.append("cellulare", this.user.cellulare);
      params.append("note", this.user.note);
      params.append("Ruolo", this.user.Ruolo);
      console.log(this.user.Ruolo);

      AXIOS.post(`/person`, params)
        .then(response => {
          this.response = response.data;
          this.showResponse = true;
          this.success = true;
          this.responseAfterSubmit();
          this.resetData();
        })
        .catch(e => {
          this.errors.push(e);
          this.error = true;
        });
    }
  }
};
</script>

<style  scoped>
.searchbar {
  margin-bottom: auto;
  margin-top: auto;
  height: 60px;
  background-color: #353b48c0;
  border-radius: 30px;
  padding: 10px;
  color: white;
}

.search_input {
  color: white;
  border: 0;
  outline: 0;
  background: none;
  width: 0;
  caret-color: transparent;
  line-height: 40px;
  transition: width 0.4s linear;
}
.searchbar > .search_input:focus {
  background-color: transparent;
}
.searchbar > .search_input::placeholder {
  color: rgb(199, 194, 194);
}
.searchbar > .search_input {
  padding: 0 10px;
  width: 450px;
  caret-color: red;
  transition: width 0.4s linear;
}

.searchbar > .search_icon {
  background: rgba(255, 255, 255, 0.657);
  color: #e74c3c !important;
}

.search_icon {
  height: 40px;
  width: 40px;
  float: right;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 50%;
  color: #e74c3c !important;;
}

.container {
  padding: 16px;
}
hr {
  border: 1px solid #f1f1f1;
  margin-bottom: 25px;
}

/* Clear floats */
.clearfix::after {
  content: "";
  clear: both;
  display: table;
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
/* Change styles for cancel button and signup button on extra small screens */
@media screen and (max-width: 300px) {
  .cancelbtn,
  .signupbtn {
    width: 100%;
  }
}
.notCorrect {
  padding: 3px;
  background: orangered;
  color: #f1f1f1;
  text-align: center;
}
</style>



