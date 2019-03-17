package commandExec.command;

import model.PriceList;
import model.PriceList.State;
import model.Product;
import commandExec.command.generic.Executor;

import java.time.LocalDate;

public class DemandExecutor extends Executor {


    public void execute(String[] args) {
        Product product = new Product(args[1]);
        LocalDate date = LocalDate.parse(args[4], formatter);

        Long acceptanceCount = priceListService.getParamCount(date, State.ACCEPTANCE, product);
        acceptanceCount = acceptanceCount == null ? 0 : acceptanceCount;

        Long shipmentCount = priceListService.getParamCount(null, State.SHIPMENT, product);
        shipmentCount = shipmentCount == null ? 0 : shipmentCount;

        if (!productService.contains(product.getName())
                || acceptanceCount < shipmentCount + Integer.parseInt(args[2])) {
            System.out.println("ERROR");
            return;
        }

        PriceList priceList = PriceList.builder()
                .product(product)
                .count(Integer.parseInt(args[2]))
                .price(Integer.parseInt(args[3]))
                .date(date)
                .state(State.SHIPMENT.name())
                .build();
        priceListService.save(priceList);
        System.out.println("OK");
    }
}
