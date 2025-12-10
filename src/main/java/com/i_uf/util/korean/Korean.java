package com.i_uf.util.korean;
/**이 수업은 한글의 타자기 배열 순서를 이용하여 한글을 다른 형태로 변형시키는 수업입니다. 외부 수업을 가져오지 않았습니다.
 * @author I_uf <i>(uf_developer@outlook.kr)</i>*/
public final class Korean { /*모든 공용 함수에 사용 설명서 포함됨*/
    private Korean() {}
    public static final String cho = "ㄱㄲㄴㄷㄸㄹㅁㅂㅃㅅㅆㅇㅈㅉㅊㅋㅌㅍㅎ";
    public static final String jong = "ㄱㄲㄳㄴㄵㄶㄷㄹㄺㄻㄼㄽㄾㄿㅀㅁㅂㅄㅅㅆㅇㅈㅊㅋㅌㅍㅎ";
    private static final String[] en = "rRseEfaqQtTdwWczxvg:koiOjpuPhynbml:ㅏㅐㅑㅒㅓㅔㅕㅖㅗㅛㅜㅠㅡㅣ".split(":");
    private static final String[] jaEn = "r:R:rt:s:sw:sg:e:E:f:fr:fa:fq:ft:fx:fv:fg:a:q:Q:qt:t:T:d:w:W:c:z:x:v:g".split(":");
    private static final String[] moEn = "k:o:i:O:j:p:u:P:h:hk:ho:hl:y:n:nj:np:nl:b:m:ml:l".split(":");
    private static final char[][] range = {{'ㄱ', 'ㅎ'}, {'ㅏ', 'ㅣ'}, {'가', '힣'}, {'A', 'Z'}, {'a', 'z'}, {' ', '~'}};
    /**문자가 색인 번호에 따라 0부터 순서대로 자음, 모음, 한글 음절, 알파벳 대문자, 알파벳 소문자, 또는 아스키코드인지 여부를 반환합니다.*/
    public static boolean in(char c, int index) { return range[index][0] <= c && range[index][1] >= c; }
    /**문자가 한글인지 여부를 반환합니다.
     * @see #in(char, int)*/
    public static boolean han(char c) { return in(c, 0) || in(c, 1) || in(c, 2); }
    private static boolean ja(char c, int i) { return (i>0 ? cho : jong).indexOf(c) < 0; }
    /**문자가 알파벳인지 여부를 반환합니다.
     * @see #in(char, int)*/
    public static boolean alpha(char c) { return in(c, 3) || in(c, 4); }
    private static char last(String s) { return s.charAt(s.length() - 1); }
    private static String test(char t, String f) { return ""+(t == 0 ? f : t); }
    /**문자열의 모든 한글을 세세하게 분해시킵니다.*/
    public static String boonhae(String s) {
        return boonhae(s, false);
    }/**문자열의 모든 한글을 분해시킵니다.*/
    public static String boonhae(String s, boolean simplyDiv) {
        return s.chars().boxed().map(integer -> boonhae((char) integer.intValue(), simplyDiv)).reduce("", String::concat);
    }/**한글 문자를 초성, 중성, 종성으로 분해시킵니다.*/
    public static String boonhae(char c) { return boonhae(c, true); }
    private static String boonhae(char c, boolean simplyDiv) {
        return in(c, 0) ? boonhae_Ja(c, simplyDiv) : in(c, 1) ? boonhae_Mo(c, simplyDiv) :
            in(c, 2) ? boonhae_Ja(cho.charAt((c - '가') / 588), simplyDiv) + boonhae_Mo
                    ((char) ('ㅏ' + (c - '가') % 588 / 28), simplyDiv) + ((c - '가') % 28 == 0 ?
                    "" : boonhae_Ja(jong.charAt((c - '가') % 28 - 1), simplyDiv)) : ""+c;
    }
    private static String boonhae_Ja(char c, boolean doNothing) {
        return doNothing ? ""+c : switch (c) {
            case 'ㄳ' -> "ㄱㅅ"; case 'ㄵ' -> "ㄴㅈ"; case 'ㄶ' -> "ㄴㅎ"; case 'ㄺ' -> "ㄹㄱ";
            case 'ㄻ' -> "ㄹㅁ"; case 'ㄼ' -> "ㄹㅂ"; case 'ㄽ' -> "ㄹㅅ"; case 'ㄾ' -> "ㄹㅌ";
            case 'ㄿ' -> "ㄹㅍ"; case 'ㅀ' -> "ㄹㅎ"; case 'ㅄ' -> "ㅂㅅ"; default -> ""+c;
        };
    }
    private static String boonhae_Mo(char c, boolean doNothing) {
        return doNothing ? ""+c : switch (c) {
            case 'ㅘ' -> "ㅗㅏ"; case 'ㅙ' -> "ㅗㅐ"; case 'ㅚ' -> "ㅗㅣ"; case 'ㅝ' -> "ㅜㅓ";
            case 'ㅞ' -> "ㅜㅔ"; case 'ㅟ' -> "ㅜㅣ"; case 'ㅢ' -> "ㅡㅣ"; default -> ""+c;
        };
    }/**문자열의 모든 한글을 합체합니다.*/
    public static String hapche(String s) {
        if(s == null || s.isBlank()) return s; s = boonhae(s);
        StringBuilder result = new StringBuilder(); char temp = s.charAt(0);
        for(int i = 1; i < s.length(); i++) if(hapche(temp, s.charAt(i)).length() != 1)
            if(-result.append(hapche(temp, s.charAt(i)).charAt(0)).hashCode() < (temp=
                    hapche(temp, s.charAt(i)).charAt(1)));else;else temp = hapche(temp, s.charAt(i)).charAt(0);
        return result.append(temp).toString();
    }/**한글 문자 두개를 합체 합니다.*/
    public static String hapche(char a, char b) {
        if(!han(a) || !(in(b, 0) || in(b, 1))) return "" + a + b;
        char[] c = boonhae(a, true).toCharArray();
        return in(a, 0) ? in(b, 0) ? test(hapche_Ja(a, b), "" + a + b) : (ja(c[0], 1) ? ""+boonhae(c[0]).charAt(0):"")
                +hapche_Um(last(boonhae(c[0])), b) : in(a, 1) ? in(b, 0) ? ""+a+b : test(hapche_Mo(c[0], b), ""+a+b) :
        in(b, 0) ? c.length == 3 ? hapche_Ja(c[2], b) != 0 ? ""+jong(a, hapche_Ja(c[2], b)) :
            ""+a+b : ja(b, 0) ? ""+a+b : ""+(char)(a + jong.indexOf(b)+1) :
            c.length == 3 ? ""+jong(a, ja(c[2], 1) ? boonhae(c[2]).charAt(0):0)+hapche_Um(last(boonhae(c[2])), b) :
            hapche_Mo(c[1], b) != 0 ? ""+jung(a, hapche_Mo(c[1], b)) : ""+a+b;
    }
    private static char jong(char a, char b) { char[] c = boonhae(a, true).toCharArray(); return hapche_Um(c[0], c[1], b); }
    private static char jung(char a, char b) { char[] c = boonhae(a, true).toCharArray(); return hapche_Um(c[0], b); }
    private static char hapche_Um(char a, char b) { return hapche_Um(a, b, (char) 0); }
    private static char hapche_Um(char a, char b, char c) { return (char) ('가'+(cho.indexOf(a))*588+(b-'ㅏ')*28+jong.indexOf(c)+1); }
    private static char hapche_Ja(char a, char b) {
        return switch ("" + a + b) {
            case "ㄱㅅ" -> 'ㄳ'; case "ㄴㅈ" -> 'ㄵ'; case "ㄴㅎ" -> 'ㄶ'; case "ㄹㄱ" -> 'ㄺ';
            case "ㄹㅁ" -> 'ㄻ'; case "ㄹㅂ" -> 'ㄼ'; case "ㄹㅅ" -> 'ㄽ'; case "ㄹㅌ" -> 'ㄾ';
            case "ㄹㅍ" -> 'ㄿ'; case "ㄹㅎ" -> 'ㅀ'; case "ㅂㅅ" -> 'ㅄ'; default -> 0;
        };
    }
    private static char hapche_Mo(char a, char b) {
        return switch ("" + a + b) {
            case "ㅗㅏ" -> 'ㅘ'; case "ㅗㅐ" -> 'ㅙ'; case "ㅗㅣ" -> 'ㅚ'; case "ㅜㅓ" -> 'ㅝ';
            case "ㅜㅔ" -> 'ㅞ'; case "ㅜㅣ" -> 'ㅟ'; case "ㅡㅣ" -> 'ㅢ'; default -> 0;
        };
    }/**영문과 한글을 반전시킵니다.*/
    public static String banjeon(String s) {return s==null||s.isEmpty()?s:hapche(boonhae(s, true).chars().mapToObj(
            a->in((char)a, 0) ? jaEn[a-'ㄱ'] : in((char)a, 1) ? moEn[a-'ㅏ'] : alpha((char)a) ?
                en[0].contains("" + (char)a) ? "" + cho.charAt(en[0].indexOf(a)) : en[1].contains(""+(char)a)?
                "" + en[2].charAt(en[1].indexOf(a)) : "" + (char)a : "" + (char)a).reduce("", String::concat));
    }/**초성으로 비교합니다. 만약 문자가 초성이 아니라면 같은 글자일 경우에만 성공합니다.*/
    public static boolean choMatch(char target, char c) {
        return cho.contains(""+c) ? boonhae(c).charAt(0) == boonhae(target).charAt(0) : c == target;
    }/**초성으로 비교합니다. 만약 문자가 초성이 아니라면 같은 글자일 경우에만 성공합니다*/
    public static boolean choMatch(String target, String s) {
        for(int i = 0; i < s.length(); i++) if(!choMatch(target.charAt(i), s.charAt(i))) return false;
        return true;
    }/**검색합니다.*/
    public static String[] basicSearch(String[] target, String s, String[] dst) {
        for(int i = 0, count = 0; i < target.length; i++) if(choMatch(target[i], s)) dst[count++] = target[i]; return dst;
    }
}