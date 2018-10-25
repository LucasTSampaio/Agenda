package br.lucas.agenda.teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteJPA {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projagenda");
		EntityManager em = emf.createEntityManager();
		em.close();
		emf.close();
	}

}
