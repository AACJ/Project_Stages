var $btnTarefas = document.getElementById("btnTarefas");
var $caixaAddTarefas = document.getElementById("caixa-add-tarefas");
var $fechaCaixa01 = document.getElementById("fecha-caixa-add-tarefas01");
var $bodyOpenProjetoStatuts = document.querySelector(".corpo");
var $opencaixaprojetostatus = document.querySelectorAll("#open-caixa-projeto-status");
var $btnAddParticipante = document.getElementById("btn-add-participante");
var $closeBoxParticipante = document.getElementById("close-box-participante");
var $boxExternoParticipante = document.getElementById("box-externo-participante");
var $boxInternoParticipante = document.getElementById("box-interno-participante");
var $pesquisaAmigoParticipante = document.getElementById("pesquisa-amigo-participante");
var $addParticipante = document.querySelectorAll("#add-participante");
var $btnShowParticipantes = document.getElementById("btn-show-participantes");
var $boxExternoGerenciamento = document.getElementById("box-externo-gerenciamento");
var $boxInternoGerenciamento = document.getElementById("box-interno-gerenciamento");
var $closeBoxGerenciamento = document.getElementById("close-box-gerenciamento");
var $pesquisaAmigoGerenciamento = document.getElementById("pesquisa-amigo-gerenciamento");
var $removeParticipante = document.querySelectorAll("#remove-participante");
var $btnOpProprietario = document.querySelectorAll("#btn-op-proprietario");
var $tornaAdm = document.querySelectorAll("#torna-adm");
var $retirarAdm = document.querySelectorAll("#retirar-adm");
var $tarefasTexto = document.querySelectorAll(".projeto-tarefa-text");  
var $dataTexto = document.querySelectorAll(".projeto-data-text");  
var $botaoDeleteTarefas= document.querySelectorAll(".botao-projeto-delete"); 
var $editProjectInfo = document.getElementById("edit-project-info");
var $saveProjectInfo = document.getElementById("save-project-info");
var $nameProjectInfo = document.getElementById("nameProjectInfo");
var $descricaoProjeto = document.getElementById("descricaoProjeto");
var $btnExitProject = document.getElementById("btn-exit-project");
var $caixaSairProjetoExterno = document.getElementById("caixa-sair-projeto-externo");
var $caixaSairProjetoInterno = document.getElementById("caixa-sair-projeto-interno");
var $fechaCaixaSairProjeto = document.getElementById("fecha-caixa-sair-projeto");
var $yesExit = document.getElementById("yes-exit");
var $cancelExil = document.getElementById("cancel-exil");



$editProjectInfo.addEventListener('click', function(){
   $nameProjectInfo.readOnly = false;
   $descricaoProjeto.readOnly = false;
   $nameProjectInfo.style.border = "1px solid rgb(59, 168, 248)";
   $descricaoProjeto.style.border = "1px solid rgb(59, 168, 248)";
   $saveProjectInfo.style.display = "block";
   $editProjectInfo.style.display = "none";
});

$saveProjectInfo.addEventListener('click', function(){ 
	$.ajax({
	       url: '/projectstages_mvc/update/info-projeto',
	       data:{nome : $nameProjectInfo.value, descricao : $descricaoProjeto.value},
	       success : function(result){
	    	  
	       }
	   });
   $nameProjectInfo.readOnly = true;
   $descricaoProjeto.readOnly = true;
   $nameProjectInfo.style.border = "none";
   $descricaoProjeto.style.border = "none";
   $editProjectInfo.style.display = "block";
   $saveProjectInfo.style.display = "none";
});

$btnAddParticipante.addEventListener('click', function(){
	$boxExternoParticipante.style.display = "flex";	 
	$boxInternoParticipante.style.display = "block";	   
});

$btnShowParticipantes.addEventListener('click', function(){
	$boxExternoGerenciamento.style.display = "flex";	 
	$boxInternoGerenciamento.style.display = "block";	   
});

$btnTarefas.addEventListener('click', function(){
   $caixaAddTarefas.style.display = "flex";
});

$fechaCaixa01.addEventListener('click', function(){
   $caixaAddTarefas.style.display = "none";
});

$btnExitProject.addEventListener('click', function(){
	$caixaSairProjetoExterno.style.display = "flex";
	$caixaSairProjetoInterno.style.display = "block";
});

$fechaCaixaSairProjeto.addEventListener('click', function(){
	$caixaSairProjetoExterno.style.display = "none";
	$caixaSairProjetoInterno.style.display = "none";
});

$cancelExil.addEventListener('click', function(){
	$caixaSairProjetoExterno.style.display = "none";
	$caixaSairProjetoInterno.style.display = "none";
});
	
//Pesquisa amigos para serem participantes
$pesquisaAmigoParticipante.addEventListener("keydown", function(e){
	
	if($pesquisaAmigoParticipante.value == ""){
		ExibirTodosOsParticipantes();
	}
	
	if(e.keyCode > 46 && e.keyCode < 91){
	$.ajax({
	       url: '/projectstages_mvc/retorna/pesquisa-amigos-projeto',
	       data:{nome : $pesquisaAmigoParticipante.value},
	       success : function(response){
	    	   $.ajax({
	    	       url: '/projectstages_mvc/clear/pesquisa-amigos-projeto',
	    	       success : function(result){
	    	      
	    	    	   for(var i = 0; i < result.length;i++ ){
	   	    	    	
		    	    	   var $participantePerfil = document.getElementById("participante-perfil-" + result[i]);
		    	    	   
		    	    	   $participantePerfil.style.display = "none";
	    	    	   }
		    	    	   
	    	    	   for(var i = 0; i < response.length;i++ ){
		  
	    	    		   var $participantePerfil = document.getElementById("participante-perfil-" + response[i]);
		    		    	   
		    		    	$participantePerfil.style.display = "flex";
		    		   }
	    	    	   
	    	       }
	    	   });
	    	  
	       }
	   });
	}
});

