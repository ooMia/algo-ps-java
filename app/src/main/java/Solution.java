class Solution {
    final StringBuilder sb = new StringBuilder(), buffer = new StringBuilder();
    boolean inTag = false;

    public String solution(String line) {
        // 반복문을 순회하는 동안 2개의 상태가 존재함
        // 1. tag 안에 있는 상태 : <를 만나면 시작하고, >를 만나면 끝나며 상태 저장
        // 2. tag 밖에 있는 상태 : tag 안에 있는 상태가 아니라면 기본 상태이고, 공백 또는 태그 시작을 만나면 끝나며 상태 저장
        for (int i = 0; i < line.length(); ++i) {
            var c = line.charAt(i);

            switch (c) {
                case '<':
                    // isTag = true
                    // flush if toggled
                    if (!inTag)
                        flush();
                    inTag = true;
                    sb.append(c);
                    break;
                case '>':
                    // isTag = false
                    flush();
                    inTag = false;
                    sb.append(c);
                    break;
                case ' ':
                    // flush buffer
                    flush();
                    sb.append(c);
                    break;
                default:
                    buffer.append(c);
            }
        }
        flush();
        return sb.toString();
    }

    void flush() {
        if (inTag) {
            sb.append(buffer.toString());
        } else {
            sb.append(buffer.reverse().toString());
        }
        buffer.setLength(0);
    }

}
