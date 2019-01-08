package 接口管理枚举;

public interface FoodOrganizeEnumByInterface {
	enum Fruit implements FoodOrganizeEnumByInterface{
		Apple,Orange,Banana;
	}
	enum Coffee implements FoodOrganizeEnumByInterface{
		BlackCoffe,Latte, Cappuccion;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FoodOrganizeEnumByInterface food=FoodOrganizeEnumByInterface.Coffee.BlackCoffe;
		Coffee food1=Coffee.BlackCoffe;
		FoodOrganizeEnumByInterface food2=Fruit.Apple;
	}

}
