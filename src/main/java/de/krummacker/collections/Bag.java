package de.krummacker.collections;

import java.util.AbstractCollection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A Bag is a collection which, just like a list, can contain equal elements at
 * the same time, but does not, similar to a set, impose an order on the
 * contained elements.
 *
 * @param <E> the type of the contained elements
 */
public class Bag<E> extends AbstractCollection<E> {

    private E[] contents = (E[]) new Object[0];

    private int modCount = 0;

    /**
     * Ensures that this bag contains the specified element.
     * This collection permits (even multiple) null elements.<p>
     *
     * @param o element whose presence in this bag is to be ensured.
     * @return always <tt>true</tt>.
     */
    @Override
    public boolean add(E o) {
        E[] tmp = (E[]) new Object[contents.length + 1];
        System.arraycopy(contents, 0, tmp, 0, contents.length);
        tmp[contents.length] = o;
        contents = tmp;
        modCount++;
        return true;
    }

    @Override
    public Iterator<E> iterator() {
        return new BagIterator();
    }

    private class BagIterator implements Iterator<E> {

        private int index = 0;

        private boolean removeAlreadyCalled = false;

        private int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return index < contents.length;
        }

        @Override
        public E next() {

            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }

            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            removeAlreadyCalled = false;
            E result = contents[index];
            ++index;
            return result;
        }

        @Override
        public void remove() {

            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }

            if (index == 0 || removeAlreadyCalled) {
                throw new IllegalStateException();
            }

            E[] tmp = (E[]) new Object[contents.length - 1];
            System.arraycopy(contents, 0, tmp, 0, index - 1);
            System.arraycopy(contents, index, tmp, index - 1, contents.length
                    - index);
            contents = tmp;
            removeAlreadyCalled = true;
            modCount++;
            expectedModCount++;
        }
    }

    @Override
    public int size() {
        return contents.length;
    }
}
