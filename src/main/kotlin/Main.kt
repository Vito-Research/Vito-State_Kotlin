
import java.io.File
fun main(args: Array<String>) {
    println("Hello spoiled brat!")
    listAssetFiles("/Users/andreas/IdeaProjects/Vito-State-Kotlin/src/main/resources")
}
private fun listAssetFiles(path: String): Boolean {
    var correct = 0
    var total = 0
    val list: Array<String>? = File(path).list()

    if (list!!.isNotEmpty()) {
        // This is a folder
        for (file in list) {
            if (file.contains("Vito")) {
                val alert = AlertLvl()
                alert.resetAlert()


                val inputString =
                    object {}.javaClass.getResourceAsStream(file)?.bufferedReader()?.readLines()
                val townList: List<String>? = inputString
                if (townList != null) {
                    for (item: String in townList) {
                        // Log.i("", item.toString())
                        val row: List<String> = item.split(",")

                        if (!row.contains("Heartrate") && row.indices.contains(2) && row[(2)].trim()
                                .toIntOrNull() != null
                        ) {
                            try {
                                if (row.last().trim().toInt() != null) {
                                    alert.calculateMedian(row[2].trim().toInt())
                                    //Log.i("iOS", alert.state.alert.hr.last().toString())

                                    if (alert.returnAlert() == row.last().trim().toInt()) {
                                        correct += 1
                                        total += 1
                                    } else {


                                        total += 1
                                    }
                                } else {

                                }
                                // Log.i("", alert.returnAlert().toString())
                            } catch (e: Exception) {

                            }
                        }
                    }
                }
            }
        }
    }
    println(((correct).toDouble() / total.toDouble()).toString())

    return true
}
