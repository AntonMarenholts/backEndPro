package hw.GroupSumHW06.src.ait.numbers.model;

import java.util.Arrays;

public class ParallelStreamGroupSum extends GroupSum{
    public ParallelStreamGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() {
        // TODO * ParallelStreamGroupSum, use parallel stream
        return Arrays.stream(numberGroups)
                .parallel()
                .mapToInt(group -> Arrays.stream(group).sum())
                .sum();
    }
}