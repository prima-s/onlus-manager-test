<template>
  <form class="modal-content" action>
    <span @click="closeAll()" class="close" title="Close Modal">&times;</span>
    <div class="container">
      <h1>Registra nuovo socio</h1>
      <hr>
      <h6>(*)Campi Obbligatori</h6>
      <hr>
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
            required
          >
        </div>
      </div>

      <label for="Ruoli">
        <b>Ruoli</b>
      </label>
      <div class="funkyradio row">
        <div class="funkyradio-danger col-md-3">
          <input
            type="checkbox"
            value="amministratore"
            v-model="user.Ruolo"
            name="checkbox"
            id="checkbox1"
          >
          <label for="checkbox1">Amministratore</label>
        </div>
        <div class="funkyradio-danger col-md-2">
          <input
            type="checkbox"
            value="presidente"
            v-model="user.Ruolo"
            name="checkbox"
            id="checkbox2"
          >
          <label for="checkbox2">Presidente</label>
        </div>
        <div class="funkyradio-danger col-md-2">
          <input
            type="checkbox"
            value="tesoriere"
            v-model="user.Ruolo"
            name="checkbox"
            id="checkbox3"
          >
          <label for="checkbox3">Tesoriere</label>
        </div>
        <div class="funkyradio-danger col-md-2">
          <input
            type="checkbox"
            value="consigliere"
            v-model="user.Ruolo"
            name="checkbox"
            id="checkbox4"
          >
          <label for="checkbox4">Consigliere</label>
        </div>
        <div class="funkyradio-danger col-md-2">
          <input type="checkbox" name="checkbox" id="checkbox5" disabled="true" checked>
          <label for="checkbox5">Socio</label>
        </div>
      </div>
      <br>
      <label for="indirizzo">
        <b>Indirizzo*</b>
      </label>
      <div style="width:100%">
        <input
          type="text"
          v-model="user.indirizzo"
          class="ind"
          placeholder="Via/Piazza, n° civico"
          name="indirizzo"
          required
        >
        <input
          type="text"
          v-model="user.citta"
          style="width:15%;"
          class="ind"
          placeholder=" Citta"
          name="citta"
          required
        >
        <input
          type="text"
          v-model="user.cap"
          style="width:10%;"
          class="ind"
          placeholder=" CAP"
          name="cap"
          required
        >
        <input
          type="text"
          v-model="user.nazione"
          style="width:20%;"
          class="ind"
          placeholder="Nazione"
          name="nazione"
          required
        >
      </div>
      <hr>
      <label for="email">
        <b>Email*</b>
      </label>
      <div style="width:100%">
        <input
          type="email"
          v-model="user.email"
          class="anagrafica"
          placeholder="primaEmail@ccc.com"
          name="email"
          required
        >
        <input
          type="email"
          v-model="user.email2"
          class="anagrafica"
          placeholder="secondaEmail@ccc.com"
          name="email2"
        >
      </div>
      <label for="numeri">
        <b>Numeri di Telefono</b>
      </label>
      <div style="width:100%">
        <input
          type="text"
          v-model="user.fisso"
          class="anagrafica"
          placeholder="Numero Fisso"
          name="fisso"
        >
        <input
          type="text"
          v-model="user.cellulare"
          class="anagrafica"
          placeholder="Numero cellulare*"
          name="cellulare"
          required
        >
      </div>
      <hr>
      <label>
        <b>Note</b>
      </label>
      <br>
      <textarea
        type="text"
        v-model="user.note"
        id="message"
        name="message"
        rows="2"
        class="form-control md-textarea"
      ></textarea>
      <div class="row">
        <div class="col-xs-6 col-sm-6 col-md-6">
          <button @click="closeAll()" class="cancelbtn btn-block">Annulla</button>
        </div>
        <div class="col-xs-6 col-sm-6 col-md-6">
          <button
            type="submit"
            @click="createUser()"
            class="btn-success btn-block"
            value="Sign In"
          >Registra</button>
        </div>
      </div>
    </div>
  </form>
</template>


<script>
import { AXIOS } from "./http-common";
export default {
  name: "user",

  data() {
    return {
      response: [],
      errors: [],
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
        Ruolo: []
      },
      showResponse: false,
      retrievedUser: {},
      showRetrievedUser: false
    };
  },
  methods: {
    closeAll() {
      for (
        var i = 0;
        i < document.getElementsByClassName("modal").length;
        i++
      ) {
        document.getElementsByClassName("modal")[i].style.display = "none";
      }
    },

    // Fetches posts when the component is created.
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
          //JSON responses are automatically parsed.
          this.response = response.data;
          console.log(response.data);

          this.showResponse = true;
        })
        .catch(e => {
          this.errors.push(e);
        });
    }
  }
};
</script>


<style scoped>
.funkyradio {
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
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
  text-indent: 3.25em;
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
  color:#777;
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

.funkyradio input[type="radio"]:focus ~ label:before,
.funkyradio input[type="checkbox"]:focus ~ label:before {
  box-shadow: 0 0 0 3px #999;
}

.funkyradio-danger input[type="radio"]:checked ~ label:before,
.funkyradio-danger input[type="checkbox"]:checked ~ label:before {
  color: #fff;
  background-color: #1800f19b;
}

.testo,
input[type="password"],
textarea[type="text"] {
  padding: 15px;
  background: #f1f1f1;
}
.selle {
  padding: 15px;
  background: #f1f1f1;
}
/* Add a background color when the inputs get focus */
.testo:focus,
input[type="password"]:focus,
textarea[type="text"]:focus {
  background-color: #ddd;
  outline: none;
}
.selle:focus {
  background-color: #ddd;
  outline: none;
}
/* Set a style for all buttons */
button {
  background-color: #4caf50;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
  opacity: 0.9;
}

button:hover {
  opacity: 1;
}

/* Extra styles for the cancel button */
.cancelbtn {
  padding: 14px 20px;
  background-color: #f44336;
}

/* Float cancel and signup buttons and add an equal width */
.cancelbtn,
.signupbtn {
  width: 100%;
}
.container {
  padding: 16px;
}
hr {
  border: 1px solid #f1f1f1;
  margin-bottom: 25px;
}

/* The Close Button (x) */
.close {
  position: absolute;
  right: 35px;
  top: 15px;
  font-size: 40px;
  font-weight: bold;
  color: black;
}

.close:hover,
.close:focus {
  color: #f44336;
  cursor: pointer;
}

/* Clear floats */
.clearfix::after {
  content: "";
  clear: both;
  display: table;
}

/* Change styles for cancel button and signup button on extra small screens */
@media screen and (max-width: 300px) {
  .cancelbtn,
  .signupbtn {
    width: 100%;
  }
}
.ind {
  margin: 5px 10px 22px 0;
  width: 45%;
  padding: 15px;
  display: inline-block;
  border: none;
  background: #f1f1f1;
  outline: none;
}
.anagrafica {
  margin: 5px 10px 22px 0;
  width: 48%;
  padding: 15px;
  display: inline-block;
  border: none;
  border: none;
  background: #f1f1f1;
}
.anagrafica:focus {
  background-color: #ddd;
  outline: none;
}
.numeri {
  margin: 5px 10px 22px 0 !important;
  width: 48% !important;
  padding: 15px !important;
  display: inline-block !important;
  border: none !important;
  border: none !important;
  background: #f1f1f1 !important;
}
.numeri:focus {
  background-color: #ddd;
  outline: none;
}
input:focus {
  background-color: #ddd;
  outline: none;
}
</style>




