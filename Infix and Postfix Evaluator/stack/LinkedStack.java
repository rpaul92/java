package stack;

/**
 * A {@link LinkedStack} is a generic stack that is implemented using
 * a Linked List structure to allow for unbounded size.
 */
public class LinkedStack<T> {
  
  public LLNode<T> index; //the top of the stack
  public int count; //keeps track of the amount of stuff in the stack

  // TODO: define class variables here

  /**
   * Remove and return the top element on this stack.
   * If stack is empty, return null (instead of throw exception)
   */
  public T pop() {
    // TODO
    if (!isEmpty()) {
      T element = index.info;
      index = index.link;
      count--;
      return element;
    }
    return null;
  }

  /**
   * Return the top element of this stack (do not remove the top element).
   * If stack is empty, return null (instead of throw exception)
   */
  public T top() {
    // TODO
    if (isEmpty()) {return null;}
    return index.info;
  }

  /**
   * Return true if the stack is empty and false otherwise.
   */
  public boolean isEmpty() {
    // TODO
    return index == null;
  }

  /**
   * Return the number of elements in this stack.
   */
  public int size() {
    // TODO
    return count;
  }

  /**
   * Pushes a new element to the top of this stack.
   */
  public void push(T elem) {
    // TODO
    LLNode<T> node = new LLNode<T>(elem);
   
    node.link = index;
    index = node;
    count++;
    
  }

}
