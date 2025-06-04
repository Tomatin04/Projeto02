<?php

declare(strict_types=1);

namespace Porjeto02\Frontend\controllers;

use League\Plates\Engine;
use Nyholm\Psr7\Response;
use Porjeto02\Frontend\service\RequestBackEnd;
use Psr\Http\Message\ServerRequestInterface;
use Psr\Http\Message\ResponseInterface;
use Psr\Http\Server\RequestHandlerInterface;

class NovaNewController implements RequestHandlerInterface
{
    public function __construct(private Engine $templates){}

    public function handle(ServerRequestInterface $request): ResponseInterface
    {
        $titulo = filter_input(INPUT_POST, 'titulo');
        $conteudo = filter_input(INPUT_POST, 'conteudo');

        $data = [
            'titulo' => $titulo,
            'conteudo' => $conteudo
        ];

        if(filter_input(INPUT_POST, 'salvar') != null){
            $data['id'] = filter_input(INPUT_POST, 'id', FILTER_VALIDATE_INT);
            
            (new RequestBackEnd())->requestPut('noticias', json_encode($data));

            if($_SESSION['HttpStatus'] == 200){
                return new Response($_SESSION['HttpStatus'], ['Location' => '/']);
            }
            return new Response(401, ['Location' => 'nova-new']);
        }

        (new RequestBackEnd())->requestPost('noticias', json_encode($data));

        if($_SESSION['HttpStatus'] == 201){
            return new Response($_SESSION['HttpStatus'], ['Location' => '/']);
        }
        return new Response(401, ['Location' => 'nova-new']);
    }
}