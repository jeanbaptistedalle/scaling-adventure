package dessin.collaboratif.view.component.button;

//~--- non-JDK imports --------------------------------------------------------

import dessin.collaboratif.controller.component.MoveDialMouseAdapter;
import dessin.collaboratif.controller.component.button.ScaleButtonListener;
import dessin.collaboratif.misc.ScaleEnum;

//~--- JDK imports ------------------------------------------------------------

import javax.swing.JToggleButton;

public class ScaleButton extends JToggleButton {

    /**
     *
     */
    private static final long serialVersionUID = 167490277775938940L;
    private ScaleEnum         scale;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public ScaleButton(ScaleEnum dir) {
        super(dir.toString());
        this.setFocusable(false);
        this.setScale(dir);
        this.setActionCommand(dir.toString());
        this.addActionListener(new ScaleButtonListener());
        this.addMouseListener(new MoveDialMouseAdapter());
        this.addMouseMotionListener(new MoveDialMouseAdapter());
        this.setVisible(true);

//      this.setPreferredSize(new Dimension (40,50));
    }

    public ScaleEnum getScale() {
        return scale;
    }

    public void setScale(ScaleEnum scale) {
        this.scale = scale;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
