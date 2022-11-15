package utils;

import java.util.Map;

import Beans.Product;

public class ParseToBean {
	public static Product partToBean(Map<String, String> map) {
		String[] keys = new String[map.size()];
		String[] values = new String[map.size()];
		Product product = new Product();
		int i = 0;
		for (String key : map.keySet()) {
			keys[i++] = key;
		}
		i = 0;
		for (String value : map.values()) {
			values[i++] = value;
		}
		for (int j = 0; j < keys.length; j++) {
			if (keys[j].equals("codeProduct")) {
				product.setId(values[j]);
			} else if (keys[j].equals("nameProduct")) {
				product.setName(values[j]);
			} else if (keys[j].equals("brand")) {
				product.setBrand(values[j]);
			} else if (keys[j].equals("description")) {
				product.setDescription(values[j]);
			} else if (keys[j].equals("thumbnail")) {
				product.setThumbnail(values[j]);
			}else if(keys[j].equals("price")) {
				product.setPrice(values[j]);
			}
		}
		return product;
	}
}
