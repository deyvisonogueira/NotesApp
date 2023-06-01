package br.edu.ifsuldeminas.mch.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import io.realm.Realm;


public class AddNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        EditText tituloInput = findViewById(R.id.tituloInput);
        EditText descricaoInput = findViewById(R.id.descricaoInput);
        MaterialButton salvarBtn = findViewById(R.id.salvarbtn);

        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        salvarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titulo = tituloInput.getText().toString();
                String descricao = descricaoInput.getText().toString();
                long criarHora = System.currentTimeMillis();

                realm.beginTransaction();
                Note note = realm.createObject(Note.class);
                note.setTitulo(titulo);
                note.setDescricao(descricao);
                note.setCriarHora(criarHora);
                realm.commitTransaction();
                Toast.makeText(getApplicationContext(),"Note saved",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}