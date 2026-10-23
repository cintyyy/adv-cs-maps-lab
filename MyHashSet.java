public class MyHashSet<E>{
    private Object[] hashArray;
    private int size;
    public MyHashSet(){
        hashArray = new Object[9000];
        size = 0;
    }

    public boolean add(E obj) {
        int index = obj.hashCode();
        if (hashArray[index] == null) {
            hashArray[index] = obj;
            size++;
        }
        return true;
    }

    public void clear() {
        for (int i = 0; i < hashArray.length; i++){
            hashArray[i] = null;
            size--;
        }
    }

    public boolean contains(Object o) {
        int index = o.hashCode();
        if (hashArray[index] != null && hashArray[index].equals(o)) {
            return true;
        }
        return false;
    }

    public boolean remove(Object obj) {
        int location = obj.hashCode();
        if (hashArray[location].equals(obj)){
            hashArray[location] = null;
            size--;
            return true;
        }
        return false;
        
        
    }
    
    public int size() {
        return size;
    }

    public DLList<E> toDLList() {
        DLList<E> list = new DLList<E>();
        for (int i = 0; i < hashArray.length; i++){
            if (hashArray[i] != null){
                list.add((E) hashArray[i]);
            }
        }
        return list;
    }
}