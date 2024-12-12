interface ArraySorter {
    double[] sort(double[] array);
}

class MergeSorter implements ArraySorter {
    @Override
    public double[] sort(double[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        return mergeSort(array, 0, array.length - 1);
    }

    private double[] mergeSort(double[] array, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            
            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);
            
            merge(array, left, middle, right);
        }
        return array;
    }

    private void merge(double[] array, int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        double[] leftArray = new double[n1];
        double[] rightArray = new double[n2];

        for (int i = 0; i < n1; ++i) {
            leftArray[i] = array[left + i];
        }
        for (int j = 0; j < n2; ++j) {
            rightArray[j] = array[middle + 1 + j];
        }

        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }
}

class OptimizedSorter implements ArraySorter {
    @Override
    public double[] sort(double[] array) {
        java.util.Arrays.sort(array);
        return array;
    }
}

public class SortingLab {
    public static void main(String[] args) {
        double[] testArray = {5.7, 2.3, 9.1, 1.6, 7.4, 3.8};

        ArraySorter mergeSorter = new MergeSorter();
        System.out.println("Merge Sort Result:");
        printArray(mergeSorter.sort(testArray.clone()));

        ArraySorter optimizedSorter = new OptimizedSorter();
        System.out.println("\nOptimized Sort Result:");
        printArray(optimizedSorter.sort(testArray.clone()));
    }

    private static void printArray(double[] arr) {
        for (double num : arr) {
            System.out.printf("%.2f ", num);
        }
        System.out.println();
    }
}