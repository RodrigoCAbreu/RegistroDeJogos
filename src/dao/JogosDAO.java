package dao;

import java.util.List;

import entidade.Jogo;

public interface JogosDAO {
	public void adicionar(Jogo j) throws GenericDAOException;
	public List<Jogo> pesquisaPorJogo(String jogo) throws GenericDAOException;
}
