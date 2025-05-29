<?php 

declare(strict_types=1);

namespace Porjeto02\Frontend\service;

use Porjeto02\Frontend\entity\Comment;

class  CommentAux
{
    static function  renderComentarios($comentarios, int $nivel = 0): string {
    $html = '';
    foreach ($comentarios as $comentario) {
        $margem = $nivel * 10;

        $html .= "<div class='comentario' style='margin-left: {$margem}px'>";
        $html .= "<p class='p-resposta-comentario' ><strong>ID {$comentario->id}:</strong> " . htmlspecialchars($comentario->comment) . "</p>";
        
        if (!empty($comentario->getRespostas())) {
            
            $html .= self::renderComentarios($comentario->getRespostas(), $nivel + 1);
        }

        $html .= "</div>";
    }
    return $html;
}

}