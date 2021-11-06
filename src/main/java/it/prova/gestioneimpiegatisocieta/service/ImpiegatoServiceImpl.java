package it.prova.gestioneimpiegatisocieta.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestioneimpiegatisocieta.model.Impiegato;
import it.prova.gestioneimpiegatisocieta.repository.ImpiegatoRepository;

@Service
public class ImpiegatoServiceImpl implements ImpiegatoService {

	@Autowired
	private ImpiegatoRepository impiegatoRepository;
	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Impiegato> listAllEdifici() {
		return (List<Impiegato>) impiegatoRepository.findAll();
	}
	@Override
	@Transactional(readOnly = true)
	public Impiegato caricaSingoloEdificio(Long id) {
		return impiegatoRepository.findById(id).orElse(null);
	}
	@Override
	@Transactional
	public void aggiorna(Impiegato impiegatoInstance) {
		impiegatoRepository.save(impiegatoInstance);
		
	}
	@Override
	@Transactional
	public void inserisciNuovo(Impiegato impiegatoInstance) {
		impiegatoRepository.save(impiegatoInstance);
	}
	@Override
	@Transactional
	public void rimuovi(Impiegato impiegatoInstance) {
		impiegatoRepository.delete(impiegatoInstance);		
	}
	@Override
	@Transactional(readOnly = true)
	public Impiegato cercaImpiegatoVecchioProgettoSeiMesiSocieta1990() {
		return impiegatoRepository.findImpiegatoVecchioConProgettoSuperioreASeiMesieSocietaFondataPrimaDel1990();
		
	}
	
	
}
