<?php

namespace Porjeto02\Frontend\controllers;

use Nyholm\Psr7\ServerRequest;
use Psr\Http\Message\RequestInterface;
use Psr\Http\Message\ServerRequestInterface;
use Psr\Http\Server\RequestHandlerInterface;
use League\Plates\Engine;
use Nyholm\Psr7\Response;
use Porjeto02\Frontend\service\RequestBackEnd;
use Psr\Http\Message\ResponseInterface;

class PerfilController implements RequestHandlerInterface
{

    public function __construct(private Engine $templates){}

    public function handle(ServerRequestInterface $request): ResponseInterface
    {
        $name = filter_input(INPUT_POST, 'nome');
        $password = filter_input(INPUT_POST, 'senha');

        $data = [
            "nome" => $name,
            "senha" => $password 
        ];

        $json = json_encode($data);

        (new RequestBackEnd()) -> requestPut('usuarios', $json);

        return new Response($_SESSION['HttpStatus'], ['Location' => '/perfil']);
    }
    
}