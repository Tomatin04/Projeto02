<?php $this->layout( 'layout'); ?>

<form class="form-container"  method="POST" action="/nova-new">
    <h2>Nova Noticia</h3>
    <div class="form-group">
        <label class="form-label" for="assunto">Titulo da Noticia:</label>
        <input type="text" id="assunto" name="titulo" class="form-input" placeholder="Digite o titulo" value="<?= $anew->titulo?>">
    </div>

    <div class="form-group">
        <label class="form-label" for="mensagem">Descrição da Noticia:</label>
        <textarea id="mensagem" name="conteudo" class="form-textarea" placeholder="Digite o conteudo"><?= $anew->conteudo ?></textarea>
    </div>

    <div class="form-buttons">
            <a href="/" ><button type="button" class="btn btn-cancelar">CANCELAR</button></a>

            <?php if(isset($anew)):?>
                <input type="hidden" name="id" value="<?= $anew->id?>">
                <button type="submit" class="btn btn-enviar" name='salvar' value="salvar">SALVAR</button>
            <?php else:?>
                 <button type="submit" class="btn btn-enviar" >ENVIAR</button>
            <?php endif;?>
        </div>
    </div>
</form>