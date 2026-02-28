<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    include_once '../service/EtudiantService.php';

    $id = $_POST['id'] ?? null;
    if ($id == null) { http_response_code(400); echo json_encode(["error"=>"id missing"]); exit; }

    $es = new EtudiantService();
    $es->deleteById($id);

    header('Content-Type: application/json');
    echo json_encode($es->findAllApi());
}