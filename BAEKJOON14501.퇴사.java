import java.util.Scanner;

public class retire {
	static int N, print_sum = 0;
	static consult[] con;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		con = new consult[N + 1];

		for (int i = 1; i < N + 1; i++)
			con[i] = new consult(sc.nextInt(), sc.nextInt());

		dfs(1, 0);

		System.out.println(print_sum);
	}

	public static int dfs(int pos, int sum) {

		if (pos != 1 && pos >= N + 1) {
			if (print_sum < sum)
				print_sum = sum;
			return 0;
		} else {
			for (int i = pos; i < N + 1; i++) {
				if (con[i].period + i - 1 < N + 1) {
					sum += con[i].price;
				}
				dfs(con[i].period + i, sum);
				if (con[i].period + i - 1 < N + 1)
					sum -= con[i].price;

			}

		}
		return 0;
	}

}

class consult {
	int period;
	int price;

	consult(int period, int price) {
		this.period = period;
		this.price = price;
	}
}
