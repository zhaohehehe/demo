package test.algorithm;
/**
 * 有n个人围成一圈，顺序排号。从第一个人开始报数（从1到3报数），凡报到3的人退出圈子，
 * 问最后留下的是原来第几号的那位。
 * @author zhaohe
 *2016年9月30日 下午4:57:53
 */
public class RemoveLoopBy3 {
	public void loopBy3(int n){
		boolean[] isRemoved=new boolean[n];
		//初始化，开始时都在圈内
		for(int i=0;i<isRemoved.length;i++){
			isRemoved[i]=false;
		}
		int startCall=0;//当前位置
		int count=0;//记录数的次数；
		int removeNum=0;//记录出圈的个数；
		
		while(removeNum<n){
			if(!isRemoved[startCall]){
				count++;
			}
			startCall++;
			startCall=startCall%n;
			//以下条件&&(!isRemoved[startCall])不可少，否则结果不正确！！！！！！！
			if(count==2&&(!isRemoved[startCall])){
				isRemoved[startCall]=true;
				System.out.println("第"+(startCall)+"出圈！");
				count=0;
				removeNum++;
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RemoveLoopBy3 rl=new RemoveLoopBy3();
		rl.loopBy3(1);
		System.out.println("\n");
		rl.loopBy3(2);
		System.out.println("\n");
		rl.loopBy3(3);
		System.out.println("\n");
		rl.loopBy3(8);
		System.out.println("\n");
		rl.loopBy3(9);
		System.out.println("\n");
		rl.loopBy3(10);
	}

}
