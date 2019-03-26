<template>
  <form class="modal-content" style="width:60%;" action>
    <span @click="closeAll()" class="close" title="Close Modal">&times;</span>
    <div class="container">
      <h1>Bilancio</h1>
      <hr>
      <div>
        <label for="range-1">
          <b>Seleziona anno</b>
        </label>
        <input
          type="range"
          class="custom-range"
          id="range-1"
          v-model="annoCorrente"
          :min="this.minAnno"
          :max="this.maxAnno"
          @input="updateBilancioAnnuale()"
        >
        <br>
        <br>
      </div>
      <div class="row" id="listaVoci">
        <div class="col-md-8">
          <h4 :class="{'text-center consolidato' : consolidato, 'text-center provvisorio' : !consolidato}">Bilancio per l'anno {{annoCorrente}}
          </h4>
        </div>
        <div class="col-md-4">
          <h4>{{bilancio}} €</h4>
        </div>
      </div>

      <br>

      <div v-if="rows > 0">
        <div class="row">
          <div class="col-md-12">
            <div>
              <b-table
                striped
                responsive
                hover
                id="vociTable"
                :items="voci"
                :fields="fields"
                :per-page="perPage"
                :current-page="currentPage"
              />
            </div>
          </div>
        </div>

        <div class="row">
          <div class="col-md-12">
            <b-pagination
              v-model="currentPage"
              :total-rows="rows"
              :per-page="perPage"
              aria-controls="vociTable"
            />
          </div>
        </div>
      </div>
      <div class="noData" v-else>
        <b>Nessuna voce presente</b>
      </div>
      <br>

      <button type="submit" @click="showBilancioTotale()" class="btn-block">Mostra Bilancio Totale</button>
      <br>
      <div class="row" v-if="totaleTrue">
        <div class="col-md-8">
          <h4 class="text-center">Bilancio Totale</h4>
        </div>
        <div class="col-md-4">
          <h4>{{bilancioTotale}} €</h4>
        </div>
      </div>
    </div>

    <br>
    <div class="row">
      <div class="col-md-6">
        <button @click="closeAll()" class="cancelbtn btn-block">Annulla</button>
      </div>
      <div class="col-md-6">
        <button type="submit" @click="savePDF()" class="signupbtn btn-block">Scarica PDF</button>
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
      bilancio: 0,
      bilancioTotale: 0,
      totaleTrue: false,
      annoCorrente: 2018,
      consolidato: true,
      minAnno: 2014,
      maxAnno: new Date().getFullYear(),
      voci: [],
      fields: [
        {
          key: "nomeVoce",
          label: "Nome Voce",
          sortable: false
        },
        {
          key: "sommaAssociata",
          label: "Importo",
          sortable: true
        },
        {
          key: "dataRiferimento",
          label: "Data",
          sortable: true
        }
      ],
      perPage: 7,
      currentPage: 1
    };
  },
  created() {
    var params = new URLSearchParams();
    params.append("anno", this.annoCorrente);
    AXIOS.post(`/bilancio`, params)
      .then(response => {
        this.bilancio = response.data;
        console.log(response.data);
      })
      .catch(e => {
        this.errors.push(e);
      });
  },
  updated() {
    if (this.annoCorrente == this.maxAnno) {
        this.consolidato = false;
      } else {
        this.consolidato = true;
      }
  },
  beforeMount() {
    var params = new URLSearchParams();
    params.append("anno", this.annoCorrente);
    AXIOS.post(`/vociBilancioAnnuale`, params)
      .then(response => {
        this.voci = response.data;
        console.log(response.data);
      })
      .catch(e => {
        this.errors.push(e);
      });
  },
  watch: {
    annoCorrente(value) {
      var params = new URLSearchParams();
      params.append("anno", this.annoCorrente);
      AXIOS.post(`/vociBilancioAnnuale`, params)
        .then(response => {
          this.voci = response.data;
          console.log(response.data);
        })
        .catch(e => {
          this.errors.push(e);
        });
    }
  },
  computed: {
    rows() {
      return this.voci.length;
    }
  },
  methods: {
    showBilancioTotale() {
      AXIOS.get(`/bilancioTotale`)
        .then(response => {
          this.bilancioTotale = response.data;
          this.totaleTrue = true;
          console.log(response.data);
        })
        .catch(e => {
          this.errors.push(e);
        });
    },
    updateBilancioAnnuale() {
      var params = new URLSearchParams();
      params.append("anno", this.annoCorrente);
      AXIOS.post(`/bilancio`, params)
        .then(response => {
          this.bilancio = response.data;
          console.log(response.data);
        })
        .catch(e => {
          this.errors.push(e);
        });
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
    onclick: function(event) {
      if (event.target == modal) {
        modal.style.display = "none";
      }
    },
    savePDF() {
      var doc = new jsPDF();
      let title = "BILANCIO PER L'ANNO " + this.annoCorrente + "\n";
      let total = "Totale: " + this.bilancio + "€\n";
      let list = "";
      let pdf = "";

      for (var i = 0; i < this.voci.length; i++) {
        list +=
          this.voci[i]["nomeVoce"] +
          " - " +
          this.voci[i]["sommaAssociata"] +
          " - " +
          this.voci[i]["dataRiferimento"] +
          "\n";
      }
      pdf += title + "\n";
      pdf += list + "\n";
      pdf += total;
      let splitList = doc.splitTextToSize(pdf, 180);
      doc.text(splitList, 10, 10);

      doc.save("Bilancio" + this.annoCorrente + ".pdf");
    },
    mouseOver: function() {
      this.active = !this.active;
    }
  }
};
</script>

<style scoped>
/* Set a style for all buttons */
.pagination {
  justify-content: center;
}

.noData {
  color: rgba(99, 99, 99, 0.685);
  text-align: center;
}

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
button:focus {
  outline: none !important;
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

/* Add a background color when the inputs get focus */
.testo:focus {
  background-color: #ddd;
  outline: none;
}

.custom-range::before {
  content: attr(min);
}
.custom-range::after {
  content: attr(max);
}
.custom-range {
  height: 0;
}
.consolidato::after, .provvisorio::after {
  position: absolute;
  color: white;
  padding: 5px;
  border: 1px solid whitesmoke;
  border-radius: 15px;
  font-size: 14px;
  top: 1px;
  margin-left: 5px;
}
.consolidato::after {
  content: "Approvato";
  background-color: #74bd76;
}
.provvisorio::after {
  content: "Provvisorio";
  background-color: #be4f5e;
  opacity: .7;
}
</style>
