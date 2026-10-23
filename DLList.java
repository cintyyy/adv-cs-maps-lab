public class DLList<E>{
    private Node<E> head;
    private Node<E> tail;
    private int size;
    public DLList(){
		head = new Node<E>(null);
		tail = new Node<E>(null);
        head.setNext(tail);
		tail.setPrev(head);
		size = 0;

    }
    public boolean add(E data){
        Node<E> newNode = new Node<E>(data);
        Node<E> lastNode = tail.prev(); 
        newNode.setNext(tail);
        newNode.setPrev(lastNode);
        lastNode.setNext(newNode);
        tail.setPrev(newNode);

        size++;
        return true;
    }
    public void add(int index, E data){
        if (index == size) {
            add(data);
            return;
        }
        Node<E> current = getNode(index);
        Node<E> newNode = new Node<E>(data);
        Node<E> before = current.prev();
        before.setNext(newNode);
        newNode.setPrev(before);
        newNode.setNext(current);
        current.setPrev(newNode);

        size++;
    }
    private Node<E> getNode(int index){
        if (index < size/2){
            Node<E> current = head.next();
            for (int i = 0; i < index; i++){
                current = current.next();
            }
            return current;
        }else{
            Node<E> current = tail.prev();
            for (int i = size-1; i > index; i--){
                current = current.prev();
            }
            return current;
        }
    }
    public boolean contains(Object obj){
        Node<E> current = head.next();
        for (int i = 0; i < size; i++){
            if (current.get().equals(obj)){
                return true;
            }
            current = current.next();
        }
        return false;
    }
    public E get(int index){
        return getNode(index).get();
    }

    public E set(int index, E e){
        Node<E> prevNode = getNode(index);
        E prevValue = prevNode.get();
        prevNode.setData(e);
        return prevValue;
    }

    @Override
    public String toString(){
        if (size == 0) {
            return "";
        }
        String result = "";
        Node<E> current = head.next();
        for (int i = 0; i < size - 1; i++) {
            result += current.get().toString() + ", ";
            current = current.next();
        }
        result += current.get().toString(); 
        
        return result;
    }

    
    public E remove(int index){
        Node<E> current;
        current = getNode(index);
        Node<E> removed = current;
        Node<E> before = current.prev();
        Node<E> after = current.next();
        before.setNext(after);
        after.setPrev(before);
        size--;
        return removed.get();
    }
    public int size(){
        return size;
    }
    public void scramble(){
        Object[] data = new Object[size];
        Node<E> current = head.next();
        for (int i = 0; i < size; i++) {
            data[i] = current.get();
            current = current.next();
        }
        for (int i = 0; i < size; i++){
            int randomIndex = (int)(Math.random() * size);
            Object temp = data[i];
            data[i] = data[randomIndex];
            data[randomIndex] = temp;
        }
        current = head.next();
        for (int i = 0; i < size; i++){
            current.setData((E) data[i]);
            current = current.next();
        }
    }   

    public boolean remove(Object obj){
        Node<E> current = head.next();
        for (int i = 0; i < size; i++){
            if (current.get().equals(obj)){
                Node<E> removed = current;
                Node<E> before = current.prev();
                Node<E> after = current.next();
                before.setNext(after);
                after.setPrev(before);
                size--;
                return true;
            }
            current = current.next();
        }
        return false;
    }
    public void clear(){
        head.setNext(tail);
        tail.setPrev(head);
        size = 0;
    }
}