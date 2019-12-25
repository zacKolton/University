
/**
 * Class    : JSONIter
 * Author   : Zachary Kolton
 * REMARKS  : Interface for IteratorObj and IteratorArray (IteratorArray not created yet)
 */
public interface JSONIter {
	public boolean hasNext();
	public Value getNext();
}

