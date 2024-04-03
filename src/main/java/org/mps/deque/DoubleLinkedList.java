package org.mps.deque;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * Marta Granado Rodriguez
 */

public class DoubleLinkedList<T> implements DoubleLinkedQueue<T> {

    private LinkedNode<T> first;
    private LinkedNode<T> last;
    private int size;

    public DoubleLinkedList() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    @Override
    public void prepend(T value) {
        LinkedNode<T> newNode = new LinkedNode<>(value, null, first);
        if (first == null) {
            last = newNode;
        } else {
            first.setPrevious(newNode);
        }
        first = newNode;
        size++;
    }

    @Override
    public void append(T value) {
        LinkedNode<T> newNode = new LinkedNode<>(value, last, null);
        if (last == null) {
            first = newNode;
        } else {
            last.setNext(newNode);
        }
        last = newNode;
        size++;
    }

    @Override
    public void deleteFirst() {
        if (size == 0) {
            throw new DoubleLinkedQueueException("Cannot delete from an empty deque");
        }
        first = first.getNext();
        if (first == null) {
            last = null;
        } else {
            first.setPrevious(null);
        }
        size--;
    }

    @Override
    public void deleteLast() {
        if (size == 0) {
            throw new DoubleLinkedQueueException("Cannot delete from an empty deque");
        }
        last = last.getPrevious();
        if (last == null) {
            first = null;
        } else {
            last.setNext(null);
        }
        size--;
    }

    @Override
    public T first() {
        if (size == 0) {
            throw new DoubleLinkedQueueException("Deque is empty");
        }
        return first.getItem();
    }

    @Override
    public T last() {
        if (size == 0) {
            throw new DoubleLinkedQueueException("Deque is empty");
        }
        return last.getItem();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        LinkedNode<T> current = first;
        for (int i = 0; i < index; i++) {
            current = current.getNext(); // looks for the value in all the LinkedNode
        }
        return current.getItem();
    }

    @Override
    public boolean contains(T value) {
        LinkedNode<T> current = first;
        while (current != null) {
            if (current.getItem().equals(value)) { // if it has thta value, it returns of
                return true;
            }
            current = current.getNext(); // else it will continue searching
        }
        return false;
    }

    @Override
    public void remove(T value) {
        LinkedNode<T> current = first;
        while (current != null) {
            if (current.getItem().equals(value)) {
                if (current == first) {
                    deleteFirst();
                } else if (current == last) {
                    deleteLast();
                } else {
                    current.getPrevious().setNext(current.getNext()); // elimina el actual
                    current.getNext().setPrevious(current.getPrevious());
                    size--;
                }
                return;
            }
            current = current.getNext();
        }
    }

    @Override
    public void sort(Comparator<? super T> comparator) {
        if (size > 1) {
            List<T> list = new ArrayList<>();
            LinkedNode<T> current = first;
            while (current != null) {
                list.add(current.getItem());
                current = current.getNext();
            }

            list.sort(comparator);

            current = first;
            for (T t : list) {
                current.setItem(t);
                current = current.getNext();
            }
        }
    }
}
