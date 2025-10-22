class Casa extends Imovel {
    public Casa(String endereco, int numero, Proprietario proprietario) {
        super(endereco, numero, proprietario);
    }

    @Override
    public String statusAluguel() {
        return estaAlugado() ? "A casa está alugada." : "A casa está disponível.";
    }
}
