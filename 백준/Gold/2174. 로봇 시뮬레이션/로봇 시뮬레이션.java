import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

	public static class robot {
		int r;
		int c;
		int d;
		public robot(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}

	public static class ab {
		int who;
		String what;
		int count;
		public ab(int who, String what, int count) {
			this.who = who;
			this.what = what;
			this.count = count;
		}
	}
	static int n, m, num, absol;
	static int map[][], deltas[][] = {{ 0, 1 }, { 1, 0 }, { 0, -1 },{ -1, 0 } };
	static robot w[];
	static ab com[];
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		map=new int[n+1][m+1];
		st=new StringTokenizer(br.readLine());
		num=Integer.parseInt(st.nextToken());
		absol=Integer.parseInt(st.nextToken());
		w=new robot[num+1];
		com=new ab[absol];
		for(int i=1;i<=num;i++) {
			st=new StringTokenizer(br.readLine());
			int r=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			String d=st.nextToken();
			int di=0;
			if(d.equals("N"))
			{
				di=0;
			}
			else if(d.equals("E"))
			{
				di=1;
			}
			else if(d.equals("S"))
			{
				di=2;
			}else if(d.equals("W"))
			{
				di=3;
			}
			w[i]=new robot(r,c,di);
			map[r][c]=i;
		}
		for(int i=0;i<absol;i++) {
			st=new StringTokenizer(br.readLine());
			com[i]=new ab(Integer.parseInt(st.nextToken()),st.nextToken(),Integer.parseInt(st.nextToken()));
		}//입력 완료
		int wha=-1;
		int whatsol=-1;
		fi:for(int i=0;i<absol;i++) {
			int wh=com[i].who;
			if(com[i].what.equals("L")) {
				int k=com[i].count%4;
				for(int j=0;j<k;j++) {
					w[wh].d=(w[wh].d+3)%4;
				}
			}else if(com[i].what.equals("R")) {
				int k=com[i].count%4;
				for(int j=0;j<k;j++) {
					w[wh].d=(w[wh].d+1)%4;
				}
			}else if(com[i].what.equals("F")) {
				int k=com[i].count;
				int r=w[wh].r;
				int c=w[wh].c;
				map[r][c]=0;
				for(int j=0;j<k;j++) {
					r+=deltas[w[wh].d][0];
					c+=deltas[w[wh].d][1];
					if(r<1||r>n||c<1||c>m) {
						wha=wh;
						break fi;
					}
					if(map[r][c]!=0) {
						wha=wh;
						whatsol=map[r][c];
						break fi;
					}
				}
				map[r][c]=wh;
				w[wh].r=r;
				w[wh].c=c;
			}			
		}
		if(wha==-1) {
			System.out.println("OK");
		}else if(whatsol==-1){
			System.out.println("Robot "+wha+" crashes into the wall");
		}else
			System.out.println("Robot "+wha+" crashes into robot "+whatsol);
	}
}