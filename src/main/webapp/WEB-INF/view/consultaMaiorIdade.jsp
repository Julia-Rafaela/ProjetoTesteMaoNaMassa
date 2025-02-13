<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Consultar Funcionário de Maior Idade</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/consultaMaiorIdade.css">
</head>
<body>


	<div class="menu">
		<jsp:include page="menu.jsp" />
	</div>


	<div class="container">
		<h2>Funcionário de Maior Idade</h2>


		<c:if test="${not empty erro}">
			<p class="erro">${erro}</p>
		</c:if>


		<c:if test="${not empty nome}">
			<div class="resultado">
				<h3>Resultado:</h3>
				<table>
					<thead>
						<tr>
							<th>Nome</th>
							<th>Idade</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${nome}</td>
							<td>${idade}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</c:if>
	</div>
</body>
</html>