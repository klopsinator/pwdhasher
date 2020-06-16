package pwdhasher.controllers;

import com.google.common.eventbus.Subscribe;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.skin.TextFieldSkin;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pwdhasher.events.OptionChangedEvent;
import pwdhasher.model.Options;
import pwdhasher.services.PasswordHasherService;

/**
 * @author Andreas Werner
 */
@Controller
public class MainController {

    private static final int OPTION_PANE_HEIGHT = 126;
    private final Options options;
    private final PasswordHasherService passwordHasherService;
    @FXML
    private TextField txtSiteTag;
    @FXML
    private TextField txtMasterKey;
    @FXML
    private TextField txtPassword;
    @FXML
    private ToggleButton btShowMasterKey;
    @FXML
    private TitledPane tabOptions;

    @Autowired
    public MainController(Options options, PasswordHasherService passwordHasherService) {
        this.options = options;
        this.passwordHasherService = passwordHasherService;
    }

    @FXML
    protected void initialize() {
        tabOptions.expandedProperty().addListener((val, notExpanded, expanded) -> {
            if (notExpanded) {
                tabOptions.getScene().getWindow().sizeToScene();
            } else {
                double expandedHeight = tabOptions.getScene().getWindow().getHeight() + OPTION_PANE_HEIGHT;
                tabOptions.getScene().getWindow().setHeight(expandedHeight);
            }
        });

        btShowMasterKey.textProperty().bind(Bindings.when(btShowMasterKey.selectedProperty()).then("Hide").otherwise("Show"));
        btShowMasterKey.selectedProperty().addListener((val, notSelected, selected) -> txtMasterKey.setText(txtMasterKey.getText()));

        txtMasterKey.skinProperty().set(new TextFieldSkin(txtMasterKey) {
            @Override
            protected String maskText(String txt) {
                if (!btShowMasterKey.isSelected()) {
                    return "*".repeat(txt.length());
                } else {
                    return txt;
                }
            }
        });
    }

    private void updatePasswordHash() {
        String siteTag = txtSiteTag.getText();
        String masterKey = txtMasterKey.getText();

        String passwordLength = options.getPasswordLength();

        boolean requireDigit = options.getRequireDigit();
        boolean requirePunctuation = options.getRequirePunctuation();
        boolean requireMixedCase = options.getRequireMixedCase();
        boolean restrictSpecialChar = options.getRestrictSpecialChar();
        boolean restrictDigit = options.getRestrictDigit();

        String password;
        try {
            password = passwordHasherService.hashPassword(siteTag, masterKey, Integer.parseInt(passwordLength), requireDigit, requirePunctuation, requireMixedCase, restrictSpecialChar, restrictDigit);
        } catch (NumberFormatException e) {
            password = "";
        }

        txtPassword.setText(password);
    }

    @FXML
    protected void handleSiteTagChanged() {
        updatePasswordHash();
    }

    @FXML
    protected void handleMasterKeyChanged() {
        updatePasswordHash();
    }

    @Subscribe
    public void handleOptionChanged(OptionChangedEvent event) {
        updatePasswordHash();
    }

    @FXML
    protected void handleCutAndClose() {
        ClipboardContent content = new ClipboardContent();
        content.putString(txtPassword.getText());
        Clipboard.getSystemClipboard().setContent(content);

        Platform.exit();
    }
}
