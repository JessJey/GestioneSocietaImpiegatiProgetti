package it.prova.gestioneimpiegatisocieta.service;

import java.util.List;

import it.prova.gestioneimpiegatisocieta.model.Progetto;
import it.prova.gestioneimpiegatisocieta.model.Societa;

public interface ProgettoService {

	public List<Progetto> listAllEdifici();

	public Progetto caricaSingoloEdificio(Long id);

	public void aggiorna(Progetto progettoInstance);

	public void inserisciNuovo(Progetto progettoInstance);

	public void rimuovi(Progetto progettoInstance);
	
	public List<Progetto> cercaClientiSocieta(Long id);
	
	public List<Progetto> cercaProgettiLavoratoreRal(int input);
}
