import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class robot_bfs {
	static int[][] arr;
	static int N, M, clean_cnt = 0, check = -1;
	static Queue<robot_dir> que = new LinkedList();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		robot_dir c_robot = new robot_dir(0, 0, 0);
		robot_dir[] robot = new robot_dir[4];
		int x, y, way, k, k_cnt = 0;

		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N + 2][M + 2];

		x = sc.nextInt();
		y = sc.nextInt();
		way = sc.nextInt();

		for (int i = 0; i < N + 2; i++) {
			for (int j = 0; j < M + 2; j++) {
				if (i == 0 || i == N + 1) {
					arr[0][j] = 1;
					arr[N + 1][j] = 1;
				} else if (j == 0 || j == M + 1) {
					arr[i][0] = 1;
					arr[i][M + 1] = 1;
				} else
					arr[i][j] = sc.nextInt();
			}
		}

		if (way == 3)
			que.offer(new robot_dir(x + 1, y + 1, 1));
		else if (way == 1)
			que.offer(new robot_dir(x + 1, y + 1, 3));
		else {
			que.offer(new robot_dir(x + 1, y + 1, way));
		}

		while (!que.isEmpty()) {
			c_robot = que.poll();
			x = c_robot.x;
			y = c_robot.y;
			way = c_robot.way;

			if (arr[x][y] == 0)
				arr[x][y] = check--;
			robot = dir_init(x, y, way);

			if (way == 3)
				way = -1;

			for (k = way + 1, k_cnt = 0; k_cnt < 4; k++, k_cnt++) {
				if (arr[robot[k].x][robot[k].y] == 0) {
					que.offer(new robot_dir(robot[k].x, robot[k].y, k));
					break;
				}

				if (k == 3)
					k = -1;
			}

			if (k_cnt == 4) {
				if (way == -1)
					check(robot, x, y, 3);
				else
					check(robot, x, y, way);
			}

		}

		System.out.println(-(check + 1));

	}

	public static void check(robot_dir[] robot, int x, int y, int way) {
		int back_way, i;

		if (way >= 2)
			back_way = way - 2;
		else
			back_way = way + 2;

		while (true) {
			robot = dir_init(x, y, way);
			for (i = 0; i < 4; i++) {
				if (arr[robot[i].x][robot[i].y] == 0)
					break;
			}

			if (i == 4) {
				if (arr[robot[back_way].x][robot[back_way].y] == 1) {
					que.clear();
					break;
				} else {
					if (way == 0) {
						x++;
					} else if (way == 1) {
						y++;
					} else if (way == 2) {
						x--;
					} else if (way == 3) {
						y--;
					}

				}
			} else {
				que.offer(new robot_dir(x, y, way));
				break;
			}
		}

	}

	public static robot_dir[] dir_init(int x, int y, int way) {
		robot_dir[] pos = new robot_dir[4];

		for (int i = 0; i < 4; i++)
			pos[i] = new robot_dir(x, y, way);

		pos[0].x--; 
		pos[1].y--; 
		pos[2].x++; 
		pos[3].y++; 

		return pos;
	}

}

class robot_dir {
	int x;
	int y;
	int way;

	robot_dir(int x, int y, int way) {
		this.x = x;
		this.y = y;
		this.way = way;
	}
}
