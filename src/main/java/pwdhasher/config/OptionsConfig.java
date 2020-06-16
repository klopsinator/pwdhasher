package pwdhasher.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "options")
public class OptionsConfig {

    private String passwordLength;
    private boolean requireDigit;
    private boolean requireMixedCase;
    private boolean requirePunctuation;
    private boolean restrictDigit;
    private boolean restrictSpecialChar;

    public String getPasswordLength() {
        return passwordLength;
    }

    public void setPasswordLength(String passwordLength) {
        try {
            Integer.valueOf(passwordLength);
            this.passwordLength = passwordLength;
        } catch (NumberFormatException e) {
            this.passwordLength = "12";
        }
    }

    public boolean getRequireDigit() {
        return requireDigit;
    }

    public void setRequireDigit(boolean requireDigit) {
        this.requireDigit = requireDigit;
    }

    public boolean getRequireMixedCase() {
        return requireMixedCase;
    }

    public void setRequireMixedCase(boolean requireMixedCase) {
        this.requireMixedCase = requireMixedCase;
    }

    public boolean getRequirePunctuation() {
        return requirePunctuation;
    }

    public void setRequirePunctuation(boolean requirePunctuation) {
        this.requirePunctuation = requirePunctuation;
    }

    public boolean getRestrictDigit() {
        return restrictDigit;
    }

    public void setRestrictDigit(boolean restrictDigit) {
        this.restrictDigit = restrictDigit;
    }

    public boolean getRestrictSpecialChar() {
        return restrictSpecialChar;
    }

    public void setRestrictSpecialChar(boolean restrictSpecialChar) {
        this.restrictSpecialChar = restrictSpecialChar;
    }
}
