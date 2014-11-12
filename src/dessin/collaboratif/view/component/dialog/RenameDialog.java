package dessin.collaboratif.view.component.dialog;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.TextArea;
import java.awt.Window;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

import javax.swing.JDialog;
import javax.swing.JPanel;

import dessin.collaboratif.misc.DirectionEnum;
import dessin.collaboratif.model.Client;
import dessin.collaboratif.view.component.button.MoveButton;

public class RenameDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1151343403574524760L;
	
	private TextArea text;

	public RenameDialog() {
		super();
		fill();
	}

	public RenameDialog(Frame owner) {
		super(owner);
		fill();
	}

	public RenameDialog(Dialog owner) {
		super(owner);
		fill();
	}

	public RenameDialog(Window owner) {
		super(owner);
		fill();
	}

	public RenameDialog(Frame owner, boolean modal) {
		super(owner, modal);
		fill();
	}

	public RenameDialog(Frame owner, String title) {
		super(owner, title);
		fill();
	}

	public RenameDialog(Dialog owner, boolean modal) {
		super(owner, modal);
		fill();
	}

	public RenameDialog(Dialog owner, String title) {
		super(owner, title);
		fill();
	}

	public RenameDialog(Window owner, ModalityType modalityType) {
		super(owner, modalityType);
		fill();
	}

	public RenameDialog(Window owner, String title) {
		super(owner, title);
		fill();
	}

	public RenameDialog(Frame arg0, String arg1, boolean arg2) {
		super(arg0, arg1, arg2);
		fill();
	}

	public RenameDialog(Dialog owner, String title, boolean modal) {
		super(owner, title, modal);
		fill();
	}

	public RenameDialog(Window owner, String title, ModalityType modalityType) {
		super(owner, title, modalityType);
		fill();
	}

	public RenameDialog(Frame arg0, String arg1, boolean arg2,
			GraphicsConfiguration arg3) {
		super(arg0, arg1, arg2, arg3);
		fill();
	}

	public RenameDialog(Dialog owner, String title, boolean modal,
			GraphicsConfiguration gc) {
		super(owner, title, modal, gc);
		fill();
	}

	public RenameDialog(Window owner, String title, ModalityType modalityType,
			GraphicsConfiguration gc) {
		super(owner, title, modalityType, gc);
		fill();
	}

	private void fill() {
		int i = Client.getInstance().getSelected();
		String txt = Client.getInstance().getImage().getDocumentElement().getChildNodes().item(i).getTextContent();
		text = new TextArea(txt);
		
		text.addTextListener(new TextListener() {
			
			@Override
			public void textValueChanged(TextEvent arg0) {
				Client.getInstance().rename(text.getText());				
			}
		});

		JPanel pan = new JPanel(new BorderLayout());
		
		pan.add(text);
		
		add(pan);
		
		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(250,250);
		setVisible(true);
		setResizable(false);
//		this.setSize(new Dimension (250,250));
//		((JPanel)this.getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	}

}
