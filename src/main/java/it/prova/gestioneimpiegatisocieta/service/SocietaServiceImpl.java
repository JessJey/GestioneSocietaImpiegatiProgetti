package it.prova.gestioneimpiegatisocieta.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestioneimpiegatisocieta.model.Societa;
import it.prova.gestioneimpiegatisocieta.repository.SocietaRepository;

@Service
public class SocietaServiceImpl implements SocietaService{

	@Autowired
	private SocietaRepository societaRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional(readOnly = true)
	public List<Societa> listAllEdifici() {
		return (List<Societa>) societaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Societa caricaSingoloEdificio(Long id) {
		return societaRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void aggiorna(Societa progettoInstance) {
		societaRepository.save(progettoInstance);
	}

	@Override
	@Transactional
	public void inserisciNuovo(Societa progettoInstance) {
		societaRepository.save(progettoInstance);
	}

	@Override
	@Transactional
	public void rimuovi(Societa progettoInstance) {
		societaRepository.delete(progettoInstance);
	}

	@Override
	public List<Societa> findByExample(Societa example) {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select s from Societa s where s.id = s.id ");

		if (StringUtils.isNotEmpty(example.getRagioneSociale())) {
			whereClauses.add(" s.ragioneSociale  like :ragioneSociale ");
			paramaterMap.put("ragioneSociale", "%" + example.getRagioneSociale() + "%");
		}
		if (example.getDataFondazione() != null) {
			whereClauses.add("s.dataFondazione >= :dataFondazione ");
			paramaterMap.put("dataFondazione", example.getDataFondazione());
		}
		
		queryBuilder.append(!whereClauses.isEmpty()?" and ":"");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Societa> typedQuery = entityManager.createQuery(queryBuilder.toString(), Societa.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

	@Override
	@Transactional
	public List<String> cercaNomeSocietaProgettiPiuDiUnAnno() {
		return societaRepository.findNomeSocietaProgettiPiuDiUnAnno();
	}

}
