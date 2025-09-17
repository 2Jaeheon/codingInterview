import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    static class DSU {
        private final int[] parent;

        DSU(int n) {
            parent = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            if (parent[x] == x) 
                return x;
            return parent[x] = find(parent[x]); // 경로 압축
        }

        void union(int a, int b) {
            int ra = find(a);
            int rb = find(b);
            if (ra != rb) parent[rb] = ra; // 간단히 rb의 루트를 ra로 붙임
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력: 사람 수 N, 파티 수 M
        StringTokenizer st = new StringTokenizer(br.readLine());
        int peopleCount = Integer.parseInt(st.nextToken()); // N
        int partyCount  = Integer.parseInt(st.nextToken()); // M

        // 입력: 진실을 아는 사람들
        st = new StringTokenizer(br.readLine());
        int truthCount = Integer.parseInt(st.nextToken());
        List<Integer> truthKnowers = new ArrayList<>();
        
        for (int i = 0; i < truthCount; i++) {
            truthKnowers.add(Integer.parseInt(st.nextToken()));
        }

        // 입력: 각 파티 참석자 목록
        List<List<Integer>> parties = new ArrayList<>(partyCount);
        for (int i = 0; i < partyCount; i++) {
            st = new StringTokenizer(br.readLine());
            int attendeeCount = Integer.parseInt(st.nextToken());
            List<Integer> attendees = new ArrayList<>(attendeeCount);
            for (int j = 0; j < attendeeCount; j++) {
                attendees.add(Integer.parseInt(st.nextToken()));
            }
            parties.add(attendees);
        }

        // 진실을 아는 사람이 아무도 없으면 모든 파티에서 과장 가능
        if (truthCount == 0) {
            System.out.println(partyCount);
            return;
        }

        // DSU로 같은 파티 사람들 묶기
        DSU dsu = new DSU(peopleCount);
        
        for (List<Integer> party : parties) {
            if (party.size() <= 1) 
                continue;
            
            int first = party.get(0);
            
            for (int i = 1; i < party.size(); i++) {
                dsu.union(first, party.get(i));
            }
        }

        // 진실을 아는 사람의 루트를 true로 표시하면,
        // 같은 루트(=같은 집합)에 속한 사람들은 모두 진실 전파 집합에 속한다.
        boolean[] isTruthRoot = new boolean[peopleCount + 1];
        for (int t : truthKnowers) {
            isTruthRoot[dsu.find(t)] = true;
        }

        // 결과 계산: 각 파티가 거짓말 가능한지 검사
        int canExaggerateCount = 0;

        for (List<Integer> party : parties) {
            boolean connectedToTruth = false;
        
            for (int person : party) {
                int root = dsu.find(person);
                if (!isTruthRoot[root]) {
                    canExaggerateCount++;
                    break; // 더 볼 필요 없음 → 진실과 연결
                }
            }
        }

        // 7) 출력
        System.out.println(canExaggerateCount);
    }
}