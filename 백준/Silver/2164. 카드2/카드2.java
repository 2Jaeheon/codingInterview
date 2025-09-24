import java.util.*;

public class Main{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        // 제일 위의 카드를 바닥에 버리고 다음 제일 위의 카드를 제일 아래로 옮김
        // 이거는 들어간게 제일 늦게 나가니까 일종의 Queue라고 보는게 타당해보임
        // 큐에 들어가야 하는 것은 카드 하나 (객체로 만들필요는 없지만 만들고 싶으니까 만들래)

        int n = sc.nextInt(); // 카드 개수
        Queue<Card> queue = new ArrayDeque<>();
        for(int i = 1; i <= n; i++) {
            queue.offer(new Card(i));
        }

        int timer = 0;
        while(queue.size() != 1) {
            Card cur = queue.poll();
            
            if(timer % 2 == 0) {
                timer++;
                continue;
            } else {
                queue.offer(new Card(cur.number));
                timer++;
            }
        }

        System.out.println(queue.poll().number);
    }

    static class Card {
        int number;

        public Card(int number) {
            this.number = number;
        }
    }
}
