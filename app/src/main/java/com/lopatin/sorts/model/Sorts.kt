package com.lopatin.sorts.model

import android.util.Log

private fun showArray(array: Array<Int>) {
    val sb = StringBuilder("")
    for (a in array) {
        sb.append(a.toString())
        sb.append(" ")
    }
}

fun bubbleSortDecrease(unsortedArray: Array<Int>): Array<Int> {
    val size = unsortedArray.size
    var j = 0
    while (j < size) {
        var i = 0
        while (i < size - 1) {
            i++
            if (unsortedArray[i] > unsortedArray[i - 1]) {
                val temp = unsortedArray[i]
                unsortedArray[i] = unsortedArray[i - 1]
                unsortedArray[i - 1] = temp
            }
        }
        j++
    }
    return unsortedArray
}

/**
 * Пузырьковая
 * сравниваем каждый элемент с каждым, если текущий элемент больше, то меняем местами
 */

fun Array<Int>.bubbleSortIncrease(): Array<Int> {
    val size = this.size
    var i = 0
    while (i < size) {
        var j = 0
        while (j < size - 1) {
            j++
            if (this[j] < this[j - 1]) {
                val temp = this[j]
                this[j] = this[j - 1]
                this[j - 1] = temp
            }
        }
        i++
    }
    return this
}

/**
 * сортировка выбором
 * берем ключ (первый элемент) выбираем из оставшейся части минимальное значение, если оно меньше ключа, то меняем местами в массиве с ключом,
 * если больше или равно - то не меняем (получается сортировка устойчивая)
 *
 */
fun Array<Int>.selectionSortIncrease(): Array<Int> {
    val array = this
    val size = array.size

    var i = 0
    while (i < size) {
        var minIndex = i
        var j = i

        while (j < size) {
            if (array[j] < array[minIndex]) {
                minIndex = j
            }
            j++
        }

        val temp = array[i]
        array[i] = array[minIndex]
        array[minIndex] = temp
        showArray(array)
        i++
    }

    return array
}

/**
 * сортировка вставками
 * левый подмассив отсортирован (начинаем с 1-го элемента, т.к. один элемент отсортирован)
 * i двигаем вправо и вычисляем новый ключ, потом этот ключ сравниваемм с левым отсортированным подмассивом (j = i-1 и двигаем влево)
 * пока значения больше ключа сдвигаем их вправо, как только встретили значение меньше или равно - то в пустое место вставляем ключ
 */

fun Array<Int>.insertionSortIncrease(): Array<Int> {
    val size = this.size
    var i = 1
    while (i < size) {
        var j = i - 1
        val key = this[i]
        while (j >= 0 && this[j] > key) {
            this[j + 1] = this[j]
            j--
        }
        this[j + 1] = key
        i++
    }
    return this
}


fun Array<Int>.mergeSort(): Array<Int> {
    return merge(this)
}

/**
 * разбиваем массив на два массива рекурсивно, пока не останется один элемент в массиве
 * потом при слиянии массивов сравниваем значения подмассивом и "складываем" в результирующий массив
 */
private fun merge(array: Array<Int>): Array<Int> {
    val size = array.size
    if (size < 2) {
        return array
    }

    val midIndex = (size - 1) / 2
    val leftArray = Array<Int>(midIndex + 1) { 0 }
    val rightArray = Array<Int>(size - (midIndex + 1)) { 0 }
    var i = 0
    while (i <= midIndex) {
        leftArray[i] = array[i]
        i++
    }

    var rIndex = 0
    while (i < size) {
        rightArray[rIndex] = array[i]
        i++
        rIndex++
    }
    val left = merge(leftArray)
    val right = merge(rightArray)
    return mergeArray(left, right)
}

private fun mergeArray(arrayLeft: Array<Int>, arrayRight: Array<Int>): Array<Int> {
    val leftSize = arrayLeft.size
    val rightSize = arrayRight.size
    val resultArraySize = leftSize + rightSize
    val resultArray = Array<Int>(resultArraySize) { 0 }
    var leftIndex = 0
    var rightIndex = 0
    var resIndex = 0
    while (resIndex < resultArraySize) {
        if (leftIndex < leftSize && rightIndex < rightSize) {
            if (arrayLeft[leftIndex] <= arrayRight[rightIndex]) {
                resultArray[resIndex] = arrayLeft[leftIndex]
                leftIndex++
            } else {
                resultArray[resIndex] = arrayRight[rightIndex]
                rightIndex++
            }
        } else if (leftIndex >= leftSize && rightIndex < rightSize) {
            resultArray[resIndex] = arrayRight[rightIndex]
            rightIndex++
        } else if (leftIndex < leftSize && rightIndex >= rightSize) {
            resultArray[resIndex] = arrayLeft[leftIndex]
            leftIndex++
        }
        resIndex++
    }

    return resultArray
}

