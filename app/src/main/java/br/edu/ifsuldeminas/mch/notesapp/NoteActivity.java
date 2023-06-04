package br.edu.ifsuldeminas.mch.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;

public class NoteActivity extends AppCompatActivity {


    @Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Realm.init(this);
    setContentView(R.layout.activity_notes);

    MaterialButton addNoteButton = findViewById(R.id.addnewnotebtn);



    addNoteButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(NoteActivity.this,AddNoteActivity.class));
        }
    });

    Realm.init(getApplicationContext());
    Realm realm = Realm.getDefaultInstance();

    RealmResults<Note> noteList = realm.where(Note.class).sort("criarHora", Sort.DESCENDING).findAll();

    RecyclerView recyclerView = findViewById(R.id.recyclerview);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    MyAdapter myAdapter = new MyAdapter(getApplicationContext(),noteList);
    recyclerView.setAdapter(myAdapter);

    noteList.addChangeListener(new RealmChangeListener<RealmResults<Note>>() {
        @Override
        public void onChange(RealmResults<Note> notes) {
            myAdapter.notifyDataSetChanged();

        }
    });
}
}
