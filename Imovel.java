abstract class Imovel {
    protected String endereco;
    protected int numero;
    protected boolean alugado;
    protected Proprietario proprietario;
    protected Inquilino inquilino;

    public Imovel(String endereco, int numero, Proprietario proprietario) {
        this.endereco = endereco;
        this.numero = numero;
        this.proprietario = proprietario;
        this.alugado = false;
        this.inquilino = null;
    }

    public String getEndereco() { return endereco; }
    public int getNumero() { return numero; }
    public boolean estaAlugado() { return alugado; }
    public Proprietario getProprietario() { return proprietario; }
    public Inquilino getInquilino() { return inquilino; }

    public void setEndereco(String endereco) { this.endereco = endereco; }
    public void setNumero(int numero) { this.numero = numero; }

    public void alugar(Inquilino inquilino) {
        this.inquilino = inquilino;
        this.alugado = true;
    }

    public void liberar() {
        this.inquilino = null;
        this.alugado = false;
    }

    public String contatoProprietario() {
        return proprietario.toString();
    }

    public int calcularAluguel(int meses) {
        return meses * 1000;
    }

    public abstract String statusAluguel();

    @Override
    public String toString() {
        String status = alugado ? "Alugado por: " + inquilino.getNome() : "Disponível";
        return endereco + ", nº " + numero + " - Proprietário: " + proprietario.getNome() + " | " + status;
    }
}
