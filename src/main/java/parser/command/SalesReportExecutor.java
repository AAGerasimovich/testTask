package parser.command;

import model.PriceList;
import model.PriceList.State;
import model.Product;
import parser.command.generic.Executor;

import java.time.LocalDate;
import java.util.List;

public class SalesReportExecutor extends Executor {

    public void execute(String[] args) {

        LocalDate date = LocalDate.parse(args[2], formatter);
        Product product = new Product(args[1]);

        Long acceptanceCount = priceListService.getParamCount(date, State.ACCEPTANCE, product);
        acceptanceCount = acceptanceCount == null ? 0 : acceptanceCount;

        if (!productService.contains(product.getName())
                || acceptanceCount == 0) {
            System.out.println("ERROR");
            return;
        }

        List<PriceList> list = priceListService.getListByDateAndProduct(date, product);

        Long shipmentCount = priceListService.getParamCount(date, State.SHIPMENT, product);
        shipmentCount = shipmentCount == null ? 0 : shipmentCount;

        Integer totalSoldAt = list.stream()
                .filter(pl -> pl.getState().equals(State.SHIPMENT.name()))
                .reduce(0, (x, y) -> x + y.getCount() * y.getPrice(),
                        (x, y) -> x + y);

        int cnt = 0;
        int cost = 0;
        for (PriceList pl : list) {
            if (cnt < shipmentCount || pl.getState().equals(State.ACCEPTANCE.name())) {
                if (cnt + pl.getCount() < shipmentCount) {
                    cost += pl.getCount() * pl.getPrice();
                    cnt += pl.getCount();
                } else {
                    cost += (cnt + pl.getCount() - shipmentCount) * pl.getPrice();
                    cnt += cnt + pl.getCount() - shipmentCount;
                }
            } else {
                break;
            }
        }

        System.out.println(totalSoldAt - cost);
    }
}
