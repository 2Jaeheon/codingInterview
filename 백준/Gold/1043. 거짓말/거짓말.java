import java.util.*;

public class Main{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 사람 수
        int M = sc.nextInt(); // 파티의 수
        List<Integer> knownPerson = new ArrayList<>();
        int knownCount = sc.nextInt(); // 진실을 아는 사람 수를 먼저 읽기
        
        for (int i = 0; i < knownCount; i++) { // 그 수만큼만 반복
            knownPerson.add(sc.nextInt());
        }
        
        Map<Integer, List<Integer>> participants = new HashMap<>();
        
        for(int i = 0; i < M; i++) {
            int participantCount = sc.nextInt();
            
            // i번째 파티에 참여하는 사람들을 담을 임시 리스트
            List<Integer> partyMembers = new ArrayList<>(); 
            for (int j = 0; j < participantCount; j++) {
                partyMembers.add(sc.nextInt());
            }
            
            // 맵에 (파티 번호, 참석자 리스트) 형태로 저장
            participants.put(i, partyMembers);
        }

        for(int k = 0; k < M; k++) {
            for (int i = 0; i < M; i++) {
                List<Integer> currentParty = participants.get(i);
                boolean truthInParty = false;

                // 현재 파티에 진실을 아는 사람이 있는지 확인
                for (int person : currentParty) {
                    if (knownPerson.contains(person)) {
                        truthInParty = true;
                        break;
                    }
                }

                // 파티에 진실을 아는 사람이 있다면 진실을 전파해야함.
                // 즉 진실을 알고있는 knownPerson 리스트를 완성시켜주는 것.
                if (truthInParty) {
                    for (int person : currentParty) {
                        if (!knownPerson.contains(person)) { // 중복 방지
                            knownPerson.add(person);
                        }
                    }
                }
            }
        }

        int liePartyCount = 0;
        for (int i = 0; i < M; i++) {
            List<Integer> currentParty = participants.get(i);

            // 파티에 최종적으로 진실을 아는 사람이 한 명이라도 있는지 확인
            for (int person : currentParty) {
                if (!knownPerson.contains(person)) {
                    liePartyCount++;
                    break;
                }
            }
            
        }
        System.out.println(liePartyCount);

    }
}
