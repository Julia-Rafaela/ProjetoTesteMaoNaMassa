package br.edu.fatec.zl.ProjetoTesteMaoNaMassa.controller;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
public class ConsultaFuncaoController {

	@Autowired
	private FuncionarioDao fDao;

	
	@RequestMapping(name = "consultaFuncao", value = "/consultaFuncao", method = RequestMethod.GET)
	public ModelAndView consultaFuncaoGet(ModelMap model) {
		return new ModelAndView("consultaFuncao");
	}

	@RequestMapping(name = "consultaFuncao", value = "/consultaFuncao", method = RequestMethod.POST)
	public ModelAndView consultaFuncaoPost(@RequestParam Map<String, String> allRequestParam, ModelMap model) {
		String cmd = allRequestParam.get("cmd");
		String erro = "";
		Map<String, List<Funcionario>> funcionariosPorFuncao = null;

		try {
			if ("Listar".equals(cmd)) {

				List<Funcionario> funcionarios = fDao.listar(new Funcionario(0, "", null, BigDecimal.ZERO, ""));

				funcionariosPorFuncao = funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));
			}
		} catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		}

		model.addAttribute("erro", erro);
		model.addAttribute("funcionariosPorFuncao", funcionariosPorFuncao);

		return new ModelAndView("consultaFuncao");
	}
}
