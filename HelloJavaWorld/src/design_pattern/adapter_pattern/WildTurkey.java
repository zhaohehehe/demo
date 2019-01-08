package test.design_pattern.adapter_pattern;

public class WildTurkey implements Turkey {

	@Override
	public void gobble() {
		System.out.println("»ğ¼¦ÔÚ¿©¿©½Ğ");

	}

	@Override
	public void fly() {
		System.out.println("»ğ¼¦ÔÚÂıÂı·É");

	}

}
