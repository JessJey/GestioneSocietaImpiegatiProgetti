package it.prova.gestioneimpiegatisocieta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.prova.gestioneimpiegatisocieta.model.Societa;


public interface SocietaRepository extends CrudRepository<Societa, Long>,QueryByExampleExecutor <Societa> {


	@Query("Select s.ragioneSociale from Societa s join s.impiegati i join i.progetti p where p.durataInMesi > 12  ")
	List<String> findNomeSocietaProgettiPiuDiUnAnno();
}
