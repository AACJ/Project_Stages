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
                    </li>
                        
                    <li class="cronograma-item">
                    <input type="checkbox" id="checkCronograma2">
                    <label for="checkCronograma2">
                    <div class="setaBaixoCronograma"><img src="img/setaDireitaBlueIcon.png"></div>
                    <span>Hoje</span>
                    </label>
                    </li>
                        
                    <li class="cronograma-item">
                     <input type="checkbox" id="checkCronograma3">
                    <label for="checkCronograma3">
                    <div class="setaBaixoCronograma"><img src="img/setaDireitaBlueIcon.png"></div>
                    <span>A seguir</span>
                    </label>
                    </li>
                        
                    <li class="cronograma-item">
                    <input type="checkbox" id="checkCronograma4">
                    <label for="checkCronograma4">
                    <div class="setaBaixoCronograma"><img src="img/setaDireitaBlueIcon.png"></div>
                    <span>Sem data</span>
                    </label>
                    </li>
                        
                    <li class="cronograma-item">
                     <input type="checkbox" id="checkCronograma5">
                    <label for="checkCronograma5">
                    <div class="setaBaixoCronograma"><img src="img/setaDireitaBlueIcon.png"></div>
                    <span>Concluídos</span>
                    </label>
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