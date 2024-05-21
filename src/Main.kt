import java.lang.Thread.sleep
import kotlin.concurrent.thread

suspend fun main() {

//    GlobalScope
    mtFun()
}


suspend fun mtFun() {
    println("Start")
    sleep(2000)
    println("Done")
}