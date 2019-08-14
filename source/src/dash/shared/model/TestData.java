package dash.shared.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sencha.gxt.core.client.util.DateWrapper;

public class TestData {

	public static List<Item> getItens() {
		List<Item> itens = new ArrayList<Item>();

		itens.add(new Item(1, "HB 20", 1, randomDate(), randomDate()));
		itens.add(new Item(2, "Ap", 1, randomDate(), randomDate()));

		return itens;

	}

	private static Date randomDate() {
		DateWrapper w = new DateWrapper();
		int r = (int) (Math.random() * 10) * 10;
		w = w.addDays(-r);
		return w.asDate();
	}

}
