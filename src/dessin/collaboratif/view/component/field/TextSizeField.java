package dessin.collaboratif.view.component.field;

import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import dessin.collaboratif.model.Client;

public class TextSizeField extends JSpinner{

	/**
	 * 
	 */
	private static final long serialVersionUID = -23958304697115492L;

	public TextSizeField() {
		super();
		SpinnerModel model = new SpinnerNumberModel(12,0,100,1);
		setModel(model);
		
		SpinnerModel mod = getModel();
		mod.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				Client.getInstance().setSizeTextToInsert(
						getModel().getValue().toString());
			}
		});
	}

}
