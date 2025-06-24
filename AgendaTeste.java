/*
 * Bruna F. Costa

Classe Principal (AgendaTeste) com um método main que permite ao usuário
interagir com a agenda telefônica através de um menu simples. O menu deve
oferecer as seguintes opções:

1.Adicionar um novo contato.
2.Remover um contato existente.
3.Buscar um contato pelo nome.
4.Listar todos os contatos.
5.Sair do programa.
 */

package AgendaTeste;

import java.util.List;
import java.util.Scanner;

public class AgendaTeste {

    public static void main(String[] args) {
        System.out.println("Bem vindo a agenda telefonica!\n");
        
        Scanner scanner = new Scanner(System.in);
        AgendaTelefonica agenda = new AgendaTelefonica();
        int opcao;
        
        do {
            System.out.println("Informe a opcao desejada:");
            System.out.println("1.Adicionar um novo contato.");
            System.out.println("2.Remover um contato existente.");
            System.out.println("3.Buscar um contato pelo nome.");
            System.out.println("4.Listar todos os contatos.");
            System.out.println("5.Sair do programa.\n");
            System.out.println("Digite um dos numeros acima:");
            try {
                   opcao = Integer.parseInt(scanner.nextLine());
                }  catch (NumberFormatException e) {
                   System.out.println("Opcao invalida. Por favor, digite\n uma opcao valida:");
                   opcao = 0;
                }
        
        

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nomeAdd = scanner.nextLine();
                    System.out.print("Telefone: ");
                    String telefoneAdd = scanner.nextLine();
                    System.out.print("Email: ");
                    String emailAdd = scanner.nextLine();
                    agenda.adicionarContato(new Contato(nomeAdd, telefoneAdd, emailAdd));
                    break;
                case 2:
                    System.out.print("Digite o nome do contato a ser excluido: ");
                    String nomeRemover = scanner.nextLine();
                    agenda.removerContato(nomeRemover);
                    break;
                case 3:
                    System.out.print("Digite o nome do contato desejado: ");
                    String nomeBuscar = scanner.nextLine();
                    Contato contatoEncontrado = agenda.buscarContato(nomeBuscar);
                    if (contatoEncontrado != null) {
                        System.out.println("Contato encontrado: " + contatoEncontrado);
                    } else {
                        System.out.println("Contato " + nomeBuscar + " nao encontrado.");
                    }
                    break;
                case 4:
                    List<Contato> contatos = agenda.listarContatos();
                    if (contatos.isEmpty()) {
                        System.out.println("A agenda esta vazia.");
                    } else {
                        System.out.println("\nTodos os Contatos:");
                        for (Contato c : contatos) {
                            System.out.println(c);
                        }
                    }
                    break;
                case 5:
                    System.out.println("Fechando agenda");
                    break;
                default:
                    if (opcao != 0) {
                        System.out.println("Opcao invalida, tente novamente.");
                    }
                    break;
            }
        } while (opcao != 5);

        scanner.close();
    }
}
