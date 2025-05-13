<?php

$builder = new \DI\ContainerBuilder();
$builder->addDefinitions([
    \League\Plates\Engine::class =>function(){
        $templatPath = __DIR__ . "/../src/view";
        return new \League\Plates\Engine($templatPath);
    }
]);

/** @var \Psr\Container\ContainerInterface $container */
$container = $builder->build();

return $container;
