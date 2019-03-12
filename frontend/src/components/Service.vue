<template>
<div class="servicemy">

<header class="navbar-nav">
  <div id="nav" class="bg-dark">
    <b-dropdown
      id="ddown-offset"
      offset="25"
      text="Modifica account"
      variant="outline-info btn-block"
      class="m-2 w-15"
    >
      <b-dropdown-item onclick="document.getElementById('id01').style.display='block'" style="width:auto;">Registra utente</b-dropdown-item>
      <b-dropdown-item onclick="document.getElementById('id02').style.display='block'" style="width:auto;">Modifica la password</b-dropdown-item>
      <b-dropdown-item href="#">Aggiorna dati socio</b-dropdown-item>
      <b-dropdown-item href="#">Visualizza lista dei soci</b-dropdown-item>
      <b-dropdown-item href="#">Lista soci PDF</b-dropdown-item>
    </b-dropdown>

    <b-dropdown
      id="ddown-offset"
      offset="25"
      text="Gestione bilancio"
      variant="outline-info btn-block"
      class="m-2 w-15"
    >
      <b-dropdown-item href="#">Visualizza bilancio</b-dropdown-item>
      <b-dropdown-item href="#">Bilancio in PDF</b-dropdown-item>
      <b-dropdown-item href="#">Nuova voce di bilancio</b-dropdown-item>
      <b-dropdown-item href="#">Rimuovi voce di bilancio</b-dropdown-item>
    </b-dropdown>

    <b-dropdown
      id="ddown-offset"
      offset="25"
      text="Modifica opzioni"
      variant="outline-info btn-block"
      class="m-2 w-15"
    >
      <b-dropdown-item href="#">Inserisci importo bilancio</b-dropdown-item>
      <b-dropdown-item href="#">Modifica importo bilancio</b-dropdown-item>
      <b-dropdown-item href="#">Area amministratore</b-dropdown-item>
    </b-dropdown>
    <b-dropdown
      id="ddown-offset"
      offset="25"
      text="Visualizza file log"
      variant="outline-info"
      class="m-2 w-15"
    >
      <b-dropdown-item href="#">Registro attivita</b-dropdown-item>
    </b-dropdown>
  </div>
  </header>
  <body>
    <div id="id01" class="modal">
  <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
  <RegistraUtente />
  
</div>
  </body>
 </div>
</template>

<script>
// import axios from 'axios'
import { AXIOS } from "./http-common";
import RegistraUtente from './RegistraUtente';
export default {
  name: "service",

  data() {
    return {
      msg: "HowTo call REST-Services:",
      response: [],
      errors: [],
      value: 75,
      modal: ('id01'),
      modal: ('id02')

    };
  },
   components: {
    RegistraUtente
  },
  methods: {
    
    // Fetches posts when the component is created.
    callRestService() {
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
    onclick:function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}
  }
};
</script>



<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.test > .btn-group > .dropdown-menu:active {
  display: block;
  margin-bottom: 5px;
  position: static;
}
#ddown-offset {
  height: 50px;
}
body {
  margin-top: 20px;
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
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

/* The Modal (background) */
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 85px;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow:auto ; /* Enable scroll if needed */
  background-color: #474e5d;
  padding-top: 0px;
}

/* Modal Content/Box */
.modal-content {
  background-color: #fefefe;
  margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
  border: 1px solid #888;
  width: 80%; /* Could be more or less, depending on screen size */
}



/* Change styles for cancel button and signup button on extra small screens */
@media screen and (max-width: 300px) {
  .cancelbtn, .signupbtn {
     width: 100%;
  }
}
</style>
