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
        id="message"
        name="message"
        rows="2"
        class="form-control md-textarea"
      ></textarea>
    </div>
    <br>
    <div class="row">
      <div class="col-md-6">
        <button @click="closeAll()" class="cancelbtn btn-block">Annulla</button>
      </div>
      <div class="col-md-6">
        <button @click="createUser()" class="signupbtn btn-block">Registra</button>
      </div>
    </div>
  </form>
</template>


<script>
import { AXIOS } from "./http-common";
import { closeMixin } from "./close-mixin";
import { checkCodFisc } from "./checkCodFiscMixin";

export default {
  name: "user",
  mixins: [closeMixin, checkCodFisc],
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




