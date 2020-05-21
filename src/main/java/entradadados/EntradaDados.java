package entradadados;

import cliente.Cliente;
import enums.TipoPagamento;
import lombok.Getter;
import lombok.Setter;
import sistema.Sistema;

import java.util.InputMismatchException;
import java.util.Scanner;

@Setter
@Getter
public class EntradaDados {
    static Scanner scanner;
    private static Sistema sistema;

    static {
        scanner = new Scanner(System.in);
        sistema = new Sistema();
    }

    public EntradaDados(){
    }

    public static void pedirOpcaoMenu() {
        int opcaoMenu = 0;

        do {
            System.out.println("########### Sistema de Solicitação de Pagamento ##########");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Visualizar clientes Cadastrados");
            System.out.println("3 - Solicitar pagamento e escolher quantidade de parcelas");
            System.out.println("4 - Visualizar clientes com solicitacoes");
            System.out.println("5 - Efetuar pagamento");
            System.out.println("6 - Sair");
            System.out.println("########################## MENU ##########################");

            System.out.println("Digite a opcao: ");
            try {
                opcaoMenu = scanner.nextInt();
                limparBufferEntrada();
                switch(opcaoMenu) {
                    case 1:
                        sistema.pedirDadosECriarCliente();
                        break;
                    case 2:
                        sistema.exibirClientesCadastrados();
                        break;
                    case 3:
                        sistema.solicitarPagamentoEQuantidadeParcelas();
                        break;
                    case 4:
                        sistema.visualizarClientesComSolicitacoes();
                        break;
                    case 5:
                        sistema.efetuarPagamento();
                        break;
                    default:
                        System.out.println("Opcao invalida. Digite novamente.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Opcao invalida. Erro " + e + ". Digite novamente.");
                limparBufferEntrada();
            } catch (NullPointerException e1) {
                System.out.println("Opcao invalida. Erro " + e1 + ". Digite novamente.");
            }
        } while(opcaoMenu != 6);
    }

    public static Cliente pedirDadosCliente() {
        Cliente cliente = new Cliente();
        System.out.println("Nome do Cliente");
        cliente.setNome(scanner.nextLine());
        System.out.println("CPF do Cliente");
        cliente.setCpf(scanner.nextLine());
        return cliente;
    }

    public static String pedirCpf() {
        System.out.println("Qual o CPF do cliente para qual deseja solicitar o pagamento?");
        return scanner.nextLine();
    }

    private static void limparBufferEntrada() {
        scanner.nextLine();
    }

    public static void pedirValorEQuantidadeParcelas(Cliente cliente) {
        System.out.println("Qual o valor deseja solicitar para " + cliente.getNome() + "?");
        cliente.setValor(scanner.nextDouble());
        System.out.println("Qual a quantidade de parcelas?");
        cliente.setQuantidadeParcelas(scanner.nextInt());
        TipoPagamento tipoPagamento = EntradaDados.pedirTipoPagamento();
        cliente.setTipoPagamento(tipoPagamento);
    }

    public static TipoPagamento pedirTipoPagamento(){
        System.out.println("Qual o tipo de pagamento? 1 - Dinheiro / 2 - Cartao / 3 - Cheque");
        Integer tipoPagamento = scanner.nextInt();
        limparBufferEntrada();
        if (tipoPagamento >= 1 && tipoPagamento <= 3) {
            switch (tipoPagamento) {
                case 1:
                    return TipoPagamento.DINHEIRO;
                case 2:
                    return TipoPagamento.CARTAO;
                case 3:
                    return TipoPagamento.CHEQUE;
            }
        } else {
            System.out.println("Informe uma opção válida");
            return null;
        }
        return null;
    }

    public static Integer pedirQuantidadeParcelasAPagar(Cliente cliente){
        System.out.printf("Valor total a pagar: R$%.2f\n", cliente.getValor());
        System.out.println("Quantidade de parcelas a pagar: " + cliente.getQuantidadeParcelas());
        Double valorCadaParcela = cliente.getValor() / cliente.getQuantidadeParcelas();
        System.out.printf("Deseja pagar quantas parcelas de R$%.2f?\n", valorCadaParcela);
        Integer parcelasPagas = scanner.nextInt();
        if (parcelasPagas <= 0 || parcelasPagas > cliente.getQuantidadeParcelas()){
            return null;
        } else {
            return parcelasPagas;
        }
    }
}
