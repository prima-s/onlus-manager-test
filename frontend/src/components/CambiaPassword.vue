<template>
  <div class="modal-content">
    <form action :class="{ '' : (!success && !error), 'opac' : (success || error) }">
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
            @click.prevent="updatePassword()"
            :class="{'signupbtn btn-block' : passwordOk, 'signupbtn disabled' : !passwordOk}"
            value="aggiorna"
            :disabled="passwordOk === false"
          >Aggiorna</button>
        </div>
      </div>
    </form>

    <transition name="formSuccess">
      <div v-if="response === true" class="row">
        <div class="col-md-12 text-center no-height">
          <i style="transform: translateY(-35vh);" class="fas fa-check-circle"></i>
        </div>
      </div>

      <div v-if="response === false" class="row">
        <div class="col-md-12 text-center no-height flex-col">
          <i style="transform: translateY(-35vh);" class="fas fa-times">
            <div class="errorMessage">{{errorMessage}}</div>
            </i>
        </div>
      </div>
    </transition>
  </div>
</template>


<script>
import { AXIOS } from "./http-common";
import { closeMixin } from "./close-mixin";

export default {
  mixins: [closeMixin],
  data() {
    return {
      response: [],
      errors: [],
      oldPassword: "",
      newPassword: "",
      repeatPassword: "",
      message: null,
      passwordOk: false,
      success: null,
      error: null,
      errorMessage: null
    };
  },

  methods: {
    resetData() {
      this.oldPassword = "";
      this.newPassword = "";
      this.repeatPassword = "";
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
    updatePassword(e) {
      var params = new URLSearchParams();
      params.append("oldPassword", this.oldPassword);
      params.append("newPassword", this.newPassword);
      AXIOS.post(`/aggiornaPassword`, params)
        .then(response => {
          this.response = response.data;
          if (this.response === false) {
            this.errorMessage =
              "La password '" + this.oldPassword + "' non è stata trovata!";
          }
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
</style>


