import java.util.*
import kotlin.collections.HashSet

// Method to find page faults using FIFO
fun pageFaultsFIFO(pages: IntArray, n: Int, capacity: Int): Int {
    // To represent set of current pages. We use
    // an unordered_set so that we quickly check
    // if a page is present in set or not
    val s = HashSet<Int>(capacity)

    // To store the pages in FIFO manner
    val indexes: Queue<Int> = LinkedList()

    // Start from initial page
    var pageFaults = 0
    for (i in 0..< n) {
        // Check if the set can hold more pages
        if (s.size < capacity) {
            // Insert it into set if not present
            // already which represents page fault
            if (!s.contains(pages[i])) {
                s.add(pages[i])

                // increment page fault
                pageFaults++

                // Push the current page into the queue
                indexes.add(pages[i])
            }
        } else {
            // Check if current page is not already
            // present in the set
            if (!s.contains(pages[i])) {
                //Pop the first page from the queue
                val `val` = indexes.peek()

                indexes.poll()

                // Remove the indexes page
                s.remove(`val`)

                // insert the current page
                s.add(pages[i])

                // push the current page into
                // the queue
                indexes.add(pages[i])

                // Increment page faults
                pageFaults++
            }
        }
    }

    return pageFaults
}

fun main() {
    val pages = intArrayOf(7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2)

    val capacity = 4
    println(pageFaultsFIFO(pages, pages.size, capacity))
}

