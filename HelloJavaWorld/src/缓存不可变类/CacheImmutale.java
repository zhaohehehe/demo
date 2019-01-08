package 缓存不可变类;

/**
 * 缓存实例的不可变类
 */
public class CacheImmutale {
	private static int MAX_SIZE=10;
	private static int pos=0;
	private static CacheImmutale[] cache = new CacheImmutale[MAX_SIZE];
	private final String name;
	private CacheImmutale(String name){
		this.name=name;
	}
	public static CacheImmutale valueOf(String name){
		for(int i=0;i<MAX_SIZE;i++){
			if(cache[i]!=null && cache[i].name.equals(name)){
				return cache[i];
			}
		}
		//如果缓存数组已经存满，新的实例覆盖第一个旧实例
		if(pos==MAX_SIZE){
			cache[0]=new CacheImmutale(name);
			pos=1;
		}
		cache[pos++]=new CacheImmutale(name);
		return cache[pos-1];
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CacheImmutale other = (CacheImmutale) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	public static void main(String[] args) {
		CacheImmutale ins1=CacheImmutale.valueOf("hello");
		CacheImmutale ins2=CacheImmutale.valueOf("hello");
		System.out.println(ins1==ins2);
		System.out.println(ins1.equals(ins2));
	}
}

