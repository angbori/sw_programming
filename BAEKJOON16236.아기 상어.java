import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class babyshark {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N, cnt = 0, state = 1, cur_x = 0, cur_y = 0;
		int size = 2, sizeup_cnt = 0, sec = 0, min = 0, find = 0;
		Scanner sc = new Scanner(System.in);
		Queue<Position> xy = new LinkedList<>();
		int[][] arr, real;
		Position[] pos = new Position[4];
		Position find_pos = new Position(0, 0),xy_data;

		N = sc.nextInt();
		arr = new int[N][N];
		real = new int[N][N];

		for (int i = 0; i < 4; i++)
			pos[i] = new Position(0, 0);

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++) {
				arr[i][j] = sc.nextInt();
				if (arr[i][j] == 0)
					cnt++;
				if (arr[i][j] == 9) {
					xy.offer(new Position(i, j));
					arr[i][j] = 0;
				}

			}

		if ((N * N) - 1 == cnt)
			state = 0;
		else
			copy(arr, real, N);

		while (state == 1) {
			find = 0;
			find_pos.x = N;
			find_pos.y = N;

			while (!xy.isEmpty()) {
				xy_data=xy.poll();
				cur_x = xy_data.x;
				cur_y = xy_data.y;
				setting(pos, cur_x, cur_y);

				for (int idx = 0; idx < 4; idx++) {
					if (pos[idx].x < 0 || pos[idx].x > N - 1 || pos[idx].y < 0 || pos[idx].y > N - 1)
						continue;
					if (find == 1 && arr[cur_x][cur_y] - 1 < min) {
						xy.clear();
						break;
					}
					if (arr[pos[idx].x][pos[idx].y] < size && arr[pos[idx].x][pos[idx].y] > 0) {
						arr[pos[idx].x][pos[idx].y] = arr[cur_x][cur_y] - 1;
						min = arr[pos[idx].x][pos[idx].y];

						if (find_pos.x >= pos[idx].x) {
							if (find_pos.x == pos[idx].x) {
								if (find_pos.y > pos[idx].y) {
									find_pos.x = pos[idx].x;
									find_pos.y = pos[idx].y;
								}
							} else {
								find_pos.x = pos[idx].x;
								find_pos.y = pos[idx].y;
							}

						}

						find = 1;
					} else if (arr[pos[idx].x][pos[idx].y] == 0 || arr[pos[idx].x][pos[idx].y] == size) {
						arr[pos[idx].x][pos[idx].y] = arr[cur_x][cur_y] - 1;
						if (find == 0)
							xy.offer(new Position(pos[idx].x, pos[idx].y));
					}
				}
			}

			if (find == 1) {
				sizeup_cnt++;
				if (sizeup_cnt == size) {
					size++;
					sizeup_cnt = 0;
				}
				sec = arr[find_pos.x][find_pos.y];
				real[find_pos.x][find_pos.y] = 0;
				copy(real, arr, N);
				arr[find_pos.x][find_pos.y] = sec;

				xy.offer(new Position(find_pos.x, find_pos.y));
			} else
				state = 0;

		}

		System.out.println(-sec);
		sc.close();
	}

	public static void setting(Position[] pos, int x, int y) {

		pos[0].x = x - 1;
		pos[0].y = y;

		pos[1].x = x;
		pos[1].y = y - 1;

		pos[2].x = x;
		pos[2].y = y + 1;

		pos[3].x = x + 1;
		pos[3].y = y;

	}

	public static void copy(int[][] arr, int[][] real, int N) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				real[i][j] = arr[i][j];
			}
		}
	}
}

class Position {
	int x;
	int y;

	Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

}
