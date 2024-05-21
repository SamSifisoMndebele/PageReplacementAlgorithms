internal object GFG {
    // Function to check whether a page exists
    // in a frame or not
    fun search(key: Int, fr: IntArray): Boolean {
        for (i in fr.indices) if (fr[i] == key) return true
        return false
    }

    // Function to find the frame that will not be used
    // recently in future after given index in pg[0..pn-1]
    fun predict(
        pg: IntArray, fr: IntArray, pn: Int,
        index: Int
    ): Int {
        // Store the index of pages which are going
        // to be used recently in future
        var res = -1
        var farthest = index
        for (i in fr.indices) {
            var j = index
            while (j < pn) {
                if (fr[i] == pg[j]) {
                    if (j > farthest) {
                        farthest = j
                        res = i
                    }
                    break
                }
                j++
            }

            // If a page is never referenced in future,
            // return it.
            if (j == pn) return i
        }

        // If all of the frames were not in future,
        // return any of them, we return 0. Otherwise
        // we return res.
        return if ((res == -1)) 0 else res
    }

    fun optimalPage(pg: IntArray, pn: Int, fn: Int) {
        // Create an array for given number of
        // frames and initialize it as empty.
        val fr = IntArray(fn)

        // Traverse through page reference array
        // and check for miss and hit.
        var hit = 0
        var index = 0
        for (i in 0 until pn) {
            // Page found in a frame : HIT

            if (search(pg[i], fr)) {
                hit++
                continue
            }

            // Page not found in a frame : MISS

            // If there is space available in frames.
            if (index < fn) fr[index++] = pg[i]
            else {
                val j = predict(pg, fr, pn, i + 1)
                fr[j] = pg[i]
            }
        }
        println("No. of hits = $hit")
        println("No. of misses = " + (pn - hit))
    }

    // driver function
    @JvmStatic
    fun main(args: Array<String>) {
        val pg = intArrayOf(7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2)
        val pn = pg.size
        val fn = 4
        optimalPage(pg, pn, fn)
    }
}

