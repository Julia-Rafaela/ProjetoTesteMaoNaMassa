<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Consulta Total de Salários por Função</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/consultaTotalSalario.css">
</head>
<body>


	<div class="menu">
		<jsp:include page="menu.jsp" />
	</div>


	<div class="container">
		<h2>Consultar Total de Salários Por Função</h2>


		<form method="POST"
			action="${pageContext.request.contextPath}/consultaTotalSalario"
			class="form-consulta">
			<button type="submit" name="cmd" value="Listar" class="btn-submit">Consultar</button>
		</form>


		<c:if test="${not empty erro}">
			<p class="erro">${erro}</p>
		</c:if>


		<c:if test="${not empty consultas}">
			<table>
				<thead>
					<tr>
						<th>Função</th>
						<th>Total de Salário</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="entry" items="${consultas}">
						<tr>
							<td>${entry["funcao"]}</td>
							<td><fmt:formatNumber value="${entry['Total_Salarios']}"
									type="currency" currencySymbol="R$" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>

	</div>

</body>
</html>
