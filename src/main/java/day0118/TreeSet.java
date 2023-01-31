package day0118;

import model.BoardDTO;

public class TreeSet implements Set {
    public int[] array = new int[0];
    public BoardDTO[] boardArray = new BoardDTO[0];

    @Override
    public void add(int element) {
        int[] temp = new int[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            temp[i] = array[i];
        }
        temp[array.length] = element;
        array = temp;

        sort();
    }

    @Override
    public boolean contains(int element) {
        return indexOf(element) != -1;
    }

    @Override
    public int indexOf(int element) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == element) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void set(int index, int element) {
        array[index] = element;
    }

    @Override
    public void removeByIndex(int index) {
        int[] temp = new int[array.length - 1];
        for (int i = 0; i < index; i++) {
            temp[i] = array[i];
        }

        for (int i = index; i < array.length; i++) {
            temp[i - 1] = array[i];
        }

        array = temp;
    }

    @Override
    public void removeByElement(int element) {
        if (contains(element)) {
            removeByIndex(indexOf(element));
        }
    }

    public void sort() {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                int temp = array[i];
                array[i] = array[i + 1];
                array[i + 1] = temp;
                i = -1;
            }
        }
    }
}
