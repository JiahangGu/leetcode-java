//快排
class Solution {
    Random random = new Random();
    public int findKthLargest(int[] nums, int k) {
        k--;
        int l = 0, r = nums.length - 1;
        int idx = -1;
        while (idx != k) {
            idx = partition(nums, l, r);
            if (idx < k) {
                l = idx + 1;
            }
            else {
                r = idx - 1;
            }
        }
        return nums[k];
    }

    private int partition(int []nums, int l, int r) {
        int idx = random.nextInt(r - l + 1) + l;
        int pivot = nums[idx];
        swap(nums, idx, l);
        while (l < r) {
            while (l < r && nums[r] <= pivot) {
                r--;
            }
            if (l < r) {
                nums[l] = nums[r];
            }
            while (l < r && nums[l] > pivot) {
                l++;
            }
            if (l < r) {
                nums[r] = nums[l];
            }
        }
        nums[l] = pivot;
        return l;
    }

    private void swap(int []nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

//堆排序，最小堆复杂度O(nlgk)
class Solution {
    public int findKthLargest(int[] nums, int k) {
        int l = 0, r = nums.length - 1;
        int []heap = new int[k];
        if (k >= 0) System.arraycopy(nums, 0, heap, 0, k);
        buildMinHeap(heap, k);
        for (int i = k;i < nums.length;i++) {
            if (heap[0] < nums[i]) {
                heap[0] = nums[i];
                minHeapify(heap, 0, k);
            }
        }
        return heap[0];
    }

    private void buildMinHeap(int []nums, int size) {
        for (int i = (size - 1) / 2;i >= 0;i--) {
            minHeapify(nums, i, size);
        }
    }

    private void minHeapify(int []nums, int i, int size) {
        int lSon = 2 * i + 1;
        int parent = i;
        while (lSon < size) {
            if (lSon + 1 < size && nums[lSon] > nums[lSon + 1]) {
                lSon++;
            }
            if (nums[lSon] > nums[parent]) {
                break;
            }
            swap(nums, lSon, parent);
            parent = lSon;
            lSon = 2 * parent + 1;
        }
    }

    private void swap(int []nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}