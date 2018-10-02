package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.GenericDAOException;
import dao.JogosDAO;
import dao.JogosDAOImpl;
import entidade.Jogo;


@WebServlet("/JogosController")
public class JogosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Para acessar utilize a pagina de <a href=\"./Jogos.jsp\">Jogos</a>").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		String msg = null;
		HttpSession session = request.getSession();
		try {
			JogosDAO jDAO = new JogosDAOImpl();
			if("adicionar".equals(cmd)) {
				Jogo j = new Jogo();
				j.setJogo(request.getParameter("txtJogo"));
				j.setDificuldade(request.getParameter("txtDificuldade"));
				jDAO.adicionar(j);
				msg = "Jogo foi adicionado com sucesso";
			} else if ("pesquisar".equals(cmd)){
				List<Jogo> lista = jDAO.pesquisaPorJogo(request.getParameter("txtJogo"));
				session.setAttribute("LISTA", lista);
				msg = "Foram encontrados " + lista.size() + " Jogos";
			}
		} catch (GenericDAOException e) {
			e.printStackTrace();
			msg = "Erro ao adicionar este jogo";
		}
		
		session.setAttribute("MENSAGEM", msg);
		response.sendRedirect("./Jogos.jsp");
		
	}

}
