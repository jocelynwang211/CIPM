package cipm.consistency.vsum.test.evaluationdata;

import cipm.consistency.tools.evaluation.data.EvaluationDataContainer;
import cipm.consistency.tools.evaluation.data.ExecutionTimeData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EvaluationDataContainerTest {
	@Test
	void getExecutionTimesReturnsSameInstance() {
		EvaluationDataContainer container = new EvaluationDataContainer();
		ExecutionTimeData first = container.getExecutionTimes();
		ExecutionTimeData second = container.getExecutionTimes();
		assertSame(first,second);
	}
	@Test
	void  getExecutionTimesKeepsUpdatedValues() {
		EvaluationDataContainer container = new EvaluationDataContainer();
		ExecutionTimeData first = container.getExecutionTimes();
		first.setChangePropagationTime(123L);
		ExecutionTimeData second = container.getExecutionTimes();
		assertEquals(123L,second.getChangePropagationTime());
	}
}
