package br.lucas.agenda.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Contato")
@Table(name = "T_CONTATO")
public class Contato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_contato", nullable = false, unique = true)
	private int idContato;

	@Column(name = "dsc_nome", nullable = false, unique = false)
	private String dscNome;

	@Column(name = "telefone", nullable = false, unique = true)
	private String telefone;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	// getters e setters

	public int getIdContato() {
		return idContato;
	}

	public void setIdContato(int idContato) {
		this.idContato = idContato;
	}

	public String getDscNome() {
		return dscNome;
	}

	public void setDscNome(String dscNome) {
		this.dscNome = dscNome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
