
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ladder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		Queue<HW> bridge = new LinkedList<>();
		int N, M, H, a, b, check = 0;
		HW bri1 = new HW(0, 0), bri2 = new HW(0, 0);
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
				a = sc.nextInt();
				b = sc.nextInt();

				arr[a][b] = 1;
			}
		}
		
		if(ladder_check(arr, H, N) == 1) {
			System.out.println("0");
			return;
		}

		for (int i = 1; i < N; i++) {
			for (int j = 1; j < H + 1; j++) {
				if (arr[j][i] == 0 && arr[j][i + 1] == 0 && arr[j][i - 1] == 0) {
					bridge.offer(new HW(j, i));
					arr[j][i] = 1;

					if (ladder_check(arr, H, N) == 1) {
						System.out.println("1");
						return;
					}

					arr[j][i] = 0;
				}
			}
		}

		if (!bridge.isEmpty()) {
			bridge.offer(new HW(0, 0));

			bri1 = bridge.poll();
			while (bri1.x != 0) {
				arr[bri1.x][bri1.y] = 1;

				bridge.offer(new HW(bri1.x, bri1.y));
				for (int i = 1; i < N; i++) {
					for (int j = 1; j < H + 1; j++) {
						if (arr[j][i] == 0 && arr[j][i + 1] == 0 && arr[j][i - 1] == 0) {
							bridge.offer(new HW(j, i));
							arr[j][i] = 1;

							if (ladder_check(arr, H, N) == 1) {
								System.out.println("2");
								return;
							}

							arr[j][i] = 0;
						}
					}
				}

				arr[bri1.x][bri1.y] = 0;
				bridge.offer(new HW(0, 0));
				bri1 = bridge.poll();
			}
		}

		while (!bridge.isEmpty()) {
			bri1 = bridge.poll();
			arr[bri1.x][bri1.y] = 1;

			bri2 = bridge.poll();
			while (bri2.x != 0) {
				arr[bri2.x][bri2.y] = 1;
				for (int i = 1; i < N; i++) {
					for (int j = 1; j < H + 1; j++) {
						if (arr[j][i] == 0 && arr[j][i + 1] == 0 && arr[j][i - 1] == 0) {
							arr[j][i] = 1;

							if (ladder_check(arr, H, N) == 1) {
								System.out.println("3");
								return;
							}

							arr[j][i] = 0;
						}
					}
				}
				arr[bri2.x][bri2.y] = 0;
				bri2 = bridge.poll();
			}
			arr[bri1.x][bri1.y] = 0;
		}

		System.out.println("-1");
		return;

	}

	public static int ladder_check(int[][] arr, int H, int N) {
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

class HW {
	int x;
	int y;

	HW(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
