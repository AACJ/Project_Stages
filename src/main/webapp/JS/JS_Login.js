var $textEmail = document.getElementById("email");
var $textSenha = document.getElementById("senha");
var $btnEnvio = document.getElementById("btnEnvio");

$btnEnvio.addEventListener("click",function(e){
    validacaoEmail(e);
    validacaoSenha(e);
});

function validacaoEmail(e){
	alert("Passou");
	 $.ajax({
         url : 'http://localhost:8080/projectstages_mvc/login/getEmails',
         success : function(data) {
        	 alert("aqui");
        	 alert(data);
             for(var i = 0; i < data.size(); i++){
            	 alert(data[i]);
             if($textEmail.value != data[i]){
            	 alert("Senha ou Email incorreto!");
                 e.defaultPrevented();
             }
         }
     }
	 });
	alert("Terminou");
}

function validacaoSenha(e){
	 $.ajax({
        url : 'http://localhost:8080/projectstages_mvc/login/getSenhas',
        success : function(data) {
         for(var i = 0; i < data.size(); i++){
        	 alert(data[i]);
            if($textSenha.value != data[i]){
            	alert("Senha ou Email incorreto!");
                e.defaultPrevented();
            }
        }
        }
    });
	
}

