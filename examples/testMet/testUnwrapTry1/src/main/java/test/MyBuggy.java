package test;

public class MyBuggy {

	public Integer operation(int i1, int i2, String type) {

		try {// wrong wrap
			if (type == null)
				throw new IllegalArgumentException("type is null");
		} catch (Exception e) {
		}

		if ("*".equals(type)) {
			return i1 * i2;

		}

		if ("+".equals(type)) {
			return i1 + i2;

		}

		if ("-".equals(type)) {
			return i1 - i2;

		}

		if ("gr".equals(type)) {

			if (i2 > i1)
				return i2;

			if (i1 >= i2)
				return i1;

		}

		return null;
	}

}
