<?php 

declare(strict_types=1);

namespace Porjeto02\Frontend\entity;

class User
{
    public function __construct(
        public readonly string $nome,
        public readonly string $email
    ){}

    public static function fromArray(array $data): self
    {
        return new self(
            nome: $data['nome'],
            email: $data['email']
        );
    }
}