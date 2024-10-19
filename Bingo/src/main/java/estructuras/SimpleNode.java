
package estructuras;

/**
 *
 * @author c.escribano.2021
 */
public class SimpleNode<Type> {
    private Type value;
    private SimpleNode next;
    private SimpleNode prev;
    
    public SimpleNode(Type elem){
        this.value = elem;
    }

    /**
     * @return the value
     */
    public Type getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Type value) {
        this.value = value;
    }

    /**
     * @return the next
     */
    public SimpleNode getNext() {
        return next;
    }

    /**
     * @param next the next to set
     */
    public void setNext(SimpleNode next) {
        this.next = next;
    }
}
    
 
