package parser.command;


import model.Product;
import parser.command.generic.Executor;


public class NewProductExecutor extends Executor {

    public void execute(String[] args) {
        Product product = new Product(args[1]);

        if (productService.contains(product.getName())) {
            System.out.println("ERROR");
        } else {
            productService.save(product);
            System.out.println("OK");
        }

    }
}
