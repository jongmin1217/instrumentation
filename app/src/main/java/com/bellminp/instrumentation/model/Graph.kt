package com.bellminp.instrumentation.model

import com.bellminp.common.timberMsg
import com.github.mikephil.charting.data.Entry
import timber.log.Timber
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

data class GraphLegend(
    val list : List<LegendData>
) : GraphData

data class LegendData(
    val color : Int,
    val text : String
)

data class GraphType1(
    val managenum: String,
    val name: String,
    val hi1enable: Boolean,
    val hi2enable: Boolean,
    val hi3enable: Boolean,
    val low1enable: Boolean,
    val low2enable: Boolean,
    val low3enable: Boolean,
    val hi1: Double,
    val hi2: Double,
    val hi3: Double,
    val low1: Double,
    val low2: Double,
    val low3: Double,
    val ystep: Double,
    val reunit: String,
    val autorange: Boolean,
    val minrange: Double,
    val maxrange: Double,
    val list: List<GraphGroupPointType1>
) : GraphData,Serializable {

    fun getMax(): Double {
        val list = ArrayList<GraphPointType1>()

        this.list.forEach {
            it.list.forEach { data ->
                list.add(data)
            }
        }

        val valueList = ArrayList(list.map { it.value ?: 0.toDouble() })
        if (hi1enable) valueList.add(hi1)
        if (hi2enable) valueList.add(hi2)
        if (hi3enable) valueList.add(hi3)
        val max = Collections.max(valueList)
        val range = (max * 0.2)
        return if (max == 0.0) {
            10.0
        } else {
            max + if (range > 0) range else -range
        }
    }

    fun getMin(): Double {
        val list = ArrayList<GraphPointType1>()

        this.list.forEach {
            it.list.forEach { data ->
                list.add(data)
            }
        }

        val valueList = ArrayList(list.map { it.value ?: 0.toDouble() })
        if (low1enable) valueList.add(low1)
        if (low2enable) valueList.add(low2)
        if (low3enable) valueList.add(low3)
        val min = Collections.min(valueList)
        val range = (min * 0.2)
        return if (min == 0.0) {
            -10.0
        } else {
            min - if (range > 0) range else -range
        }
    }

    fun getMaxTime(): Long {
        val list = ArrayList<GraphPointType1>()

        this.list.forEach {
            it.list.forEach { data ->
                list.add(data)
            }
        }

        val valueList = list.map { it.time }
        return Collections.max(valueList)
    }

    fun getMinTime(): Long {
        val list = ArrayList<GraphPointType1>()

        this.list.forEach {
            it.list.forEach { data ->
                list.add(data)
            }
        }

        val valueList = list.map { it.time }
        return Collections.min(valueList)
    }

    fun getTime(): List<Long> {
        return list[0].list.map { it.time }
    }
}

data class GraphGroupPointType1(
    val name: String,
    val list: List<GraphPointType1>
) : Serializable

data class GraphPointType1(
    val time: Long,
    val value: Double?
) : Serializable

data class GraphType2(
    val managenum: String,
    val name: String,
    val hi1enable: Boolean,
    val hi2enable: Boolean,
    val hi3enable: Boolean,
    val low1enable: Boolean,
    val low2enable: Boolean,
    val low3enable: Boolean,
    val hi1: Double,
    val hi2: Double,
    val hi3: Double,
    val low1: Double,
    val low2: Double,
    val low3: Double,
    val ystep: Double,
    val reunit: String,
    val autorange: Boolean,
    val minrange: Double,
    val maxrange: Double,
    val list: List<GraphGroupPointType2>
) : GraphData,Serializable {
    fun getMax(): Double {
        val list = ArrayList<GraphPointType2>()

        this.list.forEach {
            it.list.forEach { data ->
                list.add(data)
            }
        }

        val valueList = ArrayList(list.map { it.value ?: 0.toDouble() })
        if (hi1enable) valueList.add(hi1)
        if (hi2enable) valueList.add(hi2)
        if (hi3enable) valueList.add(hi3)
        val max = Collections.max(valueList)
        val range = (max * 0.2)
        return if (max == 0.0) {
            10.0
        } else {
            max + if (range > 0) range else -range
        }
    }

    fun getMin(): Double {
        val list = ArrayList<GraphPointType2>()

        this.list.forEach {
            it.list.forEach { data ->
                list.add(data)
            }
        }

        val valueList = ArrayList(list.map { it.value ?: 0.toDouble() })
        if (low1enable) valueList.add(low1)
        if (low2enable) valueList.add(low2)
        if (low3enable) valueList.add(low3)
        val min = Collections.min(valueList)
        val range = (min * 0.2)
        return if (min == 0.0) {
            -10.0
        } else {
            min - if (range > 0) range else -range
        }
    }

    fun getMaxVpos(): Double {
        val list = ArrayList<GraphPointType2>()

        this.list.forEach {
            it.list.forEach { data ->
                list.add(data)
            }
        }

        val valueList = list.map { it.vpos }
        return Collections.max(valueList)
    }

    fun getMinVpos(): Double {
        val list = ArrayList<GraphPointType2>()

        this.list.forEach {
            it.list.forEach { data ->
                list.add(data)
            }
        }

        val valueList = list.map { it.vpos }
        return Collections.min(valueList)
    }

    fun getVpos(): List<Double> {
        return list[0].list.map { it.vpos }
    }
}

