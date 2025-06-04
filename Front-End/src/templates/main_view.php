<?php $this->layout('layout'); ?>

<main class="content">
    <div class="painel-principal">
        <section class="painel-info">
            <h2><?= reset($news)->titulo ?></h2>
            <p><?= reset($news)->conteudo ?></p>
        </section>

        <section class="painel-lista">
            <h2>Nóticias passadas</h2>
            <?php foreach ($news as $new): ?>
                <ul>
                    <li>
                        <a href="/new-view?id=<?= $new->id ?>" class="remover-sub-a">
                            <div class='item-com-botao '>
                                <?= $new->titulo ?>
                                <?php if ($_SESSION['ADM']): ?>
                                    <form method="POST" action="/deleteNew">
                                        <input type="hidden" name='id' value="<?= $new->id ?>">
                                        <button class='botao-pequeno02'>DELETE</button>
                                    </form>
                                <?php endif; ?>
                            </div>
                        </a>
                    </li>
                </ul>
            <?php endforeach; ?>
        </section>
    </div>

    <section class="painel-lateral">
        <h2>NOVIDADES</h2>
        <ul>
            <li><p>Novo sistema de noticias sobre questões ambientais está disponivei, confira todas as noticias que temos.</p></li>
            <li><p>Entre em contato para melhorias, sujestões e avaliação.</p></li>
        </ul>
    </section>
</main>