<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Página de Cadastro</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/tela-central.css">
    <link rel="stylesheet" href="css/aviso.css">
    <link rel="stylesheet" href="css/cadastro.css">
    <link rel="stylesheet" href="css/perfil.css">
    <link rel="stylesheet" href="css/creditos.css">
    <link rel="stylesheet" href="css/comentario.css">
    
</head>

<body>

    <!-- Cabeçalho -->
    <header>
        <!-- Botão do Menu -->
        <input type="checkbox" id="menu-toggle" class="menu-toggle">
        <label for="menu-toggle" class="menu-btn">☰ Menu</label>

        <!-- Menu -->
        <?php if(isset($_SESSION['token'])): ?>
        <nav id="menu" class="hidden">
            <ul>
                <li><a href="/">Home</a></li>
                <li><a href="/perfil">Perfil</a></li>
                <?php if($_SESSION['ADM']): ?><li><a href="/nova-new">Noticia</a></li> <?php endif;?>
                <li><a href="/creditos">Créditos</a></li>
            </ul>
        </nav>
        <?php endif; ?>

        <?php if(isset($_SESSION['token'])): ?>
        <a href="/logout" class="remover-sub-a"><button class="login-btn">Logout</button></a>
        <?php endif;?>
    </header>

    <?= $this->section('content');  ?>

    <footer>
        <p>Contato: <a href="mailto:contato@exemplo.com">contato@exemplo.com</a> | Telefone: (11) 1234-5678</p>
    </footer>
</body>
<script>
    let tempoInatividade = 3 * 60 * 60 * 1000; // 5 minutos
    let timer;

    function redirecionar() {
        window.location.href = "/timeout";
    }

    function resetarTimer() {
        clearTimeout(timer);
        timer = setTimeout(redirecionar, tempoInatividade);
    }

    // Detecta qualquer atividade do usuário
    window.onload = resetarTimer;
    document.onmousemove = resetarTimer;
    document.onkeypress = resetarTimer;
    document.onscroll = resetarTimer;
    document.onclick = resetarTimer;
</script>

</html>