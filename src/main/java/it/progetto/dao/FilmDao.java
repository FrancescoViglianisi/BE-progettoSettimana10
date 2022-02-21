package it.progetto.dao;

import java.util.List;

import it.progetto.entity.Film;

public interface FilmDao {
	
	public void salva(Film f);
	public void modifica(Film f);
	public void cancella(int id);
	public Film trovaById(int id);
	public List<Film> trovaByRegista(String regista);
	public List<Film> trovaTutti();
}
