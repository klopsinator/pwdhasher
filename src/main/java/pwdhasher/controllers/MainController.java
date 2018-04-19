package pwdhasher.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.common.eventbus.Subscribe;
import com.sun.javafx.scene.control.skin.TextFieldSkin;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import pwdhasher.events.OptionChangedEvent;
import pwdhasher.model.Options;
import pwdhasher.services.PasswordHasherService;

/**
 * @author Andreas Werner
 *
 */
@Controller
public class MainController {

	private static final int OPTION_PANE_HEIGHT = 126;

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
        
	private Options options;

	private PasswordHasherService passwordHasherService;

	@Autowired
	public MainController(Options options, PasswordHasherService passwordHasherService) {
		this.options = options;
		this.passwordHasherService = passwordHasherService;
	}

	@FXML
	void initialize() {
		tabOptions.expandedProperty().addListener((val, notExpanded, expanded) -> {
			if (notExpanded) {
				tabOptions.getScene().getWindow().sizeToScene();
			} else {
				double expandedHeight = tabOptions.getScene().getWindow().getHeight() + OPTION_PANE_HEIGHT;
				tabOptions.getScene().getWindow().setHeight(expandedHeight);
			}
		});
		
		btShowMasterKey.textProperty().bind(Bindings.when(btShowMasterKey.selectedProperty()).then("Hide").otherwise("Show"));
		btShowMasterKey.selectedProperty().addListener((val, notSelected, selected) -> {
			txtMasterKey.setText(txtMasterKey.getText());
		});
		
		txtMasterKey.skinProperty().set(new TextFieldSkin(txtMasterKey) {
			@Override
			protected String maskText(String txt) {
				if (!btShowMasterKey.isSelected()) {
		            int n = txt.length();
		            StringBuilder passwordBuilder = new StringBuilder(n);
		            for (int i = 0; i < n; i++) {
		                passwordBuilder.append(TextFieldSkin.BULLET);
		            }
		            return passwordBuilder.toString();
		        } else {
		            return txt;
		        }
			}
		});
	}
	
	void updatePasswordHash() {
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
			password = passwordHasherService.hashPassword(siteTag, masterKey, Integer.valueOf(passwordLength), requireDigit, requirePunctuation, requireMixedCase, restrictSpecialChar, restrictDigit);
		} catch (NumberFormatException e) {
			password = "";
		}
		
		txtPassword.setText(password);
	}
	
	@FXML
	void handleSiteTagChanged() {
		updatePasswordHash();
	}

	@FXML
	void handleMasterKeyChanged() {
		updatePasswordHash();
	}
    

    @FXML
    void handleBtShowMasterKeyClicked() {
    	boolean showPassword = btShowMasterKey.isSelected();
    	
    	if (showPassword) {
    		txtMasterKey.getStyleClass().remove("password-field");
    		btShowMasterKey.setText("Hide");
    		
    	} else {
    		txtMasterKey.getStyleClass().add("password-field");
    		btShowMasterKey.setText("Show");
    	}
    }
    
	@Subscribe
	public void handleOptionChanged(OptionChangedEvent event) {
		updatePasswordHash();
	}
}
