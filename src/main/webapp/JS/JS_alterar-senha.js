var $formesqueceusenha = document.getElementById('form-esqueceu-senha');
var $novaSenha = document.getElementById('novaSenha');
var $novaSenhaConfirmada = document.getElementById('novaSenhaConfirmada');

$formesqueceusenha.addEventListener('submit', function(e){
	 if($novaSenha.value != $novaSenhaConfirmada.value){
         alert("Erro na confirmação de senha!");
         e.preventDefault();
        }
});