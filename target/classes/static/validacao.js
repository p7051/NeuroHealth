/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


function validarFormulario() {
    var nome = document.getElementById('nome').value;
    var email = document.getElementById('email').value;
    var senha = document.getElementById('senha').value;

    // Exemplo de validação: Nome não pode ser vazio
    if (nome.trim() === '') {
        alert('Por favor, preencha o campo Nome.');
        return false;
    }

    // Exemplo de validação: E-mail deve ser válido
    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
        alert('Por favor, insira um e-mail válido.');
        return false;
    }

    // Exemplo de validação: Senha deve ter pelo menos 6 caracteres
    if (senha.length < 6) {
        alert('A senha deve ter pelo menos 6 caracteres.');
        return false;
    }

    // Se todas as validações passarem, o formulário é enviado
    return true;
}