import org.moeaframework.core.Solution;
import org.moeaframework.core.variable.EncodingUtils;
import org.moeaframework.problem.AbstractProblem;

import java.util.*;
import java.util.stream.Collectors;

public class NQueensProblem extends AbstractProblem {

    private final int n;
    private final Set<List<String>> validSolutions;

    public NQueensProblem(int n) {
        super(n, 1);
        this.n = n;
        this.validSolutions = new HashSet<>();
    }

    @Override
    public void evaluate(Solution solution) {
        int[] variables = EncodingUtils.getPermutation(solution.getVariable(0));
        int conflicts = fitness(variables);

        solution.setObjective(0, conflicts);
        if (conflicts == 0) {
            List<String> solutionCoords = convertToBoard(variables);
            validSolutions.add(solutionCoords);  // Lưu tọa độ
        }
    }

    @Override
    public Solution newSolution() {
        Solution solution = new Solution(1, 1);
        solution.setVariable(0, EncodingUtils.newPermutation(n));
        return solution;
    }

    private int fitness(int[] queens) {
        int conflicts = 0;

        for (int i = 0; i < queens.length - 1; i++) {
            for (int j = i + 1; j < queens.length; j++) {
                if (Math.abs(queens[i] - queens[j]) == j - i) {
                    conflicts++;
                }
            }
        }

        return conflicts;
    }

    private List<String> convertToBoard(int[] queens) {
        List<String> coords = new ArrayList<>();
        for (int row = 0; row < queens.length; row++) {
            int col = queens[row];
            coords.add("(" + (row + 1) + ", " + (col + 1) + ")");
        }
        return coords;
    }

    public Set<List<String>> getValidSolutions() {
        return validSolutions;
    }
}