package br.edu.fatec.zl.ProjetoTesteMaoNaMassa.persistence;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

@Repository
public class AumentoDao {

	private GenericDao gDao;

	public AumentoDao(GenericDao gDao) {
		this.gDao = gDao;
	}

	public String aumentarSalario(BigDecimal porcentagem) throws SQLException, ClassNotFoundException {
		String mensagem;

		try (Connection c = gDao.getConnection(); CallableStatement cs = c.prepareCall("{CALL AumentarSalario(?)}")) {

			cs.setBigDecimal(1, porcentagem);

			cs.execute();

			mensagem = "Salários aumentados com sucesso";
		} catch (SQLException e) {
			mensagem = "Erro ao tentar aumentar os salários: " + e.getMessage();
			throw e;
		}
		return mensagem;
	}
}
