package day0118;

public class HashSet implements Set {
    public int[] array = {};

    @Override
    public void add(int element) {
        if (!contains(element)) {
            int[] temp = new int[array.length + 1];
            for (int i = 0; i < array.length; i++) {
                temp[i] = array[i];
            }
            temp[array.length] = element;
            array = temp;
        }
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
        for (int i = index + 1; i < array.length; i++) {
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
}














