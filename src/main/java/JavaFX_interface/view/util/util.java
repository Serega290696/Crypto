package JavaFX_interface.view.util;

import JavaFX_interface.view.MenuController;
import data_base.data_access_objects.NotesDAO;
import data_base.entities.Note;

/**
 * Created by Вова on 29.08.2015.
 */
public class util {

    NotesDAO notesDao = new NotesDAO();

    public boolean addToDataBase(String title, String value) {
        Note newNote = new Note();
        newNote.setTitle(title);
        newNote.setValue(value);
        newNote.setIdUser(MenuController.getCurrentUser().getId());
        if (isValidate(newNote))
            notesDao.add(newNote);
        return true;
    }

    private boolean isValidate(Note note) {
        if (
                note.getIdUser() != 0 &&
                        note.getTitle().length() > 0 &&
                        note.getValue().length() > 0
                )
            return true;
        return false;
    }
}
