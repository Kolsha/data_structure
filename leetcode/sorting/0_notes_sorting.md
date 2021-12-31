## [Sorting](https://leetcode.com/problems/sort-an-array/)
![image](https://user-images.githubusercontent.com/5952279/146661274-0b39a773-8428-4e34-99d0-fbdaf349d83e.png)

---

#### Comparison sorts: Sort input array by comparing elements
1. <a class="nav-link" href="#insert_sort">Insertion sort</a>
2. <a class="nav-link" href="#merge_sort">Merge sort</a>
3. <a class="nav-link" href="#quick_sort">Quick sort</a>
4. <a class="nav-link" href="#heap_sort">Heap sort</a>
   
#### Sort input array by means other than comparing elements

5. <a class="nav-link" href="#count_sort">Counting sort</a>
6. <a class="nav-link" href="#radix_sort">Radix sort</a>
7. <a class="nav-link" href="#bucket_sort">Bucket sort</a>
---


<h4 id="insert_sort">Insertion sort</h4>

[<h4 id="merge_sort">Merge sort</h4>](https://www.baeldung.com/java-merge-sort)
```java
class Solution {
    public int[] sortArray(int[] nums) {
        mergeSort(nums, nums.length);
        return nums;
    }
    
    private void mergeSort(int[] nums, int len) {
        if(len < 2) {
            return;
        }
        int mid = len / 2;
        int[] left = new int[mid];
        int[] right = new int[len - mid];
        
        for(int i = 0; i < mid; i++) {
            left[i] = nums[i];
        }
        
        for(int i = mid; i < len; i++) {
            right[i - mid] = nums[i];
        }
        
        mergeSort(left, mid);
        mergeSort(right, len - mid);

        merge(nums, left, right, mid, len - mid);
    }
    
    private void merge(int[] nums, int[] left, int[] right, int lMax, int rMax) {
        int i = 0, j = 0, n = 0;
        while(i < lMax && j < rMax) {
            if(left[i] <= right[j]) {
                nums[n++] = left[i++];
            } else {
                nums[n++] = right[j++];
            }
        }
        
        while(i < lMax) {
            nums[n++] = left[i++];
        }
        
        while(j < rMax) {
            nums[n++] = right[j++];
        }
        
    }
}
```
<h4 id="heap_sort">Heap sort</h4>

#### Heap
In place sorting algorithm, applying heap data structure.

[<h4 id="quick_sort">Quick sort</h4>](https://www.baeldung.com/java-quicksort)

```java
class Solution {
    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length-1);
        return nums;
    }

    public void quickSort(int arr[], int begin, int end) {
        if(begin > end) {
            return;
        }
        int partitionIndex = partition(arr, begin, end);

        quickSort(arr, begin, partitionIndex-1);
        quickSort(arr, partitionIndex+1, end);
    }
    

   //  This function takes last element as pivot, places
   // the pivot element at its correct position in sorted
   //  array, and places all smaller (smaller than pivot)
   // to left of pivot and all greater elements to right
   // of pivot
    private int partition(int arr[], int begin, int end) {
        // pivot (Element to be placed at right position)
        int pivot = arr[end];

        // Index of smaller element and indicates the 
        // right position of pivot found so far
        int i = begin - 1;

        for (int j = begin; j < end; j++) {
            if (arr[j] < pivot) {
                // Increment index of smaller element
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, end);
        return i + 1;
    }
    
    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
```

<h4 id="count_sort">Counting sort</h4>

<h4 id="radix_sort">Radix sort</h4>

<h4 id="bucket_sort">Bucket sort</h4>
```java
class Solution {
    public int[] sortArray(int[] nums) {
        int n = 50000;
        // -50000 <= nums[i] <= 50000
        int[] sort = new int[n * 2 + 1];
        int[] result = new int[nums.length];
        for (int i : nums) {
            sort[i + n]++;
        }
        int i = 0;
        int j = 0;
        while (i < sort.length && j < result.length) {
            if (sort[i] != 0) {
                result[j++] = i - n;
                sort[i]--;
            } else {
                i++;
            }
        }
        
        return result;
    }
}
```

