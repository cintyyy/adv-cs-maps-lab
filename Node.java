public class Node<E>{
    private E data;
    private Node<E> next;
    private Node<E> prev;
    public Node(E data){
        this.data = data;
        next = null;
        prev = null;
    }

    public void setPrev(Node<E> prev){
        this.prev = prev;
    }
    public Node<E> next(){
        return next;
    }
    public E get(){
        return data;
    }

    public void setData(E data){
        this.data = data;
    }

    public Node<E> prev(){
        return prev;
    }
    public void setNext(Node<E> next){
        this.next = next;
    }
}