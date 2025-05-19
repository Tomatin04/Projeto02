<?php

$builder = new \DI\ContainerBuilder();
$builder->addDefinitions([
    \League\Plates\Engine::class => function(){
        $templatePath = __DIR__ . '/../src/templates';
        return new League\Plates\Engine($templatePath);
    }
]);

/** @var \Psr\Container\ContainerInterface $container */
$container = $builder->build();
return $container;