$pesquisaAmigoParticipante.addEventListener("keyup", function(e){
	
	if($pesquisaAmigoParticipante.value == ""){
		ExibirTodosOsParticipantes();
	}
	if(e.keyCode > 46 && e.keyCode < 91){
	$.ajax({
	       url: '/projectstages_mvc/retorna/pesquisa-amigos-projeto',
	       data:{nome : $pesquisaAmigoParticipante.value},
	       success : function(response){
	    	   $.ajax({
	    	       url: '/projectstages_mvc/clear/pesquisa-amigos-projeto',
	    	       success : function(result){
	    	    	   
	    	    	   for(var i = 0; i < result.length;i++ ){
		   	    	    	
		    	    	   var $participantePerfil = document.getElementById("participante-perfil-" + result[i]);
		    	    	   
		    	    	   $participantePerfil.style.display = "none";
	    	    	   }
		    	    	   
	    	    	   for(var i = 0; i < response.length;i++ ){
	
	    	    		   var $participantePerfil = document.getElementById("participante-perfil-" + response[i]);
		    		    	   
		    		    	$participantePerfil.style.display = "flex";
		    		   }
	    	       }
	    	   });
	    	  
	       }
	   });
	}
});

//Pesquisa Participantes
$pesquisaAmigoGerenciamento.addEventListener("keydown", function(e){
	
	if($pesquisaAmigoGerenciamento.value == ""){
		ExibirTodosOsParticipantesInseridosNoProjeto();
	}
	
	if(e.keyCode > 46 && e.keyCode < 91){
	$.ajax({
	       url: '/projectstages_mvc/retorna/pesquisa-participantes-projeto',
	       data:{nome : $pesquisaAmigoGerenciamento.value},
	       success : function(response){
	    	   $.ajax({
	    	       url: '/projectstages_mvc/retorna/ids-participantes-projeto',
	    	       success : function(result){
	    	      
	    	    	   for(var i = 0; i < result.length;i++ ){
	   	    	    	
		    	    	   var $participantePerfil = document.getElementById("participante-projeto-perfil-" + result[i]);
		    	    	   
		    	    	   $participantePerfil.style.display = "none";
	    	    	   }
		    	    	   
	    	    	   for(var i = 0; i < response.length;i++ ){
		  
	    	    		   var $participantePerfil = document.getElementById("participante-projeto-perfil-" + response[i]);
		    		    	   
		    		    	$participantePerfil.style.display = "flex";
		    		   }
	    	    	   
	    	       }
	    	   });
	    	  
	       }
	   });
	}
});

$pesquisaAmigoGerenciamento.addEventListener("keyup", function(e){
	
	if($pesquisaAmigoGerenciamento.value == ""){
		ExibirTodosOsParticipantesInseridosNoProjeto();
	}
	if(e.keyCode > 46 && e.keyCode < 91){
	$.ajax({
	       url: '/projectstages_mvc/retorna/pesquisa-participantes-projeto',
	       data:{nome : $pesquisaAmigoGerenciamento.value},
	       success : function(response){
	    	   $.ajax({
	    	       url: '/projectstages_mvc/retorna/ids-participantes-projeto',
	    	       success : function(result){
	    	    	   
	    	    	   for(var i = 0; i < result.length;i++ ){
		   	    	    	
		    	    	   var $participantePerfil = document.getElementById("participante-projeto-perfil-" + result[i]);
		    	    	   
		    	    	   $participantePerfil.style.display = "none";
	    	    	   }
		    	    	   
	    	    	   for(var i = 0; i < response.length;i++ ){
	
	    	    		   var $participantePerfil = document.getElementById("participante-projeto-perfil-" + response[i]);
		    		    	   
		    		    	$participantePerfil.style.display = "flex";
		    		   }
	    	       }
	    	   });
	    	  
	       }
	   });
	}
});

function ExibirTodosOsParticipantes(){
	$.ajax({
	       url: '/projectstages_mvc/clear/pesquisa-amigos-projeto',
	       success : function(result){
	    	   
	    	   for(var i = 0; i < result.length;i++ ){
	    	    	
 	    	   var $participantePerfil = document.getElementById("participante-perfil-" + result[i]);
 	    	   
 	    	   $participantePerfil.style.display = "flex";
	    	   }
	       }
	   });
}

function ExibirTodosOsParticipantesInseridosNoProjeto(){
	$.ajax({
	       url: '/projectstages_mvc/retorna/ids-participantes-projeto',
	       success : function(result){
	    	   
	    	   for(var i = 0; i < result.length;i++ ){
	    		  
 	    	   var $participanteProjetoPerfil = document.getElementById("participante-projeto-perfil-" + result[i]);
 	    	   
 	    	  $participanteProjetoPerfil.style.display = "flex";
	    	   }
	       }
	   });
}

