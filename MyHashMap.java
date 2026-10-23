public class MyHashMap<K,V>{
    private Object[] hashArray;
    private int size;
    private MyHashSet<K> keySet;
    public MyHashMap(){
        size = 0;
        keySet = new MyHashSet<K>();
        hashArray = new Object[676];
    }
    @SuppressWarnings("unchecked")
    public V put(K key, V value){
        int index = key.hashCode();
        V prevValue = (V) hashArray[index];
        hashArray[index] = value;
        if (prevValue == null){
            size++;
            keySet.add(key);

        }
        return prevValue;

    }
    
    @SuppressWarnings("unchecked")
    public V get(Object key){
        return (V) hashArray[key.hashCode()];
    }
    
    @SuppressWarnings("unchecked")
    public V remove(Object key){
        int index = key.hashCode();
        if (hashArray[index] != null) {
            keySet.remove(key);
            hashArray[index] = null;
            size--;

            return (V) key;
        }
        return null;
    }
    
    public int size(){
        return size;
    }
    public MyHashSet<K> keySet(){
        return keySet;
    }
}