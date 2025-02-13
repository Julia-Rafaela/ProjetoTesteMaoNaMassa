<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Consulta por Função</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/consultaFuncao.css">
</head>
<body>

	<div class="menu">
		<jsp:include page="menu.jsp" />
	</div>


	<div class="container">
		<h2>Consultar Funcionários por Função</h2>


		<form method="POST"
			action="${pageContext.request.contextPath}/consultaFuncao"
			class="form-consulta">
			<button type="submit" name="cmd" value="Listar" class="btn-submit">Consultar</button>
		</form>


		<c:if test="${not empty erro}">
			<p class="erro">${erro}</p>
		</c:if>


		<c:if test="${not empty funcionariosPorFuncao}">
			<c:forEach var="entry" items="${funcionariosPorFuncao}">
				<h3 class="h2">Função: ${entry.key}</h3>
				<table border="1">
					<thead>
						<tr>
							<th>Código</th>
							<th>Nome</th>
							<th>Data de Nascimento</th>
							<th>Salário</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="funcionario" items="${entry.value}">
							<tr>
								<td>${funcionario.id}</td>
								<td>${funcionario.nome}</td>
								<td><fmt:formatDate
										value="${funcionario.dataNascimentoComoDate}"
										pattern="dd/MM/yyyy" /></td>
								<td>${funcionario.salario}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:forEach>
		</c:if>
	</div>
</body>
</html>