function ExibirTodosOsProprietarios(idTarefa){
	$.ajax({
	       url: '/projectstages_mvc/retorna/ids-proprietarios',
	       success : function(result){
	    	   for(var i = 0; i < result.length;i++ ){
	    		   
	    		   var $perfilProprietario = document.getElementById("perfil-"+ idTarefa +"-proprietario-" + result[i]);
    		    	   
	    		   $perfilProprietario.style.display = "flex";
	    		   
	    	   }
	       }
	       
	   });
}

function ExibirProjetoStatusTarefas(){ 
		$.ajax({
		       url: '/projectstages_mvc/retorna/projeto-status-tarefa',
		       success : function(response){
		    	   for(var i = 0; i < response.length ;i++){
		    	  	var opProjetoStatus01 = document.getElementById("op-projeto-status01" + i);
		     		var opProjetoStatus02 = document.getElementById("op-projeto-status02" + i);
		     		var opProjetoStatus03 = document.getElementById("op-projeto-status03" + i);
		     		var opProjetoStatus04 = document.getElementById("op-projeto-status04" + i);
		     		var opProjetoStatus05 = document.getElementById("op-projeto-status05" + i);

		     		if(response[i] == "Finalizado"){
		       			opProjetoStatus01.style.background = "rgb(10, 157, 4)";
		       			opProjetoStatus01.style.display = "flex";
		       			opProjetoStatus02.style.display = "none";
		       			opProjetoStatus03.style.display = "none";
		       			opProjetoStatus04.style.display = "none";
		       			opProjetoStatus05.style.display = "none";
		       			
		       		}else
		       		if(response[i] == "Parado"){
		       			opProjetoStatus02.style.background = "rgb(216, 5, 5)";
		       			opProjetoStatus02.style.display = "flex";
		       			opProjetoStatus01.style.display = "none";
		       			opProjetoStatus03.style.display = "none";
		       			opProjetoStatus04.style.display = "none";
		       			opProjetoStatus05.style.display = "none";
		       			
		       		}else
			       	if(response[i] == "Em Analise"){
			       		opProjetoStatus03.style.background = "rgb(0, 55, 178)";
			       		opProjetoStatus03.style.display = "flex";
			       		opProjetoStatus02.style.display = "none";
		       			opProjetoStatus01.style.display = "none";
		       			opProjetoStatus04.style.display = "none";
		       			opProjetoStatus05.style.display = "none";
		       			
			       	}else
				     if(response[i] == "Em Andamento"){
				       	opProjetoStatus04.style.background = "rgb(234, 216, 0)";
				       	opProjetoStatus04.style.display = "flex";
				       	opProjetoStatus02.style.display = "none";
		       			opProjetoStatus03.style.display = "none";
		       			opProjetoStatus01.style.display = "none";
		       			opProjetoStatus05.style.display = "none";
		       			
				     }else
					  if(response[i] == "Nao Iniciado"){
					     opProjetoStatus05.style.background ="rgb(59, 168, 248)";
					     opProjetoStatus05.style.display = "flex";
					     opProjetoStatus02.style.display = "none";
			       		 opProjetoStatus03.style.display = "none";
			       		 opProjetoStatus04.style.display = "none";
			       		 opProjetoStatus01.style.display = "none";
					  }
		       		
		       }
		       }
		   });
	
	}

function ExibirProjetoStatusDesenvolvimentos(){ 
	$.ajax({
	       url: '/projectstages_mvc/retorna/projeto-status-desenvolvimentos',
	       success : function(response){
	    	   for(var i = 0; i < response.length ;i++){
	    	  	var opProjetoStatus01 = document.getElementById("op-desenvolvimento-status01" + i);
	     		var opProjetoStatus02 = document.getElementById("op-desenvolvimento-status02" + i);
	     		var opProjetoStatus03 = document.getElementById("op-desenvolvimento-status03" + i);
	     		var opProjetoStatus04 = document.getElementById("op-desenvolvimento-status04" + i);
	     		var opProjetoStatus05 = document.getElementById("op-desenvolvimento-status05" + i);

	     		if(response[i] == "Finalizado"){
	       			opProjetoStatus01.style.background = "rgb(10, 157, 4)";
	       			opProjetoStatus01.style.display = "flex";
	       			opProjetoStatus02.style.display = "none";
	       			opProjetoStatus03.style.display = "none";
	       			opProjetoStatus04.style.display = "none";
	       			opProjetoStatus05.style.display = "none";
	       			
	       		}else
	       		if(response[i] == "Parado"){
	       			opProjetoStatus02.style.background = "rgb(216, 5, 5)";
	       			opProjetoStatus02.style.display = "flex";
	       			opProjetoStatus01.style.display = "none";
	       			opProjetoStatus03.style.display = "none";
	       			opProjetoStatus04.style.display = "none";
	       			opProjetoStatus05.style.display = "none";
	       			
	       		}else
		       	if(response[i] == "Em Analise"){
		       		opProjetoStatus03.style.background = "rgb(0, 55, 178)";
		       		opProjetoStatus03.style.display = "flex";
		       		opProjetoStatus02.style.display = "none";
	       			opProjetoStatus01.style.display = "none";
	       			opProjetoStatus04.style.display = "none";
	       			opProjetoStatus05.style.display = "none";
	       			
		       	}else
			     if(response[i] == "Em Andamento"){
			       	opProjetoStatus04.style.background = "rgb(234, 216, 0)";
			       	opProjetoStatus04.style.display = "flex";
			       	opProjetoStatus02.style.display = "none";
	       			opProjetoStatus03.style.display = "none";
	       			opProjetoStatus01.style.display = "none";
	       			opProjetoStatus05.style.display = "none";
	       			
			     }else
				  if(response[i] == "Nao Iniciado"){
				     opProjetoStatus05.style.background ="rgb(59, 168, 248)";
				     opProjetoStatus05.style.display = "flex";
				     opProjetoStatus02.style.display = "none";
		       		 opProjetoStatus03.style.display = "none";
		       		 opProjetoStatus04.style.display = "none";
		       		 opProjetoStatus01.style.display = "none";
				  }
	       		
	       }
	       }
	   });

}

