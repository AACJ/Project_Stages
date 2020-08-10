<!DOCTYPE html>

<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<html lang="pt-br" xmlns="http://www.w3.org/1999/xhtml" xmls:th="http//thymeleaf.org">
    <head>
        <title>Project Stages</title>
        <meta charset="utf-8">
        <meta name="author" content="Affonso Ruiz, Aline Alves, Carlos Felipe, Joyce Brandão">
        <meta name="description" content="Cadastro usuario">
        <meta name="keywords" content="Cadastro">
        <meta name="viewport" content="width=device-width, initial-scale-1.0">
        <link rel=stylesheet type="text/css" href="CSS/normalize.css">
        <link rel="stylesheet" type="text/css" href="CSS/style_cadastro.css" media="screen">
        <link rel="shortcut icon" href="img/LOGO-OFFICIAL-200x200%20cortada.png">
        <link rel=stylesheet type="text/css" href="Bootstrap/css/bootstrap.min.css">
    </head>
    <body class="principal">
        <main>
            <section class="gallery">
                <img src="img/LOGO-OFFICIAL-200x200%20cortada.png">
            </section>
                <form action="/projectstages_mvc/cadastro/pos-cadastro" method="post" class="cadastro">
                    <legend class="legenda">Crie sua conta</legend>
                        <input type="text" id="txtNome" class="caixa" name="nome" size="30" placeholder="Nome Completo" required>
                        <input type="text" id="txtNomeUsuario" class="caixa" name="nomeUsuario" size="30" placeholder="Nome Usuario" required>
                        <input type="email" id="txtEmail" class="caixa" name="email" size="30" placeholder="Email" required>
                        <input type="password" id="txtSenha" class="caixa" name="senha" size="30" minlength="8" placeholder="Senha" required>
                        <input type="password" id="txtConfirmarSenha" class="caixa" name="confrmarSenha" minlength="8" size="30" placeholder="Confirmar Senha" required>
                    <label id="ajuste">
                    <input type="checkbox" id="chk" name="termos" checked required>
                    Aceito os<a href="#" id="termos">Termos de uso</a></label>
           		 <input type="submit" id="btn" class="envio" value="Cadastra-se">
                    <p class="link"><a href="/projectstages_mvc/login">Já possui uma conta?</a></p>
                    <security:csrfInput/>
                </form>
                <div class="card01">
                    <img src="img/Tela_Cadastro_Login/Menina-sentada--300x500png.png">
                </div>
                <div class="card02">
                    <img src="img/Tela_Cadastro_Login/meninapensando.svg">
                </div>
        </main>
        <script src="Bootstrap/js/jquery-3.4.1.min.js" type="text/javascript"></script>
        <script src="Bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="JS/JS_cadastro.js" type=text/javascript></script>
    </body>
</html>