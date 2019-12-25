
/**
 * Class    : JSONArray
 * Author   : Zachary Kolton
 * REMARKS  : Interface for type ValueArray
 */
public interface JSONArray extends Value { 
	public void addValue (Value v);
	public JSONIter iterator (); 
}