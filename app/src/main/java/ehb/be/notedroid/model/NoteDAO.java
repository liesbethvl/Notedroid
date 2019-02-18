package ehb.be.notedroid.model;

import java.util.ArrayList;

public class NoteDAO {

    private static final NoteDAO ourInstance = new NoteDAO();
    public ArrayList<Note> noteList = new ArrayList<Note>();

    public static NoteDAO getInstance() {
        return ourInstance;
    }

    private NoteDAO() {
    }

    public void addNote (Note newNote){
        noteList.add(newNote);
    }

    public void removeNote (int id) {
        Note noteToBeRemoved = noteList.get(id);
        noteList.remove(noteToBeRemoved);
    }

    public ArrayList<Note> getNoteList (){
        return noteList;
    }

}
