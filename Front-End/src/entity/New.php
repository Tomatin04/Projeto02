<?php

declare(strict_types=1);

namespace Porjeto02\Frontend\entity;

class Anew
{
    public function __construct(
        public readonly int $id,
        public readonly string $titulo,
        public readonly string $conteudo
    ){}
}