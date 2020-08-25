<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<html lang="pt-br" xmlns="http://www.w3.org/1999/xhtml" xmls:th="http//thymeleaf.org">
    <head>
        <title>Project Stages</title>
        <meta charset="utf-8">
        <meta name="author" content="Affonso Ruiz, Aline Alves, Carlos Felipe, Joyce Brandão">
        <meta name="description" content="Solicitação de e-mail">
        <meta name="keywords" content="Email">
        <meta name="viewport" content="width=device-width, initial-scale-1.0">
        <link rel=stylesheet type="text/css" href="CSS/normalize.css">
        <link rel="stylesheet" href="CSS/style_alterar-senha.css" media=screen>
        <link rel="shortcut icon" href="img/LOGO-OFFICIAL-200x200%20cortada.png">
        <link rel=stylesheet type="text/css" href="Bootstrap/css/bootstrap.min.css">
    </head>
    <body class="principal">
        <main>
            <section class="gallery">
                <img src="img/LOGO-OFFICIAL-200x200%20cortada.png">
            </section>
            <form action="/projectstages_mvc/alterar-senha/atualizar-senha" method="post" id="form-esqueceu-senha" class="cadastro">
                <legend class="legenda"><b>Alterar</b> senha?</legend>
                
                <label for="novaSenha" id="senhaNova">Nova senha
                <input class="caixa" name="novaSenha" type="password" size="30" id="novaSenha" required>
                </label>
                
                <label for="novaSenhaConfirmada" id="senhaNovaConfirmada">Confirmar senha
                <input class="caixa" name="novaSenhaConfirmada" type="password" size="30" id="novaSenhaConfirmada" required>
                </label>
                
                <input class="envio" type="submit" value="Alterar senha">
                <security:csrfInput/>
            </form>
        </main>
        <script src="Bootstrap/js/jquery-3.4.1.min.js" type="text/javascript"></script>
        <script src="Bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="JS/JS_alterar-senha.js" type="text/javascript"></script>
    </body>
</html>