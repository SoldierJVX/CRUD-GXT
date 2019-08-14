package dash.shared.model;

import java.util.Date;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface ItemProperties extends PropertyAccess<Item> {

	@Path("id")
	ModelKeyProvider<Item> key();

	ValueProvider<Item, Integer> id();

	ValueProvider<Item, String> description();

	ValueProvider<Item, Integer> amount();

	ValueProvider<Item, Date> dateCreated();

	ValueProvider<Item, Date> dateUpdated();

	@Path("id")
	ModelKeyProvider<Item> idModel();

}
