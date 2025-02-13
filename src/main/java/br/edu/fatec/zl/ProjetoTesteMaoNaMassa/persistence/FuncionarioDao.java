package br.edu.fatec.zl.ProjetoTesteMaoNaMassa.persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import br.edu.fatec.zl.ProjetoTesteMaoNaMassa.model.Funcionario;

@Repository
public class FuncionarioDao {
    private GenericDao gDao;

    public FuncionarioDao(GenericDao gDao) {
        this.gDao = gDao;
    }

    public String inserirFuncionario(Funcionario f) throws SQLException, ClassNotFoundException {
        String mensagem;
        try (Connection c = gDao.getConnection();
             CallableStatement cs = c.prepareCall("{CALL InserirFuncionario(?,?,?,?,?)}")) {

            cs.setString(1, f.getNome());
            cs.setDate(2, java.sql.Date.valueOf(f.getDataNascimento()));
            cs.setBigDecimal(3, f.getSalario());
            cs.setString(4, f.getFuncao());
            cs.registerOutParameter(5, Types.VARCHAR);

            cs.execute();
            mensagem = cs.getString(5);
        }
        return mensagem;
    }

    public String deletarFuncionario(Funcionario f) throws SQLException, ClassNotFoundException {
        String mensagem;
        try (Connection c = gDao.getConnection();
             CallableStatement cs = c.prepareCall("{CALL DeletarFuncionario(?,?)}")) { 

            cs.setInt(1, f.getId());  
            cs.registerOutParameter(2, Types.VARCHAR);  
            cs.execute();
            mensagem = cs.getString(2);  
        }
        return mensagem;
    }


    public Funcionario consultar(Funcionario f) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Funcionario WHERE id = ?";
        Funcionario funcionario = null;

        try (Connection c = gDao.getConnection();
             PreparedStatement stmt = c.prepareStatement(sql)) {

            stmt.setInt(1, f.getId());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                funcionario = new Funcionario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDate("data_nascimento").toLocalDate(),
                        rs.getBigDecimal("salario"),
                        rs.getString("funcao")
                );
            }
        }
        return funcionario;
    }

    public List<Funcionario> listar(Funcionario f) throws SQLException, ClassNotFoundException {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "EXEC OrdemAlfabetica";

        try (Connection c = gDao.getConnection();
             PreparedStatement stmt = c.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Funcionario fun = new Funcionario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDate("data_nascimento").toLocalDate(),
                        rs.getBigDecimal("salario"),
                        rs.getString("funcao")
                );
                funcionarios.add(fun);
            }
        }
        return funcionarios;
    }
    public Funcionario obterFuncionarioMaiorIdade() throws SQLException, ClassNotFoundException {
        Funcionario funcionario = null;
        
        try (Connection c = gDao.getConnection();
             CallableStatement cs = c.prepareCall("{CALL MaiorIdade}")) {

           
            ResultSet rs = cs.executeQuery();

            
            if (rs.next()) {
                String nome = rs.getString("Nome");
                int idade = rs.getInt("Idade");

                
                funcionario = new Funcionario(idade, nome, null, null, nome);
                funcionario.setNome(nome);
                funcionario.setIdade(idade);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new SQLException("Erro ao chamar a procedure MaiorIdade", e);
        }

        return funcionario;
    }
    public List<Map<String, Object>> listarAniversariante(int idMes) throws SQLException, ClassNotFoundException {
		List<Map<String, Object>> aniversariantes = new ArrayList<>();
		Connection c = gDao.getConnection();
		String sql = "{CALL ListarAniversariantes(?)}"; 

		try (CallableStatement cs = c.prepareCall(sql)) {
			cs.setInt(1, idMes); 

			
			try (ResultSet rs = cs.executeQuery()) {
				while (rs.next()) {
					Map<String, Object> aniversariante = new HashMap<>();
					aniversariante.put("nome", rs.getString("nome"));
					Date utilDate = Date.valueOf(rs.getDate("data_nascimento").toLocalDate());
					aniversariante.put("data_nascimento", utilDate);

					aniversariantes.add(aniversariante);
				}
			}
		} catch (SQLException e) {
			System.err.println("Erro ao listar aniversariantes: " + e.getMessage());
		} finally {
			c.close();
		}

		return aniversariantes;
	}
    public List<Map<String, Object>> listarTotalSalariosPorFuncao() {
    	 List<Map<String, Object>> totalSalarios = new ArrayList<>();
         String sql = "{CALL TotalSalario}";

         try (Connection c = gDao.getConnection();
              CallableStatement stmt = c.prepareCall(sql);
              ResultSet rs = stmt.executeQuery()) {

             while (rs.next()) {
                 Map<String, Object> dados = new HashMap<>();
                 dados.put("funcao", rs.getString("funcao"));
                 dados.put("Total_Salarios", rs.getDouble("Total_Salarios"));
                 totalSalarios.add(dados);
             }

         } catch (Exception e) {
             e.printStackTrace();
         }
         return totalSalarios;
     }
}
