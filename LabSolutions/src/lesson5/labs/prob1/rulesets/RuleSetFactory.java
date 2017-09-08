package lesson5.labs.prob1.rulesets;

import java.awt.Component;
import java.util.HashMap;

import javax.swing.JFrame;

final public class RuleSetFactory {
	private RuleSetFactory(){}
	static HashMap<Class<? extends Component>, RuleSet> map = new HashMap<>();

	public static void addRule(Component c, RuleSet r) {
		map.put(c.getClass(), r);
	}

	public static RuleSet getRule(Class<? extends Component> c) {
		return map.get(c);
	}
}
