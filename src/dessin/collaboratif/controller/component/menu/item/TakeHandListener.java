/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dessin.collaboratif.controller.component.menu.item;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import reseau.client.ClientNetwork;

/**
 *
 * @author kevin
 */
public class TakeHandListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent arg0) {
            ClientNetwork.getInstance().requestControl();
    }
}
