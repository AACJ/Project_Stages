<!DOCTYPE html>

<%@taglib	uri="http://java.sun.com/jsp/jstl/core"	prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@	taglib	uri="http://www.springframework.org/tags/form"	prefix="form"%>
<html lang="pt-br" xmlns="http://www.w3.org/1999/xhtml" xmls:th="http//thymeleaf.org">
    <head>
        <Title>Project Stages</Title>
        <meta charset="utf-8">
        <meta name="author" content="Affonso Ruiz, Aline Alves, Carlos Felipe, Joyce Brandão">
        <meta name="description" content="Site de organiação de criação de projetos">
        <meta name="keywords" content="Organização, Criação, Projetos, Desenvolvimentos">
        <meta name="viewport" content="width=device-width, initial-scale-1.0">
        <link rel=stylesheet type="text/css" href="CSS/style_home.css" media="screen">
        <link rel=stylesheet type="text/css" href="CSS/style_notificacao.css">
        <link rel=stylesheet type="text/css" href="Bootstrap/css/bootstrap.min.css">
        <link rel="shortcut icon" href="img/LOGO-OFFICIAL-200x200%20cortada.png"> 
    </head>
    
   <body id="corpo" class="corpo">
  
       <%@include	file="/WEB-INF/views/home.jsp"	%>
            
   <main>
           <section class="user-Notificacao">
               <section class="header-notificacao">
               <h1>Notificações</h1>
               </section>
               <section class="body-notificacao">
                   
                <c:forEach items="${listaNotificacoes}"	var="notificacao" varStatus="status">
                   <div class="pedido-de-amizade">
                   <div class="foto-remetente">
                    <c:if test="${notificacao.foto == null}">
                       <img src="img/Tela_Principal/UsercomCirculoIcon.png">
                     </c:if>
                     
                     <c:if test="${notificacao.foto != null}">
                       <img src="${notificacao.foto}">
                    </c:if>
                    </div>
                    <div class="msg-notificacao-amizade">
                    <p>O Usuario ${notificacao.userName} fez um pedido de amizade, aceitar?</p>
					<p class="msg-confirm" id="msg-confirm">Você e o usuario ${notificacao.userName} agora são amigos.</p>
                    <button type="button" class="btn-aceitar-pedido" id="btn-act-pedido" value="${notificacao.email}">Aceitar</button>
                    <a href="/projectstages_mvc/remove/destinatario/notificacao?email=${notificacao.email}" class="btn-recusar-pedido" id="btn-rem-pedido" value="${notificacao.email}">Recusar</a>
                    </div>
                   		<a href="/projectstages_mvc/remove/destinatario/notificacao?email=${notificacao.email}"  class="btn-fechar-pedido"><img src="img/Tela_Principal/fecha-caixa-projetos-status.png"></a>
                   </div>
                
                   </c:forEach>
                                      
               </section>
            </section>
        </main>
            <script src="Bootstrap/js/jquery-3.4.1.min.js" type="text/javascript"></script>
            <script src="Bootstrap/js/jquery.mask.js" type="text/javascript"></script>
            <script src="Bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
            <script src="JS/JS_home.js" type="text/javascript"></script>
            <script src="JS/JS_notificacao.js" type="text/javascript"></script>
          </body>
</html>