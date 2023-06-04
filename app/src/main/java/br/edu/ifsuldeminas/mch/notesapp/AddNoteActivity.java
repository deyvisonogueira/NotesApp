package br.edu.ifsuldeminas.mch.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import io.realm.Realm;


public class AddNoteActivity extends AppCompatActivity {

    private static final String PREFS_FILE_NAME = "MyPrefsFile";
    private static final String PREFS_KEY_TITLE = "title";
    private static final String PREFS_KEY_DESCRIPTION = "description";

    private EditText tituloInput;
    private EditText descricaoInput;
    private MaterialButton salvarBtn;
    private ImageView backBtn;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        tituloInput = findViewById(R.id.tituloInput);
        descricaoInput = findViewById(R.id.descricaoInput);
        salvarBtn = findViewById(R.id.salvarbtn);
        backBtn = findViewById(R.id.back_button);

        sharedPreferences = getSharedPreferences(PREFS_FILE_NAME, MODE_PRIVATE);

        // Carregar os valores salvos nas SharedPreferences, se existirem
        String savedTitle = sharedPreferences.getString(PREFS_KEY_TITLE, "");
        String savedDescription = sharedPreferences.getString(PREFS_KEY_DESCRIPTION, "");
        tituloInput.setText(savedTitle);
        descricaoInput.setText(savedDescription);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddNoteActivity.this, NoteActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        salvarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titulo = tituloInput.getText().toString().trim();
                String descricao = descricaoInput.getText().toString().trim();

                if (titulo.isEmpty() || descricao.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                } else {
                    long criarHora = System.currentTimeMillis();

                    realm.beginTransaction();
                    Note note = realm.createObject(Note.class);
                    note.setTitulo(titulo);
                    note.setDescricao(descricao);
                    note.setCriarHora(criarHora);
                    realm.commitTransaction();

                    Toast.makeText(getApplicationContext(), "Nota Salva", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Salvar os valores digitados nas SharedPreferences ao pausar a atividade
        String title = tituloInput.getText().toString().trim();
        String description = descricaoInput.getText().toString().trim();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREFS_KEY_TITLE, title);
        editor.putString(PREFS_KEY_DESCRIPTION, description);
        editor.apply();
    }
}
