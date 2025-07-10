package BasicExceptionHandling;

public class OverDraftLimitUsedException extends RuntimeException {


	

	public String getMessage() {
		return "OverDraft Limit will be exceeded";
	}
	
}
