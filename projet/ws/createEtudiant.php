<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {

    include_once '../service/EtudiantService.php';

    
    $lastName  = $_POST['nom'] ?? '';
    $firstName = $_POST['prenom'] ?? '';
    $city      = $_POST['ville'] ?? '';
    $gender    = $_POST['sexe'] ?? '';

    $es = new EtudiantService();

   
    $es->create(new Etudiant(null, $lastName, $firstName, $city, $gender));

    header('Content-Type: application/json');
    echo json_encode($es->findAllApi());
}
?>