package bandodedados;

import cliente.Cliente;

import java.util.ArrayList;
import java.util.List;

public class BancoDados {
    private static List<Cliente> clientes = new ArrayList();

    public BancoDados(){

    }

    public static Cliente criar(Cliente cliente) {
        clientes.add(cliente);
        return cliente;
    }

    public static List<Cliente> consultarClientes() {
        return clientes;
    }

    public static void exibirClientesComSolicitacao() {
        for (int i = 0; i < clientes.size(); i++){
            if (clientes.get(i).getValor() != null && clientes.get(i).getQuantidadeParcelas() != 0){
                System.out.println("Cliente: " + clientes.get(i).getNome() + " - Valor restante a pagar: R$" + clientes.get(i).getValor()
                                    + " Quantidade de parcelas restante: " + clientes.get(i).getQuantidadeParcelas()
                                    + " Forma de pagamento: " + clientes.get(i).getTipoPagamento());
            }
        }
    }
}
