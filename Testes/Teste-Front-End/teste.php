<?php

// URL da sua API Spring Boot
$url = 'http://localhost:8090/login';

// Token de autenticação


// Dados que você quer enviar
$data = [
    "login" => 'louco',
    "senha" => '1234'
];

/**
 * "nome": "string",
  "email": "string",
  "telefone": "string",
  "crm": "977528",
  "especialidade": "ORTOPEDIA",
  "endereco": {
    "logradouro": "string",
    "bairro": "string",
    "cep": "31661513",
    "cidade": "string",
    "uf": "string",
    "complemento": "string",
    "numero": "string"
  }
 */
// Transforma o array em JSON
$jsonData = json_encode($data);

echo $jsonData;
// Inicializa cURL
$ch = curl_init($url);

// Configurações da requisição
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLOPT_POST, true);
curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonData);

// Cabeçalhos
curl_setopt($ch, CURLOPT_HTTPHEADER, [
    
    'Accept: application/json',
    'Content-Type: application/json',
    'Content-Length: ' . strlen($jsonData)
]);

// Executa e captura a resposta
$response = curl_exec($ch);

// Verifica erros
if (curl_errno($ch)) {
    echo 'Erro: ' . curl_error($ch);
} else {
    echo 'Resposta da API: ' . $response;
}

curl_close($ch);
$respostaArray = json_decode($response, true);
print_r($respostaArray);