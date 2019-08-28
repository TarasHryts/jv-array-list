package core.basesyntax;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List</p>
 */
public class ArrayList<T> implements List<T> {
    private Object[] arrayList;
    private int countElements;
    private int listSize;

    public int indexOf(T t) {
        for (int i = 0; i < arrayList.length; i++) {
            if (t.equals(arrayList[i])) {
                return i;
            }
        }
        return -1;
    }

    public ArrayList() {
        this.listSize = 10;
        this.arrayList = new Object[listSize];
        this.countElements = 0;
    }

    public void resize() {
        listSize *= 1.5;
        Object[] tempList = new Object[listSize];
        System.arraycopy(arrayList, 0, tempList, 0, countElements);
        arrayList = tempList;
    }

    public void resize(int elements) {
        listSize += elements + 4;
        Object[] tempList = new Object[listSize];
        System.arraycopy(arrayList, 0, tempList, 0, countElements);
        arrayList = tempList;
    }

    @Override
    public void add(T value) {
        if (countElements == listSize) {
            resize();
        }
        arrayList[countElements] = value;
        countElements++;
    }

    @Override
    public void add(T value, int index) {
        if (countElements == listSize) {
            resize();
        }
        if (index < countElements) {
            Object[] tempList = new Object[countElements - index + 1];
            System.arraycopy(arrayList, index, tempList, 0, countElements - index + 1);
            arrayList[index] = value;
            System.arraycopy(tempList, 0, arrayList, index + 1, countElements - index + 1);
            countElements++;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (countElements + list.size() > listSize) {
            resize(list.size());
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        return (T) arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < countElements) {
            arrayList[index] = value;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public T remove(int index) {
        T temp = (T) arrayList[index];
        if (index < countElements) {
            System.arraycopy(arrayList, index + 1, arrayList, index, arrayList.length - index - 1);
            arrayList[arrayList.length - 1] = null;
            countElements--;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
        return temp;
    }

    @Override
    public T remove(T t) {
        if (indexOf(t) == -1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        for (int i = 0; i < arrayList.length; i++) {
            if ((t != null) && (arrayList[i].equals(t))) {
                return remove(i);
            }
        }
        return null;
    }

    @Override
    public int size() {
        return countElements;
    }

    @Override
    public boolean isEmpty() {
        return countElements == 0;
    }
}
