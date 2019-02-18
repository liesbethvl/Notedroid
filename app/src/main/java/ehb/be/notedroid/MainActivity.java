package ehb.be.notedroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import ehb.be.notedroid.model.Note;
import ehb.be.notedroid.model.NoteDAO;
import ehb.be.notedroid.recyclerUtilities.NoteAdapter;
import ehb.be.notedroid.recyclerUtilities.RecyclerTouchListener;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvNotes;
    private NoteAdapter adapter;
    private Button editButton;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mi_add) {
            //start navigatie
            Intent intent = new Intent(this, AddActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //verwijzing naar recycler
        rvNotes = findViewById(R.id.rv_notes);

        //hoe recycler opvullen mbv adapter
        adapter = new NoteAdapter(NoteDAO.getInstance().getNoteList());
        rvNotes.setAdapter(adapter);

        //hoe elementen weergeven
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvNotes.setLayoutManager(layoutManager);


        rvNotes.addOnItemTouchListener(new RecyclerTouchListener(this, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);

                Note selectedNote = NoteDAO.getInstance().getNoteList().get(position);

                intent.putExtra("note", selectedNote);
                startActivity(intent);

            }
        }));

    }
}
