package com.example.simplenotesapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddEditNoteActivity extends AppCompatActivity {

    private EditText editTitle,editDescription;
    private NumberPicker numberPicker;
    public static final String EXTRA_ID="com.example.simplenotesapplication.EXTRA_ID";
    public static final String EXTRA_TITLE="com.example.simplenotesapplication.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION="com.example.simplenotesapplication.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY="com.example.simplenotesapplication.EXTRA_PRIORITY";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTitle=findViewById(R.id.edit_text_title);
        editDescription=findViewById(R.id.edit_text_description);
        numberPicker=findViewById(R.id.number_picker);

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_close_24);
        Intent intent= getIntent();
        if(intent.hasExtra(EXTRA_ID))
        {
            setTitle("Edit Note");
            editTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            numberPicker.setValue(intent.getIntExtra(EXTRA_PRIORITY,1));
        }
        else {
            setTitle("Add Note");
        }
    }

    private void saveNote()
    {
        String title= editTitle.getText().toString();
        String description=editDescription.getText().toString();
        int priority= numberPicker.getValue();

        if(title.trim().isEmpty() || description.trim().isEmpty())
        {
            Toast.makeText(this,"please insert a title and description",Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data= new Intent();
        data.putExtra(EXTRA_TITLE,title);
        data.putExtra(EXTRA_DESCRIPTION,description);
        data.putExtra(EXTRA_PRIORITY,priority);

        int id= getIntent().getIntExtra(EXTRA_ID,-1);
        if(id != -1 )
        {
            data.putExtra(EXTRA_ID,id);
        }
        setResult(RESULT_OK,data);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}