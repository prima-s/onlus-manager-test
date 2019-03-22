<template>
  <form class="modal-content" action>
    <span @click="closeAll()" class="close" title="Close Modal">&times;</span>
    <div class="container">
      <h1>Cambia password</h1>
      <hr>
      <div class="row">
        <div class="col-md-6">
          <label for="password">
            <b>Vecchia password*</b>
          </label>
          <div style="width:100%">
            <input
              type="password"
              class="testo"
              placeholder="xxxxxxOLDxxxxxxxx"
              name="oldPass"
              v-model="oldPassword"
              required
            >
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-6">
          <label for="password">
            <b>Nuova password*</b>
          </label>
          <br>
          <input
            type="password"
            placeholder="xxxxxxNEWxxxxxxxx"
            name="newPass"
            v-model="newPassword"
            required
          >
        </div>
        <div class="col-md-6">
          <label for="password">
            <b>Ripeti password*</b>
          </label>
          <br>
          <input
            type="password"
            placeholder="xxxxxxNEWxxxxxxxx"
            name="repeatPass"
            v-model="repeatPassword"
            @input="activeButton()"
            @mouseleave="checkPasswordMatching()"
            required
          >
          <p :class="{'notMatching' : message != null}">{{message}}</p>
        </div>
      </div>
      <br>

  

    </div>
   
    <div class="row">
      <div class="col-md-6">
      <button @click="closeAll()" class="cancelbtn btn-block">Annulla</button>
      </div>
      <div class="col-md-6">
      <button
            type="submit"
            @click="updatePassword()"
            :class="{'signupbtn btn-block' : passwordOk, 'signupbtn disabled' : !passwordOk}"
            value="aggiorna"
            :disabled="passwordOk === false"
          >Aggiorna</button>
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
      oldPassword: "",
      newPassword: "",
      repeatPassword: "",
      message: null,
      passwordOk: false
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
    checkPasswordMatching() {
      if (
        this.newPassword !== this.repeatPassword ||
        this.newPassword === null
      ) {
        this.message = "Password not matching!";
      } else {
        this.message = null;
      }
    },
    activeButton() {
      if (
        this.newPassword !== this.repeatPassword ||
        this.newPassword === null
      ) {
        this.passwordOk = false;
      } else {
        this.passwordOk = true;
      }
    },
    updatePassword() {
      var params = new URLSearchParams();
      params.append("oldPassword", this.oldPassword);
      params.append("newPassword", this.newPassword);
      AXIOS.post(`/aggiornaPassword`, params)
        .then(response => {
          this.response = response.data;
          console.log(response.data);
        })
        .catch(e => {
          this.errors.push(e);
        });
    },
    onclick: function(event) {
      if (event.target == modal) {
        modal.style.display = "none";
      }
    }
  }
};
</script>


<style>
/* Full-width input fields */
input {
  width: 100%;
  padding: 15px;
  display: inline-block;
  border: none;
  border: none;
  background: #f1f1f1;
}
/* Add a background color when the inputs get focus */
input[type="password"]:focus {
  background-color: #ddd;
  outline: none;
}
.disabled {
  opacity: 0.3;
}
.disabled:hover {
  opacity: 0.3;
}
.notMatching {
  padding: 3px;
  background: orangered;
  color: #f1f1f1;
  text-align: center;
}
/* Float cancel and signup buttons and add an equal width */

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

.numeri {
  margin: 5px 10px 22px 0 !important;
  width: 48% !important;
  padding: 15px !important;
  display: inline-block !important;
  border: none !important;
  border: none !important;
  background: #f1f1f1 !important;
}
.numeri:focus {
  background-color: #ddd;
  outline: none;
}
input[name="CF"] {
  width: 50% !important;
}
</style>


