import java.io.*;
import java.util.*;

class Fruits3 {
    // Map from capacity to available basket indices
    static HashMap<Integer, LinkedList<Integer>> basketsGroupedByCapacity = new HashMap<>();
    static HashMap<Integer, Integer> capacitiesUpperBoundariesHash = new HashMap<>();
    static int[] sortedBaskets = null;

    public static int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        sortedBaskets = Arrays.copyOf(baskets, baskets.length);
        Arrays.sort(sortedBaskets);
        int unplaced = fruits.length;
        for (int i = 0; i < baskets.length; i++) {
            basketsGroupedByCapacity
                    .computeIfAbsent(baskets[i], k -> new LinkedList<>())
                    .add(i); // Store index just as ID, not really needed unless required
        }
        Arrays.sort(sortedBaskets);
        for (int fruitCapacityToBePlaced : fruits) {
            int indexOfExact = getIndexOfFirstAvailableBasketWithCapacity(fruitCapacityToBePlaced);
            int currentUpper, newUpper;
            currentUpper = newUpper = getUpperBoundary(fruitCapacityToBePlaced);
            int indexOfCurrentUpper = getIndexOfFirstAvailableBasketWithCapacity(currentUpper);
            while (indexOfCurrentUpper == -1 && currentUpper != -1) {
                newUpper = currentUpper = getUpperBoundaryCached(currentUpper);
                indexOfCurrentUpper = getIndexOfFirstAvailableBasketWithCapacity(currentUpper);
            }

            // Look for the next available upper boundary
            while (newUpper != -1) {
                newUpper = getUpperBoundaryCached(newUpper);
                int indexOfNewUpper = getIndexOfFirstAvailableBasketWithCapacity(newUpper);
                if (indexOfNewUpper != -1 && indexOfNewUpper < indexOfCurrentUpper) {
                    currentUpper = newUpper;
                    indexOfCurrentUpper = getIndexOfFirstAvailableBasketWithCapacity(currentUpper);
                }
            }

            int indexOfUpper = getIndexOfFirstAvailableBasketWithCapacity(currentUpper);

            if (indexOfExact != -1 && indexOfUpper != -1) {
                int minIndex = Math.min(indexOfExact, indexOfUpper);
                shiftBasket(minIndex == indexOfUpper ? currentUpper : fruitCapacityToBePlaced);
                unplaced--;
            } else if (indexOfExact != -1 || indexOfUpper != -1) {
                int maxIndex = Math.max(indexOfExact, indexOfUpper);
                shiftBasket(maxIndex == indexOfUpper ? currentUpper : fruitCapacityToBePlaced);
                unplaced--;
            }
        }
        return unplaced;
    }

    // Remove the first basket index from the corresponding LinkedList
    private static void shiftBasket(int basketCapacity) {
        basketsGroupedByCapacity.get(basketCapacity).pollFirst();  // Shift = remove first
    }

    private static int getIndexOfFirstAvailableBasketWithCapacity(int capacity) {
        LinkedList<Integer> list = basketsGroupedByCapacity.get(capacity);
        return (list != null && !list.isEmpty()) ? list.peekFirst() : -1;
    }

    // Finds the first greater capacity than target in sorted list
    private static int getUpperBoundary(int capacity) {
        int left = 0, right = sortedBaskets.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (sortedBaskets[mid] <= capacity) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return (left < sortedBaskets.length) ? sortedBaskets[left] : -1;
    }

    private static int getUpperBoundaryCached(int capacity) {
        if (capacitiesUpperBoundariesHash.containsKey(capacity))
            return capacitiesUpperBoundariesHash.get(capacity);
        int pos = getUpperBoundary(capacity);
        if (pos != -1)
            capacitiesUpperBoundariesHash.put(capacity, pos);
        return pos;
    }

    public static void main(String[] args) {
        String filePath = "src/fruits.txt";  // path to your input file
        int[] fruits = null;
        int[] baskets = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Read fruits
            String line = reader.readLine();
            if (line != null) {
                fruits = parseLineToIntArray(line);
            }

            // Read baskets
            line = reader.readLine();
            if (line != null) {
                baskets = parseLineToIntArray(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Use the arrays
        System.out.println("Fruits: " + Arrays.toString(fruits));
        System.out.println("Baskets: " + Arrays.toString(baskets));

        // Call your method here
        int result = numOfUnplacedFruits(fruits, baskets);
        System.out.println("Unplaced fruits: " + result);
    }

    private static int[] parseLineToIntArray(String line) {
        return Arrays.stream(line.split(","))
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .toArray();
    }

}