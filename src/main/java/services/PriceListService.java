package services;

import dao.PriceListDao;
import model.PriceList;
import model.Product;

import java.time.LocalDate;
import java.util.List;

public class PriceListService {
    private PriceListDao priceListDao = new PriceListDao();


    public Long getParamCount(LocalDate localDate, PriceList.State state, Product product) {
        return priceListDao.getParamCount(localDate, state, product);
    }

    public List<PriceList> getListByDateAndProduct(LocalDate localDate, Product product) {
        return priceListDao.getListByDateAndProduct(localDate, product);
    }

    public void save(PriceList priceList) {
        priceListDao.create(priceList);
    }

}
