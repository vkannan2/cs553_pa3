package cs553_pa3.server;

import com.google.appengine.tools.cloudstorage.GcsFilename;

public class GAEFormService {
	
	GAECacheService gcs;
	CloudStorageService css;
	
	public static final String bucketName = "pa3-india.appspot.com";
	
	public GAEFormService(){
		gcs = new GAECacheService();
		css = new CloudStorageService();
	}
	
	
	public String uploadFile(String fileName, String Content){
		String result = "Upload failed";
		//byte[] encoded = Files.readAllBytes(Paths.get(fileName));
	//	byte[] value = 	Content.getBytes() ;
		try
		{
			GcsFilename csfileName = new GcsFilename(bucketName, fileName);
			css.writeToFile(csfileName, Content.getBytes());
			gcs.put(fileName, Content);
			result = "Uploaded Successfully";
		}
		catch (Exception e)
		{
			result += " "+ e.getMessage();
		}
		return result;
	}
	
	public String checkFile(String fileName){
		String result = "File Not Found";
		if(gcs.check(fileName))
		{
			result = "File exists in Cache";
		}
		else 
		{
			try{
				GcsFilename csfileName = new GcsFilename(bucketName, fileName);
				if(css.checkForFile(csfileName)){
					result =  "File exists in Cloud";
				}
					
			}
			catch (Exception e)
			{
				result += " " + e.getMessage(); 
			}
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
