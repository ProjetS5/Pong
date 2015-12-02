package item;

import java.util.StringTokenizer;

public class Score {
	
	private static final int index = 1;
	
	private int p0;
	private int p1;
	private int max;
	
	public Score(int p0, int p1, int max){
		this.p0 = p0;
		this.p1 = p1;
		this.max = max;
	}
	
	public int getScoreP0(){
		return p0;
	}
	
	public int getScoreP1(){
		return p1;
	}
	
	public void setScoreP0(int p0){
		this.p0 = p0;
	}
	
	public void setScoreP1(int p1){
		this.p1 = p1;
	}
	
	public int getMax(){
		return max;
	}
	
	public void setMax(int max){
		this.max = max;
	}
	
	public int victory(){
		if(p0 >= max)
			return 0;
		if(p1 >= max)
			return 1;
		return -1;
	}
	
	public boolean update(String paquet){
		StringTokenizer st = new StringTokenizer(paquet, "/");
		while(st.hasMoreTokens()){
			String str = st.nextToken();
			if(str.contains("S")){
				if(str.charAt(index) == '1'){
					p0++;
					return true;
				}
			}
		}
		return false;
	}
	
	public String toString(){
		return (p0 + " / " + p1);
	}
}
