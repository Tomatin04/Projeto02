<?php $this->layout( 'layout'); 
?>

<div class="user-account-panel">
    <h1>Conta do Usuário</h1>

    <form action='/perfil' method="POST">
        <div class="user-info">
            <label><strong>Nome:</strong>
                <input type="text" id="username" value="<?php echo $user['nome']?>" name='nome' disabled>
            </label>

            <p><strong>Email:</strong><?php echo $user['email']?></p>
        </div>
        
        <div class="password-section">
            <input type="password" id="password" placeholder="" name="senha" disabled>
        </div>

        <div class="submit-section">
            <button id="submit-button" style="display: none;" type="submit">Enviar Dados</button>
        </div>
        </form>
        <div class="password-section">
            <button id="unlock-password-button" >Alterar Informações</button>
        </div>
    
    <button id="delete-account-button" class="delete-button">X</button>
</div>
<script>
    const usernameInput = document.getElementById('username');
    const passwordInput = document.getElementById('password');
    const unlockPasswordButton = document.getElementById('unlock-password-button');

    const submitButton = document.getElementById('submit-button');


    unlockPasswordButton.addEventListener('click', () => {
        passwordInput.disabled = false;
        usernameInput.disabled = false;
        submitButton.style.display = 'inline-block'; 
    });


</script>
