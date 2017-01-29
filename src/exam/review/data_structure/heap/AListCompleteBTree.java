package exam.review.data_structure.heap;

import exam.review.data_structure.BTNodeADT;
import exam.review.data_structure.BTreeADT;
import exam.review.data_structure.TreeNode;

import java.util.ArrayList;
import java.util.Iterator;

/** todo: finish this
 * Created by shanwu on 17-1-29.
 * ArrayList implementation of complete binary tree
 */
public class AListCompleteBTree<T> implements CompleteBTreeADT<T> {
    private ArrayList<T> mHolding;

    @Override
    public int size() {
        return mHolding.size();
    }

    @Override
    public boolean isEmpty() {
        return mHolding.isEmpty();
    }

    @Override
    public Iterator<TreeNode<T>> iterator() {
        return mHolding.iterator();
    }

    @Override
    public void replace(TreeNode<T> oldOne, T newValue) {

    }

    @Override
    public void addNewNode(TreeNode<T> parent, TreeNode<T> child) {

    }

    @Override
    public TreeNode<T> root() {
        return null;
    }

    @Override
    public TreeNode<T> findNode(T target) {
        return null;
    }

    @Override
    public TreeNode<T> parent(TreeNode<T> v) {
        return null;
    }

    @Override
    public Iterable<TreeNode<T>> children(TreeNode<T> v) {
        return null;
    }

    @Override
    public boolean isInternal(TreeNode<T> v) {
        return false;
    }

    @Override
    public boolean isExternal(TreeNode<T> v) {
        return false;
    }

    @Override
    public boolean isRoot(TreeNode<T> v) {
        return false;
    }

    @Override
    public BTNodeADT<T> getRoot() {
        return null;
    }

    @Override
    public void addRoot(BTNodeADT<T> node) {

    }

    @Override
    public void insertLeft(BTNodeADT<T> target, BTNodeADT<T> node) {

    }

    @Override
    public void insertRight(BTNodeADT<T> target, BTNodeADT<T> node) {

    }

    @Override
    public void remove(BTNodeADT<T> target) {

    }

    @Override
    public void attach(BTNodeADT<T> target, BTreeADT<T> left, BTreeADT<T> right) {

    }

    @Override
    public BTNodeADT<T> add(T element) {
        return null;
    }

    @Override
    public T remove() {
        return null;
    }

    private static class AListBTNode<E> extends BT<E> {
        private E mElement;
        private int mIndex;
    }
}