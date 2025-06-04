<?php

declare(strict_types=1);

namespace Porjeto02\Frontend\controllers;

use League\Plates\Engine;
use Nyholm\Psr7\Request;
use Nyholm\Psr7\Response;
use Porjeto02\Frontend\entity\Anew;
use Porjeto02\Frontend\service\RequestBackEnd;
use Psr\Http\Message\ServerRequestInterface;
use Psr\Http\Message\ResponseInterface;
use Psr\Http\Server\RequestHandlerInterface;

class NewFormController implements RequestHandlerInterface
{
    public function __construct(private Engine $templates){}

    public function handle(ServerRequestInterface $request): ResponseInterface
    {
        if(isset($_GET['id'])){
            $response = (new RequestBackEnd())->requestGet('noticias', (int)$_GET['id']);
            $anew = Anew::fromArray($response);
        }
        return new Response(200, body: $this->templates->render('new_form', ['anew' => $anew]));
    }
}