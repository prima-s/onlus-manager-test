<template>
  <div class="servicemy">
    <div id="nav" class="flex bg-dark">
      <b-dropdown
        id="ddown-offset"
        offset="25"
        text="Modifica Account"
        variant=" btn-outline-light btn-block"
        class="m-2 w-15"
      >
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

      <b-dropdown
        id="ddown-offset"
        offset="25"
        text="Gestione bilancio"
        variant=" btn-outline-light btn-block"
        class="m-2 w-15"
      >
        <b-dropdown-item
          onclick="document.getElementById('id05').style.display='block'"
          style="width:auto;"
          @click="closeAll()"
        >Visualizza bilancio</b-dropdown-item>
        <b-dropdown-item
          onclick="document.getElementById('id06').style.display='block'"
          style="width:auto;"
          @click="closeAll()"
          v-if="accesso === 'A' || accesso === 'B'"
        >Nuova voce di bilancio</b-dropdown-item>
        <b-dropdown-item
          onclick="document.getElementById('id07').style.display='block'"
          style="width:auto;"
          @click="closeAll()"
          v-if="accesso === 'A' || accesso === 'B'"
        >Rimuovi voce di bilancio</b-dropdown-item>
        <b-dropdown-item
          onclick="document.getElementById('id08').style.display='block'"
          style="width:auto;"
          @click="closeAll()"
          v-if="accesso === 'A' || accesso === 'B'"
        >Inserisci voce in bilancio</b-dropdown-item>
        <b-dropdown-item
          onclick="document.getElementById('id09').style.display='block'"
          style="width:auto;"
          @click="closeAll()"
          v-if="accesso === 'A' || accesso === 'B'"
        >Inserisci importo bilancio</b-dropdown-item>
      </b-dropdown>

      <b-dropdown
        id="ddown-offset"
        offset="25"
        text="Modifica opzioni"
        variant=" btn-outline-light btn-block"
        class="m-2 w-15"
        v-if="accesso === 'A' || accesso === 'B'"
      >
        <b-dropdown-item href="#">Area amministratore</b-dropdown-item>
      </b-dropdown>

      <b-dropdown
        id="ddown-offset"
        offset="25"
        text="Visualizza file log"
        variant=" btn-outline-light"
        class="m-2 w-15"
        v-if="accesso === 'A' || accesso === 'B'"
      >
        <b-dropdown-item href="#">Registro attivita</b-dropdown-item>
      </b-dropdown>

      <b-button
        offset="25"
        text="Logout"
        variant=" btn-block"
        class="m-2 w-15 logout btn-outline-light btn-block"
        @click="logout()"
      >Logout</b-button>
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
    <div id="id06" class="modal">
      <AggiungiVoce/>
    </div>
    <div id="id07" class="modal">
      <RimuoviVoce/>
    </div>
    <div id="id08" class="modal">
      <AggiungiVoceBilancio/>
    </div>
    <div id="id09" class="modal">
      <InserisciImportoBilancio/>
    </div>
    </body>
  </div>
</template>

<script>
import { AXIOS } from "./http-common";
import RegistraUtente from "./RegistraUtente";
import CambiaPassword from "./CambiaPassword";
import AggiornaUtente from "./AggiornaUtente";
import ListaSoci from "./ListaSoci";
import VisualizzaBilancio from "./VisualizzaBilancio";
import AggiungiVoce from "./AggiungiVoce";
import AggiungiVoceBilancio from "./AggiungiVoceBilancio";
import InserisciImportoBilancio from "./InserisciImportoBilancio";
import RimuoviVoce from "./RimuoviVoce";
export default {
  name: "service",

  data() {
    return {
      response: [],
      errors: [],
      modal: "id01",
      modal: "id02",
      modal: "id03",
      modal: "id04",
      modal: "id05",
      modal: "id06",
      modal: "id07",
      modal: "id08",
      modal: "id09",
      accesso: null,
      all: ["id01", "id02", "id03", "id04", "id05", "id06", "id07", "id08", "id09"]
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
    AggiungiVoce,
    AggiungiVoceBilancio,
    InserisciImportoBilancio,
    RimuoviVoce
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
    logout() {
      this.accesso = null;
      window.location.href="/#/";
    },
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
.flex {
  display: flex;
  justify-content: center;
}

.test > .btn-group > .dropdown-menu:active {
  display: block;
  margin-bottom: 5px;
  position: static;
}
#ddown-offset {
  height: 50px;
}
body {
  margin-top: 2%;
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
body {
  font-family: Arial, Helvetica, sans-serif;
  size: 100%;
}
* {
  box-sizing: border-box;
}

/* The Modal (background) */
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 12.5%;
  width: 100%;
  height: 100%;
  overflow: auto;
  background-color: #474e5d;
  padding-top: 0px;
}

/* Modal Content/Box */
.modal-content {
  background-color: #fefefe;
  margin: 5% auto 15% auto;
  border: 1px solid #888;
  width: 80%;
}

.logout {
  width: auto;
}
</style>
