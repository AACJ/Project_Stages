<!DOCTYPE html>

<html lang="pt-br" xmlns="http://www.w3.org/1999/xhtml" xmls:th="http//thymeleaf.org">
    <head>
        <title>Project Stages</title>
        <meta charset="utf-8">
        <meta name="author" content="Affonso Ruiz, Aline Alves, Carlos Felipe, Joyce Brand�o">
        <meta name="description" content="E-mail n�o encontrado">
        <meta name="keywords" content="Email">
        <meta name="viewport" content="width=device-width, initial-scale-1.0">
        <link rel=stylesheet type="text/css" href="CSS/normalize.css">
        <link rel="stylesheet" href="CSS/style_email-nao-encontrado.css" media=screen>
        <link rel="shortcut icon" href="img/LOGO-OFFICIAL-200x200%20cortada.png">
        <link rel=stylesheet type="text/css" href="Bootstrap/css/bootstrap.min.css">
    </head>
    <body class="principal">
        <main>
            <section class="gallery">
                <img src="img/LOGO-OFFICIAL-200x200%20cortada.png">
            </section>
            <div class="mensagem">
                <h3 class="legenda">Recupera��o de <b>senha</b></h3>
                <img src="img/NegaIcon.png" width="50" height="50">
                <p id="texto-principal">${email} o email inserido n�o foi encontrado. Solicite um novo envio de email.</p>
                <p id="texto-link">Lembra-se da senha ? - <b><a href="/projectstages_mvc/login">Voltar para o Login</a></b></p>
            </div>
              
        </main>
        <script src="Bootstrap/js/jquery-3.4.1.min.js" type="text/javascript"></script>
        <script src="Bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    </body>
</html>