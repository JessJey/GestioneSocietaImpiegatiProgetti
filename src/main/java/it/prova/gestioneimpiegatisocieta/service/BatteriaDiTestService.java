package it.prova.gestioneimpiegatisocieta.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestioneimpiegatisocieta.model.Impiegato;
import it.prova.gestioneimpiegatisocieta.model.Progetto;
import it.prova.gestioneimpiegatisocieta.model.Societa;

@Service
public class BatteriaDiTestService {

	@Autowired
	private ImpiegatoService impiegatoService;
	@Autowired
	private ProgettoService progettoService;
	@Autowired
	private SocietaService societaService;

	public void testInserimentoSocieta() {

		Societa inserimentoSocieta = new Societa("Google", new Date());
		societaService.inserisciNuovo(inserimentoSocieta);
		if (inserimentoSocieta.getId() == null || inserimentoSocieta.getId() < 1)
			throw new RuntimeException("testInserisciNuovaSocieta...failed: inserimento fallito");

		System.out.println("TEST INSERIMENTO PASSATO OK");
	}

	public void testFindByExample() {

		Societa inserimentoSocieta = new Societa("Spring", new Date());
		societaService.inserisciNuovo(inserimentoSocieta);
		if (inserimentoSocieta.getId() == null || inserimentoSocieta.getId() < 1)
			throw new RuntimeException("testInserisciNuovaSocieta...failed: inserimento fallito");
		societaService.inserisciNuovo(inserimentoSocieta);

		Societa societaDaRicercare = new Societa("Spring");
		List<Societa> listacercate = societaService.findByExample(societaDaRicercare);
		if (listacercate.size() != 1)
			throw new RuntimeException("testInserisciNuovaSocieta...failed: inserimento fallito");

		System.out.println("TEST FIND BY EXAMPLE PASSED");

	}

	public void testRimozione() {

		Societa inserimentoSocieta = new Societa("Huawei", new Date());
		societaService.inserisciNuovo(inserimentoSocieta);

		Long idDaRimuovere = inserimentoSocieta.getId();
		societaService.rimuovi(inserimentoSocieta);

		if (societaService.caricaSingoloEdificio(idDaRimuovere) != null)
			throw new RuntimeException("TEST REMOVE EFFETTIVO...failed: inserimento fallito");

		System.out.println("TEST RIMOZIONE EFFETTUATO");

	}

	public void testInserimentoImpiegato() {

		Societa inserimentoSocieta = new Societa("OnePlus", new Date());
		societaService.inserisciNuovo(inserimentoSocieta);

		Impiegato impiegatoDaAgg = new Impiegato("Jessica", "Pet", new Date(), 500, inserimentoSocieta);
		impiegatoService.inserisciNuovo(impiegatoDaAgg);
		if (impiegatoDaAgg.getId() == null || impiegatoDaAgg.getId() < 1)
			throw new RuntimeException("testInserisciNuovoImpiegato...failed: inserimento fallito");

		System.out.println("TEST INSERIMENTO IMPIEGATO RIUSCITO");

	}

	public void testInserimentoProgetto() {

		Progetto progettoDaAgg = new Progetto("Mail", "clienti1", 5);
		progettoService.inserisciNuovo(progettoDaAgg);
		if (progettoDaAgg.getId() == null || progettoDaAgg.getId() < 1)
			throw new RuntimeException("testInserisciNuovoImpiegato...failed: inserimento fallito");

		System.out.println("Test Inserimento Progetto Passato");

	}

	public void testCollegamentoImpiegatoAPiuProgetti() {

		Societa inserimentoSocieta = new Societa("Sony", new Date());
		societaService.inserisciNuovo(inserimentoSocieta);

		Impiegato impiegatoDaAgg = new Impiegato("Paola", "pao", new Date(), 400, inserimentoSocieta);
		impiegatoService.inserisciNuovo(impiegatoDaAgg);

		Set<Progetto> listaProgettiVari = new HashSet<>();
		Progetto progettoDaAgg1 = new Progetto("Mail", "clienti1", 5);
		progettoService.inserisciNuovo(progettoDaAgg1);
		Progetto progettoDaAgg2 = new Progetto("Call", "clienti1", 2);
		progettoService.inserisciNuovo(progettoDaAgg2);
		Progetto progettoDaAgg3 = new Progetto("Phone", "clienti1", 6);
		progettoService.inserisciNuovo(progettoDaAgg3);
		listaProgettiVari.add(progettoDaAgg1);
		listaProgettiVari.add(progettoDaAgg2);
		listaProgettiVari.add(progettoDaAgg3);

		impiegatoDaAgg.setProgetti(listaProgettiVari);
		impiegatoService.aggiorna(impiegatoDaAgg);

		if (impiegatoDaAgg.getProgetti().size() != 3)
			throw new RuntimeException("test Collegamento Progetti...failed: inserimento fallito");

		System.out.println("TEST COLLEGAMENTO PROGETTI A IMPIEGATO PASSATO");

	}

