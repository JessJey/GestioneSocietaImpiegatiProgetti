package it.prova.gestioneimpiegatisocieta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.prova.gestioneimpiegatisocieta.model.Progetto;
import it.prova.gestioneimpiegatisocieta.model.Societa;


public interface ProgettoRepository extends CrudRepository<Progetto, Long>,QueryByExampleExecutor <Progetto> {

	
	@Query("select distinct p from Societa s join s.impiegati i join i.progetti p where s.id = ?1 ")
	List<Progetto> findClientiDiSocieta(Long id);
	@EntityGraph(attributePaths = { "impiegati" })
	List<Progetto> findAllDistinctByImpiegati_RalGreaterThan(int input);
}
