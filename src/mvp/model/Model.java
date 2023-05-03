package mvp.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Model<T> implements DAO<T>{

    private List<T> ldatas = new ArrayList<>();

    @Override
    public T add(T elt)
    {
        boolean present = ldatas.contains(elt);
        if(!present)
        {
            ldatas.add(elt);
            return elt;
        } else return null;
    }
}
