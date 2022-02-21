package it.progetto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import it.progetto.entity.Film;


public class FilmDaoImp implements FilmDao {

	private EntityManager emf = null;

	public EntityManager getEmf() {
		if(emf==null) {
			emf = EntityManagerHelper.getEntityManager();
		}
		return emf;
	}

	public void salva(Film f) {
		getEmf().getTransaction().begin();
		getEmf().persist(f);
		getEmf().getTransaction().commit();

	}

	public void modifica(Film f) {
		try {
			getEmf().getTransaction().begin();
			Film film = getEmf().find(Film.class, f.getId());
			if(film != null) {
				getEmf().merge(f);
				getEmf().getTransaction().commit();
			}
			else {
				return ;
			}
		} catch (Exception e) {
			getEmf().getTransaction().rollback();
		}


	}
	public void cancella(int id) {

		try {
			getEmf().getTransaction().begin();
			getEmf().remove(getEmf().find(Film.class, id));
			getEmf().getTransaction().commit();
		} catch (Exception e) {
			getEmf().getTransaction().rollback();
			e.printStackTrace();
		}



	}

	@SuppressWarnings("unchecked")
	public List<Film> trovaTutti(){

		return getEmf().createNamedQuery("TrovaTutti").getResultList(); 

	}

	@SuppressWarnings("unchecked")
	public List<Film> trovaByRegista(String regista) {
		Query q = getEmf().createNamedQuery("trovaByRegista");
		q.setParameter(1, regista);
		try {
			return q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public Film trovaById(int id) {
		return getEmf().find(Film.class, id);
	}


}
