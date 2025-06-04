<?php $this->layout('layout'); ?>

<div class="container">
    <div class="noticia">
        <h1><?= $new->titulo ?></h1>
        <p><?= $new->conteudo ?></p>
        <?php if($_SESSION['ADM']): ?>
            <a href="/nova-new?id=<?= $new->id?>"><button class='botao-alter'>ALTERAR NOTICIA</button></a>
        <?php endif;?>
    </div>

    <div class="comentarios">
        <h2>Comentar: </h2>

        <form action="comentar" method="POST">
        <div class="comentario-form">
                <input type="hidden" id="id_new" name="id_new" value="<?= $new->id?>" >
                <textarea id="comentarioInput" name="comentario" placeholder="Escreva seu comentário..."></textarea>
                <button type="submit">Comentar</button>  
        </div>
        </form>
        <h2>Comentários</h2>
        <?php foreach ($comments as $coment): ?>
            <div id="comentariosContainer" class="lista-comentarios">
                <strong><?= $coment->username ?>: </strong>
                <p><?= $coment->comment ?></p>
            </div>
        <?php endforeach; ?>
    </div>
</div>