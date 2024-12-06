package application;



import java.sql.Connection;
import java.util.List;
import java.util.Scanner;
import db.DB;
import entidades.Contato;
import entidades.ContatoProfissional;
import implementacaoDao.ContatoDaoJDBC;
import implementacaoDao.DaoFactory;
import dao.ContatoProfissionalDao;

public class Principal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Connection conn = DB.getConnection();
        ContatoDaoJDBC contatoDao = new ContatoDaoJDBC(conn);
        ContatoProfissionalDao contatoProfissionalDao = DaoFactory.createContatoProfissionalDao();

        int opcaoPrincipal;
        System.out.println("-------------SEJA BEM VINDO A SUA AGENDA------------");

        do {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Gerenciar Contatos Simples");
            System.out.println("2 - Gerenciar Contatos Profissionais");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            opcaoPrincipal = sc.nextInt();
            sc.nextLine(); 

            switch (opcaoPrincipal) {
                case 1:
                    gerenciarContatosSimples(sc, contatoDao);
                    break;
                case 2:
                    gerenciarContatosProfissionais(sc, contatoProfissionalDao);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcaoPrincipal != 0);

        DB.closeConnection();
        sc.close();
    }

    private static void gerenciarContatosSimples(Scanner sc, ContatoDaoJDBC contatoDao) {
        int op;
        System.out.println("----------------CONTATOS SIMPLES------------");
        do {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Inserir novo contato");
            System.out.println("2 - Atualizar contato");
            System.out.println("3 - Buscar contato por ID");
            System.out.println("4 - Listar todos os contatos");
            System.out.println("5 - Excluir contato por ID");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    System.out.println("--- Inserir novo contato ---");
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Telefone: ");
                    String telefone = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    Contato novoContato = new Contato(null, nome, telefone, email);
                    contatoDao.insert(novoContato);
                    System.out.println("Contato inserido! ID: " + novoContato.getId());
                    break;
                case 2:
                    System.out.println("--- Atualizar contato ---");
                    System.out.print("Informe o ID do contato para atualizar: ");
                    int idAtualizar = sc.nextInt();
                    sc.nextLine();
                    Contato contatoAtualizar = contatoDao.findById(idAtualizar);
                    if (contatoAtualizar != null) {
                        System.out.print("Novo nome: ");
                        contatoAtualizar.setNome(sc.nextLine());
                        System.out.print("Novo telefone: ");
                        contatoAtualizar.setTelefone(sc.nextLine());
                        System.out.print("Novo email: ");
                        contatoAtualizar.setEmail(sc.nextLine());
                        contatoDao.update(contatoAtualizar);
                        System.out.println("Contato atualizado!");
                    } else {
                        System.out.println("Contato não encontrado!");
                    }
                    break;
                case 3:
                    System.out.println("--- Buscar contato por ID ---");
                    System.out.print("Informe o ID do contato: ");
                    int idBuscar = sc.nextInt();
                    sc.nextLine();
                    Contato contatoEncontrado = contatoDao.findById(idBuscar);
                    System.out.println(contatoEncontrado != null ? contatoEncontrado : "Contato não encontrado!");
                    break;
                case 4:
                    System.out.println("--- Listar todos os contatos ---");
                    List<Contato> todosContatos = contatoDao.findAll();
                    if (todosContatos.isEmpty()) {
                        System.out.println("Não há contatos cadastrados.");
                    } else {
                        for (Contato c : todosContatos) {
                            System.out.println(c);
                        }
                    }
                    break;
                case 5:
                    System.out.println("--- Excluir contato por ID ---");
                    System.out.print("Informe o ID do contato a ser excluído: ");
                    int idExcluir = sc.nextInt();
                    sc.nextLine();
                    contatoDao.deleteById(idExcluir);
                    System.out.println("Contato excluído!");
                    break;
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (op != 0);
    }

    private static void gerenciarContatosProfissionais(Scanner sc, ContatoProfissionalDao contatoProfissionalDao) {
        int op;
        System.out.println("---------------- CONTATOS PROFISSIONAIS ------------");
        do {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Buscar contato profissional por ID");
            System.out.println("2 - Listar todos os contatos profissionais");
            System.out.println("3 - Inserir novo contato profissional");
            System.out.println("4 - Atualizar contato profissional");
            System.out.println("5 - Excluir contato profissional por ID");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    System.out.print("Informe o ID para buscar: ");
                    int idBuscar = sc.nextInt();
                    sc.nextLine();
                    ContatoProfissional contatoProfissional = contatoProfissionalDao.findById(idBuscar);
                    System.out.println(contatoProfissional != null ? contatoProfissional : "Contato não encontrado!");
                    break;
                case 2:
                    System.out.println("--- Listando todos os contatos profissionais ---");
                    List<ContatoProfissional> lista = contatoProfissionalDao.findAll();
                    for (ContatoProfissional obj : lista) {
                        System.out.println(obj);
                    }
                    break;
                case 3:
                    System.out.println("--- Inserir novo contato profissional ---");
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Telefone: ");
                    String telefone = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Empresa: ");
                    String empresa = sc.nextLine();
                    ContatoProfissional novoContatoProfissional = new ContatoProfissional(null, nome, telefone, email, empresa);
                    contatoProfissionalDao.insert(novoContatoProfissional);
                    System.out.println("Inserção realizada! Novo ID = " + novoContatoProfissional.getId());
                    break;
                case 4:
                    System.out.print("Informe o ID do contato para atualizar: ");
                    int idAtualizar = sc.nextInt();
                    sc.nextLine();
                    ContatoProfissional contatoAtualizar = contatoProfissionalDao.findById(idAtualizar);
                    if (contatoAtualizar != null) {
                        System.out.print("Novo nome: ");
                        contatoAtualizar.setNome(sc.nextLine());
                        System.out.print("Novo email: ");
                        contatoAtualizar.setEmail(sc.nextLine());
                        System.out.print("Novo telefone: ");
                        contatoAtualizar.setTelefone(sc.nextLine());
                        System.out.print("Nova empresa: ");
                        contatoAtualizar.setNomeEmpresa(sc.nextLine());
                        contatoProfissionalDao.update(contatoAtualizar);
                        System.out.println("Contato atualizado!");
                    } else {
                        System.out.println("Contato não encontrado!");
                    }
                    break;
                case 5:
                    System.out.print("Informe o ID do contato a ser excluído: ");
                    int idExcluir = sc.nextInt();
                    sc.nextLine();
                    contatoProfissionalDao.deleteById(idExcluir);
                    System.out.println("Exclusão realizada!");
                    break;
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (op != 0);
    }
}
