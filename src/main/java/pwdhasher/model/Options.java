package pwdhasher.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pwdhasher.config.OptionsConfig;

@Component
public class Options {

    private final StringProperty passwordLengthProperty;
    private final BooleanProperty requireDigitProperty;
    private final BooleanProperty requireMixedCaseProperty;
    private final BooleanProperty requirePunctuationProperty;
    private final BooleanProperty restrictDigitProperty;
    private final BooleanProperty restrictSpecialCharProperty;

    @Autowired
    public Options(OptionsConfig config) {
        passwordLengthProperty = new SimpleStringProperty(config.getPasswordLength());
        requireDigitProperty = new SimpleBooleanProperty(config.getRequireDigit());
        requirePunctuationProperty = new SimpleBooleanProperty(config.getRequirePunctuation());
        requireMixedCaseProperty = new SimpleBooleanProperty(config.getRequireMixedCase());
        restrictDigitProperty = new SimpleBooleanProperty(config.getRestrictDigit());
        restrictSpecialCharProperty = new SimpleBooleanProperty(config.getRestrictSpecialChar());
    }

    public StringProperty passwordLengthProperty() {
        return passwordLengthProperty;
    }

    public BooleanProperty requireDigitProperty() {
        return requireDigitProperty;
    }

    public BooleanProperty requirePunctuationProperty() {
        return requirePunctuationProperty;
    }

    public BooleanProperty requireMixedCaseProperty() {
        return requireMixedCaseProperty;
    }

    public BooleanProperty restrictDigitProperty() {
        return restrictDigitProperty;
    }

    public BooleanProperty restrictSpecialCharProperty() {
        return restrictSpecialCharProperty;
    }

    public String getPasswordLength() {
        return passwordLengthProperty.getValue();
    }

    public void setPasswordLength(String passwordLength) {
        passwordLengthProperty.setValue(passwordLength);
    }

    public boolean getRequireDigit() {
        return requireDigitProperty.getValue();
    }

    public void setRequireDigit(boolean requireDigit) {
        requireDigitProperty.setValue(requireDigit);
    }

    public boolean getRequireMixedCase() {
        return requireMixedCaseProperty.getValue();
    }

    public void setRequireMixedCase(boolean requireMixedCase) {
        requireMixedCaseProperty.setValue(requireMixedCase);
    }

    public boolean getRequirePunctuation() {
        return requirePunctuationProperty.getValue();
    }

    public void setRequirePunctuation(boolean requirePunctuation) {
        requirePunctuationProperty.setValue(requirePunctuation);
    }

    public boolean getRestrictDigit() {
        return restrictDigitProperty.getValue();
    }

    public void setRestrictDigit(boolean restrictDigit) {
        restrictDigitProperty.setValue(restrictDigit);
    }

    public boolean getRestrictSpecialChar() {
        return restrictSpecialCharProperty.getValue();
    }

    public void setRestrictSpecialChar(boolean restrictSpecialChar) {
        restrictSpecialCharProperty.setValue(restrictSpecialChar);
    }

}