function ExibirProjetoStatusConcluidos(){ 
	$.ajax({
	       url: '/projectstages_mvc/retorna/projeto-status-concluidos',
	       success : function(response){
	    	   for(var i = 0; i < response.length ;i++){
	    	  	var opProjetoStatus01 = document.getElementById("op-concluido-status01" + i);
	     		var opProjetoStatus02 = document.getElementById("op-concluido-status02" + i);
	     		var opProjetoStatus03 = document.getElementById("op-concluido-status03" + i);
	     		var opProjetoStatus04 = document.getElementById("op-concluido-status04" + i);
	     		var opProjetoStatus05 = document.getElementById("op-concluido-status05" + i);

	     		if(response[i] == "Finalizado"){
	       			opProjetoStatus01.style.background = "rgb(10, 157, 4)";
	       			opProjetoStatus01.style.display = "flex";
	       			opProjetoStatus02.style.display = "none";
	       			opProjetoStatus03.style.display = "none";
	       			opProjetoStatus04.style.display = "none";
	       			opProjetoStatus05.style.display = "none";
	       			
	       		}else
	       		if(response[i] == "Parado"){
	       			opProjetoStatus02.style.background = "rgb(216, 5, 5)";
	       			opProjetoStatus02.style.display = "flex";
	       			opProjetoStatus01.style.display = "none";
	       			opProjetoStatus03.style.display = "none";
	       			opProjetoStatus04.style.display = "none";
	       			opProjetoStatus05.style.display = "none";
	       			
	       		}else
		       	if(response[i] == "Em Analise"){
		       		opProjetoStatus03.style.background = "rgb(0, 55, 178)";
		       		opProjetoStatus03.style.display = "flex";
		       		opProjetoStatus02.style.display = "none";
	       			opProjetoStatus01.style.display = "none";
	       			opProjetoStatus04.style.display = "none";
	       			opProjetoStatus05.style.display = "none";
	       			
		       	}else
			     if(response[i] == "Em Andamento"){
			       	opProjetoStatus04.style.background = "rgb(234, 216, 0)";
			       	opProjetoStatus04.style.display = "flex";
			       	opProjetoStatus02.style.display = "none";
	       			opProjetoStatus03.style.display = "none";
	       			opProjetoStatus01.style.display = "none";
	       			opProjetoStatus05.style.display = "none";
	       			
			     }else
				  if(response[i] == "Nao Iniciado"){
				     opProjetoStatus05.style.background ="rgb(59, 168, 248)";
				     opProjetoStatus05.style.display = "flex";
				     opProjetoStatus02.style.display = "none";
		       		 opProjetoStatus03.style.display = "none";
		       		 opProjetoStatus04.style.display = "none";
		       		 opProjetoStatus01.style.display = "none";
				  }
	       		
	       }
	       }
	   });

}

function verificacaoParticipantes(){

		$.ajax({
			       url: '/projectstages_mvc/retorna/participantes',
			       success : function(response){
			    	   for(var j = 0; j < response.length; j++){
			    		   for(var i = 0; i < $addParticipante.length ;i++){
			    		   if($addParticipante[i].value == response[j]){
			    			   $addParticipante[i].style.background = "rgb(178, 172, 172)";
			    			   $addParticipante[i].disable = true;
			    		   }
			    	   }
			    	   }
			       }
			   });

}

function verificacaoADMs(){
	//Verificacao membro
	$.ajax({
		       url: '/projectstages_mvc/verificacao/adm',
		       data: {funcao : "adm"},
		       success : function(response){
		    	   for(var j = 0; j < response.length; j++){
		    		   for(var i = 0; i < $tornaAdm.length ;i++){
		    		   if($tornaAdm[i].value == response[j]){
		    			   $tornaAdm[i].style.display = "none"; 
		    				$retirarAdm[i].style.display = "block"; 
		    		   }
		    	   }
		    	   }
		       }
		   });
	
	//Verificacao membro
	$.ajax({
		       url: '/projectstages_mvc/verificacao/adm',
		       data: {funcao : "membro"},
		       success : function(response){
		    	   for(var j = 0; j < response.length; j++){
		    		   for(var i = 0; i < $tornaAdm.length ;i++){
		    		   if($tornaAdm[i].value == response[j]){
		    			   $tornaAdm[i].style.display = "block"; 
		    				$retirarAdm[i].style.display = "none"; 
		    		   }
		    	   }
		    	   }
		       }
		   });
	
	
}

