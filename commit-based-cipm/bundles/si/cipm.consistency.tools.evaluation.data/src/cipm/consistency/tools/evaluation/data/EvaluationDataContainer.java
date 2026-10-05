package cipm.consistency.tools.evaluation.data;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import java.util.function.Supplier;

/**
 * Container for the complete data.
 * 
 * @author Martin Armbruster
 * @author Lukas Burgey
 */
public class EvaluationDataContainer {
    private static EvaluationDataContainer globalContainer;
    private final Map<String,Object> data = new HashMap<>();
    private static final String EXECUTION_TIMES_KEY = "executionTimes";
    private static final String IM_UPDATE_EVAL_KEY = "imUpdateEval";
    private static final String CHANGE_STATISTIC_KEY = "changeStatistic";
    private static final String CODE_MODEL_UPDATE_EVAL_KEY = "codeModelUpdateEval";
    private static final String CODE_MODEL_CORRECTNESS_KEY = "codeModelCorrectness";

    public static EvaluationDataContainer get() {
        if (globalContainer == null) {
            globalContainer = new EvaluationDataContainer();
        }
        return globalContainer;
    }

    public static void set(EvaluationDataContainer newContainer) {
        globalContainer = newContainer;
    }
    
    public static EvaluationDataContainer fromLegacy(
            LegacyEvaluationDataContainer legacy) {

        EvaluationDataContainer container = new EvaluationDataContainer();

        container.validated = legacy.isValidated();
        container.evaluationRan = legacy.isEvaluationRan();
        container.errorMessage = legacy.getErrorMessage();

        if (legacy.getChangeStatistic() != null) {
            container.put(
                    CHANGE_STATISTIC_KEY,
                    legacy.getChangeStatistic()
            );
        }

        if (legacy.getCodeModelCorrectness() != null) {
            container.put(
                    CODE_MODEL_CORRECTNESS_KEY,
                    legacy.getCodeModelCorrectness()
            );
        }

        if (legacy.getCodeModelUpdateEval() != null) {
            container.put(
                    CODE_MODEL_UPDATE_EVAL_KEY,
                    legacy.getCodeModelUpdateEval()
            );
        }

        if (legacy.getImUpdateEval() != null) {
            container.put(
                    IM_UPDATE_EVAL_KEY,
                    legacy.getImUpdateEval()
            );
        }

        if (legacy.getExecutionTimes() != null) {
            container.put(
                    EXECUTION_TIMES_KEY,
                    legacy.getExecutionTimes()
            );
        }

        container.pcmUpdateEvals = legacy.getPcmUpdateEvals();
        container.instrumentationData = legacy.getInstrumentationData();

        return container;
    }
    
    private void put(String key, Object value) {
        data.put(key, value);
    }

    private <T> T get(String key, Class<T> type) {
        Object value = data.get(key);

        if (value == null) {
            return null;
        }

        return type.cast(value);
    }
    
    private <T> T getOrCreate(String key, Class<T> type, Supplier<T> creator) {
        T value = get(key, type);

        if (value == null) {
            value = creator.get();
            put(key, value);
        }

        return value;
    }

    /**
     * the result of the binary evaluations
     */
    private boolean validated = false;
    private boolean evaluationRan = false;
    private String errorMessage = null;

//    private long evaluationTime = System.currentTimeMillis();
    
    
    private List<PcmUpdateEvalData> pcmUpdateEvals = new ArrayList<>();
//    private InstrumentationEvaluationData instrumentationData = new InstrumentationEvaluationData();
    private InstrumentationEvaluationData instrumentationData = null;
    

//    public long getEvaluationTime() {
//        return evaluationTime;
//    }
    
    public ChangeStatistic resetChangeStatistic() {
    	ChangeStatistic value = new ChangeStatistic();
    	put(CHANGE_STATISTIC_KEY,value);
    	return value;
    }    
    
    public ImUpdateEvalData resetImUpdateEval() {
    	ImUpdateEvalData value = new ImUpdateEvalData();
        put(IM_UPDATE_EVAL_KEY, value);
        return value;
    }

    public ChangeStatistic getChangeStatistic() {
    	return getOrCreate(
                CHANGE_STATISTIC_KEY,
                ChangeStatistic.class,
                ChangeStatistic::new
        );
    }

    public CodeModelUpdateEvalData getCodeModelUpdateEvalData() {
        return getOrCreate(
        		CODE_MODEL_UPDATE_EVAL_KEY,
        		CodeModelUpdateEvalData.class,
        		CodeModelUpdateEvalData::new
        		);
    }

    public ImUpdateEvalData getImUpdateEvalData() {
        return getOrCreate(
        		IM_UPDATE_EVAL_KEY,
                ImUpdateEvalData.class,
                ImUpdateEvalData::new
        		);
    }

    public InstrumentationEvaluationData getInstrumentationData() {
        return instrumentationData;
    }

    public ExecutionTimeData getExecutionTimes() {
        return getOrCreate(
        		EXECUTION_TIMES_KEY,
        		ExecutionTimeData.class,
        		ExecutionTimeData::new
        		);
    }

    public boolean valid() {
        return validated;
    }

    public void setSuccessful(boolean success) {
        this.validated = success;
    }

    public List<PcmUpdateEvalData> getPcmUpdateEvals() {
        return pcmUpdateEvals;
    }

    public CodeModelCorrectnessEval getCodeModelCorrectness() {
    	return getOrCreate(
                CODE_MODEL_CORRECTNESS_KEY,
                CodeModelCorrectnessEval.class,
                CodeModelCorrectnessEval::new
        );
    }

    public void setCodeModelCorrectness(CodeModelCorrectnessEval codeModelCorrectness) {
        put(CODE_MODEL_CORRECTNESS_KEY,codeModelCorrectness);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isEvaluationRan() {
        return evaluationRan;
    }

    public void setEvaluationRan(boolean evaluationRan) {
        this.evaluationRan = evaluationRan;
    }
}