data class GraphGroupPointType2(
    val time: Long,
    val list: List<GraphPointType2>
) : Serializable

data class GraphPointType2(
    val vpos: Double,
    val value: Double?
) : Serializable

data class GraphType3(
    val xName: String,
    val name: String,
    val hi1enable: Boolean,
    val hi2enable: Boolean,
    val hi3enable: Boolean,
    val low1enable: Boolean,
    val low2enable: Boolean,
    val low3enable: Boolean,
    val hi1: Double,
    val hi2: Double,
    val hi3: Double,
    val low1: Double,
    val low2: Double,
    val low3: Double,
    val ystep: Double,
    val reunit: String,
    val autorange: Boolean,
    val minrange: Double,
    val maxrange: Double,
    val list: List<GraphGroupPointType3>
) : GraphData {
    fun getMax(): Double {
        val list = ArrayList<GraphPointType3>()

        this.list.forEach {
            it.list.forEach { data ->
                list.add(data)
            }
        }

        val valueList = ArrayList(list.map { it.value ?: 0.toDouble() })
        if (hi1enable) valueList.add(hi1)
        if (hi2enable) valueList.add(hi2)
        if (hi3enable) valueList.add(hi3)
        val max = Collections.max(valueList)
        val range = (max * 0.2)
        return if (max == 0.0) {
            10.0
        } else {
            max + if (range > 0) range else -range
        }
    }

    fun getMin(): Double {
        val list = ArrayList<GraphPointType3>()

        this.list.forEach {
            it.list.forEach { data ->
                list.add(data)
            }
        }

        val valueList = ArrayList(list.map { it.value ?: 0.toDouble() })
        if (low1enable) valueList.add(low1)
        if (low2enable) valueList.add(low2)
        if (low3enable) valueList.add(low3)
        val min = Collections.min(valueList)
        val range = (min * 0.2)
        return if (min == 0.0) {
            -10.0
        } else {
            min - if (range > 0) range else -range
        }
    }

    fun getMaxVpos(): Double {
        val list = ArrayList<GraphPointType3>()

        this.list.forEach {
            it.list.forEach { data ->
                list.add(data)
            }
        }

        val valueList = list.map { it.vpos }
        return Collections.max(valueList)
    }

    fun getMinVpos(): Double {
        val list = ArrayList<GraphPointType3>()

        this.list.forEach {
            it.list.forEach { data ->
                list.add(data)
            }
        }

        val valueList = list.map { it.vpos }
        return try {
            val distinctList = valueList.distinct()
            distinctList.sortedBy { it }
            val minValue = Collections.min(distinctList) + (distinctList[0] - distinctList[1])
            minValue
        }catch (e: Exception){
            Collections.min(valueList)
        }
    }

    fun getVpos(): List<Double> {
        return list[0].list.map { it.vpos }
    }
}

data class GraphGroupPointType3(
    val time: Long,
    val list: List<GraphPointType3>
)

data class GraphPointType3(
    val vpos: Double,
    val value: Double?
)

data class GraphType4(
    val xName: String,
    val name: String,
    val ystep: Double,
    val reunit: String,
    val autorange: Boolean,
    val minrange: Double,
    val maxrange: Double,
    val list: List<GraphGroupPointType4>,
    val standardList : List<GraphGroupPointStandard>
) : GraphData,Serializable{

    fun xMax() : Int{
        val valueList = ArrayList<Double>()
        list.forEach {
            it.list.forEach { data ->
                valueList.add(data.x)
            }
        }

        standardList.forEach {
            it.list.forEach { data ->
                valueList.add(data.x)
            }
        }

        val maxValue = Collections.max(valueList).toInt()

        return maxValue + (1000 - maxValue%1000)
    }

    fun xMin() : Int{
        val valueList = ArrayList<Double>()
        list.forEach {
            it.list.forEach { data ->
                valueList.add(data.x)
            }
        }

        standardList.forEach {
            it.list.forEach { data ->
                valueList.add(data.x)
            }
        }

        val minValue = Collections.min(valueList).toInt()

        return minValue - (1000 - minValue%1000)
    }

    fun yMax() : Int{
        val valueList = ArrayList<Double>()
        list.forEach {
            it.list.forEach { data ->
                valueList.add(data.y)
            }
        }

        standardList.forEach {
            it.list.forEach { data ->
                valueList.add(data.y)
            }
        }

        val maxValue = Collections.max(valueList).toInt()

        return maxValue + (1000 - maxValue%1000)
    }
}

data class GraphGroupPointType4(
    val time: Long,
    val list: List<GraphPointType4>
) : Serializable

data class GraphGroupPointStandard(
    val type: Int,
    val list: List<GraphPointType4>
) : Serializable

data class GraphPointType4(
    val x: Double,
    val y: Double,
    val initX : Double? = null,
    val initY : Double? = null
) : Serializable

data class GraphType5(
    val xName: String,
    val name: String,
    val ystep: Double,
    val reunit: String,
    val list: List<GraphGroupPointType5>
) : GraphData{
    fun getMax() : Double{
        val items = ArrayList<Double>()

        list.forEach {
            it.list.forEach { data ->
                items.add(data.y)
            }
        }

        return Collections.max(items)
    }
}

data class GraphGroupPointType5(
    val time: Long,
    val list: List<GraphPointType5>
)

data class GraphPointType5(
    val x: Int,
    val y: Double
)

interface GraphData : Serializable