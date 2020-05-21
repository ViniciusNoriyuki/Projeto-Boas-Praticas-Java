package cliente;

import bandodedados.BancoDados;
import validador.Cpf;
import validador.Parcelas;
import validador.Valor;

import java.util.Iterator;
import java.util.List;

public class ClienteService {
    public ClienteService(){

    }

    public Cliente salvar(Cliente cliente) {
        return !this.validarDados(cliente) ? null : BancoDados.criar(cliente);
    }

    public Cliente atribuirPagamento(Cliente cliente, Integer parcelasPagas) {
        Double valorPorParcela = cliente.getValor() / cliente.getQuantidadeParcelas();
        cliente.setValor(cliente.getValor() - (valorPorParcela * parcelasPagas));
        cliente.setQuantidadeParcelas(cliente.getQuantidadeParcelas() - parcelasPagas);
        return cliente;
    }

    public Cliente localizarClientePorCpf(String cpf) {
        List<Cliente> clientes = BancoDados.consultarClientes();
        Iterator var4 = clientes.iterator();

        while(var4.hasNext()) {
            Cliente cliente = (Cliente)var4.next();
            if (cliente.getCpf().contentEquals(cpf)) {
                return cliente;
            }
        }
        return null;
    }

    private Boolean validarDados(Cliente cliente) {
        if (cliente.getNome().isEmpty()) {
            System.out.println("Nome inválido");
            return false;
        } else if (!Cpf.validar(cliente.getCpf())) {
            System.out.println("CPF inválido");
            return false;
        } else if (!Valor.validar(cliente.getValor())) {
            System.out.println("Valor inválido");
            return false;
        } else if (!Parcelas.validar(cliente.getQuantidadeParcelas())) {
            System.out.println("Parcela inválida");
            return false;
        } else {
            return true;
        }
    }

    public boolean validarCpfExistente(String cpf){
        List<Cliente> clientes = BancoDados.consultarClientes();
        Iterator var4 = clientes.iterator();

        while(var4.hasNext()) {
            Cliente cliente = (Cliente)var4.next();
            if (cliente.getCpf().contentEquals(cpf)) {
                return true;
            }
        }
        return false;
    }
}
