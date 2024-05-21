import java.util.*

object Optimal {
    // Function to implement optimal page replacement algorithm
    fun optimalPage(pg: IntArray, pn: Int, fn: Int) {
        // Create an array for given number of frames and initialize it as empty.

        val fr = IntArray(fn)
        Arrays.fill(fr, -1) // set all elements of fr to -1

        // Traverse through page reference array and check for miss and hit.
        var hit = 0
        for (i in 0..< pn) {
            // Page found in a frame: HIT

            var found = false
            for (j in 0..< fn) {
                if (fr[j] == pg[i]) {
                    hit++
                    found = true
                    break
                }
            }
            if (found) continue

            // Page not found in a frame: MISS

            // If there is space available in frames.
            var emptyFrame = false
            for (j in 0 until fn) {
                if (fr[j] == -1) {
                    fr[j] = pg[i]
                    emptyFrame = true
                    break
                }
            }
            if (emptyFrame) continue

            // Find the page to be replaced.
            var farthest = -1
            var replaceIndex = -1
            for (j in 0..< fn) {
                var k = i + 1
                while (k < pn) {
                    if (fr[j] == pg[k]) {
                        if (k > farthest) {
                            farthest = k
                            replaceIndex = j
                        }
                        break
                    }
                    k++
                }
                if (k == pn) {
                    replaceIndex = j
                    break
                }
            }
            fr[replaceIndex] = pg[i]
        }

        println("No. of hits = $hit")
        println("No. of misses = " + (pn - hit))
    }

    // Driver code
    @JvmStatic
    fun main(args: Array<String>) {
        val pg = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5)
        val pn = pg.size
        val fn = 4
        optimalPage(pg, pn, fn)
    }
}
