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

        if($response != null){
            $_SESSION['token'] = $response;
            return new Response(200, body: $this->templates->render('main_view'));
        }else{
            return new Response(200, body: $this->templates->render('login_view'));
        }
        
    }
}

/**
 * public function handle(ServerRequestInterface $request): ResponseInterface
    {
        $email = filter_input(INPUT_POST, 'email');
        $password = filter_input(INPUT_POST, 'senha');

        $ch = curl_init('http://localhost:8090/api/login');

        $data = [
            "login" => $email,
            "senha" => $password
        ];
        $jsonData = json_encode($data);


        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_POST, true);
        curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonData);

        
        // Cabeçalhos
        curl_setopt($ch, CURLOPT_HTTPHEADER, [
            
            'Accept: application/json',
            'Content-Type: application/json',
            'Content-Length: ' . strlen($jsonData)
        ]);

        $response = curl_exec($ch);

        $respostaArray = json_decode($response, true);




        // Verifica erros
        
        if (curl_errno($ch) || $response == null) {
            //echo 'Erro: ' . curl_error($ch);
            return new Response(403, body: $this->templates->render('login_view'));
        } else {
            curl_close($ch);
            $_SESSION['logado'] = 'logado';
            $_SESSION['token'] = $respostaArray['token'];
           // echo 'Resposta da API: ' . $response;
            return new Response(200, body: $this->templates->render('main_view'));
        }

        

        
    }
 */