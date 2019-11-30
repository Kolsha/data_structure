## Ch7. 数据结构复习笔记(Data Structures & Algorithms in Java, 4th)－Trees

### 一、树

树是一种以层次架构方式来储存数据的数据结构。除了根节点（Root）以外，其它节点都有一个父节点及可能会有子节点。若节点没有子节点，我们称其为 external node, 又称为 leaves；相反的，若我们称一节点为 internal，则该节点至少有个子节点。

[]()

![](https://img-blog.csdn.net/20161205141957419?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center)

树的常见接口：
```java
public interface TreeADT<T> {
    int size();
 
    boolean isEmpty();
 
    Iterator<TreeNode<T>> iterator();
 
    void replace(TreeNode<T> oldOne, T newValue);
 
    void addNewNode(TreeNode<T> parent, TreeNode<T> child);
 
    TreeNode<T> root();
 
    TreeNode<T> findNode(T target);
 
    TreeNode<T> parent(TreeNode<T> v);
 
    Iterable<TreeNode<T>> children(TreeNode<T> v);
 
    boolean isInternal(TreeNode<T> v);
 
    boolean isExternal(TreeNode<T> v);
 
    boolean isRoot(TreeNode<T> v);
}
```
树的节点API

```java
public interface TreeNode<T> {
    T getElement();
 
    void setElement(T element);
 
    TreeNode<T> getParent();
 
    void setParent(TreeNode<T> parent);
 
    Iterable<TreeNode<T>> getChildren();
 
    void setChild(TreeNode<T> child);
 
    boolean isInternal();
 
    boolean isExternal();

    // Return an iterable collection of the nodes
    Iterable<Position<T>> positions();
}
```

#### 节点深度及树的高度：
节点的`高度及深度`，指的是**自 v 到树的根的节点数，扣除 v 自身** :

```java
    public static <T> int getNodeDepth(TreeNode<T> node) {
        if (isNodeRoot(node)) {
            return 0;
        } else {
            return 1 + getNodeDepth(node.getParent());
        }
    }
```

`树的高`便是**所有节点高的最大值**:
```java
    public static <T> int getTreeHeight(Tree<T> treeRoot) {
        int height = 0;
        for(Position<T> v : treeRoot.positions()) {
            if(treeRoot.isExternal(v)) {
                h = Math.max(h, depth(T, v));
            }
        }
        return h;
    }
```
利用递归：
```java
public static <T> int getTreeHeight2(Tree<T> treeRoot, Position<T> v) {
    if(treeRoot.isExternal(v)) {
        return 0;
    }

    int h = 0;
    for(Position<T> w: treeRoot.children(v)) {
        h = Math.max(h, getTreeHeight2(treeRoot, w));
    }
    return 1 + h;
}
```
#### 遍历
前序遍历(Preorder Traversal): 一个节点先访问本身，再访问子节点。

![](https://img-blog.csdn.net/20161214172152557?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvc2hhbnd1MTk4NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center)

```java
    private static <T> void preorderTraversal(TreeNode<T> root) {
        System.out.print(root.getElement() + " ");
 
        for (TreeNode<T> childNodes : root.getChildren()) {
            preorderTraversal(childNodes);
        }
    }
```

后序遍历(Postorder Traversal): 先访问该节点的子节点，再访问该节点。

![](https://img-blog.csdn.net/20161214174851826?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvc2hhbnd1MTk4NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center)

```java
    private static <T> void postorderTraversal(TreeNode<T> root) {
        for (TreeNode<T> childNodes : root.getChildren()) {
            postorderTraversal(childNodes);
        }
 
        System.out.print(root.getElement() + " ");
    }
```

### 二、二叉树
若树具有以下特性，则称其为二叉树：
1. 每个节点最多有二个子节点。
2. 二个子节点分别为左子节点（left child）及右子节点（right child）
3. 子节点中，左子节点在右子节点的前方。

![](https://img-blog.csdn.net/20161214141210446?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvc2hhbnd1MTk4NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center)

二叉树常见接口：
```java
public interface BTreeADT<T> extends TreeADT<T> {
    BTNodeADT<T> getRoot();
 
    void addRoot(BTNodeADT<T> node);
 
    void insertLeft(BTNodeADT<T> target, BTNodeADT<T> node);
 
    void insertRight(BTNodeADT<T> target, BTNodeADT<T> node);
 
    void remove(BTNodeADT<T> target);
 
    void attach(BTNodeADT<T> target, BTreeADT<T> left, BTreeADT<T> right);
}
```

二叉树节点接口：
```java
public interface BTNodeADT<T> extends TreeNode<T> {
    BTNodeADT<T> getLeft();
 
    BTNodeADT<T> getRight();
 
    boolean hasLeft();
 
    boolean hasRight();
 
    void setLeft(BTNodeADT<T> v);
 
    void setRight(BTNodeADT<T> v);
}
```

我们可一用`递归的方式`定义二叉树。假设有二叉树T，则T有：
1. T的根节点。
2. 一个二叉树，T的左子树。
3. 一个二叉树，T的右子树。

中序遍历（Inorder Traversal）

![](https://img-blog.csdn.net/20161215002803525?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvc2hhbnd1MTk4NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center)

```java
    public static <T> void inorderTraversal(BTNodeADT<T> root) {
        if (root.hasLeft()) {
            inorderTraversal(root.getLeft());
        }
 
        System.out.print(root.getElement()+" ");
 
        if (root.hasRight()) {
            inorderTraversal(root.getRight());
        }
    }
```

二叉搜索树(Binary Search Tree)

若 Ｔ 为一二叉搜索树，则
1. 任一节点 v 的值可用 x(v) 代表。
2. 左子树所有任一值 <= x(v) 而右子树所有任一值 >= x(v)
3. T的所有 external Node 皆为空，不储存任何的值。