<template>
  <form class="modal-content" action>
    <span
      onclick="document.getElementById('id01').style.display='none'"
      class="close"
      title="Close Modal"
    >&times;</span>
    <div class="container">
      <h1>Registra utente</h1>
      <hr>
      <div class="row">
        <label style="margin-left:2%" for="nome">
          <b>Nome*</b>
        </label>
        
        <label style="margin-left:43%" for="cognome">
          <b>Cognome*</b>
        </label>
      </div>

      <div style="width:100%">
        <input
          type="text"
          v-model="user.name"
          class="testo anagrafica"
          placeholder="Nome utente"
          name="nome"
          required
        >
        <input
          type="text"
          v-model="user.surname"
          class="testo anagrafica"
          placeholder="Cognome utente"
          name="cognome"
          required
        >
      </div>
      

        <label for="CF">
          <b>Codice Fiscale*</b>
        </label>
         <label style="margin-left:43%" for="nome">
            <b>Ruoli*</b>
          </label>
        <br>
        <div class="row">
        <input
          type="text"
          v-model="user.fiscalCode"
          class="testo"
          placeholder="xxxxxxxxxxxxxxxxxx"
          name="CF"
          required
        >
        
        <fieldset style="padding:0 0 0 10px">
          <br>
          <input type="checkbox" name="html" value="html"> Amministratore
          <input type="checkbox" name="css" value="css"> Presidente
          <input type="checkbox" name="javascript" value="javascript"> Tesoriere
          <input type="checkbox" name="javascript" value="javascript"> Consigliere
        </fieldset>
      </div>
      <br>
      <label for="indirizzo">
        <b>Idirizzo*</b>
      </label>
      <div style="width:100%">
        <input
          type="text"
          v-model="user.localAddress"
          class="ind"
          placeholder="Via/Piazza, n°civico"
          name="indirizzo"
          required
        >
        <input
          type="text"
          v-model="user.citta"
          style="width:15%;"
          class="ind"
          placeholder=" Citta"
          name="Citta"
          required
        >
        <input
          type="text"
          v-model="user.CAP"
          style="width:10%;"
          class="ind"
          placeholder=" CAP"
          name="CAP"
          required
        >
        <input
          type="text"
          v-model="user.nation"
          style="width:20%;"
          class="ind"
          placeholder="Nazione"
          name="Nazione"
          required
        >
      </div>
      <hr>
      <label for="email">
        <b>User Email*</b>
      </label>
      <div style="width:100%">
        <input
          type="email"
          v-model="user.firstEmail"
          class="anagrafica"
          placeholder="firsEmail@ccc.com"
          name="email"
          required
        >
        <input
          type="email"
          v-model="user.secondEmail"
          class="anagrafica"
          placeholder="secondEmail@log.com"
          name="email"
        >
      </div>
      <label for="numeri">
        <b>Numeri di Telefono</b>
      </label>
      <div style="width:100%">
        <input
          type="text"
          v-model="user.fixNumber"
          class="anagrafica"
          placeholder="Numero Fisso"
          name="fisso"
        >
        <input
          type="text"
          v-model="user.cellularNumber"
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
        v-model="user.Note"
        id="message"
        name="message"
        rows="2"
        class="form-control md-textarea"
      ></textarea>
      <div class="row">
				
					<div class="col-xs-6 col-sm-6 col-md-6">
						<button onclick="document.getElementById('id01').style.display='none'" class=" cancelbtn btn-block">Cancel</button>
					</div>
          	<div class="col-xs-6 col-sm-6 col-md-6">
                        <button type="submit" @click="createUser()" class=" btn-success btn-block" value="Sign In">Sign Up</button>
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
        name: "",
        surname: "",
        fiscalCode: "",
        localAddress: "",
        nation: "",
        CAP: "",
        citta: "",
        firstEmail: "",
        secondEmail: "",
        fixNumber: "",
        cellullarNumber: "",
        Note: ""
      }
      /*showResponse: false,
        retrievedUser: {},
        showRetrievedUser: false*/
    };
  },
  methods: {
    // Fetches posts when the component is created.
    createUser() {
      var params = new URLSearchParams();
      params.append("name", this.user.nome);
      params.append("surname", this.user.cognome);
      params.append("fiscalCode", this.user.codFisc);
      params.append("localAddress", this.user.indirizzo);
      params.append("nation", this.user.nazione);
      params.append("CAP", this.user.cap);
      params.append("citta", this.user.citta);
      params.append("firstEmail", this.user.mail1);
      params.append("secondEmail", this.user.mail2);
      params.append("fixNumber", this.user.numFisso);
      params.append("cellullarNumber", this.user.numCellulare);
      params.append("Note", this.user.campoNote);

      AXIOS.post(`/user`, params);
      //.then(response => {
      // JSON responses are automatically parsed.
      //  this.response = response.data
      //  this.user.id = response.data
      /*console.log(response.data)
            this.showResponse = true*/
      //})
      //.catch(e => {
      //  this.errors.push(e)
      //})
    }
  }
};
</script>


<style>
/* Full-width input fields */
.testo,
input[type="password"],
textarea[type="text"] {
  width: 100%;
  padding: 15px;
  margin: 5px 0 22px 0;
  display: inline-block;
  border: none;
  background: #f1f1f1;
}

/* Add a background color when the inputs get focus */
.testo:focus,
input[type="password"]:focus,
textarea[type="text"]:focus {
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
  
  width: 10%;
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
  color: #f1f1f1;
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
  background-color: #ddd;
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
input[name="CF"] {
  width: 30% !important;
}
</style>


