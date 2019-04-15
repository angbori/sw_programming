import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class chicken2 {
	static int N, M, house_cnt = 0, chicken_cnt = 0, print_cnt = 2501;
	static int[][] arr;
	static chicken_pos[] chicken = new chicken_pos[13];
	static chicken_pos[] house = new chicken_pos[100];
	static chicken_pos[] store;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N][N];
		store = new chicken_pos[M];

		for (int i = 0; i < M; i++)
			store[i] = new chicken_pos(0, 0);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = sc.nextInt();
				if (arr[i][j] == 2) {
					chicken[chicken_cnt++] = new chicken_pos(i, j);
					arr[i][j] = 0;
				} else if (arr[i][j] == 1) {
					house[house_cnt++] = new chicken_pos(i, j);
				}
			}
		}

		dfs(0, 1);
		System.out.println(print_cnt);

	}

	public static int dfs(int idx, int store_cnt) {
		int cnt;

		if (store_cnt == M + 1) {
			cnt = distance();
			if (print_cnt > cnt)
				print_cnt = cnt;
			return 0;
		} else {
			for (int i = idx; i < chicken_cnt; i++) {
				store[store_cnt - 1].x = chicken[i].x;
				store[store_cnt - 1].y = chicken[i].y;
				dfs(i, store_cnt + 1);

			}

		}
		return 0;
	}

	public static int distance() {
		int distance = 2501, sum_distance = 0, val;

		for (int i = 0; i < house_cnt; i++) { // 집
			for (int j = 0; j < M; j++) { // 치킨집
				val = (Math.abs(house[i].x - store[j].x)) + (Math.abs(house[i].y - store[j].y));
				if (val < distance) {
					distance = val;
				}
				if (val == 1)
					break;
			}
			sum_distance += distance;
			distance = 2501;
		}
		return sum_distance;
	}
}

class chicken_pos {
	int x;
	int y;

	chicken_pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
