<?php

declare(strict_types=1);

namespace Porjeto02\Frontend\controllers;

use League\Plates\Engine;
use Nyholm\Psr7\Response;
use Porjeto02\Frontend\service\RequestBackEnd;
use Psr\Http\Message\ServerRequestInterface;
use Psr\Http\Message\ResponseInterface;
use Psr\Http\Server\RequestHandlerInterface;

class NewDeleteController implements RequestHandlerInterface
{
    public function __construct(private Engine $templates){}

    public function handle(ServerRequestInterface $request): ResponseInterface
    {
        $id = filter_input(INPUT_POST, 'id');

        $data = ['id' => $id];

        (new RequestBackEnd())->requestDelete('noticias', json_encode($data));

        if($_SESSION['HttpStatus'] == 200){
            return new Response(200, ['Location' => '/']);
        }
        return new Response(401, ["Location" => '/']);
    }
}