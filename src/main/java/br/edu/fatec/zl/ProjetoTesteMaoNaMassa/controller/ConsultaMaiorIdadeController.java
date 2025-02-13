package br.edu.fatec.zl.ProjetoTesteMaoNaMassa.controller;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.fatec.zl.ProjetoTesteMaoNaMassa.model.Funcionario;
import br.edu.fatec.zl.ProjetoTesteMaoNaMassa.persistence.FuncionarioDao;

@Controller
public class ConsultaMaiorIdadeController {

	    @Autowired
	    private FuncionarioDao fDao;

	    
	    @RequestMapping(name = "consultaMaiorIdade", value = "/consultaMaiorIdade", method = RequestMethod.GET)
	    public String consultarMaiorIdade(@RequestParam Map<String, String> allRequestParam, ModelMap model) {
	        try {
	          
	            Funcionario funcionario = fDao.obterFuncionarioMaiorIdade();
	            
	            
	            if (funcionario != null) {
	                model.addAttribute("nome", funcionario.getNome());
	                model.addAttribute("idade", funcionario.getIdade());
	            } else {
	                model.addAttribute("mensagem", "Nenhum funcionário encontrado.");
	            }
	        } catch (SQLException | ClassNotFoundException e) {
	            model.addAttribute("mensagem", "Erro ao buscar dados do funcionário.");
	            e.printStackTrace();
	        }
	        return "consultaMaiorIdade"; 
	    }
}