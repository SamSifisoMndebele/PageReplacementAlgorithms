internal object LRU {
    // Method to find page faults using indexes
    fun pageFaults(pages: IntArray, n: Int, capacity: Int): Int {
        // To represent set of current pages. We use
        // an unordered_set so that we quickly check
        // if a page is present in set or not
        val s = HashSet<Int>(capacity)

        // To store least recently used indexes
        // of pages.
        val indexes = HashMap<Int, Int>()

        // Start from initial page
        var page_faults = 0
        for (i in 0 until n) {
            // Check if the set can hold more pages
            if (s.size < capacity) {
                // Insert it into set if not present
                // already which represents page fault
                if (!s.contains(pages[i])) {
                    s.add(pages[i])

                    // increment page fault
                    page_faults++
                }

                // Store the recently used index of
                // each page
                indexes[pages[i]] = i
            } else {
                // Check if current page is not already
                // present in the set
                if (!s.contains(pages[i])) {
                    // Find the least recently used pages
                    // that is present in the set
                    var lru = Int.MAX_VALUE
                    var `val` = Int.MIN_VALUE

                    val itr: Iterator<Int> = s.iterator()

                    while (itr.hasNext()) {
                        val temp = itr.next()
                        if (indexes[temp]!! < lru) {
                            lru = indexes[temp]!!
                            `val` = temp
                        }
                    }

                    // Remove the indexes page
                    s.remove(`val`)
                    //remove lru from hashmap
                    indexes.remove(`val`)
                    // insert the current page
                    s.add(pages[i])

                    // Increment page faults
                    page_faults++
                }

                // Update the current page index
                indexes[pages[i]] = i
            }
        }

        return page_faults
    }

    // Driver method
    @JvmStatic
    fun main(args: Array<String>) {
        val pages = intArrayOf(7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2)

        val capacity = 4

        println(pageFaults(pages, pages.size, capacity))
    }
}

