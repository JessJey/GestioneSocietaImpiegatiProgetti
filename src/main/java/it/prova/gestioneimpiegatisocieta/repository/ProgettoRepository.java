package it.prova.gestioneimpiegatisocieta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.prova.gestioneimpiegatisocieta.model.Progetto;
import it.prova.gestioneimpiegatisocieta.model.Societa;


public interface ProgettoRepository extends CrudRepository<Progetto, Long>,QueryByExampleExecutor <Progetto> {

	
	@Query("select p.cliente from Progetto p join p.impiegati i join i.societa s where s like ?1% ")
	List<String> findClientiDiSocieta(List<Societa> societa);
	
	@EntityGraph(attributePaths = { "impiegati" })
	List<Progetto> findAllDistinctByImpiegati_RalGreaterThan(int input);
}
