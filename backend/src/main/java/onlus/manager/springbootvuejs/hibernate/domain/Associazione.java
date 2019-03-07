package onlus.manager.springbootvuejs.hibernate.domain;


public class Associazione {
    
	private final String indirizzoMail;
	private final String nome;
	private final String tipo;
   
    public Associazione(String nome, String tipo, String email){
		this.nome = nome;
		this.tipo = tipo;
		this.indirizzoMail= email;
	}

	/**
	 * @return the indirizzoMail
	 */
	public String getIndirizzoMail() {
		return indirizzoMail;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}


	
}
