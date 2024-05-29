import java.util.*

/**
 * Function to implement optimal page replacement algorithm
  */
fun optimalPageOpt(pg: IntArray, pn: Int, frameNum: Int) {
    // Create an array for given number of frames and initialize it as empty.

    val frameArr = IntArray(frameNum)
    Arrays.fill(frameArr, -1) // set all elements of frame number to -1

    // Traverse through page reference array and check for miss and hit.
    var hit = 0
    for (i in 0..< pn) {
        // Page found in a frame: HIT

        var found = false
        for (j in 0..< frameNum) {
            if (frameArr[j] == pg[i]) {
                hit++
                found = true
                break
            }
        }
        if (found) continue

        // Page not found in a frame: MISS

        // If there is space available in frames.
        var emptyFrame = false
        for (j in 0..< frameNum) {
            if (frameArr[j] == -1) {
                frameArr[j] = pg[i]
                emptyFrame = true
                break
            }
        }
        if (emptyFrame) continue

        // Find the page to be replaced.
        var farthest = -1
        var replaceIndex = -1
        for (j in 0..< frameNum) {
            var k = i + 1
            while (k < pn) {
                if (frameArr[j] == pg[k]) {
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
        frameArr[replaceIndex] = pg[i]
    }

    println("No. of hits = $hit")
    println("No. of misses = " + (pn - hit))
}

fun main() {
    val pg = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5)
    val pn = pg.size
    val fn = 4
    optimalPageOpt(pg, pn, fn)
}