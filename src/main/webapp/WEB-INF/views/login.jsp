<!DOCTYPE html>

<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@	taglib	uri="http://www.springframework.org/tags/form"	prefix="form"%>
<html lang="pt-br" xmlns="http://www.w3.org/1999/xhtml" xmls:th="http//thymeleaf.org">
    <head>
        <title>Project Stages</title>
        <meta charset="utf-8">
        <meta name="author" content="Affonso Ruiz, Aline Alves, Carlos Felipe, Joyce Brandão">
        <meta name="description" content="Login usuario">
        <meta name="keywords" content="Cadastro">
        <meta name="viewport" content="width=device-width, initial-scale-1.0">
        <link rel=stylesheet type="text/css" href="CSS/normalize.css">
        <link rel="stylesheet" href="CSS/style_login.css" media=screen>
        <link rel="shortcut icon" href="img/LOGO-OFFICIAL-200x200%20cortada.png">
        <link rel=stylesheet type="text/css" href="Bootstrap/css/bootstrap.min.css">
    </head>
    <body class="principal">
        <main>
            <section class="gallery">
                <img src="img/LOGO-OFFICIAL-200x200%20cortada.png">
            </section>
            <form:form servletRelativeAction="/projectstages_mvc/login" class="cadastro">
             <legend class="legenda">Login</legend>
                <input class="caixa" name="email" type="email" size="30" placeholder="Email" id="email" required>
                <input class="caixa" name="senha" type="password" size="30" placeholder="Senha" id="senha" required>
                <input class="envio" type="submit" value="Entrar" id="btnEnvio">
                <p><a href="solicitacao-email.html" class="link">Esqueceu sua senha?</a></p>
                <p><a href="/projectstages_mvc/cadastro" class="link">Ainda não possui conta? Crie a sua aqui.</a></p>
           		<security:csrfInput/>
            </form:form>
                <div class="card01">
                    <img src="img/Tela_Cadastro_Login/meninna-calendario.png">
                </div>
                <div class="card02">
                    <img src="img/Tela_Cadastro_Login/menino2.svg">
                </div>
        </main>
        
        
        <script src="Bootstrap/js/jquery-3.4.1.min.js" type="text/javascript"></script>
        <script src="Bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    </body>
</html>