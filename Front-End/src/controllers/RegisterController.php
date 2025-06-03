<?php

declare(strict_types=1);

namespace Porjeto02\Frontend\controllers;

use League\Plates\Engine;
use Nyholm\Psr7\Response;
use Porjeto02\Frontend\service\RequestBackEnd;
use Psr\Http\Message\ResponseInterface;
use Psr\Http\Message\ServerRequestInterface;
use Psr\Http\Server\RequestHandlerInterface;

class RegisterController implements RequestHandlerInterface
{
    public function __construct(private Engine $templates){}

    public function handle(ServerRequestInterface $request): ResponseInterface
    {
        $email = filter_input(INPUT_POST, 'email', FILTER_VALIDATE_EMAIL);
        $password = filter_input(INPUT_POST, 'senha');
        $confirmPassword = filter_input(INPUT_POST, 'confirmar_senha');
        $name = filter_input(INPUT_POST, 'nome');


        if($password != $confirmPassword){
            $_SESSION['error_msg'] = "As senhas não conferem";
            return new Response(200, body: $this->templates->render('register_view'));
        }

        $data = [
            "email" => $email,
            "senha" => $password,
            "nome" => $name
        ];

        
        $json = json_encode($data);

        $response = (new RequestBackEnd())->requestPost('usuarios', $json);

        if($_SESSION['HttpStatus'] == 201){
            return new Response($_SESSION['HttpStatus'], [
                'Location' => '/login'
            ]);
        }else{
            return new Response($_SESSION['HttpStatus'], body: $this->templates->render('register_view'));
        }
    }
}