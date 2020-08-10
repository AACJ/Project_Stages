<!DOCTYPE html>

<html lang="pt-br" xmlns="http://www.w3.org/1999/xhtml" xmls:th="http//thymeleaf.org">
    <head>
        <Title>Project Stages</Title>
        <meta charset="utf-8">
        <meta name="author" content="Affonso Ruiz, Alie Alves, Carlos Felipe, Joyce Brandão">
        <meta name="description" content="Site de organiação de criação de projetos">
        <meta name="keywords" content="Organização, Criação, Projetos, Desenvolvimentos">
        <meta name="viewport" content="width=device-width, initial-scale-1.0">
        <link rel=stylesheet type="text/css" href="CSS/style_home.css" media="screen">
        <link rel=stylesheet type="text/css" href="CSS/style_chat.css" media="screen">
        <link rel=stylesheet type="text/css" href="Bootstrap/css/bootstrap.min.css">
        <link rel="shortcut icon" href="img/LOGO-OFFICIAL-200x200%20cortada.png"> 
    </head>
   <body id="corpo" class="corpo">
   
  <%@include file="/WEB-INF/views/home.jsp"	%>
  
        <main>
            <div class="user-chat">
                <section class="chat">
                <div class="chat-header">
                    <button type="button" id="btnChat01">Bate-papo</button>
                    <button type="button" id="btnChat02">Novo Bate-papo</button>
                </div>
                    
                <div class="chat-body">
                    <div class="chat-body-interno">
                    <div class="content-chat" id="content-chat01">
                <label for="pesquisaConversa" id="label-pesquisa">
                    <button type="button"><img src="img/Tela_Principal/Lupaicon.png" width="20" height="20"></button>
                </label>
                 <input type="search" placeholder="Procurar uma conversa..." id="pesquisaConversa">
                  
                   <c:forEach items="${listaAmigos}" var="amigo" varStatus="statusAmigos">
                   	<a href="/projectstages_mvc/bate-papo?idUsuario=${amigo.id}&userName=${amigo.userName}" class="perfil-chat">
                    	<div class="foto-perfil-chat">
                    	<c:if test="${amigo.foto == null}">
                       		<img src="img/Tela_Principal/UsercomCirculoIcon.png">
                     	</c:if>
                     
                     	<c:if test="${amigo.foto != null}">
                       		<img src="${amigo.foto}">
                    	</c:if>
                    	
                    	</div>
                    	<div class="informacoes-perfil-chat">
                        <p class="nome-perfil-chat">${amigo.userName}</p>
                    	</div>
                    	<c:if test="${msgNotViewed[statusAmigos.index] > 0}">
                    	<p class="qnt-msg">${msgNotViewed[statusAmigos.index]}</p>
                    	</c:if>
                  	</a>
                  </c:forEach>
                    </div>
                    
                    <div class="content-chat" id="content-chat02">
                        
                    </div>
                    </div>
                    </div>
                </section>
                
                <section class="imagem-chat">
                <div class="chat-image-item"><img src="img/Tela_Chat/imgChat.png"></div>
                </section>
            </div>
        </main>
        <script src="Bootstrap/js/jquery-3.4.1.min.js" type="text/javascript"></script>
        <script src="Bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="JS/JS_home.js" type="text/javascript"></script>
        <script src="JS/JS_chat.js" type="text/javascript"></script>
    </body>
</html>
