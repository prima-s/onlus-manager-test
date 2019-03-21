<template>
 <form class="modal-content" style="width:60%;" action>
    <span
      @click="closeAll()"
      class="close"
      title="Close Modal"
    >&times;</span>
      <div class="container">
      <h1>Inserisci Voce</h1>
      <hr>

<label for="tipo" style="padding-right:20px;" ><b>Tipo Voce</b></label>
<br>
<select v-model="voce.tipoVoce" name="tipo">
  <option disabled value="">Please select one</option>
  <option>Tesseramento</option>
  <option>Donazione</option>
  <option>Acquisto Materiali</option>
</select>

<br>
<br>
<div class="row">
<label for="data" style="padding-right:20px;margin-left:7.5%;" ><b>Data</b></label>
<label for="importo" style="padding-right:20px; margin-left:36.4%;" ><b>Importo</b></label>
</div>

<div style="width:100%">
  <input type="date" style="margin-left:5.4%;" class="testo" placeholder="Data riferimento" name="data" v-model="voce.dataVoce" required>
   <input type="number" style="margin-left:5.4%;" class="testo" placeholder="Importo" name="importo" v-model="voce.importoVoce" required>
   </div>


    <label for="note" style="padding-right:20px;" ><b>Note</b></label>
<br>
        <textarea style="width:100%" class="testo" placeholder="Campo note" name="note" v-model="voce.noteVoce" required></textarea>


<div class="row">

					<div class="col-xs-6 col-sm-6 col-md-6">
						<button @click="closeAll()" class=" cancelbtn btn-block">Annulla</button>
					</div>
          	<div class="col-xs-6 col-sm-6 col-md-6">
                        <button type="submit" @click="inserisciVoce()" class=" btn-success btn-block">Inserisci</button>
					</div>

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
         tipoVoce: '',
         dataVoce: '',
         importoVoce: 0,
         noteVoce: ''
      },
      success: false,
      selected: ''
    };
  },

  methods: {
      closeAll(){
      for(var i=0;i<document.getElementsByClassName('modal').length;i++){
        document.getElementsByClassName('modal')[i].style.display='none';
      }
    },


    inserisciVoce() {
        var params = new URLSearchParams();
         params.append("tipoVoce", this.voce.tipoVoce);
         params.append("dataVoce", this.voce.dataVoce);
         params.append("importoVoce", this.voce.importoVoce);
         params.append("noteVoce", this.voce.noteVoce);
      AXIOS.post(`/inserisciVoce`, params)
        .then(response => {
          // JSON responses are automatically parsed.
          this.response = response.data
          console.log(response.data)
          this.success = true
        })
        .catch(e => {
          this.errors.push(e)
        });
    },
    onclick:function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
},

    mouseOver: function () {
      this.active = !this.active;
    }

  },

  };
</script>

<style>


/* Set a style for all buttons */
button {
  background-color: #4CAF50;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
  opacity: 0.9;
}

button:hover {
  opacity:1;
}

/* Extra styles for the cancel button */
.cancelbtn {
  padding: 14px 20px;
  background-color: #f44336;
}

/* Float cancel and signup buttons and add an equal width */
.cancelbtn, .signupbtn {
  float: left;
  width: 50%;
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
  color: #f1f1f1;
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
  .cancelbtn, .signupbtn {
     width: 100%;
  }
}

.testo {
  width: 40%;
  padding:15px;
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
</style>