package exam.review.data_structure.heap;

import exam.review.data_structure.BTNodeADT;
import exam.review.data_structure.BTreeADT;
import exam.review.data_structure.TreeNode;
import exam.review.data_structure.tool.MiscUtils;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * todo: finish this
 * Created by shanwu on 17-1-29.
 * ArrayList implementation of complete binary tree
 */
public class AListCompleteBTree<T> implements CompleteBTreeADT<T> {
    private ArrayList<TreeNode<T>> mHolding = new ArrayList<>();

    @Override
    public int size() {
        return mHolding.size();
    }

    @Override
    public boolean isEmpty() {
        return mHolding.size() == 1;
    }

    @Override
    public Iterator<TreeNode<T>> iterator() {
        return mHolding.iterator();
    }

    @Override
    public void replace(TreeNode<T> oldOne, T newValue) {
        oldOne.setElement(newValue);
    }

    @Override // warning: you will have to add new node on the designated node.
    public void addNewNode(TreeNode<T> parent, TreeNode<T> child) throws RuntimeException {
    }

    @Override
    public TreeNode<T> root() {
        if (!isEmpty()) {
            return mHolding.get(1);
        } else {
            throw new RuntimeException("Complete Binary Tree is empty!");
        }
    }

    @Override
    public TreeNode<T> findNode(T target) {
        for (TreeNode node : mHolding) {
            if (node.equals(target)) {
                return node;
            }
        }
        return null;
    }

    @Override
    public TreeNode<T> parent(TreeNode<T> v) {
        return v.getParent();
    }

    @Override
    public Iterable<TreeNode<T>> children(TreeNode<T> v) {
        return v.getChildren();
    }

    @Override
    public boolean isInternal(TreeNode<T> v) {
        return v.isInternal();
    }

    @Override
    public boolean isExternal(TreeNode<T> v) {
        return v.isExternal();
    }

    @Override
    public boolean isRoot(TreeNode<T> v) {
        return v.getParent() == null;
    }

    @Override
    public BTNodeADT<T> getRoot() {
        return (AListBTNode) root();
    }

    @Override
    public void addRoot(BTNodeADT<T> node) {
        if (!isEmpty()) {
            throw new RuntimeException("root node already exists!");
        }

        mHolding.add(1, node);
    }

    @Override // note: we will disable the function for complete binary tree
    public void insertLeft(BTNodeADT<T> target, BTNodeADT<T> node) throws RuntimeException {
    }

    @Override // note: we will disable the function for complete binary tree
    public void insertRight(BTNodeADT<T> target, BTNodeADT<T> node) throws RuntimeException {
    }

    @Override
    public void remove(BTNodeADT<T> target) {
        AListBTNode castTarget = null;
        if (target instanceof AListBTNode) {
            castTarget = (AListBTNode) target;
        }

        Iterator itr = mHolding.iterator();
        while (itr.hasNext()) {
            AListBTNode node = (AListBTNode) itr.next();
            if (node.equals(castTarget)) {
                itr.remove();
            }
        }
    }

    @Override // note: we will not have attach() function in complete binary tree
    public void attach(BTNodeADT<T> target, BTreeADT<T> left, BTreeADT<T> right) throws RuntimeException {
    }

    @Override
    public BTNodeADT<T> add(T element) {
        int index = mHolding.size() + 1;
        AListBTNode<T> newNode = new AListBTNode<T>(mHolding, index, element);
        mHolding.add(newNode);
        return newNode;
    }

    @Override
    public T remove() {
        if (!isEmpty()) {
            final int index = mHolding.size() - 1;
            return mHolding.remove(index).getElement();
        } else {
            throw new RuntimeException("the tree is empty");
        }
    }

    private static class AListBTNode<E> implements BTNodeADT<E> {
        private E mElement;
        private int mIndex;
        private ArrayList<TreeNode<E>> mHolding;

        public AListBTNode(ArrayList<TreeNode<E>> list, int index, E element) {
            setIndex(index);
            setElement(element);
            mHolding = list;
        }

        @Override
        public E getElement() {
            return mElement;
        }

        @Override
        public void setElement(E element) {
            mElement = element;
        }

        @Override
        public TreeNode<E> getParent() {
            if (mIndex == 1) {
                return null;
            }

            return mHolding.get(mIndex / 2-1);
        }

        @Override // note: we will not implement setParent method for complete binary tree
        public void setParent(TreeNode<E> parent) throws RuntimeException {
        }

        @Override
        public Iterable<TreeNode<E>> getChildren() {
            ArrayList<TreeNode<E>> resultList = new ArrayList<>();
            if (hasLeft()) {
                resultList.add(getLeft());
            }

            if (hasRight()) {
                resultList.add(getRight());
            }

            return resultList;
        }

        @Override // note: we will not implement setChild method for complete binary tree
        public void setChild(TreeNode<E> child) throws RuntimeException {
        }

        @Override
        public boolean isInternal() throws RuntimeException {
            return getLeft() != null || getRight() != null;
        }

        @Override
        public boolean isExternal() throws RuntimeException {
            return getLeft() == null && getRight() == null;
        }

        @Override
        public BTNodeADT<E> getLeft() {
            AListBTNode left = null;
            if (mHolding.size() > 2 * mIndex) {
                left = (AListBTNode) mHolding.get(mIndex * 2);
            }
            return left;
        }

        @Override
        public BTNodeADT<E> getRight() {
            AListBTNode right = null;
            if (mHolding.size() > 2 * mIndex + 1) {
                right = (AListBTNode) mHolding.get(mIndex * 2 + 1);
            }
            return right;
        }

        @Override
        public boolean hasLeft() throws RuntimeException {
            return getLeft() != null;
        }

        @Override
        public boolean hasRight() throws RuntimeException {
            return getRight() != null;
        }

        @Override // note we will not have setLeft method in complete binary tree
        public void setLeft(BTNodeADT<E> v) throws RuntimeException {
        }

        @Override // note we will not have setRight() mehtod in complete binary tree
        public void setRight(BTNodeADT<E> v) throws RuntimeException {
        }

        public int getIndex() {
            return mIndex;
        }

        public void setIndex(int index) {
            mIndex = index;
        }

    }

    public static void main(String[] args) {
        AListCompleteBTree<String> tree = new AListCompleteBTree();
        tree.add("1");
        tree.add("2");
        tree.add("3");
        tree.add("4");
        tree.add("5");
        tree.add("6");
        MiscUtils.prettyPrint(tree);
        System.out.println("remove last");
        tree.remove();
        MiscUtils.prettyPrint(tree);
        System.out.println("remove last");
        tree.remove();
        MiscUtils.prettyPrint(tree);
        System.out.println("remove last");
        tree.remove();
        MiscUtils.prettyPrint(tree);
        System.out.println("remove last");
        tree.remove();
        MiscUtils.prettyPrint(tree);
        System.out.println("remove last");
        tree.remove();
        MiscUtils.prettyPrint(tree);
        System.out.println("remove last");
        tree.remove();
        MiscUtils.prettyPrint(tree);
        System.out.println("remove last");
        tree.remove();
        MiscUtils.prettyPrint(tree);

    }
}