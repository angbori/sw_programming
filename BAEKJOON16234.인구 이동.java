import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class move2 {
	static int num = 1, N, L, R, print_cnt = 0, state = 0;
	static int[][] human, cnt;
	static ArrayList<union> list = new ArrayList<>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		L = sc.nextInt();
		R = sc.nextInt();

		human = new int[N][N];
		cnt = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				human[i][j] = sc.nextInt();
		}

		while (true) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (cnt[i][j] == 0)
						dfs(i, j, num);
					if (state == 1)
						num++;
					state = 0;
				}
			}
			if (list.isEmpty())
				break;
			print_cnt++;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					for (int k = 0; k < list.size(); k++) {
						if (cnt[i][j] == list.get(k).union_num) {
							human[i][j] = list.get(k).human_cnt / list.get(k).block_cnt;
							break;
						}
					}
					cnt[i][j] = 0;
				}
			}
			list.clear();
			num=1;

		}
		System.out.println(print_cnt);

	}

	static public void dfs(int i, int j, int num) {
		int[] x = { -1, 0, 1, 0 };
		int[] y = { 0, -1, 0, 1 };
		int f_x, f_y, dif;

		for (int k = 0; k < 4; k++) {
			f_x = i + x[k];
			f_y = j + y[k];
			if (f_x < 0 || f_x > N - 1 || f_y < 0 || f_y > N - 1)
				continue;
			dif = Math.abs(human[i][j] - human[f_x][f_y]);
			if (dif >= L && dif <= R && cnt[f_x][f_y] == 0) {
				state = 1;
				if (cnt[i][j] == 0 && cnt[f_x][f_y] == 0) {
					cnt[i][j] = num;
					cnt[f_x][f_y] = num;
					list.add(new union(num, human[i][j] + human[f_x][f_y], 2));
				} else if (cnt[i][j] == num && cnt[f_x][f_y] == 0) {
					cnt[f_x][f_y] = num;
					for (int l = 0; l < list.size(); l++) {
						if (list.get(l).union_num == cnt[i][j]) {
							list.get(l).human_cnt += human[f_x][f_y];
							list.get(l).block_cnt++;
						}
					}
				}
				dfs(f_x, f_y, num);
			}
		}

	}
}

class union {
	int union_num;
	int human_cnt = 0;
	int block_cnt = 0;

	union(int union_num, int human_cnt, int block_cnt) {
		this.union_num = union_num;
		this.human_cnt = human_cnt;
		this.block_cnt = block_cnt;
	}
}
