import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class FoodRatings {
    private static class Food {
        String name;
        String cuisine;
        int rating;

        Food(String name, String cuisine, int rating) {
            this.name = name;
            this.cuisine = cuisine;
            this.rating = rating;
        }
    }

    private final Map<String, Food> foodMap;
    private final Map<String, PriorityQueue<Food>> cuisineHeaps;

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        foodMap = new HashMap<>();
        cuisineHeaps = new HashMap<>();

        for (int i = 0; i < foods.length; i++) {
            Food food = new Food(foods[i], cuisines[i], ratings[i]);
            foodMap.put(foods[i], food);

            cuisineHeaps
                .computeIfAbsent(cuisines[i], k -> new PriorityQueue<>((a, b) -> {
                    if (a.rating != b.rating) {
                        return Integer.compare(b.rating, a.rating); 
                    }
                    return a.name.compareTo(b.name); 
                }))
                .offer(food);
        }
    }

    public void changeRating(String food, int newRating) {
        Food current = foodMap.get(food);

        Food updated = new Food(current.name, current.cuisine, newRating);
        foodMap.put(food, updated);
        cuisineHeaps.get(current.cuisine).offer(updated);
    }

    public String highestRated(String cuisine) {
        PriorityQueue<Food> pq = cuisineHeaps.get(cuisine);

        while (!pq.isEmpty()) {
            Food top = pq.peek();
            if (top.rating == foodMap.get(top.name).rating) {
                return top.name;
            }
            pq.poll();
        }

        return "";
    }
}