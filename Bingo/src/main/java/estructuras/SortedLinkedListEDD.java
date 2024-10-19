/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructuras;


import java.util.Iterator;

/** @author marcct */
public class SortedLinkedListEDD<Type extends Comparable>
    implements SortedListEDD<Type>, Iterable<Type> {
  private SimpleNode<Type> first;

  public SortedLinkedListEDD() {
    // Inicializamos la lista haciendo que el primer elemento sea nulo.
    // Tiempo O(1).
    first = null;
  }

  /**
   * Inserts an element on the corresponding position to ensure the resulting list is sorted in
   * increasing order. This implementation takes O(N) time to execute, where N is the current length
   * of the list.
   *
   * @param elem The element to append.
   * @return Whether it was able to append the element.
   */
  @Override
  public boolean add(Type elem) {
    SimpleNode<Type> newNode = new SimpleNode<>(elem);
    if(first == null){
        first = newNode;
    } else if (elem.compareTo(first.getValue()) < 0){
        newNode.setNext(first);
        first = newNode;
    } else{
        SimpleNode<Type> current = first;
        while(current.getNext() != null && elem.compareTo(current.getNext().getValue()) > 0){
            current = current.getNext();
        }
        newNode.setNext(current.getNext());
        current.setNext(newNode);
    }
    return true;
  }

  /**
   * Removes all elements from this list. This implementation takes O(1) time to execute (if we
   * disregard the time for freeing the memory of the previously allocated elements, which is done
   * in the background by the garbage collector).
   */
  @Override
  public void clear() {
    // Hacemos que el primer elemeno sea nulo.
    // La memoria de los Nodos antiguo serÃ¡ liberada por le garbage collector.
    // Tiempo O(1).
    first = null;
  }

  /**
   * Creates and returns a new list which contains all the elements of the list where the function
   * is called. This implementation takes O(NÂ²) time to execute, where N is the current length of
   * the list.
   *
   * @return A new List equal to this list.
   */
  @Override
  public SortedListEDD<Type> clone() {
    // Creamos una nueva lista y aÃ±adimos todos los elementos existentes.
    // Tiempo O(N).
    SortedListEDD<Type> newList = new SortedLinkedListEDD<>();

    // AÃ±adimos, uno a uno, los N elementos a la nuevo lista.
    // Tiempo O(NÂ²).
    for (Type element : this) {
      // Cada llamada a la funciÃ³n 'add' cuesta tiempo O(N).
      // Tiempo O(N).
      newList.add(element);
    }

    return newList;
  }

  /**
   * Returns whether this list contains elem. This implementation takes O(N) time to execute, where
   * N is the current length of the list.
   *
   * @param elem The element to find.
   * @return Whether this list contains elem.
   */
  @Override
  public boolean contains(Type elem) {
    // Recorremos todos los elementos de la lista y si alguno es igual que el elemento 'elem'
    // retornamos cierto.
    // Tiempo O(N).
    for (Type element : this) {
      if (element.equals(elem)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Compares this list against the provided list. This implementation takes O(N) time to execute,
   * where N is the current length of the list.
   *
   * @param other_list The list to compare against.
   * @return Whether both lists are equals.
   */
  @Override
  public boolean equals(SortedListEDD<Type> other_list) {
    // Tiempo O(N). Esto asume que this.size() se ejecuta en O(N).
    final int CURR_NUM_ELEMS = this.size();

    // Si la longitud de las dos listas no es igual, retornamos falso.
    // Tiempo O(N). Esto asume que list.size() se ejecuta en O(N).
    if (CURR_NUM_ELEMS != other_list.size()) {
      return false;
    }

    // Si la longitud de las dos listas es igual, comparamos elemento a elemento.
    // Tiempo O(N).
    Iterator<Type> list_iterator = other_list.iterator();
    for (Type element : this) {
      // Si algun elemento es diferente, retornamos falso.
      // Tiempo O(1). Esto asume que get(i) se ejecuta en O(N).
      if (!element.equals(list_iterator.next())) {
        return false;
      }
    }

    return true;
  }

  /**
   * Returns the SimpleNode at pos. This implementation takes O(N) time to execute, where N is the
   * current length of the list.
   *
   * @param pos The position of the element to find.
   * @return The SimpleNode at pos.
   * @throws IndexOutOfBoundsException if pos is a position out of the range of this list.
   */
  private SimpleNode<Type> getNode(int pos) throws IndexOutOfBoundsException {
    // Si la lista estÃ¡ vacÃ­a o la posiciÃ³n es invÃ¡lida (negativa) lanzaremos una excepciÃ³n.
    // Tiempo O(1).
    if (isEmpty() || pos < 0) {
      throw new IndexOutOfBoundsException();
    }

    // Empezando por el primer Nodo, saltamos de Nodo en Nodo hasta hacer un total de 'pos' saltos.
    // Tiempo O(N).
    SimpleNode<Type> current = first;
    for (int i = 0; i < pos; ++i) {
      // Si aÃºn no hemos hecho todos los saltos pero nos encontramos que el nodo actual no tiene
      // siguiente, lanzaremos una excepciÃ³n.
      // Tiempo O(1).
      if (current.getNext() == null) {
        throw new IndexOutOfBoundsException();
      }
      // Saltamos al siguiente nodo.
      // Tiempo O(1).
      current = current.getNext();
    }

    return current;
  }

  /**
   * Returns the element at pos. This implementation takes O(N) time to execute, where N is the
   * current length of the list.
   *
   * @param pos The position of the element to find.
   * @return The element at pos.
   * @throws IndexOutOfBoundsException if pos is a position out of the range of this list.
   */
  @Override
  public Type get(int pos) throws IndexOutOfBoundsException {
    return this.getNode(pos).getValue();
  }

  /**
   * Returns the index of the first occurrence of elem. This implementation takes O(N) time to
   * execute, where N is the current length of the list.
   *
   * @param elem The element to find.
   * @return The index of the first occurrence of elem or -1 if not present.
   */
  @Override
  public int indexOf(Type elem) {
    int index = 0;

    // Recorremos todos los elementos de la lista y si alguno es igual que el elemento 'elem'
    // retornamos su Ã­ndice.
    // Tiempo O(N).
    for(Type element : this) {
      if (element.equals(elem)) {
        return index;   
      }
      ++index;
    }

    // Sino, retornamos -1.
    // Tiempo O(1).
    return -1;
  }

  /**
   * Returns whether this list is empty. This implementation takes O(1) time to execute.
   *
   * @return A new List equal to this list.
   */
  @Override
  public boolean isEmpty() {
    // Tiempo O(1).
    return first == null;
  }

  @Override
  public Iterator<Type> iterator() {
    return new SortedLinkedListIterator();
  }

  /**
   * Removes the element at pos. This implementation takes O(N) time to execute, where N is the
   * current length of the list.
   *
   * @param pos The position of the element to remove.
   * @return The removed element.
   * @throws IndexOutOfBoundsException if pos is a position out of the range of this list.
   */
  @Override
  public Type remove(int pos) throws IndexOutOfBoundsException {
    // Si la posiciÃ³n indicada es 0, debemos eliminar del primer elemento.
    // Tiempo O(1).
    if (pos == 0 && !isEmpty()) {
      SimpleNode<Type> nodeToRemove = first;
      first = first.getNext();
      return nodeToRemove.getValue();
    } else {
      // Buscamos el Nodo previo a la posiciÃ³n de eliminaciÃ³n. La funciÃ³n getNode se encarga de
      // comprobar que 'pos' sea una posiciÃ³n vÃ¡lida.
      // Tiempo O(N).
      SimpleNode<Type> previous = this.getNode(pos - 1);

      // Debemos comprobar que el Nodo a eliminar no es nulo.
      if (previous.getNext() == null) {
        throw new IndexOutOfBoundsException();
      }

      // Realizamos la eliminaciÃ³n.
      // Tiempo O(1).
      SimpleNode<Type> nodeToRemove = previous.getNext();
      previous.setNext(nodeToRemove.getNext());
      return nodeToRemove.getValue();
    }
  }

  /**
   * Removes the first occurrence of elem. This implementation takes O(N) time to execute, where N
   * is the current length of the list.
   *
   * @param elem The element to remove.
   * @return Whether any element was removed.
   */
  @Override 
  public Type removeElem(Type elem) {
    // Buscamos el elemento 'elem' en la lista.
    // Tiempo O(N).
    int index = indexOf(elem);
    // Si lo encontramos, lo eliminamos y retornamos cierto. Tiempo O(N).
        remove(index);
        return elem;
  }


  /**
   * Computes and returns the number of elements in the list. This implementation takes O(N) time to
   * execute, where N is the current length of the list.
   *
   * @return The number of elements in this list.
   */
  @Override
  public int size() {
    int size = 0;
    // Empezando por el primer Nodo, saltamos de Nodo en Nodo hasta el final de la lista.
    // Tiempo O(N).
    for (Type element : this) {
      // A cada salto sumamos una al tamaÃ±o de la lista.
      // Tiempo O(1).
      ++size;
    }
    return size; //Podría añadirse longitud a esta lista. 
  }

  public SimpleNode<Type> first(){
      return first;
  }
  
  public class SortedLinkedListIterator implements Iterator<Type> {

    private SimpleNode<Type> current;

    public SortedLinkedListIterator() {
      current = first;
    }

    /**
     * Returns true if there is a next element in the iterator traversal.
     *
     * @return Whether there is a next element in the iterator traversal.
     */
    @Override
    public boolean hasNext() {
      return current != null;
    }

    /**
     * Returns the next element in the iterator.
     *
     * @return The next element in the iterator.
     */
    @Override
    public Type next() {
      Type value = current.getValue();
      current = current.getNext();
      return value;
    }
  }
}

