package fr.dawan.cfa2022.entities;

import java.util.List;
import java.util.Map;

public interface MyCustomRepository {

	public List<User> searchBy(Map<String, String[]> params);
}
