import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.AbstractMap;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:
 *
 * Solution:
 *
 * @author peter
 * @date 2019/6/21.
 */
public class LRUCache extends AbstractMap {

    /**
     * 检查是否超期线程
     */
    private ExecutorService checkTimePool;

    private final static int MAX_SIZE = 1024;

    private final static ArrayBlockingQueue<Node> QUEUE = new ArrayBlockingQueue<>(MAX_SIZE);

    private final static int DEFAULT_ARRAY_SIZE = 1024;

    private int arraySize;

    private Node[] arrays;

    /**
     * 判断是否停止 flag
     */
    private volatile boolean flag = true;

    private final static Long EXPIRE_TIME = 60 * 60 * 1000L;

    private volatile AtomicInteger size = null;

    public LRUCache() {
        arraySize = DEFAULT_ARRAY_SIZE;
        arrays = new Node[arraySize];
        //开启一个线程检查最先放入队列的值是否超期
        executeCheckTime();
    }

    /**
     * 开启一个线程检查最先放入队列的值是否超期 设置为守护线程
     */
    private void executeCheckTime() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("check-thread-%d")
                .setDaemon(true)
                .build();
        checkTimePool = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(1),
                namedThreadFactory,
                new ThreadPoolExecutor.AbortPolicy());
        checkTimePool.execute(new CheckTimeThread());
    }

    @Override
    public Set<Entry> entrySet() {
        return super.keySet();
    }

    @Override
    public Object put(Object key, Object value) {
        int hash = hash(key);
        int index = hash % arraySize;
        Node currentNode = arrays[index];

        if (currentNode == null) {
            arrays[index] = new Node(null, null, key, value);

            //写入队列
            QUEUE.offer(arrays[index]);
            sizeUp();
        } else {
            Node nNode = currentNode;

            if (nNode.key == key) {
                currentNode.val = value;
            }

            while (nNode.next != null) {
                nNode = nNode.next;
                //key 存在 就覆盖 简单判断
                if (nNode.key == key) {
                    nNode.val = value;
                    break;
                } else {
                    //不存在就新增链表
                    sizeUp();
                    Node node = new Node(nNode, null, key, value);

                    //写入队列
                    QUEUE.offer(currentNode);
                    currentNode.next = node;
                }
            }
        }
        return null;
    }

    @Override
    public Object get(Object key) {

        int hash = hash(key);
        int index = hash % arraySize;
        Node currentNode = arrays[index];

        if (currentNode == null) {
            return null;
        }
        if (currentNode.next == null) {

            //更新时间
            currentNode.setUpdateTime(System.currentTimeMillis());

            //没有冲突
            return currentNode;
        }

        Node nNode = currentNode;
        while (nNode.next != null) {

            if (nNode.key == key) {

                //更新时间
                currentNode.setUpdateTime(System.currentTimeMillis());

                return nNode;
            }

            nNode = nNode.next;
        }
        return super.get(key);
    }


    @Override
    public Object remove(Object key) {

        int hash = hash(key);
        int index = hash % arraySize;
        Node currentNode = arrays[index];

        if (currentNode == null) {
            return null;
        }

        if (currentNode.key == key) {
            sizeDown();
            arrays[index] = null;

            //移除队列
            QUEUE.poll();
            return currentNode;
        }

        Node nNode = currentNode;
        while (nNode.next != null) {

            if (nNode.key == key) {
                sizeDown();
                //在链表中找到了 把上一个节点的 next 指向当前节点的下一个节点
                nNode.pre.next = nNode.next;
                nNode = null;

                //移除队列
                QUEUE.poll();

                return currentNode;
            }

            nNode = nNode.next;
        }
        return super.remove(key);
    }

    /**
     * 增加size
     */
    private void sizeUp() {
        //在put值时候认为里边已经有数据了
        flag = true;

        if (size == null) {
            size = new AtomicInteger();
        }
        int size = this.size.incrementAndGet();
        if (size >= MAX_SIZE) {
            //找到队列头的数据
            Node node = QUEUE.poll();
            if (node == null) {
                throw new RuntimeException("data error");
            }
            remove(node.key);
        }
    }

    /**
     * 数量减小
     */
    private void sizeDown() {

        if (QUEUE.size() == 0) {
            flag = false;
        }

        this.size.decrementAndGet();
    }

    @Override
    public int size() {
        return size.get();
    }

    /**
     * 链表
     */
    private class Node {

        private Node next;
        private Node pre;
        private Object key;
        private Object val;
        private Long updateTime;

        public Node(Node pre, Node next, Object key, Object val) {
            this.pre = pre;
            this.next = next;
            this.key = key;
            this.val = val;
            this.updateTime = System.currentTimeMillis();
        }

        public void setUpdateTime(Long updateTime) {
            this.updateTime = updateTime;
        }

        public Long getUpdateTime() {
            return updateTime;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", val=" + val +
                    '}';
        }
    }

    /**
     * copy HashMap 的 hash 实现
     */
    private int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    private class CheckTimeThread implements Runnable {
        @Override
        public void run() {
            while (flag) {
                Node node = QUEUE.poll();
                if (node == null) {
                    continue;
                }
                Long updateTime = node.getUpdateTime();

                if ((updateTime - System.currentTimeMillis()) >= EXPIRE_TIME) {
                    remove(node.key);
                }
            }
        }
    }
}
