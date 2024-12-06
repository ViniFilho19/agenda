//package application;
//
//
//
//import java.util.List;
//import java.util.Scanner;
//
//
//import dao.ContatoProfissionalDao;
//import entidades.ContatoProfissional;
//import implementacaoDao.DaoFactory;
//
//
//public class ProgramaTestaContatoProfissional {
//
//
//    public static void main(String[] args) {
//        
//        ContatoProfissionalDao contatoProfissionalDao = DaoFactory.createContatoProfissionalDao();
//        Scanner sc = new Scanner(System.in);
//        int op;
//        System.out.println("-------------SEJA BEM VINDO A SUA AGENDA------------");
//        System.out.println("---------------- CONTATOS PROFISSIONAIS ------------");
//
//
//        do {
//            System.out.println("Escolha uma opção:");
//            System.out.println("1 - Buscar contato profissional por ID");
//            System.out.println("2 - Listar todos os contatos profissionais");
//            System.out.println("3 - Inserir novo contato profissional");
//            System.out.println("4 - Atualizar contato profissional");
//            System.out.println("5 - Excluir contato profissional por ID");
//            System.out.println("0 - Sair");
//            System.out.print("Opção: ");
//            op = sc.nextInt();
//            
//            switch (op) {
//                case 1:
//                    System.out.print("Informe o ID para buscar: ");
//                    int idBuscar = sc.nextInt();
//                    ContatoProfissional contatoProfissional = contatoProfissionalDao.findById(idBuscar);
//                    System.out.println(contatoProfissional != null ? contatoProfissional : "Contato não encontrado!");
//                    break;
//                
//                case 2:
//                    System.out.println("--- Listando todos os contatos profissionais ---");
//                    List<ContatoProfissional> lista = contatoProfissionalDao.findAll();
//                    for (ContatoProfissional obj : lista) {
//                        System.out.println(obj);
//                    }
//                    break;
//                
//                case 3:
//                    System.out.println("--- Inserir novo contato profissional ---");
//                    System.out.print("Nome: ");
//                    String nome = sc.next();
//                    System.out.print("Telefone: ");
//                    String telefone = sc.next();
//                    System.out.print("Email: ");
//                    String email = sc.next();
//                    System.out.print("Empresa: ");
//                    String empresa = sc.next();
//                    
//                    ContatoProfissional novoContatoProfissional = new ContatoProfissional(null, nome, telefone, email, empresa);
//                    contatoProfissionalDao.insert(novoContatoProfissional);
//                    System.out.println("Inserção realizada! Novo ID = " + novoContatoProfissional.getId());
//                    break;
//                
//                case 4:
//                    System.out.print("Informe o ID do contato para atualizar: ");
//                    int idAtualizar = sc.nextInt();
//                    sc.nextLine(); 
//                    ContatoProfissional contatoAtualizar = contatoProfissionalDao.findById(idAtualizar);
//                    if (contatoAtualizar != null) {
//                        System.out.print("Novo nome: ");
//                        String novoNome = sc.nextLine(); 
//                        System.out.print("Novo email: ");
//                        String novoEmail = sc.nextLine(); 
//                        System.out.print("Novo telefone: ");
//                        String novoNumero = sc.nextLine(); 
//                        System.out.print("Nova empresa: ");
//                        String novaEmpresa = sc.nextLine(); 
//                        contatoAtualizar.setTelefone(novaEmpresa);
//                        contatoAtualizar.setTelefone(novoNumero);
//                        contatoAtualizar.setNome(novoNome);
//                        contatoAtualizar.setEmail(novoEmail);
//                        contatoProfissionalDao.update(contatoAtualizar);
//                        System.out.println("Contato atualizado: " + contatoAtualizar);
//                    } else {
//                        System.out.println("Contato não encontrado!");
//                    }
//                    break;
//                
//                case 5:
//                    System.out.print("Informe o ID do contato a ser excluído: ");
//                    int idExcluir = sc.nextInt();
//                    contatoProfissionalDao.deleteById(idExcluir);
//                    System.out.println("Exclusão executada para o ID: " + idExcluir);
//                    break;
//                
//                case 0:
//                    System.out.println("Saindo");
//                    break;
//                
//                default:
//                    System.out.println("Opção inválida! Tente novamente.");
//            }
//            
//        } while (op != 0);
//        
//        sc.close();
//    }
//}
//
//
