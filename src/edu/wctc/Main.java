package edu.wctc;

public class Main {

    public static void main(String[] args) {
        String carby = "carby";
        String tomatoey = "tomatoey";
        String cheesy = "cheesy";

        VennDiagram<String> foodDiagram = new VennDiagram<>(carby, tomatoey, cheesy);

        foodDiagram.add("Croissant", carby);
        foodDiagram.add("Roll", carby);
        foodDiagram.add("Toast", carby);
        foodDiagram.add("Grilled Cheese", carby, cheesy);
        foodDiagram.add("Mac and Cheese", carby, cheesy);
        foodDiagram.add("Cheese and Crackers", carby, cheesy);
        foodDiagram.add("Bagel and Cream Cheese", carby, cheesy);
        foodDiagram.add("Spaghetti Marinara", carby, tomatoey);
        foodDiagram.add("Tomato Sandwich", carby, tomatoey);
        foodDiagram.add("Lasagna", carby, tomatoey, cheesy);
        foodDiagram.add("Tomato Soup and Goldfish Crackers", carby, tomatoey, cheesy);
        foodDiagram.add("Pizza Margherita", carby, tomatoey, cheesy);
        foodDiagram.add("Tomato and Mozzarella Sandwich", carby, tomatoey, cheesy);
        foodDiagram.add("Tomato Slices", tomatoey);
        foodDiagram.add("Tomato Wedges", tomatoey);
        foodDiagram.add("Grape Tomatoes", tomatoey);
        foodDiagram.add("Caprese Salad", tomatoey, cheesy);
        foodDiagram.add("Greek Salad", tomatoey, cheesy);
        foodDiagram.add("Mozzarella Sticks", cheesy);
        foodDiagram.add("String Cheese", cheesy);
        foodDiagram.add("Cheese Cubes", cheesy);
        foodDiagram.add("Fresh Mozzarella", cheesy);

        // carby OR tomatoey
        IO.println(foodDiagram.unionOf(carby, tomatoey));
        // tomatoey AND cheesy
        IO.println(foodDiagram.intersectionOf(tomatoey, cheesy));
        // cheesy but NOT carby
        IO.println(foodDiagram.complementOf(cheesy, carby));
        // all three
        IO.println(foodDiagram.diagramCenter());

        String evens = "evens";
        String primes = "primes";
        String fibonaccis = "Fibonaccis";

        VennDiagram<Integer> numberDiagram = new VennDiagram<>(evens, primes, fibonaccis);

        for (int number : new int[] {2, 4, 6, 8, 10}) {
            numberDiagram.add(number, evens);
        }
        for (int number : new int[] {2, 3, 5, 7}) {
            numberDiagram.add(number, primes);
        }
        for (int number : new int[] {1, 2, 3, 5, 8}) {
            numberDiagram.add(number, fibonaccis);
        }

        IO.println(numberDiagram.unionOf(primes, evens));
        IO.println(numberDiagram.intersectionOf(primes, fibonaccis));
        IO.println(numberDiagram.complementOf(fibonaccis, evens));
        IO.println(numberDiagram.diagramCenter());
    }
}
