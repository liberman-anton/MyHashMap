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
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public Object get(Object key) {
        int hash = key.hashCode();
        int idx = hash%blocks.length;

        ArrayList<Pair> curBlock = blocks[idx];

        for(Pair p:curBlock){
            if(p.key.hashCode() == hash){
                if (p.key.equals(key)){
                    return p.value;
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

        ArrayList[] newBlocks = new ArrayList[blocks.length*2];
        for(int i=0;i<newBlocks.length;i++){
            newBlocks[i] = new ArrayList<>();
        }

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
        return null;
    }

    @Override
    public void putAll(Map m) {

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
