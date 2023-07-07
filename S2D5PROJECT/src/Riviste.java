
public class Riviste extends Elemento {
	private TipoRivista tipoRivista;

	public Riviste(String codiceISBN, String titolo, int annoPubblicazione, int numeroPagine, TipoRivista tipoRivista) {
		super(codiceISBN, titolo, annoPubblicazione, numeroPagine);
		this.setTipoRivista(tipoRivista);
	}

	public TipoRivista getTipoRivista() {
		return tipoRivista;
	}

	public void setTipoRivista(TipoRivista tipoRivista) {
		this.tipoRivista = tipoRivista;
	}

	// stampo in formato colonne
	@Override
	public void stampaInformazioni() {
		System.out.println("RIVISTA:");
		System.out.printf("%-15s %-20s %-10s %-10s %-15s\n", "Codice ISBN", "Titolo", "Anno", "Pagine",
				"Tipo di rivista");
		System.out.printf("%-15s %-20s %-10d %-10d %-15s\n", getCodiceISBN(), getTitolo(), getAnnoPubblicazione(),
				getNumeroPagine(), getTipoRivista());
	}

	// metodo per salvare gli oggetti come righe di testo nel file txt

	@Override
	public String formatRigaCatalogo() {
		return "RIVISTA-" + "Codice ISBN: " + getCodiceISBN() + ", Titolo: " + getTitolo() + ", Anno di Pubblicazione: "
				+ String.valueOf(getAnnoPubblicazione()) + ", Numero Pagine: " + String.valueOf(getNumeroPagine())
				+ ", Tipo Rivista: " + getTipoRivista().toString();
	}

}
