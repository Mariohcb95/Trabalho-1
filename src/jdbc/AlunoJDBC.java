package jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import entities.Aluno;

public class AlunoJDBC {

	Connection con;
	String sql;
	PreparedStatement pst;
	
	public void salvar(Aluno a) throws SQLException {
		
		try {
			con = db.getConexao();
			sql = "INSERT INTO aluno (nome, sexo, dt_nasc) VALUES (?,?, ?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, a.getNome());
			pst.setString(2, a.getSexo());
			Date dataSql = Date.valueOf( a.getDt_nasc() );
			pst.setDate(3, dataSql);
			pst.executeUpdate();
			System.out.println("\nCadastro do aluno realizado com sucesso!");
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	public List<Aluno> listar() throws SQLException{
		try {
			con = db.getConexao();
			sql = "SELECT * FROM aluno ORDER BY id";			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			List<Aluno> alunos = new ArrayList<Aluno>();

			while(rs.next()) {
				Aluno aluno = new Aluno();
				aluno.setId(rs.getInt(1));
				aluno.setNome(rs.getString(2));
				aluno.setSexo(rs.getString(3));
				aluno.setDt_nasc(LocalDate.ofInstant(rs.getTimestamp(4).toInstant(), ZoneId.systemDefault()));
				alunos.add(aluno);
				
			}
			return alunos;

			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return null;
	}
	
	public void apagar(int id) throws SQLException {
		try {
			con = db.getConexao();
			sql = "DELETE FROM aluno WHERE id=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			pst.executeUpdate();
			System.out.println("\nAluno apagado com sucesso!");
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	public void alterar(Aluno a) throws SQLException {
		try {
			con = db.getConexao();
			sql = "UPDATE aluno SET nome=?, sexo=?, dt_nasc=? WHERE id=?";
			pst = con.prepareStatement(sql);
			pst.setString(1, a.getNome());
			pst.setString(2, a.getSexo());
			Date dataSql = Date.valueOf( a.getDt_nasc() );
			pst.setDate(3, dataSql);
			pst.setInt(4, a.getId());
			pst.executeUpdate();
			System.out.println("\nAluno alterado com sucesso!");
			
		} catch (Exception e) {
			System.out.println(e);
		}
	
	}
}
