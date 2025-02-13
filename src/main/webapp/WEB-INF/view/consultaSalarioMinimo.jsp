<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Consulta de Salários Mínimos por Funcionário</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/consultaSalarioMinimo.css">
</head>
<body>

  
    <div class="menu">
        <jsp:include page="menu.jsp" />
    </div>

    
    <div class="container">
        <h2>Consultar Salários Mínimos por Funcionário</h2>

        
        <form method="GET"
            action="${pageContext.request.contextPath}/consultaSalarioMinimo"
            class="form-consulta">
            <button type="submit" name="cmd" value="Listar" class="btn-submit">Consultar</button>
        </form>

        
        <c:if test="${not empty erro}">
            <p class="erro">${erro}</p>
        </c:if>

        
        <c:if test="${not empty salariosMinimos}">
            <table>
                <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Função</th>
                        <th>Salário</th>
                        <th>Qtd. Salários Mínimos</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="entry" items="${salariosMinimos}">
                        <tr>
                            <td>${entry.key.nome}</td>
                            <td>${entry.key.funcao}</td>
                            <td>
                                <fmt:formatNumber value="${entry.key.salario}" type="currency" currencySymbol="R$" />
                            </td>
                            <td>
                                <fmt:formatNumber value="${entry.value}" minFractionDigits="2" maxFractionDigits="2" />
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

    </div>

</body>
</html>
