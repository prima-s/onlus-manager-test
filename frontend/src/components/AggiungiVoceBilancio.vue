<template>
  <form class="modal-content" action>
    <span @click="closeAll()" class="close" title="Close Modal">&times;</span>
    <div class="container">
      <h1>Inserisci Voce</h1>
      <hr>

      <label for="nome">
        <b>Nome Voce</b>
      </label>
      <br>
      <div class="autocomplete">
        <input
          type="text"
          v-model="voce.nomeVoce"
          placeholder="Nome voce"
          @input="onChange()"
          required
        >
        <ul v-show="isOpen" class="autocomplete-results">
          <li
            v-for="(result, i) in results"
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
  beforeMount(){
console.log("before mount");
  },
  mounted(){
console.log("mounted");
  },
  beforeUpdate(){
console.log("before update");
  },
  updated(){
console.log("updated");
  },
  activated(){
console.log("activated");
  },
  deactivated(){
console.log("deactivated");
  },
  beforeDestroy(){
console.log("before destroyed :(");
  },
   destroyed() {
      console.log("I've been destroyed :(");
    },
  methods: {
    onChange() {
      if (this.voce.nomeVoce.length < 3) {
        this.results = this.fetchedResults;
      }

      this.isOpen = true;
      this.filterResults();
      if (this.results.length < 1) {
        this.isOpen = false;
      }
    },
    filterResults() {
      this.results = this.results.filter(
        item =>
          item.toLowerCase().indexOf(this.voce.nomeVoce.toLowerCase()) > -1
      );
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

<style>
/* Set a style for all buttons */

button {
  background-color: #4caf50;
  color: white;
  padding: 14px 20px;
  border: none;
  cursor: pointer;
  width: 100%;
  opacity: 0.9;
  height: 60px;
}

button:hover {
  opacity: 1;
}

/* Extra styles for the cancel button */
.cancelbtn {
  padding: 14px 20px;
  background-color: #f44336;
  border-top-right-radius: 1rem;
}
.signupbtn {
  border-top-left-radius: 1rem;
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
  font-size: 40px !important;
  font-weight: bold;
  color: black !important;
}

.close:hover,
.close:focus {
  color: #f44336 !important;
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