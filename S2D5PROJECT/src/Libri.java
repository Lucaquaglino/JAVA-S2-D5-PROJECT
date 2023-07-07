public class Libri extends Elemento {
	private String autore;
	private String genere;

	;

	public Libri(String codiceISBN, String titolo, int annoPubblicazione, int numeroPagine, String autore,
			String genere) {
		super(codiceISBN, titolo, annoPubblicazione, numeroPagine);
		this.autore = autore;
		this.genere = genere;
	}

	public String getAutore() {
		return autore;
	}

	public String getGenere() {
		return genere;
	}

	public void setAutore(String autore) {
		this.autore = autore;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	// stampo in formato colonne
	@Override
	public void stampaInformazioni() {
		System.out.println("LIBRO:");
		System.out.printf("%-15s %-20s %-10s %-10s %-15s %-15s\n", "Codice ISBN", "Titolo", "Anno", "Pagine", "Autore",
				"Genere");
		System.out.printf("%-15s %-20s %-10d %-10d %-15s %-15s\n", getCodiceISBN(), getTitolo(), getAnnoPubblicazione(),
				getNumeroPagine(), getAutore(), getGenere());
	}

	// metodo per salvare gli oggetti come righe di testo nel file txt
	@Override
	public String formatRigaCatalogo() {
		return "LIBRO-" + "Codice ISBN: " + getCodiceISBN() + ", " + "Titolo: " + getTitolo() + ", "
				+ "Anno di Pubblicazione: " + getAnnoPubblicazione() + ", " + "Numero Pagine: " + getNumeroPagine()
				+ ", " + "Autore: " + getAutore() + ", " + "Genere: " + getGenere();
	}

}
