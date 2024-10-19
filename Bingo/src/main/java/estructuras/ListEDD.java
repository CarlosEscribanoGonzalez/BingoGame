
package estructuras;


import java.util.Iterator;

/**
 * Interface with the operations of a List.
 *
 * @author Ana BelÃƒÂ©n Moreno y Marc Comino
 * @param <Type> The data Type for the elements contained on the List.
 */
public interface ListEDD<Type> {

  /**
   * Appends elem to the end of this list.
   *
   * @param elem The element to append.
   * @return Whether it was able to append the element.
   */
  public boolean add(Type elem);

  /**
   * Inserts elem at the specified position pos in the list.
   *
   * @param pos The position where elem will be appended.
   * @param elem The element to append.
   * @throws IndexOutOfBoundsException if pos is a position out of the range of this list.
   */
  public void add(int pos, Type elem) throws IndexOutOfBoundsException;

  /** Removes all elements from this list. */
  public void clear();

  /** @return A new List equal to this list. */
  public ListEDD<Type> clone();

  /**
   * Returns whether this list contains elem.
   *
   * @param elem The element to find.
   * @return Whether this list contains elem.
   */
  public boolean contains(Type elem);

  /**
   * Compares this list against the provided list.
   *
   * @param list The list to compare against.
   * @return Whether both lists are equals.
   */
  public boolean equals(ListEDD<Type> list);

  /**
   * Returns the element at pos.
   *
   * @param pos The position of the element to find.
   * @return The element at pos.
   * @throws IndexOutOfBoundsException if pos is a position out of the range of this list.
   */
  public Type get(int pos) throws IndexOutOfBoundsException;

  /**
   * Returns the index of the first occurrence of elem.
   *
   * @param elem The element to find.
   * @return The index of the first occurrence of elem or -1 if not present.
   */
  public int indexOf(Type elem);

  /** @return An iterator over the elements in this list; */
  public Iterator iterator();

  /** @return Whether the list is empty; */
  public boolean isEmpty();

  /**
   * Removes the element at pos.
   *
   * @param pos The position of the element to remove.
   * @return The removed element.
   * @throws IndexOutOfBoundsException if pos is a position out of the range of this list.
   */
  public Type remove(int pos) throws IndexOutOfBoundsException;

  /**
   * Removes the first occurrence of elem.
   *
   * @param elem The element to remove.
   * @return Whether any element was removed.
   */
  public boolean removeElem(Type elem);

  /**
   * Replaces the element at pos by elem.
   *
   * @param pos The position of the element to replace.
   * @param elem The new element to store.
   * @return The previous stored element.
   * @throws IndexOutOfBoundsException if pos is a position out of the range of this list.
   */
  public Type set(int pos, Type elem) throws IndexOutOfBoundsException;

  /** @return The number of elements in this list. */
  public int size();
}