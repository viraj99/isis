package springapp.tests.async;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.val;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {AsyncDemoService.class})
@EnableAsync
class AsyncExecutionTest_NativeSpring {
	
	@Inject AsyncDemoService asyncDemoService;

	@Test
	void shouldRunAsync() {
		
		// we expect this to take no longer than ~250ms. (Each demo task sleeps 250ms.)
		assertTimeout(ofMillis(300), () -> {

			val futures = new Futures<String>();
			
			for(int i = 0; i < 25; i++) {
				val completable = asyncDemoService.asyncMethodWithReturnType();
				
				futures.add(completable);
			}
			
			val combinedFuture = futures.combine();
			
			combinedFuture.join();
			
	    });
		
	}
	

}