package lesson9.labs.prob8;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
	List<Optional<OrderItem>> orderItems = new ArrayList<>();
	public static void main(String[] args) {
		Main m = new Main();
		m.loadOrderItemData();
		System.out.println("Do any of these Order Items have an order of flowers? " +
		   m.findProduct("Flowers"));
	}

	private boolean findProduct(String prodName) {
		for(Optional<OrderItem> item : orderItems) {
			if(item.flatMap(x->x.getProduct())
					.flatMap(p->p.getProductName())
					.orElse("").equals(prodName))
				return true;
		}
		return false;
	}

	private void loadOrderItemData() {
		orderItems.add(Optional.of(new OrderItem(new Product("1016", "Tools", 131.00), 3)));
		orderItems.add(Optional.of(new OrderItem(new Product("1017", "Fishing Rod", 111.00), 1)));
		orderItems.add(Optional.of(new OrderItem(new Product("1018", "Game of Go",66.00), 2)));
		orderItems.add(Optional.of(new OrderItem(new Product("1019", "Flowers", 221.00), 5)));
	}
}
