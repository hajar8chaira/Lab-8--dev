<?php

include_once '../service/EtudiantService.php';

header('Content-Type: application/json');

$es = new EtudiantService();

echo json_encode($es->findAllApi());

?>