import java.util.Scanner;

public class ladder2 {

	static int N, M, H;
	static int ladder_cnt = 4;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int[][] arr;

		N = sc.nextInt();
		M = sc.nextInt();
		H = sc.nextInt();

		arr = new int[H + 1][N + 1];

		if (M == 0) {
			System.out.println("0");
			return;
		} else {
			for (int i = 0; i < M; i++) {
				arr[sc.nextInt()][sc.nextInt()] = 1;
			}
		}

		if (ladder_check(arr) == 1)
			System.out.println(0);
		else
			System.out.println(go(1, arr, 1));

	}

	public static int go(int cnt, int[][] arr, int x) {

		if (cnt == 4 || cnt >= ladder_cnt) //ladder_cnt보다 더 많이 깔아 볼 필요 없음
			return 0;
		else {
			for (int i = x; i < N; i++) {
				for (int j = 1; j < H + 1; j++) {
					if (arr[j][i] == 0 && arr[j][i - 1] == 0 && arr[j][i + 1] == 0) {
						arr[j][i] = 1;
						if (ladder_check(arr) == 1) {
							if (ladder_cnt > cnt)
								ladder_cnt = cnt;
							arr[j][i] = 0;
							return ladder_cnt;
						}

						go(cnt + 1, arr, i);
						arr[j][i] = 0;

					}
				}
			}
		}

		if (ladder_cnt == 4)
			return -1;
		return ladder_cnt;

	}

	public static int ladder_check(int[][] arr) {
		int i = 0, start = 0;

		while (start < N) {
			i = ++start;
			for (int j = 1; j < H + 1; j++) {
				if (arr[j][i - 1] == 1) {
					i = i - 1;
				} else if (arr[j][i] == 1) {
					i = i + 1;
				}

			}
			if (start != i)
				return 0;
		}
		return 1;
	}

}
