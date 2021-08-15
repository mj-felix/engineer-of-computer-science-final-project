package culinary.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.opensymphony.xwork2.validator.ValidationException;
import com.opensymphony.xwork2.validator.validators.FieldValidatorSupport;

public class PasswordIntegrityValidator extends FieldValidatorSupport {

	static Pattern digitPattern = Pattern.compile("[0-9]");
	static Pattern letterPattern = Pattern.compile("[a-zA-Z]");
	static Pattern specialCharsDefaultPattern = Pattern.compile("!@#$");

	public void validate(Object object) throws ValidationException {
		//System.out.println("object being validated = "+ object.getClass().getName());
		String fieldName = getFieldName();
		Object value = this.getFieldValue(fieldName, object);
		
		if (!(value instanceof String)) {
	         return;
	     }
		
		String fieldValue = ((String) value).trim();

		Matcher digitMatcher = digitPattern.matcher(fieldValue);
		Matcher letterMatcher = letterPattern.matcher(fieldValue);
		Matcher specialCharacterMatcher;

		if (getSpecialCharacters() != null) {
			Pattern specialPattern = Pattern.compile("["
					+ getSpecialCharacters() + "]");
			specialCharacterMatcher = specialPattern.matcher(fieldValue);
		} else {
			specialCharacterMatcher = specialCharsDefaultPattern.matcher(fieldValue);
		}

		if (!digitMatcher.find()) {
			addFieldError(fieldName, object);
		} else if (!letterMatcher.find()) {
			addFieldError(fieldName, object);
		} else if (!specialCharacterMatcher.find()) {
			addFieldError(fieldName, object);
		}
	}

	private String specialCharacters;

	public String getSpecialCharacters() {
		return specialCharacters;
	}

	public void setSpecialCharacters(String securityLevel) {
		this.specialCharacters = securityLevel;
	}
}
