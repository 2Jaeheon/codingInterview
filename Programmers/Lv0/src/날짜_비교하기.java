public class 날짜_비교하기 {

    public int solution(int[] date1, int[] date2) {
        int answer = 0;
        if (date1[0] > date2[0]) {
            return 0;
        } else if (date1[0] < date2[0]) {
            return 1;
        } else { //year equal
            if (date1[1] > date2[1]) {
                return 0;
            } else if (date1[1] < date2[1]) {
                return 1;
            } else { //month equal
                if (date1[2] > date2[2]) {
                    return 0;
                } else if (date1[2] < date2[2]) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    }

    public int solution(int[] date1, int[] date2) {
        LocalDate dateA = LocalDate.of(date1[0], date1[1], date1[2]);
        LocalDate dateB = LocalDate.of(date2[0], date2[1], date2[2]);

        if (dateA.isBefore(dateB)) {
            return 1;
        } else {
            return 0;
        }
    }

    public int solution(int[] date1, int[] date2) {
        return Arrays.compare(date1, date2) < 0 ? 1 : 0;
    }
}
