package dessin.collaboratif.controller.component;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import reseau.client.ClientNetwork;

public class FrameListener extends WindowAdapter {

	@Override
	public void windowClosing(WindowEvent windowEvent) {
		ClientNetwork.getInstance().disconnect();
		System.exit(0);
	}
}
