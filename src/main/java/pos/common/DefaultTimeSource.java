package pos.common;

public class DefaultTimeSource implements TimeSource {

	@Override
	public long currentTimeMillis() {
		return System.currentTimeMillis();
	}

}
