package br.lucas.agenda.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.lucas.agenda.facade.ContatoFacade;
import br.lucas.agenda.impl.ContatoFacadeImpl;
import br.lucas.agenda.model.Contato;


@ManagedBean(name = "contato")
@ViewScoped
public class ContatoView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8343872145123901836L;

	private ContatoFacade cf = new ContatoFacadeImpl();
	private Contato contato = new Contato();
	private Contato conSelecionado = new Contato();
	private List<Contato> contatos = new ArrayList<Contato>();

	// getters e setters
	public ContatoFacade getCf() {
		return cf;
	}

	public void setCf(ContatoFacade cf) {
		this.cf = cf;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public Contato getConSelecionado() {
		return conSelecionado;
	}

	public void setConSelecionado(Contato conSelecionado) {
		this.conSelecionado = conSelecionado;
	}

	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	// METODOS view
	public String envia() {
		String retorno = null;
		List<Contato> lst = cf.listar(contato);
		if (lst.size() == 0) {
			adicionarMensagem("CONTATO NÃO ENCONTRADO", null, null);
		} else {
			retorno = "/main";
		}
		return retorno;
	}

	public String selecionar() {
		String retorno = null;

		if (conSelecionado.getIdContato() > 0) {
			adicionarMensagem(
					"USUARIO " + conSelecionado.getDscNome() + " FOI SELECIONADO. PODERÁ SER ALTERADO OU DELETADO",
					null, null);
		}
		return retorno;
	}

	public String inicioCadastro() {
		String retorno = null;
		contato = new Contato();
		retorno = "/novocontato";
		return retorno;
	}

	public String inicioSobre() {
		String retorno = null;
		contato = new Contato();
		retorno = "/sobre";
		return retorno;
	}
	
	public String inserir() {
		String retorno = null;
			cf.inserir(contato);
			adicionarMensagem("CONTATO INSERIDO COM SUCESSO.", null, null);
			contato = new Contato();
		
		return retorno;
	}

	public String retornar() {
		String retorno = null;
		retorno = "/home";
		return retorno;
	}

	public void pesquisar() {
		this.preparaPesquisar(conSelecionado);
		contatos = new ArrayList<Contato>();
		if (conSelecionado.getDscNome().equals("")) {
			conSelecionado = new Contato();
		}
		List<Contato> lst = cf.listar(conSelecionado);
		if (lst.size() > 0) {
			for (Contato contato : lst) {
				contatos.add(contato);
			}
		} else {
			adicionarMensagem("CONTATO NÃO ENCONTRADO", null, null);
		}
	}

	public void preparaPesquisar(Contato contato) {
		if (contato.getDscNome() == null) {
			contato.setDscNome("");
		}
		if (contato.getTelefone() == null) {
			contato.setTelefone("");
		}
	}

	public String alterar() {
		String retorno = null;
		if (conSelecionado.getIdContato() == 0) {
			adicionarMensagem("SELECIONE O CONTATO QUE DESEJA ALTERAR", null, null);
		} else {
			cf.alterar(conSelecionado);
			adicionarMensagem("CONTATO ALTERADO COM SUCESSO.", null, null);
		}
		return retorno;
	}

	public String deletar() {
		String retorno = null;
		if (conSelecionado.getIdContato() == 0) {
			adicionarMensagem("SELECIONE O CONTATO QUE DESEJA DELETAR.", null, null);
		} else {
			cf.deletar(conSelecionado);
			adicionarMensagem("CONTATO EXCLUIDO COM SUCESSO.", null, null);
			contatos = new ArrayList<Contato>();
			conSelecionado = new Contato();
		}
		return retorno;
	}

	public String inicioConsulta() {
		String retorno = null;
		contato = new Contato();
		retorno = "/buscar";
		return retorno;
	}

	public void adicionarMensagem(String sumario, String detalhe, String pagina) {
		FacesMessage mensagem = new FacesMessage(sumario, detalhe);
		FacesContext.getCurrentInstance().addMessage(pagina, mensagem);
	}

}
