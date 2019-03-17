package parser.command;

import model.PriceList;
import model.PriceList.State;
import model.Product;
import parser.command.generic.Executor;

import java.time.LocalDate;

public class PurchaseExecutor extends Executor {

    public void execute(String[] args) {

        Product product = new Product(args[1]);

        if (!productService.contains(product.getName())) {
            System.out.println("ERROR");
            return;
        }

        PriceList priceList = PriceList.builder()
                .count(Integer.parseInt(args[2]))
                .price(Integer.parseInt(args[3]))
                .date(LocalDate.parse(args[4], formatter))
                .state(State.ACCEPTANCE.name())
                .product(product)
                .build();
        priceListService.save(priceList);
        System.out.println("OK");
    }
}
