<template>
<div class="modal-content">
  <form action :class="{ '' : (!success && !error), 'opac' : (success || error) }">
    <span @click="closeAll()" class="close" title="Close Modal">&times;</span>
    <div class="container">
      <h1>Inserisci Voce Bilancio</h1>
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
            step="0.01"
            name="importo"
            v-model="voce.importoVoce"
            required
          >
        </div>
      </div>
  <br>
      <label for="note" style="padding-right:20px;">
        <b>Note</b>
      </label>
      <br>
      <textarea
        style="width:100%"
        class="form-control"
        placeholder="Campo note"
        name="note"
        v-model="voce.noteVoce"
      ></textarea>
    </div>
    <br>
    <div class="row">
      <div class="col-md-6">
        <button @click="closeAll()" class="cancelbtn btn-block">Annulla</button>
      </div>
      <div class="col-md-6">
        <button
          type="submit"
          @mouseenter="checkImporto()"
          @click.prevent="inserisciVoce()"
          class="signupbtn btn-block"
        >Inserisci</button>
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
import { filterVociMixin } from "./filterVociMixin";

export default {
  mixins: [closeMixin, filterVociMixin],
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
      error: false
    };
  },
   methods: {
     resetData() {
       this.voce.nomeVoce = "";
       this.voce.dataVoce = "";
       this.voce.importoVoce = 0;
       this.voce.noteVoce = "";
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
          this.response = response.data;
          console.log(response.data);
          this.success = true;
          this.responseAfterSubmit();
          this.resetData();
        })
        .catch(e => {
          this.error = true;
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

/* Change styles for cancel button and signup button on extra small screens */
@media screen and (max-width: 300px) {
  .cancelbtn,
  .signupbtn {
    width: 100%;
  }
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