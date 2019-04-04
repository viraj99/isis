package springapp.tests.async;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.val;

@Service
public class AsyncDemoService {

	@Async @Transactional
	public CompletableFuture<String> asyncMethodWithReturnType() {
		
		val taskResult = new AsyncDemoTask().get();
		
		return new AsyncResult<String>(taskResult).completable();
	}
	
}