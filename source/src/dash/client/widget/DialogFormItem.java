package dash.client.widget;

import com.google.gwt.user.client.ui.Label;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;

public class DialogFormItem extends Dialog{
	
	public DialogFormItem() {
		
		this.setHeading("Dialog — Simple");
		this.setWidth(300);
		this.setResizable(false);
		this.setHideOnButtonClick(true);
		this.setPredefinedButtons(PredefinedButton.YES, PredefinedButton.NO);
		this.setBodyStyleName("pad-text");
		this.getBody().addClassName("pad-text");
		this.add(new Label("Ola fio"));
		
		
		
	}

	
}
