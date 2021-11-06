package it.prova.gestioneimpiegatisocieta.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.prova.gestioneimpiegatisocieta.model.Impiegato;


public interface ImpiegatoRepository extends CrudRepository<Impiegato, Long>,QueryByExampleExecutor <Impiegato> {

	@Query("Select i from Impiegato i where i.dataAssunzione in(select max(i.dataAssunzione)) "
			+ "join i.progetto p where p.durataInMesi >= 6 join i.societa s where s.dataFondazione < '1990-01-01'")
	Impiegato findImpiegatoVecchioConProgettoSuperioreASeiMesieSocietaFondataPrimaDel1990();
}
