package JAVA基础入门;

public class Teacher implements Comparable<Teacher>{
	public String id;
	/*public Runnable run=new Runnable(){
		public void run(){
			for(int i=0;i<10;i++){
				System.out.println("第"+i+"个老师");
			}
		}
	};*/
	
	public Teacher(){}
	public Teacher(String id){
		this.id=id;
	}
	public int compareTo(Teacher o){
		int v1 = Integer.valueOf(this.id); 
	    int v2 = Integer.valueOf(o.id); 
	    return v1 > v2 ? 1 : (v1 == v2 ? 0 : -1); 
	    }
	public static void main(String[] args) {
		/*Teacher teacher=new Teacher();
		Thread thread=new Thread(teacher.run);
		thread.start();*/
	}
}