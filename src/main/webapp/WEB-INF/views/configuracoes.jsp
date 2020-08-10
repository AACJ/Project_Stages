<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
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
        <link rel="stylesheet" type="text/css" href="CSS/style_configuracoes.css" media="screen">
        <link rel="shortcut icon" href="img/LOGO-OFFICIAL-200x200%20cortada.png">
        <link rel=stylesheet type="text/css" href="Bootstrap/css/bootstrap.min.css">
    </head>
 <body id="corpo" class="corpo">
  
       <%@include	file="/WEB-INF/views/home.jsp"	%>
            
            <main>
            <section class="gallery">
               <nav class="escolher-foto">
               <div class="foto">
                       <nav id="cont">
                           <img src="img/Tela_Principal/UsercomCirculoIcon.png" id="imagem">
                           <div class="foto-text">
                           </div>
                       </nav>
               </div>
                   <h3>${nomeUsuario}</h3>
               </nav>
            </section>
            
            <section class="configuracoes">
                <span class="config-titulo">Configurações</span>  
                <nav class="config-nav">
                    <div class="config01">
                        <img src="img/Tela_Configuracoes/DefinicoesIcon.png" width="30" height="30">
                        <span class="config-text">Personalizar</span>
                        <p>Configuração de tela</p>
                        <form action="/projectstages_mvc/salvar/modo-noturno" method="post" id="formAtivadoNoturno">
                        <span class="config-opcao">Modo Noturno:</span>
                
                        <input type="radio" name="modoNoturno" id="ativadoNoturno" value="true">
                        <label for="ativadoNoturno" class="config-label">Ativado</label>
                    
                        <input type="radio" name="modoNoturno" id="desativadoNoturno" value="false">
                        <label for="desativadoNoturno" class="config-label">Desativado</label> 
                        <security:csrfInput/>
                        </form>
                        
                        <p>Notificações</p>
                        <span class="config-opcao">Lembretes de prazo de entrega:</span>
                        <input type="radio" name="lembrete" id="ativadoLembrete">
                        <label for="ativadoLembrete" class="config-label">Ativado</label>
                        <input type="radio" name="lembrete" id="desativadoLembrete" checked>
                        <label for="desativadoLembrete" class="config-label">Desativado</label>
                        <br>
                        <span class="config-opcao">Atualização de status:</span>
                        <input type="radio" name="atualizacao" id="ativadoAtualizacao" checked>
                        <label for="ativadoLembrete" class="config-label">Ativado</label>
                        <input type="radio" name="atualizacao" id="desativadoAtualizacao" >
                        <label for="desativadoLembrete" class="config-label">Desativado</label>
                    </div>
                    
                    <div class="config02">
                        <img src="img/Tela_Configuracoes/Seguran%C3%A7aIcon.png" width="30" height="35">
                        <span class="config-text">Segurança</span>
                        <p>Senha</p>
                        <input type="checkbox" id="alteraSenha">
                        <label for="alteraSenha">
                        <span class="config-opcao">Alterar Senha</span>
                        <div class="setaBaixoConfig"><img src="img/setaDireitaBlueIcon.png"></div>
                        </label>    
                        <br>
                        <div class="caixa-altera-senha">
                        <span class="config-opcao" id="senhaAtual">Senha atual:</span>
                        <input type="password" name="senha" class="config-senha">
                        <br>
                        <span class="config-opcao" id="senhaNova">Nova senha:</span>
                        <input type="password" name="senha" class="config-senha">
                        <br>
                        <span class="config-opcao" id="senhaConfirmada">Confirmar senha:</span>
                        <input type="password" name="senha" class="config-senha">
                        <br>
                        <button type="submit" id="btnConfiguracaoSenha">Salvar</button>
                        </div>
                        <span class="config-opcao">Politica de senha:</span>
                        <input type="radio" name="politicaDeSenha" id="ativadoSenhaSegura">
                        <label for="ativadoSenhaSegura" class="config-label">Politica de senha segura</label>
                        <input type="radio" name="politicaDeSenha" id="ativadoSenhaRigorosa">
                        <label for="ativadoSenhaRigorosa" class="config-label">Politica de senha rigorosa</label> 
                    </div>
                    
                    <div class="config03"> 
                        <img src="img/Tela_Configuracoes/TermoIcon.png" width="30" height="35">
                        <span class="config-text">Termos de uso</span>
                        <p>Termo de uso(Link para o download do arquivo)</p>
                    </div>
                
                </nav>
            </section>

            </main>
            <script src="Bootstrap/js/jquery-3.4.1.min.js" type="text/javascript"></script>
            <script src="Bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
            <script src="JS/JS_home.js" type="text/javascript"></script>
            <script src="JS/JS_configuracoes.js" type="text/javascript"></script>
          </body>
</html>