package pwdhasher.controllers;

import com.google.common.eventbus.EventBus;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pwdhasher.events.OptionChangedEvent;
import pwdhasher.model.Options;

/**
 * @author Andreas Werner
 */
@Controller
public class OptionsController {

    private final Options options;
    private final EventBus eventBus;
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

    @Autowired
    public OptionsController(Options options, EventBus eventBus) {
        this.options = options;
        this.eventBus = eventBus;
    }

    @FXML
    protected void initialize() {
        txtPasswordLength.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        txtPasswordLength.textProperty().bindBidirectional(options.passwordLengthProperty());
        chkRequireDigit.selectedProperty().bindBidirectional(options.requireDigitProperty());
        chkRequireMixedCase.selectedProperty().bindBidirectional(options.requireMixedCaseProperty());
        chkRequirePunctuation.selectedProperty().bindBidirectional(options.requirePunctuationProperty());
        chkRestrictDigits.selectedProperty().bindBidirectional(options.restrictDigitProperty());
        chkRestrictSpecialChars.selectedProperty().bindBidirectional(options.restrictSpecialCharProperty());
    }

    @FXML
    protected void handleOptionChanged() {
        eventBus.post(new OptionChangedEvent());
    }
}
