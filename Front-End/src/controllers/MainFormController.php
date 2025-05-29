<?php

declare(strict_types=1);

namespace Porjeto02\Frontend\controllers;

use League\Plates\Engine;
use Nyholm\Psr7\Response;
use Porjeto02\Frontend\entity\Anew;
use Porjeto02\Frontend\service\RequestBackEnd;
use Psr\Http\Message\ResponseInterface;
use Psr\Http\Message\ServerRequestInterface;
use Psr\Http\Server\RequestHandlerInterface;

class MainFormController implements RequestHandlerInterface
{
    public function __construct(private Engine $templates){}

    public function handle(ServerRequestInterface $request): ResponseInterface
    {
        $responseNew = (new RequestBackEnd())->requestGet('noticias');

        $news = array_map(fn($item) => Anew::fromArray($item), $responseNew['content']);
        
        return new Response(200, body: $this->templates->render('main_view', [
            'news' => $news
        ]));
    }
}