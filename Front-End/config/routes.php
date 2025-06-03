<?php

declare(strict_types=1);

return [
    'GET|/' => Porjeto02\Frontend\controllers\MainFormController::class,
    'GET|/login' => Porjeto02\Frontend\controllers\LoginFormController::class,
    'POST|/login' => Porjeto02\Frontend\controllers\LoginController::class,
    'GET|/register' => Porjeto02\Frontend\controllers\RegisterFormController::class,
    'POST|/register' => Porjeto02\Frontend\controllers\RegisterController::class,
    'GET|/logout' => Porjeto02\Frontend\controllers\LogoutController::class,
    'GET|/perfil' => Porjeto02\Frontend\controllers\PerfilFormController::class,
    'POST|/perfil' => Porjeto02\Frontend\controllers\PerfilController::class,
    'GET|/new-view' => Porjeto02\Frontend\controllers\NewViewForm::class,
    'GET|/creditos' => Porjeto02\Frontend\controllers\CreditosFormController::class,
    'GET|/timeout' => Porjeto02\Frontend\controllers\TimeOutController::class,

];