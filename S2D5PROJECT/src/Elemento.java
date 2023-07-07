
public abstract class Elemento {

	private String codiceISBN;
	private String titolo;
	private int annoPubblicazione;
	private int numeroPagine;

	public Elemento(String codiceISBN, String titolo, int annoPubblicazione, int numeroPagine) {
		this.codiceISBN = codiceISBN;
		this.titolo = titolo;
		this.annoPubblicazione = annoPubblicazione;
		this.numeroPagine = numeroPagine;
	}

//metodo per trasformare le righe di testo nell oggetto corrispondente o libro o rivista in base alla lunghezza dell array di dati

	public static Elemento parseRigaCatalogo(String rigaCatalogo) {
		String[] dati = rigaCatalogo.split(",");

		if (dati.length == 6) {
			String codiceISBN = dati[0].split(": ")[1].trim();
			String titolo = dati[1].split(": ")[1].trim();
			int annoPubblicazione = Integer.parseInt(dati[2].split(": ")[1].trim());
			int numeroPagine = Integer.parseInt(dati[3].split(": ")[1].trim());
			String autore = dati[4].split(": ")[1].trim();
			String genere = dati[5].split(": ")[1].trim();

			return new Libri(codiceISBN, titolo, annoPubblicazione, numeroPagine, autore, genere);
		} else if (dati.length == 5) {
			String codiceISBN = dati[0].split(": ")[1].trim();
			String titolo = dati[1].split(": ")[1].trim();
			int annoPubblicazione = Integer.parseInt(dati[2].split(": ")[1].trim());
			int numeroPagine = Integer.parseInt(dati[3].split(": ")[1].trim());
			TipoRivista tipoRivista = TipoRivista.valueOf(dati[4].split(": ")[1].trim());

			return new Riviste(codiceISBN, titolo, annoPubblicazione, numeroPagine, tipoRivista);
		}

		return null;
	}

	public String getCodiceISBN() {
		return codiceISBN;
	}

	public void setCodiceISBN(String codiceISBN) {
		this.codiceISBN = codiceISBN;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public int getAnnoPubblicazione() {
		return annoPubblicazione;
	}

	public void setAnnoPubblicazione(int annoPubblicazione) {
		this.annoPubblicazione = annoPubblicazione;
	}

	public int getNumeroPagine() {
		return numeroPagine;
	}

	public void setNumeroPagine(int numeroPagine) {
		this.numeroPagine = numeroPagine;
	}

	public abstract void stampaInformazioni();

	// metodo per salvare gli oggetti come righe di testo nel file txt
	public abstract String formatRigaCatalogo();

}