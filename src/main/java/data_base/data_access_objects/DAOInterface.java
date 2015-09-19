package data_base.data_access_objects;

import java.util.List;

/**
 * Created by Serega on 24.07.2015.
 */
public interface DAOInterface<T> {


    public boolean add(T ob);

    public T get(T ob);
    public T get(long id);

    public void update(T ob);

    public void remove(T ob);

    public List<T> getAll();

}