/**
 * быстрая сортировка
 * выбираем опорный элемент
 * ищем с левого индекса двигаясь вправо элемент больший чем опорный, с двигаясь с правого элемента влево - элемент, меньший, чем опорный
 * меняем найденные элементы местами, пока индексы не встретятся
 * потом рекурсивно просматриваем таким же образом подмассивы, где левый подмассив left=left, right = pivot-1 , а правый left = pivot+1 и right = right
 */
fun Array<Int>.quickSort(): Array<Int> {
    quickSortIncrease(this, 0, this.size - 1)
    return this
}

private fun quickSortIncrease(array: Array<Int>, startLeftIndex: Int, startRightIndex: Int) {
    if (array.isEmpty())
        return //завершить выполнение если длина массива равна 0

    if (startLeftIndex >= startRightIndex)
        return //завершить выполнение если уже нечего делить

    // выбрать опорный элемент
    val middleIndex = startLeftIndex + (startRightIndex - startLeftIndex) / 2
    val pivot = array[middleIndex]

    // разделить на подмассивы, который больше и меньше опорного элемента
    var leftIndex = startLeftIndex
    var rightIndex = startRightIndex
    while (leftIndex <= rightIndex) {
        while (array[leftIndex] < pivot) {
            leftIndex++
        }

        while (array[rightIndex] > pivot) {
            rightIndex--
        }

        if (leftIndex <= rightIndex) {//меняем местами
            val temp = array[leftIndex]
            array[leftIndex] = array[rightIndex]
            array[rightIndex] = temp
            leftIndex++
            rightIndex--
        }
    }

    // вызов рекурсии для сортировки левой и правой части
    if (startLeftIndex < rightIndex)
        quickSortIncrease(array, startLeftIndex, rightIndex)

    if (startRightIndex > leftIndex)
        quickSortIncrease(array, leftIndex, startRightIndex)
}

/**
 * Сортировка двоичным деревом
 * Создаем дерево, обходим in-order (left- root - right)
 * подробности в class BinaryTree
 */
fun Array<Int>.treeSort(): Array<Int> {
    return if (this.isNullOrEmpty()) {
        Array<Int>(0) { 0 }
    } else {
        val tree = BinaryTree.createTree(this)
        val list = BinaryTree.getArrayOrder(tree)
        list.toTypedArray()
    }
}

/**
 * сортировка Шелла
 * усовершенствованная сортировка вставками
 * проходит массив сортируя вставками прореженный подмассив (элементы отстают с шагом)
 * например, массив (5,1,3,8,4,2,7,9,0,6,15,11,12) шаг равен 3, отсортировываются вставками внутри себя три подмассива(5,8,7,6,12) (1,4,9,15) (3,2,0,11)
 * и так далее уменьшая шаг до 1 (при шаге 1 получается обычная сортировка вставками практически отсортированного массива)
 * по Кнуту шаг высчитывается как newStep = 3 * oldStep + 1, как только он будет больше или равный, чем размер, то надо брать значение,
 * которое высчитывалось два шага назад, последующие шаги брать делением на 3
 */

fun Array<Int>.shellSort(): Array<Int> {
    val size = this.size
    var step = findStartStep(size)
    while (step > 0) {
        var i: Int = 0
        while (i < size) {
            var j = i - step
            val key = this[i]
            while (j >= 0 && this[j] > key) {
                this[j + step] = this[j]
                j -= step
            }
            this[j + step] = key
            i++
        }
        step /= 3
    }
    return this
}

private fun findStartStep(size: Int): Int {
    var index = 0
    var step = 1
    val stepList = ArrayList<Int>()
    stepList.add(step)
    index++
    while (step < size) {
        step = 3 * step + 1
        stepList.add(step)
        index++
    }
    index -= 2
    if (index < 0) {
        index = 0
    }
    return stepList[index]
}