function verificarPermissoesDeFuncao(){
	$.ajax({
	       url: '/projectstages_mvc/retorna/minha-funcao',
	       success : function(response){
	    	   if(response == "membro"){
	    		   $btnAddParticipante.style.display = "none";
	    		   $btnTarefas.style.display = "none";
	    		   for(var i = 0; i < $tarefasTexto.length; i++){
	    			   $tarefasTexto[i].readOnly = true;
	    			   $btnOpProprietario[i].disabled = true;
	    			   $dataTexto[i].readOnly = true;
	    			   $opencaixaprojetostatus[i].disabled = true;
	    			   $botaoDeleteTarefas[i].classList.toggle("not-active");
	    		   }
	    		      
	    	   }else{
	    		   $btnAddParticipante.style.display = "block";
	    	   }
	       }
	   });
}

//Salvar status tarefa projeto
$bodyOpenProjetoStatuts.addEventListener('click', function(e){
	$.ajax({
	       url: '/projectstages_mvc/retorna/minha-funcao',
	       success : function(resposta){	
	for(var i = 0; i < $opencaixaprojetostatus.length ;i++){
			if($opencaixaprojetostatus[i].contains(e.target) && resposta != "membro"){
				var $caixaStatusProjeto =  document.getElementById("caixa-op-projeto-status" + $opencaixaprojetostatus[i].value); 
				var $fechaCaixaStatusProjeto =document.getElementById("close-box-project-status" + $opencaixaprojetostatus[i].value);
				$caixaStatusProjeto.style.display = "block";

				$fechaCaixaStatusProjeto.addEventListener('click', function(){
					$caixaStatusProjeto.style.display = "none";
				});
			}
		}
	       }
	});
});

//Salvar Proprietario tarefa projeto
$bodyOpenProjetoStatuts.addEventListener('click', function(e){
	$.ajax({
	       url: '/projectstages_mvc/retorna/minha-funcao',
	       success : function(resposta){
	
		for(var i = 0; i < $btnOpProprietario.length ;i++){
			if($btnOpProprietario[i].contains(e.target) && resposta != "membro"){
				var id = $btnOpProprietario[i].value;
				var $boxProprietarios =  document.getElementById("box-proprietarios-" + $btnOpProprietario[i].value); 
				var $fechaBoxProprietarios = document.getElementById("fecha-box-proprietarios-" + $btnOpProprietario[i].value);
				var $barraPesquisaProprietarios = document.getElementById("barra-search-proprietarios-" + $btnOpProprietario[i].value);
				$boxProprietarios.style.display = "block";

				$fechaBoxProprietarios.addEventListener('click', function(){
					$boxProprietarios.style.display = "none";
				});
				

				$barraPesquisaProprietarios.addEventListener("keydown", function(e){
					
					if($barraPesquisaProprietarios.value == ""){
						$.ajax({
						       url: '/projectstages_mvc/retorna/id-all-atividades',
						       success : function(response){
						    	   for(var j = 0; j < response.length;j++ ){
						    		   ExibirTodosOsProprietarios(response[j]);
						    	   }
						       }
						   });
					}
					
					if(e.keyCode > 46 && e.keyCode < 91){
					$.ajax({
					       url: '/projectstages_mvc/retorna/pesquisa-participantes-projeto',
					       data:{nome :$barraPesquisaProprietarios.value},
					       success : function(response){
					    	   $.ajax({
					    	       url: '/projectstages_mvc/retorna/ids-proprietarios',
					    	       success : function(result){
					    	      
					    	    	   for(var j = 0; j < result.length;j++ ){
					   	    	    	   
						    	    	   var $perfilProprietario = document.getElementById("perfil-"+ id +"-proprietario-" + result[j]);
						    	    	   
						    	    	   $perfilProprietario.style.display = "none";
					    	    	   }
						    	    	   
					    	    	   for(var j = 0; j < response.length;j++ ){
					    	    		  
					    	    		   var $perfilProprietario =document.getElementById("perfil-"+ id +"-proprietario-" + response[j]);
						    		    	   
					    	    		   $perfilProprietario.style.display = "flex";
						    		   }
					    	    	   
					    	       }
					    	   });
					    	  
					       }
					   });
					}
				});

				$barraPesquisaProprietarios.addEventListener("keyup", function(e){
					if($barraPesquisaProprietarios.value == ""){
						$.ajax({
						       url: '/projectstages_mvc/retorna/id-all-atividades',
						       success : function(response){
						    	   for(var j = 0; j < response.length;j++ ){
						    		   ExibirTodosOsProprietarios(response[j]);
						    	   }
						       }
						   });
					}
					
					if(e.keyCode > 46 && e.keyCode < 91){
					$.ajax({
					       url: '/projectstages_mvc/retorna/pesquisa-participantes-projeto',
					       data:{nome : $barraPesquisaProprietarios.value},
					       success : function(response){
					    	   $.ajax({
					    	       url: '/projectstages_mvc/retorna/ids-proprietarios',
					    	       success : function(result){
					    	    	   
					    	    	   for(var j = 0; j < result.length;j++ ){
						   	    	    	
						    	    	   var $perfilProprietario = document.getElementById("perfil-"+ id +"-proprietario-" + result[j]);
						    	    	   
						    	    	   $perfilProprietario.style.display = "none";
					    	    	   }
						    	    	   
					    	    	   for(var j = 0; j < response.length;j++ ){
					    	    		   
					    	    		   var $perfilProprietario = document.getElementById("perfil-"+ id +"-proprietario-" + response[j]);
						    		    	   
					    	    		   $perfilProprietario.style.display = "flex";
						    		   }
					    	       }
					    	   });
					    	  
					       }
					   });
					}
				});	
		
			}
		}
		
	      }
	});
});

