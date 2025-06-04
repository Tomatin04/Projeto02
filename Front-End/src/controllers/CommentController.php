<?php

declare(strict_types=1);

namespace Porjeto02\Frontend\controllers;

use League\Plates\Engine;
use Nyholm\Psr7\Response;
use Porjeto02\Frontend\service\RequestBackEnd;
use Psr\Http\Message\ServerRequestInterface;
use Psr\Http\Message\ResponseInterface;
use Psr\Http\Server\RequestHandlerInterface;

class CommentController implements RequestHandlerInterface
{
    public function __construct(private Engine $templates){}

    public function handle(ServerRequestInterface $request): ResponseInterface
    {
        $comentario = filter_input(INPUT_POST, 'comentario');
        $idnew = filter_input(INPUT_POST, 'id_new');

        $data = [
            'comment' => $comentario,
            'id_new' => $idnew
        ];

        $reponse = (new RequestBackEnd())->requestPost('comentarios', json_encode($data));

        if($_SESSION['HttpStatus'] == 200){
            return new Response(200, ['Location' => 'new-view?id=' . $idnew]);
        }
        return new Response(401, ['Location' => '/']);
    }
}