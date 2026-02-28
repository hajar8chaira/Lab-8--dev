<?php

class Etudiant {

    private $id;
    private $lastName;   
    private $firstName;  
    private $city;      
    private $gender;     

    public function __construct($id = null, $lastName = "", $firstName = "", $city = "", $gender = "") {
        $this->id = $id;
        $this->lastName = $lastName;
        $this->firstName = $firstName;
        $this->city = $city;
        $this->gender = $gender;
    }

    // Getters
    public function getId() { return $this->id; }
    public function getLastName() { return $this->lastName; }
    public function getFirstName() { return $this->firstName; }
    public function getCity() { return $this->city; }
    public function getGender() { return $this->gender; }

    // Setters
    public function setId($id) { $this->id = $id; }
    public function setLastName($lastName) { $this->lastName = $lastName; }
    public function setFirstName($firstName) { $this->firstName = $firstName; }
    public function setCity($city) { $this->city = $city; }
    public function setGender($gender) { $this->gender = $gender; }
}