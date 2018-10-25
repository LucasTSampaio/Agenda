package br.lucas.agenda.facade;

import java.util.List;

import br.lucas.agenda.model.Contato;


public interface ContatoFacade {

	
	public void inserir(Contato obj);
	public void alterar(Contato obj);
	public void deletar(Contato obj);
	public List<Contato> listar(Contato obj);
}
