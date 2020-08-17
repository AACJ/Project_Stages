var $situacao01 = document.querySelector('#situacao01');
var $situacao02 = document.querySelector('#situacao02');
var $situacao03 = document.querySelector('#situacao03');
var $situacao04 = document.querySelector('#situacao04');
var $situacao05 = document.querySelector('#situacao05');
var $situacao06 = document.querySelector('#situacao06');
var $status = document.querySelector('.perfil-status');
var $btnAddNovoProjeto = document.querySelector('#btn-add-novo-projeto');
var $caixaAddNovoProjeto = document.querySelector('#caixa-add-projeto');
var $fechaCaixaAddNovoProjeto = document.querySelector('#fecha-caixa-add-projeto');
var $searchNav = document.getElementById("search-nav");
var $principalNav = document.getElementById("principal-nav");
var $searchMenuProjectParticipantes = document.getElementById("pesquisa");

function habilitarStatusUsuario(result){
	if(result == "Disponivel"){
		$situacao01.cheked = true;
		$situacao01.style.background = 'white';
		$status.style.background = 'green';
	}else
	if(result == "Indisponivel"){
		$situacao02.cheked = true;
		$status.style.background = 'orange';
	}else 
	if(result == "Trabalhando"){
		$situacao03.cheked = true;
		$status.style.background = 'cyan';
	}else
	if(result == "Temporiaramente"){
		$situacao04.cheked = true;
		$status.style.background = 'mediumvioletred';
	}else
	if(result == "Doenca"){
		$situacao05.cheked = true;
		$status.style.background = 'red';
	}else
	if(result == "Folga"){
		$situacao06.cheked = true;
		$status.style.background = 'yellow';
	}
}


$situacao01.addEventListener('change', function(){
	$.ajax({
	       url: '/projectstages_mvc/recebe/status-usuario',
	       data:{ status: $situacao01.value},
		   success : function(response){
			   habilitarStatusUsuario(response);
		   }    
	});
});

$situacao02.addEventListener('change', function(){
	$.ajax({
	       url: '/projectstages_mvc/recebe/status-usuario',
	       data:{ status: $situacao02.value},
		   success : function(response){
			   habilitarStatusUsuario(response);
		   }
	       
	});
});

$situacao03.addEventListener('change', function(){
	$.ajax({
	       url: '/projectstages_mvc/recebe/status-usuario',
	       data:{ status: $situacao03.value},
		   success : function(response){
			   habilitarStatusUsuario(response);
		   }    
	});
});

$situacao04.addEventListener('change', function(){
	$.ajax({
	       url: '/projectstages_mvc/recebe/status-usuario',
	       data:{ status: $situacao04.value},
		   success : function(response){
			   habilitarStatusUsuario(response);
		   }
	       
	});
});

$situacao05.addEventListener('change', function(){
	$.ajax({
	       url: '/projectstages_mvc/recebe/status-usuario',
	       data:{ status: $situacao05.value},
		   success : function(response){
			   habilitarStatusUsuario(response);
		   }
	       
	});
});

$situacao06.addEventListener('change', function(){
	$.ajax({
	       url: '/projectstages_mvc/recebe/status-usuario',
	       data:{ status: $situacao06.value},
		   success : function(response){
			   habilitarStatusUsuario(response);
		   }
	       
	});
});

const btn = document.getElementById('btnPerfil');
const btnActi = document.getElementById('btn-perfil-actived');
const aux = document.getElementById('op');
const body = document.getElementById('corpo');
    btn.addEventListener('click', function(){
        aux.style.display="block";
        btnActi.style.display="block"; 
      body.addEventListener('click', function(e){
          if(!(aux.contains(e.target)) && !(btn.contains(e.target))){
            aux.style.display="none";
            btnActi.style.display="none";
         }
        });
        
    });

$btnAddNovoProjeto.addEventListener('click', function(){
   $caixaAddNovoProjeto.style.display = "flex";
});

$fechaCaixaAddNovoProjeto.addEventListener('click', function(){
   $caixaAddNovoProjeto.style.display = "none";
});

$searchMenuProjectParticipantes.addEventListener("keydown", function(e){

	$searchNav.style.display = "block";
	$principalNav.style.display = "none";
	
	if($searchMenuProjectParticipantes.value == ""){
		$searchNav.style.display = "none";
		$principalNav.style.display = "block";
	}
	
	if(e.keyCode > 46 && e.keyCode < 91){
	$.ajax({
	       url: '/projectstages_mvc/retorna/projeto-pesquisa',
	       data:{nome : $searchMenuProjectParticipantes.value},
	       success : function(response){
	    	   $.ajax({
	    	       url: '/projectstages_mvc/clear/projeto-pesquisa',
	    	       success : function(result){
	    	       
	    	    	   for(var i = 0; i < result.length;i++ ){
	    	    	
	    	    	   var $projetoResult = document.getElementById("projeto-result-" + result[i]);
	    	    	   
	    	    	   $projetoResult.style.display = "none";
	    	    	   }
	    	    	   
	    	    	   for(var i = 0; i < response.length;i++ ){
	    	    		 var $projetoResult = document.getElementById("projeto-result-" + response[i]);
	    		    	   
	    	    		   $projetoResult.style.display = "flex";
	    		    	   }
	    	    	   
	    	    
	    	       }
	    	   });
	    	  
	       }
	   });
	}
});

$searchMenuProjectParticipantes.addEventListener("keyup", function(e){

	$searchNav.style.display = "block";
	$principalNav.style.display = "none";
	
	if($searchMenuProjectParticipantes.value == ""){
		$searchNav.style.display = "none";
		$principalNav.style.display = "block";
	}
	
	if(e.keyCode > 46 && e.keyCode < 91){
	$.ajax({
	       url: '/projectstages_mvc/retorna/projeto-pesquisa',
	       data:{nome : $searchMenuProjectParticipantes.value},
	       success : function(response){
	    	   $.ajax({
	    	       url: '/projectstages_mvc/clear/projeto-pesquisa',
	    	       success : function(result){
	    	       
	    	    	   for(var i = 0; i < result.length;i++ ){
	    	    	
	    	    	   var $projetoResult = document.getElementById("projeto-result-" + result[i]);
	    	    	   
	    	    	   $projetoResult.style.display = "none";
	    	    	   }
	    	    	   
	    	    	   for(var i = 0; i < response.length;i++ ){
	    	    		   var $projetoResult = document.getElementById("projeto-result-" + response[i]);
	    		    	   
	    	    		   $projetoResult.style.display = "flex";
	    		    	   }
	    	    	   
	    	    
	    	       }
	    	   });
	    	  
	       }
	   });
	}
});


function ExibirUsuarioStatus(){
	'use strict'; 
	
	$.ajax({
       url: '/projectstages_mvc/retorna/status-usuario/home',
       success : function(data){
    	   habilitarStatusUsuario(data);
       }
   });
}

ExibirUsuarioStatus();
