class Solution {
    public int solution(String bears, int k) {
        // 시약을 던져 얼음곰젤리 만들기
        // 노Y -> 분P -> 얼I -> 노Y
        // 시약을 던지면 반드시 k개의 곰젤리를 변화시켜야 함
        // 모두 얼I로 바꾸기 위해 필요한 최소 시약 개수
        // 전부 바꿀 수 없으면 -1 리턴

        // 예시: bears = "IPYIYP", k = 3
        // IIPYYP -> IIIPPP -> IIIIII (총 3개 시약 사용)

        // 예시: bears = "IY", k = 1
        // IY -> IP -> II (총 2개 시약 사용)

        // 예시: bears = "PPY", k = 2
        // 해당 배치에서는 모두 얼음곰젤리로 바꿀 수 없음 (-1 리턴)

        int answer = 0;
        return answer;
    }
}