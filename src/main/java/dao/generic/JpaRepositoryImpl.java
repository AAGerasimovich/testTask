package dao.generic;

import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public class JpaRepositoryImpl<T, PK extends Serializable>
        implements JpaRepository<T, PK> {
    private Class<T> entityClass;

    public JpaRepositoryImpl() {
        Session initSession = HibernateUtil.getSessionFactory().openSession();
        initSession.close();
        ParameterizedType genericSuperclass = (ParameterizedType) getClass()
                .getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass
                .getActualTypeArguments()[0];
    }

    @Override
    public T create(T t) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(t);
        tx.commit();
        session.close();
        return t;
    }

    @Override
    public Boolean contains(PK id) {
        return HibernateUtil.getSessionFactory().openSession().get(entityClass, id) != null;
    }
}