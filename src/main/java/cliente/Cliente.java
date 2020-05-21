package cliente;

import enums.TipoPagamento;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cliente {
    private String nome;
    private String cpf;
    private Double valor;
    private Integer quantidadeParcelas;
    private TipoPagamento tipoPagamento;

    public String toString() {
        return "Cliente [Nome: " + this.nome + ", CPF: " + this.cpf + "]";
    }
}
