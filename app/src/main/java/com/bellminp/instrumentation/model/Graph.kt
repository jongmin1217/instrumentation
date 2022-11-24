package com.bellminp.instrumentation.model

import com.bellminp.common.timberMsg
import com.github.mikephil.charting.data.Entry
import timber.log.Timber
import java.util.*
import kotlin.collections.ArrayList

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
) : GraphData {

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
)

data class GraphPointType1(
    val time: Long,
    val value: Double?
)

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
) : GraphData {
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
)

data class GraphPointType2(
    val vpos: Double,
    val value: Double?
)

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

interface GraphData