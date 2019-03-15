package onlus.manager.springbootvuejs.hibernate.domain;


public enum TipoVoce {


	Tesseramento{
		@Override
		public String toString() {
		   return "tesseramento";
		}
	 },
  
	 Donazione{
		@Override
		public String toString() {
		   return "donazione";
		}
	 },
  
	 RimborsoSpeseAttivita{
		@Override
		public String toString() {
		   return "rimborso spese attivita";
		}
	 },
  
	 QuotaMensileAttivita{
		@Override
		public String toString() {
		   return "quota mensile attivita";
		}
	 },
  
	 Assicurazione{
		@Override
		public String toString() {
		   return "assicurazione";
		}
	 },

	 AffittoSedi{
		@Override
		public String toString() {
		   return "affitto sedi";
		}
	 },
	 AcquistoMateriali{
		@Override
		public String toString() {
		   return "acquisto materiali";
		}
	 }
}


