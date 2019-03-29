<template>
  <form class="modal-content" action>
    <span @click="closeAll()" class="close" title="Close Modal">&times;</span>
    <div class="container">
      <h1>Rimuovi Voce Bilancio</h1>
      <hr>
      <br>
      <label for="nome">
        <b>Nome Voce (seleziona la voce che desideri rimuovere da quelle di default)</b>
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
            v-for="(result, i) in filteredVoci"
            :key="i"
            @click="setResult(result)"
            class="autocomplete-result"
          >{{result}}</li>
        </ul>
      </div>

      <br>
    </div>

    <div class="row">
      <div class="col-md-6">
        <button @click="closeAll()" class="cancelbtn btn-block">Annulla</button>
      </div>
      <div class="col-md-6">
        <button type="submit" @click="rimuoviVoce()" class="signupbtn btn-block">Rimuovi</button>
      </div>
    </div>
  </form>
</template>

<script scoped>
import { AXIOS } from "./http-common";
import { closeMixin } from "./close-mixin";
import { filterVociMixin } from "./filterVociMixin";

export default {
  mixins: [closeMixin, filterVociMixin],
  data() {
    return {
      response: [],
      errors: [],
      voce: {
        nomeVoce: ""
      }
    };
  },
  methods: {
    rimuoviVoce() {
      var params = new URLSearchParams();
      params.append("voce", this.voce.nomeVoce);

      AXIOS.post(`/rimuoviVoce`, params)
        .then(response => {
          this.response = response.data;
          console.log(response.data);
        })
        .catch(e => {
          this.errors.push(e);
        });
    }
  }
};
</script>

<style>
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