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
        <link rel="stylesheet" type="text/css" href="CSS/style_pos-cadastro.css" media="screen">
        <link rel="shortcut icon" href="img/LOGO-OFFICIAL-200x200%20cortada.png">
        <link rel=stylesheet type="text/css" href="Bootstrap/css/bootstrap.min.css">
    </head>
    <body>
        <section class="conteiner01">
          <div class="card-externo">
            <div class="card-interno" id="cardIN">
                <form action="/projectstages_mvc/pos-cadastro/home" method="post">
                <div class="cards" id="card01">
                    <h3 id="text-card01-titulo">Bem Vindo ao
                        <img src="img/ProjectR.png" width="200" height="50"></h3>
                    <p id="text-card01-principal"> O Project Stages permite trabalhar com o desenvolvimento de projetos e visando a colaboração e o sucesso no cumprimento de etapas.</p>
                    <p id="text-card01">Comece dando um nome para o projeto:</p>
                   
                        <input type="text" placeholder="Ex: Bills System" name="nome" value="Novo Projeto" id="nomeProjeto" maxlength="30">
                  
                    <button type="button" class="pass" id="btnPass"><img src="img/Tela_Principal/SetaDireitaIcon.png" width="20" height="20"></button>
                </div>
                
                <div class="cards" id="card02">
                    <img src="img/LOGO-OFFICIAL-200x200%20cortada.png" id="logo" width="70" height="70">
                    <h3>Criar grupos de listas</h3>
                    <p id="text-card02-principal">Os grupos de lista, podem ser um conjunto de ideias, um grupo de tarefas ou uma fase do projeto.</p>
                    <p id="text-card02-principal">Você poderar adicionar títulos ao seu grupo com lista:</p>
                    
                        <input type="text" placeholder="Afazeres" name="tarefa" value="Afazeres" size="30">
                        <input type="text" placeholder="Em desenvolvimento" name="desenvolvimento" value="Em desenvolvimento" size="30">
                        <input type="text" placeholder="Concluido" name="concluido" value="Concluido" size="30">
        
                    <p id="text-card02">Se quiser, siga em frente e renomeie suas listas!</p>
                    <button type="button" class="pass" id="btnPass2"><img src="img/Tela_Principal/SetaDireitaIcon.png" width="20" height="20"></button>
                </div>
                
                <div class="cards" id="card03">
                    <h3>Tudo pronto para começar!</h3>
                    <p>Continue personalizando seu quadro no</p>
                    <img src="img/ProjectR.png" width="200" height="50" id="logoNome">
                    <button type="submit" class="pass" id="btnPass3">
                        <img src="img/Tela_Principal/SetaDireitaIcon.png" width="20" height="20"></button>
                    
                </div>
                 <security:csrfInput/>
                </form>
            </div>
           </div>
            <ul>
                <li id="passo01"></li>
                <li id="passo02"></li>
                <li id="passo03"></li>
            </ul>
        </section>
        
        <section class="conteiner02">
           <div class="carrossel-imagens-externo">
           <div class="carrossel-imagens-interno">
               <img src="img/Tela_Pos-Cadastro/undraw_collaborators_prrw.svg" id="img01">
               <img src="img/Tela_Pos-Cadastro/undraw_content_structure_79gj.svg" id="img02">
               <img src="img/Tela_Pos-Cadastro/undraw_feeling_proud_qne1.svg" id="img03">
               <img src="img/Tela_Pos-Cadastro/undraw_project_completed_w0oq.svg" id="img04">
               <img src="img/Tela_Pos-Cadastro/undraw_remotely_2j6y.svg" id="img05"> 
               <img src="img/Tela_Pos-Cadastro/undraw_team_chat_y27k.svg" id="img06"> 
               <img src="img/Tela_Pos-Cadastro/undraw_team_goals_hrii.svg" id="img07"> 
            </div>
           </div>
        </section>
        <script src="JS/JS_pos-cadastro.js" type="text/javascript"></script>
        <script src="Bootstrap/js/jquery-3.4.1.min.js" type="text/javascript"></script>
        <script src="Bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="JS/JS_cadastro.js" type=text/javascript></script>
    </body>
</html>