package dessin.collaboratif.misc;

import java.awt.Dimension;



public class GeneralVariables {
	
	public final static String PROJECT_PATH = System.getProperty("user.dir")+"/";
	
	public final static String FILE_MENU_TITLE = "Fichier";
	
	public final static String FILE_MENU_NEW = "Nouveau"; 
	public final static String FILE_MENU_NEW_ICON_PATH = PROJECT_PATH+"ressources/icons/file_icon.png";
	
	public final static String FILE_MENU_OPEN = "Ouvrir"; 
	public final static String FILE_MENU_OPEN_ICON_PATH = PROJECT_PATH+"ressources/icons/open_icon.png";
	
	public final static String FILE_MENU_EXPORT = "Exporter";
	public final static String FILE_MENU_EXPORT_ICON_PATH = PROJECT_PATH+"ressources/icons/export_icon.png";
	
	public final static String FILE_MENU_CLOSE = "Fermer";
	public final static String FILE_MENU_CLOSE_ICON_PATH = PROJECT_PATH+"ressources/icons/close_icon.png";
	
	public final static String FILE_MENU_QUIT = "Quitter"; 
	public final static String FILE_MENU_QUIT_ICON_PATH = PROJECT_PATH+"ressources/icons/exit_icon.png";

	public final static String EDITION_MENU_TITLE = "Edition"; 
	
	public final static String EDITION_MENU_UNDO = "Annuler";
	public final static String EDITION_MENU_UNDO_ICON_PATH = PROJECT_PATH+"ressources/icons/undo_icon.png";
	
	public final static String COLLABORATION_MENU_TITLE = "Collaboration";
	
	public final static String COLLABORATION_MENU_TAKE_HAND = "Prendre la main";
	public final static String COLLABORATION_MENU_TAKE_HAND_ICON_PATH = PROJECT_PATH+"ressources/icons/modify_icon.png";
	
	public final static String HELP_MENU_TITLE = "Aide";
	
	public final static String HELP_MENU_HELP = "Aide";
	public final static String HELP_MENU_HELP_ICON_PATH = PROJECT_PATH+"ressources/icons/help_icon.png";

	public final static String HELP_MENU_TEXT_ICON_PATH = PROJECT_PATH+"ressources/icons/";
	
	public static final String CHOOSE_COLOR_MESSAGE = "Choisissez une couleur";
	
	public static final String DEFAULT_WIDTH = "400";
	public static final String DEFAULT_HEIGHT = "300";
	public static final String DEFAULT_STROKE_WIDTH = "3";

	public static final String HELP_FRAME_TITLE = "Dessin colaboratif - Aide";
	public static final String TEXT_INPUT_FRAME_VALIDATE_BUTTON = "Valider";
	
	public static final Integer DEFAULT_LIST_HEIGHT = 100;
	public static final Integer DEFAULT_LIST_WIDTH = 700;
	public static final Dimension DEFAULT_LIST_DIMENSION = new Dimension(DEFAULT_LIST_HEIGHT, DEFAULT_LIST_WIDTH);
}
