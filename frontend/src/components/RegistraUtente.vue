<template>
    <form class="modal-content" action="">
    <div class="container">
      <h1>Registrazione utente</h1>
      <hr>
   <div class="row">
       <label style="margin-left:2%" for="nome"><b>Nome*</b></label>
       
       <label style="margin-left:43%" for="cognome"><b>Cognome*</b></label>
       </div>

        <div style="width:100%">

      <input type="text" v-model="socio.nome" class="anagrafica" placeholder="Nome utente" name="nome" required>
      <input type="text" v-model="socio.cognome" class="anagrafica" placeholder="Cognome utente" name="cognome" required>
        </div>
        <label for="codFisc"><b>Codice Fiscale</b></label>
        <br>
        <input type="text" v-model="socio.codFisc" placeholder="xxxxxxxxxxxxxxxxxx"  name="codFisc" required>
        <br>
       <label for="indirizzo"><b>Idirizzo*</b></label>
      <div style="width:100%">
      <input type="text" v-model="socio.indirizzo" class="ind" placeholder="Via/Piazza, n°civico" name="indirizzo" required>
      <input type="text" v-model="socio.citta" style="width:20%;" class="ind" placeholder="Citta" name="citta" required>
      <input type="text" v-model="socio.nazione" style="width:20%;"  class="ind" placeholder="Nazione" name="nazione" required>
      <input type="text" v-model="socio.cap" style="width:10%;"  class="ind" placeholder=" CAP" name="cap" required>
  </div>
      <hr>
      <label for="mail1"><b>User Email*</b></label>
      <div style="width:100%">

      <input type="email" v-model="socio.mail1" class="anagrafica" placeholder="firsEmail@ccc.com" name="mail1" required>
      <input type="email" v-model="socio.mail2" class="anagrafica" placeholder="secondEmail@log.com" name="mail2">
        </div>
        <label for="fisso"><b>Numeri di Telefono</b></label>     
  <div style="width:100%">
            
      <input type="text" v-model="socio.numFisso" class="anagrafica" placeholder="Numero Fisso" name="fisso">
      <input type="text" v-model="socio.numCellulare" class="anagrafica" placeholder="Numero cellulare*" name="cellulare" required>
        </div>
      <hr>
      <label for="campoNote"><b>Note</b></label>
      <br>
     <textarea type="text" v-model="socio.campoNote" id="message" name="campoNote" rows="2" class="form-control md-textarea"></textarea>

      <!--<label>
        <input type="checkbox" checked="checked" name="remember" style="margin-bottom:15px"> Remember me
      </label>-->

      

      <div class="clearfix">
        <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">Cancel</button>
        <button type="submit" class="signupbtn" @click="createSocio()">Sign Up</button>
      </div>
    </div>
   
  </form>
</template>


<script>

import { AXIOS } from "./http-common";

  export default {
  name: "socio",

  data() {
    return {
      response: [],
      errors: [],
      socio: {
        nome: '',
        cognome: '',
        codFisc: '',
        indirizzo: '',
        citta: '',
        cap: '',
        nazione: '',
        mail1: '',
        mail2: '',
        numCellulare: '',
        numFisso: '',
        campoNote: '',
        numTessera: 0
      }

    };
  },
   methods: {
    createSocio () {
        var params = new URLSearchParams()
        params.append('nome', this.socio.nome)
        params.append('cognome', this.socio.cognome)
        params.append('codFisc', this.socio.codFisc)
        params.append('indirizzo', this.socio.indirizzo)
        params.append('citta', this.socio.citta)
        params.append('cap', this.socio.cap)
        params.append('nazione', this.socio.nazione)
        params.append('mail1', this.socio.mail1)
        params.append('mail2', this.socio.mail2)
        params.append('numCellulare', this.socio.numCellulare)
        params.append('numFisso', this.socio.numFisso)
        params.append('campoNote', this.socio.campoNote)
        AXIOS.post(`/socio`, params)
          .then(response => {
            // JSON responses are automatically parsed.
            this.response = response.data
            this.socio.numTessera = response.data
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


<style>
/* Full-width input fields */
input[type=text], input[type=password], textarea[type=text] {
  width: 100%;
  padding: 15px;
  margin: 5px 0 22px 0;
  display: inline-block;
  border: none;
  background: #f1f1f1;
}

/* Add a background color when the inputs get focus */
input[type=text]:focus, input[type=password]:focus, textarea[type=text]:focus {
  background-color: #ddd;
  outline: none;
}

/* Set a style for all buttons */
button {
  background-color: #4CAF50;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
  opacity: 0.9;
}

button:hover {
  opacity:1;
}

/* Extra styles for the cancel button */
.cancelbtn {
  padding: 14px 20px;
  background-color: #f44336;
}

/* Float cancel and signup buttons and add an equal width */
.cancelbtn, .signupbtn {
  float: left;
  width: 50%;
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
  .cancelbtn, .signupbtn {
     width: 100%;
  }
}
.ind{
  margin: 5px 10px 22px 0;
  width: 45%;
  padding: 15px;
  display: inline-block;
  border: none;
  background: #f1f1f1; 
  background-color: #ddd;
  outline: none;
}
.anagrafica{
  margin: 5px 10px 22px 0;
  width: 48%;
  padding: 15px;
  display: inline-block;
  border: none;
   border: none;
  background: #f1f1f1;

}
.anagrafica:focus{
  background-color: #ddd;
  outline: none;
}
.numero{
  margin: 5px 10px 22px 0;
  width: 48%;
  padding: 15px;
  display: inline-block;
  border: none;
   border: none;
  background: #f1f1f1;

}
.numero:focus{
  background-color: #ddd;
  outline: none;
}
input[name=CF]{
  width: 50% !important;
}
</style>


