<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href='<c:url value="/resources/funcionario.css" />'>
    <title>Funcionário</title>
</head>
<body>
<div class="menu">
		<jsp:include page="menu.jsp" />
	</div>
    <div class="container">
       
        <h2>Cadastro de Funcionário</h2>
        <form method="post" action="<c:url value='/funcionario' />">
            <input type="hidden" name="cmd" value="Cadastrar" />
            <label for="nome">Nome:</label>
            <input type="text" name="nome" id="nome" /><br><br>
            <label for="dataNascimento">Data de Nascimento:</label>
            <input type="date" name="dataNascimento" id="dataNascimento" /><br><br>
            <label for="salario">Salário:</label>
            <input type="number" name="salario" id="salario" step="0.01" /><br><br>
            <label for="funcao">Função:</label>
            <input type="text" name="funcao" id="funcao" /><br><br>
            <button type="submit">Cadastrar</button>
        </form>

        
        <c:if test="${not empty saida}">
            <div class="success">${saida}</div>
        </c:if>
        <c:if test="${not empty erro}">
            <div class="error">${erro}</div>
        </c:if>

        
        <h2>Listar Funcionários</h2>
        <form method="get" action="<c:url value='/funcionario' />">
            <input type="hidden" name="cmd" value="Listar" />
            <button type="submit">Listar Funcionários</button>
        </form>

        
        <c:if test="${not empty funcionarios}">
            <h2>Funcionários Cadastrados</h2>
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>Data de Nascimento</th>
                        <th>Salário</th>
                        <th>Função</th>
                        <th>Ação</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="f" items="${funcionarios}">
                        <tr>
                            <td>${f.id}</td>
                            <td>${f.nome}</td>
                           <td><fmt:formatDate value="${f.dataNascimentoComoDate}"
									pattern="dd/MM/yyyy" /></td>
                            <td>${f.salario}</td>
                            <td>${f.funcao}</td>
                            <td>
                                <form method="post" action="<c:url value='/funcionario' />">
                                    <input type="hidden" name="cmd" value="Excluir" />
                                    <input type="hidden" name="codigo" value="${f.id}" />
                                    <button type="submit">Excluir</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
</body>
</html>

