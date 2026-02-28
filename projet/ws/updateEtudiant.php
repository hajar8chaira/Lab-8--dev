<?php
if ($_SERVER["REQUEST_METHOD"] === "POST") {
    require_once '../service/EtudiantService.php';

    $id     = $_POST['id'] ?? null;
    $nom    = trim($_POST['nom'] ?? '');
    $prenom = trim($_POST['prenom'] ?? '');
    $ville  = trim($_POST['ville'] ?? '');
    $sexe   = trim($_POST['sexe'] ?? '');

    if ($id === null || $nom === '' || $prenom === '' || $ville === '' || $sexe === '') {
        http_response_code(400);
        header('Content-Type: application/json');
        echo json_encode(["error" => "Champs manquants"]);
        exit;
    }

    $es = new EtudiantService();
    $es->updateById($id, $nom, $prenom, $ville, $sexe);

    header('Content-Type: application/json');
    echo json_encode($es->findAllApi());
}
?>