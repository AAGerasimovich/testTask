package dao;

import dao.generic.JpaRepositoryImpl;
import model.PriceList;
import model.Product;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.time.LocalDate;
import java.util.List;

public class PriceListDao extends JpaRepositoryImpl<PriceList, Integer> {

    public Long getParamCount(LocalDate localDate, PriceList.State state, Product product) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String date = ":dateParam ";
        if (localDate == null) {
            date = "( select max(date) from PriceList )";
        }
        Query query = session.createQuery(
                " select sum(PL.count) from PriceList as PL " +
                "where state = :stateParam " +
                "and date <= " + date +
                "and product = :productParam")
                .setParameter("stateParam", state.name())
                .setParameter("productParam", product);
        if (localDate != null) {
            query.setParameter("dateParam", localDate);
        }
        List<Long> count = query.list();
        session.close();
        return count.get(0);
    }

    public List<PriceList> getListByDateAndProduct(LocalDate localDate, Product product) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<PriceList> list = session.createQuery("from PriceList as PL " +
                "where date <= :dateParam " +
                "and product = :productParam")
                .setParameter("dateParam", localDate)
                .setParameter("productParam", product)
                .list();
        session.close();
        return list;
    }
}

