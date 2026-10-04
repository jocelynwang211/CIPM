package cipm.consistency.vsum.test.evaluationdata;

import cipm.consistency.tools.evaluation.data.EvaluationDataContainer;
import cipm.consistency.tools.evaluation.data.ExecutionTimeData;
import cipm.consistency.tools.evaluation.data.ImUpdateEvalData;
import cipm.consistency.tools.evaluation.data.ChangeStatistic;
import cipm.consistency.tools.evaluation.data.CodeModelUpdateEvalData;
import cipm.consistency.tools.evaluation.data.CodeModelCorrectnessEval;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

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
	
	
	@Test
	void getImUpdateEvalDataReturnsSameInstance() {
		EvaluationDataContainer container = new  EvaluationDataContainer();
		ImUpdateEvalData first = container.getImUpdateEvalData();
		ImUpdateEvalData second = container.getImUpdateEvalData();
		assertSame(first,second);
	}
	@Test
	void resetImUpdateEvalReplacesInstance() {
		EvaluationDataContainer container = new  EvaluationDataContainer();
		ImUpdateEvalData first = container.getImUpdateEvalData();
		ImUpdateEvalData reset = container.resetImUpdateEval();
		ImUpdateEvalData afterReset = container.getImUpdateEvalData();
		assertNotSame(first,reset);
		assertSame(reset,afterReset);
	}
	
	
	@Test
	void getChangeStatisticReturnsSameInstance() {
		EvaluationDataContainer container = new EvaluationDataContainer();

	    ChangeStatistic first = container.getChangeStatistic();
	    ChangeStatistic second = container.getChangeStatistic();

	    assertSame(first, second);
	}
	@Test
	void resetChangeStatisticReplacesInstance() {
	    EvaluationDataContainer container = new EvaluationDataContainer();

	    ChangeStatistic first = container.getChangeStatistic();
	    ChangeStatistic reset = container.resetChangeStatistic();
	    ChangeStatistic afterReset = container.getChangeStatistic();

	    assertNotSame(first, reset);
	    assertSame(reset, afterReset);
	}
	
	
	@Test
	void getCodeModelUpdateEvalDataReturnsSameInstance() {
		EvaluationDataContainer container = new EvaluationDataContainer();
		CodeModelUpdateEvalData first = container.getCodeModelUpdateEvalData();
	    CodeModelUpdateEvalData second = container.getCodeModelUpdateEvalData();
	    assertSame(first, second);
	}
	
	
	@Test
	void getCodeModelCorrectnessReturnsSameInstance() {
		EvaluationDataContainer container = new EvaluationDataContainer();
	    CodeModelCorrectnessEval first = container.getCodeModelCorrectness();
	    CodeModelCorrectnessEval second = container.getCodeModelCorrectness();
	    assertSame(first, second);
	}
	@Test
	void setCodeModelCorrectnessReplacesInstance() {
	    EvaluationDataContainer container = new EvaluationDataContainer();
	    CodeModelCorrectnessEval first = container.getCodeModelCorrectness();
	    CodeModelCorrectnessEval replacement = new CodeModelCorrectnessEval();
	    container.setCodeModelCorrectness(replacement);
	    CodeModelCorrectnessEval afterSet = container.getCodeModelCorrectness();
	    assertNotSame(first, replacement);
	    assertSame(replacement, afterSet);
	}
}
