<template>
  <form class="modal-content" action>
    <span @click="closeAll()" class="close" title="Close Modal">&times;</span>
    <div class="container">
      <h1>Inserisci Voce</h1>
      <hr>

      <label for="nome">
        <b>Nome Voce</b>
        <p>(Inserendo un nome non presente nella lista dei suggerimenti questo verrà automaticamente aggiunto alla lista per le prossime operazioni di inserimento voce)</p>
      </label>
      <br>
      <div class="autocomplete">
        <input
          type="text"
          v-model="voce.nomeVoce"
          placeholder="Nome voce"
          @input="onChange"
          required
        >
        <ul v-show="isOpen" class="autocomplete-results">
          <li
            v-for="(result, i) in filteredVoci"
            :key="i"
            @click="setResult(result)"
            class="autocomplete-result"
          >{{result}}</li>
        </ul>
      </div>

      <br>
      <br>
      <div class="row">
        <div class="col-md-6">
          <label for="data">
            <b>Data</b>
          </label>
          <br>
          <input
            type="date"
            class="testo"
            placeholder="Data riferimento"
            name="data"
            v-model="voce.dataVoce"
            required
          >
        </div>
        <div class="col-md-6">
          <label for="importo">
            <b>Importo</b>
          </label>
          <br>
          <input
            type="number"
            class="testo"
            placeholder="Importo"
            name="importo"
            v-model="voce.importoVoce"
            required
          >
        </div>
      </div>

      <label for="note" style="padding-right:20px;">
        <b>Note</b>
      </label>
      <br>
      <textarea
        style="width:100%"
        class="testo"
        placeholder="Campo note"
        name="note"
        v-model="voce.noteVoce"
      ></textarea>
    </div>
    <div class="row">
      <div class="col-md-6">
        <button @click="closeAll()" class="cancelbtn btn-block">Annulla</button>
      </div>
      <div class="col-md-6">
        <button
          type="submit"
          @mouseenter="checkImporto()"
          @click="inserisciVoce()"
          class="signupbtn btn-block"
        >Inserisci</button>
      </div>
    </div>
  </form>
</template>

<script>
import { AXIOS } from "./http-common";
export default {
  data() {
    return {
      response: [],
      errors: [],
      voce: {
        nomeVoce: "",
        dataVoce: "",
        importoVoce: 0,
        noteVoce: ""
      },
      importOk: false,
      success: false,
      selected: "",
      results: this.fetchedResults,
      fetchedResults: [],
      isOpen: false
    };
  },
  created() {
    AXIOS.get(`/voci`)
      .then(response => {
        this.fetchedResults = response.data;
        console.log("created");
      })
      .catch(e => {
        this.errors.push(e);
      });
  },
  computed: {
    filteredVoci() {
      let filter = new RegExp(this.voce.nomeVoce, "i");
      return this.fetchedResults.filter(el => el.match(filter));
    }
  },
  methods: {
    onChange() {
      this.isOpen = true;
      if (this.filteredVoci.length < 1) {
        this.isOpen = false;
      }
    },
    setResult(result) {
      this.voce.nomeVoce = result;
      this.isOpen = false;
    },
    closeAll() {
      for (
        var i = 0;
        i < document.getElementsByClassName("modal").length;
        i++
      ) {
        document.getElementsByClassName("modal")[i].style.display = "none";
      }
    },
    checkImporto() {
      if (this.voce.importoVoce == 0 && this.importOk == false) {
        confirm(
          "Sei sicuro di voler inserire una voce di bilancio con importo pari a 0?"
        );
        this.importOk = true;
      }
    },
    inserisciVoce() {
      var params = new URLSearchParams();
      params.append("nomeVoce", this.voce.nomeVoce);
      params.append("dataVoce", this.voce.dataVoce);
      params.append("importoVoce", this.voce.importoVoce);
      params.append("noteVoce", this.voce.noteVoce);
      AXIOS.post(`/inserisciVoce`, params)
        .then(response => {
          // JSON responses are automatically parsed.
          this.response = response.data;
          console.log(response.data);
          this.success = true;
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

/* Change styles for cancel button and signup button on extra small screens */
@media screen and (max-width: 300px) {
  .cancelbtn,
  .signupbtn {
    width: 100%;
  }
}

.testo {
  width: 100% !important;
  padding: 15px;
  margin: 5px 0 22px 0;
  display: inline-block;
  border: none;
  background: #f1f1f1;
}

/* Add a background color when the inputs get focus */
.testo:focus {
  background-color: #ddd;
  outline: none;
}

.autocomplete {
  position: relative;
}

.autocomplete-results {
  padding: 0;
  margin: 0;
  border: 1px solid #eeeeee;
  height: 120px;
  overflow: auto;
  width: 100%;
}

.autocomplete-result {
  list-style: none;
  text-align: left;
  padding: 4px 2px;
  cursor: pointer;
}

.autocomplete-result.is-active,
.autocomplete-result:hover {
  background-color: #4aae9b;
  color: white;
}
</style>