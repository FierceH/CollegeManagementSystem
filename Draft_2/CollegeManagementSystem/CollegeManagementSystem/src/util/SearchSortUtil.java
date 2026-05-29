package util;

import model.Student;
import model.Unit;

import java.util.ArrayList;
import java.util.List;

public class SearchSortUtil {

    private SearchSortUtil() {
        // Utility class: prevents object creation.
    }

    // Q9 Searching algorithm 1: Linear search by student ID.
    public static int linearSearchStudentById(List<Student> students, String targetStudentId) {
        if (students == null || targetStudentId == null) {
            return -1;
        }
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId().equalsIgnoreCase(targetStudentId.trim())) {
                return i;
            }
        }
        return -1;
    }

    // Q9 Searching algorithm 2: Binary search by unit code. Input list must be sorted by unit code.
    public static int binarySearchUnitByCode(List<Unit> sortedUnits, String targetUnitCode) {
        if (sortedUnits == null || targetUnitCode == null) {
            return -1;
        }
        int low = 0;
        int high = sortedUnits.size() - 1;
        String target = targetUnitCode.trim().toUpperCase();

        while (low <= high) {
            int middle = low + (high - low) / 2;
            int comparison = sortedUnits.get(middle).getCode().compareToIgnoreCase(target);
            if (comparison == 0) {
                return middle;
            }
            if (comparison < 0) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }
        return -1;
    }

    // Q9 Sorting algorithm 1: Insertion sort by student full name.
    public static ArrayList<Student> insertionSortStudentsByName(List<Student> students) {
        ArrayList<Student> sorted = new ArrayList<>(students);
        for (int i = 1; i < sorted.size(); i++) {
            Student current = sorted.get(i);
            int j = i - 1;
            while (j >= 0 && sorted.get(j).getFullName().compareToIgnoreCase(current.getFullName()) > 0) {
                sorted.set(j + 1, sorted.get(j));
                j--;
            }
            sorted.set(j + 1, current);
        }
        return sorted;
    }

    // Q9 Sorting algorithm 2: Bubble sort by unit code.
    public static ArrayList<Unit> bubbleSortUnitsByCode(List<Unit> units) {
        ArrayList<Unit> sorted = new ArrayList<>(units);
        boolean swapped;
        for (int i = 0; i < sorted.size() - 1; i++) {
            swapped = false;
            for (int j = 0; j < sorted.size() - i - 1; j++) {
                if (sorted.get(j).getCode().compareToIgnoreCase(sorted.get(j + 1).getCode()) > 0) {
                    Unit temp = sorted.get(j);
                    sorted.set(j, sorted.get(j + 1));
                    sorted.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        return sorted;
    }
}
