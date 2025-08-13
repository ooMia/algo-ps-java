class Solution {
    public String solution(String octal) {
        if ("0".equals(octal)) {
            return "0";
        }
        
        StringBuilder binary = new StringBuilder();
        for (char c: octal.toCharArray()){
            binary.append(octalToBinary(c));
        }
        
        int iStart = 0;
        while(binary.charAt(iStart) == '0') iStart++;
        return binary.substring(iStart);
    }

    String octalToBinary(char c) {
        switch (c) {
            case '0': return "000";
            case '1': return "001";
            case '2': return "010";
            case '3': return "011";
            case '4': return "100";
            case '5': return "101";
            case '6': return "110";
            case '7': return "111";
            default: return "";
        }
    }
}