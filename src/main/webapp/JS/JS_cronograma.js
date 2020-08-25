function modoNoturnoAtivadoCronograma(ativado){
	   if(ativado){	  
		   //Geral Padrão
		   var corpo = document.querySelector(".corpo").classList.toggle("bodyNigth"); 
		   var menu = document.querySelector(".menu").classList.toggle("menuNigth"); 
		   var botao = document.querySelector(".botao").classList.toggle("botaoNigth");
		   var principal = document.querySelector(".principal").classList.toggle("principalNigth"); 
		   var opcao = document.querySelector(".opcao").classList.toggle("opcaoNigth");
		   var statusNigth = document.querySelector(".status").classList.toggle("statusNigth");
		   var caixaAddProjetoInterno = document.querySelector(".caixa-add-projeto-interno").classList.toggle("caixa-add-projeto-internoNigth");
		   var fechaCaixaProjeto = document.querySelector(".fecha-caixa-projeto").classList.toggle("fecha-caixa-projetoNigth");
		   var textCaixaAddProjeto = document.querySelectorAll(".text-caixa-add-projeto");
		   
		   for (var i = 0; i < textCaixaAddProjeto.length; i++) {
			   textCaixaAddProjeto[i].classList.toggle("text-caixa-add-projetoNigth");
			 }
		   
	   //Add Calendario
	   var calendario = document.querySelector(".calendario").classList.toggle("calendarioNigth");
	   var tabelaCalendario = document.querySelector(".tabela-calendario").classList.toggle("tabela-calendarioNigth");
	   var compromissos = document.querySelector(".compromissos").classList.toggle("compromissosNigth");
	   var btnPassMesAno = document.querySelector(".btnPassMesAno").classList.toggle("btnPassMesAnoNigth");
	   var btnPassMesAno2 = document.querySelector("#btnDireita").classList.toggle("btnPassMesAnoNigth");
	   }else{
		   //Remove Geral Padrão
		   var corpo = document.querySelector(".corpo").classList.remove("bodyNigth");
		   var menu = document.querySelector(".menu").classList.remove("menuNigth"); 
		   var principal = document.querySelector(".principal").classList.remove("principalNigth"); 
		   var botao = document.querySelector(".botao").classList.remove("botaoNigth");
		   var opcao = document.querySelector(".opcao").classList.remove("opcaoNigth");
		   var statusNigth = document.querySelector(".status").classList.remove("statusNigth");
		   var caixaAddProjetoInterno = document.querySelector(".caixa-add-projeto-interno").classList.remove("caixa-add-projeto-internoNigth");
		   var fechaCaixaProjeto = document.querySelector(".fecha-caixa-projeto").classList.remove("fecha-caixa-projetoNigth");
		   var textCaixaAddProjeto = document.querySelectorAll(".text-caixa-add-projeto");
		   
		   for (var i = 0; i < textCaixaAddProjeto.length; i++) {
			   textCaixaAddProjeto[i].classList.remove("text-caixa-add-projetoNigth");
			 }	   
		   
	   //Remove Calendario	   
	   var calendario = document.querySelector(".calendario").classList.remove("calendarioNigth");
	   var tabelaCalendario = document.querySelector(".tabela-calendario").classList.remove("tabela-calendarioNigth");
	   var compromissos = document.querySelector(".compromissos").classList.remove("compromissosNigth");
	   var btnPassMesAno = document.querySelector(".btnPassMesAno").classList.remove("btnPassMesAnoNigth");
	   var btnPassMesAno2 = document.querySelector("#btnDireita").classList.remove("btnPassMesAnoNigth");
	   }
}    

(function(){
	'use strict'; 
	
	$.ajax({
       url: '/projectstages_mvc/retorna/modo-noturno/cronograma',
       success : function(data){
       	modoNoturnoAtivadoCronograma(data);
       }
   });
})();

	var hoje = new Date();
    var mesAtual = hoje.getMonth();
    var anoAtual = hoje.getFullYear();
 
    var meses = [
        "Janeiro",
        "Fevereiro",
        "Março",
        "Abril",
        "Maio",
        "Junho",
        "Julho",
        "Agosto",
        "Setembro",
        "Outubro",
        "Novembro",
        "Dezembro"
    ];

    var $mesAno = document.querySelector("#mesAno");

    function mostrarCalendario(mes,ano){
        var primeiroDia = new Date(ano,mes).getDate();
        var primeiroDiadaSemana = new Date(ano,mes).getDay();
        var primeiroDiadaSemanadoProximoMes = new Date(ano,mes+1).getDay();
        var diasNoAno = 32 - new Date(ano,mes,32).getDate();
       

        var $corpoCalendario =document.querySelector("#corpo-calendario");

        $corpoCalendario.innerHTML = "";

        $mesAno.innerHTML = meses[mes] + " " + ano;

        var data = 1;

        for(var i = 0; i < 6 ; i++){
            var linha = document.createElement("tr");
            for(var j = 0; j < 7; j++){
                if(i === 0 && j < primeiroDiadaSemana){
                    var diasNoAnoDoMesPassado = 32 - new Date(ano,mes-1,32).getDate();
                    var celula = document.createElement("td");
                    var celulaTexto = document.createTextNode(diasNoAnoDoMesPassado - (primeiroDiadaSemana - (j + 1)));
                    celula.style.color ="gray";
                    celula.appendChild(celulaTexto);
                    linha.appendChild(celula);
                   }else
                if(data > diasNoAno){
                        break;
                 }else{
                     if(hoje.getDate() === data && mes === hoje.getMonth() && ano === hoje.getFullYear()){
                        var celula = document.createElement("td");
                        var celulaTexto = document.createTextNode(data);
                        celula.style.background ="white";
                        celula.style.color = "rgb(59, 168, 248)";
                        celula.appendChild(celulaTexto);
                        linha.appendChild(celula);
                         data++;
                     }else{
                        var celula = document.createElement("td");
                        var celulaTexto = document.createTextNode(data);
                        celula.appendChild(celulaTexto);
                        linha.appendChild(celula);
                        data++;
                     }
                 }      
              if(i>=4 && data > diasNoAno){
                  if(primeiroDiadaSemanadoProximoMes == 0){
                      break;
                  }else{
                    var diaDoproxMes = 1;
                    while(diaDoproxMes < (8 - primeiroDiadaSemanadoProximoMes)){
                        var celula = document.createElement("td");
                        var celulaTexto = document.createTextNode(diaDoproxMes);
                        celula.style.color ="gray";
                        celula.appendChild(celulaTexto);
                        linha.appendChild(celula);
                        diaDoproxMes++;
                    }
                  }
              }
            }
            $corpoCalendario.appendChild(linha);

        }
    }

    mostrarCalendario(mesAtual,anoAtual);
var $btnEsquerda = document.getElementById("btnEsquerda");
var $btnDireita = document.getElementById("btnDireita");


$btnEsquerda.addEventListener('click', function(){
    anoAtual = (mesAtual === 0) ? anoAtual - 1 : anoAtual;
    mesAtual =(mesAtual === 0)? 11 : mesAtual - 1;
    mostrarCalendario(mesAtual,anoAtual);
});

$btnDireita.addEventListener('click', function(){
    anoAtual = (mesAtual === 11) ? anoAtual + 1 : anoAtual;
    mesAtual =(mesAtual + 1) % 12;
    mostrarCalendario(mesAtual,anoAtual);
});





