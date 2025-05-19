<?php 

declare(strict_types=1);


return [
    'GET|/login' => \Front_End\controller\LoginFormController::class,
    'GET|/' => \Front_End\controller\MainViewController::class,
    'POST|/login' => \Front_End\controller\LoginController::class,
    'GET|/cadastrar' => \Front_End\controller\RegisterFormController::class
];