//Add participante
$bodyOpenProjetoStatuts.addEventListener('click', function(e){
	for(var i = 0; i < $addParticipante.length ;i++){
		if($addParticipante[i].contains(e.target)){
			$.ajax({
			       url: '/projectstages_mvc/salvar/participante',
			       data:{email : $addParticipante[i].value},
			       success : function(response){
			    	   
			       }
			   });
			
			  $addParticipante[i].style.background = "rgb(178, 172, 172)";
			  $addParticipante[i].disable = true;
		}
	}
});

//Remove participante
$bodyOpenProjetoStatuts.addEventListener('click', function(e){
	for(var i = 0; i < $removeParticipante.length ;i++){
		if($removeParticipante[i].contains(e.target)){
			$.ajax({
			       url: '/projectstages_mvc/remove/participante',
			       data:{email : $removeParticipante[i].value},
			       success : function(response){
			    	   
			       }
			   });
			
			$removeParticipante[i].style.background = "rgb(178, 172, 172)";
			$removeParticipante[i].disable = true;
		}
	}
});

//Tornar Adm
$bodyOpenProjetoStatuts.addEventListener('click', function(e){
	for(var i = 0; i < $tornaAdm.length ;i++){
		if($tornaAdm[i].contains(e.target)){
			$.ajax({
			       url: '/projectstages_mvc/tornar/adm',
			       data:{email :  $tornaAdm[i].value},
			       success : function(response){
			    	   
			       }
			   });
			
			$tornaAdm[i].style.display = "none"; 
			$retirarAdm[i].style.display = "block"; 
		}
	}
});

//Retirar Adm
$bodyOpenProjetoStatuts.addEventListener('click', function(e){
	for(var i = 0; i <  $retirarAdm.length ;i++){
		if( $retirarAdm[i].contains(e.target)){
			$.ajax({
			       url: '/projectstages_mvc/retirar/adm',
			       data:{email :  $retirarAdm[i].value},
			       success : function(response){
			    	   
			       }
			   });
			$tornaAdm[i].style.display = "block"; 
			$retirarAdm[i].style.display = "none"; 
		}
	}
});

//Atualiza o nome da tarefa.
$bodyOpenProjetoStatuts.addEventListener('click', function(e){
	for(var i = 0; i <  $tarefasTexto.length ;i++){
		if($tarefasTexto[i].contains(e.target)){
			var id = $btnOpProprietario[i].value;
			var p = i;
		$tarefasTexto[p].addEventListener("keydown", function(e){
			$.ajax({
	            url: '/projectstages_mvc/atualizar/tarefas-texto',
				data:{idTarefa : id ,texto :  $tarefasTexto[p].value},
				success : function(response){
				    	   
				       }
				   });
				
				});	
			
			
		$tarefasTexto[p].addEventListener("keyup", function(e){
			$.ajax({
			       url: '/projectstages_mvc/atualizar/tarefas-texto',
			       data:{idTarefa : id ,texto :  $tarefasTexto[p].value},
			       success : function(response){
			    	   
			       }
			   });
			
			});
		}
		}
});

//Atualiza o nome da desenvolvimentos.
$bodyOpenProjetoStatuts.addEventListener('click', function(e){
	for(var i = 0; i <  $tarefasTexto.length ;i++){
		if($tarefasTexto[i].contains(e.target)){
			var id = $btnOpProprietario[i].value;
			var p = i;
		$tarefasTexto[p].addEventListener("keydown", function(e){
			$.ajax({
	            url: '/projectstages_mvc/atualizar/desenvolvimentos-texto',
				data:{idTarefa : id ,texto :  $tarefasTexto[p].value},
				success : function(response){
				    	   
				       }
				   });
				
				});	
			
			
		$tarefasTexto[p].addEventListener("keyup", function(e){
			$.ajax({
			       url: '/projectstages_mvc/atualizar/desenvolvimentos-texto',
			       data:{idTarefa : id ,texto :  $tarefasTexto[p].value},
			       success : function(response){
			    	   
			       }
			   });
			
			});
		}
		}
});

//Atualiza o nome da concluidos.
$bodyOpenProjetoStatuts.addEventListener('click', function(e){
	for(var i = 0; i <  $tarefasTexto.length ;i++){
		if($tarefasTexto[i].contains(e.target)){
			var id = $btnOpProprietario[i].value;
			var p = i;
		$tarefasTexto[p].addEventListener("keydown", function(e){
			$.ajax({
	            url: '/projectstages_mvc/atualizar/concluidos-texto',
				data:{idTarefa : id ,texto :  $tarefasTexto[p].value},
				success : function(response){
				    	   
				       }
				   });
				
				});	
			
			
		$tarefasTexto[p].addEventListener("keyup", function(e){
			$.ajax({
			       url: '/projectstages_mvc/atualizar/concluidos-texto',
			       data:{idTarefa : id ,texto :  $tarefasTexto[p].value},
			       success : function(response){
			    	   
			       }
			   });
			
			});
		}
		}
});

