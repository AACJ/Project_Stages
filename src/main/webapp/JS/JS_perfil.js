var $file = document.getElementById('file');
var $btnFile = document.getElementById('btnfile');
var $img = document.getElementById("imagem");
var $imgUser = document.getElementById("user");    
var $btnEditarInformacao = document.getElementById("button-edit-informacoes");
var $btnSalvarInformacao = document.getElementById("button-save-informacoes");
var $textoGmail = document.getElementById("textGmail");
var $textoSkype = document.getElementById("textSkype");
var $textoBiografia = document.getElementById("textBiografia");
var $textoCelular = document.getElementById("textCelular");
var $textoHorario = document.getElementById("textHorario");
var $textoLocalizacao = document.getElementById("textLocalizacao");
var $textoAniversario = document.getElementById("textAniversario");
var $formFotoPerfil = document.getElementById("form-Foto-Perfil");

$(document).ready(function(){
    $('#textCelular').mask('(00)00000-0000');
});

$btnEditarInformacao.addEventListener("click", function(){
    $btnEditarInformacao.style.display = "none";
    $btnSalvarInformacao.style.display = "block";
    $textoGmail.readOnly = false;
    $textoSkype.readOnly = false;
    $textoBiografia.readOnly = false;
    $textoCelular.readOnly = false;
    $textoHorario.readOnly = false;
    $textoLocalizacao.readOnly = false;
    $textoAniversario.readOnly = false;
});

/* $btnFile.addEventListener("click", function(){
	$formFotoPerfil.submit();  
	//$file.click();
});
   */
$file.addEventListener("change",function(){
	/*var file = this.files[0];
        
    if(file){
        const reader = new FileReader();
        reader.addEventListener("load",function(){
        $img.setAttribute("src", this.result);
        $imgUser.setAttribute("src",this.result);
        });  
        reader.readAsDataURL(file);
        $formFotoPerfil.submit(); 
    }*/
    
    $formFotoPerfil.submit();
   
});  
