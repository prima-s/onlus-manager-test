<template>
  <form class="modal-content" action>
    <span @click="closeAll()" class="close" title="Close Modal">&times;</span>
    <div class="container">
      <h1>Lista soci</h1>
      <hr>
      <a href="#" @click="createPDF()">
        <i class="far fa-file-pdf"></i>Scarica Pdf ListaSoci
      </a>
      <br>
      <div id="sociTable">
        <b-table striped responsive hover :items="soci" :fields="fields"/>
      </div>
    </div>
    <br>

    <br>
    <br>

    <div class="row">
      <div class="col-md-6">
        <button @click="closeAll()" class="cancelbtn btn-block">Annulla</button>
      </div>
      <div class="col-md-6">
        <button @click="createPDF()" class="signupbtn btn-block">Download PDF</button>
      </div>
    </div>
  </form>
</template>
<script scoped>
import { AXIOS } from "./http-common";
import { closeMixin } from "./close-mixin";

export default {
  mixins: [closeMixin],
  data() {
    return {
      response: [],
      errors: [],
      fields: [
        {
          key: "numTessera",
          label: "Numero di tessera",
          sortable: false
        },
        {
          key: "nome",
          label: "Nome",
          sortable: false
        },
        {
          key: "cognome",
          label: "Cognome",
          sortable: true
        }
      ],
      soci: null
    };
  },
  created() {
    AXIOS.get(`/soci`)
      .then(response => {
        this.soci = response.data;
      })
      .catch(e => {
        this.errors.push(e);
      });
  },
  methods: {
    createPDF() {
      var doc = new jsPDF();
      let pdfName = "Lista Soci";
      let list = "";
      let pdf = "";
      for (var i = 0; i < this.soci.length; i++) {
        list +=
          this.soci[i]["numTessera"] +
          "   -   " +
          this.soci[i]["nome"] +
          "    " +
          this.soci[i]["cognome"] +
          "\n";
      }
      pdf += pdfName + "\n\n";
      pdf += list;
      let splitList = doc.splitTextToSize(pdf, 180);
      doc.text(splitList, 10, 10);
      doc.save("ListaSoci" + ".pdf");
    },

    // Fetches posts when the component is created.
    retrieveTable() {
      AXIOS.get(`/soci`)
        .then(response => {
          this.soci = response.data;
        })
        .catch(e => {
          this.errors.push(e);
        });
    }
  }
};
</script>
<style scoped>
</style>