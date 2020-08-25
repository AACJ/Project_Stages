<!DOCTYPE html>
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
        <link rel="stylesheet" type="text/css" href="CSS/style_cronograma.css" media="screen">
        <link rel="shortcut icon" href="img/LOGO-OFFICIAL-200x200%20cortada.png">
        <link rel=stylesheet type="text/css" href="Bootstrap/css/bootstrap.min.css">
    </head>
  <body id="corpo" class="corpo">
  
       <%@include	file="/WEB-INF/views/home.jsp"	%>  
            
            <main>
            <section class="user-cronograma">
            <section class="calendario">
                <nav class="calendario-nav">
                    <table class="tabela-calendario">
                       <thead>
                        <tr>
                            <th><button id="btnEsquerda" class="btnPassMesAno"><img src="img/Tela_Principal/SetaEsquerdaIcon.png" width="10" height="10"></button></th>
                            <th colspan="5" id="mesAno">Março 2020</th>
                           <th><button id="btnDireita" class="btnPassMesAno"><img src="img/Tela_Principal/SetaDireitaIcon.png" width="10" height="10"></button></th>
                        </tr>
                        <tr>
                            <td>Dom</td>
                            <td>Seg</td>
                            <td>Ter</td>
                            <td>Qua</td>
                            <td>Qui</td>
                            <td>Sex</td>
                            <td>Sab</td>
                        </tr>
                        </thead>
                        
                        <tbody id="corpo-calendario">
                        
                        </tbody>
                    </table>
                </nav>
            </section>

            <section class="compromissos">
                <nav class="cronograma">
                    <ul class="cronograma-nav">
                        
                    <li class="cronograma-item">
                    <input type="checkbox" id="checkCronograma">
                    <label for="checkCronograma">
                    <div class="setaBaixoCronograma"><img src="img/setaDireitaBlueIcon.png"></div>
                    <span>Semana</span>
                    </label> 
                    <ul class="tarefas-semana">
                    <c:forEach items="${listaTarefasSemana}" var="tarefasSemana" varStatus="statusSemana">
                    
                    <c:if test="${listaProprietariosSemana[statusSemana.index].userName != null}">
                        <li class="exibir-tarefas-semana"><p>${listaNomesTarefasSemana[statusSemana.index]} | ${tarefasSemana.nome} - ${listaProprietariosSemana[statusSemana.index].userName} - ${tarefasSemana.statusProjeto} - ${dataSemana[statusSemana.index]}</p></li>        
                    </c:if>
                    
                    <c:if test="${listaProprietariosSemana[statusSemana.index].userName == null}">
                        <li class="exibir-tarefas-semana"><p>${listaNomesTarefasSemana[statusSemana.index]} | ${tarefasSemana.nome} - Nenhum proprietário - ${tarefasSemana.statusProjeto} - ${dataSemana[statusSemana.index]}</p></li>        
                    </c:if>
                    
                    </c:forEach>
                    
                    <c:forEach items="${listaDesenvolvimentosSemana}" var="desenvolvimentosSemana" varStatus="statusDesenSemana">
                      <c:if test="${listaProprietariosDesenvolvimentosSemana[statusDesenSemana.index].userName != null}">
                    	<li class="exibir-tarefas-semana"><p>${listaNomeDesenvolvimentosSemana[statusDesenSemana.index]} | ${desenvolvimentosSemana.nome} - ${listaProprietariosDesenvolvimentosSemana[statusDesenSemana.index].userName} - ${desenvolvimentosSemana.statusProjeto} - ${dataDesenvolvimentosSemana[statusDesenSemana.index]}</p></li>   
                      </c:if>
                      
                       <c:if test="${listaProprietariosDesenvolvimentosSemana[statusDesenSemana.index].userName == null}">
                    	<li class="exibir-tarefas-semana"><p>${listaNomeDesenvolvimentosSemana[statusDesenSemana.index]} | ${desenvolvimentosSemana.nome} - Nenhum proprietário - ${desenvolvimentosSemana.statusProjeto} - ${dataDesenvolvimentosSemana[statusDesenSemana.index]}</p></li>   
                      </c:if>
                    </c:forEach>
                    
                    </ul>
                    </li>
                        
                    <li class="cronograma-item">
                    <input type="checkbox" id="checkCronograma2">
                    <label for="checkCronograma2">
                    <div class="setaBaixoCronograma"><img src="img/setaDireitaBlueIcon.png"></div>
                    <span>Hoje</span>
                    </label>
                    <ul class="tarefas-hoje">
                    <c:forEach items="${listaTarefasHoje}" var="tarefasHoje" varStatus="status">
                    
                    	<c:if test="${listaProprietarios[status.index].userName != null}">
                       
                        <li class="exibir-tarefas"><p>${listaNomesTarefas[status.index]} | ${tarefasHoje.nome} - ${listaProprietarios[status.index].userName} - ${tarefasHoje.statusProjeto} - ${dataHoje[status.index]}</p></li>        
                    	
                    	</c:if>
                    	
                    	<c:if test="${listaProprietarios[status.index].userName == null}">
                    	
                        <li class="exibir-tarefas"><p>${listaNomesTarefas[status.index]} | ${tarefasHoje.nome} - Nenhum proprietário - ${tarefasHoje.statusProjeto} - ${dataHoje[status.index]}</p></li>        
                    	
                    	</c:if>
                    
                    </c:forEach>
                    
                    <c:forEach items="${listaDesenvolvimentosHoje}" var="desenvolvimentosHoje" varStatus="statusDesenvolvimentos">
                    
                    	<c:if test="${listaProprietariosDesenvolvimentos[statusDesenvolvimentos.index].userName != null}">
                        <li class="exibir-tarefas"><p>${listaNomeDesenvolvimentos[statusDesenvolvimentos.index]} | ${desenvolvimentosHoje.nome} - ${listaProprietariosDesenvolvimentos[statusDesenvolvimentos.index].userName} - ${desenvolvimentosHoje.statusProjeto} - ${dataDesenvolvimentosHoje[statusDesenvolvimentos.index]}</p></li>        
                    	</c:if>
                    	
                    	<c:if test="${listaProprietariosDesenvolvimentos[statusDesenvolvimentos.index].userName == null}">
                        <li class="exibir-tarefas"><p>${listaNomeDesenvolvimentos[statusDesenvolvimentos.index]} | ${desenvolvimentosHoje.nome} - Nenhum proprietário - ${desenvolvimentosHoje.statusProjeto} - ${dataDesenvolvimentosHoje[statusDesenvolvimentos.index]}</p></li>        
                    	</c:if>
                    
                    </c:forEach>
                    </ul>
                    </li>
                        
                    <li class="cronograma-item">
                     <input type="checkbox" id="checkCronograma3">
                    <label for="checkCronograma3">
                    <div class="setaBaixoCronograma"><img src="img/setaDireitaBlueIcon.png"></div>
                    <span>Mês</span>
                    </label>
                  <ul class="tarefas-mes">
                  <c:forEach items="${listaTarefasMes}" var="tarefasMes" varStatus="statusMes">
                    
                    <c:if test="${listaProprietariosMes[statusMes.index].userName != null}">  
                        <li class="exibir-tarefas-mes"><p>${listaNomesTarefasMes[statusMes.index]} | ${tarefasMes.nome} - ${listaProprietariosMes[statusMes.index].userName} - ${tarefasMes.statusProjeto} - ${dataMes[statusMes.index]}</li>        
                   </c:if>
                   
                    <c:if test="${listaProprietariosMes[statusMes.index].userName == null}">  
                        <li class="exibir-tarefas-mes"><p>${listaNomesTarefasMes[statusMes.index]} | ${tarefasMes.nome} - Nenhum proprietário - ${tarefasMes.statusProjeto} - ${dataMes[statusMes.index]}</li>        
                   </c:if>
                   
                   </c:forEach>
                   
                    <c:forEach items="${listaDesenvolvimentosMes}" var="desenvolvimentosMes" varStatus="statusDesenvolvimentosMes">
                    
                    <c:if test="${listaProprietariosDesenvolvimentosMes[statusDesenvolvimentosMes.index].userName != null}">
                    	<li class="exibir-tarefas-mes"><p>${listaNomeDesenvolvimentosMes[statusDesenvolvimentosMes.index]} | ${desenvolvimentosMes.nome} - ${listaProprietariosDesenvolvimentosMes[statusDesenvolvimentosMes.index].userName} - ${desenvolvimentosMes.statusProjeto} - ${dataDesenvolvimentosMes[statusDesenvolvimentosMes.index]}</li>  
                    </c:if>
                    
                    <c:if test="${listaProprietariosDesenvolvimentosMes[statusDesenvolvimentosMes.index].userName == null}">
                    	<li class="exibir-tarefas-mes"><p>${listaNomeDesenvolvimentosMes[statusDesenvolvimentosMes.index]} | ${desenvolvimentosMes.nome} - Nenhum proprietário - ${desenvolvimentosMes.statusProjeto} - ${dataDesenvolvimentosMes[statusDesenvolvimentosMes.index]}</li>  
                    </c:if>
                    </c:forEach>
                   
                    </ul>
                    </li>
                                                
                    <li class="cronograma-item">
                     <input type="checkbox" id="checkCronograma5">
                    <label for="checkCronograma5">
                    <div class="setaBaixoCronograma"><img src="img/setaDireitaBlueIcon.png"></div>
                    <span>Concluídos</span>
                    </label>
                      <ul class="tarefas-concluido">
                   <c:forEach items="${listaConcluidosMes}" var="concluidosMes" varStatus="statusConcluidos">
                   	<c:if test="${listaProprietariosConcluidosMes[statusConcluidos.index].userName != null}">
                        <li class="exibir-tarefas-concluido"><p>${listaNomesConcluidosMes[statusConcluidos.index]} | ${concluidosMes.nome} - ${listaProprietariosConcluidosMes[statusConcluidos.index].userName} - ${concluidosMes.statusProjeto} - ${dataConcluidosMes[statusConcluidos.index]}</p></li>        
                   </c:if>
                   
                   	<c:if test="${listaProprietariosConcluidosMes[statusConcluidos.index].userName == null}">
                        <li class="exibir-tarefas-concluido"><p>${listaNomesConcluidosMes[statusConcluidos.index]} | ${concluidosMes.nome} - Nenhum proprietário - ${concluidosMes.statusProjeto} - ${dataConcluidosMes[statusConcluidos.index]}</p></li>        
                   </c:if>
                   </c:forEach>
                    </ul>
                    </li>
                     
                    </ul>
                </nav>
            </section>
            </section>
            </main>
            
            <script src="Bootstrap/js/jquery-3.4.1.min.js" type="text/javascript"></script>
            <script src="Bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
            <script src="JS/JS_home.js" type="text/javascript"></script>
            <script charset="UTF-8" src="JS/JS_cronograma.js" type="text/javascript"></script>
          </body>
</html>