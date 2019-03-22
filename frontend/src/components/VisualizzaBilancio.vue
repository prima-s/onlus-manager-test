<template>
  <form class="modal-content" style="width:60%;" action>
    <span @click="closeAll()" class="close" title="Close Modal">&times;</span>
    <div class="container">
      <h1>Bilancio</h1>
      <hr>

      <h5 class="text-center">Bilancio attuale: {{bilancio}}</h5>
      <br>

  
    </div>
    <div class="row">
      <div class="col-md-6">
      <button @click="closeAll()" class="cancelbtn btn-block">Annulla</button>
      </div>
      <div class="col-md-6">
     <button type="submit" @click="addBilancio()" class="btn-success btn-block">Scarica PDF</button>
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
      success: false
    };
  },
  created() {
    AXIOS.get(`/bilancio`)
      .then(response => {
        // JSON responses are automatically parsed.
        this.bilancio = response.data;
        console.log(response.data);
      })
      .catch(e => {
        this.errors.push(e);
      });
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

/* Float cancel and signup buttons and add an equal width */

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
