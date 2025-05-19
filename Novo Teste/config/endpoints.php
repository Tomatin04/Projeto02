<?php

declare(strict_types=1);

return [
    'login' => 'http://localhost:8090/api/login',
    'logout' => Porjeto02\Frontend\controllers\LoginFormController::class,
    'POST|/login' => Porjeto02\Frontend\controllers\LoginController::class,
    'GET|/register' => Porjeto02\Frontend\controllers\RegisterFormController::class,
    'POST|/register' => Porjeto02\Frontend\controllers\RegisterController::class,
    'GET|/logout' => Porjeto02\Frontend\controllers\LogoutController::class,
];