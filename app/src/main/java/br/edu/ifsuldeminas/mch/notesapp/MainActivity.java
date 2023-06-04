package br.edu.ifsuldeminas.mch.notesapp;

import static br.edu.ifsuldeminas.mch.notesapp.R.id.startButton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = findViewById(R.id.animatedImage);
        Glide.with(this)
                .asGif()
                .load(R.drawable.giphy)
                .into(imageView);

        Button addNoteButton = findViewById(R.id.startButton);

        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,NoteActivity.class));
            }
        });
    }
}

