package parser.command.generic;

import services.PriceListService;
import services.ProductService;

import java.time.format.DateTimeFormatter;

public abstract class Executor {

    protected static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    protected PriceListService priceListService = new PriceListService();
    protected ProductService productService = new ProductService();

    public abstract void execute(String[] args);

}
