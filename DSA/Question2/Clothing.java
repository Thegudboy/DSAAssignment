package Question2;

public class Clothing {

    public static int minMovesToEqualizeDresses(int[] sewingMachines) {
        int totalDresses = 0;
        int n = sewingMachines.length;

        for (int dresses : sewingMachines) {
            totalDresses += dresses;
        }

        if (totalDresses % n != 0) {
            return -1;
        }

        int targetDresses = totalDresses / n;
        int moves = 0;
        int balance = 0;

        for (int dresses : sewingMachines) {
            balance += dresses - targetDresses;
            moves = Math.max(moves, Math.abs(balance));
        }

        return moves;
    }

    public static void main(String[] args) {
        int[] sewingMachines = {1, 0, 5};
        int result = minMovesToEqualizeDresses(sewingMachines);
        System.out.println(result); // Output: 2
    }
}