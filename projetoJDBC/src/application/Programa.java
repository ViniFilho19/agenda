//package application;
//
//
//
//import java.sql.Connection;
//import java.util.List;
//import java.util.Scanner;
//import db.DB;
//import implementacaoDao.ContatoDaoJDBC;
//import entidades.Contato;
//
//public class Programa {
//	public static void main(String[] args) {
//		Connection conn = DB.getConnection();
//		ContatoDaoJDBC contatoDao = new ContatoDaoJDBC(conn);
//		Scanner sc = new Scanner(System.in);
//		System.out.println("-------------SEJA BEM VINDO A SUA AGENDA------------");
//		System.out.println("----------------CONTATOS SIMPLES------------");
//		int op;
//		do {
//			System.out.println("Escolha uma opção:");
//			System.out.println("1 - Inserir novo contato");
//			System.out.println("2 - Atualizar contato");
//			System.out.println("3 - Buscar contato por ID");
//			System.out.println("4 - Listar todos os contatos");
//			System.out.println("5 - Excluir contato por ID");
//			System.out.println("0 - Sair");
//			System.out.print("Opção: ");
//			op = sc.nextInt();
//			sc.nextLine();
//			switch (op) {
//			case 1:
//				System.out.println("--- Inserir novo contato ---");
//				System.out.print("Nome: ");
//				String nome = sc.nextLine();
//				System.out.print("Telefone: ");
//				String telefone = sc.nextLine();
//				System.out.print("Email: ");
//				String email = sc.nextLine();
//				Contato novoContato = new Contato(null, nome, telefone, email);
//				contatoDao.insert(novoContato);
//				System.out.println("Contato inserido! ID: " + novoContato.getId());
//				break;
//			case 2:
//				System.out.println("--- Atualizar contato ---");
//				System.out.print("Informe o ID do contato para atualizar: ");
//				int idAtualizar = sc.nextInt();
//				sc.nextLine();
//				Contato contatoAtualizar = contatoDao.findById(idAtualizar);
//				if (contatoAtualizar != null) {
//					System.out.print("Novo nome: ");
//					String novoNome = sc.nextLine();
//					System.out.print("Novo telefone: ");
//					String novoTelefone = sc.nextLine();
//					System.out.print("Novo email: ");
//					String novoEmail = sc.nextLine();
//					contatoAtualizar.setNome(novoNome);
//					contatoAtualizar.setTelefone(novoTelefone);
//					contatoAtualizar.setEmail(novoEmail);
//					contatoDao.update(contatoAtualizar);
//					System.out.println("Contato atualizado!");
//				} else {
//					System.out.println("Contato não encontrado!");
//				}
//				break;
//			case 3:
//				System.out.println("--- Buscar contato por ID ---");
//				System.out.print("Informe o ID do contato: ");
//				int idBuscar = sc.nextInt();
//				sc.nextLine();
//				Contato contatoEncontrado = contatoDao.findById(idBuscar);
//				if (contatoEncontrado != null) {
//					System.out.println("Contato encontrado: " + contatoEncontrado);
//				} else {
//					System.out.println("Contato não encontrado!");
//				}
//				break;
//			case 4:
//				System.out.println("--- Listar todos os contatos ---");
//				List<Contato> todosContatos = contatoDao.findAll();
//				if (todosContatos.isEmpty()) {
//					System.out.println("Não há contatos cadastrados.");
//				} else {
//					System.out.println("Lista de contatos:");
//					for (Contato c : todosContatos) {
//						System.out.println(c);
//					}
//				}
//				break;
//			case 5:
//				System.out.println("--- Excluir contato por ID ---");
//				System.out.print("Informe o ID do contato a ser excluído: ");
//				int idExcluir = sc.nextInt();
//				sc.nextLine();
//				contatoDao.deleteById(idExcluir);
//				System.out.println("Contato excluído!");
//				break;
//			case 0:
//				System.out.println("Saindo...");
//				break;
//			default:
//				System.out.println("Opção inválida! Tente novamente.");
//			}
//		} while (op != 0);
//		DB.closeConnection();
//		sc.close();
//	}
//}