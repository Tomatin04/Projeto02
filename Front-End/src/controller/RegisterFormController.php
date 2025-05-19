<?php

declare (strict_types=1);

namespace Front_End\controller;

use Nyholm\Psr7\ServerRequest;
use Psr\Http\Message\ResponseInterface;
use Psr\Http\Server\RequestHandlerInterface;
use League\Plates\Engine;

class RegisterFormController implements RequestHandlerInterface
{
    public function __construct(private Engine $templates)
    {
        
    }

    public function handle(ServerRequest $request): ResponseInterface
    {

        return null;
    }
}