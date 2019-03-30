import java.util.LinkedList;
import java.util.Queue;

public class Word {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] words = { "hot","dog","dog","lot","log","cog","hig","cig" };
		System.out.println(solution("hit", "cog", words));
	}

	public static int solution(String begin, String target, String[] words) {
		int answer = 0;
		String tmp = null;
		Queue<String> q = new LinkedList<>();
		int[] num = new int[words.length];

		for (int i = 0; i < words.length; i++)
			num[i] = 0;

		if (no_in(begin, target, words) == 1) {
			q.offer(begin);

			while (!q.isEmpty()) {
				tmp = q.poll();
				for (int i = 0; i < words.length; i++) {
					if (tmp.equals(words[i])) {
						answer = num[i];
						break;
					}
				}

				for (int j = 0; j < words.length; j++) {
					if (tmp.equals(target)) {
						return answer;
					}

					if (check(tmp, words[j]) == 1 && num[j] == 0) {
						q.offer(words[j]);
						num[j] = answer + 1;
					}
				}
			}
		}

		return 0;
	}

	public static int no_in(String begin, String target, String[] words) {
		int check = 0;

		for (int i = 0; i < words.length; i++) {
			if (words[i].equals(target))
				check++;
		}

		if (check == 1)
			return 1;
		else
			return 0;
	}

	public static int check(String begin, String words) {
		int cnt = 0;

		for (int i = 0; i < begin.length(); i++) {
			if (begin.charAt(i) == words.charAt(i))
				cnt++;
		}

		if (cnt == begin.length() - 1)
			return 1;

		return 0;
	}
}
