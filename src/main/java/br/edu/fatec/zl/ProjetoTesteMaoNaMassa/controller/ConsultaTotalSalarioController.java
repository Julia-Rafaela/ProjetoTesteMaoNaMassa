package br.edu.fatec.zl.ProjetoTesteMaoNaMassa.controller;

import java.math.BigDecimal;
import java.sql.SQLException;
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
public class ConsultaTotalSalarioController {
    @Autowired
    private FuncionarioDao fDao;


    @RequestMapping(name = "consultaTotalSalario", value = "/consultaTotalSalario", method = RequestMethod.GET)
    public ModelAndView consultaTotalSalarioGet(ModelMap model) {
        List<Funcionario> funcionarios = new ArrayList<>();
        String erro = "";
        String saida = "";

        try {
            funcionarios = fDao.listar(new Funcionario(0, "", null, BigDecimal.ZERO, ""));
            
        } catch (SQLException | ClassNotFoundException e) {
            erro = e.getMessage();
        }

        model.addAttribute("funcionarios", funcionarios);
        model.addAttribute("erro", erro);
        model.addAttribute("saida", saida);

        return new ModelAndView("consultaTotalSalario");
    }

    @RequestMapping(name = "consultaTotalSalario", value = "/consultaTotalSalario", method = RequestMethod.POST)
    public ModelAndView consultaTotalSalarioPost(@RequestParam Map<String, String> allRequestParam, ModelMap model) throws ClassNotFoundException, SQLException {
        
        List<Map<String, Object>> consultas = new ArrayList<>();
        String erro = "";
        String saida = "";

        consultas = fDao.listarTotalSalariosPorFuncao();

		if (consultas.isEmpty()) {
		    saida = "Consulta Inválida.";
		}

        model.addAttribute("consultas", consultas);
        model.addAttribute("erro", erro);
        model.addAttribute("saida", saida);

        return new ModelAndView("consultaTotalSalario");
    }
}
