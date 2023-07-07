import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;

public class CatalogoBibliotecario {
	private static final String FILE_NAME = "archiviazione.txt";

	private static List<Elemento> catalogo = new ArrayList<>();

	private static boolean datiCaricati = false; // booleano per fare in modo che si possano svolgere delle operazioni
													// solo se prima vengono caricati i dati dal disco

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int scelta = -1;

		while (scelta != 0) {
			System.out.println("Seleziona un'operazione da eseguire:");
			System.out.println("1. Aggiungi un libro");
			System.out.println("2. Aggiungi una rivista");
			System.out.println("3. Rimuovi un elemento");
			System.out.println("4. Cerca per codice ISBN");
			System.out.println("5. Cerca per anno di pubblicazione");
			System.out.println("6. Cerca per autore");
			System.out.println("7. Salva su disco");
			System.out.println("8. Carica i dati dal disco");
			System.out.println("0. Esci");

			scelta = scanner.nextInt();
			scanner.nextLine();

			switch (scelta) {
			case 1:
				if (!datiCaricati) {
					System.err.println("Devi caricare i dati prima di poter aggiungere un libro.");
					break;
				}
				try {
					System.out.println("Inserisci il codice ISBN del libro:");
					String codiceISBNLibro = scanner.nextLine();

					System.out.println("Inserisci il titolo del libro:");
					String titoloLibro = scanner.nextLine();

					System.out.println("Inserisci l'anno di pubblicazione del libro:");
					int annoPubblicazioneLibro = scanner.nextInt();
					scanner.nextLine();

					System.out.println("Inserisci il numero di pagine del libro:");
					int numeroPagineLibro = scanner.nextInt();
					scanner.nextLine();

					System.out.println("Inserisci l'autore del libro:");
					String autoreLibro = scanner.nextLine();

					System.out.println("Inserisci il genere del libro:");
					String genereLibro = scanner.nextLine();

					Libri libro = new Libri(codiceISBNLibro, titoloLibro, annoPubblicazioneLibro, numeroPagineLibro,
							autoreLibro, genereLibro);

					catalogo.add(libro);
				} catch (Exception e) {
					System.err.println("Errore durante l'aggiunta del libro: " + e.getMessage());
				}
				break;

			case 2:
				if (!datiCaricati) {
					System.err.println("Devi caricare i dati prima di poter aggiungere una rivista.");
					break;
				}
				try {
					System.out.println("Inserisci il codice ISBN della rivista:");
					String codiceISBNRivista = scanner.nextLine();

					System.out.println("Inserisci il titolo della rivista:");
					String titoloRivista = scanner.nextLine();

					System.out.println("Inserisci l'anno di pubblicazione della rivista:");
					int annoPubblicazioneRivista = scanner.nextInt();
					scanner.nextLine();

					System.out.println("Inserisci il numero di pagine della rivista:");
					int numeroPagineRivista = scanner.nextInt();
					scanner.nextLine();

					System.out.println("Inserisci il tipo di rivista (SETTIMANALE, MENSILE, SEMESTRALE):");
					String tipoRivistaString = scanner.nextLine();
					TipoRivista tipoRivista = TipoRivista.valueOf(tipoRivistaString);

					Riviste rivista = new Riviste(codiceISBNRivista, titoloRivista, annoPubblicazioneRivista,
							numeroPagineRivista, tipoRivista);

					catalogo.add(rivista);
				} catch (Exception e) {
					System.err.println("Errore durante l'aggiunta della rivista: " + e.getMessage());
				}
				break;

			case 3:
				if (!datiCaricati) {
					System.err.println("Devi caricare i dati prima di poter rimuovere un elemento.");
					break;
				}
				try {
					System.out.println("Inserisci il codice ISBN dell'elemento da rimuovere:");
					String codiceISBNDaRimuovere = scanner.nextLine();

					catalogo.removeIf(elemento -> elemento.getCodiceISBN().equals(codiceISBNDaRimuovere));
				} catch (Exception e) {
					System.err.println("Errore durante l'eliminazione dell' elemento: " + e.getMessage());
				}
				break;
			case 4:
				if (!datiCaricati) {
					System.err.println("Devi caricare i dati prima di poter eliminare un elemento.");
					break;
				}
				System.out.println("Inserisci il codice ISBN da cercare:");
				String codiceISBNDaTrovare = scanner.nextLine();

				List<Elemento> elementiFiltrati = catalogo.stream()
						.filter(elemento -> elemento.getCodiceISBN().equals(codiceISBNDaTrovare))
						.collect(Collectors.toList());

				if (elementiFiltrati.isEmpty()) {
					System.err.println("Nessun elemento trovato con il codice ISBN: " + codiceISBNDaTrovare);
				} else {
					elementiFiltrati.forEach(Elemento::stampaInformazioni);
				}
				break;

			case 5:
				if (!datiCaricati) {
					System.err.println("Devi caricare i dati prima di poter eseguire la ricerca.");
					break;
				}
				try {
					System.out.println("Inserisci l'anno di pubblicazione da cercare:");
					int annoPubblicazioneDaTrovare = scanner.nextInt();
					scanner.nextLine();

					List<Elemento> elementiPerAnno = catalogo.stream()
							.filter(elemento -> elemento.getAnnoPubblicazione() == annoPubblicazioneDaTrovare)
							.collect(Collectors.toList());

					if (elementiPerAnno.isEmpty()) {
						System.err.println(
								"Nessun elemento trovato per l'anno di pubblicazione: " + annoPubblicazioneDaTrovare);
					} else {
						elementiPerAnno.forEach(Elemento::stampaInformazioni);
					}
				} catch (InputMismatchException e) {
					System.err.println("Errore: devi inserire un numero per l'anno di pubblicazione.");
					scanner.nextLine();
				}
				break;

			case 6:
				if (!datiCaricati) {
					System.err.println("Devi caricare i dati prima di poter eseguire la ricerca.");
					break;
				}
				System.out.println("Inserisci l'autore da cercare:");
				String autoreDaTrovare = scanner.nextLine();

				List<Libri> libriFiltrati = catalogo.stream().filter(elemento -> elemento instanceof Libri)
						.map(elemento -> (Libri) elemento)
						.filter(librofilter -> librofilter.getAutore().equalsIgnoreCase(autoreDaTrovare))
						.collect(Collectors.toList());

				if (libriFiltrati.isEmpty()) {
					System.err.println("Nessun libro trovato con l'autore: " + autoreDaTrovare);
				} else {
					libriFiltrati.forEach(libroFiltrato -> libroFiltrato.stampaInformazioni());
				}

				break;

			case 7:

				if (!datiCaricati) {
					System.err.println("Devi caricare i dati prima di poterli salvare.");
					break;
				}
				try {
					salvaSuDisco(FILE_NAME);
					System.out.println("Salvataggio su disco completato.");
				} catch (IOException e) {
					System.err.println("Errore durante il salvataggio su disco: " + e.getMessage());
				}
				break;

			case 8:
				try {
					caricaDaDisco(FILE_NAME);
					datiCaricati = true;
					System.out.println("Caricamento da disco completato.");
				} catch (IOException e) {
					System.err.println("Errore durante il caricamento da disco: " + e.getMessage());
				}
				break;

			case 0:
				System.out.println("Programma terminato.");
				break;

			default:
				System.err.println("Opzione non valida.");
				break;
			}

			System.out.println();
		}

		scanner.close();
	}

	public static void salvaSuDisco(String nomeFile) throws IOException {
		List<String> righeCatalogo = catalogo.stream().map(Elemento::formatRigaCatalogo).collect(Collectors.toList());

		FileUtils.writeLines(new File(nomeFile), righeCatalogo);
	}

	public static void caricaDaDisco(String nomeFile) throws IOException {
		List<String> righeCatalogo = FileUtils.readLines(new File(nomeFile), "UTF-8");

		catalogo = righeCatalogo.stream().map(Elemento::parseRigaCatalogo).collect(Collectors.toList());
	}
}
