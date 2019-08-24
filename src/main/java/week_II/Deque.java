package week_II;

/* *****************************************************************************
 *  Name:  Yohanes Tadesse
 *  Date:  August 08-2019
 *  Description: Assignment - II: Deck
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int count;

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        count = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return null == first;
    }

    // return the number of items on the deque
    public int size() {
        return count;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (null == item) throw new IllegalArgumentException();
        Node element = new Node(item);
        if (null != first) {
            element.next = first;
            first.pre = element;
        }
        if (null == last)
            last = element;
        first = element;
        count++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (null == item) throw new IllegalArgumentException();
        Node element = new Node(item);
        if (null != last) {
            last.next = element;
            element.pre = last;
        }

        if (null == first)
            first = element;
        last = element;
        count++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (null == first) throw new NoSuchElementException();
        Node item = first;

        if (null == first.next) {
            first = null;
            last = null;
        } else {
            first = first.next;
            first.pre = null;
            item.next = null;
        }
        count--;
        return item.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (null ==  last) throw new NoSuchElementException();
        Node item = last;
        if (null == last.pre) {
            last = null;
            first = null;
        } else {
            last = last.pre;
            last.next = null;
            item.pre = null;
        }
        count--;
        return item.item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        Node current = first;

        public boolean hasNext() {
            return (null != current);
        }

        public Item next() {
            if (hasNext()) {
                Node node = current;
                current = current.next;
                return node.item;
            }
            throw new NoSuchElementException();
        }

        public void remove() {

        }
    }

    // unit testing (required)

    public static void main(String[] args) {
        final Deque<String> d = new Deque<>();

        StdOut.println(d.isEmpty());
        d.addFirst("1");
        StdOut.println(d.isEmpty());
        StdOut.println(d.removeFirst());
        StdOut.println(d.isEmpty());
        d.addLast("2");
        StdOut.println(d.isEmpty());
        StdOut.println(d.removeFirst());
        StdOut.println(d.isEmpty());
        d.addFirst("1");
        d.addFirst("2");
        d.addFirst("3");

        final Iterator<String> iterator = d.iterator();

        while (true) {
            if (!iterator.hasNext()) break;
            String next = iterator.next();
            StdOut.println(next);
        }


    }
    private class Node {
        private final Item item;
        private Node next;
        private Node pre;

        public Node(Item item) {
            this.item = item;
            pre = null;
            next = null;
        }

    }

}




