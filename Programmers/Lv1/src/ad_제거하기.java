/*문제 설명
문자열 배열 strArr가 주어집니다. 배열 내의 문자열 중 "ad"라는 부분 문자열을 포함하고 있는 모든 문자열을 제거하고 남은 문자열을 순서를 유지하여 배열로 return 하는 solution 함수를 완성해 주세요.

제한사항
1 ≤ strArr의 길이 ≤ 1,000
1 ≤ strArr의 원소의 길이 ≤ 20
strArr의 원소는 알파벳 소문자로 이루어진 문자열입니다.
입출력 예
strArr	result
["and","notad","abcd"]	["and","abcd"]
["there","are","no","a","ds"]	["there","are","no","a","ds"]
입출력 예 설명
입출력 예 #1

1번 인덱스의 문자열인 "notad"는 부분 문자열로 "ad"를 가집니다. 따라서 해당 문자열을 제거하고 나머지는 순서를 유지하여 ["and","abcd"]를 return 합니다.
입출력 예 #2

"ad"가 부분 문자열로 들어간 문자열이 존재하지 않습니다. 따라서 원래 배열을 그대로 return 합니다.*/
public class ad_제거하기 {

    public String[] solution(String[] strArr) {
        int anSize = 0;
        for (int i = 0; i < strArr.length; i++) {
            if (!strArr[i].contains("ad")) {
                anSize++;
            }
        }
        String[] answer = new String[anSize];
        for (int i = 0, j = 0; i < strArr.length; i++) {
            if (!strArr[i].contains("ad")) {
                answer[j++] = strArr[i];
            }
        }
        return answer;
    }


    public ArrayList<String> solution2(String[] strArr) {
        ArrayList<String> arrayList = new ArrayList<>();

        for (String str : strArr) {
            if (!str.contains("ad")) {
                arrayList.add(str);
            }
        }

        return arrayList;
    }

}
