<?php $this->layout( 'layout'); ?>

<main>
    <div class="login-container">
        <h2>Entrar</h2>
        <form action="#" method="POST">
            <label for="email">E-mail:</label>
            <input  id="email" name="email" required>
            
            <label for="senha">Senha:</label>
            <input type="password" id="senha" name="senha" >

            <button type="submit">Entrar</button>
        </form>
        <p>Não tem uma conta? <a href="/register">Cadastre-se aqui</a></p>
    </div>
</main>

