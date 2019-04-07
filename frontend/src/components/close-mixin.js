export const closeMixin = {
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
        responseAfterSubmit() {
          if (this.success) {
            setTimeout(() => {
              this.closeAll();
              this.success = null;
              this.error = null;
              this.response = null;
            }, 3000);
          } else if (this.error) {
            this.message = "Something goes wrong... try again";
          }
        }
    }
}