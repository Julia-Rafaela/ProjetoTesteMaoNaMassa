package br.edu.fatec.zl.ProjetoTesteMaoNaMassa.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fatec.zl.ProjetoTesteMaoNaMassa.model.Funcionario;
import br.edu.fatec.zl.ProjetoTesteMaoNaMassa.persistence.FuncionarioDao;

@Controller
public class ConsultaSalarioMinimoController {

	@Autowired
	private FuncionarioDao fDao;

	private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");

	@RequestMapping(name = "consultaSalarioMinimo", value = "/consultaSalarioMinimo", method = RequestMethod.GET)
	    public ModelAndView consultarSalarioMinimo(@RequestParam Map<String, String> allRequestParam, ModelMap model) {
	        List<Funcionario> funcionarios = new ArrayList<>();
	        Map<Funcionario, BigDecimal> salariosMinimos = new HashMap<>();	
	        String erro = "";
	        String cmd = allRequestParam.get("cmd");
	        
	        try {
	              	 if ("Listar".equals(cmd)) {
	            funcionarios = fDao.listar(new Funcionario(0, "", null, BigDecimal.ZERO, ""));
	              	 }
	            for (Funcionario f : funcionarios) {
	                BigDecimal qtdSalarios = f.getSalario().divide(SALARIO_MINIMO, 2, RoundingMode.HALF_UP);
	                salariosMinimos.put(f, qtdSalarios);
	            }
	        } catch (SQLException | ClassNotFoundException e) {
	            erro = e.getMessage();
	            System.out.println("Erro ao consultar salários mínimos: " + erro);
	        }
	        
	        model.addAttribute("erro", erro);
	        model.addAttribute("salariosMinimos", salariosMinimos);
	        model.addAttribute("funcionarios", funcionarios);
	        
	        return new ModelAndView("consultaSalarioMinimo");
	    }
}
