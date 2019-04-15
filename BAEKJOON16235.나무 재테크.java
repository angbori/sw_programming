import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class tree2 {
	static ArrayList<tree_info> tree = new ArrayList<tree_info>();
	static int[][] arr, energy;
	static int N, M, K;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int x, y, age, idx;

		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();

		arr = new int[N][N];
		energy = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = sc.nextInt();
				energy[i][j] = 5;
			}
		}
		for (int i = 0; i < M; i++) {
			x = sc.nextInt() - 1;
			y = sc.nextInt() - 1;
			age = sc.nextInt();

			for (idx = 0; idx < tree.size(); idx++) {
				if (x == tree.get(idx).x && y == tree.get(idx).y)
					break;
			}
			if (tree.isEmpty() || idx == tree.size())
				tree.add(new tree_info(x, y, age));
			else if (idx != tree.size()) {
				tree.get(idx).get_age().add(age);
			}
		}

		for (int i = 0; i < tree.size(); i++)
			Collections.sort(tree.get(i).get_age());

		System.out.println(season());

	}

	public static int season() {
		int[] x = { -1, -1, -1, 0, 0, 1, 1, 1 };
		int[] y = { -1, 0, 1, -1, 1, -1, 0, 1 };
		int new_x, new_y, sum_tree = 0, age, idx;

		for (int k = 0; k < K; k++) {
			for (int i = 0; i < tree.size(); i++) {
				for (int j = 0; j < tree.get(i).get_age().size(); j++) {
					age = tree.get(i).get_age().get(j);
					if (energy[tree.get(i).x][tree.get(i).y] >= age) {
						energy[tree.get(i).x][tree.get(i).y] -= age;
						tree.get(i).get_age().set(j, age + 1);
					} else {
						tree.get(i).delete_point = j;
						break;
					}
				}
			}
			for (int i = 0; i < tree.size(); i++) {
				if (tree.get(i).delete_point != -1) {
					for (int j = tree.get(i).get_age().size() - 1; j >= tree.get(i).delete_point; j--) {
						energy[tree.get(i).x][tree.get(i).y] += tree.get(i).get_age().get(j) / 2;
						tree.get(i).get_age().remove(j);
					}
					tree.get(i).delete_point = -1;
				}

			}
			for (int i = 0; i < tree.size(); i++) {
				for (int j = 0; j < tree.get(i).get_age().size(); j++) {
					age = tree.get(i).get_age().get(j);
					if (age % 5 == 0) {
						for (int l = 0; l < 8; l++) {
							new_x = tree.get(i).x + x[l];
							new_y = tree.get(i).y + y[l];
							if (new_x < 0 || new_x > N - 1 || new_y < 0 || new_y > N - 1)
								continue;
							for (idx = 0; idx < tree.size(); idx++) {
								if (new_x == tree.get(idx).x && new_y == tree.get(idx).y)
									break;

							}
							if (idx == tree.size())
								tree.add(new tree_info(new_x, new_y, 1));
							else {
								tree.get(idx).get_age().add(0, 1);
							}
						}
					}
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					energy[i][j] += arr[i][j];
				}
			}
		}

		for (int i = 0; i < tree.size(); i++) {
			sum_tree += tree.get(i).get_age().size();
		}

		return sum_tree;
	}
}

class tree_info {
	int x;
	int y;
	ArrayList<Integer> age = new ArrayList<>();
	int delete_point = -1;

	tree_info(int x, int y, int age) {
		this.x = x;
		this.y = y;
		this.age.add(age);
	}

	ArrayList<Integer> get_age() {
		return age;
	}

}
