<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href='<c:url value="/resources/aumento.css" />'>
<title>Atualizar Salário</title>
</head>
<body>
	<div class="menu">
		<jsp:include page="menu.jsp" />
	</div>

	<div class="container">
		<h1>Aumento de Salário</h1>


		<form method="POST"
			action="${pageContext.request.contextPath}/aumento"
			class="form-aumento">
			<div class="input-group">
				<label for="porcentagem">Porcentagem do Aumento (%)</label> <input
					type="text" id="porcentagem" name="porcentagem"
					placeholder="Informe a porcentagem" required />
			</div>
			<button type="submit" class="btn-submit">Aplicar Aumento</button>
		</form>

		<div class="resultado">
			<h2>Resultado:</h2>
			<p id="resultadoMensagem">${resultadoMensagem}</p>
			<p id="erro">${erro}</p>
		</div>
	</div>
</body>
</html>
