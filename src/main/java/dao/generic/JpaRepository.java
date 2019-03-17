package dao.generic;

import java.io.Serializable;

public interface JpaRepository<T, PK extends Serializable> {
    T create(T t);

    Boolean contains(PK id);

}