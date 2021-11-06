package it.prova.gestioneimpiegatisocieta.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestioneimpiegatisocieta.model.Progetto;
import it.prova.gestioneimpiegatisocieta.model.Societa;
import it.prova.gestioneimpiegatisocieta.repository.ProgettoRepository;

@Service
public class ProgettoServiceImpl implements ProgettoService {

	@Autowired
	private ProgettoRepository progettoRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional(readOnly = true)
	public List<Progetto> listAllEdifici() {
		return (List<Progetto>) progettoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Progetto caricaSingoloEdificio(Long id) {
		return progettoRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void aggiorna(Progetto progettoInstance) {
		progettoRepository.save(progettoInstance);
		
	}

	@Override
	@Transactional
	public void inserisciNuovo(Progetto progettoInstance) {
		progettoRepository.save(progettoInstance);		
	}

	@Override
	@Transactional
	public void rimuovi(Progetto progettoInstance) {
		progettoRepository.delete(progettoInstance);
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> cercaClientiSocieta(List<Societa> societa) {
		//return progettoRepository.findClientiDiSocieta(societa);
		return null;
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Progetto> cercaProgettiLavoratoreRal(int input) {
		return progettoRepository.findAllDistinctByImpiegati_RalGreaterThan(input);
	}

}
