<?php $this->layout( 'layout'); ?>

<main class="content">
        <section class="janela-aviso">
            <div class="aviso">
                <h2><?= $new->titulo?></h2>
                <p><?= $new->conteudo?></p>
            </div>

            <div class="comentarios">
                <h3>Comentários</h3>

                <?php if (!empty($comments)): ?>
                    <?= \Porjeto02\Frontend\service\CommentAux::renderComentarios($comments) ?>
                <?php endif; ?>
        </section>
    </main>

