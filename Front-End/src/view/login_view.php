<?php require_once __DIR__ . "/header.php"?>

<main>
    <div class="login-container">
        <h2>Entrar</h2>
        <form action="#" method="POST">
            <label for="email">E-mail:</label>
            <input type="email" id="email" name="email" required>
            
            <label for="senha">Senha:</label>
            <input type="password" id="senha" name="senha" required>

            <button type="submit">Entrar</button>
        </form>
        <p>Não tem uma conta? <a href="#">Cadastre-se aqui</a></p>
    </div>
</main>

<?php require_once __DIR__ . "/footer.php"?>