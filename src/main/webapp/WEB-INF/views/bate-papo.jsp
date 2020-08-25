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
        <link rel=stylesheet type="text/css" href="CSS/style_bate-papo.css" media="screen">
        <link rel=stylesheet type="text/css" href="Bootstrap/css/bootstrap.min.css">
        <link rel="shortcut icon" href="img/LOGO-OFFICIAL-200x200%20cortada.png"> 
    </head>
    <body id="corpo">
  
  	 <%@include	file="/WEB-INF/views/home.jsp"	%>
  
        <main>
            <div class="user-chat">
            	<section class="menu-chat">
                 <div class="menu-chat-header">
                    <button type="button" id="btnChat01">Bate-papo</button>
                </div>
                    
                <div class="menu-chat-body">
                    <div class="menu-chat-body-interno">
                    <div class="menu-content-chat" id="content-chat01">
                <label for="pesquisaConversa" id="label-pesquisa">
                    <button type="button"><img src="img/Tela_Principal/Lupaicon.png" width="20" height="20"></button>
                </label>
                 <input type="search" placeholder="Procurar uma conversa..." id="pesquisaConversa">
                  
                   <c:forEach items="${listaAmigos}" var="amigo" varStatus="statusAmigos">
                   	<a href="/projectstages_mvc/bate-papo?idUsuario=${amigo.id}&userName=${amigo.userName}" class="menu-perfil-chat" id="perfil-chat-${amigo.id}">
                    	<div class="menu-foto-perfil-chat">
                    	<c:if test="${amigo.foto == null}">
                       		<img src="img/Tela_Principal/UsercomCirculoIcon.png">
                     	</c:if>
                     
                     	<c:if test="${amigo.foto != null}">
                       		<img src="${amigo.foto}">
                    	</c:if>
                    	
                    	</div>
                    	<div class="menu-informacoes-perfil-chat">
                        <p class="menu-nome-perfil-chat">${amigo.userName}</p>
                    	</div>
                    	<c:if test="${msgNotViewed[statusAmigos.index] > 0}">
                    	<p class="menu-qnt-msg">${msgNotViewed[statusAmigos.index]}</p>
                    	</c:if>
                  	</a>
                  </c:forEach>
                    </div>
                    </div>
                    </div>
                </section>
            
                <section class="chat">
                
                <div class="chat-header">
                    <div class="foto-chat">
                    <c:if test="${amigoChat.foto == null}">
                       		<img src="img/Tela_Principal/UsercomCirculoIcon.png">
                     	</c:if>
                     
                     	<c:if test="${amigoChat.foto != null}">
                       		<img src="${amigoChat.foto}">
                    	</c:if>
                    </div> 
                    <div class="informacoes-chat">
                        <p class="nome-chat">${amigoChat.userName}</p>
                    </div>
                </div>
                    
                <div class="chat-body" id="chatBody">
                    
               </div>
                    
                <div class="chat-foot">
                <button type="button" class="emojy-chat" id="emoji-open-box"><img src="img/Tela_Chat/Emoji-2.png"></button>
                <textarea class="msg-chat" rows="2" cols="35" maxlength="500" placeholder="Escreva suas mensagens aqui..." id="msg"></textarea>
                <button type="button" class="send-text" id="send-msg" value="${idAmigo}"><img src="img/Tela_Chat/send.png"></button>
                
                <div class="close-box-emoji" id="fecha-box-emoji"></div>
                    
                <div class="box-emojis" id="box-emojis">
                <div class="box-emojis-interno">
                
                <div class="linha-emojis">
                    <button type="button" class="emojis" value="img/Tela_Chat/Emoji-1.png"><img src="img/Tela_Chat/Emoji-1.png"></button> 
                    <button type="button" class="emojis" value="img/Tela_Chat/Emoji-2.png"><img src="img/Tela_Chat/Emoji-2.png"></button>
                    <button type="button" class="emojis" value="img/Tela_Chat/Emoji-3.png"><img src="img/Tela_Chat/Emoji-3.png"></button>
                    <button type="button" class="emojis" value="img/Tela_Chat/Emoji-4.png"><img src="img/Tela_Chat/Emoji-4.png"></button>
                    <button type="button" class="emojis" value="img/Tela_Chat/Emoji-38.png"><img src="img/Tela_Chat/Emoji-38.png"></button>
                </div>
                
                 <div class="linha-emojis">
                    <button type="button" class="emojis" value="img/Tela_Chat/Emoji-6.png"><img src="img/Tela_Chat/Emoji-6.png"></button> 
                    <button type="button" class="emojis" value="img/Tela_Chat/Emoji-7.png"><img src="img/Tela_Chat/Emoji-7.png"></button>
                    <button type="button" class="emojis" value="img/Tela_Chat/Emoji-8.png"><img src="img/Tela_Chat/Emoji-8.png"></button>
                    <button type="button" class="emojis" value="img/Tela_Chat/Emoji-9.png"><img src="img/Tela_Chat/Emoji-9.png"></button>
                    <button type="button" class="emojis" value="img/Tela_Chat/Emoji-10.png"><img src="img/Tela_Chat/Emoji-10.png"></button>    
                </div>
                
                 <div class="linha-emojis">
                    <button type="button" class="emojis" value="img/Tela_Chat/Emoji-11.png"><img src="img/Tela_Chat/Emoji-11.png"></button> 
                    <button type="button" class="emojis" value="img/Tela_Chat/Emoji-12.png"><img src="img/Tela_Chat/Emoji-12.png"></button>
                    <button type="button" class="emojis" value="img/Tela_Chat/Emoji-13.png"><img src="img/Tela_Chat/Emoji-13.png"></button>
                    <button type="button" class="emojis" value="img/Tela_Chat/Emoji-14.png"><img src="img/Tela_Chat/Emoji-14.png"></button>
                    <button type="button" class="emojis" value="img/Tela_Chat/Emoji-15.png"><img src="img/Tela_Chat/Emoji-15.png"></button>    
                </div>
                
                 <div class="linha-emojis">
                    <button type="button" class="emojis" value="img/Tela_Chat/Emoji-16.png"><img src="img/Tela_Chat/Emoji-16.png"></button> 
                    <button type="button" class="emojis" value="img/Tela_Chat/Emoji-17.png"><img src="img/Tela_Chat/Emoji-17.png"></button>
                    <button type="button" class="emojis" value="img/Tela_Chat/Emoji-18.png"><img src="img/Tela_Chat/Emoji-18.png"></button>
                    <button type="button" class="emojis" value="img/Tela_Chat/Emoji-19.png"><img src="img/Tela_Chat/Emoji-19.png"></button>
                    <button type="button" class="emojis" value="img/Tela_Chat/Emoji-20.png"><img src="img/Tela_Chat/Emoji-20.png"></button>    
                </div>
                
                 <div class="linha-emojis">
                    <button type="button" class="emojis" value="img/Tela_Chat/Emoji-21.png"><img src="img/Tela_Chat/Emoji-21.png"></button> 
                    <button type="button" class="emojis" value="img/Tela_Chat/Emoji-22.png"><img src="img/Tela_Chat/Emoji-22.png"></button>
                    <button type="button" class="emojis" value="img/Tela_Chat/Emoji-23.png"><img src="img/Tela_Chat/Emoji-23.png"></button>
                    <button type="button" class="emojis" value="img/Tela_Chat/Emoji-24.png"><img src="img/Tela_Chat/Emoji-24.png"></button>
                    <button type="button" class="emojis" value="img/Tela_Chat/Emoji-39.png"><img src="img/Tela_Chat/Emoji-39.png"></button>
                        
                </div>
                
                 <div class="linha-emojis">
                    <button type="button" class="emojis" value="img/Tela_Chat/Emoji-26.png"><img src="img/Tela_Chat/Emoji-26.png"></button> 
                    <button type="button" class="emojis" value="img/Tela_Chat/Emoji-27.png"><img src="img/Tela_Chat/Emoji-27.png"></button>
                    <button type="button" class="emojis" value="img/Tela_Chat/Emoji-28.png"><img src="img/Tela_Chat/Emoji-28.png"></button>
                    <button type="button" class="emojis" value="img/Tela_Chat/Emoji-29.png"><img src="img/Tela_Chat/Emoji-29.png"></button>
                    <button type="button" class="emojis" value="img/Tela_Chat/Emoji-30.png"><img src="img/Tela_Chat/Emoji-30.png"></button>    
                </div>
                
                 <div class="linha-emojis">
                    <button type="button" class="emojis" value="img/Tela_Chat/Emoji-31.png"><img src="img/Tela_Chat/Emoji-31.png"></button> 
                    <button type="button" class="emojis" value="img/Tela_Chat/Emoji-32.png"><img src="img/Tela_Chat/Emoji-32.png"></button>
                    <button type="button" class="emojis" value="img/Tela_Chat/Emoji-35.png"><img src="img/Tela_Chat/Emoji-35.png"></button>
                    <button type="button" class="emojis" value="img/Tela_Chat/Emoji-36.png"><img src="img/Tela_Chat/Emoji-36.png"></button>
                    <button type="button" class="emojis" value="img/Tela_Chat/Emoji-37.png"><img src="img/Tela_Chat/Emoji-37.png"></button>    
                </div>
                
                <div class="linha-emojis"> 
                    <button type="button" class="emojis" value="img/Tela_Chat/Emoji-40.png"><img src="img/Tela_Chat/Emoji-40.png"></button>
                    <button type="button" class="emojis" value="img/Tela_Chat/Emoji-41.png"><img src="img/Tela_Chat/Emoji-41.png"></button>   
                </div>
                
                </div>
                    
                </div>
                
                </div>
                </section>
                
            </div>
        </main>
     	
     	  <script src="Bootstrap/js/jquery-3.4.1.min.js" type="text/javascript"></script>
        <script src="Bootstrap/js/bootstrap.min.js" type="text/javascript"></script>   
        <script src="JS/JS_home.js" type="text/javascript"></script>
        <script src="JS/JS_bate-papo.js" type="text/javascript"></script>
     
    </body>
</html>
