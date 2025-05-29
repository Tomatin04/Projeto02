<?php $this->layout( 'layout'); ?>

<main class="content">
        <div class="painel-principal">
            <section class="painel-info">
                <h2><?=reset($news)['titulo']?></h2>
                <p><?=reset($news)['conteudo']?></p>
            </section>
    
            <section class="painel-lista">
                <h2>Nóticias passadas</h2>
                <?php foreach($news as $new): ?>
                    <ul>
                        <li>
                            <a href="/new-view?id=<?= $new['id']?>" class="remover-sub-a">
                                <div class='div-list-news'>
                                    <?php echo $new["titulo"]?>
                                </div>
                            </a>
                        </li>
                    </ul>
                <?php endforeach; ?>
            </section>
        </div>
    
        <section class="painel-lateral">
            <h2>Painel Lateral</h2>
            <p>Esse painel contém informações adicionais...</p>
            <!-- mais conteúdo -->
        </section>
    </main>
