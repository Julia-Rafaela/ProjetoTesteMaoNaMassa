<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href='<c:url value="/resources/menu.css" />'>
    <title>Menu</title>
    <link rel="stylesheet" href="<c:url value='/css/styles.css' />">
</head>
<body>
    <header>
        <nav class="navbar">
            <div class="logo">
                <a href="<c:url value='/' />">Sistema</a>
            </div>
            <ul class="nav-links">
                <li><a href="<c:url value='/funcionario' />" class="btn-menu">Funcionários</a></li>
                <li><a href="<c:url value='/aumento' />" class="btn-menu">Atualizar Salário</a></li>
                <li><a href="#">Consultas</a>
				<ul class="dropdown-submenu">
				  <li><a href="<c:url value='/consultaFuncao' />" class="btn-menu">Consulta Função</a></li>
				   <li><a href="<c:url value='/consultaMes' />" class="btn-menu">Consulta Aniversariante</a></li>
				    <li><a href="<c:url value='/consultaMaiorIdade' />" class="btn-menu">Consulta Funcionário mais velho</a></li>
				      <li><a href="<c:url value='/consultaTotalSalario' />" class="btn-menu">Consulta Total de Salários por Função</a></li>
				       <li><a href="<c:url value='/consultaSalarioMinimo' />" class="btn-menu">Consulta Quantidade de Salário minímo</a></li>				     
            </ul>
        </nav>
    </header>
</body>
</html>
    