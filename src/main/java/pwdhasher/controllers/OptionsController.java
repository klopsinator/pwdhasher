package pwdhasher.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.common.eventbus.EventBus;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;
import pwdhasher.events.OptionChangedEvent;
import pwdhasher.model.Options;

/**
 * @author Andreas Werner
 *
 */
@Controller
public class OptionsController {
	
	@FXML
	private TextField txtPasswordLength;

	@FXML
	private CheckBox chkRequireDigit;

	@FXML
	private CheckBox chkRequireMixedCase;
	
	@FXML
	private CheckBox chkRequirePunctuation;
	
	@FXML
	private CheckBox chkRestrictDigits;

	@FXML
	private CheckBox chkRestrictSpecialChars;

	private Options options;

	private EventBus eventBus;

	@Autowired
	public OptionsController(Options options, EventBus eventBus) {
		this.options = options;
		this.eventBus = eventBus;
	}

	@FXML
	void initialize() {
		txtPasswordLength.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter()));
		txtPasswordLength.textProperty().bindBidirectional(options.passwordLengthProperty());
		chkRequireDigit.selectedProperty().bindBidirectional(options.requireDigitProperty());
		chkRequireMixedCase.selectedProperty().bindBidirectional(options.requireMixedCaseProperty());
		chkRequirePunctuation.selectedProperty().bindBidirectional(options.requirePunctuationProperty());
		chkRestrictDigits.selectedProperty().bindBidirectional(options.restrictDigitProperty());
		chkRestrictSpecialChars.selectedProperty().bindBidirectional(options.restrictSpecialCharProperty());
	}

	@FXML
	void handleOptionChanged() {
		eventBus.post(new OptionChangedEvent());
	}
}
