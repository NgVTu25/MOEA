import java.util.List;
import java.util.Set;

public static void main(String[] args) {
    int n = 7;

    NQueensProblem problem = new NQueensProblem(n);

    org.moeaframework.algorithm.NSGAII algorithm = new org.moeaframework.algorithm.NSGAII(problem);

    int maxGenerations = 1000;
    for (int i = 0; i < maxGenerations; i++) {
        algorithm.step();
    }

    Set<List<Integer>> validSolutions = problem.getValidSolutions();

    System.out.println("SOLUTIONS " + validSolutions.size() + " ");
    for (List<Integer> solution : validSolutions) {
        System.out.println(solution);
    }
}