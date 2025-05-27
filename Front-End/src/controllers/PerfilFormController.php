<?php

declare(strict_types=1);

namespace Porjeto02\Frontend\controllers;


use League\Plates\Engine;
use Nyholm\Psr7\Response;
use Porjeto02\Frontend\service\RequestBackEnd;
use Psr\Http\Message\ResponseInterface;
use Psr\Http\Message\ServerRequestInterface;
use Psr\Http\Server\RequestHandlerInterface;
class PerfilFormController implements RequestHandlerInterface{

    public function __construct(private Engine $templates){}

    public function handle(ServerRequestInterface $request): ResponseInterface
    {
        $response = (new RequestBackEnd())->requestGet('usuarios');
        $user = $response["usuario"];
        var_dump($user);
        exit();
        return new Response(200, body: $this->templates->render('user_view'). [
            'user' => $user
        ]);
    }

}