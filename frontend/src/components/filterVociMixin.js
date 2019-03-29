import { AXIOS } from "./http-common";

export const filterVociMixin = {
    data() {
        return {
          fetchedResults: [],
          isOpen: false,
        };
      },
    created() {
        AXIOS.get(`/voci`)
          .then(response => {
            this.fetchedResults = response.data;
            console.log("created");
          })
          .catch(e => {
            this.errors.push(e);
          });
      },
      computed: {
        filteredVoci() {
          let filter = new RegExp(this.voce.nomeVoce, "i");
          return this.fetchedResults.filter(el => el.match(filter));
        }
      },
      methods: {
        onChange() {
          this.isOpen = true;
          if (this.filteredVoci.length < 1) {
            this.isOpen = false;
          }
        },
        setResult(result) {
          this.voce.nomeVoce = result;
          this.isOpen = false;
        }
    }
}