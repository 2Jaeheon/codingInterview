class Solution
{
    public int solution(int n, int a, int b)
    {
        int round = 0;
        for (int i = 1; i < n; i++) {
            if (a == b) {
                break;
            }
            a = (a + 1) / 2;
            b = (b + 1) / 2;

            round++;
        }

        return round;
    }
}