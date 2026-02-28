package com.example.projetws;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddEtudiant extends AppCompatActivity implements View.OnClickListener {

    private EditText lastName, firstName;
    private Spinner citySpinner;
    private RadioButton maleRadio, femaleRadio;
    private Button addButton;

    private RequestQueue requestQueue;

    private static final String INSERT_URL = "http://10.0.2.2/projet/ws/createEtudiant.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_etudiant);

        lastName = findViewById(R.id.lastName);
        firstName = findViewById(R.id.firstName);
        citySpinner = findViewById(R.id.citySpinner);
        maleRadio = findViewById(R.id.maleRadio);
        femaleRadio = findViewById(R.id.femaleRadio);
        addButton = findViewById(R.id.addButton);


        maleRadio.setChecked(true);

        requestQueue = Volley.newRequestQueue(this);
        addButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.addButton) {
            envoyerEtudiant();
        }
    }

    private void envoyerEtudiant() {
        String nom = lastName.getText().toString().trim();
        String prenom = firstName.getText().toString().trim();

        if (nom.isEmpty() || prenom.isEmpty()) {
            Toast.makeText(this, "Remplis Nom et Prenom", Toast.LENGTH_SHORT).show();
            return;
        }

        String sexe = maleRadio.isChecked() ? "homme" : "femme";
        String ville = citySpinner.getSelectedItem().toString();

        StringRequest request = new StringRequest(
                Request.Method.POST,
                INSERT_URL,
                response -> {
                    Log.d("RESPONSE", response);

                    try {
                        Type type = new TypeToken<List<Etudiant>>() {}.getType();
                        List<Etudiant> etudiants = new Gson().fromJson(response, type);

                        if (etudiants != null) {
                            for (Etudiant e : etudiants) {
                                Log.d("ETUDIANT", e.toString());
                            }
                        }

                        Toast.makeText(this, "Ajout OK", Toast.LENGTH_SHORT).show();


                        lastName.setText("");
                        firstName.setText("");
                        maleRadio.setChecked(true);
                        citySpinner.setSelection(0);


                        startActivity(new Intent(AddEtudiant.this, ListEtudiantsActivity.class));

                    } catch (Exception ex) {
                        Log.e("GSON", "Erreur parsing JSON: " + ex.getMessage());
                        Toast.makeText(this, "Ajout OK mais JSON invalide", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    Log.e("VOLLEY", "Erreur: " + error.toString());
                    Toast.makeText(this, "Erreur lors de l'ajout", Toast.LENGTH_SHORT).show();
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("nom", nom);
                params.put("prenom", prenom);
                params.put("ville", ville);
                params.put("sexe", sexe);
                return params;
            }
        };

        requestQueue.add(request);
    }
}