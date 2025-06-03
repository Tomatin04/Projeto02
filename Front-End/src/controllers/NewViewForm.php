<?php
declare(strict_types=1);

namespace Porjeto02\Frontend\controllers;

use League\Plates\Engine;
use Nyholm\Psr7\Response;
use Porjeto02\Frontend\entity\Anew;
use Porjeto02\Frontend\entity\Comment;
use Porjeto02\Frontend\service\RequestBackEnd;
use Psr\Http\Message\ServerRequestInterface;
use Psr\Http\Message\ResponseInterface;
use Psr\Http\Server\RequestHandlerInterface;


class NewViewForm implements RequestHandlerInterface
{

    public function __construct(private Engine $templates){}

    public function handle(ServerRequestInterface $request): ResponseInterface
    {
        $id = filter_input(INPUT_GET, 'id', FILTER_VALIDATE_INT);

        $response = (new RequestBackEnd()) ->requestGet('noticias', $id);
        $new = Anew::fromArray($response);
        $response = (new RequestBackEnd()) ->requestGet('comentarios', $id);

        $comments = array_map(fn($item) => Comment::fromArray($item), $response['comentarios']);
        

        return new Response($_SESSION['HttpStatus'], body: $this->templates->render('news_view',
        ['new' => $new,
              'comments' => $comments]
    ));
    }
}