package dessin.collaboratif.misc;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Dimension;

/**
 * Variables globales
 */
public class GeneralVariables {
    public final static String PROJECT_PATH                           = System.getProperty("user.dir") + "/";
    public final static String FILE_MENU_TITLE                        = "Fichier";
    public final static String FILE_MENU_NEW                          = "Nouveau";
    public final static String FILE_MENU_NEW_ICON_PATH                = PROJECT_PATH + "ressources/icons/file_icon.png";
    public final static String FILE_MENU_OPEN                         = "Ouvrir";
    public final static String FILE_MENU_OPEN_ICON_PATH               = PROJECT_PATH + "ressources/icons/open_icon.png";
    public final static String FILE_MENU_EXPORT                       = "Exporter";
    public final static String FILE_MENU_EXPORT_ICON_PATH             = PROJECT_PATH
                                                                        + "ressources/icons/export_icon.png";
    public final static String FILE_MENU_CLOSE                        = "Fermer";
    public final static String FILE_MENU_CLOSE_ICON_PATH              = PROJECT_PATH
                                                                        + "ressources/icons/close_icon.png";
    public final static String FILE_MENU_QUIT                         = "Quitter";
    public final static String FILE_MENU_QUIT_ICON_PATH               = PROJECT_PATH + "ressources/icons/exit_icon.png";
    public final static String EDITION_MENU_TITLE                     = "Edition";
    public final static String EDITION_MENU_UNDO                      = "Annuler";
    public final static String EDITION_MENU_UNDO_ICON_PATH            = PROJECT_PATH + "ressources/icons/undo_icon.png";
    public final static String EDITION_MENU_DELETE                    = "Supprimer";
    public final static String EDITION_MENU_DELETE_ICON_PATH          = PROJECT_PATH
                                                                        + "ressources/icons/delete_icon.png";
    public final static String EDITION_MENU_MOVE                      = "Déplacer/Redimensionner";
    public final static String EDITION_MENU_MOVE_ICON_PATH            = PROJECT_PATH
                                                                        + "ressources/icons/scale_icon.png";
    public final static String EDITION_MENU_RENAME                    = "Renommer";
    public final static String EDITION_MENU_RENAME_ICON_PATH          = PROJECT_PATH
                                                                        + "ressources/icons/scale_icon.png";
    public final static String EDITION_MENU_SCALE                     = "Redimensionner";
    public final static String EDITION_MENU_SCALE_ICON_PATH           = PROJECT_PATH
                                                                        + "ressources/icons/scale_icon.png";
    public final static String COLLABORATION_MENU_TITLE               = "Collaboration";
    public final static String COLLABORATION_MENU_TAKE_HAND           = "Prendre la main";
    public final static String COLLABORATION_MENU_TAKE_HAND_ICON_PATH = PROJECT_PATH
                                                                        + "ressources/icons/modify_icon.png";
    public final static String    HELP_MENU_TITLE                  = "Aide";
    public final static String    HELP_MENU_HELP                   = "Aide";
    public final static String    HELP_MENU_HELP_ICON_PATH         = PROJECT_PATH + "ressources/icons/help_icon.png";
    public final static String    HELP_MENU_TEXT_ICON_PATH         = PROJECT_PATH + "ressources/icons/";
    public final static String    INFO_MENU_HAVE_HAND              = "Vous avez la main";
    public final static String    INFO_MENU_REQUEST_HAND           = "Vous avez demandé la main";
    public final static String    INFO_MENU_HAVENT_HAND            = "Vous n'avez pas la main";
    public static final String    CHOOSE_COLOR_MESSAGE             = "Choisissez une couleur";
    public static final String    DEFAULT_WIDTH                    = "400";
    public static final String    DEFAULT_HEIGHT                   = "300";
    public static final String    DEFAULT_STROKE_WIDTH             = "3";
    public static final String    HELP_FRAME_TITLE                 = "Dessin colaboratif - Aide";
    public static final String    TEXT_INPUT_FRAME_VALIDATE_BUTTON = "Valider";
    public static final Integer   DEFAULT_LIST_HEIGHT              = 100;
    public static final Integer   DEFAULT_LIST_WIDTH               = 580;
    public static final Dimension DEFAULT_LIST_DIMENSION           = new Dimension(DEFAULT_LIST_HEIGHT,
                                                                         DEFAULT_LIST_WIDTH);
    public static final String    HELP_CONTENT_PATH                = PROJECT_PATH + "ressources/html/help.html";
    public static final String    LOGIN_ERROR_MISSING_LOGIN        = "Vous devez saisir un login";
    public static final String    LOGIN_ERROR_INVALID_LOGIN        =
        "Le login saisi existe déjà, veuillez en saisir un autre";
    public static final String    LOGIN_ERROR_MISSING_SERVER       = "Vous devez saisir une adresse de serveur";
    public static final String    LOGIN_ERROR_INVALID_SERVER       = "Vous devez saisir une adresse de serveur valide";
    public static final String    ICONS_PATH                       = PROJECT_PATH + "ressources/icons/";
    public static final String    SQUARE_ICON_PATH                 = ICONS_PATH + "square_icon.png";
    public static final String    CIRCLE_ICON_PATH                 = ICONS_PATH + "circle_icon.png";
    public static final String    ELLIPSE_ICON_PATH                = ICONS_PATH + "ellipse_icon.png";
    public static final String    TEXT_ICON_PATH                   = ICONS_PATH + "text_icon.png";
    public static final String    LINE_ICON_PATH                   = ICONS_PATH + "line_icon.png";
    public static final String    CURSOR_ICON_PATH                 = ICONS_PATH + "cursor_icon.png";
    public static final String    RED_LIGHT_ICON_PATH              = ICONS_PATH + "red_light.png";
    public static final String    ORANGE_LIGHT_ICON_PATH           = ICONS_PATH + "orange_light.png";
    public static final String    GREEN_LIGHT_ICON_PATH            = ICONS_PATH + "green_light.png";
    public static final String    FILE_NOT_EXISTS                  = "Le fichier sélectionné n'existe pas...";
    public static final String    LOGIN_FRAME_TITLE                = "Dessin collaboratif - Launcher";
    public static final String    LOGIN_FRAME_LOGIN_BUTTON_TEXT    = "Valider";
    public static final String    LOGIN_FRAME_LOGIN_LABEL          = "Login : ";
    public static final String    LOGIN_FRAME_SERVER_ADRESS_LABEL  = "Adresse du serveur : ";
}


//~ Formatted by Jindent --- http://www.jindent.com
