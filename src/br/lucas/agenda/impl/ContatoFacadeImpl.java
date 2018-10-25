package br.lucas.agenda.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.lucas.agenda.controller.ResourcePersistence;
import br.lucas.agenda.facade.ContatoFacade;
import br.lucas.agenda.model.Contato;

public class ContatoFacadeImpl implements ContatoFacade {

	@Override
	public void inserir(Contato obj) {

		EntityManager em = ResourcePersistence.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(obj);
			em.getTransaction().commit();
			System.out.println("CONTATO INSERIDO COM SUCESSO");
		} catch (Exception e) {
			System.err.println("ERRO: " + e.getMessage());
		} finally {
			em.close();
		}

	}

	@Override
	public void alterar(Contato obj) {

		EntityManager em = ResourcePersistence.getEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(obj);
			em.getTransaction().commit();
			System.out.println("CONTATO ALTERADO COM SUCESSO");
		} catch (Exception e) {
			System.err.println("ERRO: " + e.getMessage());
		} finally {
			em.close();
		}

	}

	@Override
	public void deletar(Contato obj) {
		EntityManager em = ResourcePersistence.getEntityManager();
		try {
			em.getTransaction().begin();
			Contato contato = em.find(Contato.class, obj.getIdContato());
			em.remove(contato);
			em.getTransaction().commit();
			System.out.println("CONTATO EXCLUÍDO COM SUCESSO");
		} catch (Exception e) {
			System.err.println("ERRO: " + e.getMessage());
		} finally {
			em.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contato> listar(Contato obj) {

		EntityManager em = ResourcePersistence.getEntityManager();
		List<Contato> lista = new ArrayList<Contato>();
		try {
			em.getTransaction().begin();
			if (obj.getDscNome() != null && !obj.getTelefone().equals("")) {
				lista = em.createQuery("SELECT c FROM Contato c WHERE c.dscNome = :nome AND c.telefone = :telefone ")
						.setParameter("nome", obj.getDscNome()).setParameter("telefone", obj.getTelefone())
						.getResultList();
			} else if (obj.getDscNome() != null) {
				lista = em.createQuery("SELECT c FROM Contato c WHERE c.dscNome = :nome ")
						.setParameter("nome", obj.getDscNome()).getResultList();
			} else {
				lista = em.createQuery("SELECT c FROM Contato c ").getResultList();
			}
			em.getTransaction().commit();
			return lista;
		} catch (Exception e) {
			System.err.println("ERRO: " + e.getMessage());
		} finally {
			em.close();
		}
		return lista;
	}

}
