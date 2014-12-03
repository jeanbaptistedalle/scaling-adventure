package dessin.collaboratif.view.component.dialog;

import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

import javax.swing.JDialog;
import javax.swing.JPanel;

import dessin.collaboratif.model.Client;

public class RenameDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1151343403574524760L;

	public static RenameDialog INSTANCE = null;

	private TextArea text;

	private RenameDialog() {
		super();
		setModal(true);
		setAlwaysOnTop(true);
		setModalityType(ModalityType.APPLICATION_MODAL);
		text = new TextArea("");
		JPanel pan = new JPanel(new BorderLayout());

		pan.add(text);

		add(pan);

		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(250, 250);
		setResizable(false);
		text.addTextListener(new TextListener() {

			@Override
			public void textValueChanged(TextEvent arg0) {
				Client.getInstance().rename(text.getText());
			}
		});
		fill();
	}

	public static RenameDialog getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new RenameDialog();
		}
		return INSTANCE;
	}
	
	@Override
	public void setVisible(boolean b)
	{
		fill();
		super.setVisible(true);
	}

	private void fill() {
		int i = Client.getInstance().getSelected();
		String txt = Client.getInstance().getNode(i).getTextContent();
		text.setText(txt);

		// this.setSize(new Dimension (250,250));
		// ((JPanel)this.getContentPane()).setBorder(BorderFactory.createEmptyBorder(10,
		// 10, 10, 10));
	}

}
