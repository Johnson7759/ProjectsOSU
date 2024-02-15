package components.queue;

import java.util.Iterator;

/**
 * @authors
 *  Yoojin Jeong.358
 *  David-Benoit Tano.8
 *  Sean Yan.1074
 *  Noel Johnson.7759
 *  Alex Fornes.4
 */

/**
 * Layered implementations of secondary methods for {@code WaitingLine}.
 *
 * <p>
 * Assuming execution-time performance of O(1) for method {@code iterator} and
 * its return value's method {@code next}. Execution-time performance of
 * {@code replaceEntry} as implemented in this class is O(|{@code this}|).
 * Execution-time performance of {@code append} as implemented in this class is
 * O(|{@code q}|).
 *
 * @param <T>
 *            type of {@code WaitingLine} entries
 */
public abstract class WaitingLineSecondary<T> implements WaitingLine<T> {

    /*
     * Common methods (from Object) -------------------------------------------
     */

    @Override
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Queue<?>)) {
            return false;
        }
        Queue<?> q = (Queue<?>) obj;
        if (this.lengthOfLine() != q.length()) {
            return false;
        }
        Iterator<T> it1 = this.iterator();
        Iterator<?> it2 = q.iterator();
        while (it1.hasNext()) {
            T x1 = it1.next();
            Object x2 = it2.next();
            if (!x1.equals(x2)) {
                return false;
            }
        }
        return true;
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public int hashCode() {
        final int samples = 2;
        final int a = 37;
        final int b = 17;
        int result = 0;
        /*
         * This code makes hashCode run in O(1) time. It works because of the
         * iterator order string specification, which guarantees that the (at
         * most) samples entries returned by the it.next() calls are the same
         * when the two Queues are equal.
         */
        int n = 0;
        Iterator<T> it = this.iterator();
        while (n < samples && it.hasNext()) {
            n++;
            T x = it.next();
            result = a * result + b * x.hashCode();
        }
        return result;
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("<");
        Iterator<T> it = this.iterator();
        while (it.hasNext()) {
            result.append(it.next());
            if (it.hasNext()) {
                result.append(",");
            }
        }
        result.append(">");
        return result.toString();
    }

    /*
     * Other non-kernel methods -----------------------------------------------
     */

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    /**
     * Finds position of {@code entry} in {@code this}. Returns -1 if
     * {@code entry} not found
     *
     * @param entry
     *            {@code entry} position to be looked for
     * @return position of @entry in @this
     * @restores @this
     * @ensures position = place of customer in @this this = #this
     */
    @Override
    public int findPosition(T entry) {
        int length = this.lengthOfLine();
        int position = -1;
        for (int i = 0; i < length; i++) {
            if (this.frontOfLine().equals(entry)) {
                position = i;
            }
            this.addToLine(this.removeFrontOfLine());
        }
        return position;
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDEN
    /**
     * Removes the entry of {@code customer} and returns the entry. If no entry
     * is found, returns null.
     *
     * @param customer
     *            the customer to be removed
     * @return the customer removed
     * @updates this
     * @ensures this= this-customer
     *
     */
    @Override
    public T removeEntry(T customer) {
        T leftLine = null;
        int originalLength = this.lengthOfLine();
        for (int i = 0; i < originalLength; i++) {
            if (this.frontOfLine().equals(customer)) {
                leftLine = this.removeFrontOfLine();
            } else {
                this.addToLine(this.removeFrontOfLine());
            }
        }
        return leftLine;
    }

}