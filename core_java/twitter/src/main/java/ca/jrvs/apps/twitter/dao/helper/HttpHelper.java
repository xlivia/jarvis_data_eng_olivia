package ca.jrvs.apps.twitter.dao.helper;

import java.net.URI;
import org.apache.http.HttpResponse;

public interface HttpHelper {
  /**
   * Execute a HTTP Post call
   * @param uri parameter
   * @return return
   */
  HttpResponse httpPost(URI uri);
  /**
   * Execute a HTTP Get call
   * @param uri parameter
   * @return return
   */
  HttpResponse httpGet(URI uri);
}
