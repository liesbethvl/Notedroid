package ehb.be.notedroid.recyclerUtilities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ehb.be.notedroid.R;
import ehb.be.notedroid.model.Note;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        public final TextView tvTitle, tvDate;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_Title);
            tvDate = itemView.findViewById(R.id.tv_date);
        }
    }

    private ArrayList<Note> noteList = new ArrayList<Note>();

    public NoteAdapter(ArrayList<Note> noteList) {
        this.noteList = noteList;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        View rowView = LayoutInflater.from(context).inflate(R.layout.recycler_item, viewGroup, false);
        return new NoteViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder noteViewHolder, int i) {
        Note noteForRow = noteList.get(i);
        //hier dateformatter insteken
        noteViewHolder.tvDate.setText(noteForRow.getDateCreated().toString());
        noteViewHolder.tvTitle.setText(noteForRow.getTitle());
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }







}
