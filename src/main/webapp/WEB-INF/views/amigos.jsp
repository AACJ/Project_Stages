<!DOCTYPE html>

<%@taglib	uri="http://java.sun.com/jsp/jstl/core"	prefix="c"%>
<html lang="pt-br" xmlns="http://www.w3.org/1999/xhtml" xmls:th="http//thymeleaf.org">
    <head>
        <Title>Project Stages</Title>
        <meta charset="utf-8">
        <meta name="author" content="Affonso Ruiz, Aline Alves, Carlos Felipe, Joyce Brandão">
        <meta name="description" content="Site de organiação de criação de projetos">
        <meta name="keywords" content="Organização, Criação, Projetos, Desenvolvimentos">
        <meta name="viewport" content="width=device-width, initial-scale-1.0">
        <link rel=stylesheet type="text/css" href="CSS/style_home.css" media="screen">
        <link rel=stylesheet type="text/css" href="CSS/style_amigos.css" media="screen">
        <link rel=stylesheet type="text/css" href="Bootstrap/css/bootstrap.min.css">
        <link rel="shortcut icon" href="img/LOGO-OFFICIAL-200x200%20cortada.png"> 
    </head>
    
    <body id="corpo" class="corpo">
  
      <%@include	file="/WEB-INF/views/home.jsp"	%>
      
       <main>
        <section class="user-amigos">
            <div class="header-amigos">
                <h3>Amigos</h3>
                <ul class="amigo-menu">
                    <li class="amigo-item">
                        <input type="checkbox" name="addAmigo" id="addAmigo">
                        <label for="addAmigo" id="label-addAmigo">
                            <img src="img/Tela_Amigo/AddFriendIcon.png">
                        </label>
                        <div class="card-addAmigo">
                            <h3>Adicionar amigo</h3>
                            <input type="search" id="pesquisaAddAmigo" name="pesquisaAddAmigo" placeholder="Buscar por id ou nome de usuário">
                            <button type="button" class="lupa-amigos" id="lupa-amigos"><img src="img/Tela_Amigo/lupa.png"></button>
                           <div class="resultado-pesquisas">
                            <c:forEach items="${usuarios}"	var="usuario" varStatus="status">
                            
                            <a href="/projectstages_mvc/perfil-usuarios?idUsuario=${usuario.id}&userName=${usuario.userName}" class="amigos-perfils" id="perfil-amigo-${usuario.id}">
                                <div class="foto-perfil-amigo">
                                  <c:if test="${usuario.foto == null}">
                           			<img src="img/Tela_Principal/UsercomCirculoIcon.png" id="imagem">
                          		 </c:if>
                           
                           		<c:if test="${usuarioFoto != null}">
                           		 <img src="${usuario.foto}" id="imagem">
                           		 </c:if>
                                 </div>
                                <div class="nome-perfil-amigo">
                                <p class="name-user-search">${usuario.userName}</p>
                                
                                <p class="id-user-search">ID: ${usuario.id}</p>
                                </div>
                            </a>
                    
                            </c:forEach>
                            </div>
                        </div>
                    </li>
                    
                    <li class="amigo-item">
                        <a href="amigo-pesquisa.html" id="label-pesquisaAmigo">
                            <img src="img/Tela_Amigo/BuscarfriendIcon.png">
                        </a>
                    </li>
                
                </ul>
            </div>
             <div class="content-amigos">
             <c:forEach items="${listaAmigos}"	var="amigo" varStatus="status">
                 <a href="/projectstages_mvc/perfil-usuarios?idUsuario=${amigo.id}&userName=${amigo.userName}" class="meus-amigos-perfil">  
                    <div class="fotos-meus-amigos">
                    <c:if test="${amigo.foto == null}">
                       <img src="img/Tela_Principal/UsercomCirculoIcon.png">
                     </c:if>
                     
                     <c:if test="${amigo.foto != null}">
                       <img src="${amigo.foto}">
                    </c:if>
                    </div>
                    <div class="info-meus-amigos"><p>${amigo.userName}</p></div>
                </a>
                </c:forEach>
            </div>
        </section>
        </main>
        <script src="JS/JS_home.js" type="text/javascript"></script>
        <script src="JS/JS_amigos.js" type="text/javascript"></script>
        <script src="Bootstrap/js/jquery-3.4.1.min.js" type="text/javascript"></script>
        <script src="Bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    </body>
    
</html>
