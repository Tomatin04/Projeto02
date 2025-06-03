<?php 

declare(strict_types=1);

namespace Porjeto02\Frontend\entity;

class Comment
{

    /**
     * Summary of respostas
     * @var Comment[]
     */
    private array $respostas = [];
    
    function __construct(
        public readonly int $id,
        public readonly int $anew,
        public readonly string $comment,
        public readonly string $username
    ){}

    public static function fromArray(array $data): self
    {
        if(isset($data['respostas']) && is_array($data['respostas'])) {
            $respostas = array_map(fn($item) => self::fromArray($item), $data['respostas']);
        } else {
            $respostas = [];
        }

        $comment = new self(
            id: (int) $data['id'],
            anew: (int) $data['anew'],
            comment: $data['comment'],
            username: $data['username']
        );
        $comment->respostas = $respostas;

        return $comment;
    }
    public function getRespostas(): array
    {
        return $this->respostas;
    }
}