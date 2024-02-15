package components.queue;

import components.standard.Standard;

/**
 * @authors
 *  Yoojin Jeong.358
 *  David-Benoit Tano.8
 *  Sean Yan.1074
 *  Noel Johnson.7759
 *  Alex Fornes.4
 */

/**
 * First-in-first-out (FIFO) WaitingList kernel component with primary methods.
 * (Note: by package-wide convention, all references are non-null.)
 *
 * @param <T>
 *            type of {@code WaitingListKernel} entries
 * @mathmodel type WaitingListKernel is modeled by string of T
 * @initially <pre>
 * default:
 *  ensures
 *   this = <>
 * </pre>
 * @iterator ~this.seen * ~this.unseen = this
 */
public interface WaitingLineKernel<T>
        extends Standard<WaitingLine<T>>, Iterable<T> {

    /**
     * Adds {@code x} to the end of {@code this}.
     *
     * @param x
     *            the entry to be added
     * @aliases reference {@code x}
     * @updates this
     * @ensures this = #this * <x>
     */
    void addToLine(T x);

    /**
     * Removes and returns the entry at the front of {@code this}.
     *
     * @return the entry removed
     * @updates this
     * @requires this /= <>
     * @ensures #this = <dequeue> * this
     */
    T removeFrontOfLine();

    /**
     * Reports length of {@code this}.
     *
     * @return the length of {@code this}
     * @ensures length = |this|
     */
    int lengthOfLine();

    /**
     * @return the front of {@code this}
     * @ensures frontOfLine = this.front
     */
    T frontOfLine();

}
