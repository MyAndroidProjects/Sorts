package com.lopatin.sorts.model

/**
 * Корнем дерева будет начальный элемент последовательности.
 * Далее все элементы, меньшие корневого, располагаются в левом поддереве, все элементы, большие корневого, располагаются в правом поддереве.
 * Причем это правило соблюдается на каждом уровне.
 * После создания дерева обходим его in-order (left, root, right): обойти левое поддерево, корень, обойти правое поддерево
 */
class BinaryTree(
    val nodeValue: Int,
    var leftTree: BinaryTree? = null,
    var rightTree: BinaryTree? = null
) {
    companion object {
        fun createTree(startArray: Array<Int>): BinaryTree {
            var index = 0
            val size = startArray.size
            val rootTree = BinaryTree(startArray[index])
            index++
            while (index < size) {
                insertNode(startArray[index], rootTree)
                index++
            }
            return rootTree
        }

        private fun insertNode(insertValue: Int, node: BinaryTree?): BinaryTree {
            if (node == null) {
                return BinaryTree(insertValue)
            }
            if (insertValue < node.nodeValue) {
                node.leftTree = insertNode(insertValue, node.leftTree)
            } else {
                node.rightTree = insertNode(insertValue, node.rightTree)
            }
            return node
        }

        fun getArrayOrder(tree: BinaryTree): ArrayList<Int> {
            val list = ArrayList<Int>()
            fun getSortedArray(tree: BinaryTree) {
                tree.leftTree?.let { getSortedArray(it) }
                list.add(tree.nodeValue)
                tree.rightTree?.let { getSortedArray(it) }
            }
            getSortedArray(tree)
            return list
        }
    }
}