<!DOCTYPE html>

<%@taglib	uri="http://java.sun.com/jsp/jstl/core"	prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<html lang="pt-br" xmlns="http://www.w3.org/1999/xhtml" xmls:th="http//thymeleaf.org">
    <head>
        <Title>Project Stages</Title>
        <meta charset="utf-8">
        <meta name="author" content="Affonso Ruiz, Aline Alves, Carlos Felipe, Joyce Brandão">
        <meta name="description" content="Site de organiação de criação de projetos">
        <meta name="keywords" content="Organização, Criação, Projetos, Desenvolvimentos">
        <meta name="viewport" content="width=device-width, initial-scale-1.0">
        <link rel=stylesheet type="text/css" href="CSS/style_home.css" media="screen">
        <link rel=stylesheet type="text/css" href="CSS/style_projetos-vazio.css">
        <link rel=stylesheet type="text/css" href="Bootstrap/css/bootstrap.min.css">
        <link rel="shortcut icon" href="img/LOGO-OFFICIAL-200x200%20cortada.png"> 
    </head>
    
    <body id="corpo" class="corpo">
  
  	<%@include	file="/WEB-INF/views/home.jsp"	%>
  
		<main>
            <section class="user-projetos-vazio">
            <div class="box-new-project">
             <div class="content-add-projeto">
                <h2 class="title-caixa-projeto">Crie um Novo Projeto!</h2>
                <form action="/projectstages_mvc/salvar/novo-projeto" method="post">
                <p class="title-text-projeto">Nome do Projeto:</p>    
                <input type="text" class="text-caixa-add-projeto" placeholder="Ex: Bills System" name="nome" value="Novo Projeto" id="nomeProjeto" maxlength="30" required>
                <p class="title-text-projeto">Nomes dos títulos dos grupos:</p>    
                <input type="text" class="text-caixa-add-projeto" placeholder="Afazeres" name="tarefa" value="Afazeres" size="30">
                <input type="text" class="text-caixa-add-projeto" placeholder="Em desenvolvimento" name="desenvolvimento" value="Em desenvolvimento" size="30">
                <input type="text" class="text-caixa-add-projeto" placeholder="Concluido" name="concluido" value="Concluido" size="30">
                <input type="submit" class="botao-caixa-add-projeto" value="Criar">
                <security:csrfInput/>
                </form>
                </div>  
            </div>
            </section>
        </main>

        <script src="JS/JS_home.js" type="text/javascript"></script>
        <script src="JS/JS_projetos.js" type="text/javascript"></script>
        <script src="Bootstrap/js/jquery-3.4.1.min.js" type="text/javascript"></script>
        <script src="Bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    </body>
    
</html>
