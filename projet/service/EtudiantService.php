<?php

include_once '../classes/Etudiant.php';
include_once '../connexion/Connexion.php';
include_once '../dao/IDao.php';

class EtudiantService implements IDao {

    private $connexion;

    public function __construct() {
        $this->connexion = new Connexion();
    }

    public function create($o) {

        $query = "INSERT INTO Etudiant (nom, prenom, ville, sexe) 
                  VALUES (:nom, :prenom, :ville, :sexe)";

        $stmt = $this->connexion->getConnexion()->prepare($query);

        $stmt->execute([
            ':nom' => $o->getLastName(),    
            ':prenom' => $o->getFirstName(), 
            ':ville' => $o->getCity(),      
            ':sexe' => $o->getGender()      
        ]);
    }

    public function findAllApi() {
        $req = $this->connexion->getConnexion()->query("SELECT * FROM Etudiant");
        return $req->fetchAll(PDO::FETCH_ASSOC);
    }
    public function deleteById($id) {
        $query = "DELETE FROM Etudiant WHERE id = :id";
        $stmt = $this->connexion->getConnexion()->prepare($query);
        $stmt->execute([':id' => $id]);
}

    public function updateById($id, $nom, $prenom, $ville, $sexe) {
        $query = "UPDATE Etudiant 
                SET nom = :nom, prenom = :prenom, ville = :ville, sexe = :sexe 
                WHERE id = :id";
        $stmt = $this->connexion->getConnexion()->prepare($query);
        $stmt->execute([
            ':id' => $id,
            ':nom' => $nom,
            ':prenom' => $prenom,
            ':ville' => $ville,
            ':sexe' => $sexe
        ]);
}
    
    public function delete($o) {
        $this->deleteById($o->getId());
}

    public function update($o) {
        $this->updateById(
            $o->getId(),
            $o->getLastName(),
            $o->getFirstName(),
            $o->getCity(),
            $o->getGender()
    );
}
    public function findAll() {}
    public function findById($id) {}
}