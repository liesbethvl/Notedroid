package ehb.be.notedroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

import ehb.be.notedroid.model.Note;
import ehb.be.notedroid.model.NoteDAO;

public class AddActivity extends AppCompatActivity {

    private TextView tvTitle, tvContent;
    private EditText etTitle, etContent;
    private Button saveButton;
    private Button deleteButton;

    Note passedNote;

    public View.OnClickListener saveListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String title = etTitle.getText().toString();
            String content = etContent.getText().toString();
            Date dateCreated = new Date();
            Date dateModified = new Date();

            if (passedNote == null){
                Note newNote = new Note(title, content,dateCreated, dateModified);
                NoteDAO.getInstance().noteList.add(newNote);
            } else {
                int id = passedNote.getId();
                for (Note loopNote : NoteDAO.getInstance().getNoteList()) {
                    if (id == loopNote.getId()){
                        loopNote.setTitle(title);
                        loopNote.setContent(content);
                        loopNote.setDateCreated(dateCreated);
                    }
                }
            }

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

        }
    };

    public View.OnClickListener deleteListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (passedNote != null) {
                int id = passedNote.getId();
                Note noteToRemove = null;
                for (Note loopNote : NoteDAO.getInstance().getNoteList()) {
                    if (id == loopNote.getId()) {
                        noteToRemove =loopNote;

                        break;
                    }
                }
                NoteDAO.getInstance().noteList.remove(noteToRemove);
            }

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        tvTitle = findViewById(R.id.tv_lbl_title);
        tvContent = findViewById(R.id.tv_lbl_content);
        etTitle = findViewById(R.id.et_title);
        etContent = findViewById(R.id.et_content);
        saveButton = findViewById(R.id.btn_save);
        deleteButton = findViewById(R.id.btn_delete);

        deleteButton.setOnClickListener(deleteListener);
        saveButton.setOnClickListener(saveListener);
        passedNote = (Note) getIntent().getSerializableExtra("note");

        if(passedNote != null){
            etTitle.setText(passedNote.getTitle());
            etContent.setText(passedNote.getContent());
            deleteButton.setText("Delete");
        } else {
            deleteButton.setText("Cancel");
        }




    }
}
