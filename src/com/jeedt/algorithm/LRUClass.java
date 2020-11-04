package com.jeedt.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 设计LRU缓存结构，该结构在构造时确定大小，假设大小为K，并有如下两个功能
 *      set(key, value)：将记录(key, value)插入该结构
 *      get(key)：返回key对应的value值
 * [要求]
 *      1.set和get方法的时间复杂度为O(1)
 *      2.某个key的set或get操作一旦发生，认为这个key的记录成了最常使用的。
 *      3.当缓存的大小超过K时，移除最不经常使用的记录，即set或get最久远的。
 * 若opt=1，接下来两个整数x, y，表示set(x, y)
 * 若opt=2，接下来一个整数x，表示get(x)，若x未出现过或已被移除，则返回-1
 * 对于每个操作2，输出一个答案
 *
 * 示例1
 * 输入
 * [[1,1,1],[1,2,2],[1,3,2],[2,1],[1,4,4],[2,2]],3
 *
 * 返回值
 * [1,-1]
 *
 * 说明
 * 第一次操作后：最常使用的记录为("1", 1)
 * 第二次操作后：最常使用的记录为("2", 2)，("1", 1)变为最不常用的
 * 第三次操作后：最常使用的记录为("3", 2)，("1", 1)还是最不常用的
 * 第四次操作后：最常用的记录为("1", 1)，("2", 2)变为最不常用的
 * 第五次操作后：大小超过了3，所以移除此时最不常使用的记录("2", 2)，加入记录("4", 4)，并且为最常使用的记录，然后("3", 2)变为最不常使用的记录
 *
 * 备注:
 * 1≤K≤N≤10^5
 * −2×10^9≤x,y≤2×10^9
 *
 * 思路：双端链表+hashmap，map结构为<K,<K,V>>，链表结点<K,V>
 */
public class LRUClass {
    /**
     * lru design
     * @param operators int整型二维数组 the ops
     * @param k int整型 the k
     * @return int整型一维数组
     */
    public int[] LRU (int[][] operators, int k) {
        // write code here
        int len=operators.length;
        int resultSize=0;
        List<Integer> resultList=new ArrayList<>();
        LRUCache<Integer,Integer> cache=new LRUCache<>(k);
        for(int i=0;i<len;i++){
            int opType=operators[i][0];
            if(opType==1){
                cache.set(operators[i][1],operators[i][2]);
            }else if(opType==2){
                //输出
                Integer value=cache.get(operators[i][1]);
                resultList.add(value==null?-1:value);
                resultSize++;
            }
        }
        int[] result=new int[resultSize];
        for(int i=0;i<resultSize;i++){
            result[i]=resultList.get(i);
        }
        return result;
    }
    class LRUCache<K,V> {
        private final int MAX_CACHE_SIZE;
        private HashMap<K, DualListNode<K, V>> map;
        private DualListNode<K, V> first;
        private DualListNode<K, V> last;

        LRUCache(int size) {
            MAX_CACHE_SIZE = size;
            size=(int)(size/0.75+1);
            map = new HashMap<>(size);
        }

        //set
        public void set(K key, V value) {
            DualListNode<K,V> node = getNode(key);
            if (node == null) {
                if (map.size() >= this.MAX_CACHE_SIZE) {
                    map.remove(removeLast());
                }
                DualListNode<K,V> nodeNew = new DualListNode<>();
                nodeNew.key = key;
                nodeNew.value = value;
                moveToFirst(nodeNew);
                map.put(key, nodeNew);
            } else {
                node.value = value;
                moveToFirst(getNode(key));
            }
        }

        //get
        public V get(K key) {
            DualListNode<K, V> node = getNode(key);
            if (node == null) return null;
            moveToFirst(node);
            return node.value;
        }

        //moveToFirst node!=null
        private void moveToFirst(DualListNode<K, V> node) {
            if (first == null || last == null) {//first和last指向同一结点
                first = node;
                last = node;
                return;
            }else if(node==first){//头结点
                return;
            } else if(node == last){//尾结点
                last = last.pre;
            }
            //中间结点
            if (node.pre!=null&&node.next!=null){
                node.pre.next=node.next;
                node.next.pre=node.pre;
            }
            node.next = first;
            node.pre = null;
            first.pre = node;
            first = node;
        }

        //removeLast last!=null
        private K removeLast() {
            DualListNode<K,V> oldLast=last;
            last = last.pre;
            //判断last为空情况
            if (last == null) {
                first = null;
            } else {
                last.next = null;
            }
            return oldLast.key;
        }

        // getNode
        private DualListNode<K, V> getNode(K key) {
            return map.get(key);
        }

        class DualListNode<K, V> {
            public DualListNode<K, V> pre;
            public DualListNode<K, V> next;
            public K key;
            public V value;
        }
    }
    public static void main(String[] args) {
        int[][] arrInt=new int[][]{{1,1,1},{1,2,2},{1,3,2},{2,1},{1,4,4},{2,2}};
        int k=3;
        LRUClass lruClass=new LRUClass();
        int[] arr=lruClass.LRU(arrInt,k);
        System.out.println(Arrays.toString(arr));
    }
}
