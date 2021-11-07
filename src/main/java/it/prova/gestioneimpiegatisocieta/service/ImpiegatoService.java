package it.prova.gestioneimpiegatisocieta.service;

import java.util.List;

import it.prova.gestioneimpiegatisocieta.model.Impiegato;


public interface ImpiegatoService {

	public List<Impiegato> listAllEdifici();

	public Impiegato caricaSingoloEdificio(Long id);

	public void aggiorna(Impiegato impiegatoInstance);

	public void inserisciNuovo(Impiegato impiegatoInstance);

	public void rimuovi(Impiegato impiegatoInstance);
	
	public List<Impiegato> cercaImpiegatoVecchioProgettoSeiMesiSocieta1990();
}
