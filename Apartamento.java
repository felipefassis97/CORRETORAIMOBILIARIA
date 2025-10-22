class Apartamento extends Imovel {
    public Apartamento(String endereco, int numero, Proprietario proprietario) {
        super(endereco, numero, proprietario);
    }

    @Override
    public String statusAluguel() {
        return estaAlugado()
            ? "O apartamento de número " + numero + " está alugado."
            : "O apartamento de número " + numero + " está disponível.";
    }
}
