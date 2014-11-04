package cs553_pa3.server;

public class GAEFormService {
	
	GAECacheService gcs;
	
	public GAEFormService(){
		gcs = new GAECacheService();
	}
	
	
	public String uploadFile(String fileName, String Content){
		String result = "Uploaded Successfully";
		//byte[] encoded = Files.readAllBytes(Paths.get(fileName));
	//	byte[] value = 	Content.getBytes() ;
		gcs.put(fileName, Content);
		return result;
	}
	
	public String checkFile(String fileName){
		String result = "File Not Found";
		if(gcs.check(fileName))
		{
			result = "File exists in Cache";
		}
		return result;
	}
	
	public String findFile(String fileName){
		String result = "File Not Found";
		if(gcs.check(fileName))
		{
			result = gcs.get(fileName);
		}
		return result;
	}
	
	public String removeFile(String fileName){
		String result = "File Not deleted";
		if(gcs.remove(fileName))
		{
			result = "File deleted";
		}
		return result;
	}
	
	public String getAllFiles(){
		String result = "All FileNames";
		// Code here
		return result;
	}
}
