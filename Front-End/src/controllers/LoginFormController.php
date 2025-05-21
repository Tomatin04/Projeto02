<?php

declare(strict_types=1);

namespace Porjeto02\Frontend\controllers;

use League\Plates\Engine;
use Nyholm\Psr7\Response;
use Psr\Http\Message\ResponseInterface;
use Psr\Http\Message\ServerRequestInterface;
use Psr\Http\Server\RequestHandlerInterface;

class LoginFormController implements RequestHandlerInterface
{
    public function __construct(private Engine $templates){}

    public function handle(ServerRequestInterface $request): ResponseInterface
    {
        if (array_key_exists('token', $_SESSION) && $_SESSION['token'] != null){
            return new Response(302, [
                'Location' => '/'
            ]);
        }
        return new Response(200, body: $this->templates->render('login_view'));
    }
}