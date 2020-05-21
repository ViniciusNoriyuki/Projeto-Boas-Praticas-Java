package sistema;

import bandodedados.BancoDados;
import cliente.Cliente;
import cliente.ClienteService;
import entradadados.EntradaDados;

public class Sistema {
    ClienteService clienteService = new ClienteService();

    public Sistema(){

    }

    public void pedirDadosECriarCliente() {
        Cliente cliente = EntradaDados.pedirDadosCliente();
        if (this.clienteService.validarCpfExistente(cliente.getCpf())){
            System.out.println("Cliente com este cpf ja cadastrado");
        } else {
            this.clienteService.salvar(cliente);
        }
    }

    public void exibirClientesCadastrados() {
        System.out.println(BancoDados.consultarClientes());
    }

    public void visualizarClientesComSolicitacoes() {
        BancoDados.exibirClientesComSolicitacao();
    }

    public void solicitarPagamentoEQuantidadeParcelas(){
        String cpf = EntradaDados.pedirCpf();
        if (cpf.isEmpty()) {
            System.out.println("CPF em branco");
        } else {
            Cliente cliente = this.clienteService.localizarClientePorCpf(cpf);
            if (cliente == null) {
                System.out.println("Cliente não encontrado");
            } else {
                EntradaDados.pedirValorEQuantidadeParcelas(cliente);
            }
        }
    }

    public void efetuarPagamento(){
        String cpf = EntradaDados.pedirCpf();
        if (cpf.isEmpty()){
            System.out.println("CPF em branco");
        } else {
            Cliente cliente = this.clienteService.localizarClientePorCpf(cpf);
            if (cliente == null){
                System.out.println("Cliente não encontrado");
            }
            else if (cliente.getQuantidadeParcelas() == 0) {
                System.out.println("Cliente nao possui parcelas para pagamento");
            }
            else {
                Integer parcelasPagas = EntradaDados.pedirQuantidadeParcelasAPagar(cliente);
                this.clienteService.atribuirPagamento(cliente, parcelasPagas);
            }
        }
    }
}
