<?php

declare(strict_types=1);

namespace Porjeto02\Frontend\entity;

use function DI\string;

class Anew
{
    public function __construct(
        public readonly int $id,
        public readonly string $titulo,
        public readonly string $conteudo
    ){}

    public static function fromArray(array $data): self
    {
        return new self(
            id: (int) $data['id'],
            titulo: (string) $data['titulo'],
            conteudo: (string) $data['conteudo']
        );
    }
}