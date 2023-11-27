package br.com.biblioteca.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.biblioteca.factory.ConnectionFactory;
import br.com.biblioteca.model.Livro;

public class LivroDAO {
	public void save(Livro livro) {
		String sql = "INSERT INTO livros(titulo, autor, editora) VALUES (?, ?, ?)";
		
		Connection connection = null;
		PreparedStatement pstm = null;
		
		try {
			connection = ConnectionFactory.createConnectionToMySQL();
			pstm = (PreparedStatement) connection.prepareStatement(sql);
			pstm.setString(1, livro.getTitulo());
			pstm.setString(2, livro.getAutor());
			pstm.setString(3, livro.getEditora());
			
			pstm.execute();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(pstm!=null) {
					pstm.close();
				}
				if(connection!=null) {
					connection.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public List<Livro> listar() {
		String sql = "SELECT * FROM livros";
		
		List<Livro> livros = new ArrayList<Livro>();
		
		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
			connection = ConnectionFactory.createConnectionToMySQL();
			pstm = (PreparedStatement) connection.prepareStatement(sql);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Livro livro = new Livro();
				livro.setId(rset.getString("id"));
				livro.setTitulo(rset.getString("titulo"));
				livro.setAutor(rset.getString("autor"));
				livro.setEditora(rset.getString("editora"));
				livros.add(livro);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(rset!=null) {
					rset.close();
				}
				if(pstm!=null) {
					pstm.close();
				}
				if(connection!=null) {
					connection.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return livros;

	}
}
