<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="entidade.Jogo, java.util.List, java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registro de Jogos</title>
<link rel="stylesheet" href="./css/bootstrap.min.css"/>
<script src="./js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h2>Registro de Jogos</h2>
	</div>
	
	<% String msg = (String)session.getAttribute("MENSAGEM");
		List<Jogo> lista = (List<Jogo>)session.getAttribute("LISTA");
		if (lista == null) {
			lista = new ArrayList<Jogo>();
		}
		if (msg != null) {
			session.setAttribute("MENSAGEM", null);
		
			%>
		}
	<h3 class="alert alert-danger"><%=msg%></h3>
	<% } %>
	
	<form action="./JogosController" method="post">
		<div class="container">
			<div class="form-group">
				<label for="txtId">Id</label>
    			<input type="text" class="form-control" id="txtId" name="txtId" readonly/>
			</div>
			<div class="container">
				<div class="form-group">
    			<label for="txtJogo">T�tulo do Jogo:</label>
    			<input type="text" class="form-control" id="txtJogo" name="txtJogo"/>
  				</div>  	
			</div>
			<div class="form-group">
    			<label for="txtDificuldade">Dificuldade</label>
    			<select class="form-control" id="txtDificuldade" name="txtDificuldade">
      				<option value="muitoFacil">Muito F�cil</option>
      				<option value="facil">F�cil</option>
      				<option value="normal">Normal</option>
				    <option value="dificil">Dif�cil</option>
				    <option value="muitoDificil">Muito Dif�cil</option>
				</select>
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-primary" name="cmd" value="adicionar">Adicionar</button>
				<button type="submit" class="btn btn-primary" name="cmd" value="pesquisar">Pesquisar</button>
			</div>		
		</div>
	</form>
	
</body>
</html>