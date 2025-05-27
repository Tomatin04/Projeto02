<?php $this->layout( 'layout'); ?>

<main class="content">
        <div class="painel-principal">
            <section class="painel-info">
                <h2><?=$news[0]["titulo"]?></h2>
                <p><?=$news[0]["conteudo"]?></p>
            </section>
    
            <section class="painel-lista">
                <h2>Nóticias passadas</h2>
                <?php foreach($news as $new): ?>
                    <ul>
                    <li><?php echo $new["titulo"] ?></li>
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
