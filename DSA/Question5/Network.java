package Question5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Network {
    ArrayList<Integer> affected = new ArrayList<Integer>(); // stores final answer
    List<List<Integer>> network;// represents graph
    boolean visited[];// to make sure not to redo
    int noOfDevices;//
    int target;

    Network(int[][] link, int target) {

        this.target = target;// set target

        network = new ArrayList<>();

        this.noOfDevices = link.length;

        visited = new boolean[this.noOfDevices];

        for (int i = 0; i < noOfDevices; i++) {
            network.add(i, new ArrayList<>());// adding all devices to network
        }

        for (int i = 0; i < link.length; i++) {
            int[] connection = new int[2];
            for (int j = 0; j < link[i].length; j++) {

                connection[j] = link[i][j];
            }

            System.out.println("connection between " + Arrays.toString(connection));// check in treminal for all
                                                                                    // connections
            if (connection[0] != target && connection[1] != target) {
                addEdge(connection[0], connection[1]);
            }
        }
    }

    public void addEdge(int a, int b) {// make connection between the devices
        network.get(a).add(b);
        network.get(b).add(a);
    }

    public ArrayList<Integer> affectedDevices() {
        int startIndex = 0;
        if (startIndex == target) {// if 0 is target we dont visit 0
            startIndex = 1;
        }
        dfs(startIndex);

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {// if not visited

                if (i != target) {// no need to add the target device(as target will also not be visited we need
                                  // to filter it)
                    affected.add(i);
                }

            }
        }

        return affected;// all affected devices
    }

    public void dfs(int start) {
        Stack<Integer> stack = new Stack<>();

        stack.push(start);
        visited[start] = true;

        while (!stack.isEmpty()) {

            Integer node = stack.pop();

            List<Integer> neighboursList = network.get(node);// neighbouring devices

            for (Integer neighbour : neighboursList) {// for each loop
                if (!visited[neighbour]) {
                    stack.push(neighbour);
                    visited[neighbour] = true;
                }
            }
        }
    }




    public static void main(String[] args) {
        int[][] link = { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 1, 6 }, { 2, 4 }, { 4, 6 }, { 4, 5 }, { 5, 7 } };

        int target = 4;

        Network net = new Network(link, target);

        System.out.println("Afectted devices:" + (net.affectedDevices()));

    }

}