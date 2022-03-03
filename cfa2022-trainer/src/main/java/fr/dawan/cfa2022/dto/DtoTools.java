package fr.dawan.cfa2022.dto;

import org.modelmapper.ModelMapper;

public class DtoTools {

	private static ModelMapper myMapper = new ModelMapper();
	
	public static <TSource,TDestination> TDestination convert(TSource obj, Class<TDestination> clazz) {
		
		//ajouter les règles personnalisées
//		myMapper.typeMap(User.class, UserDto.class)
//		.addMappings(mapper->{
//			mapper.map(src->src.getFirstName(), UserDto::setFirstName);
//			mapper.map(src->src.getLastName(), UserDto::setLastName);
//		});
		
		return myMapper.map(obj, clazz);
	}
}
