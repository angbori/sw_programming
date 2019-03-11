import java.util.Scanner;

public class bread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int a, b, c, t_case;
		int[] arr;
		Scanner sc = new Scanner(System.in);

		t_case = sc.nextInt();
		arr = new int[t_case];

		for (int i = 0; i < t_case; i++) {
			a = sc.nextInt();
			b = sc.nextInt();
			c = sc.nextInt();

			if (a > b)
				arr[i] = c/b;
			else
				arr[i] = c/a;
		}
		
		for (int i=0;i<t_case;i++) 
			System.out.println("#" + (i+1) + " " + arr[i]);
	}

}
