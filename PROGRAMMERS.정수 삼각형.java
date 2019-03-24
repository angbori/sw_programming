
public class Solution {
	public int solution(int[][] triangle) {
		int answer = 0;
		
		triangle[1][0]+=triangle[0][0];
		triangle[1][1]+=triangle[0][0];
		
		for(int i=2;i<triangle.length;i++) {
			for(int j=0;j<triangle[i].length;j++) {
				if(j==0)
					triangle[i][j]+=triangle[i-1][0];
				else if(j==triangle[i].length-1)
					triangle[i][j]+=triangle[i-1][j-1];
				else {
					if(triangle[i-1][j-1]>triangle[i-1][j])
						triangle[i][j]+=triangle[i-1][j-1];
					else
						triangle[i][j]+=triangle[i-1][j];
				}
			}
		}
		
		for(int i=0;i<triangle[triangle.length-1].length;i++)
			if(triangle[triangle.length-1][i]>answer)
				answer = triangle[triangle.length-1][i];
		
		return answer;
	}
}
