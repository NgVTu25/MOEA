import org.moeaframework.core.Solution;
import org.moeaframework.core.variable.EncodingUtils;
import org.moeaframework.problem.AbstractProblem;

import java.util.*;
import java.util.stream.Collectors;

public class NQueensProblem extends AbstractProblem {

    private final int n;
    private final Set<List<Integer>> validSolutions;

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
            List<Integer> solutionList = Arrays.stream(variables).boxed().collect(Collectors.toList());
            validSolutions.add(solutionList);        }
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

    public Set<List<Integer>> getValidSolutions() {
        return validSolutions;
    }
}