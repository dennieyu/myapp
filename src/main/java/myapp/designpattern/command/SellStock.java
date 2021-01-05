package myapp.designpattern.command;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author dennieyu
 *
 */
// ConcreteCommand
@Data
@AllArgsConstructor
public class SellStock implements Order {

	private Stock abcStock; // Receiver

	@Override
	public void execute() {
		abcStock.sell();
	}

}
