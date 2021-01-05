package myapp.designpattern.command;

import lombok.extern.slf4j.Slf4j;

/**
 * @author dennieyu
 *
 */
// Receiver Class
@Slf4j
public class Stock {

	private String name = "ABC";
	private int quantity = 10;

	public void buy() {
		log.debug("Stock [name={}, quantity={}] bought", name, quantity);
	}

	public void sell() {
		log.debug("Stock [name={}, quantity={}] sold", name, quantity);
	}

}
