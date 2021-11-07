package it.prova.gestioneimpiegatisocieta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.prova.gestioneimpiegatisocieta.model.Impiegato;


public interface ImpiegatoRepository extends CrudRepository<Impiegato, Long>,QueryByExampleExecutor <Impiegato> {

	@Query("select distinct i from Societa s join s.impiegati i join i.progetti p where s.dataFondazione >= '1990/01/01' and p.durataInMesi >= 6 order by i.dataAssunzione ASC")
	List<Impiegato> findImpiegatoVecchioConProgettoSuperioreASeiMesieSocietaFondataPrimaDel1990();

}
