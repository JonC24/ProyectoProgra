	package data;

	import java.io.File;
	import java.io.IOException;
	import java.util.ArrayList;
	import java.util.List;

	import com.fasterxml.jackson.databind.ObjectMapper;
	import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

	public class JsonUtils <T>{

		private String filePath; //Ruta del JSON
		
		private static final ObjectMapper mapper = new ObjectMapper()
				.registerModule(new JavaTimeModule());//Manejo del JSON
		
		
		public JsonUtils(String path) {
			this.filePath = path;
		}
		
		public List<T> getAll(Class<T> temp) throws IOException {
		    File file = new File(this.filePath);

		    if (!file.exists() || file.length() == 0) {
		        return new ArrayList<>();
		    }

		    return this.mapper.readValue(
		        file,
		        mapper.getTypeFactory()
		              .constructCollectionType(List.class, temp)
		    );
		}
		
		public void deleteElement(T t, String d)throws IOException{
			List<T> temp = getAll((Class<T>) t.getClass());
			int index = 0;
			
			
			
			temp.remove(index);
		}
		
		public void saveElement(T t)throws IOException{
			List<T> temp = getAll((Class<T>) t.getClass());
			temp.add(t);
			this.mapper.writeValue(new File(filePath), temp);
		}

	}


