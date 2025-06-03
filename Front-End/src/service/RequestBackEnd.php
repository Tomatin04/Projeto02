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

    public function requestGet(String $endpoint, int $id = null)
    {
        if($id != null){
            $ch = curl_init($this->apiEndpoints[$endpoint] . '/' . $id);
        }else{
            $ch = curl_init($this->apiEndpoints[$endpoint]);
        }
        
        $header = ['Accept: application/json',];

        if (isset($_SESSION['token'])) $header[] = 'Authorization: Bearer ' . $_SESSION['token']['token'];

        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_HTTPHEADER, $header);

        $response = curl_exec($ch);
        $_SESSION['HttpStatus'] = curl_getinfo($ch, CURLINFO_HTTP_CODE); 
        curl_close($ch);

        return json_decode($response, true);
    }

    public function requestPost(String $endpoint, String $data = null)
    {
        $ch = curl_init($this->apiEndpoints[$endpoint]);
        
        $header = [
            'Accept: application/json',
            'Content-Type: application/json',
        ];

        if (isset($_SESSION['token'])) $header[] = 'Authorization: Bearer ' . $_SESSION['token']['token'];

        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_POST, true);
        if($data != null)curl_setopt($ch, CURLOPT_POSTFIELDS, $data);
        curl_setopt($ch, CURLOPT_HTTPHEADER, $header);

        $response = curl_exec($ch);
        $_SESSION['HttpStatus'] = curl_getinfo($ch, CURLINFO_HTTP_CODE); 
        curl_close($ch);

        return json_decode($response, true);
    }

    public function requestPut(String $endpoint, String $data)
    {
        $ch = curl_init($this->apiEndpoints[$endpoint]);

        $header = [
            'Accept: application/json',
            'Content-Type: application/json',
        ];

        if (isset($_SESSION['token'])) $header[] = 'Authorization: Bearer ' . $_SESSION['token']['token'];

        curl_setopt($ch, CURLOPT_CUSTOMREQUEST, 'PUT');
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_POSTFIELDS, $data);
        curl_setopt($ch, CURLOPT_HTTPHEADER, $header);

        $response = curl_exec($ch);
        $_SESSION['HttpStatus'] = curl_getinfo($ch, CURLINFO_HTTP_CODE); 
        curl_close($ch);

        return json_decode($response, true);
    }

    public function requestDelete(String $endpoint, String $data = null)
    {
        $ch = curl_init($this->apiEndpoints[$endpoint]);

        $header = [
            'Accept: application/json',
            'Content-Type: application/json',
        ];

        if (isset($_SESSION['token'])) $header[] = 'Authorization: Bearer ' . $_SESSION['token']['token'];

        curl_setopt($ch, CURLOPT_CUSTOMREQUEST, 'DELETE');
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        if($data != null)curl_setopt($ch, CURLOPT_POSTFIELDS, $data);
        curl_setopt($ch, CURLOPT_HTTPHEADER, $header);

        $response = curl_exec($ch);
        $_SESSION['HttpStatus'] = curl_getinfo($ch, CURLINFO_HTTP_CODE); 
        curl_close($ch);

        return json_decode($response, true);
    }
}