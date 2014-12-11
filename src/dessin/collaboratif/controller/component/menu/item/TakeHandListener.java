
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package dessin.collaboratif.controller.component.menu.item;

//~--- non-JDK imports --------------------------------------------------------

import java.awt.event.ActionListener;

import reseau.client.ClientNetwork;
//~--- JDK imports ------------------------------------------------------------
import java.awt.event.ActionEvent;

/**
 *
 * @author kevin
 */
public class TakeHandListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (!ClientNetwork.getInstance().hasRqstdCtrl())
            ClientNetwork.getInstance().requestControl();
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
