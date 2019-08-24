package week_II;

/** *****************************************************************************
 *  Name:   Yohanes Tadesse
 *  Date:   Aug 11, 2019
 *  Description:    Randomized Queue - Assignment II
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item> {

    // array with add at the end
    private Item[] array;
    private int last;
    private int first;
    private int count;

    // construct an empty randomized queue
    public RandomizedQueue() {
        array = (Item[]) new Object[2];
        last = -1;
        first = -1;
        count = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return count == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return count;
    }

    // add the item
    public void enqueue(Item item) {
        if (null == item) throw new IllegalArgumentException();
        if (first == -1)
            first = 0;
        if (last == -1)
            last = 0;

        if (last >= array.length)
            increaseArraySize();

        array[last] = item;
        last++;
        count++;
    }

    private void increaseArraySize() {
        int length = array.length;
        Item[] items = (Item[]) new Object[(length == 0 ? 1: length)* 2];
        int index = first;
        int i = 0;
        for (; index < length; i++) {
            items[i] = array[index];
            index++;
        }
        array = items;
        first = 0;
        last = i;
    }


    // remove and return a random item
    public Item dequeue() {
        if (count == 0 || first == -1) throw new NoSuchElementException();
        Item item = array[first];

        if (first == last) {
            first = 0;
            last = 0;
        }
        else {
            first++;
        }
        count--;
        if (first == last) {
            first = -1;
            last = -1;
            count = 0;
        }
        shrinkArray();
        return item;
    }

    private void shrinkArray() {
        int initialsize = 4;
        final int length = (array.length < initialsize ? initialsize : array.length);
        if (count + 1 == (int) (0.25 * length)) {
            Item[] ar = (Item[]) new Object[length / 2];
            int index = this.first;
            if (index == last) {
                array = ar;
                last = 0;
                first = 0;
                count = 0;
                return;
            }

            for (int i = 0; index < last; i++) {
                ar[i] = array[index];
                index++;
            }

            last = (last - first);
            array = ar;
            first = 0;
        }
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (count == 0) throw new NoSuchElementException();
        int uniform = StdRandom.uniform(first, last);
        return array[uniform];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomQueueIterator();
    }

    private class RandomQueueIterator implements Iterator<Item> {
        private final int[] random;
        private int index = 0;

        RandomQueueIterator() {
            random = new int[array.length];
            for (int i = 0; i < random.length; i++) {
                random[i] = i;
            }
            StdRandom.shuffle(random);
        }

        public boolean hasNext() {
            if (count == 0) return false;
            return random.length > index;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = array[random[index]];
            if (null == item) throw new NoSuchElementException();
            index++;
            return item;
        }

        public void remove() {

        }

    }

    public static void main(String[] args) {
        final RandomizedQueue<String> queue = new RandomizedQueue<>();


        for (int i = 0; i < 100; i++) {
            queue.enqueue("" + i);
        }

        for (int i = 0; i < 100; i++) {
            queue.dequeue();
            StdOut.println("dequeue: " + i + " count: " + queue.count + " first: " + queue.first + " last: " + queue.last);
        }

        queue.enqueue("1");
        queue.dequeue();
        queue.enqueue("2");
        queue.dequeue();
        queue.enqueue("3");
        queue.enqueue("3");
        queue.dequeue();
        StdOut.println("enqueue: beofre 500 starts :" + queue.size() + " first: " + queue.first+ " last: " + queue.last);

        for (int i = 0; i < 500; i++) {
            final int a = StdRandom.uniform(0, 2);
            String s;
            if (a == 0) {
                queue.enqueue("" + StdRandom.uniform());
                StdOut.println("enqueue: " + i + " count :" + queue.size() + " first: " + queue.first+ " last: " + queue.last);
            }
            else {
                try {
                    s = queue.dequeue();
                    StdOut.println("dequeue: " + i + " count :" + queue.size() + " first: " + queue.first+ " last: " + queue.last);
                } catch (NoSuchElementException ex) {
                    continue;
                }
                if (null == s)
                    StdOut.println("null found: " + s + " : " + i);
            }


        }
        final Iterator<String> iterator1 = queue.iterator();
        StdOut.println(iterator1.hasNext());

        StdOut.println(queue.isEmpty());
        queue.enqueue("1");
        StdOut.println(queue.isEmpty());
        queue.enqueue("2");
        StdOut.println(queue.size());
        queue.enqueue("3");
        StdOut.println(queue.size());
        StdOut.println(queue.dequeue());
        StdOut.println(queue.dequeue());
        StdOut.println(queue.dequeue());
        StdOut.println(queue.size());
        StdOut.println("before dequeu: " + " count :" + queue.size() + " first: " + queue.first+ " last: " + queue.last);

        for (int i = 0; i < 100; i++) {
            queue.dequeue();
            StdOut.println("dequeue: " + i + " count: " + queue.count + " first: " + queue.first + " last: " + queue.last);
        }
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        queue.enqueue("4");
        // queue.enqueue("5");
        // queue.enqueue("6");
        // queue.enqueue("7");
        // queue.enqueue("8");
        // queue.enqueue("9");
        // queue.enqueue("10");

        final Iterator<String> iterator2 = queue.iterator();
        final Iterator<String> iterator3 = queue.iterator();
        final Iterator<String> reference = iterator3;
        StdOut.println(iterator2.hasNext());
        StdOut.println(iterator3.hasNext());
        StdOut.println(reference.hasNext());


        StdOut.println(queue.sample());

        final Iterator<String> iterator = queue.iterator();

        while (iterator.hasNext()) {
            String next = iterator.next();
            StdOut.println(next);

        }


    }
}
