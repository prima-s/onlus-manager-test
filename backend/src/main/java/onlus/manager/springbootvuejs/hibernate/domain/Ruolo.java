package onlus.manager.springbootvuejs.hibernate.domain;


public enum Ruolo {

   Amministratore{
      @Override
      public String toString() {
         return "amministratore";
      }
   },

   Tesoriere{

      public String toString() {
         return "tesoriere";
      }
   },

   Consigliere{
      @Override
      public String toString() {
         return "consigliere";
      }
   },

   Presidente{
      @Override
      public String toString() {
         return "presidente";
      }
   },

   Ordinario{
      @Override
      public String toString() {
         return "ordinario";
      }
   }
}
