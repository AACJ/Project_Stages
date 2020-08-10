<!DOCTYPE html>

<html lang="pt-br" xmlns="http://www.w3.org/1999/xhtml" xmls:th="http//thymeleaf.org">
    <head>
        <Title>Project Stages</Title>
        <meta charset="utf-8">
        <meta name="author" content="Affonso Ruiz, Alie Alves, Carlos Felipe, Joyce Brandão">
        <meta name="description" content="Site de organiação de criação de projetos">
        <meta name="keywords" content="Organização, Criação, Projetos, Desenvolvimentos">
        <meta name="viewport" content="width=device-width, initial-scale-1.0">
        <link rel=stylesheet type="text/css" href="CSS/normalize.css">
        <link rel=stylesheet type="text/css" href="CSS/style.css" media="screen">
        <link rel=stylesheet type="text/css" href="Bootstrap/css/bootstrap.min.css">
        <link rel="shortcut icon" href="img/LOGO-OFFICIAL-200x200%20cortada.png"> 
    </head>
    <body class="principal">
        <header class="hero">
            <nav>
                <img src="img/Logo-com-nome%20150.png" alt="Logo do site Project Stages ">

                <ul>
                    <li><a href="/projectstages_mvc/login">Login</a></li>
                    <li><a href="/projectstages_mvc/cadastro">Cadastre-se</a></li>
                </ul>
            </nav>
        </header>
        <section class="gallery">
                <div class="card-gallery">
                <img src="img/Tela_Index/testeeeee.png">
                </div>
                <article class="content-div">
                <h1>O Project Stages permite trabalhar com desenvolvimento de projetos, visando a colaboração e o sucesso no cumprimento de etapas.</h1>
                <p>Com cronogramas, bate-papo, quadros interativos, encontros presenciais marcados, tudo para organizar e priorizar seus projetos de modo flexível, divertido e gratificante.</p>
                <input class="caixa" name="email" type="text" size="30" placeholder="Email">
                <button type ="submit" id="caixa-botao">Comece agora!</button>
                </article>
        </section>
        
        <main>
            <div class="card-text">
            <section class="block01">
            <div class="card01">
                <img src="img/Tela_Index/menina%20registro2.png">
            </div>
            <article class="text01">
                <h3>Registro de atividades</h3>
                <p>Registro no cronogama, distribuição, otimização de atividades, levantamento de atividades pendentes, notificações de atividades, checklist das atividades e atividades diárias.</p>
            </article>
            </section>
            
            <section class="block02">
            <div class="card02">
                <img src="img/Tela_Index/timecomtres.png">
            </div>
            <article class="text02">
                <h3> Trabalhe com seu team</h3>
                <p>Trabalhe com membros de todos os cantos do mundo, organize as tarefas de sua equipe, compartilhe informações com o resto do time, acompanhe o fluxo de atividades do seu grupo, trabalhe com várias pessoas ao mesmo tempo revisando e discutindo ideias.</p>
            </article>
            </section>
            </div>
            
            <section class="credito">
            <div class="card-credito" id="ajuste">
                <img src="img/Tela_Index/addamigos.svg">
                <h2>Cadastre amigos</h2>
            </div>
            <div class="card-credito">
                <img src="img/Tela_Index/lider.svg">
                <h2>Seja o Líder</h2>
                <button type="button" class="botao-credito">Inicie seu projeto!</button>
            </div>
            <div class="card-credito">
                <img src="img/Tela_Index/maapaaa.png">
                <h2>Trace rotas de encontros</h2>
            </div>
            </section>
        </main>
        <footer>
            <p>Copyright&copy;, 2020</p>
        </footer>
        <script src="Bootstrap/js/jquery-3.4.1.min.js" type="text/javascript"></script>
        <script src="Bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    </body>
</html>