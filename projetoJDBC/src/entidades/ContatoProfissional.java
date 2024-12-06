package entidades;


public class ContatoProfissional extends Contato {
    private String nomeEmpresa;

    public ContatoProfissional() {
    }

    public ContatoProfissional(Integer id, String nome, String telefone, String email, String nomeEmpresa) {
        super(id, nome, telefone, email);  
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    @Override
    public String toString() {
        return super.toString() + ", Empresa: " + nomeEmpresa;
    }
}
