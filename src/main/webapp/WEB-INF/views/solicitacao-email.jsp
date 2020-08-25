<!DOCTYPE html>

<html lang="pt-br" xmlns="http://www.w3.org/1999/xhtml" xmls:th="http//thymeleaf.org">
    <head>
        <title>Project Stages</title>
        <meta charset="utf-8">
        <meta name="author" content="Affonso Ruiz, Aline Alves, Carlos Felipe, Joyce Brand�o">
        <meta name="description" content="Solicita��o de e-mail">
        <meta name="keywords" content="Email">
        <meta name="viewport" content="width=device-width, initial-scale-1.0">
        <link rel=stylesheet type="text/css" href="CSS/normalize.css">
        <link rel="stylesheet" href="CSS/style_solicitacao-email.css" media=screen>
        <link rel="shortcut icon" href="img/LOGO-OFFICIAL-200x200%20cortada.png">
        <link rel=stylesheet type="text/css" href="Bootstrap/css/bootstrap.min.css">
    </head>
    <body class="principal">
        <main>
            <section class="gallery">
                <img src="img/LOGO-OFFICIAL-200x200%20cortada.png">
            </section>
            <form action="/projectstages_mvc/solicitacao-email/email-enviado" method="get" class="cadastro">
             <legend class="legenda">Esqueceu sua senha?</legend>
                <p id="text01">Enviaremos um e-mail com instru��es para redefinir sua senha</p>
                <input class="caixa" name="email" type="email" size="30" placeholder="Insira seu e-mail" required>
                <input class="envio" type="submit" value="Redefinir senha">
                <p id="text02">Se voc� j� possui um usu�rio - <b><a href="/projectstages_mvc/login" class="link">Fa�a Login aqui</a></b></p>
            </form>
              
        </main>
        <script src="Bootstrap/js/jquery-3.4.1.min.js" type="text/javascript"></script>
        <script src="Bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    </body>
</html>