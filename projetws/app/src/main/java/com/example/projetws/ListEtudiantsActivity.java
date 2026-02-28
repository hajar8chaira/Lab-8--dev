package com.example.projetws;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.content.Intent;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListEtudiantsActivity extends AppCompatActivity {

    private static final String URL_LIST   = "http://10.0.2.2/projet/ws/loadEtudiant.php";
    private static final String URL_DELETE = "http://10.0.2.2/projet/ws/deleteEtudiant.php";
    private static final String URL_UPDATE = "http://10.0.2.2/projet/ws/updateEtudiant.php";

    private RequestQueue queue;
    private RecyclerView recycler;
    private final List<Etudiant> etudiants = new ArrayList<>();
    private EtudiantAdapter adapter;
    private Button btnBackAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_etudiants);
        btnBackAdd = findViewById(R.id.btnBackAdd);

        btnBackAdd.setOnClickListener(v -> {
            Intent i = new Intent(ListEtudiantsActivity.this, AddEtudiant.class);
            startActivity(i);
            finish();
        });
        queue = Volley.newRequestQueue(this);

        recycler = findViewById(R.id.recyclerEtudiants);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        adapter = new EtudiantAdapter(etudiants, this::showActionsDialog);
        recycler.setAdapter(adapter);

        loadEtudiants();
    }

    private void loadEtudiants() {
        StringRequest req = new StringRequest(Request.Method.GET, URL_LIST,
                response -> {
                    Log.d("RESPONSE", response);

                    Type type = new TypeToken<List<Etudiant>>(){}.getType();
                    List<Etudiant> list = new Gson().fromJson(response, type);

                    etudiants.clear();
                    etudiants.addAll(list);
                    adapter.notifyDataSetChanged();
                },
                error -> Toast.makeText(this, "Erreur load: " + error, Toast.LENGTH_SHORT).show()
        );
        queue.add(req);
    }

    private void showActionsDialog(Etudiant e) {
        String[] actions = {"Modifier", "Supprimer"};

        new AlertDialog.Builder(this)
                .setTitle(e.getNom() + " " + e.getPrenom())
                .setItems(actions, (dialog, which) -> {
                    if (which == 0) showEditDialog(e);
                    else confirmDelete(e);
                })
                .show();
    }

    private void confirmDelete(Etudiant e) {
        new AlertDialog.Builder(this)
                .setTitle("Confirmation")
                .setMessage("Supprimer cet étudiant ?")
                .setPositiveButton("Oui", (d, w) -> deleteEtudiant(e.getId()))
                .setNegativeButton("Non", null)
                .show();
    }

    private void deleteEtudiant(int id) {
        StringRequest req = new StringRequest(Request.Method.POST, URL_DELETE,
                response -> {
                    Toast.makeText(this, "Supprimé", Toast.LENGTH_SHORT).show();
                    loadEtudiants();
                },
                error -> Toast.makeText(this, "Erreur delete: " + error, Toast.LENGTH_SHORT).show()
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(id));
                return params;
            }
        };
        queue.add(req);
    }

    private void showEditDialog(Etudiant e) {
        View v = LayoutInflater.from(this).inflate(R.layout.dialog_edit_etudiant, null);

        EditText edtNom = v.findViewById(R.id.edtNom);
        EditText edtPrenom = v.findViewById(R.id.edtPrenom);
        Spinner spVille = v.findViewById(R.id.spVille);
        EditText edtSexe = v.findViewById(R.id.edtSexe);

        edtNom.setText(e.getNom());
        edtPrenom.setText(e.getPrenom());
        edtSexe.setText(e.getSexe());

        new AlertDialog.Builder(this)
                .setTitle("Modifier")
                .setView(v)
                .setPositiveButton("Enregistrer", (d, w) -> {
                    updateEtudiant(
                            e.getId(),
                            edtNom.getText().toString(),
                            edtPrenom.getText().toString(),
                            spVille.getSelectedItem().toString(),
                            edtSexe.getText().toString()
                    );
                })
                .setNegativeButton("Annuler", null)
                .show();
    }

    private void updateEtudiant(int id, String nom, String prenom, String ville, String sexe) {
        StringRequest req = new StringRequest(Request.Method.POST, URL_UPDATE,
                response -> {
                    Toast.makeText(this, "Modifié", Toast.LENGTH_SHORT).show();
                    loadEtudiants();
                },
                error -> Toast.makeText(this, "Erreur update: " + error, Toast.LENGTH_SHORT).show()
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(id));
                params.put("nom", nom);
                params.put("prenom", prenom);
                params.put("ville", ville);
                params.put("sexe", sexe);
                return params;
            }
        };
        queue.add(req);
    }
}