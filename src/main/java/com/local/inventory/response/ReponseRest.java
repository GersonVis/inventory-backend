package com.local.inventory.response;

import java.util.ArrayList;
import java.util.HashMap;

public class ReponseRest {
	private ArrayList<HashMap<String, String>> metadata = new ArrayList<HashMap<String, String>>();

	public ArrayList<HashMap<String, String>> getMetadata() {
		return metadata;
	}

	public void setMetada(String type, String code, String date) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("type", type);
		map.put("code", code);
		map.put("date", date);
		
		//agregamos el diccionario al metadato
		metadata.add(map);
	}
	 

}
