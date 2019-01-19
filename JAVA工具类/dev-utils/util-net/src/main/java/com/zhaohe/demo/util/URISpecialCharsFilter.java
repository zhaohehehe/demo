package com.zhaohe.demo.util;

public class URISpecialCharsFilter {
	private static final String[] specialChars = { " ", // 空格 +
			"\"", // 双引号 %22
			"#", // %23
			"%", // %25
			"&", // %26
			"+", // %2B
			"<", // %3C
			">", // %3E
			"\\", // 反斜杠 %5C
			"^", // %5E
			"{", // %7B
			"|", // %7C
			"}", // %7D
			"~",// %7E
	};
}