//Atualiza a data da tarefa.
$bodyOpenProjetoStatuts.addEventListener('click', function(e){
	for(var i = 0; i <  $dataTexto.length ;i++){
		if($dataTexto[i].contains(e.target)){
			var id = $btnOpProprietario[i].value;
			var p = i;
			
			$dataTexto[p].addEventListener("change", function(e){
				$.ajax({
		            url: '/projectstages_mvc/atualizar/tarefas-data',
					data:{idTarefa : id ,texto :  $dataTexto[p].value},
					success : function(response){
					    	   
					       }
					   });
					
					
			});	
			
			
			$dataTexto[p].addEventListener("keydown", function(e){
			$.ajax({
	            url: '/projectstages_mvc/atualizar/tarefas-data',
				data:{idTarefa : id ,texto :  $dataTexto[p].value},
				success : function(response){
				    	   
				       }
				   });
				
				});	
			
			
			$dataTexto[p].addEventListener("keyup", function(e){
			$.ajax({
			       url: '/projectstages_mvc/atualizar/tarefas-data',
			       data:{idTarefa : id ,texto :  $dataTexto[p].value},
			       success : function(response){
			    	   
			       }
			   });
			
			});
		}
		}
});

//Atualiza a data da desenvolvimento.
$bodyOpenProjetoStatuts.addEventListener('click', function(e){
	for(var i = 0; i <  $dataTexto.length ;i++){
		if($dataTexto[i].contains(e.target)){
			var id = $btnOpProprietario[i].value;
			var p = i;
			
			$dataTexto[p].addEventListener("change", function(e){
				$.ajax({
		            url: '/projectstages_mvc/atualizar/desenvolvimentos-data',
					data:{idTarefa : id ,texto :  $dataTexto[p].value},
					success : function(response){
					    	   
					       }
					   });
					
					
			});	
			
			
			$dataTexto[p].addEventListener("keydown", function(e){
			$.ajax({
	            url: '/projectstages_mvc/atualizar/desenvolvimentos-data',
				data:{idTarefa : id ,texto :  $dataTexto[p].value},
				success : function(response){
				    	   
				       }
				   });
				
				});	
			
			
			$dataTexto[p].addEventListener("keyup", function(e){
			$.ajax({
			       url: '/projectstages_mvc/atualizar/desenvolvimentos-data',
			       data:{idTarefa : id ,texto :  $dataTexto[p].value},
			       success : function(response){
			    	   
			       }
			   });
			
			});
		}
		}
});

//Atualiza a data dos concluidos.
$bodyOpenProjetoStatuts.addEventListener('click', function(e){
	for(var i = 0; i <  $dataTexto.length ;i++){
		if($dataTexto[i].contains(e.target)){
			var id = $btnOpProprietario[i].value;
			var p = i;
			
			$dataTexto[p].addEventListener("change", function(e){
				$.ajax({
		            url: '/projectstages_mvc/atualizar/concluidos-data',
					data:{idTarefa : id ,texto :  $dataTexto[p].value},
					success : function(response){
					    	   
					       }
					   });
					
					
			});	
			
			
			$dataTexto[p].addEventListener("keydown", function(e){
			$.ajax({
	            url: '/projectstages_mvc/atualizar/concluidos-data',
				data:{idTarefa : id ,texto :  $dataTexto[p].value},
				success : function(response){
				    	   
				       }
				   });
				
				});	
			
			
			$dataTexto[p].addEventListener("keyup", function(e){
			$.ajax({
			       url: '/projectstages_mvc/atualizar/concluidos-data',
			       data:{idTarefa : id ,texto :  $dataTexto[p].value},
			       success : function(response){
			    	   
			       }
			   });
			
			});
		}
		}
});

