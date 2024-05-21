//Java Program for Bankers Algorithm
class BankersAlgorithm {
    var n: Int = 5 // Number of processes
    var m: Int = 3 // Number of resources
    var need: Array<IntArray> = Array(n) { IntArray(m) }
    lateinit var max: Array<IntArray>
    lateinit var alloc: Array<IntArray>
    lateinit var avail: IntArray
    var safeSequence: IntArray = IntArray(n)

    fun initializeValues() {
        // P0, P1, P2, P3, P4 are the Process names here
        // Allocation Matrix
        alloc = arrayOf(
            intArrayOf(0, 1, 0),  //P0
            intArrayOf(2, 0, 0),  //P1
            intArrayOf(3, 0, 2),  //P2
            intArrayOf(2, 1, 1),  //P3
            intArrayOf(0, 0, 2) //P4
        )

        // MAX Matrix
        max = arrayOf(
            intArrayOf(7, 5, 3),  //P0
            intArrayOf(3, 2, 2),  //P1
            intArrayOf(9, 0, 2),  //P2
            intArrayOf(2, 2, 2),  //P3
            intArrayOf(4, 3, 3) //P4
        )

        // Available Resources
        avail = intArrayOf(3, 3, 2)
    }

    val isSafe: Unit
        get() {
            var count = 0

            //visited array to find the already allocated process
            val visited = BooleanArray(n)

            //work array to store the copy of available resources
            val work = IntArray(m)
            System.arraycopy(avail, 0, work, 0, m)

            while (count < n) {
                var flag = false
                for (i in 0 until n) {
                    if (!visited[i]) {
                        var j: Int
                        j = 0
                        while (j < m) {
                            if (need[i][j] > work[j]) break
                            j++
                        }
                        if (j == m) {
                            safeSequence[count++] = i
                            visited[i] = true
                            flag = true

                            j = 0
                            while (j < m) {
                                work[j] = work[j] + alloc[i][j]
                                j++
                            }
                        }
                    }
                }
                if (!flag) {
                    break
                }
            }
            if (count < n) {
                println("The System is UnSafe!")
            } else {
                //System.out.println("The given System is Safe");
                println("Following is the SAFE Sequence")
                for (i in 0 until n) {
                    print("Cannot convert element\n" +
                            "With text: ${safeSequence[i]}")
                    if (i != n - 1) print(" -> ")
                }
            }
        }

    fun calculateNeed() {
        for (i in 0 until n) {
            for (j in 0 until m) {
                need[i][j] = max[i][j] - alloc[i][j]
            }
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val gfg = BankersAlgorithm()

            gfg.initializeValues()
            //Calculate the Need Matrix
            gfg.calculateNeed()

            // Check whether system is in safe state or not
            gfg.isSafe
        }
    }
}
