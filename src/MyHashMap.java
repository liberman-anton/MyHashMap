import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by 123 on 20.01.2016.
 */
public class MyHashMap implements Map {
    public static void main(String[] args) {
        Map m = new MyHashMap();

        for(int i=0;i<100;i++){
            m.put(i, "the" + i);
        }

        System.out.println();

    }

    static class Pair {
        Object key, value;
    }

    int size = 0;
    ArrayList<Pair>[] blocks;

    MyHashMap() {
        blocks = new ArrayList[2];
        for(int i=0;i<blocks.length;i++){
            blocks[i] = new ArrayList<>();
        }
    }

    MyHashMap(int sz) {
        blocks = new ArrayList[sz];
        for(int i=0;i<blocks.length;i++){
            blocks[i] = new ArrayList<>();
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean containsKey(Object key) {
        return getPair(key)!=null;
    }

    @Override
    public boolean containsValue(Object value) {
        for(int i=0;i<blocks.length;i++){
            int hc = value.hashCode();
            for(Pair p:blocks[i]){
                if (p.value.hashCode()==hc){
                    if (value.equals(p.value)){
                        return true;
                    }
                }

            }
        }
        return false;
    }

    @Override
    public Object get(Object key) {
        Pair pair = getPair(key);
        if (pair==null) return null;
        return pair.value;
    }

    private Pair getPair(Object key) {
        int hash = key.hashCode();
        int idx = hash%blocks.length;

        ArrayList<Pair> curBlock = blocks[idx];

        for(Pair p:curBlock){
            if(p.key.hashCode() == hash){
                if (p.key.equals(key)){
                    return p;
                }
            }
        }
        return null;
    }

    public void ensureCapacity() {
        if (size / blocks.length < 10) {
            return;
        }

        MyHashMap myHashMap = new MyHashMap(blocks.length * 2);

        for(int i=0;i<blocks.length;i++){
            for(Pair p:blocks[i]){
                myHashMap.put(p.key, p.value);
            }
        }
        this.blocks = myHashMap.blocks;

    }

    @Override
    public Object put(Object key, Object value) {

        ensureCapacity();

        int hash = key.hashCode();
        int idx = hash%blocks.length;

        ArrayList<Pair> curBlock = blocks[idx];

        for(Pair p:curBlock){
            if(p.key.hashCode() == hash){
                if (p.key.equals(key)){
                    p.key = key;
                    p.value = value;
                    return null;
                }
            }
        }

        Pair p = new Pair();
        p.key = key;
        p.value = value;

        curBlock.add(p);
        size++;
        return null;
    }

    @Override
    public Object remove(Object key) {
        int hash = key.hashCode();
        int idx = hash%blocks.length;

        ArrayList<Pair> curBlock = blocks[idx];

        for(int i=0;i<curBlock.size();i++){
            Pair p=curBlock.get(i);
            if(p.key.hashCode() == hash){
                if (p.key.equals(key)){
                    curBlock.remove(i);
                    return p.value;
                }
            }
        }
        return null;
    }

    @Override
    public void putAll(Map m) {
        for(Object key:m.keySet()){
            this.put(key, m.get(key));
        }

    }

    @Override
    public void clear() {

    }

    @Override
    public Set keySet() {
        return null;
    }

    @Override
    public Collection values() {
        return null;
    }

    @Override
    public Set<Entry> entrySet() {
        return null;
    }
}
