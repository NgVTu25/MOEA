import java.util.List;
import java.util.Set;
public class Main {
public static void main(String[] args) {
    int n = 8;

    NQueensProblem problem = new NQueensProblem(n);

    org.moeaframework.algorithm.NSGAII algorithm = new org.moeaframework.algorithm.NSGAII(problem);

    int maxGenerations = 10000;
    for (int i = 0; i < maxGenerations; i++) {
        algorithm.step();
    }

    Set<List<String>> validSolutions = problem.getValidSolutions();

    System.out.println(STR."SOLUTIONS \{validSolutions.size()} ");
    for (List<String> solution : validSolutions) {
        System.out.println(solution);
        }
    }
}