	public void testCercaClientiSocieta() {
		Societa inserimentoSocieta = new Societa("PlayStation", new Date());
		societaService.inserisciNuovo(inserimentoSocieta);

		Impiegato impiegatoDaAgg = new Impiegato("Alfi", "ser", new Date(), 7400, inserimentoSocieta);
		impiegatoService.inserisciNuovo(impiegatoDaAgg);

		Set<Progetto> listaProgettiVari = new HashSet<>();
		Progetto progettoDaAgg3 = new Progetto("Phone", "PS", 6);
		progettoService.inserisciNuovo(progettoDaAgg3);
		listaProgettiVari.add(progettoDaAgg3);

		impiegatoDaAgg.setProgetti(listaProgettiVari);
		impiegatoService.aggiorna(impiegatoDaAgg);

		List<Progetto> listap= progettoService.cercaClientiSocieta(inserimentoSocieta.getId());
		
		if (listap.size() != 1)
			throw new RuntimeException("test CERCA CLIENTI..failed: inserimento fallito");

		listap.forEach(p -> System.out.println(p.getCliente()));
		System.out.println("TEST CERCA CLIENTI DA SOCIETA PASSATO");

	}

	public void testCercaNomeSocietaConProgettiDurata1annoPiu() {

		Societa inserimentoSocieta = new Societa("Blues", new Date());
		societaService.inserisciNuovo(inserimentoSocieta);

		Impiegato impiegatoDaAgg = new Impiegato("Miry", "ban", new Date(), 7400, inserimentoSocieta);
		impiegatoService.inserisciNuovo(impiegatoDaAgg);

		Set<Progetto> listaProgettiVari = new HashSet<>();
		Progetto progettoDaAgg3 = new Progetto("Mouse", "Amzn", 14);
		progettoService.inserisciNuovo(progettoDaAgg3);
		listaProgettiVari.add(progettoDaAgg3);

		impiegatoDaAgg.setProgetti(listaProgettiVari);
		impiegatoService.aggiorna(impiegatoDaAgg);

		List<String> nomiSocieta = societaService.cercaNomeSocietaProgettiPiuDiUnAnno();
		if (nomiSocieta.size() != 1)
			throw new RuntimeException("test CERCA CLIENTI..failed: inserimento fallito");

		System.out.println("TEST NOME SOCIETA CON PROGETTI 12 MESI + ... PASSATO");

	}
	
	public void testCercaProgettiConImpiegatiRalMaggioreDi30000() {
		
		Societa inserimentoSocieta = new Societa("Hello", new Date());
		societaService.inserisciNuovo(inserimentoSocieta);

		Impiegato impiegatoDaAgg = new Impiegato("Ciro", "Na", new Date(), 10000, inserimentoSocieta);
		impiegatoService.inserisciNuovo(impiegatoDaAgg);

		Set<Progetto> listaProgettiVari = new HashSet<>();
		Progetto progettoDaAgg3 = new Progetto("Mouse", "Amzn", 14);
		progettoService.inserisciNuovo(progettoDaAgg3);
		listaProgettiVari.add(progettoDaAgg3);

		impiegatoDaAgg.setProgetti(listaProgettiVari);
		impiegatoService.aggiorna(impiegatoDaAgg);
		
		List<Progetto> cercaProj = progettoService.cercaProgettiLavoratoreRal(30000);
		if (cercaProj.size() != 0)
			throw new RuntimeException("test CERCA PROGETTI..failed: inserimento fallito");
		
		System.out.println("TEST CERCA PROGETTI CON IMPIEGATI RAL MAGG DI 30000 PASSATO");
		
	}
	
	public void testCercaImpiegatoVecchioProgPiuSeiMesiSocieta1990() throws ParseException {
		
		String dataStringa = "10-01-1980";
		SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
		Date date = dt.parse(dataStringa);
		String data = "10-10-2020";
		Date dataAss = dt.parse(data);
		
		
		Societa inserimentoSocieta = new Societa("Hello", date);
		societaService.inserisciNuovo(inserimentoSocieta);

		Impiegato impiegatoDaAgg = new Impiegato("Ciro", "Na", new Date(), 10000, inserimentoSocieta);
		impiegatoService.inserisciNuovo(impiegatoDaAgg);
		Impiegato impiegatoDaAgg2 = new Impiegato("Mari", "Na", dataAss, 10000, inserimentoSocieta);
		impiegatoService.inserisciNuovo(impiegatoDaAgg2);

		Set<Progetto> listaProgettiVari = new HashSet<>();
		Progetto progettoDaAgg3 = new Progetto("Topos", "Like", 14);
		progettoService.inserisciNuovo(progettoDaAgg3);
		listaProgettiVari.add(progettoDaAgg3);

		impiegatoDaAgg.setProgetti(listaProgettiVari);
		impiegatoDaAgg2.setProgetti(listaProgettiVari);
		impiegatoService.aggiorna(impiegatoDaAgg2);
		impiegatoService.aggiorna(impiegatoDaAgg);
		
		Impiegato impiegatoVecchio = impiegatoService.cercaImpiegatoVecchioProgettoSeiMesiSocieta1990().get(0);
		
		
		System.out.println(impiegatoVecchio.toString());
		
		System.out.println("TEST IMPIEGATO VECCHIO PASSATO.....");
		
	}
	
	

}
