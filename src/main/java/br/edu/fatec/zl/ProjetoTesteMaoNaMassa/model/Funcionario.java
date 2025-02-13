package br.edu.fatec.zl.ProjetoTesteMaoNaMassa.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Funcionario extends Pessoa {
	private int id;
    private BigDecimal salario;
    private String funcao;
    private int idade;

    
    public Funcionario(int id, String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.id = id;
        this.salario = salario;
        this.funcao = funcao;
    }
    
   
	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", salario=" + salario + ", funcao=" + funcao + "]";
	}


}
