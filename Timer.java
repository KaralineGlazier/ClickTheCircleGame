/**
 * <h1>Timer</h1>
 * <p>This class is used to create a timer object. 
 * The timer object is used to count down from an amount of time and return the amount of time.</p1>
 * <p>Created: 03/18/2024</p1>
 * 
 * @author Karaline Glazier
 */
public class Timer {
	int amount = 60;
	/**
	 * This is the no arg constructor for the timer class.
	 */
	Timer() {

	}
	/** 
	 * This is the constructor for the time class with a specified integer for the variable "amount".
	 * @param amount (int; This parameter represents the amount of time for the timer object.)
	 */
	Timer(int amount){
		this.amount = amount;
	}
	/**
	 * The getSecond method returns the amount of seconds left in a timer. 
	 * This is done by taking the total amount left, dividing it by 60, and returning the remainder.
	 * @return amount % 60
	 */
	int getSecond() {
		return amount % 60;
	}
	/**
	 * The getMinute method returns the amount of minutes left in a timer object. 
	 * This is done by taking the total amount of time left divided by 60, dividing it by 60, and returning the remainder.
	 * @return (amount / 60) % 60
	 */
	int getMinute() {
		return (amount / 60) % 60;
	}
	/**
	 * This method resets the timer by defining the variable "amount" back to its original amount.
	 */
	void reset() {
		amount = 60;
	}
	/**
	 * This method decreases the timer by subtracting 1 from the variable "amount".
	 */
	void decrease() {
		amount --;
	}
	/**
	 * This method returns the current amount that is left in a timer. 
	 * @return amount (int; this variable represents the amount of time left for a timer object.)
	 */
	int getAmount() {
		return amount;
	}
	/**
	 * This method returns the amount left in a timer in the form of a String type. 
	 */
	@Override
	public String toString() {
		return getTimerString(getMinute()) + ":" + getTimerString(getSecond());
	}
	/**
	 * This method defines the layout for the string in the toString method.
	 * If the number is one digit, there is a 0 in front of it. If the number is multiple digits, there is no 0 in front of it.
	 * @param v (int; This parameter represents the number of either minutes or seconds left in the variable amount. It can be either a one digit number or a multiple digit number.)
	 * @return "" + v
	 */
	static String getTimerString(int v) {
		if (v < 10) {
			return "0" + v;
		}
		
		else {
			return ""  + v;
		}
	}
}