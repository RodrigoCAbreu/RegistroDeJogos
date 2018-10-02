package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entidade.Jogo;

public class JogosDAOImpl implements JogosDAO {
	
	private static final String JDBC_URL = "jdbc:mariadb://localhost:3306/jogos";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASS = "";
	private Connection con;
	
	public JogosDAOImpl() throws GenericDAOException {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
		} catch (ClassNotFoundException | SQLException e) {
			throw new GenericDAOException( e );
		}
	}

	@Override
	public void adicionar(Jogo j) throws GenericDAOException {
		String sql = "INSERT INTO jogo (id, titulo, dificuldade)"
				+ "VALUES (?, ?, ?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, 0);
			pstmt.setString(2, j.getJogo());
			pstmt.setString(3, j.getDificuldade());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new GenericDAOException( e );
		}
		
		
	}

	@Override
	public List<Jogo> pesquisaPorJogo(String jogo) throws GenericDAOException {
		List<Jogo> lista = new ArrayList<>();
		String sql = "SELECT * FROM jogos WHERE titulo like ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + jogo + "%");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Jogo j = new Jogo();
				j.setId(rs.getLong("id"));
				j.setJogo(rs.getString("jogo"));
				j.setDificuldade(rs.getString("dificuldade"));
				lista.add(j);
			}
		} catch (SQLException e) {
			throw new GenericDAOException( e );
		}

		return lista;
		
	}

}
