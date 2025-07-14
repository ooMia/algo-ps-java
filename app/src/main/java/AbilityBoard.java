class AbilityBoard implements IAbilityBoard {
    final int N;
    final int[][] S;

    public AbilityBoard(final int N, final int[][] s) {
        this.N = N;
        this.S = s;
    }

    @Override
    public int absScoreDiff(boolean[] teamCase) {
        int team1Score = 0, team2Score = 0;

        for (int i = 0; i < N; ++i) {
            var myTeam = teamCase[i];

            for (int j = i + 1; j < N; ++j) {
                if (i == j)
                    continue; // 자기 자신과의 능력치는 제외
                if (myTeam == teamCase[j]) {
                    // 같은 팀이면 능력치 합산
                    if (myTeam) {
                        team1Score += S[i][j] + S[j][i];
                    } else {
                        team2Score += S[i][j] + S[j][i];
                    }
                }
            }
        }
        return Math.abs(team1Score - team2Score);
    }

}

interface IAbilityBoard {
    int absScoreDiff(boolean[] teamCase);
}