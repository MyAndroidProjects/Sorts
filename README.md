# Sorts

*Приложение создано в учебных целях.*

* [Описание приложения](#about)
* [Сортировки](#sorts)
  * [Пузырьковая](#bubble)
  * [Выбором](#selection)
  * [Вставками](#insertion)
  * [Слиянием](#merge)
  * [Быстрая](#quick)
  * [Шелла](#shell)
  * [Двоичным деревом](#tree)
* [Notes about views](#views)

### <a name="about"></a>Описание:
Приложение Сортировки.  Функционал приложения - вводятся целые числа, выбирается сортировка, после сортировки отображается отсортированный массив и введённый неотсортированный массив. 
Использовались **тесты**, для примера.

***
### <a name="sorts"></a>Сортировки:
#### <a name="bubble"></a>Пузырьковая:
Сравниваем каждый элемент с каждым, если текущий элемент больше, то меняем местами.

#### <a name="selection"></a>Выбором:
Берем ключ (первый элемент) выбираем из оставшейся части минимальное значение, если оно меньше ключа, 
то меняем местами в массиве с ключом, если больше или равно - то не меняем, получается сортировка устойчивая 
(не меняет относительный порядок сортируемых элементов, имеющих одинаковые ключи).

#### <a name="insertion"></a>Вставками:
Левый подмассив отсортирован (начинаем с 1-го элемента, т.к. один элемент отсортирован). 
i двигаем вправо и вычисляем новый ключ, потом этот ключ сравниваемм с левым отсортированным подмассивом (j = i-1 и двигаем влево) 
пока значения больше ключа сдвигаем их вправо, как только встретили значение меньше или равно - то в пустое место вставляем ключ.
 
#### <a name="merge"></a>Слиянием:
Разбиваем массив на два массива рекурсивно, пока не останется один элемент в массиве, 
потом при слиянии массивов сравниваем значения подмассивом и "складываем" в результирующий массив.

#### <a name="quick"></a>Быстрая:
Выбираем опорный элемент, ищем с левого индекса двигаясь вправо элемент больший чем опорный, 
с двигаясь с правого элемента влево - элемент, меньший, чем опорный, меняем найденные элементы местами, пока индексы не встретятся, потом рекурсивно просматриваем таким же образом подмассивы, где левый подмассив left=left, right = pivot-1 , а правый left = pivot+1 и right = right 

#### <a name="shell"></a>Шелла:
Усовершенствованная сортировка вставками. Проходит массив сортируя вставками прореженный
 подмассив (элементы отстают с шагом). Например, массив (5,1,3,8,4,2,7,9,0,6,15,11,12) шаг равен 3, 
 отсортировываются вставками внутри себя три подмассива(5,8,7,6,12) (1,4,9,15) (3,2,0,11) и так далее уменьшая
  шаг до 1 (при шаге 1 получается обычная сортировка вставками практически отсортированного массива). 
  По Кнуту шаг высчитывается как newStep = 3 * oldStep + 1, как только он будет больше или равный, чем размер, 
  то надо брать значение, которое высчитывалось два шага назад, последующие шаги брать делением на 3.
 
#### <a name="tree"></a>Двоичным деревом:
Корнем дерева будет начальный элемент последовательности. Далее все элементы, меньшие корневого, 
располагаются в левом поддереве, все элементы, большие корневого, располагаются в правом поддереве. 
Причем это правило соблюдается на каждом уровне. После создания дерева обходим его in-order (left, root, right): обойти левое поддерево, корень, обойти правое поддерево.

### <a name="views"></a>Notes about views:
**AppCompatTextView** (этот же функционал присутствует в material библиотеке):  
авторазмер (autoSizeText)  текста (устанавливается минимум, максимум, тип, также размер textView должен быть статичным)  
**BottomSheet**:  
Если использовать BottomSheetDialogFragment (его наследник), то пока BottomSheetDialog полностью не скроется будет происходить затемнение экрана
 (как при вызове диалогового окна). Если затемнение не требуется, в разметке придаем элементу BottomSheetBehavior, 
 который потом берем из view (BottomSheetBehavior.from<View>(bottomSheetText as View), подробности в MainActivity)
