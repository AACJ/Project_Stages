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
  
	<%@include	file="/WEB-INF/views/home.jsp"	%>
             
       <main>
            <section class="user-projetos">
            <div class="cabecalhoProjeto">
           <div class="caixa-informacoes-principais-projeto">
            <input type="text" class="nomeProjeto" value="${nomeProjeto}" id="nameProjectInfo" readonly>
            <input type="text" placeholder="Adicionar Descrição" value="${descricaoProjeto}" id="descricaoProjeto" readonly>
           </div>
           
           <div class="caixa-projeto-favorito">
           	<c:if test="${favorito == false}">
                <a href="/projectstages_mvc/add/favoritos?favorito=true" class="add-favorito"><img src="img/Tela_Principal/star.png"></a>
           	</c:if>
           	
           	<c:if test="${favorito == true}">
                <a href="/projectstages_mvc/add/favoritos?favorito=false" class="add-favorito"><img src="img/Tela_Principal/star2.png"></a>
           	</c:if>
            </div>
           
            <div class="caixa-mostra-participantes-projeto">
                <button type="button" class="botao-mostrar-participantes" id="btn-show-participantes">

                <div class="foto-botao-mostrar-participantes">
                    <img src="img/Tela_Principal/more1.png">    
                </div>
                <c:forEach items="${listaParticipantes}" var="participantes" varStatus="status">
               <c:if test="${status.index == 0}"> 
                <div class="foto-botao-projeto-participantes" id="foto-part01">
                   <c:if test="${participantes.foto == null}">
                    <img src="img/Tela_Principal/UsercomCirculoIcon.png">
                  </c:if>
                  
                   <c:if test="${participantes.foto != null}">
                   <img src="${participantes.foto}">
                	</c:if>
                </div>
                </c:if> 
                
                <c:if test="${status.index == 1}"> 
                 <div class="foto-botao-projeto-participantes" id="foto-part02">
                    <c:if test="${participantes.foto == null}">
                    <img src="img/Tela_Principal/UsercomCirculoIcon.png">
                  </c:if>
                  
                   <c:if test="${participantes.foto != null}">
                   <img src="${participantes.foto}">
                	</c:if>
                </div>
                </c:if>
                    
                 <c:if test="${status.index == 2}">  
                 <div class="foto-botao-projeto-participantes" id="foto-part03">
                    <c:if test="${participantes.foto == null}">
                    <img src="img/Tela_Principal/UsercomCirculoIcon.png">
                  </c:if>
                  
                   <c:if test="${participantes.foto != null}">
                   <img src="${participantes.foto}">
                	</c:if>
                </div>
                 </c:if>
                 
                 <c:if test="${status.index == 3}"> 
                 <div class="foto-botao-projeto-participantes" id="foto-part04">
                   <c:if test="${participantes.foto == null}">
                    <img src="img/Tela_Principal/UsercomCirculoIcon.png">
                  </c:if>
                  
                   <c:if test="${participantes.foto != null}">
                   <img src="${participantes.foto}">
                	</c:if>
                </div>
                 </c:if>
                    
                </c:forEach>
                
                 <c:if test="${qtnParticipante > 0}">
                 <div class="qtn-projeto-participantes" id="foto-part05">
                    <p>+${qtnParticipante}</p>
                </div>    
                </c:if>
                </button>
            </div>
           
            <nav class="caixa-de-botoes-projeto">
                
                <div class="caixa-botoes-alt-project-info">
                <button type="button" class="botao-edit-info-project" id="edit-project-info">Editar Informações</button>
                <button type="button" class="botao-save-info-project" id="save-project-info">Salvar Informações</button>
                </div>
                
                <button type ="button"class="botao-add-participante" id="btn-add-participante">Add participante</button>
                
                <button type="button" class="botao-excluir-projeto" id="btn-exit-project" value="${usuarioAtual.email}">Sair do projeto</button>
            </nav>
            </div>
            <div class="corpoProjeto">
                <table class="tabela-tarefas">
                    <thead>
                        <tr class="linha-titulo-tarefas">
                        <th class="titulo-tarefa">
                            ${tarefaProjeto}
                        </th>
                        <th class="titulo-tarefa-informacoes-proprietario">Proprietário</th>
                        <th class="titulo-tarefa-informacoes-status">Status</th>
                        <th class="titulo-tarefa-informacoes-data">Data de Entrega</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listaTarefas}"	var="tarefas" varStatus="status">
                        <tr class="linha-tarefa-projeto">
                        <td class="projeto-tarefa" id="card-tarefas">
                        <input type="text" class="projeto-tarefa-text" value="${tarefas.nome}" id="txtTarefa">
                        </td>
                        
                        <td class="projeto-proprietario">
                        	  <button type="button" class="botao-opcoes-proprietario" id="btn-op-proprietario" value="${tarefas.id}">
                           		
                                <div class="foto-proprietario-projeto">
                                <c:if test="${listaProprietarios[status.index] != null}">
                                
                                <c:if test="${listaProprietarios[status.index].foto == null}">
                  					<img src="img/Tela_Principal/UsercomCirculoIcon.png">
                  				</c:if>
                                
                                <c:if test="${listaProprietarios[status.index].foto != null}">
                  					<img src="${listaProprietarios[status.index].foto}">
                				</c:if>
                				
                				<c:if test="${listaProprietarios[status.index].statusUsuario == 'Disponivel'}"> 
                                <div class="status-proprietario-projeto" id="cor-stts-01" ></div>
                                </c:if>
                				
                				<c:if test="${listaProprietarios[status.index].statusUsuario == 'Indisponivel'}"> 
                                <div class="status-proprietario-projeto" id="cor-stts-02" ></div>
                                </c:if>
                                
                                <c:if test="${listaProprietarios[status.index].statusUsuario == 'Trabalhando'}"> 
                                <div class="status-proprietario-projeto" id="cor-stts-03" ></div>
                                </c:if>
                				
                				<c:if test="${listaProprietarios[status.index].statusUsuario == 'Temporiaramente'}"> 
                                <div class="status-proprietario-projeto" id="cor-stts-04" ></div>
                                </c:if>
                				
                				<c:if test="${listaProprietarios[status.index].statusUsuario == 'Doenca'}"> 
                                <div class="status-proprietario-projeto" id="cor-stts-05" ></div>
                                </c:if>
                				
                				<c:if test="${listaProprietarios[status.index].statusUsuario == 'Folga'}"> 
                                <div class="status-proprietario-projeto" id="cor-stts-06" ></div>
                                </c:if>
                				
                                </c:if>
                                
                               <c:if test="${listaProprietarios[status.index] == null}">
                                <div class="status-proprietario-projeto" id="position-null"></div>
                              	</c:if>
                          
                              	</div>
                            </button>
                           
                           <c:if test="${listaProprietarios[status.index] != null}">
                           <div class="indetificador-proprietario">
                               <div class="indetificador-proprietario-informacoes">
                                <p class="indetificador-nome">${listaProprietarios[status.index].userName}</p>
                                <p class="indetificador-id">ID: ${listaProprietarios[status.index].id}</p>
                                </div>
                            </div>
                           </c:if>
                           
                            <div class="caixa-opcoes-proprietario" id="box-proprietarios-${tarefas.id}">
                                <button type="button" class="fecha-caixa-proprietario-opcoes" id="fecha-box-proprietarios-${tarefas.id}"><img src="img/Tela_Principal/fecha-caixa-projetos-status.png"></button>
                                
                                <div class="pesquisa-proprietario-projeto">
                                <input type=search placeholder="Buscar por nome ou id" class="barra-pesquisa-proprietario" autocomplete="off" id="barra-search-proprietarios-${tarefas.id}">
                                </div>
                                
                                <div class="resultados-pesquisa-proprietario">
                                        
                                     <c:forEach items="${listaParticipantes}" var="participantes" varStatus="statusParticipantes"> 
                                   
                                <c:if test="${participantes.email != usuarioAtual.email}">    
                                   
                                    <a href="/projectstages_mvc/atualizar/tarefas-proprietario?idTarefa=${tarefas.id}&emailProprietario=${participantes.email}" class="perfil-proprietario" id="perfil-${tarefas.id}-proprietario-${participantes.id}">
                                        
                                        <div class="foto-perfil-proprietario">
                                        <c:if test="${participantes.foto == null}">
                  						  <img src="img/Tela_Principal/UsercomCirculoIcon.png">
                  						</c:if>
                           
                						<c:if test="${participantes.foto != null}">
                  							 <img src="${participantes.foto}">
                						</c:if>
                                        
                                       <c:if test="${participantes.statusUsuario == 'Disponivel'}"> 
                                       <div class="status-perfil-proprietario" id="cor-stts-01">
                                       </div>
                                       </c:if>
                                            
                                       <c:if test="${participantes.statusUsuario == 'Indisponivel'}"> 
                                       <div class="status-perfil-proprietario" id="cor-stts-02">
                                       </div>
                                       </c:if>
                                       
                                       <c:if test="${participantes.statusUsuario == 'Trabalhando'}"> 
                                       <div class="status-perfil-proprietario" id="cor-stts-03">
                                       </div>
                                       </c:if>
                                       
                                       <c:if test="${participantes.statusUsuario == 'Temporiaramente'}"> 
                                       <div class="status-perfil-proprietario" id="cor-stts-04">
                                       </div>
                                       </c:if>
                                       
                                       <c:if test="${participantes.statusUsuario == 'Doenca'}"> 
                                       <div class="status-perfil-proprietario" id="cor-stts-05">
                                       </div>
                                       </c:if>
                                        
                                        <c:if test="${participantes.statusUsuario == 'Folga'}"> 
                                       <div class="status-perfil-proprietario" id="cor-stts-06">
                                       </div>
                                       </c:if>
                                       </div>
                                       
                                        <div class="informacoes-proprietario">
                                            <p class="nome-proprietario">${participantes.userName}</p>
                                            <p class="id-proprietario">ID: ${participantes.id}</p>
                                        </div>
                                    </a> 
                                    
                                    </c:if>
                                    </c:forEach>
                                </div>
                                <a href="/projectstages_mvc/atualizar/tarefas-proprietario?idTarefa=${tarefas.id}&emailProprietario=${usuarioAtual.email}" class="botao-tornar-proprietario">Torna-se propriétario</a>
                                
                                <a href="/projectstages_mvc/remove/proprietario?idTarefa=${tarefas.id}&emailProprietario=${usuarioAtual.email}" class="botao-remove-proprietario">Remover propriétario</a>
                            </div>
                        </td>
                        
                       <td class="projeto-status">
                       
                            <button type="button" class="botao-opcoes-projeto-status"
                            id="open-caixa-projeto-status" value="${tarefas.id}">
                            <div class="opcoes-projeto-status" id="op-projeto-status01${status.index}">Finalizado</div>
                           
                            <div class="opcoes-projeto-status" id="op-projeto-status02${status.index}">Parado</div>
                                
                            <div class="opcoes-projeto-status" id="op-projeto-status03${status.index}">Em Análise</div>
                                
                            <div class="opcoes-projeto-status" id="op-projeto-status04${status.index}">Em Andamento</div>
                                
                            <div class="opcoes-projeto-status" id="op-projeto-status05${status.index}">Não Iniciado</div>
                            
                            </button>    
                           
                            <div class="caixa-opcoes-projeto-status" id="caixa-op-projeto-status${tarefas.id}">
                            <button class="fecha-caixa-opcoes-projeto-status" id="close-box-project-status${tarefas.id}">
                                <img src="img/Tela_Principal/fecha-caixa-projetos-status.png">
                            </button>
                            
                            <nav class="list-opcoes-projeto-status">
                            
                            <div class="item-list-opcoes-projeto-status">
                            <a href="/projectstages_mvc/atualizar/tarefas?idTarefa=${tarefas.id}&statusTarefa=Nao Iniciado" class="label-opcoes-projeto-status" id="label-op-projeto-status05">
                                Não Iniciado 
                            </a>
                            </div>
                            
                            <div class="item-list-opcoes-projeto-status">
                            <a href="/projectstages_mvc/salvar/desenvolvimentos?idTarefa=${tarefas.id}&statusTarefa=Em Andamento" class="label-opcoes-projeto-status" id="label-op-projeto-status04">
                                Em Andamento  
                            </a>
                            </div>
                           
                            <div class="item-list-opcoes-projeto-status">
                            <a href="/projectstages_mvc/salvar/desenvolvimentos?idTarefa=${tarefas.id}&statusTarefa=Em Analise" class="label-opcoes-projeto-status" id="label-op-projeto-status03">
                                Em Análise   
                            </a>
                            </div>
                                
                            <div class="item-list-opcoes-projeto-status">
                            <a href="/projectstages_mvc/salvar/desenvolvimentos?idTarefa=${tarefas.id}&statusTarefa=Parado" class="label-opcoes-projeto-status" id="label-op-projeto-status02">
                                Parado    
                            </a>
                            </div>
                                
                            <div class="item-list-opcoes-projeto-status">
                            <a href="/projectstages_mvc/atualizar/tarefas-concluidos?idTarefa=${tarefas.id}&statusTarefa=Finalizado" class="label-opcoes-projeto-status" id="label-op-projeto-status01">
                                Finalizado    
                            </a>
                            </div>
                                
                            </nav>
                            </div>
                        
                            </td>
      
                        <td class="projeto-data">
                        <input type="date" class="projeto-data-text" id="dateProjeto" value="${listaAjustaData[status.index]}">    
                        </td>

                        <td class="projeto-delete">
                        <c:if test="${minhaFuncao != 'membro'}">
                        <a href="/projectstages_mvc/delete/tarefas?idTarefa=${tarefas.id}" class="botao-projeto-delete">
                        <img src="img/Tela_Principal/trash1.png">    
                        </a>
                        </c:if>
                        
                        <c:if test="${minhaFuncao == 'membro'}">
                        <button type="button" class="botao-projeto-delete">
                        <img src="img/Tela_Principal/trash1.png">    
                        </button>
                        </c:if>
                        
                        
                        </td>
                        </tr>
                        
                        </c:forEach>
                    </tbody>
                    
                    <tfoot>
                        <tr class="botao-add-projeto">
                            <td class="projeto-add-tarefa">
                            <button type="button" class="addTarefa" id="btnTarefas">Adicionar&#43;</button>
                            </td>
                        </tr>
                    </tfoot>
                </table>
                
                 <table class="tabela-tarefas">
                    <thead>
                        <tr class="linha-titulo-tarefas">
                        <th class="titulo-tarefa">
                            ${desenvolvimentoProjeto}
                        </th>
                        <th class="titulo-tarefa-informacoes-proprietario">Proprietário</th>
                        <th class="titulo-tarefa-informacoes-status">Status</th>
                        <th class="titulo-tarefa-informacoes-data">Data de Entrega</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listDesenvolvimentos}"	var="tarefas" varStatus="status">
                        <tr class="linha-tarefa-projeto">
                        <td class="projeto-tarefa" id="card-desenvolvimentos">
                        <input type="text" class="projeto-tarefa-text" value="${tarefas.nome}" id="txtTarefa">
                        </td>
                        
                        <td class="projeto-proprietario">
                        	  <button type="button" class="botao-opcoes-proprietario" id="btn-op-proprietario" value="${tarefas.id}">
                           		
                                <div class="foto-proprietario-projeto">
                                <c:if test="${listaProprietariosDesenvolvimentos[status.index] != null}">
                                
                                <c:if test="${listaProprietariosDesenvolvimentos[status.index].foto == null}">
                  					<img src="img/Tela_Principal/UsercomCirculoIcon.png">
                  				</c:if>
                                
                                <c:if test="${listaProprietariosDesenvolvimentos[status.index].foto != null}">
                  					<img src="${listaProprietariosDesenvolvimentos[status.index].foto}">
                				</c:if>
                				
                				<c:if test="${listaProprietariosDesenvolvimentos[status.index].statusUsuario == 'Disponivel'}"> 
                                <div class="status-proprietario-projeto" id="cor-stts-01" ></div>
                                </c:if>
                				
                				<c:if test="${listaProprietariosDesenvolvimentos[status.index].statusUsuario == 'Indisponivel'}"> 
                                <div class="status-proprietario-projeto" id="cor-stts-02" ></div>
                                </c:if>
                                
                                <c:if test="${listaProprietariosDesenvolvimentos[status.index].statusUsuario == 'Trabalhando'}"> 
                                <div class="status-proprietario-projeto" id="cor-stts-03" ></div>
                                </c:if>
                				
                				<c:if test="${listaProprietariosDesenvolvimentos[status.index].statusUsuario == 'Temporiaramente'}"> 
                                <div class="status-proprietario-projeto" id="cor-stts-04" ></div>
                                </c:if>
                				
                				<c:if test="${listaProprietariosDesenvolvimentos[status.index].statusUsuario == 'Doenca'}"> 
                                <div class="status-proprietario-projeto" id="cor-stts-05" ></div>
                                </c:if>
                				
                				<c:if test="${listaProprietariosDesenvolvimentos[status.index].statusUsuario == 'Folga'}"> 
                                <div class="status-proprietario-projeto" id="cor-stts-06" ></div>
                                </c:if>
                				
                                </c:if>
                                
                               <c:if test="${listaProprietariosDesenvolvimentos[status.index] == null}">
                                <div class="status-proprietario-projeto" id="position-null"></div>
                              	</c:if>
                          
                              	</div>
                            </button>
                           
                           <c:if test="${listaProprietariosDesenvolvimentos[status.index] != null}">
                           <div class="indetificador-proprietario">
                               <div class="indetificador-proprietario-informacoes">
                                <p class="indetificador-nome">${listaProprietariosDesenvolvimentos[status.index].userName}</p>
                                <p class="indetificador-id">ID: ${listaProprietariosDesenvolvimentos[status.index].id}</p>
                                </div>
                            </div>
                           </c:if>
                           
                            <div class="caixa-opcoes-proprietario" id="box-proprietarios-${tarefas.id}">
                                <button type="button" class="fecha-caixa-proprietario-opcoes" id="fecha-box-proprietarios-${tarefas.id}"><img src="img/Tela_Principal/fecha-caixa-projetos-status.png"></button>
                                
                                <div class="pesquisa-proprietario-projeto">
                                <input type=search placeholder="Buscar por nome ou id" class="barra-pesquisa-proprietario" autocomplete="off" id="barra-search-proprietarios-${tarefas.id}">
                                </div>
                                
                                <div class="resultados-pesquisa-proprietario">
                                        
                                     <c:forEach items="${listaParticipantes}" var="participantes" varStatus="statusParticipantes"> 
                                   
                                <c:if test="${participantes.email != usuarioAtual.email}">    
                                   
                                    <a href="/projectstages_mvc/atualizar/desenvolvimentos-proprietario?idTarefa=${tarefas.id}&emailProprietario=${participantes.email}" class="perfil-proprietario" id="perfil-${tarefas.id}-proprietario-${participantes.id}">
                                        
                                        <div class="foto-perfil-proprietario">
                                        <c:if test="${participantes.foto == null}">
                  						  <img src="img/Tela_Principal/UsercomCirculoIcon.png">
                  						</c:if>
                           
                						<c:if test="${participantes.foto != null}">
                  							 <img src="${participantes.foto}">
                						</c:if>
                                        
                                       <c:if test="${participantes.statusUsuario == 'Disponivel'}"> 
                                       <div class="status-perfil-proprietario" id="cor-stts-01">
                                       </div>
                                       </c:if>
                                            
                                       <c:if test="${participantes.statusUsuario == 'Indisponivel'}"> 
                                       <div class="status-perfil-proprietario" id="cor-stts-02">
                                       </div>
                                       </c:if>
                                       
                                       <c:if test="${participantes.statusUsuario == 'Trabalhando'}"> 
                                       <div class="status-perfil-proprietario" id="cor-stts-03">
                                       </div>
                                       </c:if>
                                       
                                       <c:if test="${participantes.statusUsuario == 'Temporiaramente'}"> 
                                       <div class="status-perfil-proprietario" id="cor-stts-04">
                                       </div>
                                       </c:if>
                                       
                                       <c:if test="${participantes.statusUsuario == 'Doenca'}"> 
                                       <div class="status-perfil-proprietario" id="cor-stts-05">
                                       </div>
                                       </c:if>
                                        
                                        <c:if test="${participantes.statusUsuario == 'Folga'}"> 
                                       <div class="status-perfil-proprietario" id="cor-stts-06">
                                       </div>
                                       </c:if>
                                       </div>
                                       
                                        <div class="informacoes-proprietario">
                                            <p class="nome-proprietario">${participantes.userName}</p>
                                            <p class="id-proprietario">ID: ${participantes.id}</p>
                                        </div>
                                    </a> 
                                    
                                    </c:if>
                                    </c:forEach>
                                </div>
                                <a href="/projectstages_mvc/atualizar/desenvolvimentos-proprietario?idTarefa=${tarefas.id}&emailProprietario=${usuarioAtual.email}" class="botao-tornar-proprietario">Torna-se propriétario</a>
                                
                                <a href="/projectstages_mvc/remove/proprietario-desenvolvimentos?idTarefa=${tarefas.id}&emailProprietario=${usuarioAtual.email}" class="botao-remove-proprietario">Remover propriétario</a>
                            </div>
                        </td>
                        
                       <td class="projeto-status">
                       
                            <button type="button" class="botao-opcoes-projeto-status"
                            id="open-caixa-projeto-status" value="${tarefas.id}">
                            <div class="opcoes-projeto-status" id="op-desenvolvimento-status01${status.index}">Finalizado</div>
                           
                            <div class="opcoes-projeto-status" id="op-desenvolvimento-status02${status.index}">Parado</div>
                                
                            <div class="opcoes-projeto-status" id="op-desenvolvimento-status03${status.index}">Em Análise</div>
                                
                            <div class="opcoes-projeto-status" id="op-desenvolvimento-status04${status.index}">Em Andamento</div>
                                
                            <div class="opcoes-projeto-status" id="op-desenvolvimento-status05${status.index}">Não Iniciado</div>
                            
                            </button>    
                           
                            <div class="caixa-opcoes-projeto-status" id="caixa-op-projeto-status${tarefas.id}">
                            <button class="fecha-caixa-opcoes-projeto-status" id="close-box-project-status${tarefas.id}">
                                <img src="img/Tela_Principal/fecha-caixa-projetos-status.png">
                            </button>
                            
                            <nav class="list-opcoes-projeto-status">
                            
                            <div class="item-list-opcoes-projeto-status">
                            <a href="/projectstages_mvc/atualiza/tarefas-desenvolvimentos?idTarefa=${tarefas.id}&statusTarefa=Nao Iniciado" class="label-opcoes-projeto-status" id="label-op-projeto-status05">
                                Não Iniciado 
                            </a>
                            </div>
                            
                            <div class="item-list-opcoes-projeto-status">
                            <a href="/projectstages_mvc/atualizar/desenvolvimentos?idTarefa=${tarefas.id}&statusTarefa=Em Andamento" class="label-opcoes-projeto-status" id="label-op-projeto-status04">
                                Em Andamento  
                            </a>
                            </div>
                           
                            <div class="item-list-opcoes-projeto-status">
                            <a href="/projectstages_mvc/atualizar/desenvolvimentos?idTarefa=${tarefas.id}&statusTarefa=Em Analise" class="label-opcoes-projeto-status" id="label-op-projeto-status03">
                                Em Análise   
                            </a>
                            </div>
                                
                            <div class="item-list-opcoes-projeto-status">
                            <a href="/projectstages_mvc/atualizar/desenvolvimentos?idTarefa=${tarefas.id}&statusTarefa=Parado" class="label-opcoes-projeto-status" id="label-op-projeto-status02">
                                Parado    
                            </a>
                            </div>
                                
                            <div class="item-list-opcoes-projeto-status">
                            <a href="/projectstages_mvc/salvar/concluidos?idTarefa=${tarefas.id}&statusTarefa=Finalizado" class="label-opcoes-projeto-status" id="label-op-projeto-status01">
                                Finalizado    
                            </a>
                            </div>
                                
                            </nav>
                            </div>
                        
                            </td>
      
                        <td class="projeto-data">
                        <input type="date" class="projeto-data-text" id="dateProjeto" value="${listaAjustaDataDesenvolvimentos[status.index]}">    
                        </td>
    
                        <td class="projeto-delete">
                        <c:if test="${minhaFuncao != 'membro'}">
                        <a href="/projectstages_mvc/delete/desenvolvimentos?idTarefa=${tarefas.id}" class="botao-projeto-delete">
                        <img src="img/Tela_Principal/trash1.png">    
                        </a>
                        </c:if>
                        
                        <c:if test="${minhaFuncao == 'membro'}">
                        <button type="button" class="botao-projeto-delete">
                        <img src="img/Tela_Principal/trash1.png">    
                        </button>
                        </c:if>
                        
                        
                        </td>
                        </tr>
                        
                        </c:forEach>
                    </tbody>
                    
                    <tfoot>
                        <tr class="botao-add-projeto">
                            <td class="projeto-add-tarefa">
                            </td>
                        </tr>
                    </tfoot>
                </table>
                
                <table class="tabela-tarefas">
                    <thead>
                        <tr class="linha-titulo-tarefas">
                        <th class="titulo-tarefa">
                            ${concluidoProjeto}
                        </th>
                        <th class="titulo-tarefa-informacoes-proprietario">Proprietário</th>
                        <th class="titulo-tarefa-informacoes-status">Status</th>
                        <th class="titulo-tarefa-informacoes-data">Data de Entrega</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listConcluidos}" var="tarefas" varStatus="status">
                        <tr class="linha-tarefa-projeto">
                        <td class="projeto-tarefa" id="card-concluidos">
                        <input type="text" class="projeto-tarefa-text" value="${tarefas.nome}" id="txtTarefa">
                        </td>
                        
                        <td class="projeto-proprietario">
                        	  <button type="button" class="botao-opcoes-proprietario" id="btn-op-proprietario" value="${tarefas.id}">
                           		
                                <div class="foto-proprietario-projeto">
                                <c:if test="${listaProprietariosConcluidos[status.index] != null}">
                                
                                <c:if test="${listaProprietariosConcluidos[status.index].foto == null}">
                  					<img src="img/Tela_Principal/UsercomCirculoIcon.png">
                  				</c:if>
                                
                                <c:if test="${listaProprietariosConcluidos[status.index].foto != null}">
                  					<img src="${listaProprietariosConcluidos[status.index].foto}">
                				</c:if>
                				
                				<c:if test="${listaProprietariosConcluidos[status.index].statusUsuario == 'Disponivel'}"> 
                                <div class="status-proprietario-projeto" id="cor-stts-01" ></div>
                                </c:if>
                				
                				<c:if test="${listaProprietariosConcluidos[status.index].statusUsuario == 'Indisponivel'}"> 
                                <div class="status-proprietario-projeto" id="cor-stts-02" ></div>
                                </c:if>
                                
                                <c:if test="${listaProprietariosConcluidos[status.index].statusUsuario == 'Trabalhando'}"> 
                                <div class="status-proprietario-projeto" id="cor-stts-03" ></div>
                                </c:if>
                				
                				<c:if test="${listaProprietariosConcluidos[status.index].statusUsuario == 'Temporiaramente'}"> 
                                <div class="status-proprietario-projeto" id="cor-stts-04" ></div>
                                </c:if>
                				
                				<c:if test="${listaProprietariosConcluidos[status.index].statusUsuario == 'Doenca'}"> 
                                <div class="status-proprietario-projeto" id="cor-stts-05" ></div>
                                </c:if>
                				
                				<c:if test="${listaProprietariosConcluidos[status.index].statusUsuario == 'Folga'}"> 
                                <div class="status-proprietario-projeto" id="cor-stts-06" ></div>
                                </c:if>
                				
                                </c:if>
                                
                               <c:if test="${listaProprietariosConcluidos[status.index] == null}">
                                <div class="status-proprietario-projeto" id="position-null"></div>
                              	</c:if>
                          
                              	</div>
                            </button>
                           
                           <c:if test="${listaProprietariosConcluidos[status.index] != null}">
                           <div class="indetificador-proprietario">
                               <div class="indetificador-proprietario-informacoes">
                                <p class="indetificador-nome">${listaProprietariosConcluidos[status.index].userName}</p>
                                <p class="indetificador-id">ID: ${listaProprietariosConcluidos[status.index].id}</p>
                                </div>
                            </div>
                           </c:if>
                           
                            <div class="caixa-opcoes-proprietario" id="box-proprietarios-${tarefas.id}">
                                <button type="button" class="fecha-caixa-proprietario-opcoes" id="fecha-box-proprietarios-${tarefas.id}"><img src="img/Tela_Principal/fecha-caixa-projetos-status.png"></button>
                                
                                <div class="pesquisa-proprietario-projeto">
                                <input type=search placeholder="Buscar por nome ou id" class="barra-pesquisa-proprietario" autocomplete="off" id="barra-search-proprietarios-${tarefas.id}">
                                </div>
                                
                                <div class="resultados-pesquisa-proprietario">
                                        
                                     <c:forEach items="${listaParticipantes}" var="participantes" varStatus="statusParticipantes"> 
                                   
                                <c:if test="${participantes.email != usuarioAtual.email}">    
                                   
                                    <a href="/projectstages_mvc/atualizar/concluidos-proprietario?idTarefa=${tarefas.id}&emailProprietario=${participantes.email}" class="perfil-proprietario" id="perfil-${tarefas.id}-proprietario-${participantes.id}">
                                        
                                        <div class="foto-perfil-proprietario">
                                        <c:if test="${participantes.foto == null}">
                  						  <img src="img/Tela_Principal/UsercomCirculoIcon.png">
                  						</c:if>
                           
                						<c:if test="${participantes.foto != null}">
                  							 <img src="${participantes.foto}">
                						</c:if>
                                        
                                       <c:if test="${participantes.statusUsuario == 'Disponivel'}"> 
                                       <div class="status-perfil-proprietario" id="cor-stts-01">
                                       </div>
                                       </c:if>
                                            
                                       <c:if test="${participantes.statusUsuario == 'Indisponivel'}"> 
                                       <div class="status-perfil-proprietario" id="cor-stts-02">
                                       </div>
                                       </c:if>
                                       
                                       <c:if test="${participantes.statusUsuario == 'Trabalhando'}"> 
                                       <div class="status-perfil-proprietario" id="cor-stts-03">
                                       </div>
                                       </c:if>
                                       
                                       <c:if test="${participantes.statusUsuario == 'Temporiaramente'}"> 
                                       <div class="status-perfil-proprietario" id="cor-stts-04">
                                       </div>
                                       </c:if>
                                       
                                       <c:if test="${participantes.statusUsuario == 'Doenca'}"> 
                                       <div class="status-perfil-proprietario" id="cor-stts-05">
                                       </div>
                                       </c:if>
                                        
                                        <c:if test="${participantes.statusUsuario == 'Folga'}"> 
                                       <div class="status-perfil-proprietario" id="cor-stts-06">
                                       </div>
                                       </c:if>
                                       </div>
                                       
                                        <div class="informacoes-proprietario">
                                            <p class="nome-proprietario">${participantes.userName}</p>
                                            <p class="id-proprietario">ID: ${participantes.id}</p>
                                        </div>
                                    </a> 
                                    
                                    </c:if>
                                    </c:forEach>
                                </div>
                                <a href="/projectstages_mvc/atualizar/concluidos-proprietario?idTarefa=${tarefas.id}&emailProprietario=${usuarioAtual.email}" class="botao-tornar-proprietario">Torna-se propriétario</a>
                                
                                <a href="/projectstages_mvc/remove/proprietario-concluidos?idTarefa=${tarefas.id}&emailProprietario=${usuarioAtual.email}" class="botao-remove-proprietario">Remover propriétario</a>
                            </div>
                        </td>
                        
                       <td class="projeto-status">
                       
                            <button type="button" class="botao-opcoes-projeto-status"
                            id="open-caixa-projeto-status" value="${tarefas.id}">
                            <div class="opcoes-projeto-status" id="op-concluido-status01${status.index}">Finalizado</div>
                           
                            <div class="opcoes-projeto-status" id="op-concluido-status02${status.index}">Parado</div>
                                
                            <div class="opcoes-projeto-status" id="op-concluido-status03${status.index}">Em Análise</div>
                                
                            <div class="opcoes-projeto-status" id="op-concluido-status04${status.index}">Em Andamento</div>
                                
                            <div class="opcoes-projeto-status" id="op-concluido-status05${status.index}">Não Iniciado</div>
                            
                            </button>    
                           
                            <div class="caixa-opcoes-projeto-status" id="caixa-op-projeto-status${tarefas.id}">
                            <button class="fecha-caixa-opcoes-projeto-status" id="close-box-project-status${tarefas.id}">
                                <img src="img/Tela_Principal/fecha-caixa-projetos-status.png">
                            </button>
                            
                            <nav class="list-opcoes-projeto-status">
                            
                            <div class="item-list-opcoes-projeto-status">
                            <a href="/projectstages_mvc/atualiza/concluidos-tarefas?idTarefa=${tarefas.id}&statusTarefa=Nao Iniciado" class="label-opcoes-projeto-status" id="label-op-projeto-status05">
                                Não Iniciado 
                            </a>
                            </div>
                            
                            <div class="item-list-opcoes-projeto-status">
                            <a href="/projectstages_mvc/atualiza/concluidos-desenvolvimentos?idTarefa=${tarefas.id}&statusTarefa=Em Andamento" class="label-opcoes-projeto-status" id="label-op-projeto-status04">
                                Em Andamento  
                            </a>
                            </div>
                           
                            <div class="item-list-opcoes-projeto-status">
                            <a href="/projectstages_mvc/atualiza/concluidos-desenvolvimentos?idTarefa=${tarefas.id}&statusTarefa=Em Analise" class="label-opcoes-projeto-status" id="label-op-projeto-status03">
                                Em Análise   
                            </a>
                            </div>
                                
                            <div class="item-list-opcoes-projeto-status">
                            <a href="/projectstages_mvc/atualiza/concluidos-desenvolvimentos?idTarefa=${tarefas.id}&statusTarefa=Parado" class="label-opcoes-projeto-status" id="label-op-projeto-status02">
                                Parado    
                            </a>
                            </div>
                                
                            <div class="item-list-opcoes-projeto-status">
                            <a href="/projectstages_mvc/atualizar/concluidos?idTarefa=${tarefas.id}&statusTarefa=Finalizado" class="label-opcoes-projeto-status" id="label-op-projeto-status01">
                                Finalizado    
                            </a>
                            </div>
                                
                            </nav>
                            </div>
                        
                            </td>
      
                        <td class="projeto-data">
                        <input type="date" class="projeto-data-text" id="dateProjeto" value="${listaAjustaDataConcluidos[status.index]}">    
                        </td>

                        <td class="projeto-delete">
                        <c:if test="${minhaFuncao != 'membro'}">
                        <a href="/projectstages_mvc/delete/concluidos?idTarefa=${tarefas.id}" class="botao-projeto-delete">
                        <img src="img/Tela_Principal/trash1.png">     
                        </a>
                        </c:if>
                        
                        <c:if test="${minhaFuncao == 'membro'}">
                        <button type="button" class="botao-projeto-delete">
                        <img src="img/Tela_Principal/trash1.png">   
                        </button>
                        </c:if>
                        
                        
                        </td>
                        </tr>
                        
                        </c:forEach>
                    </tbody>
                    
                    <tfoot>
                        <tr class="botao-add-projeto">
                            <td class="projeto-add-tarefa">
                            </td>
                        </tr>
                    </tfoot>
                </table>
                
                
            </div>
            </section>
        </main>
        
 		<div class="caixa-add-participantes-externo" id="box-externo-participante">
            <div class="caixa-participantes-interno" id="box-interno-participante">
                <a href="/projectstages_mvc/home?idProjeto=${idProjeto}&nome=${nomeProjeto}" class="fecha-caixa-participantes" id="close-box-participante"><img src="img/Tela_Principal/closer01.png"></a>
                <div class="content-add-participantes">
                <h2 class="title-caixa-participantes">Adicione participantes.</h2>
                <input type="search" placeholder="Busca por nome de usuario ou id" class="pesquisa-add-participantes" autocomplete="off" id="pesquisa-amigo-participante">
                <button type="button" class="lupa-participantes"><img src="img/Tela_Amigo/lupa.png"></button>
                <div class="caixa-resultado-add-partcipantes">
                   
                <c:forEach items="${listaAmigos}" var="amigo" varStatus="status">   
                <a class="participantes-perfil" id="participante-perfil-${amigo.id}">
                 <div class="participante-foto">
                  <c:if test="${amigo.foto == null}">
                    <img src="img/Tela_Principal/UsercomCirculoIcon.png">
                  </c:if>
                           
                <c:if test="${amigo.foto != null}">
                   <img src="${amigo.foto}">
                </c:if>
                </div>
        		 <div class="participante-informacoes">
                 <p class="nome-participante">${amigo.userName}</p>
                 <p class="id-participante">ID: ${amigo.id}</p>
                 </div>
                 <button type="button" class="add-participante" id="add-participante" value="${amigo.email}">ADD</button>
                </a>
                </c:forEach>
                       
                     </div>
                </div>
            </div>
        </div>

	<div class="caixa-add-participantes-externo" id="box-externo-gerenciamento">
            <div class="caixa-participantes-interno" id="box-interno-gerenciamento">
              <a href="/projectstages_mvc/home?idProjeto=${idProjeto}&nome=${nomeProjeto}" class="fecha-caixa-participantes" id="close-box-gerenciamento"><img src="img/Tela_Principal/closer01.png"></a>
                <div class="content-add-participantes">
                <h2 class="title-caixa-participantes">Participantes do projeto.</h2>
                <input type="search" placeholder="Busca por nome de usuario ou id" class="pesquisa-add-participantes" autocomplete="off" id="pesquisa-amigo-gerenciamento">
                <button type="button" class="lupa-participantes"><img src="img/Tela_Amigo/lupa.png"></button>
                <div class="caixa-resultado-add-partcipantes">
                   
                <c:forEach items="${listaParticipantes}" var="participantes" varStatus="status">  
                
                <a class="participantes-perfil" id="participante-projeto-perfil-${participantes.id}">
                 <div class="participante-foto">
                  <c:if test="${participantes.foto == null}">
                    <img src="img/Tela_Principal/UsercomCirculoIcon.png">
                  </c:if>
                           
                <c:if test="${participantes.foto != null}">
                   <img src="${participantes.foto}">
                </c:if>
                </div>
        		 <div class="projeto-participante-informacoes">
                 <p class="nome-participante">${participantes.userName}</p>
                 <p class="id-participante">ID: ${participantes.id}</p>
                 </div>
           
         		 <c:if test="${minhaFuncao == 'criador'}">
                <c:if test="${listaFuncoes[status.index] != 'criador'}">
                 <div class="caixa-botoes-adm">
                <div class="botoes-adm">
                <button type="button" class="tornar-adm-participante" id="torna-adm" value="${participantes.email}">Tornar ADM</button>
                    
                 <button type="button" class="retirar-adm-participante" id="retirar-adm" value="${participantes.email}">Retirar ADM</button>
                </div>
                    
                    
                 <button type="button" class="remove-participante" id="remove-participante" value="${participantes.email}">Remove</button>
                </div> 
               	</c:if>
           </c:if>
           
           <c:if test="${minhaFuncao == 'adm'}">
           		<c:if test="${participantes.email == usuarioAtual.email}">
           			<button type="button" class="adm-participante">ADM</button>
           		</c:if>
       
       	<c:if test="${participantes.email != usuarioAtual.email}">
                <c:if test="${listaFuncoes[status.index] == 'adm' || listaFuncoes[status.index] == 'membro'}">
                 <div class="caixa-botoes-adm">
                <div class="botoes-adm">
                <button type="button" class="tornar-adm-participante" id="torna-adm" value="${participantes.email}">Tornar ADM</button>
                    
                 <button type="button" class="retirar-adm-participante" id="retirar-adm" value="${participantes.email}">Retirar ADM</button>
                </div>
                    
                    
                 <button type="button" class="remove-participante" id="remove-participante" value="${participantes.email}">Remove</button>
                </div> 
               	</c:if>
               	</c:if>
           </c:if>
          
           <c:if test="${minhaFuncao == 'membro'}">
          		<c:if test="${listaFuncoes[status.index] == 'adm'}">
                 <button type="button" class="adm-participante">ADM</button>
               	</c:if>
          	</c:if>	
          		
                <c:if test="${listaFuncoes[status.index] == 'criador'}">
                 <button type="button" class="criador-participante">Criador</button>
               	</c:if>
                
                </a>
                
                </c:forEach>
                       
                     </div>
                </div>
            </div>
        </div>


        <div class="caixa-add-tarefas-externo" id="caixa-add-tarefas">
            <div class="caixa-add-tarefas-interno">
                <button type="button" class="fecha-caixa-tarefas" id="fecha-caixa-add-tarefas01"><img src="img/Tela_Principal/closer01.png"></button>
                <div class="content-add-tarefas">
                <h2 class="title-caixa-tarefas">Adicione uma tarefa.</h2>
                <form action="/projectstages_mvc/salvar/tarefas" method="post">
                <p>Nome da tarefa:</p>
                <input type="text" class="text-caixa-add-tarefas" placeholder="Escreva o nome da tarefa!" name="nome" autocomplete="off" required>
                <p>Data de Entrega:</p>
                <input type="date" class="text-caixa-add-data-prazo" name="prazo" required>
                <input type="submit" class="botao-caixa-add-tarefas" value="Adicionar">
                 <security:csrfInput/>
                </form>
                </div>
            </div>
        </div>
        
        <div class="caixa-sair-projeto-externo" id="caixa-sair-projeto-externo">
            <div class="caixa-sair-projeto-interno" id="caixa-sair-projeto-interno">
               <button type="button" class="fecha-caixa-sair-projeto" id="fecha-caixa-sair-projeto"><img src="img/Tela_Principal/closer01.png"></button>
                
                <p class="title-exit-project">Tem certeza que deseja sair do projeto?</p>
               
                <c:if test="${minhaFuncao == 'membro'}">
                <a href="/projectstages_mvc/sair/projeto?email=${usuarioAtual.email}" class="botao-sim-saida" id="yes-exit">Sim</a>
                </c:if>
                
                <c:if test="${minhaFuncao == 'criador' || minhaFuncao == 'adm'}">
                <a href="/projectstages_mvc/sair/projeto-criador?email=${usuarioAtual.email}" class="botao-sim-saida" id="yes-exit">Sim</a>
                </c:if>
                
                <button type="button" class="botao-cancel-saida" id="cancel-exil">Cancelar</button>
            </div>
        </div>
        
        <script src="Bootstrap/js/jquery-3.4.1.min.js" type="text/javascript"></script>
        <script src="Bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
       	<script src="JS/JS_home.js" type="text/javascript"></script>
      	<script src="JS/JS_projetos.js" type="text/javascript"></script>
    </body>
    
</html>
