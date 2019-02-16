import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;


public class Inventory{
	private Map<String, Integer> inventory;
	private Lock inventoryLock;
	
	public Inventory() {
		inventory = new HashMap<>();
		inventoryLock = new ReentrantLock();
	}
	
	/**
	 * Add item to inventory
	 * @param str
	 * @param value
	 */
	public void addItem(String item, Integer n) {
		inventoryLock.lock();
		try {
			if (!inventory.containsKey(item)) {
				inventory.put(item, n);
			}else {
				int newN = inventory.get(item) + n;
				inventory.put(item, newN);
			}
		} finally {
			inventoryLock.unlock();
		}
		
		
	}
	
	/**
	 * return the quantity of items, if item does not exist return -1
	 * @param item
	 */
	public int checkInventory (String item) {
		inventoryLock.lock();
		try {
			if (inventory.containsKey(item)) {
				return inventory.get(item);
			}else {
				return -1;
			}
		} finally {
			inventoryLock.unlock();
		}
		
		
	}
	
	/**
	 * removes maximum n items of type specified by item name from inventory if available, 
	 * and returns number of item removed.Return -1 if there is no such item in the list.
	 * @param item
	 * @param n
	 * @return item has been removed
	 */
	public int getItem (String item, int n) {
		inventoryLock.lock();
		try {
			if (!inventory.containsKey(item)) {
				return -1;
			}else {
				if (inventory.get(item) < n) {
					int max = inventory.get(item);
					inventory.put(item, 0);
					return max;
				}else {
					inventory.put(item, inventory.get(item)-n);
					return n;
				}
			}
		} finally {
			inventoryLock.unlock();
		}
		
	}
	/**
	 *  returns all items and their numbers in the inventory that are equal or less than the threshold
	 */
	String getThresholds(int threshold) {
		inventoryLock.lock();
		try {
			String result = "";
			for (String e: inventory.keySet()) {
				if (inventory.get(e) <= threshold ) {
					result = result +"["+ e + ", "+ inventory.get(e) + "] ";
				}
			}
			
			return result;
		} finally {
			inventoryLock.unlock();
		}
		
	}
}
