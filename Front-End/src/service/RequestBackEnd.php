<?php

declare(strict_types=1);

namespace Porjeto02\Frontend\service;

use ArrayAccess;

class RequestBackEnd
{

    private $apiEndpoints;


    public function __construct(){
        $this->apiEndpoints = require __DIR__ . '/../../config/endpoints.php';
    }

    public function requestGet(String $endpoint)
    {
        $ch = curl_init($this->apiEndpoints[$endpoint]);
        
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_HTTPHEADER, [
            'Accept: application/json',
            'Authorization: Bearer ' . $_SESSION['token']
        ]);

        $response = curl_exec($ch);
        curl_close($ch);

        return json_decode($response, true);
    }

    public function requestPost(String $endpoint, String $data)
    {
        $ch = curl_init($this->apiEndpoints[$endpoint]);
        
        $header = [
            'Accept: application/json',
            'Content-Type: application/json',
        ];

        if (isset($_SESSION['token'])) $header[] = 'Authorization: Bearer ' . $_SESSION['token'];
        

        //var_dump($header);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_POST, true);
        curl_setopt($ch, CURLOPT_POSTFIELDS, $data);
        curl_setopt($ch, CURLOPT_HTTPHEADER, $header);


        $response = curl_exec($ch);
        curl_close($ch);

        return json_decode($response, true);
    }

    public function requestPut(String $endpoint, String $data)
    {
        $ch = curl_init($this->apiEndpoints[$endpoint]);

        curl_setopt($ch, CURLOPT_CUSTOMREQUEST, 'PUT');
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_POSTFIELDS, $data);
        curl_setopt($ch, CURLOPT_HTTPHEADER, [
            'Content-Type: application/json',
            //'Content-Length: ' . strlen($data),
            'Authorization: Bearer ' . $_SESSION['token']
        ]);

        $response = curl_exec($ch);
        curl_close($ch);

        return json_decode($response, true);
    }

    public function requestDelete(String $endpoint)
    {
        $ch = curl_init($this->apiEndpoints[$endpoint]);

        curl_setopt($ch, CURLOPT_CUSTOMREQUEST, 'DELETE');
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_HTTPHEADER, [
            'Accept: application/json',
            'Authorization: Bearer ' . $_SESSION['token']
        ]);

        $response = curl_exec($ch);
        curl_close($ch);

        return json_decode($response, true);
    }
}