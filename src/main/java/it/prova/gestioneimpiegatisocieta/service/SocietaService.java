package it.prova.gestioneimpiegatisocieta.service;

import java.util.List;

import it.prova.gestioneimpiegatisocieta.model.Societa;


public interface SocietaService {

	public List<Societa> listAllEdifici();

	public Societa caricaSingoloEdificio(Long id);

	public void aggiorna(Societa progettoInstance);

	public void inserisciNuovo(Societa progettoInstance);

	public void rimuovi(Societa progettoInstance);
	
	public List<Societa> findByExample(Societa example);
	
	public List<String> cercaNomeSocietaProgettiPiuDiUnAnno();
}
