package lesson5.labs.prob1.rulesets;

import java.awt.Component;
import java.util.regex.Pattern;

import lesson5.labs.prob1.gui.BookWindow;



/**
 * Rules:
 * 1. All fields must be nonempty
 * 2. Isbn must be numeric and consist of either 10 or 13 characters
 * 3. If Isbn has length 10, the first digit must be 0 or 1
 * 4. If Isbn has length 13, the first 3 digits must be either 978 or 979
 * 5. Price must be a floating point number with two decimal places
 * 6. Price must be a number greater than 0.49.
 *
 */
public class BookRuleSet implements RuleSet {

	@Override
	public void applyRules(Component ob) throws RuleException {
		BookWindow w = (BookWindow) ob;

		if(w.getIsbnValue().trim().equals("") ||
				w.getTitleValue().trim().equals("") ||
				w.getPriceValue().trim().equals("")) {
			throw new RuleException("All fields must be nonempty");
		}

		String decimalPattern = "([0-9]{10,13})";
		boolean match = Pattern.matches(decimalPattern, w.getIsbnValue());
		if(match == false){
			throw new RuleException("Isbn must be numeric and consist of either 10 or 13 characters");
		} else {
			decimalPattern = "([0-9]{10})";
			match = Pattern.matches(decimalPattern, w.getIsbnValue());

			if(match == true){
				decimalPattern = "([01][0-9]{9})";
				match = Pattern.matches(decimalPattern, w.getIsbnValue());
				if(match == false){
					throw new RuleException("If Isbn has length 10, the first digit must be 0 or 1");
				}
			}

			decimalPattern = "([0-9]{13})";
			match = Pattern.matches(decimalPattern, w.getIsbnValue());

			if(match == true){
				decimalPattern = "(978|979)([0-9]{10})";
				match = Pattern.matches(decimalPattern, w.getIsbnValue());
				if(match == false){
					throw new RuleException("If Isbn has length 13, the first 3 digits must be either 978 or 979");
				}
			}
		}

		if(!w.getPriceValue().trim().equals("")) {
			decimalPattern = "^([0-9]*)\\.([0-9]{2})$";
			match = Pattern.matches(decimalPattern, w.getPriceValue());

			if(match == false) {
				throw new RuleException("Price must be a floating point number with two decimal places");
			} else {
				double d = Double.parseDouble(w.getPriceValue());
				if(!(d > 0.49)) {
					throw new RuleException("Price must be a number greater than 0.49");
				}
			}
		}

	}

}
