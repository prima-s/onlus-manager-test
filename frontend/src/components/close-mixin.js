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
        }
    }
}