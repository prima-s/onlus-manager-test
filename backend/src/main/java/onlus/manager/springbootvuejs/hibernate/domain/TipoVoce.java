package onlus.manager.springbootvuejs.hibernate.domain;

import org.hibernate.search.annotations.Field;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;

@Entity
public class TipoVoce {
	@Id
	@GeneratedValue
	private Integer id;

	@Field
	private String name;

	public TipoVoce() {}

	public TipoVoce(String name) {

		this.name = name;
	}

	@Override
	public String toString() {
		return  name;
	}

}


