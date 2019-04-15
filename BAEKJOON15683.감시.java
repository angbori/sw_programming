import java.util.Scanner;

public class cctv2 {
	static cctv_check[] cctv = new cctv_check[8];
	static int[] cctv_way = new int[6];
	static int min;
	static int[][] arr;
	static int N, M;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int idx = 0;

		N = sc.nextInt();
		M = sc.nextInt();
		min = N * M;

		arr = new int[N][M];

		cctv_way[1] = 4;
		cctv_way[2] = 2;
		cctv_way[3] = 4;
		cctv_way[4] = 4;
		cctv_way[5] = 1;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[i][j] = sc.nextInt();

				if (arr[i][j] > 0 && arr[i][j] < 6) {
					cctv[idx++] = new cctv_check(i, j, arr[i][j]);
				} else if (arr[i][j] == 6)
					min--;
			}
		}

		if (idx == 0) {
			System.out.println(min);
		} else {
			System.out.println(go(0, idx));
		}
	}

	public static int go(int cctv_num, int cctv_total) {
		int blind_cnt;

		if (cctv_num == cctv_total) {
			blind_cnt = cctv_min(arr, N, M);
			if (min > blind_cnt)
				min = blind_cnt;
			return 0;
		} else {
			for (int go_i = 0; go_i < cctv_way[cctv[cctv_num].cctv_type]; go_i++) {
				cctv_paint(cctv_num, go_i + 1, 1);
				go(cctv_num + 1, cctv_total);
				cctv_paint(cctv_num, go_i + 1, 0);
			}
		}
		return min;
	}

	public static void cctv_paint(int cctv_num, int idx, int mode) {
		int cctv_type = cctv[cctv_num].cctv_type;
		int x = cctv[cctv_num].x;
		int y = cctv[cctv_num].y;
		cctv_num = (-cctv_num) - 1;

		if ((cctv_type == 1 && idx == 1) || (cctv_type == 2 && idx == 1) || (cctv_type == 3 && (idx == 1 || idx == 2))
				|| (cctv_type == 4 && (idx == 1 || idx == 2 || idx == 3)) || (cctv_type == 5)) {
			for (int i = y; i < M; i++) {
				if (arr[x][i] == 6)
					break;
				if (mode == 1 && arr[x][i] == 0) {
					arr[x][i] = cctv_num;
				} else if (mode == 0 && arr[x][i] == cctv_num) {
					arr[x][i] = 0;
				}
			}
		}
		if ((cctv_type == 1 && idx == 2) || (cctv_type == 2 && idx == 2) || (cctv_type == 3 && (idx == 2 || idx == 3))
				|| (cctv_type == 4 && (idx == 2 || idx == 3 || idx == 4)) || (cctv_type == 5)) {
			for (int i = x; i < N; i++) {
				if (arr[i][y] == 6)
					break;
				if (mode == 1 && arr[i][y] == 0) {
					arr[i][y] = cctv_num;
				} else if (mode == 0 && arr[i][y] == cctv_num) {
					arr[i][y] = 0;
				}
			}
		}
		if ((cctv_type == 1 && idx == 3) || (cctv_type == 2 && idx == 1) || (cctv_type == 3 && (idx == 3 || idx == 4))
				|| (cctv_type == 4 && (idx == 1 || idx == 3 || idx == 4)) || (cctv_type == 5)) {
			for (int i = y; i >= 0; i--) {
				if (arr[x][i] == 6)
					break;
				if (mode == 1 && arr[x][i] == 0) {
					arr[x][i] = cctv_num;
				} else if (mode == 0 && arr[x][i] == cctv_num) {
					arr[x][i] = 0;
				}
			}
		}
		if ((cctv_type == 1 && idx == 4) || (cctv_type == 2 && idx == 2) || (cctv_type == 3 && (idx == 1 || idx == 4))
				|| (cctv_type == 4 && (idx == 1 || idx == 2 || idx == 4)) || (cctv_type == 5)) {
			for (int i = x; i >= 0; i--) {
				if (arr[i][y] == 6)
					break;
				if (mode == 1 && arr[i][y] == 0) {
					arr[i][y] = cctv_num;
				} else if (mode == 0 && arr[i][y] == cctv_num) {
					arr[i][y] = 0;
				}
			}
		}
	}

	public static int cctv_min(int[][] arr, int N, int M) {
		int cnt = 0;

		for (int min_i = 0; min_i < N; min_i++) {
			for (int min_j = 0; min_j < M; min_j++)
				if (arr[min_i][min_j] == 0)
					cnt++;
		}

		return cnt;
	}

}

class cctv_check {
	int x;
	int y;
	int cctv_type;

	cctv_check(int x, int y, int cctv_type) {
		this.x = x;
		this.y = y;
		this.cctv_type = cctv_type;
	}
}
