package br.edu.fatec.zl.ProjetoTesteMaoNaMassa.controller;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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
public class FuncionarioController {

	@Autowired
	private FuncionarioDao fDao;

	@RequestMapping(name = "funcionario", value = "/funcionario", method = RequestMethod.GET)
	public ModelAndView funcionarioGet(@RequestParam Map<String, String> allRequestParam, ModelMap model) {
		List<Funcionario> funcionarios = new ArrayList<>();
		String erro = "";
		String cmd = allRequestParam.get("cmd");

		try {
			if ("Listar".equals(cmd)) {
				funcionarios = fDao.listar(new Funcionario(0, "", null, BigDecimal.ZERO, ""));
			}
		} catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
			System.out.println("Erro ao listar funcionários: " + erro);
		}

		model.addAttribute("erro", erro);
		model.addAttribute("funcionarios", funcionarios);

		return new ModelAndView("funcionario");
	}

	@RequestMapping(name = "funcionario", value = "/funcionario", method = RequestMethod.POST)
	public ModelAndView funcionarioPost(@RequestParam Map<String, String> allRequestParam, ModelMap model) {
		String cmd = allRequestParam.get("cmd");
		String idStr = allRequestParam.get("codigo");
		String nome = allRequestParam.get("nome");
		String funcao = allRequestParam.get("funcao");
		String dataNascimento = allRequestParam.get("dataNascimento");
		String salarioStr = allRequestParam.get("salario");

		Funcionario f = null;
		List<Funcionario> funcionarios = new ArrayList<>();
		String saida = "";
		String erro = "";

		int id = (idStr != null && !idStr.isEmpty()) ? Integer.parseInt(idStr) : 0;
		f = new Funcionario(id, nome,
				(dataNascimento != null && !dataNascimento.isEmpty()) ? LocalDate.parse(dataNascimento) : null,
				(salarioStr != null && !salarioStr.isEmpty()) ? new BigDecimal(salarioStr) : BigDecimal.ZERO, funcao);

		try {
			if ("Cadastrar".equals(cmd)) {
				saida = fDao.inserirFuncionario(f);
			} else if ("Excluir".equals(cmd)) {
				f = fDao.consultar(f);
				if (f != null) {
					saida = fDao.deletarFuncionario(f);
				} else {
					erro = "Funcionário não encontrado.";
				}
			} else if ("Listar".equals(cmd)) {
				funcionarios = fDao.listar(new Funcionario(0, "", null, BigDecimal.ZERO, ""));
			}
		} catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		}

		model.addAttribute("saida", saida);
		model.addAttribute("erro", erro);
		model.addAttribute("funcionarios", funcionarios);

		return new ModelAndView("funcionario");
	}
}
