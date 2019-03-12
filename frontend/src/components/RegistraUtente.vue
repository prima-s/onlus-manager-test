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

      <input type="text" v-model="user.nome" class="anagrafica" placeholder="Nome utente" name="nome" required>
      <input type="text" v-model="user.cognome" class="anagrafica" placeholder="Cognome utente" name="cognome" required>
        </div>
        <label for="email"><b>Codice Fiscale</b></label>
        <br>
        <input type="text" v-model="user.cf" placeholder="xxxxxxxxxxxxxxxxxx"  name="cf" required>
        <br>
       <label for="indirizzo"><b>Idirizzo*</b></label>
      <div style="width:100%">
      <input type="text" v-model="user.indirizzo" class="ind" placeholder="Via/Piazza, n°civico" name="indirizzo" required>
      <input type="text" v-model="user.nazione" style="width:20%;"  class="ind" placeholder="Nazione" name="nazione" required>
      <input type="text" v-model="user.cap" style="width:10%;"  class="ind" placeholder=" CAP" name="cap" required>
      <input type="text" v-model="user.citta" class="ind" placeholder="citta" name="citta" required>
  </div>
      <hr>
      <label for="email"><b>User Email*</b></label>
      <div style="width:100%">

      <input type="email" v-model="user.email" class="anagrafica" placeholder="firsEmail@ccc.com" name="email" required>
      <input type="email" v-model="user.email2" class="anagrafica" placeholder="secondEmail@log.com" name="email2">
        </div>
        <label for="email"><b>Numeri di Telefono</b></label>     
  <div style="width:100%">
            
      <input type="text" v-model="user.fisso" class="anagrafica" placeholder="Numero Fisso" name="fisso">
      <input type="text" v-model="user.cellulare" class="anagrafica" placeholder="Numero cellulare*" name="cellulare" required>
        </div>
              <hr>
      <label><b>Ruolo</b></label>
      <br>
      <select data-placeholder="seleziona ruoli" v-model="user.ruoli" multiple class="chosen-select" name="ruoli">
      <option value=""></option>
      <option value="amministratore">amministratore</option>
      <option value="tesoriere">tesoriere</option>
      <option value="presidente">presidente</option>
      <option value="consigliere">consigliere</option>
      </select>
      <hr>
      <label><b>Note</b></label>
      <br>
     <textarea type="text" v-model="user.note" id="message" name="note" rows="2" class="form-control md-textarea"></textarea>

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

     // import axios from 'axios'
  import {AXIOS} from './http-common'
  export default {
    name: 'user',
    data () {
      return {
        response: [],
        errors: [],
        user: {
          nome: '',
          cognome: '',
          cf: '',
          indirizzo: '',
          nazione: '',
          cap: '',
          citta: '',
          email: '',
          email2: '',
          fisso: '',
          cellulare: '',
          note: '',
          ruoli: []
        },
        showResponse: false,
        retrievedUser: {},
        showRetrievedUser: false
      }
    },
    methods: {
      // Fetches posts when the component is created.
      createSocio () {
        var params = new URLSearchParams()
        params.append('nome', this.user.nome)
        params.append('cognome', this.user.cognome)
        params.append('indirizzo', this.user.indirizzo)
        params.append('cap', this.user.cap)
        params.append('citta', this.user.citta)
        params.append('nazione', this.user.nazione)
        params.append('cf', this.user.cf)
        params.append('fisso', this.user.fisso)
        params.append('cellulare', this.user.cellulare)
        params.append('email', this.user.email)
        params.append('email2', this.user.email2)
        params.append('note', this.user.note)
        params.append("ruoli", this.user.ruoli)
        AXIOS.post(`/person`, params)
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


