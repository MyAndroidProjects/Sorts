package com.lopatin.sorts.model

import java.lang.reflect.Constructor

enum class Sort constructor(val sortName: String) {
    BUBBLE("Пузырьковая"),
    SELECTION("Выбором"),
    INSERTION("Вставками"),
    MERGE("Слиянием"),
    QUICK_SORT("Быстрая"),
    SHELL("Шелла"),
    TREE("Двоичным деревом");

    companion object {
        fun getSortBySortName(sortName: String?): Sort {
            sortName ?: return BUBBLE
            var curSort = BUBBLE
            for (value in values()) {
                if (sortName == value.sortName) {
                    curSort = value
                }
            }
            return curSort
        }
    }

}