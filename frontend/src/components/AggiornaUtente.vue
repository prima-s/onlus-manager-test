<template>
  <form class="modal-content">
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
            <input class="search_input" type="text" name placeholder="Search...">
            <a class="search_icon">
              <i class="fas fa-search"></i>
            </a>
          </div>
        </div>
      </div>

      <div class="row">
        <label style="margin-left:2%" for="nome">
          <b>Nome*</b>
        </label>
        
        <label style="margin-left:43%" for="cognome">
          <b>Cognome*</b>
        </label>
      </div>

      <div style="width:100%">
        <input type="text" class="testo anagrafica" placeholder="Nome utente" name="nome" required>
        <input
          type="text"
          class="testo anagrafica"
          placeholder="Cognome utente"
          name="cognome"
          required
        >
      </div>

      <!--
    <select class="selectpicker" multiple>
<option value="" disabled selected>Scegli il ruolo</option>
             <option   value="1"> Amministratore</option>
          <option   value="2"> Presidente</option>
          <option   value="3"> Tesoriere</option>
          <option   value="4"> Consigliere</option>
</select>
      -->
      <label for="Ruoli">
        <b>Ruoli*</b>
      </label>
      <div class="funkyradio row">
        <div class="funkyradio-danger" style="padding-left:20px;width:17%;">
          <input type="checkbox" name="checkbox" id="checkbox6">
          <label for="checkbox6">Amministratore</label>
        </div>
        <div class="funkyradio-danger" style="padding-left:20px;width:17%;">
          <input type="checkbox" name="checkbox" id="checkbox7">
          <label for="checkbox7">Presidente</label>
        </div>
        <div class="funkyradio-danger" style="padding-left:20px;width:17%;">
          <input type="checkbox" name="checkbox" id="checkbox8">
          <label for="checkbox8">Tesoriere</label>
        </div>
        <div class="funkyradio-danger" style="padding-left:20px;width:17%;">
          <input type="checkbox" name="checkbox" id="checkbox9">
          <label for="checkbox9">Consigliere</label>
        </div>
        <div class="funkyradio-danger" style="padding-left:20px;width:15%;">
          <input type="checkbox" name="checkbox" id="checkbox10" disabled="true" checked>
          <label for="checkbox10">Socio</label>
        </div>
      </div>

      <br>
      <label for="indirizzo">
        <b>Idirizzo*</b>
      </label>
      <div style="width:100%">
        <input type="text" class="ind" placeholder="Via/Piazza, n°civico" name="indirizzo" required>
        <input
          type="text"
          style="width:15%;"
          class="ind"
          placeholder=" Citta"
          name="Citta"
          required
        >
        <input type="text" style="width:10%;" class="ind" placeholder=" CAP" name="CAP" required>
        <input
          type="text"
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
          class="anagrafica"
          placeholder="firsEmail@ccc.com"
          name="email"
          required
        >
        <input type="email" class="anagrafica" placeholder="secondEmail@log.com" name="email">
      </div>
      <label for="numeri">
        <b>Numeri di Telefono</b>
      </label>
      <div style="width:100%">
        <input type="text" class="anagrafica" placeholder="Numero Fisso" name="fisso">
        <input
          type="text"
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
      <textarea type="text" id="message1" name="message" rows="2" class="form-control md-textarea"></textarea>
      <div class="row">
        <div class="col-xs-6 col-sm-6 col-md-6">
          <button @click="closeAll()" class="cancelbtn btn-block">Cancel</button>
        </div>
        <div class="col-xs-6 col-sm-6 col-md-6">
          <button
            type="submit"
            @click="createUser()"
            class="btn-success btn-block"
            value="Sign In"
          >Sign Up</button>
        </div>
      </div>
    </div>
  </form>
</template>
<script>
function myFunction() {
  document.getElementById("myDropdown").classList.toggle("show");
}
export default {
  data() {
    return {
      msg: "HowTo call REST-Services:",
      response: [],
      errors: [],
      value: 75
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
    onclick: function(event) {
      if (event.target == modal) {
        modal.style.display = "none";
      }
    },

    mouseOver: function() {
      this.active = !this.active;
    }
  }
};
</script>
<style  scoped>
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
  color: #333;
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

.searchbar {
  margin-bottom: auto;
  margin-top: auto;
  height: 60px;
  background-color: #353b48;
  border-radius: 30px;
  padding: 10px;
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

.searchbar > .search_input {
  padding: 0 10px;
  width: 450px;
  caret-color: red;
  transition: width 0.4s linear;
}

.searchbar > .search_icon {
  background: white;
  color: #e74c3c;
}

.search_icon {
  height: 40px;
  width: 40px;
  float: right;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 50%;
  color: white;
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
  width: 100%;
  padding: 15px;
  margin: 5px 0 22px 20px;
  display: inline-block;
  border: none;
  background: #f1f1f1;
  height: 50px;
}
input[name="CF"]:focus {
  background-color: #ddd;
  outline: none;
}
</style>



