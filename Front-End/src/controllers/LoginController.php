<?php

declare(strict_types=1);

namespace Porjeto02\Frontend\controllers;

use League\Plates\Engine;
use Nyholm\Psr7\Response;
use Porjeto02\Frontend\service\RequestBackEnd;
use Psr\Http\Message\ResponseInterface;
use Psr\Http\Message\ServerRequestInterface;
use Psr\Http\Server\RequestHandlerInterface;

class LoginController implements RequestHandlerInterface
{
    public function __construct(private Engine $templates){}

    public function handle(ServerRequestInterface $request): ResponseInterface
    {
        $email = filter_input(INPUT_POST, 'email', FILTER_SANITIZE_EMAIL);
        $password = filter_input(INPUT_POST, 'senha');

        $data = [
            "email" => $email,
            "senha" => $password
        ];
        $json = json_encode($data);

        $response = (new RequestBackEnd())->requestPost('login', $json);

        if($_SESSION['HttpStatus'] == 200 ){

            $_SESSION['token'] = $response;
            if($email == "adm@mail.com") $_SESSION['ADM'] = true;

            return new Response(200, [
                "Location" => "/"
            ]);
        }else{
            return new Response($_SESSION['HttpStatus'], body: $this->templates->render('login_view'));
        }
        
    }
}
