import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

class NumberContainers {
    private final Map<Integer, Integer> indexToNumber;
    private final Map<Integer, TreeSet<Integer>> numberToIndices;

    public NumberContainers() {
        indexToNumber = new HashMap<>();
        numberToIndices = new HashMap<>();
    }

    public void change(int index, int number) {
        if (indexToNumber.containsKey(index)) {
            int oldNumber = indexToNumber.get(index);
            if (oldNumber == number) {
                return; 
            }
            TreeSet<Integer> oldIndices = numberToIndices.get(oldNumber);
            oldIndices.remove(index);
            if (oldIndices.isEmpty()) {
                numberToIndices.remove(oldNumber);
            }
        }

        indexToNumber.put(index, number);
        numberToIndices.computeIfAbsent(number, k -> new TreeSet<>()).add(index);
    }

    public int find(int number) {
        TreeSet<Integer> indices = numberToIndices.get(number);
        if (indices == null || indices.isEmpty()) {
            return -1;
        }
        return indices.first(); 
    }
}