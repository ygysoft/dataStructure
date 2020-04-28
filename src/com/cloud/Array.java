package com.cloud;

import org.omg.CORBA.Object;

/**
 * Array
 *
 * @Descript: 数组
 * @Author: yanggy
 * @Date: 2020/4/28 16:10
 */
public class Array<E> {

    private E[] data;

    private int size;

    /**
     * 构造函数  构造容量为：capacity的数组
     *
     * @param capacity
     */
    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    /**
     * 构造初始容量为10的数组
     */
    public Array() {
        this(10);
    }

    /**
     * 获取元素个数
     */
    public int getSize() {
        return size;
    }

    /**
     * 获取元素容量
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * 数组是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 向数组后添加元素
     */
    public void addFirst(E e) {
        add(0, e);
    }


    /**
     * 向数组后添加元素
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 在index的位置，添加元素e
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Require: index >= 0 && index <= size");
        }

        if (size == data.length) {
            resize(2 * data.length);
        }

        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    /**
     * 获取index位置的元素
     */
    public E get(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Get failed. Require: index >= 0 && index <= size");
        }
        return data[index];
    }

    /**
     * 修改index位置的元素
     */
    public void set(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Set failed. Require: index >= 0 && index <= size");
        }
        data[index] = e;
    }

    /**
     * 数组是否包含元素e
     */
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (e.equals(data[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查找数组中第一个元素e所在的索引，如果不存在，则返回-1
     */
    public int findFirst(E e) {
        for (int i = 0; i < size; i++) {
            if (e.equals(data[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 查找数组中所有元素e所在的索引，如果不存在，则返回空Array
     */
    public Array findAll(E e) {
        Array arr = new Array();
        for (int i = 0; i < size; i++) {
            if (e.equals(data[i])) {
                arr.addLast(i);
            }
        }
        return arr;
    }

    /**
     * 删除指定位置的元素
     */
    public E remove(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Remove failed.");
        }

        if (size == data.length / 2) {
            resize(data.length / 2);
        }

        E e = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i -1] = data[i];
        }
        size--;
        return e;
    }

    /**
     * 删除第一个元素
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 删除最后一个元素
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 删除数组中第一个元素
     */
    public void removeFirstElement(E e) {
        int index = findFirst(e);
        if (index != -1) {
            remove(index);
        }
    }

    /**
     * 删除数组中所有的元素
     */
    public void removeAllElement(E e) {
        Array arr = findAll(e);
        if (arr.size != 0) {
            for (int i = 0; i < arr.size; i++) {
                int index = findFirst(e);
                remove(index);
            }
        }
    }


    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for(int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d, capacity = %d\n", size, data.length));
        res.append("[");
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1) {
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }

}
