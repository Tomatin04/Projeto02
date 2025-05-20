<?php

declare(strict_types=1);

namespace Porjeto02\Frontend\controllers;

use League\Plates\Engine;
use Nyholm\Psr7\Response;
use Porjeto02\Frontend\service\RequestBackEnd;
use Psr\Http\Message\ResponseInterface;
use Psr\Http\Message\ServerRequestInterface;
use Psr\Http\Server\RequestHandlerInterface;

class LogoutController implements RequestHandlerInterface
{
    public function __construct(private Engine $templates){}

    public function handle(ServerRequestInterface $request): ResponseInterface
    {
        $reposne = (new RequestBackEnd())->requestGet('logout');

        if($reposne != null){
            session_destroy();
            unset($_SESSION['token']);
            return new Response(200, body: $this->templates->render('login_view'));
        }else{
            return new Response(200, body: $this->templates->render('main_view'));
        }
    }
}