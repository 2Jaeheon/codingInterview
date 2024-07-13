/*문제 설명
array의 각 element 중 divisor로 나누어 떨어지는 값을 오름차순으로 정렬한 배열을 반환하는 함수, solution을 작성해주세요.
divisor로 나누어 떨어지는 element가 하나도 없다면 배열에 -1을 담아 반환하세요.

제한사항
arr은 자연수를 담은 배열입니다.
정수 i, j에 대해 i ≠ j 이면 arr[i] ≠ arr[j] 입니다.
divisor는 자연수입니다.
array는 길이 1 이상인 배열입니다.
입출력 예
arr	divisor	return
[5, 9, 7, 10]	5	[5, 10]
[2, 36, 1, 3]	1	[1, 2, 3, 36]
[3,2,6]	10	[-1]
입출력 예 설명
입출력 예#1
arr의 원소 중 5로 나누어 떨어지는 원소는 5와 10입니다. 따라서 [5, 10]을 리턴합니다.

입출력 예#2
arr의 모든 원소는 1으로 나누어 떨어집니다. 원소를 오름차순으로 정렬해 [1, 2, 3, 36]을 리턴합니다.

입출력 예#3
3, 2, 6은 10으로 나누어 떨어지지 않습니다. 나누어 떨어지는 원소가 없으므로 [-1]을 리턴합니다.*/

import java.util.*;

public class 나누어_떨어지는_숫자_배열 {

    public int[] solution(int[] arr, int divisor) {
        //리스트 선언
        List<Integer> list = new ArrayList<>();

        //arr이 divisor로 나누어지면 리스트에 추가
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % divisor == 0) {
                list.add(arr[i]);
            }
        }

        //리스트가 비어있을 경우 -1이 들어있는 배열을 리턴
        if (list.isEmpty()) {
            int[] voidArr = {-1};
            return voidArr;
        }

        //Integer 리스트를 int형 배열로 변환후 정렬
        int answer[] = new int[list.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i).intValue();
        }
        Arrays.sort(answer);

        return answer;
    }//solution method
}
