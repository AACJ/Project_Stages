<!DOCTYPE html>

<%@taglib	uri="http://java.sun.com/jsp/jstl/core"	prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@	taglib	uri="http://www.springframework.org/tags/form"	prefix="form"%>
<html lang="pt-br" xmlns="http://www.w3.org/1999/xhtml" xmls:th="http//thymeleaf.org">
    <head>
        <title>Project Stages</title>
        <meta charset="utf-8">
        <meta name="author" content="Affonso Ruiz, Aline Alves, Carlos Felipe, Joyce Brandão">
        <meta name="description" content="Perfil usuario">
        <meta name="keywords" content="Cadastro">
        <meta name="viewport" content="width=device-width, initial-scale-1.0">
        <link rel=stylesheet type="text/css" href="CSS/normalize.css">
        <link rel="stylesheet" type="text/css" href="CSS/style_home.css" media="screen">
        <link rel="stylesheet" type="text/css" href="CSS/style_perfil.css" media="screen">
        <link rel="shortcut icon" href="img/LOGO-OFFICIAL-200x200%20cortada.png">
        <link rel=stylesheet type="text/css" href="Bootstrap/css/bootstrap.min.css">
    </head>
    
   <body id="corpo" class="corpo">
  
       <%@include	file="/WEB-INF/views/home.jsp"	%>
            
            <main>
            <section class="user-perfil">
            <section class="gallery">
               <form action="/projectstages_mvc/salvar/foto-perfil" method="post"	enctype="multipart/form-data" id="form-Foto-Perfil">
               <nav class="escolher-foto">
               
               <c:if test="${usuarioFoto == null}">
               <div class="foto">
                   <input type="file" name="foto" id="file" accept="image/png image/jpe image/jpg">
                   <label for="file" id="btnfile">
                       <nav id="cont">
                       	   <c:if test="${usuarioFoto == null}">
                           <img src="img/Tela_Principal/UsercomCirculoIcon.png" id="imagem">
                           </c:if>
                           
                           <c:if test="${usuarioFoto != null}">
                           <img src="${usuarioFoto}" id="imagem">
                           </c:if>
                           <div class="foto-text">
                           <img src="img/Tela_Perfil/UploadIcon.png" width="10" height="10" id="uploadFoto">
                           <span>Escolher foto</span>
                           </div>
                       </nav>
                   </label>        
               </div>
               </c:if>
               
               <c:if test="${usuarioFoto != null}">
               <div class="foto" id="active-remove-photo">
                   <input type="file" name="foto" id="file" accept="image/png image/jpe image/jpg">
                   <label for="file" id="btnfile">
                       <nav id="cont">
                       	   <c:if test="${usuarioFoto == null}">
                           <img src="img/Tela_Principal/UsercomCirculoIcon.png" id="imagem">
                           </c:if>
                           
                           <c:if test="${usuarioFoto != null}">
                           <img src="${usuarioFoto}" id="imagem">
                           </c:if>
                           <div class="foto-text">
                           <img src="img/Tela_Perfil/UploadIcon.png" width="10" height="10" id="uploadFoto">
                           <span>Escolher foto</span>
                           </div>
                       </nav>
                   </label>
                  
                  <a href="/projectstages_mvc/remover/foto-perfil-meu-usuario" class="remove-foto">Remover foto</a>
                 
               </div>
               </c:if>
               
               <div class="linha-escolhe-foto-input">
                <input type="text" name="usuario" placeholder="Usuario" class="update-name-user" id="text-update-usuario" value="${usuarioNameUser}" readonly>
               
                <div class="botoes-edit-nome-usuario">
                   <button type="button" class="edit-name-user-pencil" id="btn-edit-name-user"><img src="img/Tela_Principal/lapisUpdate.png"></button>
                     <button type="button" class="save-name-user-pencil" id="btn-save-name-user"><img src="img/Tela_Perfil/save.png"></button>
                </div>
               
               </div>
               </nav>
             <security:csrfInput/>
             </form>
            </section>

            <section class="informacao">
            <form action="/projectstages_mvc/atualizar/perfil-meu-usuario" method="post">
                <table class="informacao-tabela">
                    <tr class="linha-informacao-tabela">
                        <th colspan="2" class="title-informacao-tabela"><span>Informações Pessoais:</span></th>
                    </tr>
                    
                    <tr class="linha-informacao-tabela">
                        <td class="coluna-title-informacao-tabela">
                        <img src="img/Tela_Perfil/IDIcon.png" width="30" heigth="40">
                            <span>ID:</span>
                        </td>
                        <td class="coluna-text-informacao-tabela-ID">
                            <input class="text-informacao-tabela" type="text" name="id" placeholder="ID" value="${usuarioId}" id="textId" readonly>
                        </td>
                        
                        <td class="coluna-copy-informacao-tabela">
                            <button type="button" class="btnCopy" id="btn-copy">
                                <img src="img/Tela_Perfil/copyid.png" width="30" heigth="40">
                            </button>
                        </td>
                    </tr>
                    
                    <tr class="linha-informacao-tabela">
                        <td class="coluna-title-informacao-tabela">
                            <img src="img/Tela_Perfil/GmailIcon.png" width="30" heigth="40">
                            <span>Gmail:</span>
                         </td>
                        <td class="coluna-text-informacao-tabela">
                            <input class="text-informacao-tabela" type="email" name="emailContato" value="${usuarioEmailContato}" placeholder="Adicione o seu email" id="textGmail" readonly>
                         </td>
                    </tr>
                    
                    <tr class="linha-informacao-tabela">
                        <td class="coluna-title-informacao-tabela">
                            <img src="img/Tela_Perfil/SkypeIcon.png" width="30" heigth="40">
                            <span>Skype:</span>
                        </td>
                        <td class="coluna-text-informacao-tabela">
                            <input class="text-informacao-tabela" type="text" name="skype" value="${usuarioSkype}" placeholder="Adicione seu Skype" id="textSkype" readonly >
                        </td>
                    </tr>
                    
                     <tr class="linha-biografia-informacao-tabela">
                        <td class="coluna-title-informacao-tabela">
                            <img src="img/Tela_Perfil/UserBioIcon.png" width="30" heigth="40">
                            <span>Biografia:</span>
                         </td>
                        <td class="coluna-biografia-text-informacao-tabela">
                            <textarea class="biografia-text-informacao-tabela" id="textBiografia" name="biografia" placeholder="Adicione sua biografia" rows="3" cols="40" maxlength="111" readonly>${usuarioBiografia}</textarea>
                         </td>
                    </tr>  
                    
                    <tr class="linha-informacao-tabela">
                        <td class="coluna-title-informacao-tabela">
                            <img src="img/Tela_Perfil/TelephoneIcon.png" width="30" heigth="40">
                            <span>Telefone/Celular:</span>
                        </td>
                        <td class="coluna-text-informacao-tabela">
                            <input class="celular-text-informacao-tabela" type="tel" 
                                   pattern="\([0-9]{2}\)[9]{1}[0-9]{4}-[0-9]{4}" name="celular" value="${usuarioCelular}" placeholder="Adicione o telefone/celular" id="textCelular" readonly>
                        </td>
                    </tr>
                       
                    <tr class="linha-informacao-tabela">
                    <td class="coluna-title-informacao-tabela">
                        <img src="img/Tela_Perfil/RelogioIcon.png" width="30" heigth="40">
                            <span>Fuso horário:</span>
                    </td>
                        <td class="coluna-text-informacao-tabela">
                            <input class="text-informacao-tabela" type="text" name="horario" value="${usuarioHorario}" placeholder="Adicione o fuso horário" id="textHorario" readonly>
                        </td>
                    </tr>
                            
                    <tr class="linha-informacao-tabela">
                         <td class="coluna-title-informacao-tabela">
                            <img src="img/Tela_Perfil/MundoIcon.png" width="30" heigth="40">
                            <span>Localização:</span>
                         </td>
                        <td class="coluna-text-informacao-tabela">
                            <input class="text-informacao-tabela" type="text" name="localizacao" value="${usuarioLocalizacao}" placeholder="Adicione sua localização" id="textLocalizacao" readonly>
                        </td>
                    </tr>
                    
                    <tr class="linha-informacao-tabela">
                        <td class="coluna-title-informacao-tabela">
                            <img src="img/Tela_Perfil/BaloesIcon.png" width="30" heigth="40">
                            <span>Data de aniversário:</span>
                        </td>
                        <td class="coluna-text-informacao-tabela">
                            <input class="data-text-informacao-tabela" type="date" name="aniversario" value="${usuarioAniversario}" id="textAniversario" readonly>
                        </td>
                    </tr>
                </table> 
                
                <div class="button-edit-save">
                    <button type="button" id="button-edit-informacoes">Editar</button>
                    <button type="submit" id="button-save-informacoes">Salvar</button>
                </div>
                <security:csrfInput/>
               </form> 
            </section>
			</section>
            </main>
            <script src="Bootstrap/js/jquery-3.4.1.min.js" type="text/javascript"></script>
            <script src="Bootstrap/js/jquery.mask.js" type="text/javascript"></script>
            <script src="Bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
            <script src="JS/JS_home.js" type="text/javascript"></script>
            <script src="JS/JS_perfil.js" type="text/javascript"></script>
          </body>
</html>