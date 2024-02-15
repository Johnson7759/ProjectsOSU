package components.queue;
/**
 * @authors
 *  Yoojin Jeong.358
 *  David-Benoit Tano.8
 *  Sean Yan.1074
 *  Noel Johnson.7759
 *  Alex Fornes.4
 */

/**
 * {@code WaitingLine} enhanced with secondary methods.
 *
 * @param <T>
 *            type of {@code WaitingLine} entries
 */
public interface WaitingLine<T> extends WaitingLineKernel<T> {

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
    int findPosition(T entry);

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
    T removeEntry(T customer);

}
