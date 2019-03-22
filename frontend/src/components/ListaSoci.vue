<template>
    <form class="modal-content" action="">
        <span  @click="closeAll()" class="close" title="Close Modal">&times;</span>
    <div class="container">
      <h1>Lista soci</h1>
      <hr>
   <a href="#" @click="createPDF()" ><i class="far fa-file-pdf"></i>Scarica Pdf ListaSoci </a>
 <br>
 	<div id="sociTable">
    <b-table striped hover :items="soci" :fields="fields" />
  </div>
    </div>
 <br>


		<br/><br/>

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
export default {
  data() {
    return {
      response: [],
      errors: [],
      fields: [
          {
            key: 'numTessera',
            label:'Numero di tessera',
            sortable:false
          },
          {
            key: 'nome',
            label: 'Nome',
            sortable: false
          },
          {
            key: 'cognome',
            label: 'Cognome',
            sortable: true
          }
         ],
      soci:null
    };
  },
  created() {
       AXIOS.get(`/soci`)
               .then(response => {
                   this.soci=response.data;
                 })
                 .catch(e => {
                   this.errors.push(e)
                 })
  },
   mounted() {
    let recaptchaScript = document.createElement('script');
    recaptchaScript.setAttribute('src', 'https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.5.3/jspdf.debug.js');
    document.head.appendChild(recaptchaScript);
    },
  methods: {
       closeAll(){
      for(var i=0;i<document.getElementsByClassName('modal').length;i++){
        document.getElementsByClassName('modal')[i].style.display='none';
      }
    },
    createPDF () {

    let pdfName = 'test';
    var doc = new jsPDF();
    for (var i=0;i<this.soci.length;i++){
    doc.text(
    this.soci[i]['numTessera']  + ' -   ' + this.soci[i]['nome'] + '   ' + this.soci[i]['cognome'], 10, 10 + 10*i)
    }
    doc.save('ListaSoci' + '.pdf');
  },

    // Fetches posts when the component is created.
    retrieveTable() {
      AXIOS.get(`/soci`)
        .then(response => {
            this.soci=response.data;
          })
          .catch(e => {
            this.errors.push(e)
          })
    }

  }

};
</script>
<style scoped>


</style>