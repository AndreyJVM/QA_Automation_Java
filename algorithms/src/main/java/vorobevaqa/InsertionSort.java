package vorobevaqa;

public class InsertionSort {

    // Функция для сортировки массива с использованием сортировки вставкой
    public static void insertionSort(int[] array) {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            int key = array[i]; // Текущий элемент, который нужно вставить в отсортированную часть
            int j = i - 1;

            // Перемещаем элементы массива, которые больше key, на одну позицию вперёд
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            // Вставляем key в правильную позицию
            array[j + 1] = key;
        }
    }
}
