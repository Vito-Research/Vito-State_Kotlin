import java.util.*

@OptIn(ExperimentalStdlibApi::class)
class AlertLvl {
    var state: Level

    init {
        this.state = Level.Zero
        this.resetAlert()
    }

    data class Alert(val clusterCount: Int = 0, var hr: IntArray = intArrayOf()) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Alert

            if (clusterCount != other.clusterCount) return false
            if (!hr.contentEquals(other.hr)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = clusterCount
            result = 31 * result + hr.contentHashCode()
            return result
        }
    }

    enum class Level(var alert: Alert = Alert()) {
        Zero,
        One,
        Two,
        Three,
        Four,
        Five,
    }

    fun returnAlert(): Int {
        return when (state) {
            Level.Five -> {
                // Log.i("", "1")
                1
            }
            else -> {
                0
            }
        }
    }

    fun resetAlert() {
        this.state = Level.Zero
        this.state.alert.hr = intArrayOf()
    }

    fun med(list: List<Double>) = list.sorted().let {
        if (it.size % 2 == 0)
            ((it[it.size / 2] + it[(it.size - 1) / 2]) / 2).toFloat()
        else
            it[it.size / 2].toFloat()
    }

    fun calculateMedian(hr: Int) {
        this.state.alert.hr += hr
        val frozenState = state

        when (state) {
            Level.Five -> {
                val median = med(list = state.alert.hr.map { it.toDouble() })

                when {
                    hr >= (median + 4) -> this.state = Level.Five
                    (hr == (median + 3).toInt()) -> this.state = Level.Three
                    else -> this.state = Level.Zero
                }
                return
            }
            Level.Four -> {
                val median = med(list = state.alert.hr.map { it.toDouble() })
                when {
                    hr >= (median + 4) -> this.state = Level.Five
                    (hr == (median + 3).toInt()) -> this.state = Level.Three
                    else -> this.state = Level.Zero
                }
                return
            }
            Level.Three -> {
                val median = med(list = state.alert.hr.map { it.toDouble() })
                when {
                    hr >= (median + 4) -> this.state = Level.Four
                    (hr == (median + 3).toInt()) -> this.state = Level.Three
                    else -> this.state = Level.Zero
                }
                return
            }
            Level.Two -> {
                // state.alert.hr += hr
                val median = med(list = state.alert.hr.map { it.toDouble() })
                when {
                    hr >= (median + 4) -> this.state = Level.Five
                    (hr == (median + 3).toInt()) -> this.state = Level.Three
                    else -> this.state = Level.Zero
                }
                return
            }
            Level.One -> {
                val median = med(list = state.alert.hr.map { it.toDouble() })
                when {
                    hr >= (median + 4) -> this.state = Level.Four
                    (hr == (median + 3).toInt()) -> this.state = Level.Three
                    else -> this.state = Level.Zero
                }
                return
            }
            Level.Zero -> {
                val median = med(list = state.alert.hr.map { it.toDouble() })
                if(state.alert.hr.size > 3) {
                    when {
                        hr >= (median + 4) -> this.state = Level.Two
                        (hr == (median + 3).toInt()) -> this.state = Level.One
                        else -> this.state = Level.Zero
                    }
                }
                return
            }
        }
    }

    data class HeathData(
        val Start_Date: Date,
        var Start_Time: String,
        var hr: Double,
        var risk: Int
    )

    fun sortingSelection(array: MutableList<Int>): MutableList<Int> {
        for (i in 0 until array.size - 1) {
            var min = i
            for (j in i + 1 until array.size) {
                if (array[j] < array[min]) {
                    min = j
                }
            }

            val temp = array[i]
            array[i] = array[min]
            array[min] = temp
        }

        return array
    }

    fun median(array: MutableList<Int>): Double {
        sortingSelection(array)

        return if (array.size % 2 == 0) {
            ((array[array.size / 2] + array[array.size / 2 - 1]) / 2).toDouble()
        } else {
            (array[array.size / 2]).toDouble()
        }
    }
}