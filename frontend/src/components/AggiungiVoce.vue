<template>
  <form class="modal-content" style="width:60%;" action>
    <span @click="closeAll()" class="close" title="Close Modal">&times;</span>
    <div class="container">
      <h1>Aggiungi Voce</h1>
      <hr>

      <label for="voci" style="padding-right:20px;">
        <b>Nuova Voce Bilancio</b>
      </label>
      
      <input
        type="text"
        class="testo"
        placeholder="Nome nuovo bilancio"
        name="NewVoce"
        v-model="voce"
        required
      >

      <div class="row">
        <div class="col-xs-6 col-sm-6 col-md-6">
          <button @click="closeAll()" class="cancelbtn btn-block">Annulla</button>
        </div>
        <div class="col-xs-6 col-sm-6 col-md-6">
          <button type="submit" @click="addVoce()" class="btn-success btn-block">Aggiungi</button>
        </div>
      </div>
    </div>
    <div v-if="success">
      <h3>
        <strong>{{voce}}</strong> è stata inserita tra le voci di bilancio.
      </h3>
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
      voce: "",
      success: false
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

    addVoce() {
      var params = new URLSearchParams();
      params.append("voce", this.voce);
      AXIOS.post(`/aggiungiVoce`, params)
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
}

button:hover {
  opacity: 1;
}

/* Extra styles for the cancel button */
.cancelbtn {
  padding: 14px 20px;
  background-color: #f44336;
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

.testo {
  width: 40%;
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
</style>
