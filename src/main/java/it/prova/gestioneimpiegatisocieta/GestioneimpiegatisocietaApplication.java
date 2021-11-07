package it.prova.gestioneimpiegatisocieta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.gestioneimpiegatisocieta.service.BatteriaDiTestService;

@SpringBootApplication
public class GestioneimpiegatisocietaApplication implements CommandLineRunner {

	@Autowired
	private BatteriaDiTestService batteriaDiTestService;
	
	public static void main(String[] args) {
		SpringApplication.run(GestioneimpiegatisocietaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("################ START   #################");
		System.out.println("################ eseguo i test  #################");

		batteriaDiTestService.testInserimentoSocieta();
		batteriaDiTestService.testFindByExample();
		batteriaDiTestService.testRimozione();
		batteriaDiTestService.testInserimentoImpiegato();
		batteriaDiTestService.testInserimentoProgetto();
		batteriaDiTestService.testCollegamentoImpiegatoAPiuProgetti();
		batteriaDiTestService.testCercaClientiSocieta();
		batteriaDiTestService.testCercaNomeSocietaConProgettiDurata1annoPiu();
		batteriaDiTestService.testCercaProgettiConImpiegatiRalMaggioreDi30000();
		batteriaDiTestService.testCercaImpiegatoVecchioProgPiuSeiMesiSocieta1990();
		

		System.out.println("################ FINE   #################");
	}

}
