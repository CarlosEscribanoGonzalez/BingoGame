/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructuras;

import estructuras.ConjuntoEDD;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class Conjunto<Type> implements ConjuntoEDD<Type> {
  private Type[] elements;
  // La capacidad de un vector serÃƒÂ¡ el nÃƒÂºmero de elementos que puede albergar antes de necesitar una
  // redimensiÃƒÂ³n.
  private final int INITIAL_CAPCITY = 2;
  // La longitud del vector serÃƒÂ¡ el nÃƒÂºmero de elementos albergados.
  private int cardinal;
  
  private Random generator;
  //Un generador de nÃºmeros aleatorios genera nÃºmeros pseudoaleatoriamente. Hay que crear un objeto Random y utilizarlo siempre en todo el documento.
  //Funcionamiento: dada una semilla "aleatoria" genera una secuencia. Dos generadores con una misma semilla dan el mismo nÃºmero. 
  //Hay que implementarlo de esta forma de manera que la semilla no se repita y las sucesivas llamadas sean distintas (nextInt)
  
  
  
  public Conjunto() {
    // Inicializamos el array con una capacidad inicial que estarÃƒÂ¡ oculta para el usuario.
    // Tiempo O(1).
    elements = (Type[]) new Object[INITIAL_CAPCITY];

    // Inicializamos el conjunto como un array vacÃƒÂ­o.
    // Tiempo O(1).
    cardinal = 0;
    
    generator = new Random();
  }


  /**
   * Adds elem to the end of the array. This implementation takes O(1) amortized time.
   *
   * @param elem The element to add.
   */
  private void addLast(Type elem) {
    // Tiempo O(1). Esto asume que this.size() se ejecuta en O(1).
    final int CURR_NUM_ELEMS = this.size();
    final int CAPACITY = elements.length;

    // Si tenemos menos elementos albergados que la capacidad del array, podemos aÃƒÂ±adir el nuevo
    // elemento sin necesidad de redimensionar.
    // Tiempo O(1).
    if (CURR_NUM_ELEMS < CAPACITY) {
      elements[CURR_NUM_ELEMS] = elem;

      // Incrementamos la longitud.
      // Tiempo O(1).
      ++cardinal;
    } else { // Se ejectua esta rama 1 de cada N veces, donde N es la longitud actual del array.
      // Si hemos llegado a la maxima capacidad, hemos de redimensionar el array.
      // Creamos un nuevo array que contendrÃƒÂ¡ el doble de capacidad.
      // Tiempo O(N).
      Type[] tmp = (Type[]) new Object[CURR_NUM_ELEMS * 2];

      // Copiamos todos los elementos ya existentes.
      // Tiempo O(N).
      for (int i = 0; i < CURR_NUM_ELEMS; ++i) {
        tmp[i] = elements[i];
      }

      // Asignamos el nuevo elemento a la ÃƒÂºltima posiciÃƒÂ³n 'libre'.
      // Tiempo O(1).
      tmp[CURR_NUM_ELEMS] = elem;

      // Guardamos el nuevo array en elements.
      // La memoria del array antiguo serÃƒÂ¡ liberada por le garbage collector.
      // Tiempo O(1).
      elements = tmp;

      // Incrementamos la longitud.
      // Tiempo O(1).
      ++cardinal;
    }
  }

  /**
   * Adds elem to the set if it was not present before. This implementation takes O(N) to execute,
   * where N is the current length of the array.
   *
   * @param elem The element to add.
   * @return Whether it was able to add the element.
   */
  @Override
  public boolean add(Type elem) {
    if(contains(elem)){
        return false;
    } else{
        addLast(elem);
        return true;
    }
  }
  
  public void addNum(Type elem){
      addLast(elem);
  }

  /**
   * Computes the union between this Set and the provided other_set. This function will add to this
   * Set all the elements from other_set. This implementation takes O(M*N) to execute, where N is
   * the current length of the array and M is the size of other_set.
   *
   * @param other_set The Set used to compute the union.
   */
  @Override
  public void addAll(ConjuntoEDD<Type> other_set) {
    for(Type element : other_set){
        this.add(element);
    }
  }

  /**
   * Removes all elements from this Set. This implementation takes O(1) time to execute (if we
   * disregard the time for freeing the memory of the previously allocated elements, which is done
   * in the background by the garbage collector).
   */
  @Override
  public void clear() {
    // Guardamos un nuevo array con la capacidad inicial en elements.
    // La memoria del array antiguo serÃƒÂ¡ liberada por le garbage collector.
    // Tiempo O(1).
    elements = (Type[]) new Object[INITIAL_CAPCITY];

    // Hacemos que la longitud sea 0.
    // Tiempo O(1).
    cardinal = 0;
  }

  /**
   * Returns whether this Set contains elem. This implementation takes O(N) time to execute, where N
   * is the current length of the array.
   *
   * @param elem The element to find.
   * @return Whether this Set contains elem.
   */
  @Override
  public boolean contains(Type elem) {
    // Recorremos todos los elementos del conjunto y si alguno es igual que el elemento 'elem'
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
   * Computes whether other_set is a subset of this Set. It checks whether all the elements in
   * other_set are contained in this Set. This implementation takes O(M*N) to execute, where N is
   * the current length of the array and M is the size of other_set.
   *
   * @param other_set The Set used for the test.
   * @return Whether this Set contains all the elements in other_set.
   */
  @Override
  public boolean containsAll(ConjuntoEDD<Type> other_set) {
    for(Type element : other_set){
        if(!contains(element)){
            return false;
        }
    }
    return true;
  }

  /**
   * Compares this Set against the provided other_set. This implementation takes O(NÃ‚Â²) time to
   * execute, where N is the current length of the array.
   *
   * @param other_set The Set to compare against.
   * @return Whether both Sets are equals.
   */
  @Override
  public boolean equals(ConjuntoEDD<Type> other_set) {
    // Tiempo O(1). Esto asume que this.size() se ejecuta en O(1).
    final int CURR_NUM_ELEMS = this.size();

    // Si el cardinal de los dos conjuntos no es igual, retornamos falso.
    // Tiempo O(1). Esto asume que other_set.size() se ejecuta en O(1).
    if (CURR_NUM_ELEMS != other_set.size()) {
      return false;
    }

    // Si el cardinal de los dos conjuntos, comparamos elemento a elemento.
    // Tiempo O(NÃ‚Â²).
    for (Type element : this) {
      // Si algun elemento con estÃƒÂ¡ en other_set, retornamos falso.
      // Tiempo O(N).
      if (!other_set.contains(element)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Returns whether this Set is empty. This implementation takes O(1) time to execute.
   *
   * @return Whether the Set is empty.
   */
  @Override
  public boolean isEmpty() {
    // Tiempo O(1). Esto asume que this.size() se ejecuta en O(1).
    final int CURR_NUM_ELEMS = this.size();
    return CURR_NUM_ELEMS == 0;
  }

  /** @return An iterator over the elements in this Set; */
  @Override
  public Iterator iterator() {
    return new SmartArraySetIterator<>();
  }

  /**
   * Removes a returns a random element from the Set.
   *
   * @return A random element from the Set.
   * @throws NoSuchElementException is the set is empty.
   */
  @Override
  public Type randomElement() throws NoSuchElementException {
    /*int pos = (int) (Math.random() * cardinal);
    Type value = (Type) elements[pos];
    remove(value);
    return value;*/
   
    if(isEmpty()) throw new NoSuchElementException("El conjunto debe tener al menos un elemento");
    
    int index = generator.nextInt(cardinal);
    Type valueToReturn = elements[index];
    remove(index);
    return valueToReturn;
  }

  
  public void remove(int i){
    elements[i] = elements[cardinal-1];
    cardinal--;
  }
  
  
  /**
   * Removes elem, if it was present on the Set. This implementation takes O(N) time to execute,
   * where N is the current length of the array.
   *
   * @param elem The element to remove.
   * @return Whether any element was removed.
   */
  @Override
  public boolean remove(Type elem) {
    for(int i = 0; i < cardinal; i++){
        if(elements[i].equals(elem)){
            remove(i);
            return true;
        }
    }
    return false;
  }

  /**
   * Computes difference between this Set and the provided other_set. This function will remove from
   * this Set any element present in the other_set. This implementation takes O(M*N) to execute,
   * where N is the current length of the array and M is the size of other_set.
   *
   * @param other_set The Set used to compute the difference.
   */
  @Override
  public void removeAll(ConjuntoEDD<Type> other_set) {
    for(Type element : other_set){
        remove(element);
    }
  }

  /**
   * Computes intersection between this Set and the provided other_set. This function will remove
   * from this Set any element not present in the other_set. This implementation takes O(M*N) to
   * execute, where N is the current length of the array and M is the size of other_set.
   *
   * @param other_set The Set used to compute the intersection.
   */
  @Override
  public void retainAll(ConjuntoEDD<Type> other_set) {
    //INEFICIENTE: Se puede implementar un mÃ©todo comÃºn public void remove(int i)
    
    /*for(Type element : this){
        if(!other_set.contains(element)){
            this.remove(element);
        }
    }*/
    
    for (int i = 0; i < cardinal; ++i){ //cardinal reduce su nÃºmero cada vez que se hace un remove(i)
        Type elem = elements[i];
        if(other_set.contains(elem)){
            remove(i); //remove(i) -> tiempo constante; remove(elem) -> tiempo lineal
            --i; //si quitamos 1 tenemos que restarle uno al iterador para que compruebe el nÃºmero que se ha intercambiado
        }
    }
  }

  /**
   * Computes and returns the number of elements in the Set. This implementation takes O(1) time to
   * execute.
   *
   * @return The number of elements in this Set.
   */
  @Override
  public int size() {
    return cardinal;
  }

  private class SmartArraySetIterator<Type> implements Iterator<Type> {
    private int index;

    public SmartArraySetIterator() {
      index = 0;
    }

    @Override
    public boolean hasNext() {
      return index < cardinal;
    }

    @Override
    public Type next() {
      Type element = (Type) elements[index];
      index = index + 1;
      return element;
    }
  }
}


