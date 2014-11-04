package cs553_pa3.server;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.api.memcache.Stats;

public class GAECacheService {
	
	MemcacheService cacheService;
	
	public GAECacheService(){
		cacheService = MemcacheServiceFactory.getMemcacheService();
	}
	
	public void put(String key, String value){
		cacheService.put(key, value);
	}
	
	public String get(String key){
		return (String) cacheService.get(key);
	}
	
	public boolean remove(String key){
		return cacheService.delete(key);
	}
	
	public void removeAll(){
		cacheService.clearAll();
	}
	
	public boolean check(String key){
		return cacheService.contains(key);
	}
	
	public long sizeElem(){
		Stats cacheStats =  cacheService.getStatistics();
		return cacheStats.getItemCount();
	}
	
	public long sizeMB(){
		Stats cacheStats =  cacheService.getStatistics();
		return cacheStats.getTotalItemBytes()/(1024 * 1024);
	}
}
