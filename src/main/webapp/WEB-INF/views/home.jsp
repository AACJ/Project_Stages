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
        <link rel=stylesheet type="text/css" href="CSS/style_projetos.css">
        <link rel=stylesheet type="text/css" href="Bootstrap/css/bootstrap.min.css">
        <link rel="shortcut icon" href="img/LOGO-OFFICIAL-200x200%20cortada.png"> 
    </head>
    
    <body id="corpo" class="corpo">
  
        <nav class="menu">
          <ul class="menu-nav">
            <li class="menu-item">
                <div class="menu-link">
                <img src="img/LOGO-OFFICIAL-200x200%20cortada.png" id="logo" width="60" height="60">
                </div>
            </li> 
                          
            <li class="menu-item">
                <a href="/projectstages_mvc/notificacao" class="menu-link">
                <img src="img/Tela_Principal/SinoIcon.png" width="28" height="28">
                <div class="hover-item" id="menu-item01"></div>
                </a>
            </li> 
              
            <li class="menu-item">
                <a href="/projectstages_mvc/cronograma" class="menu-link">
                <img src="img/Tela_Principal/Calendarioicon.png" width="30" height="30">
                <div class="hover-item" id="menu-item02"></div>
                </a>
            </li> 
              
             <li class="menu-item">
                <a href="/projectstages_mvc/amigos" class="menu-link">
                <img src="img/Tela_Principal/AmigosIcon.png" width="30" height="30">
                <div class="hover-item" id="menu-item03"></div>
                </a>
            </li> 
              
            <li class="menu-item">
                <a href="/projectstages_mvc/chat" class="menu-link">
                <img src="img/Tela_Principal/BatepapoIcon.png" width="30" height="30">
                <div class="hover-item" id="menu-item04"></div>
                </a>
            </li> 
                 
            
            <li class="menu-item">
                    
                <input type="checkbox" id="btnPerfil" class="menu-link-perfil">
                <label for="btnPerfil" id="perfil-label">
                    <div class="perfil">
                  
                    <c:if test="${usuarioFoto == null}">
                        <img src="img/Tela_Principal/UsercomCirculoIcon.png" id="user">
                    </c:if>
                           
                    <c:if test="${usuarioFoto != null}">
                        <img src="${usuarioFoto}" id="user">
                    </c:if>
                    
                    </div>
                    <div class="perfil-status"></div>
                </label>
                
                 <div id="btn-perfil-actived"></div>
                
              <div id="op" class="opcao">
                    <ul class="opcao-nav">
                        
                        <li class="opcao-item">
                            <a href="/projectstages_mvc/perfil" class="opcao-link">
                                <img src="img/Tela_Principal/UserIcon.png" width="20" height="20">
                                <span class="opcao-text">Perfil</span>
                            </a>
                        </li>
                        
                        <li class="opcao-item">
                            <div class="opcao-link" id="ops">
                                <img src="img/Tela_Principal/TrabalhomalaIcon.png" width="20" height="20">
                                <span class="opcao-text">Status</span>
                                <img src="img/Tela_Principal/SetaDireitaIcon.png" width="15" height="15" id="setaStatus">
                            <div class="status">
                                <ul class="status-nav">
                                    
                                <li class="status-item">
                                <input type="radio" name="status" id="situacao01" value="Disponivel">
                                <label for="situacao01" class="status-label">
                                    <div class="cor" id="cor01"></div>
                                    <span class="situacao-text">Disponivel</span>
                                </label>
                                    
                                </li>
                                <li class="status-item">
                                <input type="radio" name="status" id="situacao02" value="Indisponivel">
                                 <label for="situacao02" class="status-label">
                                    <div class="cor" id="cor02"></div>
                                    <span class="situacao-text">Indisponivel</span>
                                </label>
                                </li>
                                    
                                <li class="status-item">
                                <input type="radio" name="status" id="situacao03" value="Trabalhando">
                                 <label for="situacao03" class="status-label">
                                    <div class="cor" id="cor03"></div>
                                    <span class="situacao-text">Trabalhando no projeto</span>
                                </label>
                                    
                                </li>
                                    
                                <li class="status-item">
                                <input type="radio" name="status" id="situacao04" value="Temporiaramente">
                                 <label for="situacao04" class="status-label">
                                    <div class="cor" id="cor04"></div>
                                    <span class="situacao-text">Ausente Temporiaramente</span>
                                </label>
                                </li>
                                    
                                <li class="status-item">
                                <input type="radio" name="status" id="situacao05" value="Doenca">
                                 <label for="situacao05" class="status-label">
                                    <div class="cor" id="cor05"></div>
                                    <span class="situacao-text">Ausente por doença</span>
                                </label>
                                </li>
                                        
                                  <li class="status-item">
                                <input type="radio" name="status" id="situacao06" value="Folga">
                                 <label for="situacao06" class="status-label">
                                    <div class="cor" id="cor06"></div>
                                    <span class="situacao-text">De folga</span>
                                </label>
                                </li>
                                </ul>
                            </div>   
                            </div>
                        </li>
                        
                        <li class="opcao-item">
                            <a href="/projectstages_mvc/configuracoes" class="opcao-link">
                                <img src="img/Tela_Principal/ConfiguraIcon.png" width="20" height="20">
                                <span class="opcao-text">Configurações</span>
                            </a>
                        </li>
                        
                        <li class="opcao-item">
                             <a href="/projectstages_mvc/" class="opcao-link">
                                <img src="img/Tela_Principal/SairIcon.png" width="20" height="20">
                                <span class="opcao-text">Sair</span>
                            </a>
                        </li>
                        
                    </ul>
              </div>
            
              </li>
          </ul>  
            
            <div class="quantidade-notificacoes">${qtnNotificacao}</div>
            
            <div class="quantidade-total-mensagens">${totalMensagens}</div>
        </nav>
        
        <input type="checkbox" id="check">
        <label for="check" id="labelSeta">
        <div class="botao">
            <img src="img/Tela_Principal/SetaDireitaIcon.png" width="20" height="20">
          </div>
        </label>
        <nav class="principal">
               
                <div class="principal-search-item" id="area">
                    <h1>Áreas de Projetos</h1>
                    <input type=search id="pesquisa" name="Pesquisa" placeholder="Pesquisa">
                    <button type="submit" id="btn"><img src="img/Tela_Principal/Lupaicon.png" width="25" height="25"></button>
                </div>
               
            <ul class="principal-search-nav" id="search-nav">
            <c:forEach items="${projetosParticipantes}" var="projectParticipante">
               <li class="principal-result-item" id="projeto-result-${projectParticipante.id}">
                   <a href="/projectstages_mvc/home?idProjeto=${projectParticipante.id}&nome=${projectParticipante.nome}" class="link-projetos">${projectParticipante.nome}</a>
                </li>
             </c:forEach>
            </ul>   
        
            <ul class="principal-nav" id="principal-nav">
                
                <li class="principal-item">
                <button type="button" class="botao-add-novo-projeto" id="btn-add-novo-projeto">
                  Novo Projeto&#43;
                    </button>
                </li>
    
                
                 <li class="principal-item">
                    <input type="checkbox" id="checkPrincipal">
                    <label for="checkPrincipal">
                    <span class="principal-text">Principal</span>
                    <div class="setaBaixo"><img src="img/Tela_Principal/SetaBaixoIcon.png"></div>
                    </label>
                    <ul class="projetos-principal">
                   <c:forEach items="${projetosParticipantes}" var="projectParticipante">
                        <li class="link-participante"><a href="/projectstages_mvc/home?idProjeto=${projectParticipante.id}&nome=${projectParticipante.nome}" class="link-projetos">${projectParticipante.nome}</a></li>
                   </c:forEach>
                    </ul>
                </li>
                
                <li class="principal-item">
                    <input type="checkbox" id="checkPrincipal2">
                    <label for="checkPrincipal2">
                    <span class="principal-text">Favoritos</span>
                    <div class="setaBaixo"><img src="img/Tela_Principal/SetaBaixoIcon.png"></div>
                    </label>
                     <ul class="projetos-favoritos">
                   <c:forEach items="${projetosFavoritos}" var="projectFavoritos">
                        <li class="link-participante"><a href="/projectstages_mvc/home?idProjeto=${projectFavoritos.id}&nome=${projectFavoritos.nome}" class="link-projetos">${projectFavoritos.nome}</a></li>
                   </c:forEach>
                    </ul>
                </li>
            
            </ul>
        </nav>
        
         <div class="caixa-add-projeto-externo" id="caixa-add-projeto">
            <div class="caixa-add-projeto-interno">
                <button type="button" class="fecha-caixa-projeto" id="fecha-caixa-add-projeto"><img src="img/Tela_Principal/closer01.png"></button>
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
        </div>
        <script src="Bootstrap/js/jquery-3.4.1.min.js" type="text/javascript"></script>
        <script src="Bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="JS/JS_configuracoes.js" type="text/javascript"></script>
        <script src="JS/JS_home.js" type="text/javascript"></script>

    </body>
    
</html>
