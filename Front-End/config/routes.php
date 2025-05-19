<?php 

declare(strict_types=1);


return [
    'GET|/login' => \Front_End\controller\LoginFormController::class,
    'GET|/' => \Front_End\controller\MainViewController::class,
    'POST|/login' => \Front_End\controller\LoginController::class,
    'GET|/register' => \Front_End\controller\RegisterFormController::class,
    'POST|/register' => \Front_End\controller\RegisterController::class,
];