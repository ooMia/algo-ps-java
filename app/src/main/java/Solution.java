class Solution {
    String solution(String initialWord, Dict[] dicts) {
        String answer = "No Jam"; // default

        double maxEfficiency = 0;
        for (Dict dict : dicts) {
            if (contains(dict.word, initialWord)) {
                var eff = efficiency(initialWord, dict.word, dict.score);
                if (eff <= maxEfficiency) continue;
                maxEfficiency = eff;
                answer = dict.word;
            }
        }

        return answer;
    }

    private boolean contains(String source, String target) {
        int iSource = 0, iTarget = 0;
        while (iSource < source.length() && iTarget < target.length()) {
            if (source.charAt(iSource) == target.charAt(iTarget)) {
                iTarget++;
            }
            iSource++;
        }
        return iTarget == target.length();
    }

    private double efficiency(String from, String to, int score) {
        int nCharAdded = to.length() - from.length();
        return (double) score / nCharAdded;
    }
}