package dale;

public class LongestRepCharSeq {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Please supply a string and number of replacements.");
            System.exit(-99);
        }
        String sequence = args[0];
        int k = Integer.valueOf(args[1]);

        if (k > sequence.length()) {
            System.out.println("k must be less than or equal to the sequence length.");
            System.exit(-98);
        }

        System.out.println("sequence: " + sequence + "\nk: " + k);
        int subSeqLen = getLongestRepeatingCharacterWithReplacement(sequence, k);
        System.out.println("Length: " + subSeqLen);
    }

    private static int getLongestRepeatingCharacterWithReplacement(String sequence, int k) {
        int[] charCounts = new int[26];
        int maxSeqLen = 0;
        int maxCount = 0;
        int seqLen = sequence.length();

        int subSeqStart = 0;
        for (int subSeqEnd = subSeqStart; subSeqEnd < seqLen; subSeqEnd++) {
            int outerIndex = sequence.charAt(subSeqEnd) - 'A'; //Fix pointer into counts array to be from zero
            charCounts[outerIndex]++;
            int currCount = charCounts[outerIndex];
            maxCount = Math.max(maxCount, currCount);

            while (subSeqEnd - subSeqStart - maxCount + 1 > k) {
                int innerIndex = sequence.charAt(subSeqStart) - 'A';
                charCounts[innerIndex]--;
                subSeqStart++;
            }
            maxSeqLen = Math.max(maxSeqLen, subSeqEnd - subSeqStart + 1);
        }
        return maxSeqLen;
    }
}