function modoNoturnoAtivadoHome(ativado){
	   if(ativado){	    
	   //Geral Padrão
	   var corpo = document.querySelector(".corpo").classList.toggle("bodyNigth"); 
	   var menu = document.querySelector(".menu").classList.toggle("menuNigth"); 
	   var botao = document.querySelector(".botao").classList.toggle("botaoNigth");
	   var principal = document.querySelector(".principal").classList.toggle("principalNigth"); 
	   var opcao = document.querySelector(".opcao").classList.toggle("opcaoNigth");
	   var statusNigth = document.querySelector(".status").classList.toggle("statusNigth");
	   
	   //Tabela Projeto
	   var tabelatarefas = document.querySelector(".tabela-tarefas").classList.toggle("tabela-tarefasNigth");
	   var projetotarefa = document.querySelectorAll(".projeto-tarefa");
	   var projetotarefatext = document.querySelectorAll(".projeto-tarefa-text");
	   var projetoproprietario = document.querySelectorAll(".projeto-proprietario");   
	   var projetostatus = document.querySelectorAll(".projeto-status");
	   var projetodata = document.querySelectorAll(".projeto-data");
	   var projetodatatext = document.querySelectorAll(".projeto-data-text");    
	   var projetodelete = document.querySelectorAll(".projeto-delete");  
	   var projetoaddtarefa = document.querySelector(".projeto-add-tarefa").classList.toggle("projeto-add-tarefaNigth");
	   var botaoaddprojeto = document.querySelector(".botao-add-projeto").classList.toggle("botao-add-projetoNigth");
	   var boxTarefasStatus = document.querySelectorAll(".caixa-opcoes-projeto-status");

	   for (var i = 0; i < projetostatus.length; i++) {
		   projetotarefa[i].classList.toggle("projeto-tarefaNigth");
		   projetotarefatext[i].classList.toggle("projeto-tarefa-textNigth");
		   projetoproprietario[i].classList.toggle("projeto-proprietarioNigth"); 
		   projetostatus[i].classList.toggle("projeto-statusNigth"); 
		   projetodata[i].classList.toggle("projeto-dataNigth");
		   projetodatatext[i].classList.toggle("projeto-data-textNigth");
		   projetodelete[i].classList.toggle("projeto-deleteNigth");
		   boxTarefasStatus[i].classList.toggle("caixa-opcoes-projeto-statusNigth");
		 }
	   
	   //Caixa Add Tarefa
	   var caixaaddtarefasinterno = document.querySelector(".caixa-add-tarefas-interno").classList.toggle("caixa-add-tarefas-internoNigth");
	   var fechacaixatarefas = document.querySelector(".fecha-caixa-tarefas").classList.toggle("fecha-caixa-tarefasNigth");
	   var botaocaixaaddtarefas = document.querySelector(".botao-caixa-add-tarefas").classList.toggle("botao-caixa-add-tarefasNigth");
	   var textcaixaaddtarefas = document.querySelector(".text-caixa-add-tarefas").classList.toggle("text-caixa-add-tarefasNigth");
	   }else{
		  
	   //Remove Geral Padrão
	   var corpo = document.querySelector(".corpo").classList.remove("bodyNigth");
	   var menu = document.querySelector(".menu").classList.remove("menuNigth"); 
	   var principal = document.querySelector(".principal").classList.remove("principalNigth"); 
	   var botao = document.querySelector(".botao").classList.remove("botaoNigth");
	   var opcao = document.querySelector(".opcao").classList.remove("opcaoNigth");
	   var statusNigth = document.querySelector(".status").classList.remove("statusNigth");
	  
	   //Remove Tabela Projeto
	   var tabelatarefas = document.querySelector(".tabela-tarefas").classList.remove("tabela-tarefasNigth");
	   var projetotarefa = document.querySelectorAll(".projeto-tarefa");
	   var projetotarefatext = document.querySelectorAll(".projeto-tarefa-text");
	   var projetoproprietario = document.querySelectorAll(".projeto-proprietario");   
	   var projetostatus = document.querySelectorAll(".projeto-status");
	   var projetodata = document.querySelectorAll(".projeto-data");
	   var projetodatatext = document.querySelectorAll(".projeto-data-text");    
	   var projetodelete = document.querySelectorAll(".projeto-delete");  
	   var projetoaddtarefa = document.querySelector(".projeto-add-tarefa").classList.remove("projeto-add-tarefaNigth");
	   var botaoaddprojeto = document.querySelector(".botao-add-projeto").classList.remove("botao-add-projetoNigth");
	   var boxTarefasStatus = document.querySelectorAll(".caixa-opcoes-projeto-status");
	   
	   for (var i = 0; i < projetostatus.length; i++) {
		   projetotarefa[i].classList.remove("projeto-tarefaNigth");
		   projetotarefatext[i].classList.remove("projeto-tarefa-textNigth");
		   projetoproprietario[i].classList.remove("projeto-proprietarioNigth"); 
		   projetostatus[i].classList.remove("projeto-statusNigth"); 
		   projetodata[i].classList.remove("projeto-dataNigth");
		   projetodatatext[i].classList.remove("projeto-data-textNigth");
		   projetodelete[i].classList.remove("projeto-deleteNigth");
		   boxTarefasStatus[i].classList.remove("caixa-opcoes-projeto-statusNigth");
		 }
	   
	   //Remove Caixa Add Tarefa
	   var caixaaddtarefasinterno = document.querySelector(".caixa-add-tarefas-interno").classList.remove("caixa-add-tarefas-internoNigth");
	   var fechacaixatarefas = document.querySelector(".fecha-caixa-tarefas").classList.remove("fecha-caixa-tarefasNigth");
	   var botaocaixaaddtarefas = document.querySelector(".botao-caixa-add-tarefas").classList.remove("botao-caixa-add-tarefasNigth");
	   var textcaixaaddtarefas = document.querySelector(".text-caixa-add-tarefas").classList.remove("text-caixa-add-tarefasNigth");
	   }
}    

(function(){
	'use strict'; 
	
	$.ajax({
       url: '/projectstages_mvc/retorna/modo-noturno/home',
       success : function(data){
       	modoNoturnoAtivadoHome(data);
       }
   });
	
	$.ajax({
	       url: '/projectstages_mvc/retorna/id-all-atividades',
	       success : function(response){
	    	   for(var j = 0; j < response.length;j++ ){
	    		   ExibirTodosOsProprietarios(response[j]);
	    	   }
	       }
	   });
	
	verificarPermissoesDeFuncao();

	ExibirProjetoStatusTarefas();
	
	ExibirProjetoStatusDesenvolvimentos();
	
	ExibirProjetoStatusConcluidos();
	
	verificacaoParticipantes();
			
	ExibirTodosOsParticipantes();
	
	ExibirTodosOsParticipantesInseridosNoProjeto();
	
	verificacaoADMs();
	
})();