package br.edu.fatec.zl.ProjetoTesteMaoNaMassa.model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Pessoa {
	    private String nome;
	    private LocalDate dataNascimento;
	    
	    public java.util.Date getDataNascimentoComoDate() {
	        if (dataNascimento != null) {
	            return Date.from(dataNascimento.atStartOfDay(ZoneId.systemDefault()).toInstant());
	        }
	        return null;
	    }

	    public Pessoa(String nome, LocalDate dataNascimento) {
	        this.nome = nome;
	        this.dataNascimento = dataNascimento;
	    }

	}

