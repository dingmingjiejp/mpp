package lesson5.labs.prob1.rulesets;

import java.awt.Component;
import java.util.regex.Pattern;

import lesson5.labs.prob1.gui.CDWindow;


/**
 * Rules:
 *  1. All fields must be nonempty
 *  2. Price must be a floating point number with two decimal places
 *  3. Price must be a number greater than 0.49.
 */

public class CDRuleSet implements RuleSet {

	@Override
	public void applyRules(Component ob) throws RuleException {
		CDWindow w = (CDWindow) ob;

		if(w.getArtistValue().trim().equals("") ||
				w.getTitleValue().trim().equals("") ||
				w.getPriceValue().trim().equals("")) {
			throw new RuleException("All fields must be nonempty");
		}

		if(!w.getPriceValue().trim().equals("")) {
			boolean match = Pattern.matches("^([0-9]*)\\.([0-9]{2})$", w.getPriceValue());

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
