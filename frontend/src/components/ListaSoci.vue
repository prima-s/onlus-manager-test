<template>
    <form class="modal-content" action="">
        <span  @click="closeAll()" class="close" title="Close Modal">&times;</span>
    <div class="container">
      <h1>Lista soci</h1>
      <hr> 
 <br>
 	<div id="dubkovidze">
    <b-table striped hover :items="soci" :fields="fields" />
  </div>
    </div>     
 <br>
     		
		
		<br/><br/>
		
            <button @click="createPDF()" class=" cancelbtn btn-block">Download PDF</button>
            <button @click="callRestService()" class=" cancelbtn btn-block">Retrieve table</button>
						<button @click="closeAll()" class=" cancelbtn btn-block">Cancel</button>
					
          

				
</form>
</template>
<script scoped>
import { AXIOS } from "./http-common";
export default {

  data() {
    return {
      msg: "HowTo call REST-Services:",
      response: [],
      errors: [],
      value: 75,
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
   mounted() {
    let recaptchaScript = document.createElement('script');
    recaptchaScript.setAttribute('src', 'https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.5.3/jspdf.debug.js');
    document.head.appendChild(recaptchaScript);
    /*var recaptchaScript2 = document.createElement('script2');
    recaptchaScript2.setAttribute('src2', 'https://cdnjs.cloudflare.com/ajax/libs/jspdf-autotable/3.0.13/jspdf.plugin.autotable.min.js');
    document.head.appendChild(recaptchaScript2)*/
    },
   
  methods: {
       closeAll(){
      for(var i=0;i<document.getElementsByClassName('modal').length;i++){
        document.getElementsByClassName('modal')[i].style.display='none';
      }
    },
    createPDF () {
    /*var pdf = new jsPDF("p", "pt", "a4");
	  pdf.addHTML($('#dubkovidze'), 15, 15, function() {
    pdf.save('dubkovidze.pdf');});*/
    
    /*let pdfName = 'test'; 
    let tabella=document.getElementById('dubkovidze');
    var doc = new jsPDF();
    doc.text(tabella, 10, 10);
    doc.save(pdfName + '.pdf');*/
    /*let vm=this;
      let columns=[
        {title:'nome', dataKey:'nome'},
        {title:'cognome',dataKey:'cognome'}


      ],
      var doc=new jsPDF('p','pt');
      doc.autoTable(columns,v*/
    let pdfName = 'test'; 
    var doc = new jsPDF();
    for (var i=0;i<this.soci.length;i++){
    doc.text(this.soci[i]['nome'] + '  ' + this.soci[i]['cognome'], 10, 10 + 10*i)
    }
    doc.save(ListaDeiSoci + '.pdf');
    /*doc.text(this.soci[0]['nome'], 10, 10);
    doc.save(pdfName + '.pdf');*/
    
      /*let vm=this;
      let columns=[
        {title:'nome', dataKey:'nome'},
        {title:'cognome',dataKey:'cognome'}


      ],
      var doc=new jsPDF('p','pt');
      doc.autoTable(columns,vm.info);
      doc.save('info.pdf');*/
  },
    
    // Fetches posts when the component is created.
    callRestService() {
      AXIOS.get(`/soci`)
        .then(response => {
            this.soci=response.data;
          })
          .catch(e => {
            this.errors.push(e)
          })
    },
    onclick:function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}
  }
  
};
</script>
<style scoped>
button {
  position: absolute;
  padding: 14px 20px;
  margin: 8px 25% ;
  border: none;
  cursor: pointer;
  width: 50% ;
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
.cancelbtn{
  position: relative;
  padding-left:30;
  width: 50%;
}
</style>

