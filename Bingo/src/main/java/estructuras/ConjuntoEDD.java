/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package estructuras;
 

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Interface with the operations of a Set.
 *
 * @author Ana BelÃƒÂ©n Moreno y Marc Comino
 * @param <Type> The data Type for the elements contained on the Set.
 */
public interface ConjuntoEDD<Type> extends Iterable<Type> {

  /**
   * Adds elem to the set if it was not present before.
   *
   * @param elem The element to add.
   * @return Whether it was able to add the element.
   */
  public boolean add(Type elem);

  /**
   * Computes the union between this Set and the provided other_set. This function will add to this
   * Set all the elements from other_set.
   *
   * @param other_set The Set used to compute the union.
   */
  public void addAll(ConjuntoEDD<Type> other_set);

  /** Removes all elements from this Set. */
  public void clear();

  /**
   * Returns whether this Set contains elem.
   *
   * @param elem The element to find.
   * @return Whether this Set contains elem.
   */
  public boolean contains(Type elem);

  /**
   * Computes whether other_set is a subset of this Set. It checks whether all the elements in
   * other_set are contained in this Set.
   *
   * @param other_set The Set used for the test.
   * @return Whether this Set contains all the elements in other_set.
   */
  public boolean containsAll(ConjuntoEDD<Type> other_set);

  /**
   * Compares this Set against the provided other_set.
   *
   * @param other_set The Set to compare against.
   * @return Whether both Sets are equals.
   */
  public boolean equals(ConjuntoEDD<Type> other_set);

  /**
   * Returns whether this Set is empty.
   *
   * @return Whether the Set is empty.
   */
  public boolean isEmpty();

  /** @return An iterator over the elements in this Set; */
  @Override
  public Iterator iterator();

  /**
   * Removes a returns a random element from the Set.
   *
   * @return A random element from the Set.
   * @throws NoSuchElementException is the set is empty.
   */
  public Type randomElement() throws NoSuchElementException;

  /**
   * Removes elem, if it was present on the Set.
   *
   * @param elem The element to remove.
   * @return Whether any element was removed.
   */
  public boolean remove(Type elem);

  /**
   * Computes difference between this Set and the provided other_set. This function will remove from
   * this Set any element present in the other_set.
   *
   * @param other_set The Set used to compute the difference.
   */
  public void removeAll(ConjuntoEDD<Type> other_set);

  /**
   * Computes intersection between this Set and the provided other_set. This function will remove
   * from this Set any element not present in the other_set.
   *
   * @param other_set The Set used to compute the intersection.
   */
  public void retainAll(ConjuntoEDD<Type> other_set);

  /**
   * Computes and returns the number of elements in the Set.
   *
   * @return The number of elements in this Set.
   */
  public int size();
}
