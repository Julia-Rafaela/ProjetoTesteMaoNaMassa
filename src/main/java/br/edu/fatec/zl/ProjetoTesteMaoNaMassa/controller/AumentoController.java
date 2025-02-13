package br.edu.fatec.zl.ProjetoTesteMaoNaMassa.controller;

import java.math.BigDecimal;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fatec.zl.ProjetoTesteMaoNaMassa.persistence.AumentoDao;

@Controller
public class AumentoController {

	@Autowired
	private AumentoDao aumentoDao;

	@RequestMapping(name = "aumento", value = "/aumento", method = RequestMethod.GET)
	public ModelAndView aumentoGet() {
		return new ModelAndView("aumento");
	}

	@RequestMapping(name = "aumento", value = "/aumento", method = RequestMethod.POST)
	public ModelAndView aumentoPost(@RequestParam("porcentagem") String porcentagemStr, ModelMap model) {
		String resultado = "";
		String erro = "";

		if (porcentagemStr != null && !porcentagemStr.isEmpty()) {
			try {

				BigDecimal porcentagem = new BigDecimal(porcentagemStr);

				resultado = aumentoDao.aumentarSalario(porcentagem);
			} catch (SQLException | ClassNotFoundException e) {
				erro = e.getMessage();
			}
		} else {
			erro = "Porcentagem do aumento não fornecida.";
		}

		model.addAttribute("resultadoMensagem", resultado);
		model.addAttribute("erro", erro);

		return new ModelAndView("aumento");
	}
}
