package **;

enum State {
	SUCCESS {
		public boolean isSuccess() {
			return true;
		}
	},
	WARN {

		@Override
		public boolean isSuccess() {
			return true;
		}

	},
	FAIL {

		@Override
		public boolean isSuccess() {
			return false;
		}

	};
	public abstract boolean isSuccess();
}
public class EnumTest{
	public static void main(String[] args) {
		System.out.println(State.FAIL.isSuccess());
	}
}
