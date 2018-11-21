package zhaohe.study.es.performRequests;

import java.util.concurrent.CountDownLatch;

import org.apache.http.HttpEntity;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.ResponseListener;
import org.elasticsearch.client.RestClient;

/**
 * Multiple parallel asynchronous actions
 * 使用ES bulk 索引多个文档
 * @author zhaohe
 *
 */
public class ParallelIndex {
	public void bulk(RestClient restClient, HttpEntity[] documents) throws InterruptedException {
		final CountDownLatch latch = new CountDownLatch(documents.length);
		for (int i = 0; i < documents.length; i++) {
			Request request = new Request("PUT", "/posts/doc/" + i);
			// let's assume that the documents are stored in an HttpEntity array
			request.setEntity(documents[i]);
			restClient.performRequestAsync(request, new ResponseListener() {
				@Override
				public void onSuccess(Response response) {
					//这里可以添加代码内容：Process the returned response
					latch.countDown();
				}

				@Override
				public void onFailure(Exception exception) {
					//这里可以添加代码内容：Handle the returned exception, due to communication error or a response with status code that indicates an error
					latch.countDown();
				}
			});
		}
		latch.await();
	} 
}
