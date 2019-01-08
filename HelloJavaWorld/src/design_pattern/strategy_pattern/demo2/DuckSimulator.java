package test.design_pattern.strategy_pattern.demo2;

import test.design_pattern.strategy_pattern.demo2.Duck;
import test.design_pattern.strategy_pattern.demo2.FlyNoWay;
import test.design_pattern.strategy_pattern.demo2.FlyWithSwings;
import test.design_pattern.strategy_pattern.demo2.Quack;
import test.design_pattern.strategy_pattern.demo2.RedHeadDuck;
import test.design_pattern.strategy_pattern.demo2.RubberDuck;
import test.design_pattern.strategy_pattern.demo2.Squeak;

public class DuckSimulator {
	public static void main(String[] args) {
		Duck readHeadDuck=new RedHeadDuck();
		readHeadDuck.setFlyBehavior(new FlyWithSwings());
		readHeadDuck.setQuackBehavior(new Quack());
		readHeadDuck.display();
		readHeadDuck.performFly();
		readHeadDuck.performQuack();
		System.out.println("===============================");
		Duck rubberDuck=new RubberDuck();
		rubberDuck.setFlyBehavior(new FlyNoWay());
		rubberDuck.setQuackBehavior(new Squeak());
		rubberDuck.display();
		rubberDuck.performFly();
		rubberDuck.performQuack();
		System.out.println("===============================");
		Duck decoyDuck=new DecoyDuck();
		decoyDuck.setFlyBehavior(new FlyNoWay());
		decoyDuck.setQuackBehavior(new MuteQuack());
		decoyDuck.display();
		decoyDuck.performFly();
		decoyDuck.performQuack();
		
	}
}
